package jit.prelim

import jit.code.AssignmentCode
import jit.code.BinArithmCode
import jit.code.BinaryLogicCode
import jit.code.CodeCombi
import jit.code.ConstCode
import jit.code.DeclarationCode
import jit.code.FunCallCode
import jit.code.FunDefCode
import jit.code.IfCode
import jit.code.ReadCode
import jit.code.ScopedBlock
import jit.code.UnaryArithmCode
import jit.code.VariableMention
import jit.common.BinaryArithmOperation
import jit.common.CompileInvalidCodeError
import jit.common.Compiler
import jit.common.Instruction
import jit.instructions.InstructionList
import jit.common.MutableScopeStack
import jit.common.ScopeStack
import jit.common.UnaryArithmOperation
import jit.instructions.AllocateInstruction
import jit.instructions.ArithmeticInstruction
import jit.instructions.CallInstruction
import jit.instructions.PrelimFunctionInstruction
import jit.common.Type
import jit.instructions.BlockSet
import jit.instructions.ReadInstruction
import jit.instructions.ValueInstruction
import jit.instructions.Variable
import jit.instructions.WriteInstruction

class PrelimCompiler: Compiler {

    val scopes: MutableScopeStack = MutableScopeStack()

    override fun compile(func: FunDefCode): PrelimFunctionInstruction {
        scopes.push(func)
        val instructions: MutableList<Instruction<Int>> = mutableListOf()
        try {
            instructions.add(func.body.toCompiler(this))
            // Don't really want the deallocations to be inside the try, but want them before the finally...
            for (deallocation in this.scopes().nearest().generateDeallocations()) {
                instructions.add(deallocation)
            }
        } finally {
            println(scopes.toText()) // TODO
            scopes.pop()
        }
        // Function parameters should also be deallocated, no?
        val blocks = BlockSet(instructions[0], *instructions.subList(1, instructions.size).toTypedArray())
        val params = func.parameters.map{ Variable(it, Type()) }
        return PrelimFunctionInstruction(blocks, func.name, params)
    }

    override fun compile(binArithmCode: BinArithmCode): Instruction<Int> {
        return ArithmeticInstruction(
                binArithmCode.operation,
                binArithmCode.leftCode.toCompiler(this),
                binArithmCode.rightCode.toCompiler(this)
        )
    }

    override fun compile(unaryArithmCode: UnaryArithmCode): Instruction<Int> {
        when (unaryArithmCode.operation) {
            UnaryArithmOperation.NEG -> return this.compile(BinArithmCode(BinaryArithmOperation.SUB, ConstCode(0), unaryArithmCode.code))
            UnaryArithmOperation.SQR -> {
                val tmpVar = VariableMention(scopes.nearest().createTempVar(Type()).name)
                return this.compile(CodeCombi(
                    AssignmentCode(tmpVar, unaryArithmCode.code),
                    last=BinArithmCode(BinaryArithmOperation.MUL, ReadCode(tmpVar), ReadCode(tmpVar))
                ))
            }
        }
    }

    override fun compile(binaryLogicCode: BinaryLogicCode): Instruction<Boolean> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun compile(ifCode: IfCode): Instruction<Int> {

        val condVar = scopes.nearest().createTempVar(Type(/* Bool */))
        val ifResVar = scopes.nearest().createTempVar(Type(/* RT\Int */))

        // TODO: register these blocks somehow
        // TODO: make sure next steps add to endBlock
        val thenBlock = TODO()
        val elseBlock = TODO()
        val endBlock = TODO()

//        return ConditionalJumpInstruction(
//                null,
//                null,
//                null
//        )
    }

    override fun compile(constCode: ConstCode): Instruction<Int> {
        return ValueInstruction(constCode.value)
    }

    override fun compile(codeCombi: CodeCombi): InstructionList {
        val instructions: MutableList<Instruction<Int>> = mutableListOf()
        for (code in codeCombi) {
            instructions.add(code.toCompiler(this))
        }
        return InstructionList(instructions.first(), *instructions.subList(1, instructions.size).toTypedArray())
    }

    override fun compile(scopedBlock: ScopedBlock): InstructionList {
        scopes.push(scopedBlock)
        val instructions: MutableList<Instruction<Int>> = mutableListOf()
        try {
            for (code in scopedBlock) {
                instructions.add(code.toCompiler(this))
            }
            // Don't really want the deallocations to be inside the try, but want them before the finally...
            for (deallocation in this.scopes().nearest().generateDeallocations()) {
                instructions.add(deallocation)
            }
        } finally {
            scopes.pop()
        }
        return InstructionList(instructions.first(), *instructions.subList(1, instructions.size).toTypedArray())
    }

    override fun compile(declarationCode: DeclarationCode): Instruction<Int> {
        val existingVar = scopes().getVar(declarationCode.assignment.variable.name)
        if (existingVar != null) {
            TODO("currently shadowing is not allowed, it should probably be allowed in the future")
            throw CompileInvalidCodeError("name '${existingVar.name}' has already been declared")
        }
        val newVar = Variable(declarationCode.assignment.variable, Type())
        scopes().nearest().register(newVar)
        return InstructionList(
                AllocateInstruction(newVar),
                declarationCode.assignment.toCompiler(this)
        )
    }

    override fun compile(assignmentCode: AssignmentCode): Instruction<Int> {
        val existingVar = scopes().getVar(assignmentCode.variable.name)
        if (existingVar == null) {
            throw CompileInvalidCodeError("trying to assign to name '${assignmentCode.variable.name}' but it has not been declared")
        }
        return WriteInstruction(existingVar, assignmentCode.value.toCompiler(this))
    }

    override fun compile(readCode: ReadCode): Instruction<Int> {
        return ReadInstruction(Variable(readCode.variable, Type()))
    }

    override fun compile(funCallCode: FunCallCode): Instruction<Int> {
        return CallInstruction(funCallCode.name, funCallCode.args.map { it.toCompiler(this) })
    }

    override fun scopes(): ScopeStack {
        return scopes
    }
}

