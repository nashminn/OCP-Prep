# Chapter 11: Exceptions and Localization — Practice Questions

---

**1.** What is the output of the following code?

```java
public class Main {
    static String process() {
        StringBuilder sb = new StringBuilder();
        try {
            sb.append("A");
            throw new IllegalStateException();
        } catch (IllegalStateException e) {
            sb.append("B");
            return sb.toString();
        } finally {
            sb.append("C");
        }
    }

    public static void main(String[] args) {
        System.out.println(process());
    }
}
```

A. `AB`

B. `ABC`

C. `AC`

D. The code does not compile.

E. `A`

---

**2.** What is the output of the following code?

```java
public static int compute() {
    try {
        return 1;
    } finally {
        return 2;
    }
}

public static void main(String[] args) {
    System.out.println(compute());
}
```

A. `1`

B. `2`

C. The code does not compile because `finally` cannot contain a `return`.

D. The code throws an exception at runtime.

E. `1` followed by `2` on a new line.

---

**3.** Consider this shared method, called from each option below:

```java
static void risky() throws java.io.IOException, java.sql.SQLException {}
```

Which of the following multi-catch blocks, used to catch an exception thrown by `risky()`, compile without error? (Choose two.)

A. `catch (java.io.IOException | java.sql.SQLException e) { }`

B. `catch (java.io.FileNotFoundException | java.io.IOException e) { }`

C. `catch (NumberFormatException | ArithmeticException e) { }`

D. `catch (NumberFormatException | IllegalArgumentException e) { }`

E. `catch (Exception | RuntimeException e) { }`

---

**4.** What is the output of the following code?

```java
public class GiraffeException extends Exception {
    public GiraffeException(String message) {
        super(message);
    }
}

public class TallAnimal {
    public void check(int height) throws GiraffeException {
        if (height > 18) {
            throw new GiraffeException("Too tall: " + height);
        }
        System.out.println("OK: " + height);
    }

    public static void main(String[] args) {
        TallAnimal a = new TallAnimal();
        try {
            a.check(15);
            a.check(20);
            a.check(10);
        } catch (GiraffeException e) {
            System.out.println("Caught: " + e.getMessage());
        }
    }
}
```

A. `OK: 15` then `OK: 10`

B. `OK: 15` then `Caught: Too tall: 20`

C. `OK: 15` then `OK: 10` then `Caught: Too tall: 20`

D. The code does not compile because `GiraffeException` has no default constructor.

E. `Caught: Too tall: 20`

---

**5.** Given the following try-with-resources statement, in what order are the resources closed (assuming `Resource` is a custom `AutoCloseable` class)?

```java
try (Resource a = new Resource("a");
     Resource b = new Resource("b");
     Resource c = new Resource("c")) {
    System.out.println("body");
}
```

A. `a, b, c`

B. `c, b, a`

C. `b, a, c`

D. The order is undefined at runtime.

E. Only `c` is closed; `a` and `b` are closed by the garbage collector.

---

**6.** What is the output of the following code?

```java
public class Animal {
    static class Lion implements AutoCloseable {
        private String name;
        Lion(String name) { this.name = name; }
        @Override
        public void close() {
            System.out.print("Close" + name + " ");
        }
    }

    public static void main(String[] args) {
        try (Lion a = new Lion("A"); Lion b = new Lion("B")) {
            System.out.print("Body ");
            throw new RuntimeException("Roar");
        } catch (RuntimeException e) {
            System.out.print("Caught:" + e.getMessage());
        }
    }
}
```

A. `Body CloseA CloseB Caught:Roar`

B. `Body CloseB CloseA Caught:Roar`

C. `CloseB CloseA Body Caught:Roar`

D. `Body Caught:Roar CloseB CloseA`

E. The code does not compile.

---

**7.** Which statement about the following interface is correct?

```java
interface AutoCloseable {
    void close() throws Exception;
}
```

A. A class implementing `AutoCloseable` must declare `close()` to throw `Exception`.

B. A class implementing `AutoCloseable` may declare `close()` without a `throws` clause.

C. A class implementing `AutoCloseable` cannot be used in a try-with-resources statement unless `close()` throws a checked exception.

D. `Closeable` and `AutoCloseable` are unrelated interfaces.

E. A class may not override `close()` to throw `IOException` because `IOException` is a checked exception.

---

**8.** What is the output of the following code?

```java
public class ResourceDemo {
    static class Tap implements AutoCloseable {
        public void close() throws Exception {
            throw new Exception("tap stuck");
        }
    }

    public static void main(String[] args) {
        try (Tap t = new Tap()) {
            throw new RuntimeException("leak");
        } catch (RuntimeException e) {
            System.out.println("Primary: " + e.getMessage());
            for (Throwable sup : e.getSuppressed()) {
                System.out.println("Suppressed: " + sup.getMessage());
            }
        } catch (Exception e) {
            System.out.println("Other: " + e.getMessage());
        }
    }
}
```

A. `Primary: leak` then `Suppressed: tap stuck`

B. `Other: tap stuck`

C. `Primary: tap stuck` then `Suppressed: leak`

D. The code does not compile because `close()` throws a checked `Exception`.

E. Both catch blocks execute.

---

**9.** What is the output of the following code?

```java
public class Cage implements AutoCloseable {
    private final String name;
    public Cage(String name) { this.name = name; }

    @Override
    public void close() {
        System.out.print("Close" + name + " ");
        throw new IllegalStateException("broken-" + name);
    }

    public static void main(String[] args) {
        try (Cage c1 = new Cage("1"); Cage c2 = new Cage("2")) {
            System.out.print("Body ");
        } catch (IllegalStateException e) {
            System.out.print("Caught:" + e.getMessage() + " ");
            for (Throwable t : e.getSuppressed()) {
                System.out.print("Sup:" + t.getMessage() + " ");
            }
        }
    }
}
```

A. `Body Close2 Close1 Caught:broken-2 Sup:broken-1`

B. `Body Close1 Close2 Caught:broken-1 Sup:broken-2`

