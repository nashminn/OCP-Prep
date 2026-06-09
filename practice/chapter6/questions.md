# Chapter 6: Class Design — Practice Questions

---

**1.** What is the output of the following code?

```java
class Animal {
    String name = "Animal";
    Animal() { System.out.println("Animal constructor"); }
}

class Dog extends Animal {
    String name = "Dog";
    Dog() { System.out.println("Dog constructor"); }
}

public class Test {
    public static void main(String[] args) {
        Dog d = new Dog();
    }
}
```

A. `Dog constructor`

B. `Animal constructor` then `Dog constructor`

C. `Dog constructor` then `Animal constructor`

D. The code does not compile.

E. Only `Animal constructor`

---

**2.** Which of the following statements about Java inheritance are true? (Choose all that apply.)

A. A class can extend multiple classes if they are in the same package.

B. Java supports single inheritance for classes only.

C. Inheritance is transitive — if `C extends B` and `B extends A`, then `C` is a subtype of `A`.

D. A `final` class can be extended as long as it is not `abstract`.

E. All classes implicitly extend `java.lang.Object` unless they extend another class.

F. A class marked `final` cannot be subclassed.

---

**3.** What is the output of the following code?

```java
class Parent {
    int x = 10;
    void print() { System.out.println("Parent: " + x); }
}

class Child extends Parent {
    int x = 20;
    void print() { System.out.println("Child: " + x); }
}

public class Test {
    public static void main(String[] args) {
        Parent p = new Child();
        p.print();
        System.out.println(p.x);
    }
}
```

A. `Child: 20` then `20`

B. `Parent: 10` then `10`

C. `Child: 20` then `10`

D. `Parent: 10` then `20`

E. The code does not compile.

---

**4.** Which of the following constructor-related statements are true? (Choose all that apply.)

A. If a class defines no constructors, the compiler inserts a default no-argument constructor.

B. If a class defines at least one constructor with parameters, the compiler still inserts a default no-argument constructor.

C. `this()` and `super()` can both appear in the same constructor body.

D. `this()` must be the first statement in a constructor.

E. `super()` is inserted automatically as the first call in a constructor if no explicit `this()` or `super()` is present.

F. A constructor can call another constructor in the same class using `super()`.

---

**5.** Does the following code compile? If so, what does it print?

```java
class Base {
    Base() {
        this(10);
        System.out.println("Base no-arg");
    }
    Base(int x) {
        System.out.println("Base int: " + x);
    }
}

class Sub extends Base {
    Sub() {
        System.out.println("Sub");
    }
}

public class Test {
    public static void main(String[] args) {
        new Sub();
    }
}
```

A. Does not compile — `this(10)` is not the first statement.

B. `Sub`

C. `Base int: 10` then `Base no-arg` then `Sub`

D. `Base no-arg` then `Base int: 10` then `Sub`

E. `Sub` then `Base int: 10` then `Base no-arg`

---

**6.** What is the result of compiling and running the following code?

```java
class A {
    A(int x) { System.out.println("A: " + x); }
}

class B extends A {
    B() { System.out.println("B"); }
}
```

A. Compiles and prints `B` when `new B()` is called.

B. Does not compile — `B()` must explicitly call `super(int)` because `A` has no no-arg constructor.

C. Compiles but throws a runtime exception.

D. Does not compile — `B` must define `B(int x)` to match `A`.

E. Compiles and prints `A: 0` then `B` when `new B()` is called.

---

**7.** Which of the following are valid method override rules? (Choose all that apply.)

A. The overriding method may use a covariant return type.

B. The overriding method may narrow the access modifier (e.g., from `public` to `protected`).

C. The overriding method may throw any checked exception, regardless of what the parent declares.

D. The overriding method must have the same parameter types in the same order.

E. The overriding method may declare no checked exceptions even if the parent declares some.

F. The `@Override` annotation is required for a method to override a parent method.

---

**8.** What is the output of the following code?

```java
class Shape {
    public String getType() { return "Shape"; }
}

class Circle extends Shape {
    public Object getType() { return "Circle"; }
}

public class Test {
    public static void main(String[] args) {
        Shape s = new Circle();
        System.out.println(s.getType());
    }
}
```

A. `Shape`

B. `Circle`

C. Does not compile — `Object` is not a covariant return type of `String`.

D. Does not compile — return type must match exactly.

E. Throws a `ClassCastException` at runtime.

---

**9.** What is the output of the following code?

