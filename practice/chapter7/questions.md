# Chapter 7: Beyond Classes — Practice Questions

---

**1.** What is the output of the following code?

```java
interface Flyable {
    default String move() { return "flying"; }
}
interface Swimmable {
    default String move() { return "swimming"; }
}
class Duck implements Flyable, Swimmable {
    public static void main(String[] args) {
        Duck d = new Duck();
        System.out.println(d.move());
    }
}
```

A. `flying`

B. `swimming`

C. The code does not compile.

D. `flyingswimming`

E. A runtime exception is thrown.

---

**2.** Which of the following are true about interface members? (Choose all that apply.)

A. Abstract methods in an interface are implicitly `public abstract`.

B. Constants in an interface are implicitly `public static final`.

C. A `private` method in an interface must have a body.

D. A `static` method in an interface can be called on implementing classes.

E. A `default` method in an interface must have a body.

F. An interface can declare a `protected` abstract method.

---

**3.** What is the output of the following code?

```java
interface Printer {
    static void print() { System.out.println("Interface"); }
}
class ConsolePrinter implements Printer {
    public static void print() { System.out.println("Class"); }
}
public class Test {
    public static void main(String[] args) {
        Printer p = new ConsolePrinter();
        p.print();
    }
}
```

A. `Interface`

B. `Class`

C. The code does not compile.

D. A runtime exception is thrown.

E. `InterfaceClass`

---

**4.** Given the following enum, what is printed?

```java
enum Planet {
    MERCURY, VENUS, EARTH, MARS;
}
public class Test {
    public static void main(String[] args) {
        Planet p = Planet.EARTH;
        System.out.println(p.ordinal() + " " + p.name());
    }
}
```

A. `3 EARTH`

B. `2 EARTH`

C. `2 earth`

D. `3 earth`

E. The code does not compile.

---

**5.** Which of the following correctly instantiates an inner class from a static context?

```java
class Outer {
    class Inner {
        void hello() { System.out.println("hi"); }
    }
}
```

A. `Inner i = new Inner();`

B. `Outer.Inner i = new Outer.Inner();`

C. `Outer o = new Outer(); Outer.Inner i = o.new Inner();`

D. `Outer.Inner i = new Outer().Inner();`

E. `Inner i = Outer.new Inner();`

---

**6.** What is the output of the following code?

```java
public enum Status {
    PENDING, ACTIVE, CLOSED;

    Status() {
        System.out.print(name() + " ");
    }
}
public class Test {
    public static void main(String[] args) {
        Status s = Status.ACTIVE;
        System.out.println("done");
    }
}
```

A. `ACTIVE done`

B. `done`

C. `PENDING ACTIVE CLOSED done`

D. `PENDING ACTIVE CLOSED`

E. The code does not compile because the constructor is not `private`.

---

**7.** Which of the following are valid ways to declare a sealed class hierarchy? (Choose all that apply.)

A. `public sealed class Shape permits Circle, Square {}`
   `public final class Circle extends Shape {}`
   `public final class Square extends Shape {}`

B. `public sealed class Shape permits Circle {}`
   `public class Circle extends Shape {}`

C. `public sealed class Shape permits Circle {}`
   `public non-sealed class Circle extends Shape {}`

D. `public sealed class Shape {}`
   `public final class Circle extends Shape {}`

E. `public sealed class Shape permits Circle {}`
   `public sealed class Circle extends Shape {}`

---

**8.** What is the output of the following code?

```java
public record Point(int x, int y) {
    public int x() { return x * 2; }
}
public class Test {
    public static void main(String[] args) {
        Point p = new Point(3, 4);
        System.out.println(p.x() + " " + p.y());
    }
}
```

A. `3 4`

B. `6 4`

C. The code does not compile because you cannot override an accessor in a record.

D. The code does not compile because accessor return types must not change.

E. `6 8`

---

**9.** Which statements about records are true? (Choose all that apply.)

A. A record is implicitly `final` and cannot be extended.

B. A record can extend another class.

