package extension.two

interface OpenIntf {
    fun gamma()
}

class OpenCls: OpenIntf {
    fun alpha() {
        println("alpha")
    }

    override fun gamma() {
        println("gamma")
    }
}