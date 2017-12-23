package jit.ast

import jit.comp.Exec
import jit.comp.OptExec
import jit.comp.OptIf
import jit.comp.PrelimExec
import jit.comp.PrelimIf

class If(public val condition: BoolExpr, public val thenExpr: Expr<Int>, public val elseExpr: Expr<Int>): Expr<Int> {

    var prelimIf: Exec<Int>? = null;
    var conditionExec: Exec<Boolean>? = null;
    var thenExec: Exec<Int>? = null;
    var elseExec: Exec<Int>? = null;

    override fun precomp(): PrelimExec<Int> {
        conditionExec = condition.precomp()
        thenExec = thenExpr.precomp()
        elseExec = elseExpr.precomp()
        prelimIf = PrelimIf(conditionExec, thenExec, elseExec)
        return prelimIf
    }

    override fun optcomp(prelimExec: PrelimExec<Int>): OptExec<Int> {
        return OptIf(condition.optcomp(conditionExec), thenExpr.optcomp(thenExec), elseExpr.optcomp(elseExec), prelimIf.getTrueRatio())
    }
}


