package binary_json

public sealed class JSON

public class JSONInt(val value: Int): JSON()
public class JSONFloat(val value: Float): JSON()
public class JSONBoolean(val value: Boolean): JSON()
public class JSONString(value: String): JSON() {
    val value = value.intern()
}
public class JSONList(val value: List<JSON>): JSON()
public class JSONObject(val value: Map<JSONString, JSON>): JSON()
public class JSON_NDArray(val value: Any): JSON()  // TODO
public class JSONNull(): JSON()


