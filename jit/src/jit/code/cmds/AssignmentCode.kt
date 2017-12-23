package jit.code.cmds

import jit.code.Code
import jit.code.Statement

class AssignmentCode(val variable: VarCode, val value: Code<Int>): Code<Int>, Statement {
}

