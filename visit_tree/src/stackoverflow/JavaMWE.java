package stackoverflow;

interface JTreeElem {
    <T> T accept(JTreeVisitor<T> visitor);
}

class JLeaf implements JTreeElem {
    public <T> T accept(JTreeVisitor<T> visitor) {
        return visitor.visit(this);
    }
}

class JSplit implements JTreeElem {
    private JTreeElem left;
    private JTreeElem right;
    JSplit(JTreeElem left, JTreeElem right) {
        this.left = left;
        this.right = right;
    }
    public <T> T accept(JTreeVisitor<T> visitor) {
        return visitor.combine(
            visitor.visit(this),
            left.accept(visitor),
            right.accept(visitor));
    }
}

interface JTreeVisitor<T> {
    T visit(JLeaf tree);
    T visit(JSplit tree);
    T combine(T... inputs);
}

class JPrinter implements JTreeVisitor<CharSequence> {
    public CharSequence combine(CharSequence... inputs) {
        StringBuilder text = new StringBuilder();
        for (CharSequence input : inputs) {
            text.append(input);
        }
        return text;
    }
    public CharSequence visit(JLeaf tree) { return "leaf"; }
    public CharSequence visit(JSplit tree) { return "split"; }
}

public class JavaMWE {
    public static void main(String[] args) {
        JTreeElem tree = new JSplit(new JLeaf(), new JLeaf());
        JPrinter printer = new JPrinter();
        System.out.println(tree.accept(printer));
    }
}
