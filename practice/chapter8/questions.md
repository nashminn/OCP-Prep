# Chapter 8: Lambdas and Functional Interfaces — Practice Questions

---

**1.** Which of the following are valid lambda expressions? (Choose all that apply.)

A. `() -> {}`

B. `x -> x * 2`

C. `(int x, y) -> x + y`

D. `(x, y) -> x + y`

E. `(String s) -> { return s.length(); }`

F. `String s -> s.length()`

---

**2.** What is the output of the following code?

```java
import java.util.function.*;

public class LambdaTest {
    public static void main(String[] args) {
        Predicate<String> p = s -> s.length() > 3;
        System.out.println(p.test("Hi"));
        System.out.println(p.test("Hello"));
    }
}
```

A. `true` then `false`

B. `false` then `true`

C. `true` then `true`

D. Does not compile.

E. Throws a `NullPointerException` at runtime.

---

**3.** Which of the following correctly describes a functional interface?

A. An interface with no methods.

B. An interface with exactly one abstract method.

C. An interface with exactly one method total (including default and static).

D. An interface annotated with `@FunctionalInterface`.

E. An interface that extends `java.util.function.Function`.

---

**4.** What happens when you compile the following?

```java
@FunctionalInterface
interface Transformer {
    String transform(String s);
    String reverse(String s);
}
```

A. Compiles successfully; `@FunctionalInterface` is just a hint.

B. Compile error because `@FunctionalInterface` requires exactly one abstract method.

C. Compile error because interfaces cannot declare `String` return types.

D. Compiles, but only the first method is treated as the functional method.

E. Runtime error.

---

**5.** What is the output of the following code?

```java
import java.util.function.*;

public class Demo {
    public static void main(String[] args) {
        Function<Integer, Integer> doubler = x -> x * 2;
        Function<Integer, Integer> addTen = x -> x + 10;
        Function<Integer, Integer> combined = doubler.compose(addTen);
        System.out.println(combined.apply(5));
    }
}
```

A. `20`

B. `30`

C. `25`

D. `40`

E. Does not compile.

---

**6.** Which built-in functional interface from `java.util.function` has the method signature `T get()`?

A. `Consumer<T>`

B. `Supplier<T>`

C. `Function<T,T>`

D. `UnaryOperator<T>`

E. `Producer<T>`

---

**7.** What is the output of the following code?

```java
import java.util.function.*;

public class EffFinal {
    public static void main(String[] args) {
        int x = 10;
        Supplier<Integer> s = () -> x + 5;
        System.out.println(s.get());
    }
}
```

A. `10`

B. `15`

C. `5`

D. Does not compile.

E. Throws a runtime exception.

---

**8.** Which of the following will cause a compile error? (Choose all that apply.)

```java
public class VarTest {
    int instanceVar = 5;

    void method() {
        int localVar = 10;
        // Lambdas below:
        Runnable r1 = () -> System.out.println(instanceVar);         // A
        Runnable r2 = () -> instanceVar = 20;                        // B
        Runnable r3 = () -> System.out.println(localVar);            // C
        Runnable r4 = () -> localVar = 20;                           // D
        int localVar2 = 5;
        localVar2 = 6;
        Runnable r5 = () -> System.out.println(localVar2);           // E
    }
}
```

A. Line marked A

B. Line marked B

C. Line marked C

D. Line marked D

E. Line marked E

---

**9.** What is the result of the following code?

```java
import java.util.function.*;

public class Chaining {
    public static void main(String[] args) {
        Consumer<String> upper = s -> System.out.print(s.toUpperCase());
        Consumer<String> lower = s -> System.out.print(s.toLowerCase());
        Consumer<String> both = upper.andThen(lower);
        both.accept("Hello");
    }
}
```

A. `HELLOhello`

B. `helloHELLO`

C. `HELLO`

D. `hello`

E. Does not compile.

---

**10.** Which of the following is a valid method reference for a static method? Assume the class `Math` and method `abs` exist.

A. `Math::abs()`

B. `Math::abs`

C. `new Math::abs`

D. `Math.abs::`

E. `::Math.abs`

---

**11.** What is the output of the following?

