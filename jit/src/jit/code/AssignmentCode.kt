package jit.code

import jit.common.Code
import jit.common.Compiler
import jit.common.InstructionList
import jit.common.Statement

class AssignmentCode(val variable: VarCode, val value: Code<Int>): Statement<Int> {
    override fun toCompiler(compiler: Compiler): InstructionList {
        return compiler.compile(this)
    }
}

