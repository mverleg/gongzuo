package jit.intermediary

import jit.common.Inter
import jit.instructions.Variable

class AllocateInter(val variable: Variable): Inter<Int> {

    override fun toText(): CharSequence {
        return "alloc %${variable}"
    }
}

