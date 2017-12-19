package jit

import jit.ast.Name
import jit.comp.Exec

/**
 * The processor executes the AST instructions. It may not do any special processing,
 * since the physical processor also cannot do that; the {@link jit.JIT} must do this.
 */
class Processor(val blocks: MutableMap<Name, Exec>) {

    var currentBlock: Exec? = null;

    fun run() {
        println("Running " + blocks)
    }

    fun replace(newCode: Exec) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}


