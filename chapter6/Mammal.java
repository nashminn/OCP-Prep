package chapter6;

public class Mammal {
    public Mammal(int age) {}

    public static void main(String[] args) {
        Seal seal = new Seal();
        System.out.println(seal.hello());
    }
    
}

class Seal extends Mammal {
    public Seal() {
        super(5);
    }

    public String hello() {
        return "hello, i'm a seal";
    }
}