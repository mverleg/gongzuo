package decorator.whyuse.inkotlin

import java.util.LinkedList

import decorator.whyuse.shared.MyReader

class MyBufferedReaderKotlin(private val reader: MyReader): MyReader by reader {

    private val lines = LinkedList<String>()

    override fun readLine(): String {
        if (lines.size == 0) {
            for (k in 0..99) {
                lines.add(reader.readLine())
            }
        }
        return lines.remove()
    }
}
