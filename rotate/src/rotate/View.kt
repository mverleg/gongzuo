package rotate

class View(val map: Map, val width: Int = 8, val height: Int = 5) {

    var horizontalMiddle: Int = map.width / 2
    var verticalMiddle: Int = map.width / 2
    var quarterRotations: Int = 0

    init {
        val maxMapDim = if (map.width > map.height) map.width else map.height
        check(width in 1..maxMapDim)
        check(height in 1..maxMapDim)
    }

    /**
     * Transform from view coordinates to map coordinates.
     */
    private fun transform(viewX: Int, viewY: Int): Pair<Int, Int> {
        val mapX = viewX + horizontalMiddle - (width / 2).toInt()
        val mapY = viewY + verticalMiddle - (height / 2).toInt()
        return Pair(mapX, mapY)
    }

    /**
     * Render the part of the map that is currently being viewed.
     * (This uses text; shift/rotation would work similarly for graphical displays).
     */
    fun render(): CharSequence {
        val textRepr = StringBuilder()
        textRepr.append('+')
        for (viewX in 0 until width) {
            textRepr.append('-')
        }
        textRepr.append('+').append('\n')
        for (viewY in 0 until height) {
            textRepr.append('|')
            for (viewX in 0 until width) {
                val mapCoord = transform(viewX, viewY)
                val tile = map.get(mapCoord.first, mapCoord.second)
                textRepr.append(tile.getChar())
            }
            textRepr.append('|').append('\n')
        }
        textRepr.append('+')
            for (viewX in 0 until width) {
                textRepr.append('-')
            }
        textRepr.append('+').append('\n')
        return textRepr
    }

    /**
     * Shift the view up/down/left/right.
     */
    fun shift(horizontal: Int, vertical: Int) {
        horizontalMiddle -= horizontal
        verticalMiddle -= vertical
        // Could just use min/max functions; I find this more readable
        if (horizontalMiddle < (width / 2)) {
            horizontalMiddle = (width / 2)
        }
        if (horizontalMiddle > map.width - (width / 2)) {
            horizontalMiddle = map.width - (width / 2)
        }
        if (verticalMiddle < (height / 2)) {
            verticalMiddle = (height / 2)
        }
        if (verticalMiddle > map.height - (height / 2)) {
            verticalMiddle = map.height - (height / 2)
        }
    }

    /**
     * Rotate the view by multiples of 90 degrees clockwise or counter-clockwise.
     */
    fun rotate(quartersClockwise: Int) {
        quarterRotations = (quarterRotations + quartersClockwise) % 4
    }
}

