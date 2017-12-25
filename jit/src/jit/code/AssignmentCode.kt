package jit.code

import jit.common.Code
import jit.common.Compiler
import jit.common.Instruction
import jit.common.Statement

open class AssignmentCode(val variable: VariableMention, val value: Code<Int>): Statement<Int> {
    override fun toCompiler(compiler: Compiler): Instruction<Int> {
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

