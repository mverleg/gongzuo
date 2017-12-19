package jit.ast

class ArithmOp(var op: ArithmOpType, val left: IntExpr, val right: IntExpr): IntExpr {
    enum class ArithmOpType {
        ADD,
        SUB,
        MULT,
//        DIV,
//        MOD,
    }
}


