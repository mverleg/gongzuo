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
import jit.instructions.FunctionInter
import jit.instructions.InterList

interface Compiler {
    fun compile(func: FunDefCode): FunctionInter
    fun compile(binArithmCode: BinArithmCode): Inter<Int>
    fun compile(unaryArithmCode: UnaryArithmCode): Inter<Int>
    fun compile(binaryLogicCode: BinaryLogicCode): Inter<Boolean>
    fun compile(ifCode: IfCode): Inter<Int>
    fun compile(constCode: ConstCode): Inter<Int>
    fun compile(codeCombi: CodeCombi): InterList
    fun compile(readCode: ReadCode): Inter<Int>
    fun compile(assignmentCode: AssignmentCode): Inter<Int>
    fun compile(declarationCode: DeclarationCode): Inter<Int>
    fun compile(scopedBlock: ScopedBlock): Inter<Int>
    fun compile(funCallCode: FunCallCode): Inter<Int>

    fun scopes(): ScopeStack
}

