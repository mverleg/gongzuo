package jit.code

import jit.comp.OptExec
import jit.comp.PrelimExec

interface Expr<RT> {
    // note: this would in the future be defined elsewhere, probably with visitor
    fun precomp(): PrelimExec<RT>

    // note: this would in the future be defined elsewhere, probably with visitor
    fun optcomp(prelimExec: PrelimExec<RT>): OptExec<RT>
}

interface IntExpr: Expr<Int> {

}

interface BoolExpr: Expr<Boolean> {

}

