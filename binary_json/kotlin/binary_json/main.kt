package binary_json

import java.io.StringReader

fun main(args: Array<String>) {
    val json: JSON = JSONObject(mapOf(
            JSONString("age") to JSONInt(29),
            JSONString("name") to JSONString("Mark"),
            JSONString("is_cool") to JSONTrue,
            JSONString("pets") to JSONList(listOf(JSONInt(1), JSONInt(1),
                    JSONInt(2), JSONInt(3), JSONInt(5), JSONInt(8)))
    ))
    println(PeekReader(StringReader(dump(json).toString())))
}


