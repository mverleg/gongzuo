package jit.instructions

import jit.common.Inter

class ReadInter(val variable: Variable): Inter<Int> {

    override fun toText(): CharSequence {
        return "%${variable}"
    }
}

