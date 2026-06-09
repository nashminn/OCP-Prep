# Chapter 8: Lambdas and Functional Interfaces — Answers

---

**1. A, B, D, E**

Valid lambda forms:
- `() -> {}` — zero parameters, empty block body. Parens required for zero params. (A)
- `x -> x * 2` — single inferred-type param, no parens required. (B)
- `(int x, y) -> x + y` — INVALID: you cannot mix typed and untyped parameters. (C is wrong)
- `(x, y) -> x + y` — two params with inferred types. (D)
- `(String s) -> { return s.length(); }` — explicit type with block body and return. (E)
- `String s -> s.length()` — INVALID: single typed param still requires parens, e.g., `(String s) -> ...`. (F is wrong)

---

**2. B**

`p.test("Hi")` → `"Hi".length()` is `2`, which is not `> 3`, so `false`. `p.test("Hello")` → `"Hello".length()` is `5`, which is `> 3`, so `true`. Output is `false` then `true`.

---

**3. B**

A functional interface has exactly **one abstract method** (the SAM — Single Abstract Method). The `@FunctionalInterface` annotation is optional and just enforces this at compile time; an interface can still be functional without it. Default methods, static methods, and re-declared `Object` methods do not count toward the abstract method count.

---

**4. B**

`@FunctionalInterface` requires the annotated interface to have **exactly one abstract method**. `Transformer` declares two abstract methods (`transform` and `reverse`), so the compiler reports an error. Without the annotation, the interface would compile fine but could not be used as a lambda target.

---

**5. B**

`compose()` applies the **argument** function first, then the calling function. So `doubler.compose(addTen)` means: first apply `addTen(5)` = `15`, then apply `doubler(15)` = `30`. The answer is `30`.

---

**6. B**

`Supplier<T>` represents a provider of results with no input. Its functional method is `T get()`. `Consumer<T>` is `void accept(T t)`. `Function<T,T>` and `UnaryOperator<T>` both take a parameter. `Producer` does not exist in `java.util.function`.

---

**7. B**

`x` is `10` and is effectively final (never reassigned). The lambda captures it. `s.get()` returns `10 + 5 = 15`. This compiles and runs fine.

---

**8. D, E**

- A — reading `instanceVar` (an instance field) is fine. No error.
- B — **modifying** `instanceVar` (an instance field) is also fine; instance variables live on the heap, not the stack, so lambdas can read and write them.
- C — reading `localVar` is fine because it is never reassigned (effectively final).
- D — **compile error**: attempting to assign to the local variable `localVar` inside the lambda. Local variables must be effectively final.
- E — **compile error**: `localVar2` is assigned `5` and then reassigned to `6`, so it is NOT effectively final. The lambda at line E tries to capture it, which causes a compile error ("variable used in lambda expression should be final or effectively final").

---

**9. A**

`Consumer.andThen()` chains two consumers. The first consumer runs, then the second. `upper` prints `"Hello".toUpperCase()` = `HELLO`, then `lower` prints `"Hello".toLowerCase()` = `hello`. Combined output (no newlines, using `print`): `HELLOhello`.

---

**10. B**

The correct syntax for a static method reference is `ClassName::methodName` — no parentheses, no `new`, no dot-colon. `Math::abs` is the correct form.

---

**11. B**

`isEven.and(isPositive)` is short-circuit AND (like `&&`).
- `test(-4)`: `-4 % 2 == 0` is `true` (even), then `-4 > 0` is `false` (not positive) → result: `false`.
- `test(4)`: `4 % 2 == 0` is `true`, then `4 > 0` is `true` → result: `true`.
Output: `false` then `true`.

---

**12. B**

`BiPredicate<T,U>` has the functional method `boolean test(T t, U u)`. `Predicate<T>` only takes one parameter. `BiFunction<T,U,Boolean>` uses `apply()` not `test()`. `BinaryOperator<T>` extends `BiFunction<T,T,T>` with `apply()`.

---

**13. A**

- `times2.andThen(plus3).apply(4)`: apply `times2` first: `4*2=8`, then `plus3`: `8+3=11`.
- `times2.compose(plus3).apply(4)`: `compose` applies the argument (`plus3`) first: `4+3=7`, then `times2`: `7*2=14`.
Output: `11` then `14`.

---

**14. C**