C. `Body Close2 Close1 Caught:broken-1 Sup:broken-2`

D. `Body Close1 Close2 Caught:broken-2 Sup:broken-1`

E. The code does not compile.

---

**10.** Which of the following statements about checked and unchecked exceptions are true? (Choose all that apply.)

A. `IllegalStateException` is a checked exception.

B. `ConcurrentModificationException` is a subclass of `RuntimeException`.

C. `SQLException` must be declared in a method's `throws` clause or caught.

D. `StackOverflowError` is a checked exception that must be declared.

E. `ClassNotFoundException` is a checked exception.

F. `UnsupportedOperationException` does not need to be declared or caught.

---

**11.** What is the output of the following code?

```java
public class Main {
    static void inner() {
        throw new IllegalArgumentException("bad arg");
    }

    static void middle() {
        inner();
    }

    static void outer() {
        middle();
    }

    public static void main(String[] args) {
        try {
            outer();
        } catch (RuntimeException e) {
            System.out.println(e.getClass().getSimpleName() + ": " + e.getMessage());
        }
    }
}
```

A. `RuntimeException: bad arg`

B. `IllegalArgumentException: bad arg`

C. The code does not compile because `IllegalArgumentException` is not declared in `inner()`'s signature.

D. A `StackOverflowError` is thrown.

E. `IllegalArgumentException: null`

---

**12.** What is the output of the following code, assuming it is run WITHOUT the `-ea` flag?

```java
public class AssertDemo {
    public static void main(String[] args) {
        int x = 5;
        assert x > 10 : "x must be greater than 10";
        System.out.println("x = " + x);
    }
}
```

A. `x = 5`

B. An `AssertionError` is thrown with message `x must be greater than 10`.

C. The code does not compile.

D. Nothing is printed.

E. An `IllegalStateException` is thrown.

---

**13.** What is the output of the following code, assuming it is run WITH the `-ea` flag?

```java
public class AssertDemo {
    public static void main(String[] args) {
        int x = 5;
        assert x > 10 : "x must be greater than 10";
        System.out.println("x = " + x);
    }
}
```

A. `x = 5`

B. An `AssertionError` is thrown with message `x must be greater than 10`, and `x = 5` is not printed.

C. The code does not compile.

D. An `Exception` is thrown with message `x must be greater than 10`.

E. `x = 5` is printed, followed by an `AssertionError`.

---

**14.** What is the output of the following code?

```java
public class Frog {
    static String name;

    public void hop() {
        System.out.println(name.toLowerCase());
    }

    public static void main(String[] args) {
        new Frog().hop();
    }
}
```

A. `null`

B. The code does not compile.

C. `Exception in thread "main" java.lang.NullPointerException: Cannot invoke "String.toLowerCase()" because "Frog.name" is null`

D. An empty string is printed.

E. `Exception in thread "main" java.lang.NullPointerException` with no further detail.

---

**15.** Which of the following overriding declarations compile without error, given this superclass?

```java
class Aviary {
    void release() throws java.io.IOException {}
}
```

(Choose three.)

A. `class Zoo extends Aviary { void release() throws java.io.FileNotFoundException {} }`

B. `class Zoo extends Aviary { void release() throws java.lang.Exception {} }`

C. `class Zoo extends Aviary { void release() {} }`

D. `class Zoo extends Aviary { void release() throws java.sql.SQLException {} }`

E. `class Zoo extends Aviary { void release() throws java.lang.RuntimeException {} }`

---

**16.** What is the output of the following code?

```java
public class Validator {
    static class ValidationException extends RuntimeException {
        public ValidationException(String message, Throwable cause) {
            super(message, cause);
        }
    }

    static void parse(String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new ValidationException("Invalid input: " + input, e);
        }
    }

    public static void main(String[] args) {
        try {
            parse("abc");
        } catch (ValidationException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getCause().getClass().getSimpleName());
        }
    }
}
```

A. `Invalid input: abc` then `NumberFormatException`

B. `Invalid input: abc` then `null`

C. The code does not compile because `RuntimeException` has no constructor accepting `(String, Throwable)`.

D. `NumberFormatException` then `Invalid input: abc`

E. A `NumberFormatException` propagates uncaught.

---

**17.** What is the output of the following code?

```java
public class CatchOrder {
    public static void main(String[] args) {
        try {
            Object[] arr = new String[3];
            arr[0] = Integer.valueOf(5);
        } catch (NullPointerException e) {
            System.out.println("NPE");
        } catch (ArrayStoreException e) {
            System.out.println("ASE");
        } catch (RuntimeException e) {
            System.out.println("RE");
        }
    }
}
```

A. `NPE`

B. `ASE`

C. `RE`

D. The code does not compile because `ArrayStoreException` catch block is unreachable.

E. The code does not compile because `Object[] arr = new String[3]` is invalid.

---

**18.** Which statements about the following code are true? (Choose two.)

```java
1: public void process() {
2:    try {
3:       riskyOperation();
4:    } catch (java.io.IOException e) {
5:       System.out.println("io");
6:    } catch (java.io.FileNotFoundException e) {
7:       System.out.println("fnf");
8:    }
9: }
10: void riskyOperation() throws java.io.FileNotFoundException {}
```

A. The code compiles successfully.

B. Line 6 causes a compile error because `FileNotFoundException` is unreachable after line 4.

C. `FileNotFoundException` is a subclass of `IOException`.

D. The code compiles only if line 4 and line 6 are swapped.

E. `riskyOperation()` does not need a `throws` clause since `FileNotFoundException` is unchecked.

---

**19.** What is the output of the following code?

```java
public class ChainDemo {
    static class LowFuelException extends Exception {
        public LowFuelException(String msg) { super(msg); }
    }
    static class EngineFailureException extends LowFuelException {
        public EngineFailureException(String msg) { super(msg); }
    }

    static void drive(int fuel) throws LowFuelException {
        if (fuel == 0) {
            throw new EngineFailureException("engine dead");
        } else if (fuel < 10) {
            throw new LowFuelException("low fuel");
        }
        System.out.println("driving");
    }

    public static void main(String[] args) {
        try {
            drive(0);
        } catch (EngineFailureException e) {
            System.out.println("engine: " + e.getMessage());
        } catch (LowFuelException e) {
            System.out.println("fuel: " + e.getMessage());
        }
    }
}
```

