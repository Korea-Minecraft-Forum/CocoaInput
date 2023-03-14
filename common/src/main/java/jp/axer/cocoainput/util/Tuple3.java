package jp.axer.cocoainput.util;

public final class Tuple3<A, B, C> {

    private final A va;
    private final B vb;
    private final C vc;

    public Tuple3(A a, B b, C c) {
        va = a;
        vb = b;
        vc = c;
    }

    public A _1() {
        return va;
    }

    public B _2() {
        return vb;
    }

    public C _3() {
        return vc;
    }
}
