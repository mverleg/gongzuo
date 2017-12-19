package jit

import jit.ast.Name
import jit.comp.Exec

/**
 * The processor executes the AST instructions. It may not do any special processing,
 * since the physical processor also cannot do that; the {@link jit.JIT} must do this.
 */
class Processor(val blocks: MutableMap<Name, Exec>) {

    fun run() {
        println("Running " + blocks)
    }
}