The functional method of `Supplier<T>` is `T get()`. The primitive variants follow the pattern `getAsInt()`, `getAsLong()`, `getAsDouble()`, but the generic `Supplier<T>` uses `get()`.

---

**15. B**

`String::length` is an **unbound** instance method reference. The first (and only) parameter of the lambda becomes the receiver object. `Function<String, Integer>` maps a `String` to an `Integer`, so `String::length` fits: the `String` argument becomes the object on which `length()` is called.

Option C (`"hello"::length`) would be a *bound* instance method reference and would map to `Supplier<Integer>`, not `Function<String, Integer>`.

---

**16. B**

`String::isBlank` is an unbound method reference, assigned to `Predicate<String>`. `isBlank.negate()` inverts the result.
- `"  ".isBlank()` is `true`, negated → `false`.
- `"hi".isBlank()` is `false`, negated → `true`.
Output: `false` then `true`.

---

**17. A, B, E**

- A — `BinaryOperator<T>` extends `BiFunction<T,T,T>`. True.
- B — The inherited functional method from `BiFunction<T,T,T>` is `T apply(T t1, T t2)`. True.
- C — False. `Function<T,T>` takes one T and returns one T. `BinaryOperator<T>` takes two T values.
- D — False. `BinaryOperator<T>` works with any type, e.g., `BinaryOperator<String>`.
- E — True. It is in `java.util.function`.

---

**18. B**

`"ab".repeat(3)` returns `"ababab"`. The `BiFunction` applies the lambda with `s="ab"` and `n=3`, so the output is `ababab`.

---

**19. D, E**

- A — Valid: expression lambda, void return matches `Runnable.run()`.
- B — Valid: block lambda, no return needed since `run()` is void.
- C — Valid: explicit empty `return;` in a void block lambda is legal.
- D — **Invalid**: `return` cannot appear in an **expression lambda** (no braces). This is a syntax error.
- E — **Invalid**: `{ 42; }` is a block lambda with no `return` statement, but `Supplier<Integer>` requires returning an `Integer`. Missing `return` → compile error.
- F — Valid: expression lambda `() -> 42` implicitly returns `42` for `Supplier<Integer>`.

---

**20. B**

`Point::new` is a constructor reference. `BiFunction<Integer, Integer, Point>` maps two `Integer`s to a `Point`, matching the `Point(int x, int y)` constructor (autoboxing from `Integer` to `int`). The `toString()` override prints `(3,7)`.

---

**21. B, C, E**

- A — False. The variable does NOT need to be explicitly `final`; it just needs to be **effectively final** (never reassigned after initialization).
- B — True. Local variables must be effectively final.
- C — True. Instance variables are on the heap and can be read and modified inside lambdas.
- D — False. A lambda parameter **cannot** have the same name as a local variable already in scope — this would be a redeclaration and causes a compile error.
- E — True. Static variables can be freely read and modified inside a lambda.

---

**22. A**

`IntSupplier` is in `java.util.function` and its functional method is `int getAsInt()`. The lambda `() -> 42` returns `42`. Output is `42`.

---

**23. C**

`DoubleConsumer` has the functional method `void accept(double value)` — takes a `double`, returns nothing. `DoubleSupplier` returns a `double` without input. `ToDoubleFunction<T>` takes a `T` and returns a `double`. `DoubleFunction<R>` takes a `double` and returns an `R`. `DoubleUnaryOperator` takes and returns a `double`.

---

**24. B**

`Predicate.and()` is short-circuit AND. `alwaysFalse` returns `false` for every input. Since the first predicate in `alwaysFalse.and(p)` is `false`, the second predicate `p` (which calls `check`) is **never evaluated**. Nothing is printed.

---

**25. B**

`System.out::println` is a **bound** instance method reference — `System.out` is a specific object instance (`PrintStream`), and `println` is called on it. The receiver is already fixed ("bound").

- A — `String::toUpperCase` is **unbound** (any String instance).
- C — `Integer::parseInt` is a **static** method reference.
- D — `ArrayList::new` is a **constructor** reference.
- E — `Objects::isNull` is a **static** method reference.

---

**26. B**

`UnaryOperator<String>` extends `Function<String, String>` — it is not restricted to numeric types. The lambda converts the string to uppercase and appends `"!"`. Output: `HELLO!`.

---

**27. D, E**