C. A record can implement interfaces.

D. Record components are implicitly `private final`.

E. You can add additional instance fields to a record beyond its components.

F. Records generate `getX()` style accessor methods for each component.

---

**10.** What happens when the following code is compiled and run?

```java
public enum Direction {
    NORTH, SOUTH, EAST, WEST;
    public Direction() {}
}
public class Test {
    public static void main(String[] args) {
        System.out.println(Direction.NORTH);
    }
}
```

A. `NORTH`

B. `0`

C. The code does not compile because enum constructors cannot be `public`.

D. The code does not compile because enums cannot have constructors.

E. A runtime exception is thrown.

---

**11.** What is the output of the following code?

```java
interface Greet {
    private String prepare() { return "Hello"; }
    default String greet(String name) { return prepare() + ", " + name; }
}
class Polite implements Greet {}
public class Test {
    public static void main(String[] args) {
        Greet g = new Polite();
        System.out.println(g.greet("Alice"));
    }
}
```

A. `Hello, Alice`

B. The code does not compile because `private` methods in interfaces must be `static`.

C. The code does not compile because `Polite` must implement `prepare()`.

D. `null, Alice`

E. The code does not compile because `private` methods cannot exist in interfaces.

---

**12.** Given this code, which lines cause a compile error? (Choose all that apply.)

```java
1:  interface Vehicle {
2:      int WHEELS = 4;
3:      void drive();
4:  }
5:  class Car implements Vehicle {
6:      public void drive() {
7:          WHEELS = 6;
8:      }
9:  }
```

A. Line 2

B. Line 3

C. Line 7

D. Line 6

E. None — the code compiles.

---

**13.** What is the output of the following code?

```java
public enum Coin {
    PENNY(1), NICKEL(5), DIME(10), QUARTER(25);

    private final int value;
    Coin(int value) { this.value = value; }
    public int getValue() { return value; }
}
public class Test {
    public static void main(String[] args) {
        for (Coin c : Coin.values()) {
            if (c.getValue() > 5) System.out.print(c + " ");
        }
    }
}
```

A. `DIME QUARTER `

B. `NICKEL DIME QUARTER `

C. `10 25 `

D. The code does not compile.

E. `PENNY NICKEL DIME QUARTER `

---

**14.** Which of the following statements about anonymous classes are true? (Choose all that apply.)

A. An anonymous class can implement an interface.

B. An anonymous class can extend a class.

C. An anonymous class can both extend a class and implement an interface simultaneously.

D. An anonymous class can have a constructor.

E. An anonymous class can have `static` methods.

F. An anonymous class can have `static final` constants.

---

**15.** What is the output of the following code?

```java
interface Walker {
    default void walk() { System.out.println("Walker walking"); }
}
class Animal {
    public void walk() { System.out.println("Animal walking"); }
}
class Dog extends Animal implements Walker {
    public static void main(String[] args) {
        Dog d = new Dog();
        d.walk();
    }
}
```

A. `Walker walking`

B. `Animal walking`

C. The code does not compile because of a conflict between the interface default and the class method.

D. `Walker walkingAnimal walking`

E. A runtime exception is thrown.

---

**16.** Which of the following are true about static nested classes? (Choose all that apply.)

A. A static nested class requires an instance of the enclosing class to be instantiated.

B. A static nested class can access the private instance members of the outer class.

C. A static nested class can be instantiated with `new Outer.Nested()` from outside the outer class.

D. A static nested class can have its own static members.

E. A static nested class can access private static members of the outer class.

---

**17.** What is the output of the following code?

```java
public record Name(String first, String last) {
    public Name {
        if (first == null) throw new IllegalArgumentException("first cannot be null");
        first = first.trim();
    }
}
public class Test {
    public static void main(String[] args) {
        Name n = new Name("  Alice  ", "Smith");
        System.out.println(n.first());
    }
}
```

A. `  Alice  `

B. `Alice`

C. The code does not compile because the compact constructor has no parameter list.

D. An `IllegalArgumentException` is thrown.

