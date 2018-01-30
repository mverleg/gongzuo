package src.main.java.decorator.price;

import decorator.price.utils.Euro;
import decorator.price.utils.Fraction;
import decorator.price.utils.InvalidName;
import decorator.price.utils.Name;
import decorator.price.utils.Recipient;

public class Main {
    public static void main(String[] args) throws InvalidName {
        Priced<Euro> payment = new Payment<>(
                new Recipient(new Name("Henk")),
                new Euro(37, 42));
        System.out.println(payment.asText() + " -> " + payment.amount());
        payment = new WithFranchise<>(payment, new Euro(35, 0));
        System.out.println(payment.asText() + " -> " + payment.amount());
        payment = new WithExcess<>(payment, new Euro(15, 0));
        System.out.println(payment.asText() + " -> " + payment.amount());
        payment = new WithTax<>(payment, new Fraction(0.21000));
        System.out.println(payment.asText() + " -> " + payment.amount());
        payment = new CachedPriced<>(payment);
        System.out.println(payment.asText() + " -> " + payment.amount());
        System.out.println(payment.asText() + " -> " + payment.amount());

        // Works nice for independent operations, but e.g. discount for known repairer is not independent of excess (is it?)
    }
}

