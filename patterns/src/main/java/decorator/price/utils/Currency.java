package src.main.java.decorator.price.utils;

import com.sun.istack.internal.NotNull;

public interface Currency<T extends Currency> extends Comparable<T> {

    @NotNull public double asDouble();

    @NotNull public T neg();

    @NotNull public T add(@NotNull T cur);

    // I'd like this to be a static method, but can't...
    default @NotNull public T zero() {
        return this.sub((T) this);
    }

    default @NotNull public T sub(@NotNull T cur) {
        // Pity about this cast...
        return this.add((T) cur.neg());
    }

    @NotNull public T mult(@NotNull double factor);

    default @NotNull public T min(@NotNull T first, T... rest) {
        T lowest = first;
        for (T other : rest) {
            // I am not sure about this 'unchecked call' warning...
            if (other.compareTo(lowest) < 0) {
                lowest = other;
            }
        }
        return lowest;
    }

    default @NotNull public T max(T... rest) {
        T highest = (T) this;
        for (T other : rest) {
            if (other.compareTo(highest) > 0) {
                highest = other;
            }
        }
        return highest;
    }
}

