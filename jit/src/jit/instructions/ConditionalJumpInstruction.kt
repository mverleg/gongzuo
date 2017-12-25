package jit.instructions

import jit.common.Instruction
import jit.common.genSequenceStr
import jit.hardware.Processor

class ConditionalJumpInstruction(val condition: Instruction<Boolean>, val thenInstructions: Instruction<Int>,
        val elseInstructions: Instruction<Int>): Instruction<Int> {

    val postfix: String

    companion object {
        var generateIndex = 0
    }

    init {
        postfix = genSequenceStr(generateIndex)
        generateIndex++
    }

    override fun run(processor: Processor): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun toText(): CharSequence
}

