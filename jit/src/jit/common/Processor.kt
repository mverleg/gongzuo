package jit.common

import jit.instructions.FunctionInter

interface Processor {
    val funDeclarations: MutableMap<Name, FunctionInter>
    fun call(funName: Name, args: List<Int>): Int
    /**
     * Replace a function by another one.
     *
     * TODO: in reality this probably involves updating a lot of references to be able to invoke directly
     * TODO: I may still implement that, but this 'jump table' would also work, at a performance penalty
     */
    fun replace(name: Name, funAssembly: FunctionInter)
}