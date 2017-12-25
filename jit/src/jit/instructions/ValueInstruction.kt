package jit.instructions

import jit.common.Instruction
import jit.hardware.Processor

class ValueInstruction<RT>(val value: RT): Instruction<RT> {

    override fun run(processor: Processor): RT {
        return value
    }

    override fun toText(): CharSequence {
        return value.toString()
    }
}

