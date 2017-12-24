package jit.instructions

import jit.common.Instruction
import jit.common.Name
import jit.hardware.Processor

class CallInstruction(val name: Name, val args: List<Instruction<Int>>): Instruction<Int> {
    override fun run(processor: Processor): Int {
        return processor.call(name, args.map { it.run(processor) })
    }

    override fun toText(): CharSequence {
        val paramNames = args.map { "i32 " + it.toText() }.joinToString(", ")
        return StringBuilder(name.toString()).append("(").append(paramNames).append(") ")
    }
}