- A — `@FunctionalInterface` with one abstract method `go()`. Valid.
- B — One abstract method `go()` + one `default` method `stop()`. Default methods don't count. Valid.
- C — One abstract method `go()` + one `static` method `reset()`. Static methods don't count. Valid.
- D — **Compile error**: `boolean equals(Object obj)` is a re-declaration of the `Object` method. It does NOT count as an abstract method. So interface D has zero abstract methods, which violates `@FunctionalInterface`.
- E — **Compile error**: Interface E has zero abstract methods. `@FunctionalInterface` requires exactly one.

---

**28. A**

`ToIntFunction<String>` has the functional method `int applyAsInt(T value)`. `String::length` is an unbound instance method reference. `"lambda".length()` is `6`. Output: `6`.

---

**29. A, B, C**

- A — Valid: single inferred-type param, expression body.
- B — Valid: explicit type with block body and `return`.
- C — Valid: single inferred-type param in parens, expression body.
- D — **Invalid**: missing semicolon after `s.startsWith("A")` inside the block (`return s.startsWith("A")` needs `;`). Specifically the missing `;` before `}` is the compile error.
- E — **Invalid**: `String::startsWith` takes a `String` argument (the prefix), so it would match `BiPredicate<String, String>`, not `Predicate<String>`. Compile error because the types do not match.

---

**30. A**

`lt5.or(gt10)` is short-circuit OR.
- `test(3)`: `3 < 5` is `true` → short-circuits, result `true`.
- `test(7)`: `7 < 5` is `false`, `7 > 10` is `false` → result `false`.
- `test(15)`: `15 < 5` is `false`, `15 > 10` is `true` → result `true`.
Output: `true`, `false`, `true`.

---

**31. A**

`"Count: " + 5` concatenates the string `"Count: "` with the integer `5`, producing `"Count: 5"` (with space before the number). Printed with `println`, the output is `Count: 5`.

---

**32. A, B, C, E**

- A — True. `Predicate.not()` is a static factory method on the `Predicate` interface.
- B — True. It wraps a predicate and returns its logical negation.
- C — True. `Predicate.not()` was introduced in Java 11.
- D — False. It works with any `Predicate`, not just method references.
- E — True. `Predicate.not(p)` is equivalent to `p.negate()`.

---

**33. C**

`count` is a local variable. Inside the lambda, `count++` attempts to modify it. Local variables captured by lambdas must be effectively final — they cannot be reassigned (and `++` is an assignment). This is a compile error at line A.

---

**34. A, B, C, D**

- A — `Integer::parseInt` is a static method on `Integer`. True.
- B — `"hello"::toUpperCase` uses a specific `String` instance. This is a bound instance method reference. True.
- C — `String::toLowerCase` uses the class name with an instance method; the first parameter becomes the receiver. True.
- D — `ArrayList::new` is a constructor reference. True.
- E — `System.out::println` is a **bound** instance method reference (bound to the `System.out` object), NOT a static method reference. False.

---

**35. B**

The local variable `int x = 50` shadows the static field `static int x = 100` within the `main` method. The lambda captures the local `x = 50` (which is effectively final). Output: `50`.

---

**36. C**

`Consumer.andThen()` runs both consumers in sequence: first `print` (prints `5 `), then `doubled` (prints `10 `). Combined output: `5 10 ` (with a trailing space from each print).

---

**37. C**

`BiPredicate<T,U>` has the functional method `boolean test(T t, U u)`. The `test` method name distinguishes predicates from functions (which use `apply`). `BiFunction` and `BiConsumer` use `apply` and `accept` respectively.

---

**38. C**

The lambda `x -> { x * 2; }` is a block lambda. Inside a block lambda, if the return type is non-void, there must be an explicit `return` statement. `x * 2;` is just a statement expression that discards its value — there is no `return`. The compiler reports: "missing return statement." Compile error.

---

**39. B**

`f.compose(g)` means: compute `g(x)` first, then apply `f`. So `f.compose(g).apply(x)` = `f(g(x))`.
`f.andThen(g)` means: compute `f(x)` first, then apply `g`. So `f.andThen(g).apply(x)` = `g(f(x))`.
The argument function runs first for `compose`; the calling function runs first for `andThen`.

---

**40. B**

