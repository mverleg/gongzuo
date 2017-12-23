package jit.code

import jit.common.Code
import jit.common.Compiler
import jit.common.InstructionList
import jit.common.BinaryNumberLogicOperation

class BinaryLogicCode(var op: BinaryNumberLogicOperation, val left: Code<Int>, val right: Code<Int>): Code<Boolean> {

    override fun toCompiler(compiler: Compiler): InstructionList {
        return compiler.compile(this)
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
//    override fun run(proc: Processor): Boolean {
//        when (op) {
//            BinaryNumberLogicOperation.EQ ->  return left.run(proc) == right.run(proc)
//            BinaryNumberLogicOperation.NEQ -> return left.run(proc) != right.run(proc)
//            BinaryNumberLogicOperation.LT ->  return left.run(proc) <  right.run(proc)
//            BinaryNumberLogicOperation.GT ->  return left.run(proc) >  right.run(proc)
//            BinaryNumberLogicOperation.LTE -> return left.run(proc) <= right.run(proc)
//            BinaryNumberLogicOperation.GTE -> return left.run(proc) >= right.run(proc)
//        }
//    }
}


