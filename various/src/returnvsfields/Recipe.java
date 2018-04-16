package returnvsfields;

import java.util.ArrayList;
import java.util.Iterator;

import org.jetbrains.annotations.NotNull;

public class Recipe implements Iterable<Fruit> {
    @NotNull
    @Override
    public Iterator<Fruit> iterator() {
        return new ArrayList<Fruit>().iterator();
    }
}
