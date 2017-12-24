package jit.code

import jit.common.Code
import jit.common.Compiler
import jit.common.Instruction
import jit.common.Statement

/**
 * Some statements followed by an expression (which produces the return value).
 */
class CodeCombi(vararg statements: Statement<Int>, last: Code<Int>): Code<Int>, Iterable<Code<Int>> {

    var codes: List<Code<Int>>

    /**
     * If any of the statements or code are itself combinations ({@link CodeCombi}), flatten their content into this one.
     */
    init {
        val flattenedCodes: MutableList<Code<Int>> = mutableListOf()
        for (code in statements) {
//            if (code is CodeCombi) {
//                for (subcode in code) {
//                    flattenedCodes.add(subcode)
//                }
//            } else {
                flattenedCodes.add(code)
//            }
        }
        if (last is CodeCombi) {
            for (subcode in last) {
                flattenedCodes.add(subcode)
            }
        } else {
            flattenedCodes.add(last)
        }
        codes = flattenedCodes
    }

    override fun toCompiler(compiler: Compiler): Instruction<Int> {
        return compiler.compile(this)
    }

    override fun toText(): CharSequence {
        val text = StringBuilder()
        for (code in codes) {
            text.append(code.toText())
        }
        return text
    }

    override fun iterator(): Iterator<Code<Int>> {
        return codes.iterator()
    }
}

