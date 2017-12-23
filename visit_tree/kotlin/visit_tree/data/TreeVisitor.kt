package visit_tree.data

interface TreeVisitor<T> {
    fun visit(tree: Leaf): T
    fun visit(tree: Split): T
}

