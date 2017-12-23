package jit.common

import jit.code.ArithmBinOp
import jit.code.ArithmUnOp
import jit.code.AssignmentCode
import jit.code.CodeCombi
import jit.code.ConstCode
import jit.code.FunCallCode
import jit.code.IfCode
import jit.code.LogicOpCode
import jit.code.VarCode

interface Compiler {
    fun compile(arithmBinOp: ArithmBinOp): InstructionList
    fun compile(arithmBinOp: ArithmUnOp): InstructionList
    fun compile(varCode: VarCode): InstructionList
    fun compile(logicOpCode: LogicOpCode): InstructionList
    fun compile(ifCode: IfCode): InstructionList
    fun compile(constCode: ConstCode): InstructionList
    fun <RT> compile(codeCombi: CodeCombi<RT>): InstructionList
    fun compile(assignmentCode: AssignmentCode): InstructionList
    fun compile(funCallCode: FunCallCode): InstructionList
}

