package jit.instructions

import jit.code.VariableMention
import jit.common.Name
import jit.common.Type

/**
 * Represents a declared variable, with a name and type. Does not track the value.
 */
class Variable(val name: Name, val type: Type) {
    constructor(mention: VariableMention, type: Type): this(mention.name, type)
    constructor(nameStr: String, type: Type): this(Name(nameStr), type)

}

