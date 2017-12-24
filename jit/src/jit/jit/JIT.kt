package jit

import jit.code.PackageCode
import jit.code.FunDefCode
import jit.common.Name
import jit.hardware.Processor
import jit.instructions.FunctionInstruction
import jit.jit.CompileOnDemand
import jit.prelim.PrelimCompiler

val MAIN_NAME = Name("main")

/**
 * This JIT manages the code being compiled and recompiled, delegating actual compilation to {@link Compiler}.
 *
 * This operates on definitions and links between them, not looking into individual functions.
 */
class JIT(val pack: PackageCode) {

    fun run() {
        val blocks: MutableMap<Name, FunctionInstruction> = HashMap<Name, FunctionInstruction>();
        var preComp = PrelimCompiler()
        for (fn: FunDefCode in pack) {
            check(!blocks.containsKey(fn.name), { "Cannot have two functions with the same name" })
            blocks.put(fn.name, CompileOnDemand(preComp, fn))
        }
        val main: FunctionInstruction? = blocks.getOrDefault(MAIN_NAME, null)
        check(main != null)
        Processor(blocks).call(MAIN_NAME)
    }
}


