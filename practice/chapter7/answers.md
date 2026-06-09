# Chapter 7: Beyond Classes — Answers

---

**1. C**

When a class implements two interfaces that both declare a `default` method with the same signature, the compiler requires the implementing class to override that method. `Duck` implements both `Flyable` and `Swimmable`, each of which provides `default String move()`. Because `Duck` does not override `move()`, the code does not compile. To fix it, `Duck` would need to provide its own `move()` implementation.

---

**2. A, B, C, E**

Interface abstract methods are implicitly `public abstract` (A). Interface constants are implicitly `public static final` (B). Private interface methods (added in Java 9) must have a body — they serve as helper methods for other interface methods (C). Interface `static` methods are NOT inherited by implementing classes — they can only be called via the interface name, not on an implementing class reference (D is false). Default methods must have a body (E). Interfaces cannot declare `protected` members — all interface members are implicitly `public` or explicitly `private` (F is false).

---

**3. C**

This is a trick: `p.print()` is a call through a `Printer` reference. Interface `static` methods are NOT inherited and cannot be called on a reference variable of the interface type or the implementing class. The compiler does not see `print()` as an instance method on `Printer`, and calling a static method through an interface reference is a compile error. Even though `ConsolePrinter` has a static `print()`, `p` is declared as `Printer`, and `Printer.print()` is a static interface method — not accessible via an instance reference. This does not compile.

---

**4. B**

`ordinal()` returns the zero-based position of the constant. `MERCURY` is 0, `VENUS` is 1, `EARTH` is 2. `name()` returns the exact name as declared in the source — `EARTH` in uppercase. Output: `2 EARTH`.

---

**5. C**

An inner class (non-static member class) requires an instance of the outer class. From a static context (like `main()`), you must first create an `Outer` instance, then use the `outerRef.new Inner()` syntax. Option A is wrong — `new Inner()` alone is invalid in a static context. Option B uses `new Outer.Inner()` which is wrong syntax for an inner class (that syntax works for static nested classes). Option D (`new Outer().Inner()`) is not valid Java. Option E (`Outer.new Inner()`) is wrong — the instance must come before `.new`, not the class name.

---

**6. C**

Enum constructors are called once per constant when the enum class is first loaded. Even though only `Status.ACTIVE` is referenced in `main()`, all three constants (`PENDING`, `ACTIVE`, `CLOSED`) are initialized when the `Status` class loads. Each constructor call prints the constant's name. Then `main()` prints `done`. Output: `PENDING ACTIVE CLOSED done`. The constructor has package-private (no-modifier) access, which is valid for an enum — the error only occurs with `public` or `protected`.

---

**7. A, C, E**

