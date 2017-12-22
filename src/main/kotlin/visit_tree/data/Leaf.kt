package visit_tree.data

class Leaf: TreeElem {
    override fun <T> accept(visitor: TreeVisitor<T>): Iterator<T> {
        return listOf(visitor.visit(this)).iterator()
    }
}
