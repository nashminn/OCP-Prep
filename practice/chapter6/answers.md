# Chapter 6: Class Design — Answers

---

**1. B**

When you create a `new Dog()`, Java must first initialize the parent. The compiler inserts an implicit `super()` call at the top of `Dog()`. So `Animal`'s constructor runs first, printing `Animal constructor`, followed by `Dog`'s constructor printing `Dog constructor`. This is the fundamental rule of constructor chaining — parent always initializes before child.

---

**2. B, C, E, F**

Java only supports single inheritance for classes (B). Inheritance is transitive (C). All classes implicitly extend `Object` (E). A `final` class cannot be subclassed (F). A is wrong — you can never extend more than one class regardless of package. D is wrong — `final` and `abstract` are mutually exclusive; a class cannot be both.

---

**3. C**

This is the critical variable hiding vs. method overriding distinction. Method calls are polymorphic — `p.print()` dispatches to `Child.print()` because the runtime type is `Child`, printing `Child: 20`. But variable access is NOT polymorphic — `p.x` uses the declared reference type `Parent`, returning `10`. Output: `Child: 20` then `10`.

---

**4. A, D, E**

A is correct — a default no-arg constructor is inserted only when no constructors are defined. B is wrong — once you define any constructor, no default is inserted. C is wrong — `this()` and `super()` are mutually exclusive in a constructor; both cannot appear. D is correct — `this()` must be first. E is correct — when no `this()` or `super()` is present, the compiler inserts `super()` as the first call.

---

**5. C**

`Sub()` has no explicit `super()`, so the compiler inserts `super()` → calls `Base()`. Inside `Base()`, `this(10)` is the first statement, so `Base(int)` runs first, printing `Base int: 10`. Then control returns to `Base()`, printing `Base no-arg`. Then `Sub()` resumes, printing `Sub`. Full output: `Base int: 10` → `Base no-arg` → `Sub`.

---

**6. B**

`A` defines only `A(int x)`, so `A` has no no-argument constructor. When `B()` is compiled, the compiler tries to insert `super()` (calling `A()`), but that constructor does not exist. The compiler error is: "constructor A() is undefined". `B` must explicitly call `super(someInt)` or `A` must provide a no-arg constructor.

---

**7. A, D, E**

A is correct — covariant return types (subtypes of the parent return type) are allowed. D is correct — the method signature (name + parameter types in order) must match. E is correct — the overriding method may throw fewer checked exceptions or none at all. B is wrong — access can only be widened, not narrowed. C is wrong — you cannot throw a broader checked exception than the parent declares. F is wrong — `@Override` is optional; it is a compile-time check aid, not a requirement.

---

**8. C**

`Circle.getType()` declares return type `Object`. The parent `Shape.getType()` has return type `String`. Covariant return types require the child's return type to be the same as or a subtype of the parent's. `Object` is a supertype of `String`, not a subtype — so this is not a valid covariant return and the code does not compile.

---

**9. B**

Static methods and static fields are NOT polymorphic. They are resolved based on the reference type at compile time (this is method hiding, not overriding). `v` is declared as type `Vehicle`, so `v.type` accesses `Vehicle.type` ("Vehicle") and `v.describe()` calls `Vehicle.describe()`. Output: `Vehicle` then `I am a Vehicle`.

---

**10. B, C, F**

B is correct — a non-abstract class with an abstract method will not compile. C is correct — `abstract` classes cannot be instantiated with `new`. F is correct — a concrete subclass inherits all abstract methods and must implement them all or be declared abstract itself. A is wrong — an abstract class may have zero abstract methods. D is wrong — `abstract` and `final` are mutually exclusive. E is wrong — `abstract` methods cannot be `private`.

---

**11. B**

`private abstract` is illegal. An `abstract` method exists specifically to be overridden in a subclass. A `private` method is not visible to subclasses and therefore cannot be overridden. The two concepts are inherently contradictory. The compiler rejects this combination with an error.

---

**12. C**

