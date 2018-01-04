package visit_tree.op_flatten

import visit_tree.data.Leaf
import visit_tree.data.Split
import visit_tree.data.TreeVisitor
import visit_tree.op_transform.TransformedElem
import visit_tree.op_transform.TransformedLeaf
import visit_tree.op_transform.TransformedSplit

class Flatten: TreeVisitor<Iterable<TransformedElem>> {
    // TODO("nested Iterable is rather unfortunate")
    override fun combine(inputs: Iterable<Iterable<TransformedElem>>): Iterable<TransformedElem> {
        val flat: MutableList<TransformedElem> = mutableListOf()
        for (input in inputs) {
            flat.addAll(input)
        }
        return flat
    }

    override fun visit(leaf: Leaf): Iterable<TransformedLeaf> {
        // TODO("listOf is also unfortunate")
        // TODO the underlying problem is that to be generic, elements can only be combined into the same type (a collection of things should have the same type as an individual thing, otherwise tree-transforms aren't possible)
        return listOf(TransformedLeaf())
    }

    override fun visit(split: Split): Iterable<TransformedSplit> {
        return listOf(TransformedSplit())
    }
}
