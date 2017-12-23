package jit.code

import jit.Processor
import jit.comp.OptExec
import jit.comp.PrelimExec

class Const(val value: Int): IntExpr, PrelimExec<Int>, OptExec<Int> {
    override fun precomp(): PrelimExec<Int> {
        return this
    }

    override fun optcomp(prelimExec: PrelimExec<Int>): OptExec<Int> {
        return this
    }

    override fun run(proc: Processor): Int {
        return value
    }
}

