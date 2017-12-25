package jit.instructions

import jit.common.Inter
import jit.common.Name

val MAIN_BLOCK_NAME = Name("entry")

/**
 * A collection of {@link BlockInter}s. The entry block must be provided upon construction.
 */
class BlockSet(val instructions: InterList) {
    constructor(first: Inter<Int>, vararg otherInters: Inter<Int>):
            this(InterList(first, *otherInters))

    val blocks: LinkedHashMap<Name, BlockInter> = LinkedHashMap<Name, BlockInter>()

    init {
        blocks.put(MAIN_BLOCK_NAME, BlockInter(MAIN_BLOCK_NAME, instructions))
    }
}


