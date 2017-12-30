package visit_tree.data

class Leaf: TreeElem {
    override fun <T> acceptSimple(visitor: ExTreeVisitor<T>): T {
        return visitor.visit(this)
    }

    override fun <T> accept(visitor: TreeVisitor<T>): T {
        return visitor.visit(this)
    }
}
