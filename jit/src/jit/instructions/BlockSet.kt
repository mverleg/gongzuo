package jit.instructions

import jit.common.Instruction
import jit.common.Name

val MAIN_BLOCK_NAME = Name("entry")

/**
 * A collection of {@link BlockInstruction}s. The entry block must be provided upon construction.
 */
class BlockSet(val instructions: InstructionList) {
    constructor(first: Instruction<Int>, vararg otherInstructions: Instruction<Int>):
            this(InstructionList(first, *otherInstructions))

    val blocks: LinkedHashMap<Name, BlockInstruction> = LinkedHashMap<Name, BlockInstruction>()

    init {
        blocks.put(MAIN_BLOCK_NAME, BlockInstruction(MAIN_BLOCK_NAME, instructions))
    }
}


