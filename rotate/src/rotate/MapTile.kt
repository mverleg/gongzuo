package rotate

/**
 * This represents a thing on the map. There are only a few, for demo purposes.
 */
enum class MapTile(val repr: Char) {
    EMPTY(' '),
    HOUSE('$'),
    PERSON(','),
    TREE('x');

    fun getChar(): String {
        return repr.toString();
    }
}

