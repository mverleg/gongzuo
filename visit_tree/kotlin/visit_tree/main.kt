package visit_tree

import visit_tree.data.Leaf
import visit_tree.data.Split
import visit_tree.op_print.Printer

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
    println(printer.visit(tree))
}


