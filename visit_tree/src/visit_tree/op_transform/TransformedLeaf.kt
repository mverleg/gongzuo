package visit_tree.op_transform

class TransformedLeaf: TransformedElem {
    override fun toDebugText(): CharSequence {
        return "leaf"
    }
}