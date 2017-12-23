package jit.code

import jit.common.Code
import jit.common.Compiler
import jit.common.InstructionList

class IfCode(public val condition: Code<Boolean>, public val thenCode: Code<Int>, public val elseCode: Code<Int>): Code<Int> {
//
//    var prelimIf: Exec<Int>? = null;
//    var conditionExec: Exec<Boolean>? = null;
//    var thenExec: Exec<Int>? = null;
//    var elseExec: Exec<Int>? = null;
//
//    override fun precomp(): PrelimExec<Int> {
//        conditionExec = condition.precomp()
//        thenExec = thenCode.precomp()
//        elseExec = elseCode.precomp()
//        prelimIf = PrelimIf(conditionExec, thenExec, elseExec)
//        return prelimIf
//    }
//
//    override fun optcomp(prelimExec: PrelimExec<Int>): OptExec<Int> {
//        return OptIf(condition.optcomp(conditionExec), thenCode.optcomp(thenExec), elseCode.optcomp(elseExec), prelimIf.getTrueRatio())
//    }

    override fun toCompiler(compiler: Compiler): InstructionList {
        return compiler.compile(this)
    }
}


