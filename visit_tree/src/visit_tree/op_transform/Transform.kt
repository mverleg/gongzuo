package visit_tree.op_transform

import visit_tree.data.Leaf
import visit_tree.data.Split
import visit_tree.data.TreeVisitor

class Transform: TreeVisitor<TransformedElem> {
    override fun combine(inputs: Iterable<TransformedElem>): TransformedElem {
        val iter = inputs.iterator()
        if (!iter.hasNext()) {
            // This is regretably, should be a compile-time error (and it can be, but not in a general way).
            throw IllegalArgumentException("Transform.combine needs at least one argument")
        }
        val top = iter.next()
        if (top !is TransformedSplit) {
            // This is regretably, should be a compile-time error (and it can be, but not in a general way).
            throw IllegalArgumentException("First argument to Transform.combine should be a split")
        }
        top.add(iter)
        return top
    }

    override fun visit(tree: Leaf): TransformedLeaf {
        return TransformedLeaf()
    }

    override fun visit(tree: Split): TransformedSplit {
        return TransformedSplit()
    }
}
