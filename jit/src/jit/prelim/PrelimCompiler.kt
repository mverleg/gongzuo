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
import jit.common.Compiler
import jit.common.Instruction
import jit.common.InstructionList
import jit.instructions.ArithmeticInstruction
import jit.instructions.CallInstruction
import jit.instructions.FunctionInstruction
import jit.instructions.PrelimFunctionInstruction

class PrelimCompiler: Compiler {
    override fun compile(func: FunDefCode): FunctionInstruction {
        return PrelimFunctionInstruction(func.body.toCompiler(this), func.name, func.parameters)
    }

    override fun compile(binArithmCode: BinArithmCode): InstructionList {
        return InstructionList(
                ArithmeticInstruction()
        )
    }

    override fun compile(unaryArithmCode: UnaryArithmCode): InstructionList {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun compile(varCode: VarCode): InstructionList {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun compile(binaryLogicCode: BinaryLogicCode): InstructionList {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun compile(ifCode: IfCode): InstructionList {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun compile(constCode: ConstCode): InstructionList {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun <RT> compile(codeCombi: CodeCombi<RT>): InstructionList {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun compile(assignmentCode: AssignmentCode): InstructionList {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun compile(funCallCode: FunCallCode): Instruction<Int> {
        return CallInstruction(funCallCode.name, funCallCode.args.map { it.toCompiler(this) })
    }
}

