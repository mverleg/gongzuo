package jit.instructions

import jit.common.Instruction
import jit.hardware.Processor

class ValueInstruction(val value: Int): Instruction<Int> {
    override fun run(processor: Processor): Int {
        return value
    }
}

