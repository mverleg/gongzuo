package jit.instructions

import jit.common.Instruction
import jit.common.Name
import jit.common.RuntimeInvalidCodeError
import jit.hardware.Processor

abstract class FunctionInstruction(val name: Name, val parameters: List<Name>) {

    abstract fun invoke(processor: Processor, args: List<Int>): Int

    protected fun bindArgs(processor: Processor, args: List<Int>) {
        if (parameters.size != args.size) {
            throw RuntimeInvalidCodeError("function '${name}' takes ${parameters.size} arguments but got ${args.size}")
        }
        for (indx in 0 .. parameters.size - 1) {
            WriteInstruction(parameters[indx], args[indx]).run(processor)
        }
    }
}

class PrelimFunctionInstruction(val instruction: Instruction<Int>, name: Name, parameters: List<Name>):
        FunctionInstruction(name, parameters) {

    override fun invoke(processor: Processor, args: List<Int>): Int {
        /* @implNote See {@code OptFunctionInstruction.invoke} */
//        if (instruction == null) {
//            throw RuntimeInvalidCodeError("Found empty body for function '${name}' when running with '${processor}'")
//        }
        bindArgs(processor, args)
        return instruction.run(processor)
    }
}

class OptFunctionInstruction(val instruction: Instruction<Int>, name: Name, parameters: List<Name>):
        FunctionInstruction(name, parameters) {

    override fun invoke(processor: Processor, args: List<Int>): Int {
        /* @implNote See {@code PrelimFunctionInstruction.invoke} */
//        if (instruction == null) {
//            throw RuntimeInvalidCodeError("Found empty body for function '${name}' when running with '${processor}'")
//        }
        bindArgs(processor, args)
        return instruction.run(processor)
    }
}


