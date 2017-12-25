package jit.comp

import jit.common.Exec

/**
 * Preliminary executable version, with benchmarking and possibly to be replaced by {@link OptExec}.
 */
interface PrelimExec<RT>: Exec<RT> {

}

/**
 * Optimized executable version, which replaces {@link PrelimExec} if worthwhile.
 */
interface OptExec<RT>: Exec<RT> {

}

