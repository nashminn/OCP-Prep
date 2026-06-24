package chapter6;

class B {
    B(int k) {
        System.out.println("instantiating b : " + k);
    }
}

public class A extends B {
    A(int k) {
        super(k); // will not compile without this 
        System.out.println("instantiating A");
    }

    public static void main(String[] args) {
        A a = new A(11);
        System.out.println();
    }
}
