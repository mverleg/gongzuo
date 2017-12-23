package jit.code

import jit.common.Code
import jit.common.Compiler
import jit.common.InstructionList

class ConstCode(val value: Int): Code<Int> {
    override fun toCompiler(compiler: Compiler): InstructionList {
        return compiler.compile(this)
    }
}


