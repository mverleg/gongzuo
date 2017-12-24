package jit.instructions

import jit.common.Instruction
import jit.common.Name
import jit.hardware.Processor

class WriteInstruction(val name: Name, val value: Instruction<Int>): Instruction<Int> {
    override fun run(processor: Processor): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun toText(): CharSequence {
        return "%${name} = ${value}"
    }
}

