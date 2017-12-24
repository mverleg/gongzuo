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
    fun compile(binArithmCode: BinArithmCode): InstructionList
    fun compile(unaryArithmCode: UnaryArithmCode): InstructionList
    fun compile(varCode: VarCode): InstructionList
    fun compile(binaryLogicCode: BinaryLogicCode): InstructionList
    fun compile(ifCode: IfCode): InstructionList
    fun compile(constCode: ConstCode): InstructionList
    fun <RT> compile(codeCombi: CodeCombi<RT>): InstructionList
    fun compile(assignmentCode: AssignmentCode): InstructionList
    fun compile(funCallCode: FunCallCode): Instruction<Int>
}

