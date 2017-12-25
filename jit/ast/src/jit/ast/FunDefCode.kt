package jit.ast

import jit.common.Code
import jit.common.HasScope
import jit.common.Name

class FunDefCode(val body: Code<Int>, val name: Name, val parameters: List<VariableMention>): HasScope {

    constructor(body: Code<Int>, name: Name): this(body, name, listOf())

    fun toText(): CharSequence {
        val paramNames = parameters.map { "int " + it.toString() }.joinToString(", ")
        val text = StringBuilder("\nfunction ").append(name.toString())
                .append("(").append(paramNames).append("):\n\t")
        text.append(body.toText())
        return text
    }
}

