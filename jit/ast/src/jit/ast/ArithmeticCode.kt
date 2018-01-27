package jit.ast

import jit.utils.BinaryArithmOperation
import jit.common.Compiler
import jit.common.Inter
import jit.utils.UnaryArithmOperation

class BinArithmCode(var operation: BinaryArithmOperation, val leftCode: Code<Int>, val rightCode: Code<Int>): Code<Int> {

    override fun toCompiler(compiler: Compiler): Inter<Int> {
        return compiler.compile(this)
    }

    override fun toText(): String {
        when (operation) {
            BinaryArithmOperation.ADD -> return "(" + leftCode.toText() + " + " + rightCode.toText() + ")"
            BinaryArithmOperation.SUB -> return "(" + leftCode.toText() + " - " + rightCode.toText() + ")"
            BinaryArithmOperation.MUL -> return "(" + leftCode.toText() + " * " + rightCode.toText() + ")"
            BinaryArithmOperation.DIV -> return "(" + leftCode.toText() + " / " + rightCode.toText() + ")"
            BinaryArithmOperation.MOD -> return "(" + leftCode.toText() + " % " + rightCode.toText() + ")"
        }
    }

//    var leftPrelimExec: PrelimExec<Int>? = null
//    var rightPrelimExec: PrelimExec<Int>? = null
//    var leftExec: Exec<Int>? = null
//    var rightExec: Exec<Int>? = null
//
//    override fun precomp(): PrelimExec<Int> {
//        leftPrelimExec = leftCode.precomp()
//        rightPrelimExec = leftCode.precomp()
//        leftExec = leftPrelimExec
//        rightExec = rightPrelimExec
//        return this;
//    }
//
//    override fun optcomp(prelimExec: PrelimExec<Int>): OptExec<Int> {
//        if (leftPrelimExec == null || rightPrelimExec == null) {
//            throw IllegalStateException("Compile in preliminary mode before compiling in optimized mode")
//        }
//        leftExec = leftCode.optcomp(leftPrelimExec!!)
//        rightExec = leftCode.optcomp(rightPrelimExec!!)
//        return this;
//    }
//
//    override fun run(proc: HighProcessor): Int {
//        if (leftPrelimExec == null || rightPrelimExec == null) {
//            throw IllegalStateException("Compile before running")
//        }
//        when (operation) {
//            BinArithmCode.ArithmBinOpType.ADD -> return leftExec!!.run(proc) + rightExec!!.run(proc)
//            BinArithmCode.ArithmBinOpType.SUB -> return leftExec!!.run(proc) - rightExec!!.run(proc)
//            BinArithmCode.ArithmBinOpType.MUL -> return leftExec!!.run(proc) * rightExec!!.run(proc)
//            BinArithmCode.ArithmBinOpType.DIV -> return leftExec!!.run(proc) / rightExec!!.run(proc)
//            BinArithmCode.ArithmBinOpType.MOD -> return leftExec!!.run(proc) % rightExec!!.run(proc)
//        }
//    }
}

class UnaryArithmCode(var operation: UnaryArithmOperation, val code: Code<Int>): Code<Int> {
    override fun toCompiler(compiler: Compiler): Inter<Int> {
        return compiler.compile(this)
    }

    override fun toText(): String {
        when (operation) {
            UnaryArithmOperation.NEG -> return "-("  + code.toText() + ")"
            UnaryArithmOperation.SQR -> return  "("  + code.toText() + ")^2"
        }
    }
}
