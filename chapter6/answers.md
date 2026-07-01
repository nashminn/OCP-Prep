# Chapter 6 – Class Design: Answers

---

**1. D – The code does not compile.**

`Dog()` has no explicit `super()` call, so the compiler inserts `super()` automatically. However, `Animal` defines only a parameterized constructor `Animal(int age)` and therefore has no no-argument constructor. The compiler cannot find `super()` in `Animal`, so the code fails to compile.

---

**2. C – SICIC**

Class initialization runs once: the `static` initializer fires first, printing `S`. Then the `main()` method executes. Each `new Tank()` triggers the instance initializer (`I`) followed by the constructor (`C`). So the output is: `S` (class init) + `IC` (1st instance) + `IC` (2nd instance) = **SICIC**.

---

**3. C and D**

- **A** is false – an abstract class is not required to declare any abstract methods.
- **B** is false – abstract classes cannot be instantiated regardless of their constructor visibility.
- **C** is true – the first concrete subclass of an abstract class must implement all inherited abstract methods.
- **D** is true – `abstract` and `final` are mutually exclusive; marking both on a class or method causes a compilation error.
- **E** is false – an abstract method must end with a semicolon and may not have a body.

---

**4. A and B**

Overriding a method requires at least the same or broader access modifier. `protected` can be widened to `public` (A) or kept as `protected` (B). It cannot be narrowed to `private` (C) or package-private (D), both of which are more restrictive than `protected`.

---

**5. B and C**

- **A** is false – a constructor cannot call both `this()` and `super()`; only one is allowed, and it must be the first statement.
- **B** is true – `this()` must be the first statement in the constructor.
- **C** is true – the compiler automatically inserts a no-argument `super()` when no explicit `this()` or `super()` call is present.
- **D** is false – `super()` refers to the most direct parent class constructor, not necessarily `Object`'s.
- **E** is false – only one call (`this()` or `super()`) is allowed, and it must be first.

---

**6. C and D**

- **Line 3** does not compile: `fly()` is a `static` method in `Bird`, so it can only be *hidden*, not overridden. Declaring it as an instance method in `Parrot` is an invalid attempt to override a static method.
- **Line 4** does not compile: `sing()` is an instance method in `Bird`, so it can only be *overridden*, not hidden. Declaring it as `static` in `Parrot` is an invalid attempt to hide an instance method.

---

**7. A, B, and D**

The overriding method must use a return type that is *covariant* with the parent's return type (`CharSequence`). A covariant return type must be the same type or a subtype of the original.

- **A** – `String` is a subtype of `CharSequence`. Valid.
- **B** – Same return type `CharSequence`. Valid.
- **C** – `Object` is a *supertype* of `CharSequence`, not a subtype. Invalid.
- **D** – `StringBuilder` implements `CharSequence`, making it a subtype. Valid.

---

**8. B and C – The code does not compile for two reasons.**

Each constructor is evaluated independently:

- **(B)** The instance initializer assigns `speed = 100`. The second `Vehicle()` constructor then tries to assign `this.speed = 50` on line B. Assigning a `final` instance variable more than once is a compilation error.
- **(C)** The `final` field `type` is never assigned in the second constructor (the instance initializer does not touch it, and the constructor body does not set it). A `final` instance variable must be assigned exactly once by the time the constructor completes.

The first `Vehicle(String type)` constructor compiles fine — it assigns `type` in its body and inherits the `speed = 100` assignment from the instance initializer without re-assigning it.