```java
class Vehicle {
    static String type = "Vehicle";
    static void describe() { System.out.println("I am a Vehicle"); }
}

class Car extends Vehicle {
    static String type = "Car";
    static void describe() { System.out.println("I am a Car"); }
}

public class Test {
    public static void main(String[] args) {
        Vehicle v = new Car();
        System.out.println(v.type);
        v.describe();
    }
}
```

A. `Car` then `I am a Car`

B. `Vehicle` then `I am a Vehicle`

C. `Car` then `I am a Vehicle`

D. `Vehicle` then `I am a Car`

E. The code does not compile.

---

**10.** Which of the following statements about `abstract` classes and methods are true? (Choose all that apply.)

A. An `abstract` class must have at least one `abstract` method.

B. A non-`abstract` class cannot have `abstract` methods.

C. An `abstract` class cannot be instantiated directly.

D. A class can be both `abstract` and `final`.

E. An `abstract` method can be `private`.

F. A concrete subclass of an `abstract` class must implement all inherited `abstract` methods.

---

**11.** Does the following code compile?

```java
public abstract class Printer {
    private abstract void print();
}
```

A. Yes — `private abstract` is a valid combination.

B. No — `abstract` methods cannot be `private` because they cannot be overridden.

C. No — `abstract` methods must be `public`.

D. Yes — as long as the subclass overrides `print()`.

E. No — only interfaces can have abstract methods.

---

**12.** What is the output of the following code?

```java
class Grandparent {
    Grandparent() { System.out.println("GP"); }
}

class Parent extends Grandparent {
    Parent() { System.out.println("P"); }
}

class Child extends Parent {
    Child() { System.out.println("C"); }
}

public class Test {
    public static void main(String[] args) {
        new Child();
    }
}
```

A. `C`

B. `C` then `P` then `GP`

C. `GP` then `P` then `C`

D. `P` then `GP` then `C`

E. The code does not compile.

---

**13.** Given the following code, which lines (if any) cause a compile error? (Choose all that apply.)

```java
1:  class Animal {
2:      private int age = 5;
3:      protected String name = "Animal";
4:      int getAge() { return age; }
5:  }
6:
7:  class Dog extends Animal {
8:      void display() {
9:          System.out.println(age);        // line 9
10:         System.out.println(name);       // line 10
11:         System.out.println(getAge());   // line 11
12:     }
13: }
```

A. Line 9

B. Line 10

C. Line 11

D. Lines 9 and 10

E. None — all lines compile.

---

**14.** What is the output of the following code?

```java
class Base {
    int value = 5;
    int getValue() { return value; }
}

class Derived extends Base {
    int value = 10;
    int getValue() { return value; }
}

public class Test {
    public static void main(String[] args) {
        Base b = new Derived();
        System.out.println(b.value);
        System.out.println(b.getValue());
    }
}
```

A. `10` then `10`

B. `5` then `5`

C. `5` then `10`

D. `10` then `5`

E. The code does not compile.

---

**15.** Which of the following correctly describes the initialization order when `new Child()` is called, given that `Child extends Parent`?

A. Child instance variables → Child constructor body → Parent instance variables → Parent constructor body

B. Parent static initializers → Child static initializers → Parent instance variables → Parent constructor body → Child instance variables → Child constructor body

C. Parent constructor body → Child constructor body → Parent instance variables → Child instance variables

D. Child static initializers → Parent static initializers → Parent instance variables → Child instance variables → Parent constructor → Child constructor

E. Parent instance variables → Parent constructor body → Child instance variables → Child constructor body

---

**16.** Does the following code compile?

```java
public abstract final class Utility {
    public static void helper() { }
}
```

A. Yes — `abstract final` is allowed on a utility class.

B. No — a class cannot be both `abstract` and `final`.

C. Yes — because the class has no abstract methods.

D. No — `abstract` modifier must come after `final`.

E. No — utility classes must use interfaces instead.

---

**17.** What is the output of the following code?

```java
class Counter {
    static int count = 0;
    static { count = 10; }
    { count += 5; }
    Counter() { count += 1; }
}

public class Test {
    public static void main(String[] args) {
        Counter c1 = new Counter();
        Counter c2 = new Counter();
        System.out.println(Counter.count);
    }
}
```

A. `10`

B. `16`

C. `22`

D. `32`

E. The code does not compile.

---

**18.** Which of the following method declarations in `Child` are valid overrides of the `Parent` method? (Choose all that apply.)

