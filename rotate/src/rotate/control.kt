package rotate

public fun main(args: Array<String>) {
    val map = Map()
    val view = View(map)
    val inpt = Input()
    println("type a letter and press enter to control the 'camera'")
    while (true) {
        println(view.render())
        val cmd = inpt.awaitCommand()
        when (cmd.type) {
            Input.OperationType.HOR_SHIFT -> view.shift(cmd.amount, 0)
            Input.OperationType.VER_SHIFT -> view.shift(0, cmd.amount)
            Input.OperationType.ROTATE -> view.rotate(cmd.amount)
        }
    }
}


