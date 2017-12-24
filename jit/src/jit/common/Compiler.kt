package jit.common

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
import jit.instructions.FunctionInstruction

interface Compiler {
    fun compile(func: FunDefCode): FunctionInstruction
    fun compile(binArithmCode: BinArithmCode): Instruction<Int>
    fun compile(unaryArithmCode: UnaryArithmCode): Instruction<Int>
    fun compile(binaryLogicCode: BinaryLogicCode): Instruction<Boolean>
    fun compile(ifCode: IfCode): Instruction<Int>
    fun compile(constCode: ConstCode): Instruction<Int>
    fun compile(codeCombi: CodeCombi): InstructionList
    fun compile(readCode: ReadCode): Instruction<Int>
    fun compile(assignmentCode: AssignmentCode): Instruction<Int>
    fun compile(declarationCode: DeclarationCode): Instruction<Int>
    fun compile(scopedBlock: ScopedBlock): Instruction<Int>
    fun compile(funCallCode: FunCallCode): Instruction<Int>

    fun scopes(): ScopeStack
}

