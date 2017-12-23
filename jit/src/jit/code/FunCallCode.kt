package jit.code

import jit.common.Code
import jit.common.Compiler
import jit.common.InstructionList
import jit.common.Name

class FunCallCode(public val name: Name): Code<Int> {
    override fun toCompiler(compiler: Compiler): InstructionList {
        return compiler.compile(this)
    }
}