package jit.jit

import jit.code.FunDefCode
import jit.utils.Type
import jit.instructions.FunctionInter
import jit.intermediary.Variable
import jit.high.prelim.PrelimCompiler

/**
 * Placeholder for piece of code that has never been run yet and as such, not compiled.
 * This compiles the source and replaces the function by it on first invocation.
 *
 * I'm assuming here that the {@link HighProcessor} can run the {@link Compiler} without my help.
 */
class CompileOnDemand(val preComp: PrelimCompiler, val funCode: FunDefCode):
        FunctionInter(funCode.name, funCode.parameters.map{ Variable(it, Type()) }) {

    var isCompiled = false

    override fun invoke(processor: HighProcessor, args: List<Int>): Int {
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
        val text = StringBuilder("define i32 @").append(name).append("(")
                .append(parameters.map { "i32 %" + it.toString() }.joinToString(", "))
                .append(") {\nentry:\n\t")
                .append("call @COMPILE('")
                .append(name)
                .append("')\n}\n")
        return text
    }
}

