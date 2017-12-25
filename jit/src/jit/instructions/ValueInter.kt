package jit.instructions

import jit.common.Inter

class ValueInter<RT>(val value: RT): Inter<RT> {

    override fun run(processor: HighProcessor): RT {
        return value
    }

    override fun toText(): CharSequence {
        return value.toString()
    }
}

