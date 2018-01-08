package rotate

/**
 * Width and height must be odd positive numbers.
 */
class View(val map: Map, val width: Int = 9, val height: Int = 5) {

    var horizontalMiddle: Int = map.width / 2
    var verticalMiddle: Int = map.height / 2
    var quarterRotations: Int = 0

    init {
        val maxMapDim = if (map.width > map.height) map.width else map.height
        check(width % 2 == 1, { "width must be odd" })
        check(width in 1..maxMapDim)
        check(height % 2 == 1, { "height must be odd" })
        check(height in 1..maxMapDim)
    }

    /**
     * Transform from view coordinates to map coordinates.
     */
    private fun transform(viewX: Int, viewY: Int): Pair<Int, Int> {
        val mapX: Int
        val mapY: Int
        val sign = if (quarterRotations <= 1) +1 else -1
        if (quarterRotations % 2 == 0) {
            // view's horizontal aligned with map horizontal
            mapX = sign * viewX + horizontalMiddle
            mapY = sign * viewY + verticalMiddle
        } else {
            mapX = sign * viewY + verticalMiddle
            mapY = sign * viewX + horizontalMiddle
        }
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
        for (viewY in -(height / 2)..(height / 2)) {
            textRepr.append('|')
            for (viewX in -(width / 2)..(width / 2)) {
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

    fun statusInfo(): CharSequence {
        return "${horizontalMiddle}/${getMapHorDim()} x ${verticalMiddle}/${getMapVerDim()} ; ${90*quarterRotations} deg"
    }

    /**
     * Shift the view up/down/left/right.
     */
    fun shift(horizontal: Int, vertical: Int) {
        horizontalMiddle += horizontal
        verticalMiddle += vertical
        stayInsideMap()
    }

    /**
     * Rotate the view by multiples of 90 degrees clockwise or counter-clockwise.
     */
    fun rotate(quartersClockwise: Int) {
        quarterRotations = (quarterRotations + quartersClockwise) % 4
        // Swap horizontal and vertical coordinates
        val tmp = horizontalMiddle
        horizontalMiddle = verticalMiddle
        verticalMiddle = tmp
        stayInsideMap()
    }

    private fun stayInsideMap() {
        // Could just use min/max functions; I find this more readable
        if (horizontalMiddle < (width / 2)) {
            horizontalMiddle = (width / 2)
        }
        if (horizontalMiddle >= getMapHorDim() - (width / 2)) {
            horizontalMiddle = getMapHorDim() - (width / 2) - 1
        }
        if (verticalMiddle < (height / 2)) {
            verticalMiddle = (height / 2)
        }
        if (verticalMiddle >= getMapVerDim() - (height / 2)) {
            verticalMiddle = getMapVerDim() - (height / 2) - 1
        }
    }

    /**
     * Get the dimension of the map that is aligned with the horizontal of the view
     * (this is the width or height, depending on rotation)
     */
    private fun getMapHorDim(): Int {
        return if (quarterRotations % 2 == 0) map.width else map.height
    }
    private fun getMapVerDim(): Int {
        return if (quarterRotations % 2 == 0) map.height else map.width
    }
}

