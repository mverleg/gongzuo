package decorator.price;

import decorator.price.utils.Currency;

final public class WithFranchise<T extends Currency<T>> implements Priced<T> {

    final private Priced priced;
    final private T franchise;

    public WithFranchise(Priced<T> priced, T franchise) {
        this.priced = priced;
        this.franchise = franchise;
    }

    @Override
    public T amount() {
        if (priced.amount().compareTo(franchise) >= 0) {
            return (T) priced.amount();
        } else {
            return (T) priced.amount().zero();
        }
    }

    @Override
    public Priced inner() {
        return priced;
    }

    @Override
    public String asText() {
        return inner().asText() + ", if above " + franchise + " franchise";
    }
}
