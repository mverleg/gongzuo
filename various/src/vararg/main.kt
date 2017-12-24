package vararg

fun try_vararg(vararg several: Int, oneMore: Int) {
    print("hello world")
}

fun main(args: Array<String>) {
    try_vararg(1, 2, oneMore=3);
}
