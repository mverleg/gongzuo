package visit_tree

import visit_tree.data.Leaf
import visit_tree.data.Split
import visit_tree.op_print.Printer
import visit_tree.op_transform.Transform

fun main(args: Array<String>) {
    val tree = Split(
            Split(
                    Leaf(),
                    Leaf()
            ),
            Split(
                    Leaf(),
                    Split(
                            Leaf(),
                            Leaf()
                    )
            )
    )
    val printer = Printer()
    // TODO: here I also typed printer.visit(tree) at first, which only visits one node; how to avoid?
    // .visit should only be callable from the data, and only for it's own data type; this seems impossible to enforce automatically
    println(tree.accept(printer))
    println(tree.accept(Transform()).toDebugText())
}


