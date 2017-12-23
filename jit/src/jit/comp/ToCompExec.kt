package jit.comp

import jit.Processor
import jit.code.Code

/**
 * Placeholder for piece of code that has never been run yet. Replace by {@code PrelimExec} on first invocation.
 */
class ToCompExec(val preComp: PreCompiler, val code: Code<Int>): FunDefExec {
    override fun run(proc: Processor): Int {
        val compiled = preComp.precompile(code)
        proc.replace(compiled)
        println("replacing placeholder for " + code + " by " + compiled)
        return compiled.run(proc)
    }
}

