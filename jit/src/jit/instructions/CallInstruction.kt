package jit.instructions

import jit.common.Instruction
import jit.common.Name
import jit.hardware.Processor

class CallInstruction(val name: Name, val parameters: List<Instruction<Int>>): Instruction<Int> {
    override fun run(processor: Processor): Int {
        return processor.call(name, parameters.map { it.run(processor) })
    }
}


