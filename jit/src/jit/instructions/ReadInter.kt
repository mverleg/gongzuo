package jit.instructions

import jit.common.Inter

class ReadInter(val variable: Variable): Inter<Int> {
    override fun run(processor: HighProcessor): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun toText(): CharSequence {
        return "%${variable}"
    }
}

