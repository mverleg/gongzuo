package jit.common

class Name(value: String) {
    val value: String

    init {
        if (value in setOf("function", "return", "if", "then", "else", "endif")) {
            throw IllegalArgumentException("Reserved name: {}" + value)
        }
        if (Regex("^[a-zA-Z_][a-zA-Z0-9_]*$").containsMatchIn(value)) {
            this.value = value
        } else {
            throw IllegalArgumentException("Not a valid name: ${value}")
        }
    }

    override fun toString(): String {
        return value
    }

    override fun hashCode(): Int {
        return value.hashCode() * 3
    }

    override fun equals(other: Any?): Boolean {
        if (other !is Name) {
            return false
        }
        return value.equals(other.value)
    }
}
