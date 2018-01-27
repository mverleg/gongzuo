package jit.intermediary

/**
 * A list of at least one inters, which is itself an inters.
 *
 * The result is determined by the last inters.
 */
class InterList(val first: Inter<Int>, vararg val otherInters: Inter<Int>):
        Inter<Int>, Iterable<Inter<Int>> {

    val inters: List<Inter<Int>>

    /**
     * If any of the inters are themselves combinations ({@link InterList}), flatten their content into this one.
     */
    init {
        val flattenedInters: MutableList<Inter<Int>> = mutableListOf()
        if (first is InterList) {
            for (subinstr in first) {
                flattenedInters.add(subinstr)
            }
        } else {
            flattenedInters.add(first)
        }
        for (instr in otherInters) {
            if (instr is InterList) {
                for (subinstr in instr) {
                    flattenedInters.add(subinstr)
                }
            } else {
                flattenedInters.add(instr)
            }
        }

        inters = flattenedInters
    }

    override fun toText(): CharSequence {
        val text = StringBuilder()
        for (instruction in otherInters) {
            text.append(instruction.toText())
            text.append("\n\t")
        }
        return text
    }

    override fun iterator(): Iterator<Inter<Int>> {
        return inters.iterator()
    }
}


