package jit.code

import jit.common.Code
import jit.common.Statement

class AssignmentCode(val variable: VarCode, val value: Code<Int>): Code<Int>, Statement {
}

