package jit.instructions

import jit.common.Inter

class DeallocateInter(val variable: Variable): Inter<Int> {

    override fun toText(): CharSequence {
        return "alloc %${variable.name}"
    }
}

