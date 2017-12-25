package jit.code

import jit.common.BinaryNumberLogicOperation
import jit.common.Code
import jit.common.Compiler
import jit.common.Inter

class BinaryLogicCode(var op: BinaryNumberLogicOperation, val leftCode: Code<Int>, val rightCode: Code<Int>): Code<Boolean> {

    override fun toCompiler(compiler: Compiler): Inter<Boolean> {
        return compiler.compile(this)
    }

    override fun toText(): String {
        when (op) {
            BinaryNumberLogicOperation.EQ  -> return "(" + leftCode.toText() + " == " + rightCode.toText() + ")"
            BinaryNumberLogicOperation.NEQ -> return "(" + leftCode.toText() + " != " + rightCode.toText() + ")"
            BinaryNumberLogicOperation.LT  -> return "(" + leftCode.toText() + " < "  + rightCode.toText() + ")"
            BinaryNumberLogicOperation.GT  -> return "(" + leftCode.toText() + " > "  + rightCode.toText() + ")"
            BinaryNumberLogicOperation.LTE -> return "(" + leftCode.toText() + " <= " + rightCode.toText() + ")"
            BinaryNumberLogicOperation.GTE -> return "(" + leftCode.toText() + " >= " + rightCode.toText() + ")"
        }
    }
//
//    override fun precomp(): PrelimExec<Boolean> {
//        return this;
//    }
//
//    override fun optcomp(prelimExec: PrelimExec<Boolean>): OptExec<Boolean> {
//        return this;
//    }
//
//    override fun run(proc: HighProcessor): Boolean {
//        when (operation) {
//            BinaryNumberLogicOperation.EQ ->  return leftCode.run(proc) == right.run(proc)
//            BinaryNumberLogicOperation.NEQ -> return leftCode.run(proc) != right.run(proc)
//            BinaryNumberLogicOperation.LT ->  return leftCode.run(proc) <  right.run(proc)
//            BinaryNumberLogicOperation.GT ->  return leftCode.run(proc) >  right.run(proc)
//            BinaryNumberLogicOperation.LTE -> return leftCode.run(proc) <= right.run(proc)
//            BinaryNumberLogicOperation.GTE -> return leftCode.run(proc) >= right.run(proc)
//        }
//    }
}


