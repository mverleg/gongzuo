package jit.instructions

import jit.common.BinaryArithmOperation
import jit.common.Instruction
import jit.hardware.Processor

class ArithmeticInstruction(val operation: BinaryArithmOperation, val leftInstruction: Instruction<Int>, val rightInstruction: Instruction<Int>): Instruction<Int> {

    override fun run(processor: Processor): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun toText(): String {
        when (operation) {
            BinaryArithmOperation.ADD -> return "add i32" + leftInstruction.toText() + ", " + rightInstruction.toText()
            BinaryArithmOperation.SUB -> return "sub i32" + leftInstruction.toText() + ", " + rightInstruction.toText()
            BinaryArithmOperation.MUL -> return "mul i32" + leftInstruction.toText() + ", " + rightInstruction.toText()
            BinaryArithmOperation.DIV -> return "div i32" + leftInstruction.toText() + ", " + rightInstruction.toText()
            BinaryArithmOperation.MOD -> return "mod i32" + leftInstruction.toText() + ", " + rightInstruction.toText()
        }
    }
}

