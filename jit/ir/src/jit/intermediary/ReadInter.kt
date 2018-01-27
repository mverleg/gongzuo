package jit.intermediary

class ReadInter(val variable: Variable): Inter<Int> {

    override fun toText(): CharSequence {
        return "%${variable}"
    }
}

