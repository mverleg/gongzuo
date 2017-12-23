package jit.code

import jit.common.Code
import jit.common.Compiler
import jit.common.InstructionList
import jit.common.Statement

/**
 * Some statements followed by an expression.
 */
class CodeCombi<RT>(vararg statements: Statement<RT>, code: Code<RT>): Code<RT> {
    override fun toCompiler(compiler: Compiler): InstructionList {
        return compiler.compile(this)
    }
}

