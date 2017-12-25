package jit.instructions

import jit.common.Inter

class DeallocateInter(val variable: Variable): Inter<Int> {
    override fun run(processor: HighProcessor): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun toText(): CharSequence {
        return "alloc %${variable.name}"
    }
}