E. The code does not compile because you cannot assign to `first` in the compact constructor.

---

**18.** What does the following code print?

```java
enum Season {
    SPRING, SUMMER, FALL, WINTER;
}
public class Test {
    public static void main(String[] args) {
        Season s1 = Season.SUMMER;
        Season s2 = Season.FALL;
        System.out.println(s1.compareTo(s2));
    }
}
```

A. `0`

B. `1`

C. `-1`

D. `SUMMER`

E. `-2`

---

**19.** Which of the following correctly describes a functional interface?

A. An interface with no methods.

B. An interface with exactly one `default` method.

C. An interface with exactly one `abstract` method, regardless of the number of `default` or `static` methods.

D. An interface that is annotated with `@FunctionalInterface`.

E. An interface with exactly one method total.

---

**20.** What is the output of the following code?

```java
class Outer {
    private int x = 10;
    class Inner {
        private int x = 20;
        void print() {
            int x = 30;
            System.out.println(x + " " + this.x + " " + Outer.this.x);
        }
    }
    public static void main(String[] args) {
        new Outer().new Inner().print();
    }
}
```

A. `10 20 30`

B. `30 20 10`

C. `30 10 20`

D. The code does not compile.

E. `20 30 10`

---

**21.** Which of the following are valid for enums? (Choose all that apply.)

A. An enum can implement an interface.

B. An enum can extend another enum.

C. An enum can extend an abstract class.

D. An enum can have abstract methods if each constant provides an implementation.

E. You can call `new` on an enum to create a new instance.

F. An enum constructor can be declared with no access modifier (package-private).

---

**22.** What is the output of the following code?

```java
public sealed class Shape permits Circle, Rectangle {}
public final class Circle extends Shape {
    public String type() { return "circle"; }
}
public non-sealed class Rectangle extends Shape {
    public String type() { return "rectangle"; }
}
public class ColoredRectangle extends Rectangle {
    public String type() { return "colored-rectangle"; }
}
public class Test {
    public static void main(String[] args) {
        Shape s = new ColoredRectangle();
        System.out.println(s instanceof Rectangle);
    }
}
```

A. `true`

B. `false`

C. The code does not compile because `ColoredRectangle` is not in the `permits` clause.

D. The code does not compile because `Shape` is sealed.

E. A runtime exception is thrown.

---

**23.** A local class is defined inside a method. Which variables from the enclosing method can the local class access? (Choose all that apply.)

A. Local variables that are `final`.

B. Local variables that are effectively `final` (never reassigned after initial assignment).

C. Local variables whether or not they are effectively final, as long as they're in scope.

D. Instance variables of the enclosing class.

E. Static variables of the enclosing class.

---

**24.** What is the output of this code?

```java
interface Speak {
    default String speak() { return "..."; }
    static String shout() { return "HEY"; }
}
class Parrot implements Speak {
    public String speak() { return "Polly wants a cracker"; }
}
public class Test {
    public static void main(String[] args) {
        Speak s = new Parrot();
        System.out.println(s.speak());
        System.out.println(Speak.shout());
    }
}
```

A. `...` then `HEY`

B. `Polly wants a cracker` then `HEY`

C. The code does not compile because `shout()` must be called on `Parrot`.

D. `Polly wants a cracker` then the code throws an exception.

E. The code does not compile because static methods in interfaces cannot be called.

---

**25.** Which of the following cause a compile error? (Choose all that apply.)

```java
1:  public record Employee(String name, int salary) {
2:      static int count = 0;
3:      int bonus = 500;
4:      public Employee {
5:          if (salary < 0) throw new IllegalArgumentException();
6:      }
7:      public String getName() { return name; }
8:      public int name() { return 0; }
9:  }
```

A. Line 2

B. Line 3

C. Line 7

D. Line 8

E. None — all lines compile.

---

**26.** What is the output of the following code?

```java
interface Countable {
    int count = 0;
}
class MyList implements Countable {
    public static void main(String[] args) {
        MyList list = new MyList();
        list.count = 5;
        System.out.println(count);
    }
}
```

