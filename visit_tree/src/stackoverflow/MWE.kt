package stackoverflow
interface TreeElem {
    fun <T> accept(visitor: TreeVisitor<T>): T
}

class Leaf: TreeElem {
    override fun <T> accept(visitor: TreeVisitor<T>): T {
        return visitor.visit(this)
    }
}

class Split(val left: TreeElem, val right: TreeElem): TreeElem {
    override fun <T> accept(visitor: TreeVisitor<T>): T {
        return visitor.combine(
            visitor.visit(this),
            left.accept(visitor),
            left.accept(visitor))
    }
}

interface TreeVisitor<T> {
    fun visit(tree: Leaf): T
    fun visit(tree: Split): T
    fun combine(vararg inputs: T): T
}

class Printer: TreeVisitor<CharSequence> {
    override fun combine(vararg inputs: CharSequence): CharSequence {
        return inputs.joinToString(" ")
    }
    override fun visit(tree: Leaf): CharSequence { return "leaf" }
    override fun visit(tree: Split): CharSequence { return "split" }
}

fun main(args: Array<String>) {
    val tree = Split(Leaf(), Leaf())
    val printer = Printer()
    println(tree.accept(printer))
}


