package jit.ast

import jit.comp.OptExec
import jit.comp.OptIf
import jit.comp.PrelimExec
import jit.comp.PrelimIf

class If<RT>(public val condition: BoolExpr, public val thenEx: Expr<RT>, public val elseEx: Expr<RT>): Expr<RT> {
    override fun precomp(): PrelimExec<RT> {
        return PrelimIf(condition.precomp(), thenEx.precomp(), elseEx.precomp())
    }

    override fun optcomp(prelimIf: PrelimIf<RT>): OptExec<RT> {
        return OptIf(condition.optcomp(), thenEx.optcomp(), elseEx.optcomp(), prelimIf.getTrueRatio())
    }
}


