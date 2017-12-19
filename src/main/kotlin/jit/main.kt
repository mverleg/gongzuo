package jit

import jit.ast.*

// todo: mark most AST nodes as Exec except the ones that are different in prelim/opt mode (e.g. If)

fun main(args: Array<String>) {
    val pack = Package(listOf(
        FunDef(Name("main"), FunCall(Name("call_me"))),
        FunDef(Name("call_me"), ArithmOp(ArithmOp.ArithmOpType.ADD, Const(5), Const(8)))
    ))
    Processor(pack).run()
}


