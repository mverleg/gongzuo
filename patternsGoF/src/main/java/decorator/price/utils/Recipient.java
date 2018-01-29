package decorator.price.utils;

public class Recipient {
    final private Name name;

    public Recipient(Name name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name.toString();
    }
}
