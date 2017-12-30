package visit_tree.data

class Leaf: TreeElem {

    override fun <T> accept(visitor: TreeVisitor<T>): T {
        return visitor.visit(this)
    }
}
