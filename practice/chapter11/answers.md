# Chapter 11: Exceptions and Localization — Answers

---

**1. A**

In `process()`, `sb` accumulates `"A"`, then an `IllegalStateException` is thrown and caught. The `catch` block appends `"B"` (sb is now `"AB"`) and evaluates `return sb.toString()`, which captures the value `"AB"` to be returned. The `finally` block then runs and appends `"C"` to `sb`, but this happens *after* the return value was already captured, so it has no effect on the returned string. The output is `AB`.

---

**2. B**

When both the `try` and `finally` blocks contain a `return`, the `finally` block's `return` wins — it overrides the pending return from `try`. The `try` block's `return 1` is abandoned before it completes, and `compute()` returns `2`. A `finally` block is allowed to contain a `return` statement (option C is false), and no exception occurs.

---

**3. A, C**

Option A (`IOException | SQLException`) compiles because neither type is a subclass of the other — they are unrelated checked exceptions, both declared by `risky()`. Option C (`NumberFormatException | ArithmeticException`) compiles because both are unrelated unchecked `RuntimeException` subclasses, which never need to be declared. Option B fails because `FileNotFoundException` is a subclass of `IOException` (alternatives in a multi-catch cannot be related by subclassing). Option D fails for the same reason — `NumberFormatException` is a subclass of `IllegalArgumentException`. Option E fails because `RuntimeException` is a subclass of `Exception`.

---

**4. C**

`a.check(15)` does not throw (15 is not greater than 18), printing `OK: 15`. `a.check(20)` throws a `GiraffeException` with message `"Too tall: 20"`, which is caught and printed as `Caught: Too tall: 20`. Because the exception is thrown and caught, `a.check(10)` is never reached. `GiraffeException`'s constructor explicitly calls `super(message)`, which is valid since `Exception` provides a `(String)` constructor, so option D is incorrect. The output is `OK: 15` then `Caught: Too tall: 20`.

---

**5. B**

Resources declared in a try-with-resources statement are closed in the **reverse order** of their declaration, regardless of whether an exception occurs. Since `a`, `b`, and `c` are declared in that order, they are closed in the order `c`, `b`, `a`. This order is well-defined by the JLS, not arbitrary (option D is false), and all three resources are closed by the try-with-resources mechanism itself, not the garbage collector (option E is false).

---

**6. B**

Both `Lion` resources are opened, then `"Body "` is printed, then a `RuntimeException` is thrown. Before the exception propagates to the `catch` block, the resources are closed in reverse declaration order: `b` first, then `a`, printing `CloseB ` then `CloseA `. Only after both `close()` calls complete does control reach the `catch` block, printing `Caught:Roar`. The output is `Body CloseB CloseA Caught:Roar`.

---

**7. B**

`AutoCloseable.close()` is declared to throw `Exception`, but an implementing class is free to narrow this to a more specific checked exception, an unchecked exception, or declare no exception at all (overriding rules allow narrowing or removing checked exceptions). Option A is false because the `throws Exception` on the interface is the *widest* allowed, not a requirement. Option C is false — try-with-resources works with any `AutoCloseable`, regardless of what `close()` throws. Option D is false; `Closeable` extends `AutoCloseable` and narrows `close()` to throw only `IOException`. Option E is false — narrowing to `IOException` (a subclass of `Exception`) is permitted.

---

**8. A**

The `try` block throws a `RuntimeException("leak")` before `close()` is called. During unwinding, `t.close()` is invoked, which throws `Exception("tap stuck")`; since the body's exception is already propagating, this becomes a **suppressed exception** attached to the primary `RuntimeException`. The first matching `catch (RuntimeException e)` catches the primary exception, printing `Primary: leak`, and iterating `e.getSuppressed()` prints `Suppressed: tap stuck`. The code compiles fine — try-with-resources permits a `close()` that throws checked `Exception` as long as it's handled or declared, and here it's covered by the second `catch (Exception e)` (which is never reached because the first catch matches).

---

**9. A**

Both `Cage` resources are constructed (`c1` then `c2`), and `"Body "` is printed. On exiting the try block normally, resources close in reverse order: `c2` closes first, printing `Close2 ` and throwing `IllegalStateException("broken-2")`; this becomes the **primary** exception since no exception was already propagating. Then `c1` closes, printing `Close1 ` and throwing `IllegalStateException("broken-1")`, which becomes a **suppressed** exception attached to the primary one. The `catch` block prints `Caught:broken-2` and then iterates suppressed exceptions, printing `Sup:broken-1`. The full output is `Body Close2 Close1 Caught:broken-2 Sup:broken-1`.

---

**10. B, C, E, F**

