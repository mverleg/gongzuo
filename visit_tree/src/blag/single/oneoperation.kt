package blag.single

interface Tree {
    // We also use Composite pattern
    public fun accept(visitor: Serializer): CharSequence
}

class Node(public vararg val children: Tree): Tree {
    final override public fun accept(visitor: Serializer): CharSequence {
        // This is dynamic dispatch on visitor, but static on the Tree element.
        return visitor.serializeNode(this)
    }
}

class Leaf(): Tree {
    final override public fun accept(visitor: Serializer): CharSequence {
        // Pretty much the same as Node; all Tree instances need this.
        return visitor.serializeLeaf(this)
    }
}

class Serializer {
    final public fun serializeNode(node: Node): CharSequence {
        // This is often named `serialize`, but I've used `serializeNode` to
        // emphasize this is statically dispatched, not connected to `serializeLeaf`.
        val txt = StringBuilder("<node>")
        for (child in node.children) {
            txt.append(child.accept(this))
        }
        return txt.append("</node>")
    }

    final public fun serializeLeaf(leaf: Leaf): CharSequence {
        // This is often named `serialize`, but I've used `serializeLeaf` to
        // emphasize this is statically dispatched, not connected to `serializeNode`.
        return "<leaf/>"
    }
}

fun main(args: Array<String>) {
    val data: Tree = Node(Node(Leaf()), Leaf(), Leaf())
    val serializer = Serializer()
    println("text: " + data.accept(serializer))
}
