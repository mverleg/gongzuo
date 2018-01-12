package binary_json

import java.io.Reader

class PeekReader(val reader: Reader) {

    var current: Int? = null

    public fun read(): Int {
        // Not thread-safe
        if (current == null) {
            return reader.read()
        } else {
            val res = current!!
            current = null
            return res
        }
    }

    public fun peek(): Int {
        // Not thread-safe
        if (current == null) {
            current = reader.read()
        }
        return current!!
    }
}