package jit.instructions

import jit.common.BinaryNumberLogicOperation
import jit.common.Inter

class LogicInter(val operation: BinaryNumberLogicOperation, val leftInter: Inter<Int>, val rightInter: Inter<Int>): Inter<Boolean> {
    override fun run(processor: HighProcessor): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

   override fun toText(): String {
       when (operation) {
           BinaryNumberLogicOperation.EQ  -> return "icmp eq i32 "  + leftInter.toText() + ", " + rightInter.toText()
           BinaryNumberLogicOperation.NEQ -> return "icmp ne i32 "  + leftInter.toText() + ", " + rightInter.toText()
           BinaryNumberLogicOperation.LT  -> return "icmp sgt i32 " + leftInter.toText() + ", " + rightInter.toText()
           BinaryNumberLogicOperation.GT  -> return "icmp slt i32 " + leftInter.toText() + ", " + rightInter.toText()
           BinaryNumberLogicOperation.LTE -> return "icmp sge i32 " + leftInter.toText() + ", " + rightInter.toText()
           BinaryNumberLogicOperation.GTE -> return "icmp sle i32 " + leftInter.toText() + ", " + rightInter.toText()
       }
   }
}
