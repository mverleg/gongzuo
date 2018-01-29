package decorator.price.utils;

import sun.misc.Regexp;

public class Name {
    final private String value;

    public Name(String text) throws InvalidName {
        if (text.length() < 2) {
            throw new InvalidName("Names should be at least 1 character.");
        }
        if (text.length() > 100) {
            throw new InvalidName("Names should be at most 100 characters; use an abbreviated name if it is longer.");
        }
        if (text.matches("^.*[^\\d\\w ].*$")) {
            throw new InvalidName("Names should not contain any numbers or unprintable/special characters.");
        }
        this.value = text;
    }

    @Override
    public String toString() {
        return value;
    }
}