`ConcurrentModificationException` extends `RuntimeException` (B is true). `SQLException` is a checked exception and must be declared or caught (C is true). `ClassNotFoundException` is a checked exception extending `ReflectiveOperationException` → `Exception` (E is true). `UnsupportedOperationException` extends `RuntimeException`, so it never needs to be declared or caught (F is true). `IllegalStateException` is unchecked, extending `RuntimeException` (A is false). `StackOverflowError` extends `Error`, not `Exception`, and is unchecked — it is never required to be declared (D is false).

---

**11. B**

`inner()` throws an unchecked `IllegalArgumentException`, which is never declared in any method's `throws` clause (unchecked exceptions never require declaration, so option C is false). The exception propagates unchanged through `middle()` and `outer()` to `main()`, where it is caught by `catch (RuntimeException e)` since `IllegalArgumentException` is a subclass of `RuntimeException`. `e.getClass().getSimpleName()` returns `"IllegalArgumentException"`, and `e.getMessage()` returns `"bad arg"`. The output is `IllegalArgumentException: bad arg`.

---

**12. A**

By default, assertions are **disabled** in the JVM unless the `-ea` (or `-enableassertions`) flag is passed. With assertions disabled, the `assert` statement is effectively skipped entirely — the condition `x > 10` is never evaluated, and no `AssertionError` is thrown. Execution proceeds normally to `System.out.println`, printing `x = 5`.

---

**13. B**

With `-ea` enabled, the `assert x > 10 : "..."` statement evaluates its condition. Since `x` is `5`, `x > 10` is `false`, so an `AssertionError` is thrown with the message `"x must be greater than 10"`. This error propagates out of `main` (it is uncaught), and the `System.out.println` line is never reached. `AssertionError` extends `Error`, not `Exception` (option D is false).

---

**14. C**

Calling `name.toLowerCase()` where `name` is a `null` static field throws a `NullPointerException`. Since Java 14, helpful NPE messages are enabled by default, identifying the exact null reference. Because `name` is a field (not a local variable or parameter), the message references it by its qualified name, `Frog.name`, producing: `Cannot invoke "String.toLowerCase()" because "Frog.name" is null`. The code compiles fine (option B is false) — the failure occurs at runtime.

---

**15. A, C, E**

An overriding method may declare a checked exception that is the **same as or a subclass of (narrower than)** what the overridden method declares, may declare **no exceptions at all**, and may declare **any unchecked exception** regardless of the superclass declaration. Option A (`FileNotFoundException`, a subclass of `IOException`) compiles. Option C (no `throws` clause) compiles. Option E (`RuntimeException`, unchecked) compiles. Option B (`Exception`, broader than `IOException`) does not compile — an override cannot declare a broader checked exception. Option D (`SQLException`, an unrelated checked exception not a subtype of `IOException`) does not compile.

---

**16. A**

`parse()` calls `Integer.parseInt("abc")`, which throws `NumberFormatException`. This is caught, and a new `ValidationException` is constructed with message `"Invalid input: abc"` and `cause` set to the caught `NumberFormatException`. `ValidationException`'s constructor passes both arguments to `super(message, cause)`, which `RuntimeException` supports via its `(String, Throwable)` constructor (option C is false). In `main`, `e.getMessage()` returns `"Invalid input: abc"`, and `e.getCause().getClass().getSimpleName()` returns `"NumberFormatException"`. The output is `Invalid input: abc` then `NumberFormatException`.

---

**17. B**

Assigning `new String[3]` to an `Object[]` reference is legal (array covariance), but storing an `Integer` into a `String[]` at runtime throws `ArrayStoreException` — the JVM checks the actual array type on each store. `ArrayStoreException` is a subclass of `RuntimeException` but not of `NullPointerException`, so the first `catch` does not match. The second `catch (ArrayStoreException e)` matches, printing `ASE`. The catch ordering is valid since `ArrayStoreException` is not a subclass of `NullPointerException`, so option D is false.

---

**18. B, C**

`FileNotFoundException` is a subclass of `IOException` (C is true). The `catch` blocks are ordered with the broader `IOException` first (line 4) and the narrower `FileNotFoundException` second (line 6); since `IOException` already covers every `FileNotFoundException`, the second clause is unreachable, and the compiler rejects it with "exception FileNotFoundException has already been caught" (B is true, A is false). Swapping lines 4 and 6 would make the code compile, but it is not the *only* possible fix (e.g., removing line 6 entirely would also work), so the strict "only if" framing in option D is misleading and considered false.

---

**19. A**

`drive(0)` checks `fuel == 0` first and throws `new EngineFailureException("engine dead")`. `EngineFailureException extends LowFuelException`, so it "is-a" `LowFuelException`, satisfying `drive`'s `throws LowFuelException` declaration (option D is false). In `main`, the `catch (EngineFailureException e)` is listed first and is reachable (it's a subclass of `LowFuelException`, ordered correctly before its supertype, so option C is false). It catches the exception and prints `engine: engine dead`.

---

**20. A**

