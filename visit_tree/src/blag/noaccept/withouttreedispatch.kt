package blag.noaccept

interface Tree {}

class Node(public vararg val children: Tree): Tree {}

class Leaf(): Tree {}

class Serializer {
    final public fun serialize(elem: Tree): CharSequence {
        return when (elem) {
            is Node -> serializeNode(elem)
            is Leaf -> serializeLeaf(elem)
            else -> throw NotImplementedError("Don't know how to deal with "
                    + elem + "; this is a bug")
        }
        // We are basically implementing a vtable, which is error-prone and
        // should not be necessary. This is a hint for what we really want:
        // dynamic dispatch on the `elem` parameter.
    }

    final public fun serializeNode(node: Node): CharSequence {
        val txt = StringBuilder("<node>")
        for (child in node.children) {
            txt.append(serialize(child))
        }
        return txt.append("</node>")
    }

    final public fun serializeLeaf(leaf: Leaf): CharSequence {
        return "<leaf/>"
    }
}

fun main(args: Array<String>) {
    val data: Tree = Node(Node(Leaf()), Leaf(), Leaf())
    val serializer = Serializer()
    println("text: " + serializer.serialize(data))
}
