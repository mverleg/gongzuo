package blag.datastruct

import java.util.Stack

/// Data.

interface Tree {
	// We also use Composite pattern
	public fun accept(visitor: Visitor)
}

class Node(public vararg val children: Tree): Tree {
	// This method delegates to childen, because the visitor
	// doesn't know about the structure anymore.
	override fun accept(visitor: Visitor) {
		visitor.startVisitNode(this)
		for (child in children) {
			child.accept(visitor)
		}
		visitor.endVisitNode(this)
	}
}

class Leaf(): Tree {
	override fun accept(visitor: Visitor) {
		visitor.startVisitLeaf(this)
		visitor.endVisitLeaf(this)
	}
}

interface Visitor {
	fun startVisitNode(node: Node)
	fun endVisitNode(node: Node)
	fun startVisitLeaf(leaf: Leaf)
	fun endVisitLeaf(leaf: Leaf)

}

/// First operation: Serialize to XML.

class Serializer: Visitor {

	val xml = StringBuilder()

	override fun startVisitNode(node: Node) {
		xml.append("<node>")
	}

	override fun endVisitNode(node: Node) {
		xml.append("</node>")
	}

	override fun startVisitLeaf(leaf: Leaf) {
		xml.append("<leaf/>")
	}

	override fun endVisitLeaf(leaf: Leaf) {
	}
}

/// Second operation: Convert to another tree.

interface NewTree {}

class NewNode: NewTree {
	fun add(newNode: NewTree) {
		children.add(newNode)
	}

	override fun toString(): String {
		return "NewNode($children)"
	}

	val children: MutableList<NewTree> = mutableListOf()
}

class NewLeaf: NewTree {}

class Converter: Visitor {

	var activeNodes: Stack<NewNode> = Stack<NewNode>()
	init {
		// This node just holds children but isn't part of the tree
		activeNodes.push(NewNode())
	}

	override fun startVisitNode(node: Node) {
		val newNode = NewNode()
		activeNodes.peek().add(newNode)
		activeNodes.push(newNode)
	}

	override fun endVisitNode(node: Node) {
		/// EmptyStackException should not happen in practice
		activeNodes.pop()
	}

	override fun startVisitLeaf(leaf: Leaf) {
		activeNodes.peek().add(NewLeaf())
	}

	override fun endVisitLeaf(leaf: Leaf) {
	}

	fun getTree(): NewTree {
		assert(activeNodes.size == 1)
		return activeNodes.peek().children.first()
	}
}

/// Testing.

fun main(args: Array<String>) {
	val data: Tree = Node(Node(Leaf()), Leaf(), Leaf())
	val serializer = Serializer()
	data.accept(serializer)
	println("text: " + serializer.xml.toString())
	val converter = Converter()
	data.accept(converter)
	println("new tree: " + converter.getTree())
}