```java
import java.util.function.*;

public class PredicateCompose {
    public static void main(String[] args) {
        Predicate<Integer> isEven = n -> n % 2 == 0;
        Predicate<Integer> isPositive = n -> n > 0;
        Predicate<Integer> isEvenAndPositive = isEven.and(isPositive);
        System.out.println(isEvenAndPositive.test(-4));
        System.out.println(isEvenAndPositive.test(4));
    }
}
```

A. `true` then `true`

B. `false` then `true`

C. `true` then `false`

D. `false` then `false`

E. Does not compile.

---

**12.** Which of the following interfaces has a method `boolean test(T t, U u)`?

A. `Predicate<T>`

B. `BiPredicate<T,U>`

C. `BiFunction<T,U,Boolean>`

D. `Function<T,Boolean>`

E. `BinaryOperator<T>`

---

**13.** What is the output of the following code?

```java
import java.util.function.*;

public class AndThenCompose {
    public static void main(String[] args) {
        Function<Integer, Integer> times2 = x -> x * 2;
        Function<Integer, Integer> plus3 = x -> x + 3;
        System.out.println(times2.andThen(plus3).apply(4));
        System.out.println(times2.compose(plus3).apply(4));
    }
}
```

A. `11` then `14`

B. `14` then `11`

C. `11` then `11`

D. `14` then `14`

E. Does not compile.

---

**14.** What is the method name that must be implemented for `Supplier<T>`?

A. `supply()`

B. `provide()`

C. `get()`

D. `produce()`

E. `fetch()`

---

**15.** Which of the following correctly uses an unbound instance method reference?

```java
import java.util.function.*;

public class Unbound {
    public static void main(String[] args) {
        // Which line correctly creates a Function<String, Integer> using
        // an unbound method reference to String's length() method?
    }
}
```

A. `Function<String, Integer> f = String.length::get;`

B. `Function<String, Integer> f = String::length;`

C. `Function<String, Integer> f = "hello"::length;`

D. `Function<String, Integer> f = s -> String.length();`

E. `Function<String, Integer> f = String::new;`

---

**16.** What is the result of the following?

```java
import java.util.function.*;

public class NegateTest {
    public static void main(String[] args) {
        Predicate<String> isBlank = String::isBlank;
        Predicate<String> notBlank = isBlank.negate();
        System.out.println(notBlank.test("  "));
        System.out.println(notBlank.test("hi"));
    }
}
```

A. `true` then `false`

B. `false` then `true`

C. `false` then `false`

D. `true` then `true`

E. Does not compile.

---

**17.** Which of the following statements about `BinaryOperator<T>` are correct? (Choose all that apply.)

A. It extends `BiFunction<T,T,T>`.

B. Its functional method is `T apply(T t1, T t2)`.

C. It is equivalent to `Function<T,T>`.

D. It can only be used with numeric types.

E. It is in the `java.util.function` package.

---

**18.** What does the following code print?

```java
import java.util.function.*;

public class BiTest {
    public static void main(String[] args) {
        BiFunction<String, Integer, String> repeat = (s, n) -> s.repeat(n);
        System.out.println(repeat.apply("ab", 3));
    }
}
```

A. `ab`

B. `ababab`

C. `3`

D. `abababababab`

E. Does not compile.

---

**19.** Which of the following lambda expressions will NOT compile? (Choose all that apply.)

A.
```java
Runnable r = () -> System.out.println("Hi");
```

B.
```java
Runnable r = () -> { System.out.println("Hi"); };
```

C.
```java
Runnable r = () -> { return; };
```

D.
```java
Runnable r = () -> return;
```

E.
```java
Supplier<Integer> s = () -> { 42; };
```

F.
```java
Supplier<Integer> s = () -> 42;
```

---

**20.** What is the output of the following?

```java
import java.util.function.*;

public class ConstructorRef {
    static class Point {
        int x, y;
        Point(int x, int y) { this.x = x; this.y = y; }
        public String toString() { return "(" + x + "," + y + ")"; }
    }
    public static void main(String[] args) {
        BiFunction<Integer, Integer, Point> factory = Point::new;
        System.out.println(factory.apply(3, 7));
    }
}
```

A. `Point@...` (default Object toString)

B. `(3,7)`

C. `(7,3)`

D. Does not compile.

E. Throws a runtime exception.

---

**21.** Which of the following statements about variables captured by lambdas are true? (Choose all that apply.)

A. Local variables used in lambdas must be explicitly declared `final`.