```java
class Parent {
    protected Number compute(int x) throws Exception { return x; }
}
```

A. `public Number compute(int x) throws Exception { return x; }`

B. `public Integer compute(int x) throws Exception { return x; }`

C. `public Integer compute(int x) { return x; }`

D. `private Number compute(int x) { return x; }`

E. `public Number compute(int x) throws RuntimeException { return x; }`

F. `public Number compute(int x) throws IOException { return x; }`

---

**19.** What happens when you compile the following code?

```java
class Animal {
    protected void eat() { System.out.println("Animal eats"); }
}

class Dog extends Animal {
    void eat() { System.out.println("Dog eats"); }
}
```

A. Compiles — access modifiers can be narrowed in overrides.

B. Does not compile — `void eat()` narrows `protected` to package-private.

C. Compiles — package-private and `protected` are the same.

D. Does not compile — overriding methods must keep the same access modifier.

E. Compiles but throws an exception at runtime when `eat()` is called on a `Dog` reference.

---

**20.** What is the output of the following code?

```java
abstract class Shape {
    abstract double area();
    void describe() {
        System.out.println("Area: " + area());
    }
}

class Circle extends Shape {
    double radius;
    Circle(double r) { this.radius = r; }
    double area() { return 3.14 * radius * radius; }
}

public class Test {
    public static void main(String[] args) {
        Shape s = new Circle(2.0);
        s.describe();
    }
}
```

A. `Area: 0.0`

B. `Area: 12.56`

C. Does not compile — `Shape` cannot call `area()` because it is `abstract`.

D. Does not compile — `Shape s = new Circle(2.0)` is not allowed.

E. Throws `AbstractMethodError` at runtime.

---

**21.** Which of the following are true about the `this()` and `super()` calls in constructors? (Choose all that apply.)

A. `this()` calls another constructor in the same class.

B. `super()` always calls the constructor of the most direct parent class.

C. `this()` can be used to call a grandparent constructor directly.

D. Both `this()` and `super()` can appear in the same constructor.

E. `this()` must appear as the first statement in a constructor.

F. A constructor that calls `this()` never has an implicit `super()` inserted by the compiler.

---

**22.** What is the output of the following code?

```java
class A {
    A() { this(5); System.out.println("A()"); }
    A(int x) { System.out.println("A(" + x + ")"); }
}

class B extends A {
    B() { super(3); System.out.println("B()"); }
}

public class Test {
    public static void main(String[] args) {
        new B();
    }
}
```

A. `A(3)` then `B()`

B. `A(5)` then `A()` then `B()`

C. `A(3)` then `A()` then `B()`

D. `B()` then `A(3)`

E. The code does not compile.

---

**23.** Which of the following classes are correctly defined as immutable? (Choose all that apply.)

```java
// Option A
public final class ImmutableA {
    private final int x;
    public ImmutableA(int x) { this.x = x; }
    public int getX() { return x; }
}

// Option B
public class ImmutableB {
    private final int x;
    public ImmutableB(int x) { this.x = x; }
    public int getX() { return x; }
}

// Option C
public final class ImmutableC {
    private final List<String> items;
    public ImmutableC(List<String> items) { this.items = items; }
    public List<String> getItems() { return items; }
}

// Option D
public final class ImmutableD {
    private final List<String> items;
    public ImmutableD(List<String> items) {
        this.items = new ArrayList<>(items);
    }
    public List<String> getItems() {
        return Collections.unmodifiableList(items);
    }
}
```

A. Option A only

B. Options A and D

C. Options A, B, and D

D. All four options

E. None of the options

---

**24.** What is the output of the following code?

```java
class Parent {
    String name;
    Parent() {
        name = "Parent";
        printName();
    }
    void printName() { System.out.println("Parent: " + name); }
}

class Child extends Parent {
    String name;
    Child() {
        name = "Child";
    }
    void printName() { System.out.println("Child: " + name); }
}

public class Test {
    public static void main(String[] args) {
        new Child();
    }
}
```

A. `Parent: Parent`

B. `Child: Child`

C. `Child: null`

D. `Parent: null`

E. The code does not compile.

---

**25.** What is the result of the following code?

```java
class Constructor {
    Constructor() {
        this(1);
    }
    Constructor(int x) {
        this(x, 2);
    }
    Constructor(int x, int y) {
        this();
    }
}
```

A. Compiles and runs correctly.

B. Does not compile — `this()` chain exceeds two levels.

C. Does not compile — constructor chaining cycle detected.

