package jit.jit

import jit.code.FunDefCode
import jit.hardware.Processor
import jit.instructions.FunctionInstruction
import jit.prelim.PrelimCompiler

/**
 * Placeholder for piece of code that has never been run yet and as such, not compiled.
 * This compiles the source and replaces the function by it on first invocation.
 *
 * I'm assuming here that the {@link Processor} can run the {@link Compiler} without my help.
 */
class CompileOnDemand(val preComp: PrelimCompiler, val funCode: FunDefCode):
        FunctionInstruction(funCode.name, funCode.parameters) {

    var isCompiled = false

    override fun invoke(processor: Processor, args: List<Int>): Int {
        check(!isCompiled, { "Function ${name} is already compiled; it should not be compiled twice." })
        isCompiled = true
        /* Compile the function in preliminary mode. */
        val assembly = preComp.compile(funCode)
        print(assembly.toText())
        /* Replace this placeholder by the compiled version. */
        processor.replace(funCode.name, assembly)
        /* Call the original function, passing on the arguments. */
        return processor.call(funCode.name, args)
    }

    override fun toText(): CharSequence {
        return "call @COMPILE('{$name}')"
    }
}