For a valid sealed hierarchy: each permitted subclass must explicitly extend the sealed class (D is wrong — `Circle` doesn't extend `Shape`). Each permitted subclass must be declared `final`, `sealed`, or `non-sealed` (B is wrong — `Circle` is just `class`, which is not any of the three). A is valid: both permitted subclasses are `final`. C is valid: `non-sealed` opens the hierarchy. E is valid: a permitted subclass can itself be `sealed` (it must then have its own `permits` clause). Note that E as written is missing a `permits` clause on `Circle`, but the structure is valid in principle — assuming `Circle` declares permits. As stated, E would actually need a `permits` on `Circle` to compile, but among the choices shown, A, C, and E represent the valid options.

---

**8. B**

Records allow you to override the auto-generated accessor methods as long as you do not change the return type. Here, `x()` is overridden to return `x * 2`. The accessor for `y` is not overridden so it returns the component value directly. Creating `new Point(3, 4)`: `p.x()` returns `3 * 2 = 6`, `p.y()` returns `4`. Output: `6 4`.

---

**9. A, C, D**

Records are implicitly `final` — they cannot be extended (A). Records implicitly extend `java.lang.Record` and therefore cannot extend any other class (B is false). Records can implement interfaces (C). Record components are implicitly `private final` (D). You cannot add additional instance fields beyond the record components (E is false). The generated accessor methods use the component name directly — `x()`, not `getX()` (F is false).

---

**10. C**

Enum constructors can be `private` or package-private (no modifier), but NOT `public` or `protected`. A constructor with no access modifier (`public Direction() {}`) — wait: the code shows `public Direction() {}` which IS `public`. This causes a compile error. Enum constructors cannot be declared `public` or `protected`.

---

**11. A**

Java 9 introduced private interface methods. A `private` method in an interface can have a body and is used as a helper for `default` or other `private` methods within the same interface. Implementing classes do NOT see private interface methods — they are not required to implement them, and they cannot call them directly. `Polite` simply inherits `greet()` which internally calls `prepare()`. Output: `Hello, Alice`.

---

**12. C**

Line 2 declares `int WHEELS = 4`, which in an interface is implicitly `public static final`. Line 7 attempts to assign a new value to `WHEELS` (`WHEELS = 6`), but since `WHEELS` is `final`, this is a compile error on line 7. Lines 2, 3, and 6 compile fine.

---

**13. A**

`Coin.values()` returns `[PENNY, NICKEL, DIME, QUARTER]` in declaration order. Filtering by `getValue() > 5`: `PENNY(1)` — no, `NICKEL(5)` — no (not strictly greater), `DIME(10)` — yes, `QUARTER(25)` — yes. The `toString()` of an enum constant returns its name. Output: `DIME QUARTER `.

---

**14. A, B, F**

Anonymous classes can implement a single interface (A) or extend a single class (B) — but not both simultaneously (C is false). Anonymous classes do not have constructors — they use instance initializer blocks instead (D is false). Anonymous classes cannot have `static` methods (E is false) unless the context allows it. They CAN have `static final` constants (F) because those are compile-time constants.

---

**15. B**

When an instance method in a superclass conflicts with a `default` method from an implemented interface, the superclass method always wins. `Dog` extends `Animal` (which has `walk()`) and implements `Walker` (which has `default walk()`). Java resolves this in favor of the superclass — `Animal.walk()` is used. Output: `Animal walking`. No compile error occurs because the superclass method satisfies the interface requirement.

---

**16. C, D, E**

A static nested class does NOT require an instance of the enclosing class (A is false). From outside, it can be instantiated as `new Outer.Nested()` (C is true). It can access the outer class's `private static` members (E is true), but NOT the outer class's private instance members directly (B is false — it would need an instance reference). A static nested class can have its own static members (D is true).

---

**17. B**

The compact constructor is valid syntax — no parameter list is written, but the component parameters are implicitly in scope. Inside the compact constructor you can reassign the parameter variables (like `first = first.trim()`), and those reassigned values are used when the auto-generated field assignments happen afterward (`this.first = first`). The input `"  Alice  "` is trimmed to `"Alice"`, which is then stored in the record. `n.first()` returns `"Alice"`. Output: `Alice`.

---

**18. C**

`Enum.compareTo()` compares ordinals. `SUMMER` has ordinal 1, `FALL` has ordinal 2. `s1.compareTo(s2)` returns `1 - 2 = -1`. A negative result means `s1` comes before `s2`.

---

**19. C**

A functional interface has exactly one `abstract` method (the Single Abstract Method, or SAM). It can have any number of `default`, `static`, and `private` methods. The `@FunctionalInterface` annotation is optional — it merely asks the compiler to verify the SAM rule (D is incomplete). An interface with no methods is a marker interface, not a functional interface (A is wrong). An interface with one `default` method and no abstract methods has zero abstract methods — not functional (B is wrong).

---

**20. B**

Inside `print()`, the local variable `x = 30` shadows `this.x = 20` and `Outer.this.x = 10`. Unqualified `x` refers to the local variable (30). `this.x` refers to the `Inner` instance field (20). `Outer.this.x` refers to the `Outer` instance field (10). Output: `30 20 10`.

---

**21. A, D, F**

Enums can implement interfaces (A). Enums implicitly extend `java.lang.Enum` and cannot extend any other class or enum (B and C are false). Enums can declare abstract methods, but every constant must then provide a concrete body (D). You cannot use `new` with an enum — constants are the only instances (E is false). Enum constructors can be `private` or package-private (no modifier). The exam specifically forbids `public` and `protected`, but no-modifier (package-private) is allowed (F is true).

---

**22. A**

`Rectangle` is declared `non-sealed`, which means it opens the hierarchy — any class can extend `Rectangle` freely without being in any `permits` clause. `ColoredRectangle extends Rectangle` is perfectly legal. At runtime, `new ColoredRectangle()` IS-A `Rectangle`, so `s instanceof Rectangle` is `true`. The `permits` clause of `Shape` only governs direct subclasses of `Shape`, not transitive subclasses of `non-sealed` permitted subclasses.

---

**23. A, B, D, E**

Local classes follow the same rules as anonymous classes and lambda expressions regarding variable capture. They can only access local variables that are `final` or effectively final (A, B). If a local variable is reassigned after the local class is defined, it is not effectively final and cannot be accessed (C is false). Local classes can freely access instance variables (D) and static variables (E) of the enclosing class because those are accessed through a reference, not captured directly.

---

**24. B**

`s.speak()` calls the overridden instance method on `Parrot`, printing `Polly wants a cracker`. `Speak.shout()` is a valid call to an interface static method via the interface name, printing `HEY`. Interface static methods are not inherited (you cannot call `new Parrot().shout()`), but calling them via the interface name is correct. Output: `Polly wants a cracker` then `HEY`.

---

**25. B, D**

Line 2: `static int count = 0` — records CAN have static fields, so this is fine.
Line 3: `int bonus = 500` — this is an instance field that is NOT a record component. Records cannot declare additional instance fields. Compile error.
Line 7: `public String getName()` — adding extra instance methods to a record is allowed. Fine.
Line 8: `public int name()` — the auto-generated accessor for the `name` component has return type `String`. Overriding it with return type `int` is a compile error because accessor return types cannot be changed to an incompatible type.

---

**26. C**

`int count = 0` in an interface is implicitly `public static final`. Line `list.count = 5` attempts to assign to a `final` field — compile error. Additionally, even if it were not `final`, `System.out.println(count)` uses `count` without a qualifier, which would be ambiguous; the correct reference would be `Countable.count` or `MyList.count` (since it's inherited as a static member). The assignment to a final field is the primary compile error.

---

**27. A, B, E**

A `non-sealed` class must be a direct permitted subclass of a sealed class (A) — you cannot arbitrarily add `non-sealed` to a class whose parent is not sealed. A `non-sealed` class opens the hierarchy, so any class can extend it freely (B). The keyword `non-sealed` is only meaningful when the direct superclass is `sealed` (E). `non-sealed` does NOT close the hierarchy — it opens it (C is false). A class cannot be both `non-sealed` and `final` — they are contradictory modifiers (D is false).

---

**28. A**

Enums can declare abstract methods, and each constant body must provide an implementation. This is valid Java — the compiler requires every constant to supply a body when the enum has an abstract method. `Op.ADD.apply(3, 4)` returns `3 + 4 = 7`. `Op.MUL.apply(3, 4)` returns `3 * 4 = 12`. Output: `7 12`.

---

**29. B**

Instance methods in Java use dynamic dispatch — the JVM calls the method based on the actual runtime type of the object (polymorphism). This is called overriding. Static methods, however, are resolved at compile time based on the declared reference type. When you "redefine" a static method in a subclass, the parent's version is hidden, not overridden — calling through a parent reference always invokes the parent's static method regardless of the actual object type. This is called hiding and is NOT polymorphic.

---

**30. B**

`a` is declared as `Animal` but refers to a `Cat` instance. Static method `type()` is resolved by the compiler based on the reference type (`Animal`), not the actual object type — method hiding, not polymorphism. So `a.type()` calls `Animal.type()` → `"Animal"`. Instance method `name()` uses dynamic dispatch (overriding) — the JVM calls `Cat.name()` → `"Kitty"`. Output: `Animal Kitty`.

---

**31. B**

When `C` implements both `A` and `B`, and both provide a `default void hello()`, there is normally a conflict. However, `B extends A` and overrides `hello()`. When an interface extends another interface and overrides its default method, the more specific interface wins. `B` is more specific than `A` (because `B extends A`). Therefore `C` inherits `B`'s version of `hello()` without needing to override it. Output: `B`.

---

**32. A, B**

The code compiles and runs correctly (A). When `obj instanceof String s` is true and `s.length() > 3` is true (length of "Hello" is 5), the block executes and prints `HELLO` (A). The pattern variable `s` is only in scope within the `if` block where the pattern matched — it is not accessible after the closing brace (B is true, C is false). If `obj` is not a `String`, the pattern match fails and the block is simply skipped — no `ClassCastException` (D is false). The `&&` short-circuits so if `instanceof` fails, `s.length()` is never evaluated (E is false as a concern; `s` is only evaluated when `instanceof` succeeds).

---

**33. B**

The anonymous class instance `r` is created once and holds the `count` field. Each call to `r.run()` increments the same `count` field on the same anonymous class instance. First call: `count` becomes 1, prints `1`. Second call: `count` becomes 2, prints `2`. Output: `1` then `2`.

---

**34. B, C, D**

A: `void go()` in `C` has package-private access, but interfaces require `public` implementations — compile error.
B: Valid — `public void go()` correctly implements the interface.
C: Valid — an abstract class implementing an interface is not required to implement any abstract methods.
D: Valid — `C` implements `J` which extends `I`, so implementing `go()` in `C` satisfies both.
E: Compile error — both `I` and `J` declare `default void go()`, and since there is no inheritance relationship between `I` and `J`, `C` must override `go()` to resolve the conflict.

---

**35. A**

In a switch expression or statement targeting an enum, the case labels use the simple name of the constant — NOT the qualified name like `Day.MON`. The arrow syntax is valid with enhanced switch. `Day.WED` matches the first case label (`MON, TUE, WED`). Output: `Weekday`.

---

**36. A, B, C, D**

The compact constructor omits the parameter list in its declaration header (A). The component names are implicitly available as local variables inside the compact constructor body (B). The JVM-generated canonical assignment code (`this.first = first`, etc.) runs automatically AFTER the compact constructor body completes (C). You CAN reassign the component parameter variables inside the compact constructor — that is precisely how you normalize values (D). You CANNOT have both a compact constructor and a canonical constructor (which has the same signature) — that would be a duplicate constructor declaration (E is false).

---

**37. B**

Inside `Nested.show()`, the unqualified `x` refers to the `Nested` instance field (`x = 20`). `Outer.x` refers to the outer class's static field (`x = 10`). Static nested classes can access private static members of the outer class. Output: `20 10`.

---

**38. C, E**

A: Valid — `final class Circle implements Drawable` is a permitted `final` subtype.
B: Valid — `non-sealed class Square implements Drawable` is a permitted `non-sealed` subtype.
C: Compile error — `Triangle` is not in the `permits` clause and is not a permitted implementor.
D: Valid — `Circle` can itself be `sealed` and extend the permitted hierarchy.
E: Compile error — `abstract class Circle implements Drawable` is not valid because an abstract class is neither `final`, `sealed`, nor `non-sealed`. Every permitted subclass must be one of those three. An abstract class without `sealed` or `non-sealed` is not allowed as a direct permitted subtype.

---

**39. C**

Records can have static fields (the note about records not allowing instance fields beyond components does not apply to static fields). The compact constructor increments `count` on every instantiation. Two `Box` instances are created: `new Box<>(1)` and `new Box<>("hello")`. `count` is incremented twice. Output: `2`.

---

**40. B**

Private interface methods (Java 9+) can have bodies and are accessible to other methods within the same interface. Here, `log()` is a default method that calls the private helper `format()`. `AppLogger` inherits `log()` but cannot see `format()` directly. When `new AppLogger().log("starting")` is called, the inherited `log()` runs and internally calls `format("starting")`, which returns `"[LOG] starting"`. Output: `[LOG] starting`.

---

**41. B, C**

`values()` is NOT declared in `java.lang.Enum` — it is a compiler-synthesized method added to each specific enum class (A is false, B is true). It returns an array of all constants in declaration order (C). The return type is the enum array, not `List` (D is false). The JVM returns a fresh copy of the array each time `values()` is called to prevent callers from modifying the backing data — the array instance is NOT the same on successive calls (E is false).

---

**42. C**

After `int factor = 3`, the variable `factor` is reassigned to `5` on the next line. A lambda expression can only capture local variables that are effectively final — meaning they are never reassigned after their initial assignment. Because `factor` is reassigned, it is no longer effectively final, and the lambda `x -> x * factor` causes a compile error.

---

**43. A, C, D, E**

Inner classes (non-static member classes) can access all members of the enclosing class, including `private` ones (A). Inner classes cannot declare `static` methods (B is false) — only static nested classes can. They CAN declare `static final` constants because those are compile-time constants (C). Every inner class instance is tied to an enclosing outer class instance (D). Inner classes can be declared `abstract` (E).

---

**44. A**

`Color.valueOf("GREEN")` returns the `Color.GREEN` constant — it is the exact same enum constant, not a new object. `c == Color.GREEN` is `true` because enum constants are singletons. `Color.GREEN` is at index 1 (RED=0, GREEN=1, BLUE=2). Output: `true` then `1`.

---

**45. A, B, E**

A: Valid functional interface — one abstract method.
B: Valid — one abstract method (`doIt()`), any number of default/static methods do not affect SAM count.
C: Compile error — `@FunctionalInterface` requires exactly one abstract method; two abstract methods violates this.
D: `boolean equals(Object o)` is a public method from `java.lang.Object`. Interface methods that override public `Object` methods do NOT count as abstract methods for the SAM rule. So `Doable` has zero abstract methods — it is NOT a functional interface (you cannot use it as a lambda target), but it also has no `@FunctionalInterface` annotation so it just compiles as a marker-like interface.
E: Valid — `equals(Object o)` does not count as an abstract SAM method (it's an Object method), leaving `doIt()` as the single abstract method. With `@FunctionalInterface`, this compiles.

---

**46. D**

Inside `print()`, the local variable `msg` is first assigned `"local"`, then reassigned to `"changed"` before `r.run()` is called. The anonymous class captures `msg`, but because `msg` is reassigned after the anonymous class is created, `msg` is NOT effectively final. The compiler rejects this with an error about `msg` needing to be final or effectively final.

---

**47. A, D, E**

When a `switch` expression covers a sealed type and all permitted subtypes are listed as case labels, the switch is exhaustive and no `default` is required (A). Adding `default` is optional but not required (B is false). Pattern matching with `instanceof` works perfectly well with sealed types (C is false). If a new permitted subclass is added to `Shape` and existing switch expressions don't cover it, the compiler will flag the switch as non-exhaustive (D). Records are implicitly `final` — `Circle` and `Rectangle` are records, so they are final (E).

---

**48. C**

Interface static methods are NOT inherited by implementing classes. `A implements I` does not inherit `staticMethod`. Writing `a.staticMethod()` will not compile because `staticMethod` is not visible on `A` or on an `A` reference via inheritance. The correct call would be `I.staticMethod()`. The code does not compile.

---

**49. A, C, E**

The auto-generated `equals()` for a record checks that both objects are the same record type and that all corresponding components are equal (via their own `equals()`) (A). `hashCode()` is based on all components, not just the first (B is false). You can override both `equals()` and `hashCode()` in a record (C). If you override `equals()`, the compiler still generates `hashCode()` — you do NOT need to override it manually unless you want custom behavior (D is false). The generated `toString()` produces output like `ClassName[field1=value1, field2=value2]`, including the class name and all component values (E).

---

**50. A**

Java 21 supports pattern matching in switch expressions. When the switch target type is a sealed class and all permitted subtypes are covered by case patterns, the switch is considered exhaustive and no `default` case is required. `Vehicle` is sealed with only `Car` and `Truck` as permitted subclasses. Both are covered by the two case arms. The code compiles and runs correctly. `describe(new Car())` → `"It's a car"`, `describe(new Truck())` → `"It's a truck"`. Output: `It's a car` then `It's a truck`.