A. `engine: engine dead`

B. `fuel: engine dead`

C. The code does not compile because `EngineFailureException` catch block is unreachable.

D. The code does not compile because `drive()` cannot throw `EngineFailureException`.

E. `fuel: low fuel`

---

**20.** What is the output of the following code?

```java
public class FinallyOverride {
    static String process() {
        StringBuilder sb = new StringBuilder();
        try {
            sb.append("try-");
            throw new RuntimeException("fail");
        } catch (RuntimeException e) {
            sb.append("catch-");
            throw new IllegalStateException("rethrown");
        } finally {
            sb.append("finally");
            return sb.toString();
        }
    }

    public static void main(String[] args) {
        System.out.println(process());
    }
}
```

A. `try-catch-finally`

B. The code throws `IllegalStateException: rethrown` at runtime.

C. `try-finally`

D. The code does not compile because `finally` cannot contain a `return` after a `throw` in `catch`.

E. The code throws `RuntimeException: fail` at runtime.

---

**21.** What is the output of the following code?

```java
public class NestedTry {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        try {
            try {
                sb.append("inner-try-");
                throw new RuntimeException("X");
            } finally {
                sb.append("inner-finally-");
            }
        } catch (RuntimeException e) {
            sb.append("outer-catch:" + e.getMessage());
        }
        System.out.println(sb);
    }
}
```

A. `inner-try-inner-finally-outer-catch:X`

B. `inner-try-outer-catch:X`

C. `inner-try-inner-finally-`

D. The code does not compile.

E. `outer-catch:X`

---

**22.** Which of the following are true regarding `assert` statements? (Choose all that apply.)

A. Assertions are enabled by default.

B. The `-ea` flag must be used to enable assertions at runtime.

C. `assert` throws `AssertionError`, which extends `Error`.

D. `assert` throws `AssertionException`, which extends `Exception`.

E. Using `assert` to validate arguments of a public API method is discouraged because callers might run without assertions enabled.

F. `assert condition : message;` is valid syntax where `message` is any expression that produces a value.

---

**23.** What is the output of the following code?

```java
public class Bank {
    static class InsufficientFundsException extends Exception {
        private final double shortfall;
        public InsufficientFundsException(double shortfall) {
            super("Short by " + shortfall);
            this.shortfall = shortfall;
        }
        public double getShortfall() { return shortfall; }
    }

    static class Account {
        private double balance;
        Account(double balance) { this.balance = balance; }

        void withdraw(double amount) throws InsufficientFundsException {
            if (amount > balance) {
                throw new InsufficientFundsException(amount - balance);
            }
            balance -= amount;
        }
    }

    public static void main(String[] args) {
        Account acc = new Account(100.0);
        try {
            acc.withdraw(50.0);
            acc.withdraw(75.0);
        } catch (InsufficientFundsException e) {
            System.out.printf("%s (shortfall=%.1f)%n", e.getMessage(), e.getShortfall());
        }
        System.out.println("Balance: " + acc.balance);
    }
}
```

A. `Short by 25.0 (shortfall=25.0)` then `Balance: 50.0`

B. `Short by 25.0 (shortfall=25.0)` then `Balance: 0.0`

C. The code does not compile because `InsufficientFundsException` has no constructor matching `super()`.

D. `Short by 25.0 (shortfall=25.0)` then `Balance: 25.0`

E. No exception is thrown; `Balance: -25.0` is printed.

---

**24.** What is the output of the following code?

```java
public class TryWithVar {
    static class Logger implements AutoCloseable {
        private String name;
        Logger(String name) {
            this.name = name;
            System.out.print("Open" + name + " ");
        }
        public void log(String msg) {
            System.out.print(msg + " ");
        }
        @Override
        public void close() {
            System.out.print("Close" + name + " ");
        }
    }

    public static void main(String[] args) {
        var logger = new Logger("X");
        try (logger) {
            logger.log("working");
        }
        System.out.print("Done");
    }
}
```

A. `OpenX working CloseX Done`

B. The code does not compile because `logger` is not declared inside the try-with-resources parentheses.

C. `OpenX CloseX working Done`

D. The code does not compile because `Logger` does not implement `AutoCloseable` correctly.

E. `OpenX working Done CloseX`

---

**25.** What is the output of the following code?

```java
public class ReassignDemo {
    static class Res implements AutoCloseable {
        public void close() { System.out.print("closed "); }
    }

    public static void main(String[] args) {
        var r = new Res();
        try (r) {
            System.out.print("using ");
        }
        r = null;
    }
}
```

A. `using closed`

B. `closed using`

C. The code does not compile because `r` is reassigned after the try-with-resources statement, so it is not effectively final.

D. The code does not compile because `Res` cannot be declared with `var`.

E. `using`

---

**26.** What is the output of the following code?

```java
public class MultiCatchDemo {
    public static void main(String[] args) {
        String[] data = {"10", "abc", "20"};
        int total = 0;
        for (String s : data) {
            try {
                total += Integer.parseInt(s);
            } catch (NumberFormatException | NullPointerException e) {
                System.out.print("skip ");
            }
        }
        System.out.println("total=" + total);
    }
}
```

A. `skip total=30`

B. `total=30`

C. `skip total=10`

D. The code does not compile because `NumberFormatException` and `NullPointerException` are unrelated.

E. `skip skip total=30`

---

**27.** What is the output of the following code?

```java
public class FinalCatchVar {
    public static void main(String[] args) {
        try {
            throw new IllegalArgumentException("oops");
        } catch (IllegalArgumentException | NullPointerException e) {
            e = new NullPointerException("reassigned");
            System.out.println(e.getMessage());
        }
    }
}
```

A. `oops`

B. `reassigned`

C. The code does not compile because `e` is implicitly final and cannot be reassigned in a multi-catch block.

