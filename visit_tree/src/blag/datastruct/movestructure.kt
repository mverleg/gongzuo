//package blag.datastruct
//
//    /// Data.
//
//    interface Tree {
//        public fun acceptSerializer(serializer: Serializer): CharSequence
//    }
//
//    class Node(public vararg val children: Tree): Tree {
//        // This method needs to delegate to children, because the visitor doesn't know about the data structure
//        // But to able to combine the results of delegated calls,
//        public final override fun acceptSerializer(serializer: Serializer): CharSequence {
//            val parts = mutableListOf(serializer.visitNodeStart(this))
//            for (child in children) {
//                parts.add(child.acceptSerializer(serializer))
//            }
//            parts.add(serializer.visitNodeEnd(this))
//            return Serializer.combine(*parts.toTypedArray())
//        }
//    }
//
//    class Leaf(): Tree {
//        public final override fun acceptSerializer(serializer: Serializer): CharSequence {
//            return Serializer.combine(
//                    serializer.visitLeafStart(this),
//                    serializer.visitLeafEnd(this)
//            )
//        }
//    }
//
//    /// First operation: Serialize to XML.
//
//    class Serializer {
//        companion object {
//            fun combine(vararg parts: CharSequence): CharSequence {
//                return parts.joinToString("")
//            }
//        }
//
//        // The visit methods don't know about the tree structure anymore
//        final public fun visitNodeStart(node: Node): CharSequence = "<node>"
//        final public fun visitNodeEnd(node: Node): CharSequence = "</node>"
//        final public fun visitLeafStart(leaf: Leaf): CharSequence = "<leaf/>"
//        final public fun visitLeafEnd(leaf: Leaf): CharSequence = ""
//    }
//
//    /// Second operation: Convert to another tree.
//
//    //interface NewTree {}
//    //
//    //data class NewNode(public val children: Array<NewTree>): NewTree {}
//    //
//    //class NewLeaf: NewTree {}
//    //
//    //class Converter: Visitor<NewTree> {
//    //    final override fun visitNode(node: Node): NewTree {
//    //        return NewNode(node.children.map { it.accept(this) }.toTypedArray())
//    //    }
//    //
//    //    final override fun visitLeaf(leaf: Leaf): NewTree {
//    //        return NewLeaf()
//    //    }
//    //}
//
//    /// Testing.
//
//    fun main(args: Array<String>) {
//        val data: Tree = Node(Node(Leaf()), Leaf(), Leaf())
//        val serializer = Serializer()
//        println("text: " + data.acceptSerializer(serializer))
//    //    val converter = Converter()
//    //    println("new tree: " + data.accept(converter))
//    }
//