A. `0`

B. `5`

C. The code does not compile.

D. A runtime exception is thrown.

E. `null`

---

**27.** Which of the following are true about a `non-sealed` class? (Choose all that apply.)

A. A `non-sealed` class must be a direct permitted subclass of a sealed class.

B. A `non-sealed` class can be extended by any class without restriction.

C. A `non-sealed` class closes the sealed hierarchy.

D. A class can be both `non-sealed` and `final`.

E. The keyword `non-sealed` is only valid when the superclass is `sealed`.

---

**28.** What is the output of the following?

```java
enum Op {
    ADD {
        public int apply(int x, int y) { return x + y; }
    },
    MUL {
        public int apply(int x, int y) { return x * y; }
    };
    public abstract int apply(int x, int y);
}
public class Test {
    public static void main(String[] args) {
        System.out.println(Op.ADD.apply(3, 4) + " " + Op.MUL.apply(3, 4));
    }
}
```

A. `7 12`

B. `34 34`

C. The code does not compile because enum constants cannot have bodies.

D. The code does not compile because abstract methods are not allowed in enums.

E. `ADD MUL`

---

**29.** Which of the following correctly describes the difference between method overriding and method hiding?

A. Overriding applies to static methods; hiding applies to instance methods.

B. Overriding applies to instance methods and is polymorphic; hiding applies to static methods and is not polymorphic.

C. Both overriding and hiding are polymorphic.

D. Hiding applies to instance methods and is polymorphic; overriding applies to static methods and is not polymorphic.

E. There is no difference — both result in the child class method being called on the reference type.

---

**30.** What is the output of the following code?

```java
class Animal {
    public static String type() { return "Animal"; }
    public String name() { return "Generic"; }
}
class Cat extends Animal {
    public static String type() { return "Cat"; }
    public String name() { return "Kitty"; }
}
public class Test {
    public static void main(String[] args) {
        Animal a = new Cat();
        System.out.println(a.type() + " " + a.name());
    }
}
```

A. `Cat Kitty`

B. `Animal Kitty`

C. `Animal Generic`

D. `Cat Generic`

E. The code does not compile.

---

**31.** What is the output of the following code?

```java
interface A {
    default void hello() { System.out.println("A"); }
}
interface B extends A {
    default void hello() { System.out.println("B"); }
}
class C implements A, B {
    public static void main(String[] args) {
        new C().hello();
    }
}
```

A. `A`

B. `B`

C. The code does not compile because `C` must override `hello()`.

D. The output is unpredictable.

E. `AB`

---

**32.** Which of the following are true about the `instanceof` pattern matching feature? (Choose all that apply.)

```java
Object obj = "Hello";
if (obj instanceof String s && s.length() > 3) {
    System.out.println(s.toUpperCase());
}
```

A. This code compiles and prints `HELLO`.

B. The variable `s` is only in scope inside the `if` block.

C. The variable `s` can be used after the `if` block.

D. If `obj` is not a `String`, the code throws a `ClassCastException`.

E. The `&&` is necessary to prevent a `NullPointerException`.

---

**33.** What is the output of the following code?

```java
public class Test {
    public static void main(String[] args) {
        Runnable r = new Runnable() {
            private int count = 0;
            public void run() {
                count++;
                System.out.println(count);
            }
        };
        r.run();
        r.run();
    }
}
```

A. `1` then `1`

B. `1` then `2`

C. `0` then `1`

D. The code does not compile.

E. `1` then an exception is thrown.

---

**34.** Which of the following compile? (Choose all that apply.)

A.
```java
interface I { void go(); }
class C implements I { void go() {} }
```

B.
```java
interface I { void go(); }
class C implements I { public void go() {} }
```

C.
```java
abstract class C implements I { }
interface I { void go(); }
```

D.
```java
interface I { void go(); }
interface J extends I {}
class C implements J { public void go() {} }
```

E.
```java
interface I { default void go() {} }
interface J { default void go() {} }
class C implements I, J {}
```