D. The code does not compile because `IllegalArgumentException` and `NullPointerException` are unrelated types in a multi-catch.

E. A `NullPointerException` propagates uncaught.

---

**28.** What is the output of the following code?

```java
public class StackTraceDemo {
    static int divide(int a, int b) {
        return a / b;
    }

    static int compute(int x) {
        return divide(100, x);
    }

    public static void main(String[] args) {
        try {
            System.out.println(compute(0));
        } catch (ArithmeticException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
```

A. `Error: / by zero`

B. `Error: null`

C. The code does not compile because `divide` and `compute` do not declare `throws ArithmeticException`.

D. `Error: 100 / 0`

E. A `StackOverflowError` is thrown.

---

**29.** What is the output of the following code?

```java
public class SuppressedChain {
    static class ResourceA implements AutoCloseable {
        public void close() throws Exception {
            throw new Exception("A failed to close");
        }
    }
    static class ResourceB implements AutoCloseable {
        public void close() throws Exception {
            throw new Exception("B failed to close");
        }
    }

    public static void main(String[] args) {
        try (ResourceA a = new ResourceA(); ResourceB b = new ResourceB()) {
            System.out.print("body ");
        } catch (Exception e) {
            System.out.print("primary:" + e.getMessage() + " ");
            for (Throwable t : e.getSuppressed()) {
                System.out.print("suppressed:" + t.getMessage() + " ");
            }
        }
    }
}
```

A. `body primary:B failed to close suppressed:A failed to close`

B. `body primary:A failed to close suppressed:B failed to close`

C. `body primary:A failed to close`

D. The code does not compile because `close()` throws a checked `Exception` and is not handled.

E. `body suppressed:A failed to close suppressed:B failed to close`

---

**30.** What is the output of the following code?

```java
public class RecoverDemo {
    public static void main(String[] args) {
        int[] values = {10, 20, 0, 5};
        int sum = 0;
        for (int v : values) {
            try {
                sum += 100 / v;
            } catch (ArithmeticException e) {
                continue;
            } finally {
                sum += 1;
            }
        }
        System.out.println(sum);
    }
}
```

A. `39`

B. `35`

C. `40`

D. `38`

E. The code does not compile because `continue` cannot appear in a `catch` block.

---

**31.** Which of the following statements about overriding methods that declare exceptions are true? (Choose all that apply.)

A. An overriding method may declare a checked exception that is a subclass of the one declared by the overridden method.

B. An overriding method may declare a broader checked exception than the overridden method.

C. An overriding method may declare fewer checked exceptions than the overridden method, including none at all.

D. An overriding method may declare any unchecked exception, regardless of what the overridden method declares.

E. An overriding method must declare exactly the same checked exceptions as the overridden method.

F. If the overridden method declares no checked exceptions, the overriding method may not declare any new checked exceptions.

---

**32.** What is the output of the following code, assuming it is compiled WITHOUT the `-g:vars` flag?

```java
public class ParamNpe {
    public void greet(String name) {
        System.out.println(name.toUpperCase());
    }

    public static void main(String[] args) {
        new ParamNpe().greet(null);
    }
}
```

A. `Exception in thread "main" java.lang.NullPointerException: Cannot invoke "String.toUpperCase()" because "<parameter1>" is null`

B. `null`

C. `NULL`

D. The code does not compile.

E. `Exception in thread "main" java.lang.NullPointerException: Cannot invoke "String.toUpperCase()" because "name" is null`

---

**33.** What is the output of the following code?

```java
public class WrapperHierarchy {
    static class ZooException extends Exception {}
    static class HabitatException extends ZooException {}
    static class FeedingException extends ZooException {}

    public static void main(String[] args) {
        try {
            throw new HabitatException();
        } catch (FeedingException e) {
            System.out.println("feeding");
        } catch (ZooException e) {
            System.out.println("zoo");
        }
    }
}
```

A. `feeding`

B. `zoo`

C. The code does not compile because the `FeedingException` catch block can never be reached, since the `try` block cannot throw a `FeedingException`.

D. The code does not compile because `main` does not declare `throws ZooException`.

E. The code does not compile because `FeedingException` must be listed before `HabitatException`.

---

**34.** What is the output of the following code?

```java
public class OrderOfOps {
    public static void main(String[] args) {
        System.out.println(test());
    }

    static String test() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            try {
                if (i == 1) {
                    throw new RuntimeException();
                }
                sb.append("T" + i);
            } catch (RuntimeException e) {
                sb.append("C" + i);
                break;
            } finally {
                sb.append("F" + i);
            }
        }
        return sb.toString();
    }
}
```

A. `T0F0C1F1`

B. `T0F0C1F1T2F2`

C. `T0F0T1F1T2F2`

D. `T0F0C1F1F2`

E. The code does not compile because `break` cannot appear in a `catch` block within a `try`/`finally`.

---

**35.** Given the following declarations:

```java
class ZooException extends Exception {}
class ClosedException extends ZooException {}
class FeedingTimeException extends RuntimeException {}

void risky() throws ClosedException {
    throw new ClosedException();
}
```

Which of the following, when used as the body of a method that calls `risky()` inside a `try`, compile without error? (Choose two.)

A. `try { risky(); } catch (ZooException e) { } catch (ClosedException e) { }`

B. `try { risky(); } catch (ClosedException e) { } catch (ZooException e) { }`

C. `try { risky(); } catch (ZooException | ClosedException e) { }`

D. `try { risky(); } catch (ClosedException | FeedingTimeException e) { }`

E. `try { risky(); } catch (FeedingTimeException | RuntimeException e) { }`

---

**36.** What is the output of the following code?

```java
public class CustomCauseDemo {
    static class ConfigException extends RuntimeException {
        public ConfigException(String msg) {
            super(msg);
        }
    }

    static void load() {
        try {
            Object o = null;
            o.toString();
        } catch (NullPointerException e) {
            ConfigException ce = new ConfigException("config load failed");
            ce.initCause(e);
            throw ce;
        }
    }

    public static void main(String[] args) {
        try {
            load();
        } catch (ConfigException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getCause() instanceof NullPointerException);
        }
    }
}
```

