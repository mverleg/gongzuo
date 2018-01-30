package src.main.java.decorator.price.utils;

import com.sun.istack.internal.NotNull;

public class Euro implements Currency<Euro> {

    final private int totalEuroCents;

    public Euro(@NotNull int cents) {
        this.totalEuroCents = cents;
    }

    public Euro(@NotNull int wholeEuros, @NotNull int euroCents) {
        this(100 * wholeEuros + euroCents);
        // Kind of stupid that the check has to happen after construction...
        if (euroCents < 0 || euroCents >= 100) {
            throw new IllegalArgumentException("eurocents " + euroCents + " not between 0 and 99");
        }
    }

    @Override
    public String toString() {
        return String.format("€ %.2f", 0.01 * totalEuroCents);
    }

    @Override
    public double asDouble() {
        return totalEuroCents / 100.0;
    }

    @Override
    public Euro neg() {
        return new Euro(-totalEuroCents);
    }

    @Override
    public Euro add(Euro other) {
        return new Euro(this.totalEuroCents + other.totalEuroCents);
    }

    @Override
    public Euro zero() {
        return new Euro(0);
    }

    @Override
    public Euro mult(double factor) {
        return new Euro((int) Math.round(this.totalEuroCents * factor));
    }

    @Override
    public int compareTo(Euro other) {
        return Integer.compare(totalEuroCents, other.totalEuroCents);
    }
}
