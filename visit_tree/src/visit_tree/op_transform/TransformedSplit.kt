package visit_tree.op_transform;

class TransformedSplit: TransformedElem {
    val children: MutableList<TransformedElem> = mutableListOf()

    fun add(additions: Iterator<TransformedElem>) {
        for (child in additions) {
            children.add(child)
        }
    }

    override fun toDebugText(): CharSequence {
        val text = StringBuilder("split { ")
        var ifFirst = true
        for (child in children) {
            if (ifFirst) {
                ifFirst = false
            } else {
                text.append(", ")
            }
            text.append(child.toDebugText())
        }
        text.append(" }")
        return text
    }
}
