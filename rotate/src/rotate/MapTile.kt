package rotate

enum class MapTile(val repr: Char) {
    EMPTY(' '),
    HOUSE('H'),
    PERSON(','),
    TREE('T');

    fun getChar(): String {
        return repr.toString();
    }
}

