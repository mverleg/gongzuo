package jit.common

import jit.hardware.Processor

/**
 * A hardware instruction that the {@link Processor} can run.
 */
interface Instruction<RT> {
    fun run(processor: Processor): RT
}


