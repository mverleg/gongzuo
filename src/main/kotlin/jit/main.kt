package jit

import jit.ast.*

fun main(args: Array<String>) {
    val pack = Package(listOf(
        FunDef(Name("main"), FunCall(Name("call_me"))),
        FunDef(Name("call_me"), ArithmOp(ArithmOp.ArithmOpType.ADD, Const(5), Const(8)))
    ))
    Processor(pack).run()
}


