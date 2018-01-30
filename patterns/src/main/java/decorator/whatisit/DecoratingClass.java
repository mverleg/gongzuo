package src.main.java.decorator.whatisit;

public class DecoratingClass implements TheType {

    private final TheType decoratedInst;

    public DecoratingClass(TheType inner) {
        decoratedInst = inner;
    }

    @Override public void doTheOperation() {
        decoratedInst.doTheOperation();
        System.out.println("do something extra");
    }
}
