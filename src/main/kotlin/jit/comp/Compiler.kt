package jit.comp

import jit.ast.Expr

/**
 * This is the compiler which converts individual units to assembly.
 */
class Compiler {
    /**
     * Compile the source in a preliminary way, with benchmarking.
     */
    fun precomp(expr: Expr): Exec {
        return expr.precomp()
    }

    /**
     * Compile the source in an optimized way, using the benchmarks.
     */
    fun optimize(expr: Expr, prelimExec: PrelimExec): Exec {
        return expr.optcomp(prelimExec)
    }
}
