package chapter2;

public class Precedence {

    public static void main(String[] args) {
        int k = 0;
        int someOp = (--k) + (k++);
        System.out.println(k + " " + someOp);

        k = 0;
        someOp = (k--) + (k++);
        System.out.println(k + " " + someOp);

        k = 0;
        someOp = (k--) + (++k);
        System.out.println(k + " " + someOp);

        k = 0;
        someOp = (--k) + (++k);
        System.out.println(k + " " + someOp);

        k = 0;
        System.out.println(k++);
        k = 0;
        System.out.println(k--);
        k = 0;
        System.out.println(--k);
        k = 0;
        System.out.println(++k);

        System.out.println(-13 % 5);

        float x = 12; // valid
        // float x = 12.1; // invalid
        x = 13.1f; // valid
        System.out.println(x);

        k = 0;
        k += k++;
        System.out.println(k);

        k = 0;
        k += ++k;
        System.out.println(k);

        k = 0;
        k += ++k + --k; // because this is equal to k = k + (rhs)
        System.out.println(k);

        k = 0;
        k += (++k) + (k++); // because this is equal to k = k + (rhs)
        System.out.println(k);

        long a = 100000000L;
        long b = 100000000L;
        System.out.println(a * b);
    }
}