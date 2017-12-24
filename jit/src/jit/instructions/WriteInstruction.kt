package jit.instructions

import jit.common.Instruction
import jit.common.Name
import jit.hardware.Processor

class WriteInstruction(val name: Name, val value: Int): Instruction<Int> {
    override fun run(processor: Processor): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}