A. `config load failed` then `true`

B. `config load failed` then `false`

C. The code does not compile because `RuntimeException` does not have a `(String)` constructor.

D. The code does not compile because `initCause` cannot be called on a `RuntimeException`.

E. A `NullPointerException` propagates uncaught.

---

**37.** What is the output of the following code?

```java
public class FinallyThrows {
    public static void main(String[] args) {
        try {
            method();
        } catch (Exception e) {
            System.out.println("Caught: " + e.getMessage());
        }
    }

    static void method() throws Exception {
        try {
            throw new Exception("original");
        } finally {
            throw new Exception("from finally");
        }
    }
}
```

A. `Caught: original`

B. `Caught: from finally`

C. `Caught: original` then `Caught: from finally`

D. The code does not compile because a `finally` block cannot contain a `throw`.

E. Both exceptions are added as suppressed exceptions to a combined exception.

---

**38.** What is the output of the following code?

```java
public class LayeredResources {
    static class Connection implements AutoCloseable {
        private final String id;
        Connection(String id) {
            this.id = id;
            System.out.print("Open" + id + " ");
        }
        void use() { System.out.print("Use" + id + " "); }
        @Override
        public void close() { System.out.print("Close" + id + " "); }
    }

    public static void main(String[] args) {
        try (Connection outer = new Connection("Outer")) {
            outer.use();
            try (Connection inner = new Connection("Inner")) {
                inner.use();
            }
            System.out.print("AfterInner ");
        }
    }
}
```

A. `OpenOuter UseOuter OpenInner UseInner CloseInner AfterInner CloseOuter`

B. `OpenOuter OpenInner UseOuter UseInner CloseOuter CloseInner AfterInner`

C. `OpenOuter UseOuter OpenInner UseInner AfterInner CloseInner CloseOuter`

D. `OpenOuter UseOuter OpenInner UseInner CloseOuter CloseInner AfterInner`

E. The code does not compile.

---

**39.** What is the output of the following code?

```java
public class HopperDemo {
    static class CanNotHopException extends Exception {}

    static class Hopper {
        void hop() throws CanNotHopException {
            throw new CanNotHopException();
        }
    }

    static class Bunny extends Hopper {
        @Override
        void hop() {
            System.out.println("hopping happily");
        }
    }

    public static void main(String[] args) throws CanNotHopException {
        Hopper h = new Bunny();
        h.hop();
    }
}
```

A. `hopping happily`

B. The code does not compile because `Bunny.hop()` does not declare `throws CanNotHopException`.

C. A `CanNotHopException` is thrown at runtime.

D. The code does not compile because `main` declares a checked exception that it never throws.

E. The code does not compile for both reasons B and D.

---

**40.** What is the output of the following code?

```java
public class ArrayTrouble {
    public static void main(String[] args) {
        int[] data = {1, 2, 3};
        try {
            System.out.println(data[3]);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Index error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Generic: " + e.getMessage());
        }
    }
}
```

A. `Index error: Index 3 out of bounds for length 3`

B. `Generic: Index 3 out of bounds for length 3`

C. The code does not compile because `Exception` catch block is unreachable.

D. `Index error: 3`

E. `0`

---

**41.** What is the output of the following code?

```java
public class TicketBooth {
    static class SoldOutException extends Exception {
        public SoldOutException(String show) {
            super("Sold out: " + show);
        }
    }

    static class VipSoldOutException extends SoldOutException {
        public VipSoldOutException(String show) {
            super(show);
        }
    }

    static void buyTicket(String show, boolean vip) throws SoldOutException {
        if (vip) {
            throw new VipSoldOutException(show);
        }
        throw new SoldOutException(show);
    }

    public static void main(String[] args) {
        try {
            buyTicket("Magic Show", true);
        } catch (VipSoldOutException e) {
            System.out.println("VIP: " + e.getMessage());
        } catch (SoldOutException e) {
            System.out.println("Regular: " + e.getMessage());
        }
    }
}
```

A. `VIP: Sold out: Magic Show`

B. `Regular: Sold out: Magic Show`

C. The code does not compile because `VipSoldOutException` catch block is unreachable.

D. The code does not compile because `VipSoldOutException` has no `(String)` constructor available via `super`.

E. `VIP: null`

---

**42.** What is the output of the following code?

```java
public class FinallyNullCheck {
    static String describe(String input) {
        StringBuilder result = new StringBuilder();
        try {
            result.append("len=").append(input.length());
        } catch (NullPointerException e) {
            result.append("null-input");
        } finally {
            result.append(input.trim());
        }
        return result.toString();
    }

    public static void main(String[] args) {
        System.out.println(describe(null));
    }
}
```

A. `null-inputnull`

B. `len=0null-input`

C. A `NullPointerException` propagates out of `describe()` to `main()`, uncaught.

D. `null-input`

E. The code does not compile.

---

**43.** What is the result of the following code?

```java
public class RethrowDemo {
    static void risky(int code) throws java.io.IOException {
        try {
            if (code == 1) {
                throw new java.io.FileNotFoundException("file gone");
            } else {
                throw new java.io.IOException("io trouble");
            }
        } catch (java.io.IOException e) {
            System.out.print("logged:" + e.getMessage() + " ");
            throw e;
        }
    }

    public static void main(String[] args) {
        try {
            risky(1);
        } catch (java.io.FileNotFoundException e) {
            System.out.print("fnf:" + e.getMessage());
        } catch (java.io.IOException e) {
            System.out.print("io:" + e.getMessage());
        }
    }
}
```

A. `logged:file gone fnf:file gone`

B. `logged:file gone io:file gone`

C. The code does not compile because `risky()` rethrows `e` typed as `IOException`, so `main` cannot catch `FileNotFoundException` first.

D. `logged:io trouble io:io trouble`

E. The code does not compile because `FileNotFoundException` catch block is unreachable in `main`.

---

**44.** What is the output of the following code?

