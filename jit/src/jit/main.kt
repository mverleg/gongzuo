package jit

import jit.code.ArithmBinOp
import jit.code.ArithmUnOp
import jit.code.AssignmentCode
import jit.code.CodeCombi
import jit.code.ConstCode
import jit.code.FunCallCode
import jit.code.FunDefCode
import jit.code.PackageCode
import jit.code.VarCode
import jit.common.Name

// todo: rewrite: store the benchmark data outside the preliminary-compiled object (perhaps pass it in)
// todo: how to connect preliminary compiled versions to final ones? is that even necessary? not really if the benchmark data is separate
// todo: mark most AST nodes as Exec except the ones that are different in prelim/opt mode (e.g. If)
// todo: make function definition keep back-references to call sites
// todo: make sure call sites don't know about optimization level of callee
// todo: make every prelim expression count it's calls (but not necessarily have the same cutoff)

fun main(args: Array<String>) {
    val a = VarCode(Name("a"))
    val source = PackageCode(listOf(
            FunDefCode(Name("main"), FunCallCode(Name("call_me"))),
            FunDefCode(Name("call_me"), CodeCombi(
                    AssignmentCode(a, ArithmUnOp(ArithmUnOp.ArithmUnOpType.SQR, ConstCode(4))),
                    ArithmBinOp(ArithmBinOp.ArithmBinOpType.ADD, a, ConstCode(8))
            ))
    ))
//    Processor(pack).run()
}


