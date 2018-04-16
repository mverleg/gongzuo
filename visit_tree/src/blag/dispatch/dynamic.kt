package blag.dispatch

import java.util.*

interface Tree {
    public fun method()
}

class Node(public vararg val children: Tree): Tree {
    override fun method() {
        println("Node.method")
    }
}

class Leaf(): Tree {
    override fun method() {
        println("Leaf.method")
    }
}

public fun treeFactory(): Tree {
    if (Random().nextInt(2) == 0) {
        return Node()
    } else {
        return Leaf()
    }
}

public fun main(args: Array<String>) {
    treeFactory().method()
}