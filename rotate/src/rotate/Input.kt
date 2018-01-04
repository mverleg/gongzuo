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

    /**
     * Read command letters from a line of input.
     */
    fun awaitCommand(): List<Operation> {
        val ops: MutableList<Operation> = mutableListOf()
        val command = BufferedReader(InputStreamReader(System.`in`))
        var shownInfo = false;
        for (chr in command.readLine().toCharArray()) {
            val op = when (chr) {
                'w' -> Operation(OperationType.VER_SHIFT, -1)
                's' -> Operation(OperationType.VER_SHIFT, +1)
                'a' -> Operation(OperationType.HOR_SHIFT, -1)
                'd' -> Operation(OperationType.HOR_SHIFT, +1)
                'l' -> Operation(OperationType.ROTATE, -1)
                'r' -> Operation(OperationType.ROTATE, +1)
                else -> {
                    if (!shownInfo) {
                        shownInfo = true
                        println("enter one or more of w/a/s/d/l/r")
                    }
                    null
                }
            }
            if (op != null) {
                ops.add(op)
            }
        }
        return ops
    }
}