The `try` block appends `"try-"` and throws `RuntimeException("fail")`. The `catch` block appends `"catch-"` (sb is now `"try-catch-"`) and throws a new `IllegalStateException("rethrown")`. Before this new exception can propagate, the `finally` block runs: it appends `"finally"` (sb is now `"try-catch-finally"`) and executes `return sb.toString()`. A `return` in `finally` **discards** any in-flight exception — the `IllegalStateException` is swallowed entirely, and `process()` returns and prints `try-catch-finally`.

---

**21. A**

The inner `try` appends `"inner-try-"` and throws `RuntimeException("X")`. Before the exception propagates out of the inner `try`, its `finally` block runs, appending `"inner-finally-"`. The exception then propagates to the outer `catch (RuntimeException e)`, which appends `"outer-catch:X"`. The final string, printed by `System.out.println(sb)`, is `inner-try-inner-finally-outer-catch:X`.

---

**22. B, C, E, F**

Assertions are **disabled by default**; the `-ea` flag is required to enable them at runtime (A is false, B is true). `assert` throws `java.lang.AssertionError`, which extends `Error` (C is true; D is false since there is no `AssertionException`). Using `assert` for validating public API arguments is discouraged because callers can disable assertions, bypassing the check entirely (E is true). The second form, `assert condition : message;`, is valid as long as `message` is any expression that produces a value, such as a `String`, a number, or an object whose `toString()` will be used (F is true).

---

**23. A**

`InsufficientFundsException`'s constructor calls `super("Short by " + shortfall)`, which compiles fine because `Exception` provides a `(String message)` constructor (option C is false). `acc.withdraw(50.0)` succeeds (`50.0 <= 100.0`), reducing the balance to `50.0`. `acc.withdraw(75.0)` checks `75.0 > 50.0`, which is true, so it throws `new InsufficientFundsException(75.0 - 50.0)` = `InsufficientFundsException(25.0)`, with message `"Short by 25.0"` and `shortfall = 25.0`. The balance is **not** further modified, since the `throw` happens before `balance -= amount` executes. The catch block prints `Short by 25.0 (shortfall=25.0)`, and `acc.balance` remains `50.0`.

---

**24. A**

Java 9+ allows try-with-resources to reference a pre-existing **effectively final** variable directly (without redeclaring it), as long as it is final or effectively final — `var logger = new Logger("X")` is never reassigned, so it qualifies (option B is false). The constructor prints `OpenX `. Inside the `try`, `logger.log("working")` prints `working `. On exiting the try block, `close()` is called automatically, printing `CloseX `. Finally, `"Done"` is printed. The output is `OpenX working CloseX Done`.

---

**25. C**

For Java 9+'s "effectively final" try-with-resources syntax, the referenced variable must be effectively final for its **entire scope**, including after the try-with-resources statement. Here, `r` is reassigned to `null` after the try block (`r = null;`), which means `r` is **not** effectively final, so the code fails to compile. This is a compile-time error, not a runtime issue.

---

**26. A**

Iterating `data = {"10", "abc", "20"}`: `Integer.parseInt("10")` succeeds, `total += 10` → `total = 10`. `Integer.parseInt("abc")` throws `NumberFormatException`, caught by the multi-catch (which is valid — `NumberFormatException` and `NullPointerException` are unrelated `RuntimeException` subclasses, so option D is false), printing `skip `. `Integer.parseInt("20")` succeeds, `total += 20` → `total = 30`. The final output is `skip total=30`.

---

**27. C**

In a multi-catch clause (`catch (TypeA | TypeB e)`), the exception variable `e` is **implicitly final** — it cannot be reassigned within the catch block, even though the declared type is effectively a common supertype of the alternatives. Attempting `e = new NullPointerException("reassigned")` is a compile-time error. Note that `IllegalArgumentException` and `NullPointerException` being unrelated types is perfectly valid for a multi-catch (option D is false) — the issue is solely the reassignment of `e`.

---

**28. A**

`compute(0)` calls `divide(100, 0)`, which evaluates `100 / 0`, throwing `ArithmeticException` with message `"/ by zero"`. `ArithmeticException` is unchecked, so neither `divide` nor `compute` need to declare it (option C is false). The exception propagates up to `main`'s `catch (ArithmeticException e)`, which prints `Error: / by zero`.

---

**29. B**

Both `ResourceA` and `ResourceB` are constructed successfully, and `"body "` is printed. On exiting the try block normally, resources close in reverse order: `b` (ResourceB) closes first, throwing `Exception("B failed to close")`. Since no exception is currently propagating, this becomes the **primary** exception. Then `a` (ResourceA) closes, throwing `Exception("A failed to close")`, which becomes **suppressed** and attached to the primary exception. The catch block prints `primary:B failed to close` then iterates suppressed exceptions, printing `suppressed:A failed to close`. The checked `Exception` thrown by `close()` is handled by the `catch (Exception e)` clause, so the code compiles fine (option D is false).

