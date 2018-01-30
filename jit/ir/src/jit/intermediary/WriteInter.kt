package jit.instructions

import jit.common.Inter

class WriteInter(val variable: Variable, val value: Inter<Int>): Inter<Int> {

    override fun toText(): CharSequence {
        return "%${variable} = ${value}"
    }
}

