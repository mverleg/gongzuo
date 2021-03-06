package jit.ast

/**
 * Some statements followed by an expression (which produces the return value).
 */
class ScopedBlock(vararg statements: Statement<Int>, last: Code<Int>):
        Code<Int>, Iterable<Code<Int>>, HasScope {

    val combi: CodeCombi = CodeCombi(*statements, last=last)

    override fun toCompiler(compiler: Compiler): Inter<Int> {
        return compiler.compile(this)
    }

    override fun toText(): CharSequence {
        return combi.toText()
    }

    override fun iterator(): Iterator<Code<Int>> {
        return combi.iterator()
    }
}

