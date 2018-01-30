package jit.instructions

import jit.common.Inter
import jit.common.Name

/**
 * A named block with a list of inters, that can be jumped between.
 */
class BlockInter(val name: Name, val instructions: InterList):
        Inter<Int>, Iterable<Inter<Int>> {
    constructor(name: Name, first: Inter<Int>, vararg otherInters: Inter<Int>):
            this(name, InterList(first, *otherInters))

    override fun toText(): CharSequence {
        val text = StringBuilder(name.toString()).append(":\n\t")
        text.append(instructions.toText())
        return text
    }

    override fun iterator(): Iterator<Inter<Int>> {
        return instructions.iterator()
    }
}


