package jit.instructions

import jit.common.Instruction
import jit.hardware.Processor

/**
 * A list of at least one instructions, which is itself an instructions.
 *
 * The result is determined by the last instructions.
 */
class InstructionList(val first: Instruction<Int>, vararg val otherInstructions: Instruction<Int>):
        Instruction<Int>, Iterable<Instruction<Int>> {

    val instructions: List<Instruction<Int>>

    /**
     * If any of the instructions are themselves combinations ({@link InstructionList}), flatten their content into this one.
     */
    init {
        val flattenedInstructions: MutableList<Instruction<Int>> = mutableListOf()
        if (first is InstructionList) {
            for (subinstr in first) {
                flattenedInstructions.add(subinstr)
            }
        } else {
            flattenedInstructions.add(first)
        }
        for (instr in otherInstructions) {
            if (instr is InstructionList) {
                for (subinstr in instr) {
                    flattenedInstructions.add(subinstr)
                }
            } else {
                flattenedInstructions.add(instr)
            }
        }

        instructions = flattenedInstructions
    }

    override fun run(processor: Processor): Int {
        var result = first.run(processor)
        for (instr in otherInstructions) {
            result = instr.run(processor)
        }
        return result
    }

    override fun toText(): CharSequence {
        val text = StringBuilder()
        for (instruction in otherInstructions) {
            text.append(instruction.toText())
            text.append("\n\t")
        }
        return text
    }

    override fun iterator(): Iterator<Instruction<Int>> {
        return instructions.iterator()
    }
}


