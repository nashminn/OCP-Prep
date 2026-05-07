package chapter5;

public class Counter {
    private static int count;
    private static Integer count_;

    public Counter() {
        count++;
        // count_++;
    }

    void moo(int x, int... y) {
        System.out.println(x);
        System.out.println(y.length);
        System.out.println();
    }

    void moo(int... x) {
        System.out.println("moo2 " + x.length);
    }

    void moo(char x) {
        System.out.println("moo3 " + x);
    }
    public static void main(String[] args) {
        Counter c1 = new Counter();
        Counter c2 = new Counter();
        Counter c3 = new Counter();
        System.out.println(count);
        System.out.println(count_);
        // c1.moo(1, 2, 3); // ambiguous call, because both methods are valid
        c2.moo('a');
    }
}
