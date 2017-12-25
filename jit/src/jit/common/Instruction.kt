package jit.common

import jit.hardware.Processor

/**
 * A hardware instructions that the {@link Processor} can run.
 */
interface Instruction<RT> {
    fun run(processor: Processor): RT

    fun toText(): CharSequence
}


