package jit.common

/**
 * Executable piece of code.
 */
interface Exec<RT> {
    fun run(proc: HighProcessor): RT;
}

