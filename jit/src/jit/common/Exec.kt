package jit.common

import jit.hardware.Processor

/**
 * Executable piece of code.
 */
interface Exec<RT> {
    fun run(proc: Processor): RT;
}

