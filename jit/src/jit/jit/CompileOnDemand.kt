package jit.jit

import jit.code.FunDefCode
import jit.hardware.Processor
import jit.instructions.FunctionInstruction
import jit.prelim.PrelimCompiler

/**
 * Placeholder for piece of code that has never been run yet. Replace by {@code PrelimExec} on first invocation.
 *
 * I'm assuming here that the {@link Processor} can run the {@link Compiler} without my help.
 */
class CompileOnDemand(val preComp: PrelimCompiler, val funCode: FunDefCode): FunctionInstruction(null) {

    var assembly: FunctionInstruction? = null

    fun call(processor: Processor) {
        if (assembly == null) {
            assembly = preComp.compile(funCode)
        }
        processor.replace(funCode.name, assembly!!)
        processor.call(funCode.name)
//        assembly!!.run(processor)
    }
}

