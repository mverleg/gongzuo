package jit.prelim

import jit.code.AssignmentCode
import jit.code.BinArithmCode
import jit.code.BinaryLogicCode
import jit.code.CodeCombi
import jit.code.ConstCode
import jit.code.FunCallCode
import jit.code.FunDefCode
import jit.code.IfCode
import jit.code.UnaryArithmCode
import jit.code.VarCode
import jit.common.BinaryArithmOperation
import jit.common.Compiler
import jit.common.Instruction
import jit.common.InstructionList
import jit.common.UnaryArithmOperation
import jit.instructions.CallInstruction
import jit.instructions.FunctionInstruction
import jit.instructions.PrelimFunctionInstruction
import jit.instructions.ValueInstruction

class PrelimCompiler: Compiler {
    override fun compile(func: FunDefCode): FunctionInstruction {
        return PrelimFunctionInstruction(func.body.toCompiler(this), func.name, func.parameters)
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

    override fun <RT> compile(codeCombi: CodeCombi<RT>): InstructionList {
        val instructions: MutableList<Instruction<Int>> = mutableListOf()
        for (code in codeCombi) {
            instructions.add(code.toCompiler(this))
        }
        return InstructionList(instructions.first(), *instructions.subList(1, instructions.size).toTypedArray())
    }

    override fun compile(assignmentCode: AssignmentCode): Instruction<Int> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun compile(funCallCode: FunCallCode): Instruction<Int> {
        return CallInstruction(funCallCode.name, funCallCode.args.map { it.toCompiler(this) })
    }
}

