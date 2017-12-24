package jit.instructions

import jit.common.InstructionList
import jit.common.Name
import jit.common.RuntimeInvalidCodeError
import jit.hardware.Processor

abstract class FunctionInstruction(val name: Name, val parameters: List<Name>) {

    abstract fun invoke(processor: Processor, vararg args: Int): Int

    protected fun bindArgs(processor: Processor, vararg args: Int) {
        if (parameters.size != args.size) {
            throw RuntimeInvalidCodeError("function '${name}' takes ${parameters.size} arguments but got ${args.size}")
        }
        for (indx in 0 .. parameters.size) {
            WriteInstruction(parameters[indx], args[indx]).run(processor)
        }
    }
}

class PrelimFunctionInstruction(val instructions: InstructionList?, name: Name, parameters: List<Name>):
        FunctionInstruction(name, parameters) {

    override fun invoke(processor: Processor, vararg args: Int): Int {
        /* @implNote See {@code OptFunctionInstruction.invoke} */
        if (instructions == null) {
            throw RuntimeInvalidCodeError("Found empty body for function '${name}' when running with '${processor}'")
        }
        bindArgs(processor, *args)
        return instructions.run(processor)
    }
}

class OptFunctionInstruction(val instructions: InstructionList?, name: Name, parameters: List<Name>):
        FunctionInstruction(name, parameters) {

    override fun invoke(processor: Processor, vararg args: Int): Int {
        /* @implNote See {@code PrelimFunctionInstruction.invoke} */
        if (instructions == null) {
            throw RuntimeInvalidCodeError("Found empty body for function '${name}' when running with '${processor}'")
        }
        bindArgs(processor, *args)
        return instructions.run(processor)
    }
}


