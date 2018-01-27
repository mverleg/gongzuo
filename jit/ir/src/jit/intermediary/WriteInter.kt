package jit.intermediary

import jit.intermediary.Inter
import jit.intermediary.Variable

class WriteInter(val variable: Variable, val value: Inter<Int>): Inter<Int> {

    override fun toText(): CharSequence {
        return "%${variable} = ${value}"
    }
}

