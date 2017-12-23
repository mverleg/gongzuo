package jit.comp

import jit.hardware.Processor

class PrelimIf<ET>(val conditionExpr: PrelimExec<Boolean>, val thenExec: PrelimExec<ET>, val elseExec: PrelimExec<ET>): PrelimExec<ET> {

    private var thenCount: Int = 0;
    private var elseCount: Int = 0;

    override fun run(proc: Processor): ET {
        if (conditionExpr.run(proc)) {
            thenCount++
            return thenExec.run(proc)
        } else {
            elseCount++
            return elseExec.run(proc)
        }
    }
}

class OptIf<ET>(val conditionExpr: OptExec<Boolean>, val thenExec: OptExec<ET>, val elseExec: OptExec<ET>, val trueFrac: Float): PrelimExec<ET> {

    override fun run(proc: Processor): ET {
        if (conditionExpr.run(proc)) {
            println("optimized if " + this + " true branch (" + (100 * trueFrac) + "%)")
            return thenExec.run(proc)
        } else {
            println("optimized if " + this + " false branch (" + (100 * (1 - trueFrac)) + "%)")
            return elseExec.run(proc)
        }
    }
}


