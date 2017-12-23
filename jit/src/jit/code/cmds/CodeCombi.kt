package jit.code.cmds

import jit.code.Code
import jit.code.Statement

/**
 * Some statements followed by an expression.
 */
class CodeCombi<RT>(vararg statements: Statement, code: Code<RT>): Code<RT> {
}