---

**30. A**

Tracing through `values = {10, 20, 0, 5}` with `sum` starting at `0`:
- `v=10`: `sum += 100/10 = 10` → `sum=10`; `finally` runs: `sum += 1` → `sum=11`.
- `v=20`: `sum += 100/20 = 5` → `sum=16`; `finally`: `sum += 1` → `sum=17`.
- `v=0`: `100/0` throws `ArithmeticException`, caught, `continue` skips to the next iteration — but `finally` still runs first: `sum += 1` → `sum=18`.
- `v=5`: `sum += 100/5 = 20` → `sum=38`; `finally`: `sum += 1` → `sum=39`.

The final value of `sum` is `39`. Note that `continue` **is** legal inside a `catch` block (option E is false) — `finally` still executes before control transfers to the next loop iteration.

---

**31. A, C, D, F**

An overriding method may declare a checked exception that is the same as or a **subclass** of the overridden method's declared exception (A is true), may declare **fewer** checked exceptions or none at all (C is true), and may declare **any unchecked exception** regardless of the superclass's declarations, since unchecked exceptions are never restricted by override rules (D is true). If the overridden method declares **no** checked exceptions, the override cannot introduce any new checked exception either — doing so would be "broader" than declaring none (F is true). Declaring a **broader** checked exception than the overridden method is forbidden (B is false), and the override does **not** need to match exactly — narrowing or omitting is allowed (E is false).

---

**32. A**

Calling `name.toUpperCase()` where `name` (a parameter) is `null` throws `NullPointerException`. Since Java 14, helpful NPE messages are enabled by default. Without the `-g:vars` debug flag, the compiler cannot recover the original parameter name `name`, so the JVM uses a generic placeholder `<parameter1>` (referring to the first parameter of the method) in the message: `Cannot invoke "String.toUpperCase()" because "<parameter1>" is null`. If compiled *with* `-g:vars`, the message would instead show `"name"` (as in option E), but that is not the scenario described.

---

**33. C**

`HabitatException` and `FeedingException` are siblings — both extend `ZooException` directly; neither is a subclass of the other. The `try` block can only throw a `HabitatException` (from the explicit `throw`). The compiler determines that a checked exception type can only be caught if the `try` block can actually throw that type (or a supertype of it). Since `FeedingException` (a checked exception) can never be thrown by this `try` block, its `catch` clause is **unreachable**, causing a compile error: "exception FeedingException is never thrown in body of corresponding try statement." `main` does not need a `throws ZooException` declaration because the thrown `HabitatException` is fully handled by the second `catch (ZooException e)` clause — but this is moot since the code fails to compile due to the unreachable `FeedingException` clause first.

---

**34. A**

Trace through the loop: `i=0`: no exception, `sb.append("T0")`; `finally` appends `"F0"` → sb=`"T0F0"`. `i=1`: `throw new RuntimeException()` is caught; `sb.append("C1")`, then `break` — but before `break` exits the loop, `finally` runs and appends `"F1"` → sb=`"T0F0C1F1"`. The `break` then exits the loop entirely, so `i=2` never executes. `break` is permitted inside a `catch` block within a `try`/`finally` construct (option E is false). The final string returned and printed is `T0F0C1F1`.

---

**35. B, D**

Option B (`catch (ClosedException e) { } catch (ZooException e) { }`) compiles: `risky()` throws `ClosedException`, which is caught by the first clause; the second clause for the broader `ZooException` is reachable for other potential checked exceptions of that hierarchy and produces, at most, a compiler warning about a redundant catch — not an error. Option D (`catch (ClosedException | FeedingTimeException e) { }`) compiles cleanly: the two types are unrelated by subclassing (one checked, one unchecked), which is valid for multi-catch. Option A fails because `ClosedException` is already caught by the preceding `catch (ZooException e)` clause, making the second clause unreachable. Option C fails because `ClosedException` is a subclass of `ZooException` — multi-catch alternatives cannot be related by subclassing. Option E fails for the same reason: `FeedingTimeException` is a subclass of `RuntimeException`.

---

**36. A**

Inside `load()`, calling `o.toString()` on a `null` reference throws `NullPointerException`, which is caught. A new `ConfigException("config load failed")` is created (valid, since `RuntimeException` provides a `(String)` constructor — option C is false), and `ce.initCause(e)` sets its cause to the caught `NullPointerException` (this is a valid call inherited from `Throwable` — option D is false). The `ConfigException` is then thrown and caught in `main`, printing its message `config load failed`, and `e.getCause() instanceof NullPointerException` evaluates to `true`.

---

**37. B**

