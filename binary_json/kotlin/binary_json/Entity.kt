package binary_json

public sealed class JSON

public data class JSONInt(val value: Int): JSON()
public data class JSONFloat(val value: Float): JSON()
public data class JSONString(var value: String): JSON() {
    init { value = value.intern() }
}
public data class JSONList(val value: List<JSON>): JSON()
public data class JSONObject(val value: Map<JSONString, JSON>): JSON()
public data class JSON_NDArray(val value: Any): JSON()  // TODO
public object JSONTrue: JSON()
public object JSONFalse: JSON()
public object JSONNull: JSON()

