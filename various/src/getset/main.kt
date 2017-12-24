package getset

class GetSet {
    private var entries: List<Int> = listOf(1, 2, 3)
    private var oldEntries: List<Int> = entries
    public var isEmpty: Boolean
        get() = entries.isEmpty()
        set(value) {
            if (value) {
                entries = listOf()
            } else {
                entries = oldEntries
            }
        }
}

fun main(args: Array<String>) {
    GetSet().isEmpty = true
}

