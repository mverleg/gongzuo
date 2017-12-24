package jit.hardware

import jit.common.Name
import jit.common.Processor
import jit.common.RuntimeInvalidCodeError
import jit.instructions.FunctionInstruction

/**
 * The processor executes the AST instruction. It may not do any special processing,
 * since the physical processor also cannot do that; the {@link JIT} must do this.
 */
class Processor(override val funDeclarations: MutableMap<Name, FunctionInstruction>): Processor {
    override fun call(funName: Name, args: List<Int>): Int {
        val func = funDeclarations.get(funName)
        if (func == null) {
            throw RuntimeInvalidCodeError("trying to invoke non-existent function '${funName}'")
        }
        return func.invoke(this, args)
    }

    /**
     * Replace a function by another one.
     *
     * TODO: in reality this probably involves updating a lot of references to be able to invoke directly
     * TODO: I may still implement that, but this 'jump table' would also work, at a performance penalty
     */
    override fun replace(name: Name, funAssembly: FunctionInstruction) {
        funDeclarations.replace(name, funAssembly)
    }
}


