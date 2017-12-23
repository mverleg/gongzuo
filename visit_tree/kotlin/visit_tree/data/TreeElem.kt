package visit_tree.data

interface TreeElem {

    fun <T> accept(visitor: TreeVisitor<T>): Iterator<T>

}