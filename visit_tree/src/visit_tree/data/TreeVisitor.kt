package visit_tree.data

interface TreeVisitor<T> {
    fun visit(tree: Leaf): T
    fun visit(tree: Split): T
    // Have to use list here because generics don't work well with varargs
    fun combine(inputs: Iterable<T>): T
}


