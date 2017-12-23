package jit.code

import jit.common.Code
import jit.common.Statement

/**
 * Some statements followed by an expression.
 */
class CodeCombi<RT>(vararg statements: Statement, code: Code<RT>): Code<RT> {
}