D. Compiles but throws a `StackOverflowError` at runtime.

E. Does not compile — a constructor cannot have more than one `this()` call chain.

---

**26.** Which of the following access rules for `protected` members are correct? (Choose all that apply.)

A. A `protected` member is accessible from any class in the same package.

B. A `protected` member is accessible from a subclass in a different package via a reference of the subclass type.

C. A `protected` member is accessible from a subclass in a different package via a reference of the parent type.

D. A `protected` member is more accessible than `public`.

E. A `protected` member declared in a parent class is accessible in a subclass even if they are in different packages.

---

**27.** What is the output of the following code?

```java
class Animal {
    void speak() { System.out.println("..."); }
}

class Dog extends Animal {
    void speak() { System.out.println("Woof"); }
    void fetch() { System.out.println("Fetching"); }
}

public class Test {
    public static void main(String[] args) {
        Animal a = new Dog();
        a.speak();
        ((Dog) a).fetch();
        a.fetch();
    }
}
```

A. `Woof` then `Fetching` then `Fetching`

B. `Woof` then `Fetching` then a compile error on the last line.

C. `...` then `Fetching` then a compile error on the last line.

D. Does not compile — `a.fetch()` is invalid.

E. `Woof` then `Fetching` then a `ClassCastException` at runtime.

---

**28.** Which modifier combinations are ILLEGAL on a method? (Choose all that apply.)

A. `public abstract`

B. `private abstract`

C. `static abstract`

D. `final abstract`

E. `protected abstract`

F. `public final`

---

**29.** What is the output of the following code?

```java
class Base {
    Base() {
        System.out.println("Base: " + getValue());
    }
    int getValue() { return 10; }
}

class Sub extends Base {
    int value = 20;
    Sub() { }
    int getValue() { return value; }
}

public class Test {
    public static void main(String[] args) {
        new Sub();
    }
}
```

A. `Base: 10`

B. `Base: 20`

C. `Base: 0`

D. `Base: 0` then `Sub constructor completes`

E. Throws a `NullPointerException`.

---

**30.** Which of the following correctly describes what happens when a parent class constructor calls an overridden method?

A. The parent's version of the method runs because we are inside the parent constructor.

B. The child's version of the method runs, even though the child's instance variables have not yet been assigned their declared values.

C. A `RuntimeException` is thrown.

D. The code does not compile.

E. The behavior is undefined.

---

**31.** What is the result of the following code?

```java
abstract class A {
    abstract void run();
    A() { run(); }
}

class B extends A {
    private String msg = "Hello";
    B() { super(); }
    void run() { System.out.println(msg); }
}

public class Test {
    public static void main(String[] args) {
        new B();
    }
}
```

A. `Hello`

B. `null`

C. Does not compile — an abstract class constructor cannot call an abstract method.

D. Throws `AbstractMethodError` at runtime.

E. Does not compile — `super()` cannot be called explicitly from `B()`.

---

**32.** Which of the following are valid placements for the `abstract` keyword on a class declaration? (Choose all that apply.)

A. `public abstract class Foo { }`

B. `abstract public class Foo { }`

C. `class abstract Foo { }`

D. `public class abstract Foo { }`

E. `public Foo abstract class { }`

---

**33.** What is the output of the following code?

```java
class P {
    static { System.out.println("P static"); }
    { System.out.println("P instance"); }
    P() { System.out.println("P constructor"); }
}

class Q extends P {
    static { System.out.println("Q static"); }
    { System.out.println("Q instance"); }
    Q() { System.out.println("Q constructor"); }
}

public class Test {
    public static void main(String[] args) {
        new Q();
        new Q();
    }
}
```

A. `P static`, `Q static`, `P instance`, `P constructor`, `Q instance`, `Q constructor`, then for the second `new Q()`: `P instance`, `P constructor`, `Q instance`, `Q constructor`

B. `P static`, `P instance`, `P constructor`, `Q static`, `Q instance`, `Q constructor`, then the same again for second `new Q()`

C. `P static`, `Q static`, `P instance`, `P constructor`, `Q instance`, `Q constructor`, then the same again

D. `Q static`, `P static`, then twice: `P instance`, `P constructor`, `Q instance`, `Q constructor`

E. The code does not compile.

---

**34.** Given the following code, what is the output?

```java
class Vehicle {
    private int speed = 60;
    int getSpeed() { return speed; }
}

class Truck extends Vehicle {
    private int speed = 80;
    int getSpeed() { return speed; }
}

public class Test {
    public static void main(String[] args) {
        Vehicle v = new Truck();
        System.out.println(v.getSpeed());
    }
}
```

