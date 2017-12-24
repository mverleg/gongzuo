package jit.code

import jit.common.Code
import jit.common.Compiler
import jit.common.InstructionList
import jit.common.Name

class FunCallCode(val name: Name, vararg val args: Code<Int>): Code<Int> {
    override fun toCompiler(compiler: Compiler): InstructionList {
        return compiler.compile(this)
    }

    override fun toText(): CharSequence {
        val paramNames = args.map { it.toText() }.joinToString(", ")
        return StringBuilder(name.toString()).append("(").append(paramNames).append(") ")
    }
}