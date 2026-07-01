# Chapter 6 – Class Design: Practice Questions

---

**1.** Given the following code, what is the result?

```java
public class Animal {
    public Animal(int age) {
        System.out.print("A");
    }
}
public class Dog extends Animal {
    public Dog() {
        System.out.print("D");
    }
}
public class Main {
    public static void main(String[] args) {
        new Dog();
    }
}
```

- A. AD
- B. DA
- C. D
- D. The code does not compile.

---

**2.** Given the following class, what is printed when `main()` is executed?

```java
public class Tank {
    static { System.out.print("S"); }
    { System.out.print("I"); }
    public Tank() { System.out.print("C"); }
    public static void main(String[] args) {
        new Tank();
        new Tank();
    }
}
```

- A. SICSIC
- B. SICC
- C. SICIC
- D. The code does not compile.

---

**3.** Which of the following statements are true about abstract classes and abstract methods? (Choose all that apply.)

- A. An abstract class must declare at least one abstract method.
- B. An abstract class can be instantiated if it defines a public constructor.
- C. A non-abstract class that extends an abstract class must implement all inherited abstract methods.
- D. A method cannot be marked both `abstract` and `final`.
- E. An abstract method may include a body enclosed in braces.

---

**4.** Which of the following are valid overrides of the `display()` method in `Child`? (Choose all that apply.)

```java
class Parent {
    protected void display() {}
}
class Child extends Parent {
    // which declarations are valid here?
}
```

- A. `public void display() {}`
- B. `protected void display() {}`
- C. `private void display() {}`
- D. `void display() {}` (package-private)

---

**5.** Which of the following statements about `this()` and `super()` in constructors are correct? (Choose all that apply.)

- A. Both `this()` and `super()` can appear in the same constructor.
- B. `this()` must be the first statement in a constructor if used.
- C. If a constructor contains neither `this()` nor `super()`, the compiler inserts `super()` automatically.
- D. `super()` always refers to the constructor of `java.lang.Object`.
- E. A constructor may call `this()` and `super()` in any order.

---

**6.** Consider the following code. Which lines cause a compilation error? (Choose all that apply.)

```java
public class Bird {
    public static void fly() {}      // line 1
    public void sing() {}            // line 2
}
public class Parrot extends Bird {
    public void fly() {}             // line 3
    public static void sing() {}     // line 4
}
```

- A. Line 1
- B. Line 2
- C. Line 3
- D. Line 4

---

**7.** Given the following declarations, which of the `Parrot` method declarations below are valid overrides of `getName()` in `Bird`? (Choose all that apply.)

```java
public class Bird {
    protected CharSequence getName() { return "Bird"; }
}
public class Parrot extends Bird {
    // insert override here
}
```

- A. `public String getName() { return "Parrot"; }`
- B. `public CharSequence getName() { return "Parrot"; }`
- C. `public Object getName() { return "Parrot"; }`
- D. `protected StringBuilder getName() { return new StringBuilder("Parrot"); }`

---

**8.** Given the following code, what is the result?

```java
public class Vehicle {
    private final int speed;
    private final String type;
    {
        speed = 100;
    }
    public Vehicle(String type) {
        this.type = type;
    }
    public Vehicle() {      // line A
        this.speed = 50;    // line B
    }
}
```

- A. The code compiles successfully.
- B. The code does not compile because `speed` is assigned twice in the second constructor.
- C. The code does not compile because `type` is never assigned in the second constructor.
- D. The code does not compile because `final` fields cannot be assigned in instance initializers.
