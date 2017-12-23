package visit_tree.op_print

import visit_tree.data.Leaf
import visit_tree.data.Split
import visit_tree.data.TreeVisitor

class Printer: TreeVisitor<String> {
    override fun visit(tree: Leaf): String {
        return "leaf "
    }

    override fun visit(tree: Split): String {
        return "split "
    }
}
