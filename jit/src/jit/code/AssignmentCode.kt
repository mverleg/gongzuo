package jit.code

import jit.common.Code
import jit.common.Compiler
import jit.common.Instruction
import jit.common.InstructionList
import jit.common.Statement

open class AssignmentCode(val variable: VarCode, val value: Code<Int>): Statement<Int> {
    override fun toCompiler(compiler: Compiler): Instruction<Int> {
        return compiler.compile(this)
    }

    override fun toText(): CharSequence {
        return StringBuilder(variable.toText()).append(" = ").append(value.toText()).append(";\n\t")
    }
}

