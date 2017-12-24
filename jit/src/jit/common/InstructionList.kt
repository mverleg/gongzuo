package jit.common

import jit.hardware.Processor

/**
 * A list of at least one instruction, which is itself an instruction.
 *
 * The result is determined by the last instruction.
 */
class InstructionList(val first: Instruction, vararg val instructions: Instruction): Instruction {

    override fun run(processor: Processor) {
        first.run(processor)
        for (instr in instructions) {
            instr.run(processor)
        }
    }
}


