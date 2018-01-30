package src.main.java.decorator.price;

import java.util.Optional;

import decorator.price.utils.Currency;

final public class CachedPriced<T extends Currency<T>> implements Priced<T> {

    final private Priced priced;
    private Optional<T> cache;

    public CachedPriced(Priced<T> priced) {
        this.priced = priced;
        this.cache = Optional.empty();
    }

    @Override
    public T amount() {
        if (!cache.isPresent()) {
            cache = Optional.of((T) inner().amount());
        }
        return cache.get();
    }

    @Override
    public Priced inner() {
        return priced;
    }

    @Override
    public String asText() {
        return inner().asText() + (cache.isPresent() ? " (cached)" : " (new)");
    }
}
