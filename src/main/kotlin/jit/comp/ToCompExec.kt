package jit.comp

import jit.Processor
import jit.ast.Expr

/**
 * Placeholder for piece of code that has never been run yet. Replace by {@code PrelimExec} on first invocation.
 */
class ToCompExec(val code: Expr): Exec {
    override fun run(proc: Processor): Int {
        val compiled = Compiler().precomp(code)
        proc.replace(compiled)
        println("replacing placeholder for " + code + " by " + compiled)
        return compiled.run(proc)
    }
}

