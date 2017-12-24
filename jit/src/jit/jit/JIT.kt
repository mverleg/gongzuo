package jit.jit

import jit.code.FunDefCode
import jit.code.PackageCode
import jit.common.CompileInvalidCodeError
import jit.common.Name
import jit.hardware.Processor
import jit.instructions.FunctionInstruction
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
        for (func: FunDefCode in pack) {
            if (blocks.containsKey(func.name)) {
                throw CompileInvalidCodeError("Cannot have two functions with name '{fn.name}'")
            }
            blocks.put(func.name, CompileOnDemand(preComp, func))
        }
        val main: FunctionInstruction? = blocks.getOrDefault(MAIN_NAME, null)
        check(main != null)
        Processor(blocks).call(MAIN_NAME, listOf())
    }
}


