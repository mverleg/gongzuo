package visit_tree.data

interface TreeElem {

    fun <T> accept(visitor: TreeVisitor<T>): T

    fun <T> acceptSimple(visitor: ExTreeVisitor<T>): T
}
