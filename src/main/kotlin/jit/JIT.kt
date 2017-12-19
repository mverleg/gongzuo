package jit

import jit.ast.Package
import jit.ast.FunDef
import jit.ast.Name

class JIT(val pack: Package) {

    fun run() {
        val blocks: MutableMap<Name, Cmd> = HashMap<Name, Cmd>();
        for (fn: FunDef in pack) {
            blocks.put(fn.name, Cmd(fn.body))
        }
        Processor(blocks).run();
    }
}