---

**35.** What is the output of the following code?

```java
enum Day {
    MON, TUE, WED, THU, FRI, SAT, SUN;
}
public class Test {
    public static void main(String[] args) {
        Day d = Day.WED;
        switch (d) {
            case MON, TUE, WED -> System.out.println("Weekday");
            case SAT, SUN -> System.out.println("Weekend");
            default -> System.out.println("Other");
        }
    }
}
```

A. `Weekday`

B. `Other`

C. The code does not compile because switch cases for enums must use the full qualified name like `Day.MON`.

D. `Weekend`

E. The code does not compile because the arrow syntax is not valid with enums.

---

**36.** Which of the following statements are true about a record's compact constructor? (Choose all that apply.)

A. The compact constructor has no parameter list in its declaration.

B. The compact constructor's parameters are implicitly available by the component names.

C. Field assignment (`this.x = x`) happens automatically AFTER the compact constructor body executes.

D. You can reassign the component variables inside the compact constructor.

E. A record can have both a compact constructor and a canonical constructor at the same time.

---

**37.** What is the output of the following code?

```java
class Outer {
    static int x = 10;
    static class Nested {
        int x = 20;
        void show() {
            System.out.println(x + " " + Outer.x);
        }
    }
    public static void main(String[] args) {
        new Nested().show();
    }
}
```

A. `10 10`

B. `20 10`

C. `10 20`

D. `20 20`

E. The code does not compile.

---

**38.** Which of the following cause a compile error when attempting to extend or use the following sealed interface? (Choose all that apply.)

```java
public sealed interface Drawable permits Circle, Square {}
```

A. `public final class Circle implements Drawable {}`

B. `public non-sealed class Square implements Drawable {}`

C. `public class Triangle implements Drawable {}`

D. `public sealed class Circle implements Drawable permits SmallCircle {}`
   `public final class SmallCircle extends Circle {}`

E. `public abstract class Circle implements Drawable {}`

---

**39.** What is the output of the following code?

```java
public record Box<T>(T value) {
    public static int count = 0;
    public Box {
        count++;
    }
}
public class Test {
    public static void main(String[] args) {
        new Box<>(1);
        new Box<>("hello");
        System.out.println(Box.count);
    }
}
```

A. `0`

B. `1`

C. `2`

D. The code does not compile because records cannot have static fields.

E. The code does not compile because records cannot be generic.

---

**40.** What is the output of the following code?

```java
interface Logger {
    default void log(String msg) {
        System.out.println(format(msg));
    }
    private String format(String msg) {
        return "[LOG] " + msg;
    }
}
class AppLogger implements Logger {}
public class Test {
    public static void main(String[] args) {
        new AppLogger().log("starting");
    }
}
```

A. `starting`

B. `[LOG] starting`

C. The code does not compile because `format()` is `private` and cannot be used in a `default` method.

D. The code does not compile because `AppLogger` must implement `format()`.

E. The code does not compile because `private` methods in interfaces cannot return values.

---

**41.** Which of the following are true about the `enum` method `values()`? (Choose all that apply.)

A. `values()` is declared in `java.lang.Enum`.

B. `values()` is implicitly added by the compiler to every enum.

C. `values()` returns an array of the enum constants in declaration order.

D. `values()` returns a `List` of the enum constants.

E. The array returned by `values()` is the same array instance each time it is called.

---

**42.** What is the output of the following code?

```java
public class Test {
    interface Transformer {
        int transform(int x);
    }
    public static void main(String[] args) {
        int factor = 3;
        Transformer t = x -> x * factor;
        factor = 5;
        System.out.println(t.transform(4));
    }
}
```

A. `12`

B. `20`

C. The code does not compile because `factor` is not effectively final.

D. `15`

E. The code does not compile because `Transformer` is not annotated with `@FunctionalInterface`.

---

**43.** Which of the following are true about inner classes (non-static member classes)? (Choose all that apply.)

A. An inner class can access `private` members of the outer class.

