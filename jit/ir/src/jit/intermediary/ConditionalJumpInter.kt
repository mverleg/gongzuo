package jit.instructions

import jit.common.Inter
import jit.common.genSequenceStr

class ConditionalJumpInter(val condition: Inter<Boolean>, val thenInstructions: Inter<Int>,
                           val elseInstructions: Inter<Int>): Inter<Int> {

    val postfix: String

    companion object {
        var generateIndex = 0
    }

    init {
        postfix = genSequenceStr(generateIndex)
        generateIndex++
    }

    override fun toText(): CharSequence
}

