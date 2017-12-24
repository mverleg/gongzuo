package jit.code

import jit.common.Code
import jit.common.Compiler
import jit.common.Instruction

/**
 * Read the value of a variable.
 */
open class ReadCode(val variable: VarCode): Code<Int> {
    override fun toCompiler(compiler: Compiler): Instruction<Int> {
        return compiler.compile(this)
    }

    override fun toText(): CharSequence {
        return variable.toString()
    }
}