The `try` block in `method()` throws `Exception("original")`. However, before this exception can propagate, the `finally` block executes and itself throws `Exception("from finally")`. A `throw` from a `finally` block **completely replaces** any exception that was propagating from the `try` or `catch` blocks — the original exception (`"original"`) is discarded entirely (it is not chained or suppressed by default). Only `Exception("from finally")` propagates to `main`, where it is caught and printed as `Caught: from finally`. A `finally` block throwing an exception is legal Java (option D is false).

---

**38. C**

`outer` (a `Connection`) is constructed, printing `OpenOuter `. Inside the try, `outer.use()` prints `UseOuter `. Then `inner` is constructed, printing `OpenInner `, and `inner.use()` prints `UseInner `. The inner try-with-resources block ends, closing `inner` and printing `CloseInner `. Then `"AfterInner "` is printed. Finally, the outer try-with-resources block ends, closing `outer` and printing `CloseOuter`. The full output is `OpenOuter UseOuter OpenInner UseInner CloseInner AfterInner CloseOuter`.

---

**39. A**

`Hopper.hop()` declares `throws CanNotHopException` (a checked exception). `Bunny.hop()` overrides it **without** a `throws` clause — this is legal, since an override may declare fewer (or zero) checked exceptions (option B is false). `main` declares `throws CanNotHopException`, which is allowed even though it is never actually thrown through this particular call path — a method is permitted to declare checked exceptions it doesn't use (option D is false). At runtime, `h` is a `Bunny` instance, so `h.hop()` invokes `Bunny`'s override via dynamic dispatch, printing `hopping happily` and throwing nothing.

---

**40. A**

`data = {1, 2, 3}` has length `3`, so valid indices are `0`–`2`. Accessing `data[3]` throws `ArrayIndexOutOfBoundsException` with message `"Index 3 out of bounds for length 3"`. The first `catch (ArrayIndexOutOfBoundsException e)` matches (it is listed before the broader `Exception` catch, so it is reachable — option C is false), printing `Index error: Index 3 out of bounds for length 3`.

---

**41. A**

