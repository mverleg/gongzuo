package jit.ast

import jit.comp.OptExec
import jit.comp.PrelimExec
import jit.comp.VarExec

// Only integer variables for now

class Var(val name: Name): IntExpr {

    override fun precomp(): PrelimExec<Int> {
        return VarExec(this);
    }

    override fun optcomp(prelimExec: PrelimExec<Int>): OptExec<Int> {
        return VarExec(this);
    }
}
