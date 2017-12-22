package visit_tree.data

class Split(val left: TreeElem, val right: TreeElem): TreeElem {
    override fun <T> accept(visitor: TreeVisitor<T>): Iterator<T> {
        return listOf(visitor.visit(this)).iterator()
    }
}
