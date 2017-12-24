package extension.one

import extension.two.OpenCls
import extension.two.OpenIntf

fun OpenCls.beta() {

}

/** Cannot add abstract methods apparently. */
//fun OpenIntf.delta()

fun main(args: Array<String>) {
    val inst = OpenCls()
    inst.alpha()
    inst.beta()
    inst.gamma()
//    inst.delta()
}


