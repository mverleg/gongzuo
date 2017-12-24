package jit.common

import jit.hardware.Processor

/**
 * A hardware instruction that the {@link Processor} can run.
 */
interface Instruction {
    fun run(processor: Processor)
}


