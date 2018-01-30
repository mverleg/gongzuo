package test

import binary_json.JSON
import binary_json.JSONInt
import binary_json.JSONList
import binary_json.JSONObject
import binary_json.JSONString
import binary_json.JSONTrue
import binary_json.PeekReader
import binary_json.dump
import binary_json.load
import java.io.StringReader

fun test_parse_json() {
    val json: JSON = JSONObject(mapOf(
            JSONString("age") to JSONInt(29),
            JSONString("name") to JSONString("Mark"),
            JSONString("is_cool") to JSONTrue,
            JSONString("pets") to JSONList(listOf(JSONInt(1), JSONInt(1),
                    JSONInt(2), JSONInt(3), JSONInt(5), JSONInt(8)))
    ))
    val txt = dump(json)
    val back = load(PeekReader(StringReader(txt.toString())))
}

