package jit.ast

class Name(value: String) {
    val value: String

    init {
        if (Regex("^[a-zA-Z_][a-zA-Z0-9_]*$").containsMatchIn(value)) {
            this.value = value
        } else {
            throw IllegalArgumentException("Not a valid name: {}" + value)
        }
    }
}
