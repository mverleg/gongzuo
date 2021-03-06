package jit.ast

import jit.common.Code
import jit.common.Compiler
import jit.common.Inter
import jit.common.Name

class FunCallCode(val name: Name, val args: List<Code<Int>>): Code<Int> {
    override fun toCompiler(compiler: Compiler): Inter<Int> {
        return compiler.compile(this)
    }

    override fun toText(): CharSequence {
        val paramNames = args.map { it.toText() }.joinToString(", ")
        return StringBuilder(name.toString()).append("(").append(paramNames).append(") ")
    }
}