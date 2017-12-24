package jit.code

import jit.common.Code
import jit.common.Compiler
import jit.common.InstructionList
import jit.common.Statement

/**
 * Some statements followed by an expression (which produces the return value).
 */
class CodeCombi<RT>(vararg val statements: Statement<RT>, val last: Code<RT>): Code<RT> {
    override fun toCompiler(compiler: Compiler): InstructionList {
        return compiler.compile(this)
    }

    override fun toText(): CharSequence {
        val text = StringBuilder()
        for (statement in statements) {
            text.append(statement.toText())
        }
        text.append(last.toText())
        return text
    }
}

