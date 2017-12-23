package jit

import jit.code.Package
import jit.code.FunDef
import jit.common.Name
import jit.comp.FunDefExec
import jit.comp.PreCompiler
import jit.comp.ToCompExec

val MAIN_NAME = Name("main")

/**
 * This JIT manages the code being compiled and recompiled, delegating actual compilation to {@link Compiler}.
 *
 * This operates on definitions and links between them, not looking into individual functions.
 */
class JIT(val pack: Package) {

    fun run() {
        val blocks: MutableMap<Name, FunDefExec> = HashMap<Name, FunDefExec>();
        var preComp = PreCompiler()
        for (fn: FunDef in pack) {
            check(!blocks.containsKey(fn.name), { "Cannot have two functions with the same name" })
            blocks.put(fn.name, ToCompExec(preComp, fn.body))
        }
        val main: FunDefExec? = blocks.getOrDefault(MAIN_NAME, null)
        check(main != null)
        Processor(blocks).call(MAIN_NAME)
    }
}


