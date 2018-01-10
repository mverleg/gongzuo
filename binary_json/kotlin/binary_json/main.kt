package binary_json

import javax.naming.OperationNotSupportedException

fun main(args: Array<String>) {
    val json: JSON = JSONObject(mapOf(
            JSONString("age") to JSONInt(29),
            JSONString("name") to JSONString("Mark"),
            JSONString("is_cool") to JSONBoolean(true),
            JSONString("pets") to JSONList(listOf())
    ))
    println(dump(json))
}


fun dump(json: JSON): CharSequence {
    // todo: no cyclic protection
    // todo: no string builder optimizations
    return when (json) {
        is JSONInt -> json.value.toString()
        is JSONFloat -> json.value.toString()
        is JSONBoolean -> if (json.value) "true" else "false"
        is JSONString -> "\"" + json.value + "\""
        is JSONList -> "[" + json.value.map{ dump(it) }.joinToString { ", " } + "]"
        is JSONObject -> "{" + json.value.entries.map{ "\"${it.key.value}\": ${dump(it.value)}" }.joinToString(", ") + "}"
        is JSON_NDArray -> throw OperationNotSupportedException()
        is JSONNull -> "null"
    }
}
