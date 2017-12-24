package jit.code

import jit.common.Code
import jit.common.Compiler
import jit.common.Instruction
import jit.common.Statement

/**
 * Some statements followed by an expression (which produces the return value).
 */
class CodeCombi<RT>(vararg val statements: Statement<Int>, val last: Code<RT>): Code<Int>, Iterable<Statement<Int>> {
    override fun toCompiler(compiler: Compiler): Instruction<Int> {
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

    override fun iterator(): Iterator<Statement<Int>> {
        return statements.iterator()
    }
}

