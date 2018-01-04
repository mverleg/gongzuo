package jit.instructions

import jit.common.BinaryArithmOperation
import jit.common.Inter

class ArithmeticInter(val operation: BinaryArithmOperation, val leftInter: Inter<Int>, val rightInter: Inter<Int>): Inter<Int> {

    override fun toText(): String {
        when (operation) {
            BinaryArithmOperation.ADD -> return "add i32" + leftInter.toText() + ", " + rightInter.toText()
            BinaryArithmOperation.SUB -> return "sub i32" + leftInter.toText() + ", " + rightInter.toText()
            BinaryArithmOperation.MUL -> return "mul i32" + leftInter.toText() + ", " + rightInter.toText()
            BinaryArithmOperation.DIV -> return "div i32" + leftInter.toText() + ", " + rightInter.toText()
            BinaryArithmOperation.MOD -> return "mod i32" + leftInter.toText() + ", " + rightInter.toText()
        }
    }
}

