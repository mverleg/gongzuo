package rotate

import java.util.*

class Map(val width: Int = 200, val height: Int = 150) {

    val data: MutableList<MutableList<MapTile>>
    private val random: Random = Random(4242_4242)

    init {
        data = mutableListOf()
        for (x in 0 until width) {
            val row: MutableList<MapTile> = mutableListOf()
            for (y in 0 until height) {
                val nr = random.nextInt(165)
                if (nr > 160) {
                    row.add(MapTile.HOUSE)
                } else if (nr > 145) {
                    row.add(MapTile.TREE)
                } else if (nr > 100) {
                    row.add(MapTile.PERSON)
                } else {
                    row.add(MapTile.EMPTY)
                }
            }
            data.add(row)
        }
    }

    fun get(x: Int, y: Int): MapTile {
        check(x in 0 until width)
        check(y in 0 until height)
        return data[x][y]
    }
}


