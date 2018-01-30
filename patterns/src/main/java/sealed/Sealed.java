package src.main.java.sealed;

public class Sealed {
    /* Private constructor so that all instances must be nested classes (or derived from them). */
    private Sealed() {}

    public static final class Alpha extends Sealed {
        public final String txt;
        public Alpha(String txt) {
            super();
            this.txt = txt;
        }
    }

    public static final class Beta extends Sealed {
        public final Integer first;
        public final Integer second;
        public Beta(Integer first, Integer second) {
            super();
            this.first = first;
            this.second = second;
        }
    }
}


