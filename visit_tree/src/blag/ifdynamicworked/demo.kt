package blag.ifdynamicworked

interface Tree {}

class Node(public vararg val children: Tree): Tree {}

class Leaf(): Tree {}

class Serializer {
    final public fun serialize(node: Node): CharSequence {
        val txt = StringBuilder("<node>")
        for (child in node.children) {
//            txt.append(serialize(child))
        }
        return txt.append("</node>")
    }

    final public fun serialize(leaf: Leaf): CharSequence {
        return "<leaf/>"
    }
}

fun main(args: Array<String>) {
    val data: Tree = Node(Node(Leaf()), Leaf(), Leaf())
    val serializer = Serializer()
//    println("text: " + serializer.serialize(data))
}
