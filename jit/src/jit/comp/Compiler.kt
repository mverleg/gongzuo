package jit.comp

import jit.ast.Expr

/**
 * This is the compiler which converts individual units to assembly.
 *
 * The compiler follows the visitor pattern, transforming the AST into executable code.
 * It operates on the code inside units (functions for now), stopping at boundaries.
 */
class Compiler {
    /**
     * Compile the source in a preliminary way, with benchmarking.
     */
    fun <RT> precomp(expr: Expr<RT>): Exec<RT> {
        return expr.precomp()
    }

    /**
     * Compile the source in an optimized way, using the benchmarks.
     */
    fun <RT> optimize(expr: Expr<RT>, prelimExec: PrelimExec<RT>): Exec<RT> {
        return expr.optcomp(prelimExec)
    }
}
