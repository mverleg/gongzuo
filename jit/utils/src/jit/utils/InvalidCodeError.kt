package jit.common

abstract class InvalidCodeError(msg: String): RuntimeException(msg) {
    /**
     * Indicates that the input code is invalid.
     */
}

class CompileInvalidCodeError(msg: String): InvalidCodeError(msg) {}

class RuntimeInvalidCodeError(msg: String): InvalidCodeError(msg) {}


