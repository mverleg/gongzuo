package jit.instructions

import jit.common.Instruction
import jit.hardware.Processor

class AllocateInstruction(val variable: Variable): Instruction<Int> {
    override fun run(processor: Processor): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun toText(): CharSequence {
        return "alloc %${variable}"
    }
}

