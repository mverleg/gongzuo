package blag.toxml

interface Tree {
    public fun toXML(): CharSequence
}

class Node(public vararg val children: Tree): Tree {
    override fun toXML(): CharSequence {
        TODO("This is left as an exercise to the student")
    }
}

class Leaf(): Tree {
    override fun toXML(): CharSequence {
        TODO("This is left as an exercise to the student")
    }
}

fun main(args: Array<String>) {
    val data: Tree = Node(Node(Leaf()), Leaf(), Leaf())
    println("text: " + data.toXML())
}