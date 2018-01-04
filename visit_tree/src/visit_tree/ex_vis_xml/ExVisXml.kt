package visit_tree.ex_vis_xml

import visit_tree.data.ExTreeVisitor
import visit_tree.data.Leaf
import visit_tree.data.Split

class ExVisXml: ExTreeVisitor<CharSequence> {
    override fun visit(leaf: Leaf): CharSequence {
        return "<leaf />"
    }

    override fun visit(split: Split): CharSequence {
        return "<split>" + split.left.acceptSimple(this) + split.right.acceptSimple(this) + "</split>"
    }
}
