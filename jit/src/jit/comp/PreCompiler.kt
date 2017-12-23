package jit.comp

import jit.code.Expr

/**
 * This is the compiler which converts individual units to assembly.
 *
 * The compiler follows the visitor pattern, transforming the AST into executable code.
 * It operates on the code inside units (functions for now), stopping at boundaries.
 *
 * Note that in reality, this would be two steps: convert AST to debug form,
 * and compile this form to assembly.
 */
class PreCompiler {
    /**
     * Compile the source in a preliminary way, with benchmarking.
     */
    fun <RT> precompile(expr: Expr<RT>): Exec<RT> {
        return expr.precomp()
    }
}

/**
 * Similar to {@code PreCompiler} but optimized
 */
class OptCompiler {
    /**
     * Compile the source in an optimized way, using the benchmarks.
     */
    fun <RT> optimize(expr: Expr<RT>, prelimExec: PrelimExec<RT>): Exec<RT> {
        return expr.optcomp(prelimExec)
    }
}
