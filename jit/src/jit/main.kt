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
import jit.code.ReadCode
import jit.code.UnaryArithmCode
import jit.code.VariableMention
import jit.common.BinaryArithmOperation
import jit.common.BinaryNumberLogicOperation
import jit.common.Name
import jit.common.UnaryArithmOperation
import jit.jit.JIT

fun main(args: Array<String>) {
    val a = VariableMention(Name("a"))
    val a2 = VariableMention(Name("a2"))
    val b = VariableMention(Name("b"))
    val source = PackageCode(listOf(
            FunDefCode(FunCallCode(Name("call_me"), listOf(ConstCode(3))), Name("main")),
            FunDefCode(CodeCombi(
                    DeclarationCode(a2, DeclarationCode(a, UnaryArithmCode(UnaryArithmOperation.SQR, ConstCode(4)))),
                    DeclarationCode(b, BinArithmCode(BinaryArithmOperation.SUB, ReadCode(a), ConstCode(7))),
                    last=IfCode(
                            BinaryLogicCode(BinaryNumberLogicOperation.GTE, ReadCode(b), ConstCode(10)),
                            ReadCode(a),
                            UnaryArithmCode(UnaryArithmOperation.NEG, ReadCode(b)))
            ), Name("call_me"), listOf(VariableMention("arg_one")))
    ))
    print(source.toText())  // TODO
    JIT(source).run()
//    Processor(pack).run()
}


