package jit.common

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
import jit.instructions.FunctionInstruction

interface Compiler {
    fun compile(func: FunDefCode): FunctionInstruction
    fun compile(binArithmCode: BinArithmCode): Instruction<Int>
    fun compile(unaryArithmCode: UnaryArithmCode): Instruction<Int>
    fun compile(varCode: VarCode): Instruction<Int>
    fun compile(binaryLogicCode: BinaryLogicCode): Instruction<Boolean>
    fun compile(ifCode: IfCode): Instruction<Int>
    fun compile(constCode: ConstCode): Instruction<Int>
    fun <RT> compile(codeCombi: CodeCombi<RT>): InstructionList
    fun compile(assignmentCode: AssignmentCode): Instruction<Int>
    fun compile(funCallCode: FunCallCode): Instruction<Int>
}

