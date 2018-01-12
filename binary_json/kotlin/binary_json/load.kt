package binary_json

public fun load(src: PeekReader): JSON {
    return read_list(src)
//    val chr = read_first_nonwhite(src)
//    when (chr) {
//        '['.toInt()
//    }
//    if (chr == '['.toInt()) {
//        return read_list(src)
//    }
}

private fun read_first_nonwhite(src: PeekReader): Int {
    var chr = ' '.toInt()
    while (chr == ' '.toInt() || chr == '\t'.toInt() || chr == '\n'.toInt()) {
        chr = src.read()
    }
    return chr
}

private fun peek_first_nonwhite(src: PeekReader): Int {
    var chr: Int? = null
    while (true) {
        chr = src.peek()
        if (chr == ' '.toInt() || chr == '\t'.toInt() || chr == '\n'.toInt()) {
            src.read()
        } else {
            break
        }
    }
    return chr!!
}

private fun read_list(src: PeekReader): JSON {
    var chr = read_first_nonwhite(src)
    if (chr != '['.toInt()) {
        return read_object(src)
    }
    val items: MutableList<JSON> = mutableListOf()
    while (true) {
        chr = peek_first_nonwhite(src)
        when (chr) {
            ','.toInt() -> src.read()
            ']'.toInt() -> {
                src.read()
                return JSONList(items)
            }
            else -> items.add(read_list(src))
        }
    }
}

private fun read_object(src: PeekReader): JSON {
    TODO()
}
