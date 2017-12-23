package jit.code.cmds

class PackageCode(val funCodes: List<FunDefCode>): Iterable<FunDefCode> {

    override fun iterator(): Iterator<FunDefCode> {
        return funCodes.iterator();
    }
}
