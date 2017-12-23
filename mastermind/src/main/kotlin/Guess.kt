package mastermind

class Guess: Combination {
    val correctPosition: Int
    val otherPosition: Int

    constructor(config: Config, guess: List<Int>, master: Combination): super(config, guess) {
        val target = master.pins.toMutableList()
        var corPos = 0
        var otherPos = 0
        for (k in 0 .. guess.size) {
            if (guess[k] == target[k]) {
                target[k] = -1
                corPos++
            }
            for (j in 0 .. target.size) {
                if (k != j && guess[k] == target[j]) {
                    target[k] = -1
                    otherPos++
                }
            }
        }
        correctPosition = corPos
        otherPosition = otherPos
    }
}


