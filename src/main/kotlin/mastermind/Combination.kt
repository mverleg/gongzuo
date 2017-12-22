package mastermind

abstract class Combination {
    val config: Config
    val pins: List<Int>

    constructor(config: Config, pins: List<Int>) {
        assert(pins.size == config.spots)
        assert(pins.map{ 0 <= it && it < config.colors }.all{ it })
        this.config = config
        this.pins = pins
    }
}