B. Local variables used in lambdas must be effectively final.

C. Instance variables can be freely read and modified inside a lambda.

D. A lambda parameter can have the same name as a local variable already in scope.

E. Static variables can be freely read and modified inside a lambda.

---

**22.** What is the output of the following?

```java
import java.util.function.*;

public class PrimFI {
    public static void main(String[] args) {
        IntSupplier is = () -> 42;
        System.out.println(is.getAsInt());
    }
}
```

A. `42`

B. `42.0`

C. Does not compile because `IntSupplier` is not in `java.util.function`.

D. Does not compile because `getAsInt()` is not the correct method name.

E. Throws `ClassCastException`.

---

**23.** Which interface would you use for a lambda that takes a `double` and returns nothing?

A. `DoubleSupplier`

B. `DoubleFunction<Void>`

C. `DoubleConsumer`

D. `ToDoubleFunction<Double>`

E. `DoubleUnaryOperator`

---

**24.** What is the result of the following code?

```java
import java.util.function.*;

public class ShortCircuit {
    static boolean check(String s) {
        System.out.print("check ");
        return s.isEmpty();
    }

    public static void main(String[] args) {
        Predicate<String> p = s -> check(s);
        Predicate<String> alwaysFalse = s -> false;
        Predicate<String> combined = alwaysFalse.and(p);
        combined.test("");
    }
}
```

A. `check ` is printed.

B. Nothing is printed.

C. `false` is printed.

D. Does not compile.

E. Throws a runtime exception.

---

**25.** Which of the following method references is a **bound instance** method reference?

A. `String::toUpperCase`

B. `System.out::println`

C. `Integer::parseInt`

D. `ArrayList::new`

E. `Objects::isNull`

---

**26.** What is the output of the following?

```java
import java.util.function.*;

public class UnaryOp {
    public static void main(String[] args) {
        UnaryOperator<String> shout = s -> s.toUpperCase() + "!";
        System.out.println(shout.apply("hello"));
    }
}
```

A. `hello!`

B. `HELLO!`

C. `HELLO`

D. Does not compile because `UnaryOperator` requires numeric types.

E. Does not compile because `UnaryOperator<String>` is invalid.

---

**27.** Which of the following will cause a compile error? (Choose all that apply.)

```java
@FunctionalInterface
interface A {
    void go();
}                                                    // Interface A

@FunctionalInterface
interface B {
    void go();
    default void stop() {}
}                                                    // Interface B

@FunctionalInterface
interface C {
    void go();
    static void reset() {}
}                                                    // Interface C

@FunctionalInterface
interface D {
    boolean equals(Object obj);
}                                                    // Interface D

@FunctionalInterface
interface E {
}                                                    // Interface E
```

A. Interface A

B. Interface B

C. Interface C

D. Interface D

E. Interface E

---

**28.** What is the result of the following code?

```java
import java.util.function.*;

public class ToInt {
    public static void main(String[] args) {
        ToIntFunction<String> len = String::length;
        System.out.println(len.applyAsInt("lambda"));
    }
}
```

A. `6`

B. `7`

C. `lambda`

D. Does not compile.

E. Throws a runtime exception.

---

**29.** Which of the following are valid ways to write a `Predicate<String>` that checks if a string starts with "A"? (Choose all that apply.)

A. `Predicate<String> p = s -> s.startsWith("A");`

B. `Predicate<String> p = (String s) -> { return s.startsWith("A"); };`

C. `Predicate<String> p = (s) -> s.startsWith("A");`

D. `Predicate<String> p = s -> { return s.startsWith("A") };`

E. `Predicate<String> p = String::startsWith;`

---

**30.** What is the output?

```java
import java.util.function.*;

public class OrTest {
    public static void main(String[] args) {
        Predicate<Integer> lt5 = n -> n < 5;
        Predicate<Integer> gt10 = n -> n > 10;
        Predicate<Integer> either = lt5.or(gt10);
        System.out.println(either.test(3));
        System.out.println(either.test(7));
        System.out.println(either.test(15));
    }
}
```

A. `true`, `false`, `true`

B. `true`, `true`, `true`

C. `false`, `false`, `true`

D. `true`, `false`, `false`

E. Does not compile.

---

**31.** What is the output of the following code?

