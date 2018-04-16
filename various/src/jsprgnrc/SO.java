package jsprgnrc;

interface a {
}

class b {
}

class c extends b implements a {
}

class d extends b {
}

class e {
    public void makeItWork() {
        b[] bees = new b[]{new c(), new d()};
        for (b bee : bees) {
            if (bee instanceof a) {
                a beeA = (a) bee;
                test(beeA);
//                test(beeA.getClass(), beeA);
            }
        }
    }

    public <T extends a> void test(T concrete) {
    }

    public <T extends a> void test(Class<T> classType, T concrete) {
    }
}
