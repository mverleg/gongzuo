package jit.common

import jit.hardware.Processor

/**
 * A list of at least one instruction, which is itself an instruction.
 *
 * The result is determined by the last instruction.
 */
class InstructionList(val first: Instruction<Int>, vararg val instructions: Instruction<Int>): Instruction<Int> {
    override fun run(processor: Processor): Int {
        var result = first.run(processor)
        for (instr in instructions) {
            result = instr.run(processor)
        }
        return result
    }

    override fun toText(): CharSequence {
        val text = StringBuilder()
        for (instruction in instructions) {
            text.append(instruction.toText())
            text.append("\n\t")
        }
        return text
    }
}


