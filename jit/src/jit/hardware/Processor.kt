package jit.hardware

import jit.code.FunDefCode
import jit.common.Name
import jit.common.Exec
import jit.instructions.FunctionInstruction

/**
 * The processor executes the AST instructions. It may not do any special processing,
 * since the physical processor also cannot do that; the {@link JIT} must do this.
 */
class Processor(val blocks: MutableMap<Name, FunctionInstruction>) {
    fun call(funName: Name): Int {
        throw NotImplementedError()
        TODO("not implemented")
    }
}