```java
import java.util.function.*;

public class BiConsumerTest {
    public static void main(String[] args) {
        BiConsumer<String, Integer> printer =
            (s, n) -> System.out.println(s + n);
        printer.accept("Count: ", 5);
    }
}
```

A. `Count: 5`

B. `Count:5`

C. `5Count: `

D. Does not compile.

E. Throws a runtime exception.

---

**32.** Which of the following statements about `Predicate.not()` are correct? (Choose all that apply.)

A. It is a static method on `Predicate`.

B. It returns a `Predicate` that is the logical negation of the given predicate.

C. It was introduced in Java 11.

D. It can only be used with method references.

E. `Predicate.not(String::isBlank)` is equivalent to `((Predicate<String>) String::isBlank).negate()`.

---

**33.** What is the result of the following code?

```java
import java.util.function.*;

public class EffFinalBug {
    public static void main(String[] args) {
        int count = 0;
        Runnable r = () -> {
            count++;                     // line A
            System.out.println(count);
        };
        r.run();
    }
}
```

A. Prints `1`.

B. Prints `0`.

C. Does not compile due to line A.

D. Throws `IllegalStateException` at runtime.

E. Does not compile because `Runnable` is not a functional interface.

---

**34.** Which of the following correctly maps each method reference type to its description? (Choose all that apply.)

A. `Integer::parseInt` is a static method reference.

B. `"hello"::toUpperCase` is a bound instance method reference.

C. `String::toLowerCase` is an unbound instance method reference.

D. `ArrayList::new` is a constructor reference.

E. `System.out::println` is a static method reference.

---

**35.** What is the output of the following?

```java
import java.util.function.*;

public class ShadowTest {
    static int x = 100;

    public static void main(String[] args) {
        int x = 50;
        Supplier<Integer> s = () -> x;
        System.out.println(s.get());
    }
}
```

A. `100`

B. `50`

C. Does not compile because `x` shadows the static field.

D. Does not compile because `x` is already in scope and cannot be used in the lambda.

E. Throws a runtime exception.

---

**36.** What is the output?

```java
import java.util.function.*;

public class AndThenConsumer {
    public static void main(String[] args) {
        Consumer<Integer> print = n -> System.out.print(n + " ");
        Consumer<Integer> doubled = n -> System.out.print((n * 2) + " ");
        Consumer<Integer> both = print.andThen(doubled);
        both.accept(5);
    }
}
```

A. `5 `

B. `10 `

C. `5 10 `

D. `10 5 `

E. Does not compile.

---

**37.** Which of the following is the correct functional method for `BiPredicate<T,U>`?

A. `boolean test(T t)`

B. `boolean apply(T t, U u)`

C. `boolean test(T t, U u)`

D. `T apply(T t, U u)`

E. `void accept(T t, U u)`

---

**38.** What happens when the following code is compiled and run?

```java
import java.util.function.*;

public class BlockReturn {
    public static void main(String[] args) {
        Function<Integer, Integer> f = x -> {
            x * 2;
        };
        System.out.println(f.apply(5));
    }
}
```

A. Prints `10`.

B. Prints `5`.

C. Compile error: missing return statement.

D. Compile error: block lambda requires braces.

E. Throws a runtime exception.

---

**39.** Which of the following best describes the difference between `compose()` and `andThen()` on `Function`?

A. They are identical; both apply the calling function first.

B. `compose()` applies the argument function first; `andThen()` applies the calling function first.

C. `compose()` applies the calling function first; `andThen()` applies the argument function first.

D. `compose()` is for `Consumer`; `andThen()` is for `Function`.

E. `andThen()` and `compose()` only work with `UnaryOperator`.

---

**40.** Which of the following primitive functional interfaces has the method `int applyAsInt(int left, int right)`?

A. `IntUnaryOperator`

B. `IntBinaryOperator`

C. `BinaryOperator<Integer>`

D. `ToIntBiFunction<Integer, Integer>`

E. `IntFunction<Integer>`

---

**41.** What is the output of the following code?

```java
import java.util.function.*;

public class LambdaScope {
    String name = "Outer";

    void test() {
        String name = "Local";
        Supplier<String> s = () -> name;
        System.out.println(s.get());
    }

    public static void main(String[] args) {
        new LambdaScope().test();
    }
}
```

A. `Outer`

B. `Local`

C. Does not compile because `name` is ambiguous.

