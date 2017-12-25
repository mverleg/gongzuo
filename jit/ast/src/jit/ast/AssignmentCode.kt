package jit.ast

import jit.common.Code
import jit.common.Compiler
import jit.common.Inter
import jit.common.Statement

open class AssignmentCode(val variable: VariableMention, val value: Code<Int>): Statement<Int> {
    override fun toCompiler(compiler: Compiler): Inter<Int> {
        return compiler.compile(this)
    }

    override fun toText(): CharSequence {
        val text = StringBuilder(variable.toString()).append(" = ").append(value.toText())
        if (value !is AssignmentCode && value !is DeclarationCode) {
            text.append(";\n\t")
        }
        return text
    }
}