A. `60`

B. `80`

C. Does not compile — `speed` is private in `Vehicle`, so `Truck` cannot declare a field with the same name.

D. Does not compile — `getSpeed()` is not `public`.

E. Throws a runtime exception.

---

**35.** Which of the following statements about `final` methods are true? (Choose all that apply.)

A. A `final` method cannot be overridden in a subclass.

B. A `final` method cannot be `static`.

C. A `final` method can call `super.method()`.

D. A class with all `final` methods does not need to be declared `final`.

E. A `final` method cannot be `abstract`.

---

**36.** What is the output of the following code?

```java
class A {
    void print() { System.out.println("A"); }
}

class B extends A {
    void print() {
        super.print();
        System.out.println("B");
    }
}

class C extends B {
    void print() {
        super.print();
        System.out.println("C");
    }
}

public class Test {
    public static void main(String[] args) {
        new C().print();
    }
}
```

A. `C`

B. `A` then `B` then `C`

C. `C` then `B` then `A`

D. `A` then `C`

E. The code does not compile.

---

**37.** Which statement about constructors in abstract classes is correct?

A. Abstract classes cannot have constructors because they cannot be instantiated.

B. Abstract classes must have at least one constructor with the same name as the class.

C. Abstract classes can have constructors that are called via `super()` from concrete subclasses.

D. The constructor of an abstract class is never called during object creation.

E. Abstract class constructors must be `protected` or `public`.

---

**38.** What is the result of the following code?

```java
class Outer {
    class Inner extends Outer { }
}
```

A. Compiles — inner classes can extend their outer class.

B. Does not compile — a class cannot extend its enclosing class.

C. Compiles but throws a `StackOverflowError` when an `Inner` is instantiated.

D. Does not compile — inner classes cannot extend any class.

E. Compiles — and `Inner` inherits all `public` members of `Outer`.

---

**39.** What is the output of the following code?

```java
class Animal {
    String sound = "generic";
    Animal() {
        System.out.println(sound);
        makeSound();
    }
    void makeSound() { System.out.println(sound); }
}

class Cat extends Animal {
    String sound = "meow";
    Cat() { super(); }
    void makeSound() { System.out.println(sound); }
}

public class Test {
    public static void main(String[] args) {
        new Cat();
    }
}
```

A. `generic` then `meow`

B. `generic` then `null`

C. `meow` then `meow`

D. `null` then `null`

E. The code does not compile.

---

**40.** Which of the following are legal uses of `instanceof`? (Choose all that apply.)

```java
class Animal {}
class Dog extends Animal {}
class Cat extends Animal {}
```

A. `Animal a = new Dog(); boolean b = a instanceof Dog;`

B. `Dog d = new Dog(); boolean b = d instanceof Cat;`

C. `Object o = "hello"; boolean b = o instanceof String;`

D. `Animal a = new Animal(); boolean b = a instanceof Dog;`

E. `Dog d = null; boolean b = d instanceof Dog;`

---

**41.** What is the result of the following code?

```java
class Parent {
    protected int calculate(int a, int b) throws ArithmeticException {
        return a / b;
    }
}

class Child extends Parent {
    public long calculate(int a, int b) { return a + b; }
}
```

A. Compiles — `long` is a covariant return type of `int`.

B. Does not compile — `long` is not a covariant return type of `int`.

C. Does not compile — the access modifier was widened from `protected` to `public`.

D. Compiles — the child method is an overload, not an override, because the return type differs.

E. Does not compile — checked exceptions were removed, which is not allowed.

---

**42.** Which of the following produce a compile error? (Choose all that apply.)

```java
// Snippet 1
abstract class A {
    abstract void m();
}
class B extends A { }         // Snippet 1

// Snippet 2
abstract class C extends A { }   // Snippet 2

// Snippet 3
class D extends A {
    void m() { }               // Snippet 3
}
```

A. Snippet 1

B. Snippet 2

C. Snippet 3

D. All three compile.

E. Snippets 1 and 2.

---

**43.** What is the output of the following code?

```java
class Alpha {
    static int x = 0;
    static {
        x = 1;
        System.out.println("Alpha static: " + x);
    }
}

class Beta extends Alpha {
    static {
        x = 2;
        System.out.println("Beta static: " + x);
    }
}

public class Test {
    public static void main(String[] args) {
        System.out.println("main: " + Beta.x);
    }
}
```