D. Does not compile because a lambda parameter cannot shadow a local variable.

E. Throws a runtime exception.

---

**42.** Which of the following statements about `ObjIntConsumer<T>` are true? (Choose all that apply.)

A. Its functional method is `void accept(T t, int value)`.

B. It extends `BiConsumer<T, Integer>`.

C. It is in the `java.util.function` package.

D. It avoids boxing of the `int` parameter.

E. It can be replaced by `Consumer<T>` with no behavioral difference.

---

**43.** What is the output of the following?

```java
import java.util.function.*;

public class MethodRefType {
    static String shout(String s) { return s.toUpperCase(); }

    public static void main(String[] args) {
        UnaryOperator<String> op = MethodRefType::shout;
        System.out.println(op.apply("hello"));
    }
}
```

A. `hello`

B. `HELLO`

C. Does not compile because `MethodRefType::shout` returns `String`, not the same type.

D. Does not compile because static methods cannot be used as method references for `UnaryOperator`.

E. Throws a runtime exception.

---

**44.** Given the following code, which line(s) cause a compile error? (Choose all that apply.)

```java
import java.util.function.*;

public class VarRedeclare {
    public static void main(String[] args) {
        int score = 10;
        Predicate<Integer> p = score -> score > 5;       // line 1
        Function<Integer, Integer> f = x -> {
            int x = 5;                                    // line 2
            return x * 2;
        };
    }
}
```

A. Line 1 only.

B. Line 2 only.

C. Both line 1 and line 2.

D. Neither line; both compile.

E. Only line 1 would cause an error if `score` were `final`.

---

**45.** What does the following print?

```java
import java.util.function.*;

public class PredicateNot {
    public static void main(String[] args) {
        Predicate<String> notEmpty = Predicate.not(String::isEmpty);
        System.out.println(notEmpty.test(""));
        System.out.println(notEmpty.test("Java"));
    }
}
```

A. `true` then `true`

B. `false` then `true`

C. `true` then `false`

D. `false` then `false`

E. Does not compile.

---

**46.** Which of the following functional interfaces would be most appropriate for a lambda that accepts a `String` and returns an `int` without boxing?

A. `Function<String, Integer>`

B. `ToIntFunction<String>`

C. `IntFunction<String>`

D. `IntUnaryOperator`

E. `UnaryOperator<String>`

---

**47.** What is the result of the following?

```java
import java.util.function.*;

public class MultiRef {
    public static void main(String[] args) {
        Function<String, String> f1 = String::trim;
        Function<String, String> f2 = String::toUpperCase;
        Function<String, String> pipeline = f1.andThen(f2);
        System.out.println(pipeline.apply("  hello  "));
    }
}
```

A. `  hello  `

B. `  HELLO  `

C. `HELLO`

D. `hello`

E. Does not compile.

---

**48.** Consider the following interface and class. What is the output?

```java
import java.util.function.*;

@FunctionalInterface
interface Greeter {
    String greet(String name);
    default String shout(String name) { return greet(name).toUpperCase(); }
}

public class FITest {
    public static void main(String[] args) {
        Greeter g = name -> "Hello, " + name;
        System.out.println(g.shout("World"));
    }
}
```

A. `Hello, World`

B. `HELLO, WORLD`

C. Does not compile because `Greeter` has two methods.

D. Does not compile because `@FunctionalInterface` cannot be used with `default` methods.

E. Throws a runtime exception.

---

**49.** Which of the following are true about lambda expressions in Java? (Choose all that apply.)

A. A lambda expression can be stored in a variable of any interface type.

B. A lambda's type is inferred from the target type (the functional interface it's assigned to).

C. Lambda expressions can throw checked exceptions if the functional interface's abstract method declares them.

D. A lambda expression creates a new class file at compile time (like anonymous inner classes).

E. A lambda can access `this`, which refers to the enclosing class instance.

---

**50.** What is the result of the following?

```java
import java.util.function.*;

public class FinalTrap {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3};
        Consumer<Integer> c = i -> arr[i] = arr[i] * 10;
        c.accept(0);
        System.out.println(arr[0]);
    }
}
```

A. `1`

B. `10`

C. Does not compile because `arr` is not effectively final.

D. Does not compile because arrays cannot be used inside lambdas.

E. Throws `ArrayIndexOutOfBoundsException`.
