package jit.code

class PackageCode(val funCodes: List<FunDefCode>): Iterable<FunDefCode> {

    override fun iterator(): Iterator<FunDefCode> {
        return funCodes.iterator();
    }
}
