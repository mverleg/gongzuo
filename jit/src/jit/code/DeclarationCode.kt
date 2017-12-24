package jit.code

import jit.common.Code

class DeclarationCode(variable: VarCode, value: Code<Int>): AssignmentCode(variable, value) {
    override fun toText(): CharSequence {
        return StringBuilder("int ").append(super.toText())
    }
}