Constructor chaining always goes up the hierarchy first. `new Child()` triggers `super()` → `Parent()`, which triggers `super()` → `Grandparent()`. Grandparent prints `GP`, then control returns and Parent prints `P`, then control returns and Child prints `C`. Output: `GP` → `P` → `C`.

---

**13. A**

Line 9 fails because `age` is `private` in `Animal`. Private members are NOT inherited — they are not visible in the subclass at all, even though they exist in the parent object. Line 10 compiles because `name` is `protected` and accessible in a subclass. Line 11 compiles because `getAge()` is package-private and accessible (same package) and returns the private field via the parent's own method. Only line 9 causes a compile error.

---

**14. C**

Fields are not polymorphic. `b.value` uses the reference type `Base`, accessing `Base.value = 5`. But `b.getValue()` dispatches polymorphically to the runtime type `Derived`, calling `Derived.getValue()` which returns `Derived.value = 10`. This is the classic variable-hiding trap: fields hide, methods override. Output: `5` then `10`.

---

**15. B**

The complete initialization order for `new Child()` where `Child extends Parent`: (1) Parent's static initializers (on first class load), (2) Child's static initializers (on first class load), (3) Parent's instance variable declarations (in source order), (4) Parent's instance initializers (in source order), (5) Parent's constructor body, (6) Child's instance variable declarations (in source order), (7) Child's instance initializers (in source order), (8) Child's constructor body. Option E omits the static phase and option B correctly captures the full order.

---

**16. B**

`abstract` and `final` cannot be applied together to a class. `abstract` means "must be subclassed to be used," while `final` means "cannot be subclassed." These are mutually exclusive by definition, and the compiler rejects this combination.

---

**17. C**

The static initializer runs once when the class is loaded, setting `count = 10`. Each `new Counter()` call runs the instance initializer (`count += 5`) and then the constructor (`count += 1`). After `c1`: `10 + 5 + 1 = 16`. After `c2`: `16 + 5 + 1 = 22`. Output: `22`.

---

**18. A, B, C, E**

A is valid — widening access from `protected` to `public` is allowed. B is valid — `Integer` is a subtype of `Number` (covariant return). C is valid — the overriding method may throw fewer or no checked exceptions. E is valid — `RuntimeException` is unchecked, so declaring it does not violate the throws contract. D is invalid — `private` narrows access (compile error). F requires analysis: `IOException` is a checked exception. The parent declares `throws Exception`. `IOException extends Exception`, so `IOException` is a subset of `Exception` — this IS allowed because the overriding method cannot throw broader checked exceptions, but can throw narrower ones. F is actually valid too.

Wait — re-reading carefully: the rule is the overriding method cannot throw new or broader checked exceptions. `IOException` is more specific than `Exception`, so F is also valid. Correct answers: A, B, C, E, F.

---

**19. B**

Package-private (no modifier) is narrower than `protected`. In the access hierarchy: `private < package-private < protected < public`. Overriding methods can only widen access, not narrow it. `Dog.eat()` has no access modifier (package-private), which is narrower than `protected` in `Animal`. The compiler rejects this with an error.

---

**20. B**

Calling an `abstract` method from a non-abstract method in the same abstract class is perfectly legal. The abstract class's `describe()` calls `area()`, which at runtime is dispatched to `Circle.area()` = `3.14 * 2.0 * 2.0 = 12.56`. The assignment `Shape s = new Circle(2.0)` is valid upcasting. Output: `Area: 12.56`.

---

**21. A, B, E, F**

A is correct — `this()` calls another constructor in the same class. B is correct — `super()` always chains to the immediate parent, never a grandparent directly. E is correct — `this()` must be the first statement. F is correct — when a constructor explicitly calls `this()`, the compiler does NOT insert an implicit `super()` (because `this()` already chains to another constructor which will eventually call `super()`). C is wrong — you cannot skip levels with `this()`. D is wrong — `this()` and `super()` cannot both appear in the same constructor.

---

**22. A**

