package visit_tree.data

interface ExTreeVisitor<T> {
    fun visit(leaf: Leaf): T
    fun visit(split: Split): T
}

interface TreeVisitor<T>: ExTreeVisitor<T> {
    // Have to use list here because generics don't work well with varargs
    // TODO: this combine still has info the tree structure (parent first and children after)
    fun combine(inputs: Iterable<T>): T
}


