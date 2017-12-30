package visit_tree.ex_xml

import visit_tree.data.Leaf
import visit_tree.data.Split
import visit_tree.data.TreeElem

interface ExternalVisitor {
}

/* It's more a collection of functions than a class now... */
class XmlEx: ExternalVisitor {
    fun transform(split: Split): CharSequence {
        val l = transformAll(split.left)
        val r = transformAll(split.right)
        return "<split>${l}${r}</split>"
    }

    fun transform(leaf: Leaf): CharSequence {
        return "<leaf></leaf>"
    }

    fun transformAll(node: TreeElem): CharSequence {
        return when(node) {
            is Leaf -> transform(node)
            is Split -> transform(node)
            else -> throw NotImplementedError("unknown TreeElem type found")
        }
    }
}