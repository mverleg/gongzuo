package jit.ast

import jit.Processor
import jit.comp.OptExec
import jit.comp.PrelimExec

// Only integer variables for now

class Var(val name: Name): IntExpr {

    override fun precomp(): PrelimExec<Int> {
        return this;
    }

    override fun optcomp(prelimExec: PrelimExec<Int>): OptExec<Int> {
        return this;
    }

    override fun run(proc: Processor): Int {

    }
}
