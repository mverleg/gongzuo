package jit.instructions

import jit.common.Name
import jit.common.RuntimeInvalidCodeError
import jit.hardware.Processor

abstract class FunctionInstruction(val name: Name, val parameters: List<Variable>) {

    abstract fun invoke(processor: Processor, args: List<Int>): Int

    protected fun bindArgs(processor: Processor, args: List<Int>) {
        if (parameters.size != args.size) {
            throw RuntimeInvalidCodeError("function '${name}' takes ${parameters.size} arguments but got ${args.size}")
        }
        for (indx in 0 .. parameters.size - 1) {
            WriteInstruction(parameters[indx], ValueInstruction(args[indx])).run(processor)
        }
    }

    abstract fun toText(): CharSequence
}

abstract class CompiledFunctionInstruction(val instructions: BlockSet, name: Name, parameters: List<Variable>):
        FunctionInstruction(name, parameters) {

    override fun invoke(processor: Processor, args: List<Int>): Int {
//        if (instructions == null) {
//            throw RuntimeInvalidCodeError("Found empty body for function '${name}' when running with '${processor}'")
//        }
        bindArgs(processor, args)
        return instructions.run(processor)
    }

    override fun toText(): CharSequence {
        val text = StringBuilder("define i32 @").append(name).append("(")
        text.append(parameters.map { "i32 %" + it.toString() }.joinToString(", "))
        text.append(") {\nentry:\n\t")
        text.append(instructions.toText())
        text.append("}\n")
        return text
    }
}

class PrelimFunctionInstruction(instruction: BlockSet, name: Name, parameters: List<Variable>):
        CompiledFunctionInstruction(instruction, name, parameters) {
}

class OptFunctionInstruction(instructions: BlockSet, name: Name, parameters: List<Variable>):
        CompiledFunctionInstruction(instructions, name, parameters) {
}


