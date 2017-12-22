package mastermind

public fun main(args: Array<String>) {
    val conf = Config()
    val master = MasterCombi(conf, listOf(0, 1, 2, 3))
    val game = Game(conf, master)

    val guess = game.guess(listOf(1, 2, 3, 3))
}


