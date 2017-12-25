package jit.code

import jit.common.Code
import jit.common.Compiler
import jit.common.Inter

class ConstCode(val value: Int): Code<Int> {
    override fun toCompiler(compiler: Compiler): Inter<Int> {
        return compiler.compile(this)
    }

    override fun toText(): CharSequence {
        return value.toString()
    }
}


