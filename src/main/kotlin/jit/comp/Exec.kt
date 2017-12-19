package jit.comp

/**
 * Executable piece of code.
 */
interface Exec {

}

/**
 * Preliminary executable version, with benchmarking and possibly to be replaced by {@link OptExec}.
 */
interface PrelimExec: Exec {

}

/**
 * Optimized executable version, which replaces {@code PrelimExec} if worthwhile.
 */
interface OptExec: Exec {

}

