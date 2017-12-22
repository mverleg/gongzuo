package mastermind

class Game(val conf: Config, val master: Combination) {
    val guesses: MutableList<Combination> = mutableListOf<Combination>();

    fun guess(pins: List<Int>): Guess {
        val guess = Guess(conf, pins, master)
        guesses.add(guess)
        return guess
    }
}


