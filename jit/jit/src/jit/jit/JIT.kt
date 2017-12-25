package jit.jit

import jit.code.FunDefCode
import jit.code.PackageCode
import jit.common.CompileInvalidCodeError
import jit.common.Name
import jit.instructions.FunctionInter
import jit.high.prelim.PrelimCompiler

val MAIN_NAME = Name("main")

/**
 * This JIT manages the ast being compiled and recompiled, delegating actual compilation to {@link Compiler}.
 *
 * This operates on definitions and links between them, not looking into individual functions.
 */
class JIT(val pack: PackageCode) {

    fun run() {
        val blocks: MutableMap<Name, FunctionInter> = HashMap<Name, FunctionInter>();
        var preComp = PrelimCompiler()
        for (func: FunDefCode in pack) {
            if (blocks.containsKey(func.name)) {
                throw CompileInvalidCodeError("Cannot have two functions with name '{fn.name}'")
            }
            blocks.put(func.name, CompileOnDemand(preComp, func))
        }
        for (instr in blocks.values) {
            println(instr.toText())  // TODO
        }
        val main: FunctionInter? = blocks.getOrDefault(MAIN_NAME, null)
        check(main != null)
        HighProcessor(blocks).call(MAIN_NAME, listOf())
    }
}


