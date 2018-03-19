package vararg;

class Main {
    public static void main(String[] args) {
        doVars(null);
        Integer q = null;
        doVars(q, q);
        doVars(q);
    }

    static public void doVars(Integer... vals) {
        System.out.println(vals);
    }
}
