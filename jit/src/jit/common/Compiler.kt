package jit.common

import jit.code.BinArithmCode
import jit.code.AssignmentCode
import jit.code.CodeCombi
import jit.code.ConstCode
import jit.code.FunCallCode
import jit.code.IfCode
import jit.code.BinaryLogicCode
import jit.code.UnaryArithmCode
import jit.code.VarCode

interface Compiler {
    fun compile(binArithmCode: BinArithmCode): InstructionList
    fun compile(unaryArithmCode: UnaryArithmCode): InstructionList
    fun compile(varCode: VarCode): InstructionList
    fun compile(binaryLogicCode: BinaryLogicCode): InstructionList
    fun compile(ifCode: IfCode): InstructionList
    fun compile(constCode: ConstCode): InstructionList
    fun <RT> compile(codeCombi: CodeCombi<RT>): InstructionList
    fun compile(assignmentCode: AssignmentCode): InstructionList
    fun compile(funCallCode: FunCallCode): InstructionList
}

