package jit.code

import jit.code.cmds.ArithmBinOp
import jit.code.cmds.ArithmUnOp

interface SourceVisitor {
    fun compile(arithmBinOp: ArithmBinOp)
    fun compile(arithmUnOp: ArithmUnOp, op: ArithmUnOp.ArithmUnOpType, visit: Unit)
}

