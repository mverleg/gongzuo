package jit.instructions

import jit.common.Instruction
import jit.common.Name
import jit.hardware.Processor

class DeallocateInstruction(val name: Name): Instruction<Int> {
    override fun run(processor: Processor): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun toText(): CharSequence {
        return "alloc %${name}"
    }
}

