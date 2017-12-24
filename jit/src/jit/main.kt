package jit

import jit.code.BinArithmCode
import jit.code.BinaryLogicCode
import jit.code.CodeCombi
import jit.code.ConstCode
import jit.code.DeclarationCode
import jit.code.FunCallCode
import jit.code.FunDefCode
import jit.code.IfCode
import jit.code.PackageCode
import jit.code.UnaryArithmCode
import jit.code.VarCode
import jit.common.BinaryArithmOperation
import jit.common.BinaryNumberLogicOperation
import jit.common.Name
import jit.common.UnaryArithmOperation
import jit.jit.JIT


fun main(args: Array<String>) {
    val a = VarCode(Name("a"))
    val a2 = VarCode(Name("a2"))
    val b = VarCode(Name("b"))
    val source = PackageCode(listOf(
            FunDefCode(FunCallCode(Name("call_me"), listOf(ConstCode(3))), Name("main")),
            FunDefCode(CodeCombi(
                    DeclarationCode(a2, DeclarationCode(a, UnaryArithmCode(UnaryArithmOperation.SQR, ConstCode(4)))),
                    DeclarationCode(b, BinArithmCode(BinaryArithmOperation.SUB, a, ConstCode(7))),
                    last=IfCode(
                            BinaryLogicCode(BinaryNumberLogicOperation.GTE, b, ConstCode(10)),
                            a,
                            UnaryArithmCode(UnaryArithmOperation.NEG, b))
            ), Name("call_me"), listOf(Name("arg_one")))
    ))
    print(source.toText())  // TODO
    JIT(source).run()
//    Processor(pack).run()
}


