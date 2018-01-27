package jit.instructions

import jit.utils.Name
import jit.utils.RuntimeInvalidCodeError
import jit.intermediary.BlockSet
import jit.intermediary.ValueInter
import jit.intermediary.Variable
import jit.intermediary.WriteInter

abstract class FunctionInter(val name: Name, val parameters: List<Variable>) {

    abstract fun invoke(processor: HighProcessor, args: List<Int>): Int

    protected fun bindArgs(processor: HighProcessor, args: List<Int>) {
        if (parameters.size != args.size) {
            throw RuntimeInvalidCodeError("function '${name}' takes ${parameters.size} arguments but got ${args.size}")
        }
        for (indx in 0 .. parameters.size - 1) {
            WriteInter(parameters[indx], ValueInter(args[indx])).run(processor)
        }
    }

    abstract fun toText(): CharSequence
}

abstract class CompiledFunctionInter(val instructions: BlockSet, name: Name, parameters: List<Variable>):
        FunctionInter(name, parameters) {

    override fun invoke(processor: HighProcessor, args: List<Int>): Int {
//        if (inters == null) {
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

class PrelimFunctionInter(instruction: BlockSet, name: Name, parameters: List<Variable>):
        CompiledFunctionInter(instruction, name, parameters) {
}

class OptFunctionInter(instructions: BlockSet, name: Name, parameters: List<Variable>):
        CompiledFunctionInter(instructions, name, parameters) {
}


