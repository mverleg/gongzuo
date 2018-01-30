package src.main.java.decorator.price;

import decorator.price.utils.Currency;
import decorator.price.utils.Recipient;

final public class Payment<T extends Currency<T>> implements Priced<T> {

    final private Recipient to;
    final private T amount;

    public Payment(Recipient to, T amt) {
        this.to = to;
        this.amount = amt;
    }

    @Override
    public T amount() {
        return amount;
    }

    @Override
    public Priced inner() {
        return null;
    }

    @Override
    public String asText() {
        return "Payment of " + amount + " to " + to;
    }
}