`buyTicket("Magic Show", true)` takes the `vip` branch and throws `new VipSoldOutException("Magic Show")`. `VipSoldOutException`'s constructor calls `super(show)`, which matches `SoldOutException`'s `(String show)` constructor — this compiles fine (option D is false). `VipSoldOutException` is a subclass of `SoldOutException`, and it is listed **first** in the catch clauses, so it is reachable (option C is false) and catches the exception. `e.getMessage()` returns `"Sold out: Magic Show"` (set via `SoldOutException`'s constructor, which builds the message as `"Sold out: " + show`). The output is `VIP: Sold out: Magic Show`.

---

**42. C**

`describe(null)` is called. In the `try` block, `input.length()` is evaluated where `input` is `null`, throwing `NullPointerException`, which is caught by `catch (NullPointerException e)`, appending `"null-input"` to `result`. Then the `finally` block executes: `input.trim()` is evaluated — but `input` is still `null`, so this **also** throws `NullPointerException`. An exception thrown in a `finally` block **replaces** any pending result/exception from the `try`/`catch` — since this new `NullPointerException` is never caught (there's no enclosing handler in `describe`), it propagates out of `describe()` to `main()`, where it remains uncaught and the program terminates with a stack trace. The `return result.toString()` statement is never reached.

---

**43. A**

`risky(1)` enters the `try` block, where `code == 1`, so it throws `FileNotFoundException("file gone")`. This is caught by `catch (IOException e)` (since `FileNotFoundException` is a subclass of `IOException`), printing `logged:file gone `, and then `throw e` re-throws the **same exception object** — its runtime type is still `FileNotFoundException`, even though `e` is declared as type `IOException`. In `main`, `catch (FileNotFoundException e)` is checked first; since the actual thrown object is a `FileNotFoundException`, this catch matches (this is legal and reachable because `risky()` declares `throws IOException`, which encompasses `FileNotFoundException` — option C and E are false), printing `fnf:file gone`. The output is `logged:file gone fnf:file gone`.

---

**44. A**

`codes = List.of(1, 2, 3)` has size `3`, valid indices `0`–`2`. The loop runs `i` from `0` to `3` inclusive (`i <= codes.size()`). For `i=0,1,2`, `codes.get(i)` succeeds, printing `1 `, `2 `, `3 `. For `i=3`, `codes.get(3)` throws `IndexOutOfBoundsException` (a superclass relationship: `ArrayIndexOutOfBoundsException` is for arrays, but `List.get()` throws plain `IndexOutOfBoundsException`, which is still caught by `catch (IndexOutOfBoundsException e)`), printing `done`. The `finally` block then runs, printing `!`. The output is `1 2 3 done!`. `List.of()` does support `get()` (it's read-only for mutation methods like `add`, but `get` works fine — option C is false).

---

**45. C**

The static field initializer `static int value = 10 / 0;` runs during **class initialization**, which happens before `main()` is invoked. `10 / 0` throws `ArithmeticException`, which the JVM wraps in an `ExceptionInInitializerError` and throws during the class-loading/initialization phase. Since this occurs entirely outside of `main()`'s `try` block, **neither** `catch (ArithmeticException e)` nor `catch (ExceptionInInitializerError e)` inside `main` ever executes — `main` itself never starts. The code compiles successfully (catching `Error` subtypes is syntactically legal), but the program terminates with an uncaught `ExceptionInInitializerError` (with a `Caused by: java.lang.ArithmeticException: / by zero`) printed to standard error.

---

**46. B**

`registry.addAnimal("Tiger", "BigCats", 1)`: `current = 0`, `0 >= 1` is false, `name` is non-null, so `enclosures.put("BigCats", 1)`. `registry.addAnimal("Lion", "BigCats", 1)`: `current = 1`, `1 >= 1` is true, so it throws `new EnclosureFullException("BigCats")`, with message `"Enclosure full: BigCats"`. In `main`, the catch clauses are ordered `AnimalNotFoundException`, then `EnclosureFullException`, then `ZooException` — both subclasses are siblings extending `ZooException`, listed before their common supertype, so all are reachable (option D is false). `EnclosureFullException` matches the second `catch`, printing `Full: Enclosure full: BigCats`.

---

**47. D**

`increment()` enters the `try` block: `counter++` makes `counter = 1`, and `return counter` evaluates and captures the value `1` to be returned (this captured value does not change even though `counter` itself can still change afterward, because `int` is a primitive — the return value `1` is already determined). The `finally` block then runs: `counter++` makes `counter = 2`, but this does not affect the already-captured return value. `increment()` returns `1`, so `result = 1`. After the call, `counter = 2`. The output is `1 2`.

---

**48. A, C, E**

`Throwable` has two direct subclasses relevant to the exam: `Exception` and `Error` (A is true). `Error` and all its subclasses are unchecked (C is true). `StackOverflowError` occurs when unbounded recursion exhausts the call stack (E is true). `RuntimeException` is a subclass of `Exception`, not a *direct* subclass of `Throwable` (B is false). It is **not** recommended to catch `Error` types like `OutOfMemoryError` for recovery purposes — the JVM may already be in an unstable state and recovery is generally not possible (D is false).

---

**49. A**

All three `Step` resources are constructed in order, printing `Start1 Start2 Start3 `. Inside the `try`, `s1.run()`, `s2.run()`, `s3.run()` print `Run1 Run2 Run3 `. On exiting the try block normally, resources close in **reverse** order: `s3` closes first (`End3 `, no exception since `failOnClose=false`), then `s2` closes (`End2 `, throws `Exception("Fail2")` since `failOnClose=true` — this becomes the primary exception), then `s1` closes (`End1 `, no exception). The `catch (Exception e)` then prints `Caught:Fail2`. The full output is `Start1 Start2 Start3 Run1 Run2 Run3 End3 End2 End1 Caught:Fail2`.

---

**50. B**

`compute(3)`: `3 % 2 == 0` is false, `3 > 0` is true, so it throws `new IllegalStateException("positive odd")`. This is caught in `main` by `catch (IllegalStateException e)`, setting `result = "error: positive odd"`. The code compiles fine — every path in `compute` either returns a value or throws (option D is false), and `IllegalStateException` is unchecked, so it does not need to be declared (option E is false). The output is `error: positive odd`.

---

**51. A**

`Locale.of("en", "US")` (the modern Java 19+ equivalent of the older `new Locale("en", "US")` constructor) creates a locale for English/United States. Its `toString()` representation uses an underscore separator: `en_US` (not a hyphen, so option C is false). `Locale.of("fr")` creates a locale with just the language `fr`, and its `toString()` is simply `fr`. `l1.getLanguage()` returns `"en"` and `l1.getCountry()` returns `"US"`, so the third line prints `en US`. `Locale.of()` accepts a single language argument or language+country (option D is false), and language/country codes are returned in their original case as provided, not uppercased (option E is false).

---

**52. A, B, E**

`en` (language only) is valid (A). `en_US` (language_COUNTRY) is valid (B). `fr_CA` (French Canadian) is valid (E). `EN_us` (C) has incorrect casing — by convention, language codes are lowercase and country codes are uppercase; while `Locale` may not throw for this, it does not represent a properly-formed standard locale string in `language_COUNTRY` form. `US` alone (D) is just a country code with no language, which is not a valid `language` or `language_COUNTRY` form. `enUS` (F) is missing the underscore separator required between language and country. The valid forms are A, B, and E.

---

**53. A**

`Locale.Builder` is a fluent builder: `setLanguage("de")` and `setRegion("AT")` configure the language and region, and `build()` produces the final `Locale` only when called (it does not need to be called "before" other setters — option D is false). The resulting locale's `toString()` produces `de_AT` (language first, then region, joined by underscore — not `de-AT` with a hyphen, and not reversed as in option B).

---

**54. B**

`Locale.Builder().setLanguage("e")` attempts to set a language subtag of length `1`. Per BCP 47, a language subtag must be **empty or 2–8 alphabetic characters** — a single character is invalid. `setLanguage("e")` throws `IllformedLocaleException` immediately (during the builder call, before `build()` is even reached), which is caught by `catch (IllformedLocaleException e)`, printing `invalid`.

---

**55. A**

`ResourceBundle.getBundle("Zoo", Locale.of("en", "US"))` resolves to `Zoo_en_US.properties` as the most specific matching bundle. The resolution then forms a hierarchy: `Zoo_en_US` → `Zoo_en` → `Zoo` (the base bundle), and keys are looked up in that order, falling back to less specific bundles only if a key is missing from the resolved bundle. `name` is not in `Zoo_en_US`, so it falls back to `Zoo_en`, which has `name=City Zoo`. `motto` is found directly in `Zoo_en_US`: `Wild about fun`. `hours` is not in `Zoo_en_US`, so it falls back to `Zoo_en`, which has `hours=9-5`. The output is `City Zoo` then `Wild about fun` then `9-5`.

---

**56. A**

`ResourceBundle.getBundle("Dolphins", Locale.of("fr"))` resolves to `Dolphins_fr.properties` as the best match (there is no `Dolphins_fr_XX` variant, and `fr` itself matches `Dolphins_fr`). The bundle hierarchy becomes `Dolphins_fr` → `Dolphins` (base) — note that `Dolphins_en` is **not** part of this hierarchy chain since the requested locale's language is `fr`, not `en`, and once a bundle for `fr` is found, only progressively-less-specific `fr`-related and base bundles are consulted (not `en` variants, even if `en` is the JVM default). `name` is found directly in `Dolphins_fr`: `Dauphin`. `age` is not in `Dolphins_fr`, so it falls back to the base `Dolphins.properties`, which has `age=0`. The output is `Dauphin` then `0`.

---

**57. A**

`ResourceBundle.getBundle("Messages", Locale.of("de", "DE"))` — no `Messages_de_DE` or `Messages_de` variants exist, so it falls back all the way to the base `Messages.properties`. (Java does not throw `MissingResourceException` from `getBundle` itself as long as *some* bundle, including the base, is found — option C is false.) `rb.getString("greeting")` returns `Hello`. `rb.containsKey("farewell")` returns `false` since no bundle in the hierarchy defines `farewell`. Calling `rb.getString("farewell")` then throws `MissingResourceException`, which is caught and prints `missing: farewell`. The output is `Hello` then `false` then `missing: farewell`.

---

**58. A**

`NumberFormat.getCurrencyInstance(Locale.US)` formats `1234.5` as US currency: `$1,234.50` (`,` as the thousands separator, `.` as the decimal separator, `$` prefix, two decimal places). `NumberFormat.getCurrencyInstance(Locale.GERMANY)` formats the same value using German conventions, where `.` is the thousands separator, `,` is the decimal separator, and the `€` symbol follows the number with a leading space: `1.234,50 €`. The output is `$1,234.50` then `1.234,50 €`.

---

**59. B**

`NumberFormat.getInstance(Locale.FRANCE)` returns a number formatter configured for French conventions, where `,` (not `.`) is the decimal separator. `NumberFormat.parse()` is lenient and parses only as much of the input as matches valid number syntax for the locale: reading `"12.5"`, it consumes the digits `12` and stops at `.` because `.` is not a recognized decimal or grouping character in the French format. The method returns the `Number` `12` (no `ParseException` is thrown, since at least part of the string was successfully parsed), and `System.out.println(n)` prints `12`.

---

**60. C**

`NumberFormat.getPercentInstance(Locale.US)` formats a fraction as a percentage by multiplying by 100 and appending `%`, with the result **rounded to the nearest whole number** by default (the default percent format has a maximum fraction digit count of 0). `0.4567 * 100 = 45.67`, which rounds to `46`. The output is `46%`.

---

**61. A**

`DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT)` produces a locale-dependent short date format. `withLocale()` is a valid method on `DateTimeFormatter` that returns a new formatter configured for the given locale (option E is false). For `Locale.US`, the SHORT date style for March 5, 2025 renders as `3/5/25` (M/d/yy, no leading zeros). For `Locale.of("en", "GB")`, the SHORT date style uses day-first ordering with leading zeros: `05/03/2025`. The output is `3/5/25` then `05/03/2025`.

---

**62. B**

The pattern `"hh:mm a"` uses `hh` for the **clock-hour-of-am-pm (1–12)** and `a` for the AM/PM marker — both are valid and supported by `LocalTime` (options D and E are false, since `LocalTime` does support time-of-day fields including am/pm). `LocalTime.of(14, 5)` represents 2:05 PM in 24-hour time. Formatted with `hh:mm a`, the hour `14` converts to `02` in 12-hour format, and since `14 >= 12`, the marker is `PM`. The output is `02:05 PM`.

---

**63. A**

In `DateTimeFormatter` patterns, a single quote `'` begins/ends a literal (escaped) text section, and **two consecutive single quotes (`''`) within or outside such a section represent a literal single-quote character**. The pattern `"MMMM d, ''yy''"` breaks down as: `MMMM` (full month name) → `July`, ` d, ` (literal) → ` 4, `, then `''` → a literal `'`, then `yy` (2-digit year) → `25`, then `''` → another literal `'`. The result is `July 4, '25'`.

---

**64. A**

`Locale.setDefault(Locale.of("en", "US"))` sets the overall default locale to `en_US`. `Locale.setDefault(Category.FORMAT, germany)` overrides **only** the `FORMAT` category default to `Locale.GERMANY`, leaving the `DISPLAY` category default as `en_US` — the two categories can be set independently (`Category` is a public nested enum and is fully accessible, so option E is false). `NumberFormat.getInstance()` uses the `FORMAT` category default, which is now Germany, so `1234.5` formats using German conventions: `1.234,5`. `Locale.getDefault(Category.DISPLAY).getLanguage()` still returns `"en"` since `DISPLAY` was never changed. The output is `1.234,5` then `en`.

---

**65. A**

`MessageFormat.format(pattern, args...)` substitutes `{0}`, `{1}`, etc., in the pattern with the corresponding positional arguments, converting them to strings as needed. `{0}` is replaced with `"Alice"` and `{1}` is replaced with `5` (auto-formatted as `"5"`). `MessageFormat.format` is a static convenience method that does not require a `Locale` argument (option D is false), and no `ParseException` occurs during formatting (that exception is relevant to `parse()`, not `format()`). The output is `Dear Alice, you have 5 new messages.`.

---

**66. A**

`props.setProperty("hours", "9-5")` sets a key. `props.getProperty("location")` — `"location"` was never set, and no default is provided, so it returns `null`. `props.getProperty("location", "Unknown")` — again `"location"` is absent, but a default value `"Unknown"` is provided as the second argument (a valid overload of `getProperty` — option C is false), so it returns `"Unknown"`. `props.getProperty("hours")` returns `"9-5"`. The output is `null` then `Unknown` then `9-5`. `Properties.getProperty()` never throws `MissingResourceException` (that's a `ResourceBundle` exception) — it simply returns `null` or the supplied default (option E is false).

---

**67. A, B, D, E**

Once a resource bundle is found for a locale, only that bundle and its progressively-less-specific "parent" bundles in the same family are searched for keys (A is true). If the requested locale has no matching bundle at all, Java falls back to the JVM default locale's bundles, and ultimately to the base (no-suffix) bundle (B is true). `ResourceBundle.getBundle("Name")` (single-argument form) uses `Locale.getDefault()` (D is true). `Properties`-backed bundles (`PropertyResourceBundle`) support `keySet()` for iteration (E is true). `getString()` throws `MissingResourceException` if the key is not found anywhere in the hierarchy — it does **not** return `null` (C is false). Java does not check the same locale's bundle "twice" in some special redundant way merely because the requested and default locales coincide — the resolution algorithm proceeds normally without duplication (F is false).

---

**68. A**

`NumberFormat.getCompactNumberInstance(Locale.US, Style.SHORT)` formats large numbers using abbreviated suffixes (K, M, B) with zero fraction digits by default. `7_123_456` formatted in SHORT style rounds to `7M`. `Style.LONG` produces the full word form with the same default rounding: `7 million`. `NumberFormat.Style` is a valid public nested enum (option E is false). The output is `7M` then `7 million`.

---

**69. B**

`Locale.Builder().setLanguage("en")` is valid (`en` is 2 alphabetic characters). `.setRegion("USA")` attempts to set a 3-character **alphabetic** region code. Per BCP 47, a region subtag must be either exactly **2 alphabetic characters** or exactly **3 digit characters** — `"USA"` (3 letters) satisfies neither, so `setRegion("USA")` throws `IllformedLocaleException` immediately. This is caught by `catch (IllformedLocaleException e)`, printing `caught`.

---

**70. A**

`String.format(Locale, format, args)` is a valid overload that applies locale-specific formatting conventions (option E is false). For `Locale.GERMANY`, `%,.2f` formats `1234567.891` using German grouping (`.` for thousands) and decimal (`,`) separators, rounded to two decimal places: `1.234.567,89`. For `Locale.US`, the same pattern uses `,` for thousands and `.` for decimals: `1,234,567.89`. The output is `1.234.567,89` then `1,234,567.89`.
