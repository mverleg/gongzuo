package src.main.java.decorator.price;

import decorator.price.utils.Currency;
import decorator.price.utils.Fraction;

final public class WithTax<T extends Currency<T>> implements Priced<T> {

    final private Priced priced;
    final private Fraction tax;

    public WithTax(Priced<T> priced, Fraction tax) {
        this.priced = priced;
        this.tax = tax;
    }

    @Override
    public T amount() {
        return (T) priced.amount().mult(tax.composite().asDouble());
    }

    @Override
    public Priced inner() {
        return priced;
    }

    @Override
    public String asText() {
        return inner().asText() + ", minus " + tax.asPercentageString() + " tax";
    }
}
