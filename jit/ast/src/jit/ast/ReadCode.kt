package jit.ast

import jit.common.Code
import jit.common.Compiler
import jit.common.Inter

/**
 * Read the value of a variable.
 */
open class ReadCode(val variable: VariableMention): Code<Int> {
    override fun toCompiler(compiler: Compiler): Inter<Int> {
        return compiler.compile(this)
    }

    override fun toText(): CharSequence {
        return variable.toString()
    }
}

