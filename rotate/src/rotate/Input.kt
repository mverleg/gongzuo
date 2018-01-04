package rotate

import java.io.BufferedReader
import java.io.InputStreamReader

class Input {
    enum class OperationType {
        HOR_SHIFT,
        VER_SHIFT,
        ROTATE
    }
    // would be more elegant to have the 'amount' depend on the operation type,
    // but in this case they are all single small integers anyway
    class Operation(val type: OperationType, val amount: Int)

    fun awaitCommand(): Operation {
        val command = BufferedReader(InputStreamReader(System.`in`))
        return when (command.readLine()) {
            "w" -> Operation(OperationType.VER_SHIFT, -1)
            "s" -> Operation(OperationType.VER_SHIFT, +1)
            "a" -> Operation(OperationType.HOR_SHIFT, -1)
            "d" -> Operation(OperationType.HOR_SHIFT, +1)
            "l" -> Operation(OperationType.ROTATE, -1)
            "r" -> Operation(OperationType.ROTATE, +1)
            else -> {
                println("enter one of w/a/s/d/l/r")
                return awaitCommand()
            }
        }
    }
}