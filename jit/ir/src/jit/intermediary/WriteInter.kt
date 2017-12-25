package jit.instructions

import jit.common.Inter

class WriteInter(val variable: Variable, val value: Inter<Int>): Inter<Int> {
    override fun run(processor: HighProcessor): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun toText(): CharSequence {
        return "%${variable} = ${value}"
    }
}

