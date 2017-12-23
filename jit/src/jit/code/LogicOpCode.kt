package jit.code

import jit.common.Code

class LogicOpCode(var op: LogicOpType, val left: Code<Int>, val right: Code<Int>): Code<Boolean> {
    enum class LogicOpType {
        EQ,
        NEQ,
        LT,
        GT,
        LTE,
        GTE,
    }
//
//    override fun precomp(): PrelimExec<Boolean> {
//        return this;
//    }
//
//    override fun optcomp(prelimExec: PrelimExec<Boolean>): OptExec<Boolean> {
//        return this;
//    }
//
//    override fun run(proc: Processor): Boolean {
//        when (op) {
//            LogicOpType.EQ ->  return left.run(proc) == right.run(proc)
//            LogicOpType.NEQ -> return left.run(proc) != right.run(proc)
//            LogicOpType.LT ->  return left.run(proc) <  right.run(proc)
//            LogicOpType.GT ->  return left.run(proc) >  right.run(proc)
//            LogicOpType.LTE -> return left.run(proc) <= right.run(proc)
//            LogicOpType.GTE -> return left.run(proc) >= right.run(proc)
//        }
//    }
}


