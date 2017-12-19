package jit.comp

import jit.Processor

/**
 * Executable piece of code.
 */
interface Exec<RT> {
    fun run(proc: Processor): RT;
}

/**
 * Preliminary executable version, with benchmarking and possibly to be replaced by {@link OptExec}.
 */
interface PrelimExec<RT>: Exec<RT> {

}

/**
 * Optimized executable version, which replaces {@code PrelimExec} if worthwhile.
 */
interface OptExec<RT>: Exec<RT> {

}

