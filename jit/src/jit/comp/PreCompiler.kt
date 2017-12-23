package jit.comp

import jit.code.Code

/**
 * This is the compiler which converts individual units to assembly.
 *
 * The compiler follows the visitor pattern, transforming the AST into executable code.
 * It operates on the code inside units (functions for now), stopping at boundaries.
 *
 * Note that in reality, this would be two steps: convert AST to debug form,
 * and compile this form to assembly.
 */
class PreCompiler: SourceVisitor {
    /**
     * Compile the source in a preliminary way, with benchmarking.
     */
    fun <RT> visit(code: Code<RT>): Exec<RT> {
        return code.precompile()
    }
}

/**
 * Similar to {@code PreCompiler} but optimized
 */
class OptCompiler: SourceVisitor {
    /**
     * Compile the source in an optimized way, using the benchmarks.
     */
    fun <RT> visit(code: Code<RT>, prelimExec: PrelimExec<RT>): Exec<RT> {
        return code.optcomp(prelimExec)
    }
}


