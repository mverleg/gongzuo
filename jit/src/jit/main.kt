package jit

import jit.ast.*

// todo: rewrite: store the benchmark data outside the preliminary-compiled object (perhaps pass it in)
// todo: how to connect preliminary compiled versions to final ones? is that even necessary? not really if the benchmark data is separate
// todo: mark most AST nodes as Exec except the ones that are different in prelim/opt mode (e.g. If)
// todo: make function definition keep back-references to call sites
// todo: make sure call sites don't know about optimization level of callee
// todo: make every prelim expression count it's calls (but not necessarily have the same cutoff)

fun main(args: Array<String>) {
    val source = Package(listOf(
        FunDef(Name("main"), FunCall(Name("call_me"))),
        FunDef(Name("call_me"), ArithmOp(ArithmOp.ArithmOpType.ADD, Const(5), Const(8)))
    ))
//    Processor(pack).run()
}