```java
public class ListBoundsDemo {
    public static void main(String[] args) {
        java.util.List<Integer> codes = java.util.List.of(1, 2, 3);
        try {
            for (int i = 0; i <= codes.size(); i++) {
                System.out.print(codes.get(i) + " ");
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.print("done");
        } finally {
            System.out.print("!");
        }
    }
}
```

A. `1 2 3 done!`

B. `1 2 3 !done`

C. The code does not compile because `List.of()` returns an immutable list and `get()` cannot be called.

D. `1 2 3 done`

E. `done!`

---

**45.** What is the output of the following code?

```java
public class ExceptionInInitializer {
    static int value = 10 / 0;

    public static void main(String[] args) {
        try {
            System.out.println(value);
        } catch (ArithmeticException e) {
            System.out.println("caught arithmetic");
        } catch (ExceptionInInitializerError e) {
            System.out.println("caught initializer error");
        }
    }
}
```

A. `caught arithmetic`

B. `caught initializer error`

C. The code compiles, but an `ExceptionInInitializerError` is thrown during class initialization, before `main` begins, so neither `catch` block in `main` can handle it.

D. `0`

E. The code does not compile.

---

**46.** Consider the following custom exception hierarchy used across a small zoo management system:

```java
class ZooOperationException extends Exception {
    public ZooOperationException(String msg) { super(msg); }
}

class AnimalNotFoundException extends ZooOperationException {
    public AnimalNotFoundException(String name) {
        super("Animal not found: " + name);
    }
}

class EnclosureFullException extends ZooOperationException {
    public EnclosureFullException(String enclosure) {
        super("Enclosure full: " + enclosure);
    }
}

class ZooRegistry {
    java.util.Map<String, Integer> enclosures = new java.util.HashMap<>();

    void addAnimal(String name, String enclosure, int capacity) throws ZooOperationException {
        int current = enclosures.getOrDefault(enclosure, 0);
        if (current >= capacity) {
            throw new EnclosureFullException(enclosure);
        }
        if (name == null) {
            throw new AnimalNotFoundException("(unnamed)");
        }
        enclosures.put(enclosure, current + 1);
    }
}
```

What is the output of the following `main` method?

```java
public static void main(String[] args) {
    ZooRegistry registry = new ZooRegistry();
    try {
        registry.addAnimal("Tiger", "BigCats", 1);
        registry.addAnimal("Lion", "BigCats", 1);
    } catch (AnimalNotFoundException e) {
        System.out.println("Not found: " + e.getMessage());
    } catch (EnclosureFullException e) {
        System.out.println("Full: " + e.getMessage());
    } catch (ZooOperationException e) {
        System.out.println("General: " + e.getMessage());
    }
}
```

A. `Not found: (unnamed)`

B. `Full: Enclosure full: BigCats`

C. `General: Enclosure full: BigCats`

D. The code does not compile because `EnclosureFullException` catch block is unreachable after `AnimalNotFoundException`.

E. No output is printed.

---

**47.** What is the output of the following code?

```java
public class TryReturn {
    static int counter = 0;

    static int increment() {
        try {
            counter++;
            return counter;
        } finally {
            counter++;
        }
    }

    public static void main(String[] args) {
        int result = increment();
        System.out.println(result + " " + counter);
    }
}
```

A. `1 1`

B. `1 2`

C. `2 2`

D. `2 1`

E. The code does not compile.

---

**48.** Which of the following statements about `Throwable`, `Error`, and `Exception` are correct? (Choose all that apply.)

A. `Throwable` has two direct subclasses relevant to the exam: `Exception` and `Error`.

B. `RuntimeException` is a direct subclass of `Throwable`.

C. `Error` and its subclasses are unchecked.

D. It is recommended that application code catch `Error` to recover from `OutOfMemoryError`.

E. `StackOverflowError` is thrown when a method recurses without a terminating condition, eventually exhausting the call stack.

F. A class can extend `Throwable` directly, without extending `Exception` or `Error`, and such a class is treated as a checked exception for the handle-or-declare rule.

---

**49.** What is the output of the following code?

```java
public class CustomAutoCloseableChain {
    static class Step implements AutoCloseable {
        private final String label;
        private final boolean failOnClose;

        Step(String label, boolean failOnClose) {
            this.label = label;
            this.failOnClose = failOnClose;
            System.out.print("Start" + label + " ");
        }

        void run() {
            System.out.print("Run" + label + " ");
        }

        @Override
        public void close() throws Exception {
            System.out.print("End" + label + " ");
            if (failOnClose) {
                throw new Exception("Fail" + label);
            }
        }
    }

    public static void main(String[] args) {
        try (Step s1 = new Step("1", false);
             Step s2 = new Step("2", true);
             Step s3 = new Step("3", false)) {
            s1.run();
            s2.run();
            s3.run();
        } catch (Exception e) {
            System.out.print("Caught:" + e.getMessage());
        }
    }
}
```

A. `Start1 Start2 Start3 Run1 Run2 Run3 End3 End2 End1 Caught:Fail2`

B. `Start1 Start2 Start3 Run1 Run2 Run3 End1 End2 End3 Caught:Fail2`

C. `Start1 Start2 Start3 Run1 Run2 Run3 End3 End2 Caught:Fail2`

D. `Start1 Start2 Start3 Run1 Run2 Run3 End3 End2 End1 Caught:Fail3`

E. The code does not compile.

---

**50.** What is the output of the following code?

```java
public class ConditionalThrow {
    public static void main(String[] args) {
        String result;
        try {
            result = compute(3);
        } catch (IllegalStateException e) {
            result = "error: " + e.getMessage();
        }
        System.out.println(result);
    }

    static String compute(int n) {
        if (n % 2 == 0) {
            return "even";
        } else if (n > 0) {
            throw new IllegalStateException("positive odd");
        } else {
            return "non-positive";
        }
    }
}
```

A. `even`

B. `error: positive odd`

C. `non-positive`

D. The code does not compile because not all paths in `compute` return a value or throw.

E. A `RuntimeException` propagates uncaught because `IllegalStateException` is not declared.

