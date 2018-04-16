package blag.dispatch

class Bla {
    fun method(node: Node) {
        println("Bla.method for node")
    }
    fun method(leaf: Leaf) {
        println("Bla.method for leaf")
    }
}

public fun main(args: Array<String>) {
//    Bla().method(treeFactory())
}