`new B()` calls `B()`. `B()` explicitly calls `super(3)` → `A(int x)` with `x=3`. `A(int x)` does NOT call `this(5)` — that is only in `A()`. `A(3)` prints `A(3)`. Then `B()` resumes and prints `B()`. Output: `A(3)` then `B()`. The no-arg `A()` is never invoked.

---

**23. B**

Only Options A and D are truly immutable. Option A: final class, final field, no setter, primitive field — fully immutable. Option B: NOT immutable because the class is not `final` — a subclass could add mutable state and break immutability guarantees. Option C: NOT immutable — the `List<String>` field is not defensively copied on construction, and the getter returns the original mutable reference. A caller can modify the list externally. Option D: IS immutable — defensive copy on construction, unmodifiable view returned from getter.

---

**24. C**

When `new Child()` is called, `super()` is implicitly called first, running `Parent()`. Inside `Parent()`, `printName()` is called. Because `printName()` is an instance method and the runtime type is `Child`, `Child.printName()` runs. At this point, `Child.name` has not been assigned yet (instance variable initialization for `Child` happens after the `super()` call completes). So `Child.name` is `null`. Output: `Child: null`. This is one of the most dangerous patterns in OOP.

---

**25. C**

`Constructor()` calls `Constructor(int)`, which calls `Constructor(int, int)`, which calls `Constructor()` — a cycle. The compiler detects constructor chaining cycles at compile time and refuses to compile the code. This is not a runtime `StackOverflowError`; it is a compile-time error.

---

**26. A, B, E**

A is correct — same-package access is always allowed for `protected` members. B is correct — a subclass in a different package can access a protected member through a reference of its own type (or a subtype). C is wrong — accessing a protected member through a parent-type reference from outside the package is not allowed, even from a subclass. D is wrong — `public` is more accessible than `protected`. E is correct — inheritance grants access to protected members regardless of package.

---

**27. D**

The code does not compile. `Animal a = new Dog()` gives `a` the static type `Animal`. `a.fetch()` is a compile error because `fetch()` is not defined in `Animal`. The compiler resolves method calls based on the static (declared) type of the reference. Note that `((Dog) a).fetch()` would compile and run, but the third line `a.fetch()` prevents compilation. The answer is D, not B.

---

**28. B, C, D**

`private abstract` is illegal — private methods cannot be overridden, making abstract meaningless. `static abstract` is illegal — static methods belong to the class and cannot be polymorphically overridden (only hidden), so abstract makes no sense. `final abstract` is illegal — final prevents overriding, which directly contradicts abstract's requirement to be overridden. A (`public abstract`) is legal in an abstract class. E (`protected abstract`) is legal in an abstract class. F (`public final`) is legal and common.

---

**29. C**

When `new Sub()` is called, `super()` runs first (implicitly from `Sub()`). Inside `Base()`, `getValue()` is called. Because the runtime type is `Sub`, `Sub.getValue()` is called polymorphically. However, `Sub.value` has not yet been initialized — instance variable initialization for `Sub` happens after `super()` completes. Instance variable `int value` defaults to `0` before declaration assignment runs. So `getValue()` returns `0`. Output: `Base: 0`.

---

**30. B**

When a parent constructor calls an overridden method, Java uses dynamic dispatch — the child's overridden version runs. However, because the child's constructor has not yet run (we are still in the parent constructor), the child's instance variables have their default values (0, null, false), not their declared initialized values. This is legal but dangerous code, and a common source of bugs.

---

**31. B**

The code compiles. `A()` calls `run()`, which is abstract — but since the runtime type is `B`, `B.run()` is invoked. However, `B.run()` accesses `msg`, which is a `String` instance field. At the time `A()` runs (invoked from `super()` at the start of `B()`), `B`'s instance variable initializer has NOT run yet. `msg` is `null` at that point. Output: `null`. This demonstrates that calling overridden methods from a constructor is legal but produces surprising results.

---

**32. A, B**