A. `main: 0`

B. `Beta static: 2` then `main: 2`

C. `Alpha static: 1` then `Beta static: 2` then `main: 2`

D. `Alpha static: 1` then `main: 1`

E. `Beta static: 2` then `Alpha static: 1` then `main: 1`

---

**44.** Which of the following are characteristics of variable hiding (as opposed to method overriding)? (Choose all that apply.)

A. The variable accessed depends on the declared (reference) type, not the runtime type.

B. Variable hiding is polymorphic.

C. Static variables can be hidden in subclasses.

D. Instance variables can be hidden in subclasses.

E. Using `super.fieldName` in a subclass accesses the parent's version of the field.

F. The `@Override` annotation can be applied to a hidden field declaration.

---

**45.** What is the output of the following code?

```java
class Fruit {
    String color() { return "unknown"; }
}

class Apple extends Fruit {
    String color() { return "red"; }
}

class GoldenApple extends Apple {
    String color() { return "yellow"; }
}

public class Test {
    public static void main(String[] args) {
        Fruit f = new GoldenApple();
        Apple a = new GoldenApple();
        System.out.println(f.color() + " " + a.color());
    }
}
```

A. `unknown red`

B. `yellow yellow`

C. `red yellow`

D. `unknown yellow`

E. The code does not compile.

---

**46.** Does the following code compile?

```java
class Parent {
    final void stop() { System.out.println("Stop"); }
}

class Child extends Parent {
    void stop() { System.out.println("Cannot stop"); }
}
```

A. Yes — `final` methods can be overridden by their direct subclass.

B. No — `final` methods cannot be overridden in any subclass.

C. Yes — because `Child.stop()` has a different method body.

D. Yes — because `Child.stop()` is not declared `final`.

E. No — `final` and overriding only apply to abstract methods.

---

**47.** What is the output of the following code?

```java
class Node {
    int val;
    Node(int val) { this.val = val; }
}

public final class ImmutableWrapper {
    private final Node node;
    public ImmutableWrapper(Node n) { this.node = n; }
    public Node getNode() { return node; }

    public static void main(String[] args) {
        Node n = new Node(1);
        ImmutableWrapper w = new ImmutableWrapper(n);
        w.getNode().val = 99;
        System.out.println(w.getNode().val);
    }
}
```

A. `1`

B. `99`

C. Does not compile.

D. Throws an exception because `ImmutableWrapper` is `final`.

E. Throws an exception because `node` is `final`.

---

**48.** Which of the following statements about calling `super.method()` are correct? (Choose all that apply.)

A. `super.method()` can only be called from within a constructor.

B. `super.method()` calls the most direct parent class's version of the method.

C. `super.method()` can skip a level — e.g., calling a grandparent method directly from a grandchild.

D. `super.method()` can be used from an instance method in a subclass.

E. `super.method()` can be used from a static method.

---

**49.** What is the output of the following code?

```java
class Mammal {
    String name = "Mammal";
    void show() { System.out.println(name); }
}

class Human extends Mammal {
    String name = "Human";
    void show() {
        System.out.println(name);
        System.out.println(super.name);
    }
}

public class Test {
    public static void main(String[] args) {
        Mammal m = new Human();
        m.show();
    }
}
```

A. `Mammal` then `Mammal`

B. `Human` then `Human`

C. `Human` then `Mammal`

D. `Mammal` then `Human`

E. The code does not compile.

---

**50.** Which of the following classes correctly implement all requirements to be considered truly immutable? (Choose all that apply.)

```java
import java.util.Date;

// Option A
public final class EventA {
    private final String title;
    private final Date date;
    public EventA(String title, Date date) {
        this.title = title;
        this.date = date;
    }
    public String getTitle() { return title; }
    public Date getDate() { return date; }
}

// Option B
public final class EventB {
    private final String title;
    private final Date date;
    public EventB(String title, Date date) {
        this.title = title;
        this.date = new Date(date.getTime());
    }
    public String getTitle() { return title; }
    public Date getDate() { return new Date(date.getTime()); }
}

// Option C
public final class EventC {
    private final String title;
    private final long timestamp;
    public EventC(String title, Date date) {
        this.title = title;
        this.timestamp = date.getTime();
    }
    public String getTitle() { return title; }
    public long getTimestamp() { return timestamp; }
}
```

A. Option A only

B. Option B only

C. Options B and C

D. Options A, B, and C

E. None of the options

---
