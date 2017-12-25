package jit.instructions

import jit.common.Instruction
import jit.hardware.Processor

class WriteInstruction(val variable: Variable, val value: Instruction<Int>): Instruction<Int> {
    override fun run(processor: Processor): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun toText(): CharSequence {
        return "%${variable} = ${value}"
    }
}