B. An inner class can declare `static` methods.

C. An inner class can declare `static final` constants.

D. An inner class instance requires an existing outer class instance.

E. An inner class can be declared `abstract`.

---

**44.** What is the output of the following code?

```java
enum Color {
    RED, GREEN, BLUE;
}
public class Test {
    public static void main(String[] args) {
        Color c = Color.valueOf("GREEN");
        System.out.println(c == Color.GREEN);
        System.out.println(c.ordinal());
    }
}
```

A. `true` then `1`

B. `true` then `2`

C. `false` then `1`

D. The code does not compile.

E. An `IllegalArgumentException` is thrown at runtime.

---

**45.** Which of the following correctly declares an interface that can be used as a functional interface? (Choose all that apply.)

A.
```java
@FunctionalInterface
interface Doable { void doIt(); }
```

B.
```java
interface Doable {
    void doIt();
    default void doItLater() {}
    static void doItNever() {}
}
```

C.
```java
@FunctionalInterface
interface Doable {
    void doIt();
    void doItAgain();
}
```

D.
```java
interface Doable {
    boolean equals(Object o);
}
```

E.
```java
@FunctionalInterface
interface Doable {
    void doIt();
    boolean equals(Object o);
}
```

---

**46.** What is the output of the following code?

```java
class Outer {
    private String msg = "outer";
    void print() {
        String msg = "local";
        Runnable r = new Runnable() {
            public void run() {
                System.out.println(msg);
            }
        };
        msg = "changed";
        r.run();
    }
    public static void main(String[] args) {
        new Outer().print();
    }
}
```

A. `outer`

B. `local`

C. `changed`

D. The code does not compile because `msg` is not effectively final.

E. The code does not compile because the anonymous class cannot access the outer instance field.

---

**47.** Which of the following are true about sealed classes and pattern matching? (Choose all that apply.)

```java
sealed interface Shape permits Circle, Rectangle {}
record Circle(double radius) implements Shape {}
record Rectangle(double w, double h) implements Shape {}
```

A. A `switch` expression over `Shape` can be exhaustive without a `default` case.

B. A `switch` expression over `Shape` always requires a `default` case.

C. Pattern matching with `instanceof` does not work with sealed types.

D. Adding a new permitted subclass to `Shape` without updating switch expressions can cause a compile error.

E. The `Circle` and `Rectangle` records are implicitly `final`.

---

**48.** What is the output of the following code?

```java
interface I {
    static void staticMethod() { System.out.println("I"); }
}
class A implements I {
    public static void main(String[] args) {
        A a = new A();
        a.staticMethod();
    }
}
```

A. `I`

B. The code does not compile because `staticMethod` is not accessible on `a`.

C. The code does not compile because interface static methods are not inherited.

D. A runtime exception is thrown.

E. The code compiles but prints nothing.

---

**49.** Which of the following are true about the `equals()` and `hashCode()` methods generated for a record? (Choose all that apply.)

A. Two record instances are equal if they are the same type and all components are equal via `equals()`.

B. `hashCode()` is based solely on the first component.

C. You can override `equals()` and `hashCode()` in a record.

D. If you override `equals()`, you must also manually override `hashCode()` — the record will NOT auto-generate it.

E. The generated `toString()` includes the record class name and all component values.

---

**50.** What is the output of the following code?

```java
sealed class Vehicle permits Car, Truck {}
final class Car extends Vehicle {}
final class Truck extends Vehicle {}

public class Test {
    static String describe(Vehicle v) {
        return switch (v) {
            case Car c -> "It's a car";
            case Truck t -> "It's a truck";
        };
    }
    public static void main(String[] args) {
        System.out.println(describe(new Car()));
        System.out.println(describe(new Truck()));
    }
}
```

A. `It's a car` then `It's a truck`

B. The code does not compile because the switch is missing a `default` case.

C. The code does not compile because pattern matching is not allowed in switch expressions.

D. The code does not compile because `Car` and `Truck` are final.

E. A runtime exception is thrown.
