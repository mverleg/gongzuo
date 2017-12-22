package visit_tree

fun main(args: Array<String>) {
    val tree = Split(
        Split(
            Leaf(),
            Leaf()
        ),
        Split(
            Leaf(),
            Split(
                Leaf(),
                Leaf()
            )
        )
    );

}
