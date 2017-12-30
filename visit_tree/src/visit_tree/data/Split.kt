package visit_tree.data

class Split(val left: TreeElem, val right: TreeElem): TreeElem {

    override fun <T> accept(visitor: TreeVisitor<T>): T {
        return visitor.combine(listOf(
                visitor.visit(this),
                // TODO: a confusing part is whether to use visitor.visit(thing) or thing.accept(visitor); isn't there a way to force this?
                left.accept<T>(visitor),
                right.accept<T>(visitor)
        ))
    }
}

