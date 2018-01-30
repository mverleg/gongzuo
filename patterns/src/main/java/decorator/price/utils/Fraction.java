package src.main.java.decorator.price.utils;

public class Fraction {

    final private double frac;

    public Fraction(double frac) {
        if (frac < 0 || frac > 1) {
            throw new IllegalArgumentException("Fraction must be between 0 and 1");
        }
        this.frac = frac;
    }

    public<T extends Currency<T>> T mult(T cur) {
        return (T) cur.mult(frac);
    }

    public double asDouble() {
        return frac;
    }

    public Fraction composite() {
        return new Fraction(1 - asDouble());
    }

    @Override
    public String toString() {
        return String.format("%.3f", frac);
    }

    public String asPercentageString() {
        return String.format("%.2f%%", 100 * frac);
    }
}
