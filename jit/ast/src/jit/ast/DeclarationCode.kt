package jit.ast

import jit.common.Code
import jit.common.Compiler
import jit.common.Inter
import jit.common.Statement

class DeclarationCode(variable: VariableMention, value: Code<Int>): Statement<Int> {

    val assignment = AssignmentCode(variable, value)

    override fun toCompiler(compiler: Compiler): Inter<Int> {
        return compiler.compile(this)
    }

    override fun toText(): CharSequence {
        return StringBuilder("int ").append(assignment.toText())
    }
}

