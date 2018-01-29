package decorator.price;

import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;
import decorator.price.utils.Currency;

public interface Priced<T extends Currency> {
    @NotNull public T amount();

    @Nullable public abstract Priced inner();

    public abstract String asText();
}

