package jit.hardware

import jit.common.Name
import jit.instructions.FunctionInstruction

/**
 * The processor executes the AST instructions. It may not do any special processing,
 * since the physical processor also cannot do that; the {@link JIT} must do this.
 */
class Processor(val funDeclarations: MutableMap<Name, FunctionInstruction>) {
    fun call(funName: Name): Int {
        TODO("not implemented")
    }

    /**
     * Replace a function by another one (probably a more compiled one).
     *
     * TODO: in reality this probably involves updating a lot of references to be able to call directly
     * TODO: I may still implement that, but this 'jump table' would also work, at a performance penalty
     */
    fun replace(name: Name, funAssembly: FunctionInstruction) {
        funDeclarations.replace(name, funAssembly)
    }
}


