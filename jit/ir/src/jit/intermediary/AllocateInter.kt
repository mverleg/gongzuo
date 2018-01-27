package jit.intermediary

class AllocateInter(val variable: Variable): Inter<Int> {

    override fun toText(): CharSequence {
        return "alloc %${variable}"
    }
}



