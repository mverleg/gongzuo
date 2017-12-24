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
import jit.code.ScopedBlock
import jit.code.UnaryArithmCode
import jit.code.VarCode
import jit.common.BinaryArithmOperation
import jit.common.Compiler
import jit.common.Instruction
import jit.common.InstructionList
import jit.common.MutableScopeStack
import jit.common.ScopeStack
import jit.common.UnaryArithmOperation
import jit.instructions.AllocateInstruction
import jit.instructions.CallInstruction
import jit.instructions.FunctionInstruction
import jit.instructions.PrelimFunctionInstruction
import jit.instructions.ValueInstruction
import jit.instructions.WriteInstruction

class PrelimCompiler: Compiler {

    val scopes: MutableScopeStack = MutableScopeStack()

    override fun compile(func: FunDefCode): FunctionInstruction {
        scopes.push(func)
        val instructions: MutableList<Instruction<Int>> = mutableListOf()
        try {
            instructions.add(func.body.toCompiler(this))
            // Don't really want the deallocations to be inside the try, but want them before the finally...
            for (deallocation in this.scopes().nearest().generateDeallocations()) {
                instructions.add(deallocation)
            }
        } finally {
            scopes.pop()
        }
        // Function parameters should also be deallocated, no?
        val instr = InstructionList(instructions[0], *instructions.subList(1, instructions.size).toTypedArray())
        return PrelimFunctionInstruction(instr, func.name, func.parameters)
    }

    override fun compile(binArithmCode: BinArithmCode): Instruction<Int> {
        TODO("not implemented") //To change bod of created functions use File | Settings | File Templates.
    }

    override fun compile(unaryArithmCode: UnaryArithmCode): Instruction<Int> {
        when (unaryArithmCode.operation) {
            UnaryArithmOperation.NEG -> return this.compile(BinArithmCode(BinaryArithmOperation.SUB, ConstCode(0), unaryArithmCode))
            UnaryArithmOperation.SQR -> return this.compile(BinArithmCode(BinaryArithmOperation.MUL, unaryArithmCode, unaryArithmCode))
        }
    }

    override fun compile(varCode: VarCode): Instruction<Int> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun compile(binaryLogicCode: BinaryLogicCode): Instruction<Boolean> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun compile(ifCode: IfCode): Instruction<Int> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
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

    override fun compile(assignmentCode: AssignmentCode): Instruction<Int> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun compile(declarationCode: DeclarationCode): Instruction<Int> {
        AllocateInstruction(declarationCode.assignment.variable.name)
        WriteInstruction(declarationCode.assignment.variable.name, declarationCode.assignment.value.toCompiler(this))
        TODO("deallocate")
    }

    override fun compile(funCallCode: FunCallCode): Instruction<Int> {
        return CallInstruction(funCallCode.name, funCallCode.args.map { it.toCompiler(this) })
    }

    override fun scopes(): ScopeStack {
        return scopes
    }
}

