package jit.code

import jit.common.Code
import jit.common.Compiler
import jit.common.Instruction
import jit.common.Name

// Only integer variables for now
class VarCode(val name: Name): Code<Int> {
    override fun toCompiler(compiler: Compiler): Instruction<Int> {
        return compiler.compile(this)
    }

    override fun toText(): CharSequence {
        return name.toString()
    }

//    override fun precomp(): PrelimExec<Int> {
//        return VarExec(this);
//    }
//
//    override fun optcomp(prelimExec: PrelimExec<Int>): OptExec<Int> {
//        return VarExec(this);
//    }
}
