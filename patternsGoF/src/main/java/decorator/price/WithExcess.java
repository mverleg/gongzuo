package decorator.price;

import decorator.price.utils.Currency;
import decorator.price.utils.Euro;

final public class WithExcess<T extends Currency<T>> implements Priced<T> {

    final private Priced priced;
    final private T excess;

    public WithExcess(Priced<T> priced, T excess) {
        this.priced = priced;
        this.excess = excess;
    }

    @Override
    public T amount() {
        return (T) priced.amount().sub(excess).max(new Euro(0));
    }

    @Override
    public Priced inner() {
        return priced;
    }

    @Override
    public String asText() {
        return inner().asText() + ", minus " + excess + " excess";
    }
}
