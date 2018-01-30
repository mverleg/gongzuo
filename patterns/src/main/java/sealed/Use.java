package src.main.java.sealed;

public class Use {
    public static void main(String[] args) {
        Sealed oneState = new Sealed.Alpha("Hello World");
        Sealed otherState = new Sealed.Beta(37, 42);
        if (oneState instanceof Sealed.Alpha) {
            // Safe cast, but not automatic in Java
            System.out.println(((Sealed.Alpha) oneState).txt);
        } else if (oneState instanceof Sealed.Beta) {
            // Safe cast, but not automatic in Java
            System.out.println(((Sealed.Beta) oneState).first + " " + ((Sealed.Beta) oneState).second);
        } else {
            throw new IllegalStateException("This code is unreadable, Java just doesn't know it");
        }
    }
}