`IntBinaryOperator` has the functional method `int applyAsInt(int left, int right)` — it takes two `int`s and returns an `int`. `IntUnaryOperator` takes only one `int`. `BinaryOperator<Integer>` boxes/unboxes. `IntFunction<Integer>` takes one `int` and returns a result. `ToIntBiFunction<T,U>` exists but uses object types, not primitives.

---

**41. B**

The local variable `String name = "Local"` in `test()` shadows the instance field `name`. The lambda captures the local variable `name` (which is effectively final — never reassigned). Output: `Local`. There is no compile error because the lambda captures the local variable, not the field. (If the lambda parameter were named `name`, that would be a redeclaration error — but here the lambda body simply uses the captured local.)

---

**42. A, C, D**

- A — True. The functional method is `void accept(T t, int value)`.
- B — False. `ObjIntConsumer<T>` does NOT extend `BiConsumer<T, Integer>`. It is a separate interface in `java.util.function` specifically to avoid boxing.
- C — True. It is in `java.util.function`.
- D — True. The `int` parameter is primitive, avoiding boxing overhead.
- E — False. `Consumer<T>` accepts only one argument. `ObjIntConsumer<T>` accepts two.

---

**43. B**

`MethodRefType::shout` is a static method reference. `UnaryOperator<String>` extends `Function<String, String>`, so its functional method is `String apply(String t)`. `shout` takes a `String` and returns a `String` — this matches. Applying `"hello"` calls `shout("hello")` = `"HELLO"`. Output: `HELLO`.

---

**44. C**

- Line 1: `score -> score > 5` — the lambda parameter is named `score`, but there is already a local variable `score` (of type `int`) in scope. A lambda parameter cannot redeclare a name already in the enclosing scope. **Compile error.**
- Line 2: `int x = 5` inside the lambda body — the lambda parameter is also named `x`. You cannot declare a local variable inside a lambda body with the same name as the lambda's own parameter. **Compile error.**

Both lines cause compile errors.

---

**45. B**

`Predicate.not(String::isEmpty)` creates a predicate that returns `true` when the string is NOT empty.
- `"".isEmpty()` is `true`, so `not` of that is `false`.
- `"Java".isEmpty()` is `false`, so `not` of that is `true`.
Output: `false` then `true`.

---

**46. B**

`ToIntFunction<String>` has the functional method `int applyAsInt(String value)` — it takes a `String` and returns a primitive `int` (no boxing). `Function<String, Integer>` boxes the `int` to `Integer`. `IntFunction<String>` goes the other way (takes `int`, returns `String`). `IntUnaryOperator` takes and returns `int`. `UnaryOperator<String>` takes and returns `String`.

---

**47. C**

`f1.andThen(f2)` runs `f1` (trim) first, then `f2` (toUpperCase).
1. `"  hello  ".trim()` = `"hello"`
2. `"hello".toUpperCase()` = `"HELLO"`
Output: `HELLO`.

---

**48. B**

`Greeter` has exactly one abstract method (`greet`) and one default method (`shout`). Default methods do not prevent a functional interface, so `@FunctionalInterface` is valid. The lambda `name -> "Hello, " + name` implements `greet`. Calling `g.shout("World")` invokes the default method which calls `greet("World").toUpperCase()` = `"HELLO, WORLD"`. Output: `HELLO, WORLD`.

---

**49. B, C, E**

- A — False. A lambda can only be assigned to a **functional interface** type, not any interface type (e.g., not `Serializable` which has no abstract method, or a non-functional interface).
- B — True. The compiler infers the lambda's type from the target type (the functional interface context).
- C — True. If the functional interface's abstract method declares a checked exception, the lambda body may throw that checked exception.
- D — False. Unlike anonymous inner classes, lambda expressions do NOT generate separate `.class` files. They use `invokedynamic` at the bytecode level.
- E — True. Inside a lambda, `this` refers to the **enclosing class instance** (not the lambda itself, as there is no "lambda object" for `this` to refer to in the usual sense). This is a key difference from anonymous inner classes.

---

**50. B**

`arr` is a local variable of array type. The array reference itself is never reassigned (effectively final), so the lambda compiles fine — the compile-time check is on the **reference**, not the array contents. Inside the lambda, `arr[0] = arr[0] * 10` = `1 * 10 = 10`. After `c.accept(0)`, `arr[0]` is `10`. Output: `10`.

This is a classic trap: you cannot reassign `arr` to a new array inside the lambda, but you CAN mutate the array's elements.
