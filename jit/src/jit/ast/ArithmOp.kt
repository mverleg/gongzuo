package jit.ast

import jit.Processor
import jit.comp.Exec
import jit.comp.OptExec
import jit.comp.PrelimExec

class ArithmOp(var op: ArithmOpType, val leftExpr: IntExpr, val rightExpr: IntExpr): IntExpr, PrelimExec<Int>, OptExec<Int> {

    var leftPrelimExec: PrelimExec<Int>? = null
    var rightPrelimExec: PrelimExec<Int>? = null
    var leftExec: Exec<Int>? = null
    var rightExec: Exec<Int>? = null

    enum class ArithmOpType {
        ADD,
        SUB,
        MUL,
        DIV,
        MOD,
    }

    override fun precomp(): PrelimExec<Int> {
        leftPrelimExec = leftExpr.precomp()
        rightPrelimExec = leftExpr.precomp()
        leftExec = leftPrelimExec
        rightExec = rightPrelimExec
        return this;
    }

    override fun optcomp(prelimExec: PrelimExec<Int>): OptExec<Int> {
        if (leftPrelimExec == null || rightPrelimExec == null) {
            throw IllegalStateException("Compile in preliminary mode before compiling in optimized mode")
        }
        leftExec = leftExpr.optcomp(leftPrelimExec!!)
        rightExec = leftExpr.optcomp(rightPrelimExec!!)
        return this;
    }

    override fun run(proc: Processor): Int {
        if (leftPrelimExec == null || rightPrelimExec == null) {
            throw IllegalStateException("Compile before running")
        }
        when (op) {
            ArithmOp.ArithmOpType.ADD -> return leftExec!!.run(proc) + rightExec!!.run(proc)
            ArithmOp.ArithmOpType.SUB -> return leftExec!!.run(proc) - rightExec!!.run(proc)
            ArithmOp.ArithmOpType.MUL -> return leftExec!!.run(proc) * rightExec!!.run(proc)
            ArithmOp.ArithmOpType.DIV -> return leftExec!!.run(proc) / rightExec!!.run(proc)
            ArithmOp.ArithmOpType.MOD -> return leftExec!!.run(proc) % rightExec!!.run(proc)
        }
    }
}


