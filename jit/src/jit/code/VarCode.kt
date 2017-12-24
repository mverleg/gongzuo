package jit.code

import jit.common.Name

// Only integer variables for now
class VarCode(val name: Name) {

    override fun toString(): String {
        return name.toString()
    }
}
