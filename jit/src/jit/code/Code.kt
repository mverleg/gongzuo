package jit.code

interface Code<RT> {
    fun accept(visitor: SourceVisitor)
}

interface Statement {

}
