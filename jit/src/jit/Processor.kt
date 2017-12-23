package jit

import jit.ast.FunDef
import jit.ast.Name
import jit.comp.Exec
import jit.comp.FunDefExec

/**
 * The processor executes the AST instructions. It may not do any special processing,
 * since the physical processor also cannot do that; the {@link jit.JIT} must do this.
 */
class Processor(val blocks: MutableMap<Name, FunDefExec>) {

    // TODO: replace by stack
    var currentBlock: Exec<Int>? = null;

    fun replace(newCode: FunDef) {
        throw NotImplementedError()
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun call(funName: Name): Int {
        throw NotImplementedError()
        TODO("not implemented")
    }
}


