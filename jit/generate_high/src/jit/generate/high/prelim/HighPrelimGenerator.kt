package jit.high.prelim

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
import jit.common.Inter
import jit.instructions.InterList
import jit.common.MutableScopeStack
import jit.common.ScopeStack
import jit.common.UnaryArithmOperation
import jit.instructions.AllocateInter
import jit.instructions.ArithmeticInter
import jit.instructions.CallInter
import jit.instructions.PrelimFunctionInter
import jit.common.Type
import jit.instructions.BlockSet
import jit.instructions.ReadInter
import jit.instructions.ValueInter
import jit.instructions.Variable
import jit.instructions.WriteInter

class HighPrelimGenerator: HighGenerator {

    val scopes: MutableScopeStack = MutableScopeStack()

    override fun compile(func: FunDefCode): PrelimFunctionInter {
        scopes.push(func)
        val inters: MutableList<Inter<Int>> = mutableListOf()
        try {
            inters.add(func.body.toCompiler(this))
            // Don't really want the deallocations to be inside the try, but want them before the finally...
            for (deallocation in this.scopes().nearest().generateDeallocations()) {
                inters.add(deallocation)
            }
        } finally {
            println(scopes.toText()) // TODO
            scopes.pop()
        }
        // Function parameters should also be deallocated, no?
        val blocks = BlockSet(inters[0], *inters.subList(1, inters.size).toTypedArray())
        val params = func.parameters.map{ Variable(it, Type()) }
        return PrelimFunctionInter(blocks, func.name, params)
    }

    override fun compile(binArithmCode: BinArithmCode): Inter<Int> {
        return ArithmeticInter(
                binArithmCode.operation,
                binArithmCode.leftCode.toCompiler(this),
                binArithmCode.rightCode.toCompiler(this)
        )
    }

    override fun compile(unaryArithmCode: UnaryArithmCode): Inter<Int> {
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

    override fun compile(binaryLogicCode: BinaryLogicCode): Inter<Boolean> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun compile(ifCode: IfCode): Inter<Int> {

        val condVar = scopes.nearest().createTempVar(Type(/* Bool */))
        val ifResVar = scopes.nearest().createTempVar(Type(/* RT\Int */))

        // TODO: register these blocks somehow
        // TODO: make sure next steps add to endBlock
        val thenBlock = TODO()
        val elseBlock = TODO()
        val endBlock = TODO()

//        return ConditionalJumpInter(
//                null,
//                null,
//                null
//        )
    }

    override fun compile(constCode: ConstCode): Inter<Int> {
        return ValueInter(constCode.value)
    }

    override fun compile(codeCombi: CodeCombi): InterList {
        val inters: MutableList<Inter<Int>> = mutableListOf()
        for (code in codeCombi) {
            inters.add(code.toCompiler(this))
        }
        return InterList(inters.first(), *inters.subList(1, inters.size).toTypedArray())
    }

    override fun compile(scopedBlock: ScopedBlock): InterList {
        scopes.push(scopedBlock)
        val inters: MutableList<Inter<Int>> = mutableListOf()
        try {
            for (code in scopedBlock) {
                inters.add(code.toCompiler(this))
            }
            // Don't really want the deallocations to be inside the try, but want them before the finally...
            for (deallocation in this.scopes().nearest().generateDeallocations()) {
                inters.add(deallocation)
            }
        } finally {
            scopes.pop()
        }
        return InterList(inters.first(), *inters.subList(1, inters.size).toTypedArray())
    }

    override fun compile(declarationCode: DeclarationCode): Inter<Int> {
        val existingVar = scopes().getVar(declarationCode.assignment.variable.name)
        if (existingVar != null) {
            TODO("currently shadowing is not allowed, it should probably be allowed in the future")
            throw CompileInvalidCodeError("name '${existingVar.name}' has already been declared")
        }
        val newVar = Variable(declarationCode.assignment.variable, Type())
        scopes().nearest().register(newVar)
        return InterList(
                AllocateInter(newVar),
                declarationCode.assignment.toCompiler(this)
        )
    }

    override fun compile(assignmentCode: AssignmentCode): Inter<Int> {
        val existingVar = scopes().getVar(assignmentCode.variable.name)
        if (existingVar == null) {
            throw CompileInvalidCodeError("trying to assign to name '${assignmentCode.variable.name}' but it has not been declared")
        }
        return WriteInter(existingVar, assignmentCode.value.toCompiler(this))
    }

    override fun compile(readCode: ReadCode): Inter<Int> {
        return ReadInter(Variable(readCode.variable, Type()))
    }

    override fun compile(funCallCode: FunCallCode): Inter<Int> {
        return CallInter(funCallCode.name, funCallCode.args.map { it.toCompiler(this) })
    }

    override fun scopes(): ScopeStack {
        return scopes
    }
}

