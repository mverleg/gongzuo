package jit.utils

val NAMELIST: List<String> = listOf(
        "red",
        "orange",
        "yellow",
        "green",
        "blue",
        "indigo",
        "violet",
        "mouse",
        "cow",
        "tiger",
        "rabbit",
        "dragon",
        "snake",
        "horse",
        "goat",
        "monkey",
        "chicken",
        "dog",
        "pig",
        "bear",
        "housecat",
        "wasp",
        "salmon",
        "scorpion",
        "meerkat",
        "honeybadger",
        "shrimp",
        "roach",
        "swan",
        "rhino",
        "fox",
        "seal",
        "gorilla",
        "elephant",
        "ladybug",
        "octopus",
        "frog",
        "owl",
        "spider",
        "shark",
        "squirrel",
        "turtle",
        "butterfly",
        "dolphin",
        "parrot",
        "ant",
        "lion",
        "grasshopper",
        "camel",
        "hedgehog",
        "bluewhale",
        "crocodile",
        "mole",
        "giraffe",
        "penguin",
        "beaver",
        "hippo",
        "worm"
)

val LETTERS = listOf('A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', /* no I */ 'J', 'K',
        'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
        /* no 0 */ '1', '2', '3', '4', '5', '6', '7', '8', '9')

fun genName(index: Int): String {
    if (index < NAMELIST.size) {
        return NAMELIST[index]
    } else {
        return "tmp_" + genSequenceStr(index - NAMELIST.size)
    }
}

fun genSequenceStr(index: Int): String {
    val name = StringBuilder()
    var mult: Long = 1
    while (mult <= index) {
        val letterindx = (index / mult).toInt() % LETTERS.size
        name.append(LETTERS[letterindx])
        mult *= LETTERS.size
    }
    if (name.length == 0) {
        return "A"
    }
    return name.reversed().toString()
}