---

**51.** What is the output of the following code?

```java
import java.util.Locale;

public class LocaleBasics {
    public static void main(String[] args) {
        Locale l1 = Locale.of("en", "US");
        Locale l2 = Locale.of("fr");
        System.out.println(l1);
        System.out.println(l2);
        System.out.println(l1.getLanguage() + " " + l1.getCountry());
    }
}
```

A. `en_US` then `fr` then `en US`

B. `en_US` then `fr` then `en_US US`

C. `en-US` then `fr` then `en US`

D. The code does not compile because `Locale.of()` requires both a language and country.

E. `EN_US` then `FR` then `EN US`

---

**52.** Which of the following `Locale` representations are valid (i.e., a real `Locale` could legitimately use this `language` or `language_COUNTRY` form)? (Choose all that apply.)

A. `en`

B. `en_US`

C. `EN_us`

D. `US`

E. `fr_CA`

F. `enUS`

---

**53.** What is the output of the following code?

```java
import java.util.Locale;

public class BuilderDemo {
    public static void main(String[] args) {
        Locale locale = new Locale.Builder()
                .setLanguage("de")
                .setRegion("AT")
                .build();
        System.out.println(locale);
    }
}
```

A. `de_AT`

B. `AT_de`

C. `de-AT`

D. The code does not compile because `Locale.Builder` requires `build()` to be called before `setRegion()`.

E. `de`

---

**54.** What is the output of the following code?

```java
import java.util.Locale;

public class BuilderInvalid {
    public static void main(String[] args) {
        try {
            Locale locale = new Locale.Builder()
                    .setLanguage("e")
                    .build();
            System.out.println(locale);
        } catch (java.util.IllformedLocaleException e) {
            System.out.println("invalid");
        }
    }
}
```

A. `e`

B. `invalid`

C. `en`

D. The code does not compile.

E. `E`

---

**55.** Given the following resource bundle files:

```
Zoo.properties:
    name=Generic Zoo
    motto=Have fun

Zoo_en.properties:
    name=City Zoo
    hours=9-5

Zoo_en_US.properties:
    motto=Wild about fun
```

And the following code, where the JVM default locale is `Locale.of("en", "US")`:

```java
var rb = ResourceBundle.getBundle("Zoo", Locale.of("en", "US"));
System.out.println(rb.getString("name"));
System.out.println(rb.getString("motto"));
System.out.println(rb.getString("hours"));
```

What is printed?

A. `City Zoo` then `Wild about fun` then `9-5`

B. `Generic Zoo` then `Wild about fun` then `9-5`

C. `City Zoo` then `Have fun` then `9-5`

D. `City Zoo` then `Wild about fun` then a `MissingResourceException` is thrown

E. `Generic Zoo` then `Have fun` then `9-5`

---

**56.** Given the following resource bundle files:

```
Dolphins.properties:
    name=The Dolphin
    age=0

Dolphins_en.properties:
    name=Dolly
    age=4

Dolphins_fr.properties:
    name=Dauphin
```

And the default locale is `Locale.of("en", "US")`. What is printed by the following code?

```java
var rb = ResourceBundle.getBundle("Dolphins", Locale.of("fr"));
System.out.println(rb.getString("name"));
System.out.println(rb.getString("age"));
```

A. `Dauphin` then `0`

B. `Dauphin` then `4`

C. `Dolly` then `4`

D. `The Dolphin` then `0`

E. `Dauphin` then a `MissingResourceException` is thrown

---

**57.** What is the output of the following code, assuming the default locale is `Locale.of("en", "US")` and only `Messages.properties` (no locale-specific variants) exists with content `greeting=Hello`?

```java
var rb = ResourceBundle.getBundle("Messages", Locale.of("de", "DE"));
System.out.println(rb.getString("greeting"));
System.out.println(rb.containsKey("farewell"));
try {
    rb.getString("farewell");
} catch (java.util.MissingResourceException e) {
    System.out.println("missing: farewell");
}
```

A. `Hello` then `false` then `missing: farewell`

B. `Hello` then `true` then `missing: farewell`

C. A `MissingResourceException` is thrown when `getBundle` is called.

D. `Hello` then `false` then nothing else (no exception)

E. `null` then `false` then `missing: farewell`

---

**58.** What is the output of the following code?

```java
import java.text.NumberFormat;
import java.util.Locale;

public class CurrencyDemo {
    public static void main(String[] args) {
        double price = 1234.5;
        NumberFormat us = NumberFormat.getCurrencyInstance(Locale.US);
        NumberFormat de = NumberFormat.getCurrencyInstance(Locale.GERMANY);
        System.out.println(us.format(price));
        System.out.println(de.format(price));
    }
}
```

A. `$1,234.50` then `1.234,50 €`

B. `$1234.50` then `€1234.50`

C. `$1,234.50` then `€1,234.50`

D. `1,234.50 USD` then `1.234,50 EUR`

E. The code does not compile because `NumberFormat` is abstract.

---

**59.** What is the output of the following code?

```java
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

public class ParseDemo {
    public static void main(String[] args) throws ParseException {
        NumberFormat fr = NumberFormat.getInstance(Locale.FRANCE);
        Number n = fr.parse("12.5");
        System.out.println(n);
    }
}
```

A. `12.5`

B. `12`

C. `125`

D. The code does not compile because `parse` is not a method on `NumberFormat`.

E. A `ParseException` is thrown at runtime.

---

**60.** What is the output of the following code?

```java
import java.text.NumberFormat;
import java.util.Locale;

public class PercentDemo {
    public static void main(String[] args) {
        double rate = 0.4567;
        NumberFormat pct = NumberFormat.getPercentInstance(Locale.US);
        System.out.println(pct.format(rate));
    }
}
```

A. `0.4567%`

B. `45.67%`

C. `46%`

D. `45%`

E. `4567%`

---

**61.** What is the output of the following code?

