package jit.code

import jit.common.Code
import jit.common.Compiler
import jit.common.Instruction
import jit.common.Statement

class DeclarationCode(variable: VarCode, value: Code<Int>): Statement<Int> {

    val assignment = AssignmentCode(variable, value)

    override fun toCompiler(compiler: Compiler): Instruction<Int> {
        return compiler.compile(this)
    }

    override fun toText(): CharSequence {
        return StringBuilder("int ").append(assignment.toText())
    }
}

