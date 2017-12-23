package jit.code.cmds

import jit.code.Code
import jit.code.SourceVisitor

class ArithmBinOp(var op: ArithmBinOpType, val leftCode: Code<Int>, val rightCode: Code<Int>): Code<Int> {
    override fun accept(visitor: SourceVisitor) {
        visitor.compile(this)
    }

    enum class ArithmBinOpType {
        ADD,
        SUB,
        MUL,
        DIV,
        MOD,
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
//    override fun run(proc: Processor): Int {
//        if (leftPrelimExec == null || rightPrelimExec == null) {
//            throw IllegalStateException("Compile before running")
//        }
//        when (op) {
//            ArithmBinOp.ArithmBinOpType.ADD -> return leftExec!!.run(proc) + rightExec!!.run(proc)
//            ArithmBinOp.ArithmBinOpType.SUB -> return leftExec!!.run(proc) - rightExec!!.run(proc)
//            ArithmBinOp.ArithmBinOpType.MUL -> return leftExec!!.run(proc) * rightExec!!.run(proc)
//            ArithmBinOp.ArithmBinOpType.DIV -> return leftExec!!.run(proc) / rightExec!!.run(proc)
//            ArithmBinOp.ArithmBinOpType.MOD -> return leftExec!!.run(proc) % rightExec!!.run(proc)
//        }
//    }
}

class ArithmUnOp(var op: ArithmUnOpType, val code: Code<Int>): Code<Int> {
    override fun accept(compiler: SourceVisitor) {
        return compiler.compile(this, op, visitor.visit(code))
    }

    enum class ArithmUnOpType {
        NEG,
        SQR,
    }
}
