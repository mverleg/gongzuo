package jit.comp

import jit.Processor
import jit.ast.Name

interface FunDefExec: Exec<Int> {
}

class PrelimFunDef(val code: Exec<Int>): FunDefExec, PrelimExec<Int> {
    override fun run(proc: Processor): Int {
        return code.run(proc)
    }
}

class OptFunDef(val code: Exec<Int>): FunDefExec, OptExec<Int> {
    override fun run(proc: Processor): Int {
        return code.run(proc)
    }
}

/* Should not know about the optimization state of the callee. */

class FunCallExec(val funName: Name): PrelimExec<Int>, OptExec<Int> {
    override fun run(proc: Processor): Int {
        return proc.call(funName)
    }
}
