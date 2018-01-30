package jit.instructions

import jit.common.Inter

class ValueInter<RT>(val value: RT): Inter<RT> {

    override fun toText(): CharSequence {
        return value.toString()
    }
}

