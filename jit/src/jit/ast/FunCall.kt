package jit.ast

import jit.comp.FunCallExec
import jit.comp.OptExec
import jit.comp.PrelimExec

class FunCall(public val name: Name): Expr<Int> {
    override fun precomp(): PrelimExec<Int> {
        return FunCallExec(name)
    }

    override fun optcomp(prelimExec: PrelimExec<Int>): OptExec<Int> {
        return FunCallExec(name)
    }

}