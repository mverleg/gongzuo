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

    override fun run(processor: HighProcessor): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun toText(): CharSequence
}

