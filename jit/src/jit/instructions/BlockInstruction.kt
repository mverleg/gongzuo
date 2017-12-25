package jit.instructions

import jit.common.Instruction
import jit.common.Name
import jit.hardware.Processor

/**
 * A named block with a list of instructions, that can be jumped between.
 */
class BlockInstruction(val name: Name, val instructions: InstructionList):
        Instruction<Int>, Iterable<Instruction<Int>> {
    constructor(name: Name, first: Instruction<Int>, vararg otherInstructions: Instruction<Int>):
            this(name, InstructionList(first, *otherInstructions))

    override fun run(processor: Processor): Int {
        return instructions.run(processor)
    }

    override fun toText(): CharSequence {
        val text = StringBuilder(name.toString()).append(":\n\t")
        text.append(instructions.toText())
        return text
    }

    override fun iterator(): Iterator<Instruction<Int>> {
        return instructions.iterator()
    }
}


