package binary_json

import javax.naming.OperationNotSupportedException

public fun dump(json: JSON): CharSequence {
    // todo: no cyclic protection
    // todo: no string builder optimizations
    return when (json) {
        is JSONInt -> json.value.toString()
        is JSONFloat -> json.value.toString()
        is JSONString -> "\"" + json.value + "\""
        is JSONList -> "[" + json.value.map{ dump(it) }.joinToString { ", " } + "]"
        is JSONObject -> "{" + json.value.entries.map{ "\"${it.key.value}\": ${dump(it.value)}" }.joinToString(", ") + "}"
        is JSON_NDArray -> throw OperationNotSupportedException()
        is JSONTrue -> "true"
        is JSONFalse -> "false"
        is JSONNull -> "null"
    }
}

