package jit.ast

class Package(val funs: List<FunDef>): Iterable<FunDef> {

    override fun iterator(): Iterator<FunDef> {
        return funs.iterator();
    }
}
