package jit.code

import jit.Processor
import jit.comp.OptExec
import jit.comp.PrelimExec

class LogicOp(var op: LogicOpType, val left: IntExpr, val right: IntExpr): BoolExpr, PrelimExec<Boolean>, OptExec<Boolean> {
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

    override fun precomp(): PrelimExec<Boolean> {
        return this;
    }

    override fun optcomp(prelimExec: PrelimExec<Boolean>): OptExec<Boolean> {
        return this;
    }

    override fun run(proc: Processor): Boolean {
        when (op) {
            LogicOpType.EQ ->  return left.run(proc) == right.run(proc)
            LogicOpType.NEQ -> return left.run(proc) != right.run(proc)
            LogicOpType.LT ->  return left.run(proc) <  right.run(proc)
            LogicOpType.GT ->  return left.run(proc) >  right.run(proc)
            LogicOpType.LTE -> return left.run(proc) <= right.run(proc)
            LogicOpType.GTE -> return left.run(proc) >= right.run(proc)
        }
    }
}


