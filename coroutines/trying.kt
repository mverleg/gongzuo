
// https://kotlinlang.org/docs/reference/coroutines.html

fun main(args: Array<String>) {
    async {
        stopMe()
    }
}

suspend fun stopMe(x: Int?): String {
    if (x == null) {
        return ""
    }
    return x.toString()
}
