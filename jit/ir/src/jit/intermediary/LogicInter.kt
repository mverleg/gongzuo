package jit.intermediary

import jit.utils.BinaryNumberLogicOperation

class LogicInter(val operation: BinaryNumberLogicOperation, val leftInter: Inter<Int>, val rightInter: Inter<Int>): Inter<Boolean> {

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
