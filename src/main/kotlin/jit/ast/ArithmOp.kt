package jit.ast

import jit.Processor
import jit.comp.OptExec
import jit.comp.PrelimExec

class ArithmOp(var op: ArithmOpType, val left: IntExpr, val right: IntExpr): IntExpr, PrelimExec<Int>, OptExec<Int> {
    enum class ArithmOpType {
        ADD,
        SUB,
        MUL,
        DIV,
        MOD,
    }

    override fun precomp(): PrelimExec<Int> {
        return this;
    }

    override fun optcomp(prelimExec: PrelimExec<Int>): OptExec<Int> {
        return this;
    }

    override fun run(proc: Processor): Int {
        when (op) {
            ArithmOp.ArithmOpType.ADD -> return left.run(proc) + right.run(proc)
            ArithmOp.ArithmOpType.SUB -> return left.run(proc) - right.run(proc)
            ArithmOp.ArithmOpType.MUL -> return left.run(proc) * right.run(proc)
            ArithmOp.ArithmOpType.DIV -> return left.run(proc) / right.run(proc)
            ArithmOp.ArithmOpType.MOD -> return left.run(proc) % right.run(proc)
        }
    }
}


