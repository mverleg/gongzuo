package blag

/// Data.

interface Tree {
    // We also use Composite pattern
    public fun <T> accept(visitor: Visitor<T>): T
}

class Node(public vararg val children: Tree): Tree {
    final override public fun <T> accept(visitor: Visitor<T>): T {
        // This is dynamic dispatch on visitor, but static on the Tree element.
        return visitor.visitNode(this)
    }
}

class Leaf(): Tree {
    final override public fun <T> accept(visitor: Visitor<T>): T {
        // Pretty much the same as Node; all Tree instances need this.
        return visitor.visitLeaf(this)
    }
}

interface Visitor<T> {
    // All visitors must be able to visit all nodes.
    fun visitNode(node: Node): T
    fun visitLeaf(leaf: Leaf): T

}

/// First operation: Serialize to XML.

class Serializer: Visitor<CharSequence> {
    final override public fun visitNode(node: Node): CharSequence {
        val txt = StringBuilder("<node>")
        for (child in node.children) {
            txt.append(child.accept(this))
        }
        return txt.append("</node>")
    }

    final public override fun visitLeaf(leaf: Leaf): CharSequence {
        return "<leaf/>"
    }
}

/// Second operation: Convert to another tree.

interface NewTree {}

data class NewNode(public val children: Array<NewTree>): NewTree {}

class NewLeaf: NewTree {}

class Converter: Visitor<NewTree> {
    final override fun visitNode(node: Node): NewTree {
        return NewNode(node.children.map { it.accept(this) }.toTypedArray())
    }

    final override fun visitLeaf(leaf: Leaf): NewTree {
        return NewLeaf()
    }
}

/// Testing.

fun main(args: Array<String>) {
    val data: Tree = Node(Node(Leaf()), Leaf(), Leaf())
    val serializer = Serializer()
    println("text: " + data.accept(serializer))
    val converter = Converter()
    println("new tree: " + data.accept(converter))
}

