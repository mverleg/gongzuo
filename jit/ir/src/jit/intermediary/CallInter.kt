package jit.intermediary

import jit.utils.Name

class CallInter(val name: Name, val args: List<Inter<Int>>): Inter<Int> {

    override fun toText(): CharSequence {
        val paramNames = args.map { "i32 " + it.toText() }.joinToString(", ")
        return StringBuilder(name.toString()).append("(").append(paramNames).append(") ")
    }
}


