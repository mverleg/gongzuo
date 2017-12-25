package jit.ast

class PackageCode(val funCodes: List<FunDefCode>): Iterable<FunDefCode> {

    override fun iterator(): Iterator<FunDefCode> {
        return funCodes.iterator();
    }

    fun toText(): CharSequence {
        val text = StringBuilder()
        for (func in funCodes) {
            text.append(func.toText())
        }
        text.append("\n\n")
        return text
    }
}
