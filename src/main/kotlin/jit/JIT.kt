package jit

import jit.ast.Package
import jit.ast.FunDef
import jit.ast.Name
import jit.comp.Exec
import jit.comp.ToCompExec

/**
 * This JIT manages the code being compiled and recompiled, delegating actual compilation to {@link Compiler}.
 */
class JIT(val pack: Package) {

    fun run() {
        val blocks: MutableMap<Name, Exec> = HashMap<Name, Exec>();
        for (fn: FunDef in pack) {
            blocks.put(fn.name, ToCompExec(fn.body))
        }
        Processor(blocks).run();
    }
}


