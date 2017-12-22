package visit_tree

interface TreeElem {
    public <T> accept(Visitor<T> visitor) {
        T val = visitor.visitSuperCls(this);
        results.add(val);
        for (SubCls child : children) {
            val = child.accept(visitor);
            results.add(val);
        }
        return results;
    }
}