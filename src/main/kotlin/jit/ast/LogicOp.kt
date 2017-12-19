package jit.ast

class LogicOp(var op: LogicOpType, val left: BoolExpr, val right: BoolExpr): BoolExpr {
    enum class LogicOpType {
        EQ,
        NEQ,
        LT,
        GT,
        LTE,
        GTE,
//        DIV,
//        MOD,
    }
}


