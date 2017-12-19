package jit.ast

class If<ET: Expr>(val condition: BoolExpr, val thenEx: ET, val elseEx: ET): Expr {

}

