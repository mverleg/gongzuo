package jit.instructions

import jit.common.BinaryNumberLogicOperation
import jit.common.Instruction
import jit.hardware.Processor

class LogicInstruction(val operation: BinaryNumberLogicOperation, val leftInstruction: Instruction<Int>, val rightInstruction: Instruction<Int>): Instruction<Boolean> {
    override fun run(processor: Processor): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

   override fun toText(): String {
       when (operation) {
           BinaryNumberLogicOperation.EQ  -> return "icmp eq i32 "  + leftInstruction.toText() + ", " + rightInstruction.toText()
           BinaryNumberLogicOperation.NEQ -> return "icmp ne i32 "  + leftInstruction.toText() + ", " + rightInstruction.toText()
           BinaryNumberLogicOperation.LT  -> return "icmp sgt i32 " + leftInstruction.toText() + ", " + rightInstruction.toText()
           BinaryNumberLogicOperation.GT  -> return "icmp slt i32 " + leftInstruction.toText() + ", " + rightInstruction.toText()
           BinaryNumberLogicOperation.LTE -> return "icmp sge i32 " + leftInstruction.toText() + ", " + rightInstruction.toText()
           BinaryNumberLogicOperation.GTE -> return "icmp sle i32 " + leftInstruction.toText() + ", " + rightInstruction.toText()
       }
   }
}