```java
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

public class DateLocaleDemo {
    public static void main(String[] args) {
        LocalDate date = LocalDate.of(2025, 3, 5);
        DateTimeFormatter f = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT);
        System.out.println(f.withLocale(Locale.US).format(date));
        System.out.println(f.withLocale(Locale.of("en", "GB")).format(date));
    }
}
```

A. `3/5/25` then `05/03/2025`

B. `05/03/2025` then `3/5/25`

C. `2025-03-05` then `2025-03-05`

D. `3/5/25` then `3/5/25`

E. The code does not compile because `withLocale` does not exist on `DateTimeFormatter`.

---

**62.** What is the output of the following code?

```java
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class PatternDemo {
    public static void main(String[] args) {
        LocalTime time = LocalTime.of(14, 5);
        DateTimeFormatter f = DateTimeFormatter.ofPattern("hh:mm a");
        System.out.println(time.format(f));
    }
}
```

A. `14:05 PM`

B. `02:05 PM`

C. `02:05 AM`

D. The code does not compile because `a` is not a valid pattern symbol.

E. A `DateTimeException` is thrown because `LocalTime` does not support `a`.

---

**63.** What is the output of the following code?

```java
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class EscapeDemo {
    public static void main(String[] args) {
        LocalDate date = LocalDate.of(2025, 7, 4);
        DateTimeFormatter f = DateTimeFormatter.ofPattern("MMMM d, ''yy''");
        System.out.println(date.format(f));
    }
}
```

A. `July 4, '25'`

B. `July 4, ''25''`

C. The code throws `IllegalArgumentException` at runtime due to an unmatched quote.

D. `July 4, 25`

E. `July 4, yy`

---

**64.** What is the output of the following code?

```java
import java.util.Locale;
import java.util.Locale.Category;
import java.text.NumberFormat;

public class CategoryDemo {
    public static void main(String[] args) {
        Locale.setDefault(Locale.of("en", "US"));
        Locale germany = Locale.GERMANY;

        Locale.setDefault(Category.FORMAT, germany);

        System.out.println(NumberFormat.getInstance().format(1234.5));
        System.out.println(Locale.getDefault(Category.DISPLAY).getLanguage());
    }
}
```

A. `1.234,5` then `en`

B. `1,234.5` then `de`

C. `1.234,5` then `de`

D. `1,234.5` then `en`

E. The code does not compile because `Category` is not accessible.

---

**65.** What is the output of the following code?

```java
import java.text.MessageFormat;

public class MessageFormatDemo {
    public static void main(String[] args) {
        String pattern = "Dear {0}, you have {1} new messages.";
        String result = MessageFormat.format(pattern, "Alice", 5);
        System.out.println(result);
    }
}
```

A. `Dear Alice, you have 5 new messages.`

B. `Dear {0}, you have {1} new messages.`

C. `Dear Alice, you have {1} new messages.`

D. The code does not compile because `format` requires a `Locale` argument.

E. A `ParseException` is thrown.

---

**66.** What is the output of the following code?

```java
import java.util.Properties;

public class PropertiesDemo {
    public static void main(String[] args) {
        Properties props = new Properties();
        props.setProperty("hours", "9-5");
        System.out.println(props.getProperty("location"));
        System.out.println(props.getProperty("location", "Unknown"));
        System.out.println(props.getProperty("hours"));
    }
}
```

A. `null` then `Unknown` then `9-5`

B. `Unknown` then `Unknown` then `9-5`

C. The code does not compile because `getProperty` does not accept a default value.

D. `null` then `null` then `9-5`

E. A `MissingResourceException` is thrown on the first `getProperty` call.

---

**67.** Which of the following statements about `ResourceBundle` resolution are true? (Choose all that apply.)

A. Once a matching resource bundle is found for a requested locale, only that bundle and its "parent" bundles (with progressively fewer locale components) are searched for keys.

B. If no bundle matches the requested locale at all, Java falls back to bundles for the JVM's default locale before falling back to the base bundle with no locale suffix.

C. If a key is not found anywhere in the resolved hierarchy, `getString()` returns `null`.

D. `ResourceBundle.getBundle("Name")` uses `Locale.getDefault()`.

E. A `Properties`-based resource bundle can be iterated with `keySet()`.

F. If the requested locale and the default locale are the same, Java will check that locale's bundle twice.

---

**68.** What is the output of the following code?

```java
import java.text.NumberFormat;
import java.util.Locale;
import java.text.NumberFormat.Style;

public class CompactFormatDemo {
    public static void main(String[] args) {
        NumberFormat shortFmt = NumberFormat.getCompactNumberInstance(Locale.US, Style.SHORT);
        NumberFormat longFmt = NumberFormat.getCompactNumberInstance(Locale.US, Style.LONG);
        System.out.println(shortFmt.format(7_123_456));
        System.out.println(longFmt.format(7_123_456));
    }
}
```

A. `7M` then `7 million`

B. `7,123,456` then `7,123,456`

C. `7.1M` then `7.1 million`

D. `7123456` then `7123456`

E. The code does not compile because `NumberFormat.Style` is not a valid type.

---

**69.** What is the output of the following code?

```java
import java.util.Locale;

public class IllformedDemo {
    public static void main(String[] args) {
        try {
            Locale locale = new Locale.Builder()
                    .setLanguage("en")
                    .setRegion("USA")
                    .build();
            System.out.println(locale);
        } catch (java.util.IllformedLocaleException e) {
            System.out.println("caught");
        }
    }
}
```

A. `en_USA`

B. `caught`

C. `en`

D. The code does not compile.

E. `en_US`

---

**70.** What is the output of the following code?

```java
public class StringFormatLocale {
    public static void main(String[] args) {
        double value = 1234567.891;
        System.out.println(String.format(java.util.Locale.GERMANY, "%,.2f", value));
        System.out.println(String.format(java.util.Locale.US, "%,.2f", value));
    }
}
```

A. `1.234.567,89` then `1,234,567.89`

B. `1,234,567.89` then `1,234,567.89`

C. `1234567.89` then `1234567.89`

D. `1.234.567,89` then `1.234.567,89`

E. The code does not compile because `String.format` does not accept a `Locale` parameter.
