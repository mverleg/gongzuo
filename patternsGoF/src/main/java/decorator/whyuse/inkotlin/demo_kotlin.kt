package decorator.whyuse.inkotlin

import decorator.shared.MyFileReader
import decorator.shared.MySequenceReader
import decorator.shared.Utils
import java.io.File

fun main(args: Array<String>) {
    val rdr = MyBufferedReaderKotlin(MyFileReader(File("shared/demo.txt")))
    for (k in 0..249) {
        println(k.toString() + ": " + rdr.readLine())
    }
    System.out.println("Those were the first 250 lines of " + rdr.getSourceName())
    // It also works for other readers!
    val seqrdr = MyBufferedReaderKotlin(MySequenceReader())
    Utils.functionForMyReaders(rdr);
}

