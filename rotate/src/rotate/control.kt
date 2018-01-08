package rotate

public fun main(args: Array<String>) {
    val map = Map(width=20, height=12)
    val view = View(map)
    val inpt = Input()
    println("type one or more letters and press enter to control the 'camera'")
    while (true) {
        print(view.render())
        println(view.statusInfo())  // for debugging and demo purposes
        for (cmd in inpt.awaitCommand()) {
            when (cmd.type) {
                Input.OperationType.HOR_SHIFT -> view.shift(cmd.amount, 0)
                Input.OperationType.VER_SHIFT -> view.shift(0, cmd.amount)
                Input.OperationType.ROTATE -> view.rotate(cmd.amount)
            }
        }
    }
}