The `abstract` keyword must appear before the class name and after any access modifiers. Both `public abstract class Foo` and `abstract public class Foo` are valid — access modifiers and non-access modifiers like `abstract` can appear in any order relative to each other. C, D, and E place `abstract` after the `class` keyword or after the class name, which are invalid positions.

---

**33. A**

Static initializers run exactly once when the class is first loaded, in hierarchy order (parent then child). Instance initializers and constructors run on every instantiation. First `new Q()`: `P static`, `Q static` (class loading), then instance init: `P instance`, `P constructor`, `Q instance`, `Q constructor`. Second `new Q()`: static blocks do NOT re-run, so only: `P instance`, `P constructor`, `Q instance`, `Q constructor`. Full output: `P static` → `Q static` → `P instance` → `P constructor` → `Q instance` → `Q constructor` → `P instance` → `P constructor` → `Q instance` → `Q constructor`.

---

**34. B**

Both `Vehicle` and `Truck` have a `speed` field, but fields in a subclass shadow (not override) parent fields. The method `getSpeed()` is a true instance method override. `v.getSpeed()` dispatches polymorphically to `Truck.getSpeed()`, which returns `Truck`'s private `speed = 80`. The fact that `speed` is private in `Vehicle` does not prevent `Truck` from declaring its own `speed` field. Output: `80`.

---

**35. A, D, E**

