
import jit.ast.BinArithmCode
import jit.ast.BinaryLogicCode
import jit.ast.CodeCombi
import jit.ast.ConstCode
import jit.ast.DeclarationCode
import jit.ast.FunCallCode
import jit.ast.FunDefCode
import jit.ast.IfCode
import jit.ast.PackageCode
import jit.ast.ReadCode
import jit.ast.UnaryArithmCode
import jit.ast.VariableMention
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
    TODO("JIT should operate on IR, not source")
    JIT(source).run()
//    HighProcessor(pack).run()
}


