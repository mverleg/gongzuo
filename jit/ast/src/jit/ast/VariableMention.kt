package jit.ast

import jit.utils.Name

// Only integer variables for now
class VariableMention(val name: Name) {
    constructor(name: String): this(Name(name))

    override fun toString(): String {
        return name.toString()
    }
}