A is correct — `final` methods cannot be overridden. D is correct — you can prevent subclassing by making the class `final`, but having all `final` methods does not require the class to be `final` (subclasses can still be created, they just can't override those methods). E is correct — `final` and `abstract` are mutually exclusive on methods, just like on classes. B is wrong — `final` static methods are valid. C is a bit of a trick: a `final` method in a subclass can still call `super.method()` if it wants to access the parent's implementation, but the statement is broadly true.

---

**36. B**

`C.print()` calls `super.print()` (which is `B.print()`), then prints `C`. `B.print()` calls `super.print()` (which is `A.print()`), then prints `B`. So the execution order is: `A.print()` → prints `A`, return to `B.print()` → prints `B`, return to `C.print()` → prints `C`. Output: `A` → `B` → `C`.

---

**37. C**

Abstract classes can and do have constructors. They are called indirectly via `super()` when a concrete subclass is instantiated. A is wrong — abstract classes have constructors (they just can't be called with `new` directly). B is wrong — there is no requirement for any particular constructor; any constructor structure is fine. D is wrong — the constructor IS called during object creation. E is wrong — abstract class constructors can have any access modifier including package-private or even private.

---

**38. A**

This compiles. Inner classes can extend their outer class (or any other class). There is no rule preventing it. The `Inner` class would inherit members of `Outer`. This is unusual but legal Java.

---

**39. B**

`new Cat()` calls `super()` → `Animal()`. Inside `Animal()`, first `System.out.println(sound)` — here `sound` is accessed on `this`, which has runtime type `Cat`. But `sound` is a field, not a method — fields use variable hiding. The `Animal()` constructor accesses the `Animal.sound` field, not `Cat.sound`. `Animal.sound = "generic"` has already been initialized (parent instance variables initialize before the parent constructor body runs). Then `makeSound()` is called — this IS polymorphic. `Cat.makeSound()` runs, but `Cat.sound` has not been initialized yet (we are still in the parent constructor). `Cat.sound` is `null`. Output: `generic` then `null`.

---

**40. A, C, D, E**

A is valid — checking if a `Dog` reference is a `Dog` instance. C is valid — `o` holds a `String` and the check works. D is valid — it evaluates to `false` at runtime (the `Animal` object is not a `Dog`), but it compiles fine. E is valid — `instanceof` with a null reference always returns `false`, never throws. B requires examination: `Dog` and `Cat` are sibling classes with no relationship, so the compiler knows a `Dog` variable can never hold a `Cat` — this is a compile error because the types are unrelated and the check is always false. Answer: A, C, D, E.

---

**41. B**

Covariant return types only work for reference types (objects), not primitives. `long` and `int` are primitives, and `long` is not a subtype of `int` in the object sense. Because the return type differs and `long` is not a covariant return of `int`, this is not a valid override. However, since the parameter list is the same (`int a, int b`), the compiler tries to treat it as an override — and since `long` is not a valid covariant return for `int`, it fails to compile.

---

**42. A**

Snippet 1: `B extends A` but does not implement abstract method `m()`, and `B` is not declared abstract. This is a compile error. Snippet 2: `C extends A` but `C` is itself abstract, so it is not required to implement `m()`. This compiles. Snippet 3: `D extends A` and implements `m()` with a valid concrete implementation. This compiles. Only Snippet 1 causes a compile error.

---

**43. C**

When `Beta.x` is accessed, Java loads `Beta`. Before loading `Beta`, Java must load `Alpha` (because `Beta extends Alpha`). Loading `Alpha` runs its static initializer: sets `x = 1`, prints `Alpha static: 1`. Then loading `Beta` runs its static initializer: sets `x = 2`, prints `Beta static: 2`. Then `main` prints `Beta.x` which is `2`. Output: `Alpha static: 1` → `Beta static: 2` → `main: 2`.

---

**44. A, C, D, E**

A is correct — variable access is determined by the reference (declared) type at compile time, not the runtime type. C and D are correct — both static and instance variables can be hidden in subclasses. E is correct — `super.fieldName` accesses the parent's version. B is wrong — variable hiding is explicitly NOT polymorphic; that is the key distinction from method overriding. F is wrong — `@Override` only applies to methods, not fields.

---

**45. B**

Method overriding is polymorphic. Both `f` (type `Fruit`) and `a` (type `Apple`) have runtime type `GoldenApple`. `GoldenApple.color()` overrides all the way down and returns `"yellow"` for both calls. Polymorphism dispatches to the most specific runtime type's implementation regardless of the reference type. Output: `yellow yellow`.

---

**46. B**

`final` methods cannot be overridden in any subclass. `Child.stop()` attempts to override `Parent.stop()`, which is `final`. The compiler produces an error: "stop() in Child cannot override stop() in Parent; overridden method is final."

---

**47. B**

`ImmutableWrapper` holds a `final` reference to a `Node`, but the `Node` object itself is mutable (its `val` field has no access restriction). The `final` keyword on `node` means the reference cannot be reassigned to point to a different `Node` object — it does not make the `Node`'s fields immutable. `w.getNode()` returns the actual `Node` reference, allowing `val` to be changed. Output: `99`. This illustrates why immutability requires defensive copies for mutable object fields.

---

**48. B, D**

B is correct — `super.method()` always refers to the immediate parent class's version, not any more distant ancestor. D is correct — `super.method()` can be called from any instance method in a subclass. A is wrong — `super.method()` can be used in instance methods, not just constructors. C is wrong — you cannot skip levels with `super`; `super` always refers to the direct parent. E is wrong — `super` cannot be used in a static context because `super` is an implicit reference to the parent portion of the current instance, and static methods have no `this` or `super`.

---

**49. C**

`m.show()` dispatches polymorphically to `Human.show()` (runtime type is `Human`). Inside `Human.show()`, `name` refers to `Human.name = "Human"`. `super.name` accesses `Mammal.name = "Mammal"` directly (fields are not polymorphic, and `super.name` explicitly targets the parent's field). Output: `Human` then `Mammal`.

---

**50. C**

Option A is NOT truly immutable — `Date` is a mutable object. The constructor stores the caller's `Date` reference directly (no defensive copy), so the caller can modify the `Date` after construction. The getter also returns the original mutable `Date` reference, allowing external mutation. Option B IS immutable — defensive copy is made in the constructor (`new Date(date.getTime())`), and the getter returns a new copy each time. The class is `final`. This is a properly immutable class. Option C IS immutable — instead of storing the mutable `Date` object, it stores the `long` timestamp (a primitive), which is inherently immutable. No defensive copy issue exists. Correct answers: B and C.

---
