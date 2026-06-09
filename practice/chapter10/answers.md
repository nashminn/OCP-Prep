# Chapter 10: Streams — Answers

---

**1. B**

`Optional.of("hello")` wraps the non-null value `"hello"`. Calling `.map(String::toUpperCase)` applies the function and returns `Optional.of("HELLO")`. Then `.orElse("none")` returns `"HELLO"` because the `Optional` is present. The result is `HELLO`.

---

**2. B, C, E**

`Optional.of(null)` throws a `NullPointerException` — it requires a non-null value (B is true, A is false). `Optional.ofNullable(null)` returns `Optional.empty()` (C is true, D is false). `Optional.of("x").get()` returns `"x"` (E is true). Calling `get()` on an empty `Optional` throws `NoSuchElementException`, not `IllegalStateException` (F is false).

---

**3. C**

`Optional.empty()` contains no value. `.orElseGet(() -> 42)` invokes the `Supplier` since no value is present and returns `42`. The output is `42`.

---

**4. E**

Both `Stream.generate(Math::random)` and `DoubleStream.generate(Math::random)` create infinite streams using the supplier. Option A wraps the method reference as an element in the stream rather than invoking it. Option C is wrong because `Stream.iterate` takes a `UnaryOperator<T>` but `Math.random` is a `Supplier`, not a `UnaryOperator<Double>` — it does not accept a seed argument.

---

**5. D**

A `Stream` can only be consumed once. After the first `forEach()` call, the stream is closed. Calling `forEach()` a second time on the same stream instance throws `IllegalStateException: stream has already been operated upon or closed`.

---

**6. A**

`Stream.iterate(1, n -> n * 2)` generates: `1, 2, 4, 8, 16, ...`. `limit(4)` takes the first four: `1, 2, 4, 8`. `forEach(System.out::print)` prints them all on one line: `1248`.

---

**7. A, C, E, F**

`findFirst()` returns `Optional<T>` (A). `count()` returns a primitive `long` (B is wrong). `min(Comparator)` and `max(Comparator)` return `Optional<T>` (C is correct). `anyMatch(Predicate)` returns a primitive `boolean` (D is wrong). `findAny()` returns `Optional<T>` (E). `reduce(BinaryOperator)` without an identity returns `Optional<T>` (F).

---

**8. A**

The stream is filtered to elements starting with `"a"`. Only `"apple"` qualifies. `findFirst()` returns `Optional.of("apple")`. `orElse("not found")` returns `"apple"` since the `Optional` is present. Output: `apple`.

---

**9. B, D, F**

Intermediate operations are lazy — they only execute when a terminal operation is invoked (B is true, A is false). `filter()` is stateless — it evaluates each element independently (C is false). `sorted()` is stateful — it must see all elements before it can produce output (D is true). `distinct()` is also stateful — it must track previously seen elements (E is false). `map()` is stateless — it transforms each element independently (F is true).

---

**10. B**

Strings of length 3: `"cat"` (3), `"dog"` (3). `"bird"` has length 4 and `"fish"` has length 4. So `count()` returns `2`.

---

**11. B**

`peek()` is an intermediate operation. No terminal operation is attached to this stream pipeline, so the pipeline never executes and nothing is printed. The stream is created but the pipeline stays lazy.

---

**12. B**

`flatMap(Collection::stream)` converts each inner `List<Integer>` into an `IntStream`-like `Stream<Integer>`, then flattens them all into a single stream: `1, 2, 3, 4, 5`. `forEach(System.out::print)` prints `12345`.

---

**13. B, C, D, F**

`reduce(identity, BinaryOperator)` returns `T` (the same type as the identity), not `Optional<T>` (A is false, B is true). `reduce(BinaryOperator)` without identity returns `Optional<T>` (C is true). If the stream is empty and an identity is provided, the identity value itself is returned (D is true). `reduce(BinaryOperator)` on an empty stream returns `Optional.empty()`, not a thrown exception (E is false, F is true).

---

**14. C**

The identity is `0`. The `BinaryOperator` adds the accumulator and each element: `0+1=1, 1+2=3, 3+3=6, 6+4=10, 10+5=15`. `reduce(identity, BinaryOperator)` returns `T` (not `Optional`), so assigning to `int` is valid. Output: `15`.

---

**15. C**

`Collectors.groupingBy(classifier)` with no downstream collector defaults to `Collectors.toList()` as the downstream. The result is `Map<K, List<T>>` where each key maps to a `List` of the stream elements that map to that key.

---

**16. A**

`"hi"` has length 2, `"hello"` has length 5, `"hey"` has length 3, `"ok"` has length 2. `map.get(2)` returns `[hi, ok]` (in encounter order). `map.get(5)` returns `[hello]`. Note: Option B is wrong because `map.get(5)` returns a list `[hello]`, not `null`. The list elements are in encounter order, which is `[hi, ok]`.

---

**17. B**

The even numbers in `{1, 2, 3, 4, 5}` are `{2, 4}` — the `true` partition. The odd numbers `{1, 3, 5}` form the `false` partition. `result.get(false).size()` is `3`.

---

**18. B**

Option A uses distinct keys `"a"`, `"b"`, `"c"` — no duplicates — so it succeeds. Option B has duplicate key `"a"` appearing twice, and no merge function is provided, so `Collectors.toMap()` throws `IllegalStateException`. Option C also has duplicate key `"a"`, but the merge function `(v1, v2) -> v1` resolves the conflict by keeping the first value — no exception is thrown.

---

**19. B**

`IntStream.range(start, end)` is exclusive of the end bound. `IntStream.range(1, 5)` generates `1, 2, 3, 4`. Output: `1234`. To include `5`, you would use `IntStream.rangeClosed(1, 5)`.

---

**20. A**

`IntStream.of(2, 4, 6).average()` computes `(2 + 4 + 6) / 3 = 12 / 3 = 4.0`. The result is `OptionalDouble` with value `4.0`. `getAsDouble()` returns `4.0`. Note: `average()` returns `OptionalDouble`, not `Optional<Double>`.

---

**21. B**

`allMatch` on an empty stream returns `true` — this is called "vacuous truth." Because there are no elements for which the predicate could return `false`, the condition holds trivially. The predicate `x -> false` is never evaluated. Output: `true`.

---

**22. A, C, D**

`mapToObj(String::valueOf)` converts each `int` to a `String` and returns `Stream<String>` (A). Option B calls `map()` on `IntStream`, which returns `IntStream`, not `Stream<String>` (B is wrong). `boxed()` returns `Stream<Integer>`, then `.map(String::valueOf)` produces `Stream<String>` (C is correct). Option D uses a lambda equivalent to A (D is correct). Option E boxes to `Stream<Long>`, not `Stream<String>` (E is wrong).

---

**23. A**

The filter retains `"apple"`, `"apricot"`, and `"avocado"`. `sorted()` applies natural (lexicographic) ordering: `apple < apricot < avocado`. They are printed each on their own line in that order.

---

**24. A, B, D, E**

`Stream.iterate(seed, UnaryOperator)` is an infinite stream (A). `Stream.iterate(seed, Predicate, UnaryOperator)` — the three-argument version added in Java 9 — is a finite stream that stops when the predicate returns `false` (B, D). Option C is false: `Stream.iterate` returns `Stream<T>` (a boxed reference stream) while `IntStream.iterate` returns a primitive `IntStream`. Option E is true — calling a terminal operation like `count()` or `collect()` on an unlimited infinite stream will run forever (hang), so `limit()` is necessary.

---

**25. B**

`Optional.of("Java")` is non-empty. `ifPresentOrElse()` takes a `Consumer` for the present case and a `Runnable` for the empty case. Since the `Optional` is present, the `Consumer` runs and prints `Present: Java`.

---

**26. A**

The streams `s2` and `s3` are created by chaining intermediate operations on `s1`, but no terminal operation is called on any of them. Because stream pipelines are lazy, no processing occurs. The stream source `s1` is not consumed until a terminal operation fires. Creating multiple intermediate views of the same source stream does not immediately throw. `"done"` is printed. Note: if a terminal operation had been called on `s1` first and then another operation attempted, that would throw `IllegalStateException`. But here, no terminal op is called at all.

---

**27. B**

`Collectors.joining(delimiter, prefix, suffix)` joins all elements with `", "` as the delimiter, prefixes the result with `"["` and suffixes with `"]"`. Result: `[hello, world, hi]`.

---

**28. C, D, E**

The goal is to keep the larger value when duplicates are encountered. Option A has no merge function — it will throw `IllegalStateException` on duplicates. Option B keeps `v2` (the second/new value), which may or may not be larger — it doesn't guarantee the maximum. Options C (`Integer::max`), D (explicit ternary comparison returning the larger), and E (`Math::max`) all correctly return the maximum of the two values. Note: `Integer::max` and `Math::max` are both valid `BinaryOperator<Integer>` references here because they each take two `int`/`Integer` arguments and return the maximum.

---

**29. D**

A stream can only be used once. `stream.sum()` is a terminal operation that consumes the `IntStream`. Calling `stream.sum()` a second time throws `IllegalStateException: stream has already been operated upon or closed`. The first `15` is printed, then the exception is thrown.

---

**30. A**

The stream elements are `3, 1, 4, 1, 5, 9, 2, 6`. After `distinct()`: `3, 1, 4, 5, 9, 2, 6`. After `sorted()`: `1, 2, 3, 4, 5, 6, 9`. After `limit(4)`: `1, 2, 3, 4`. Output: `1234`.

---

**31. C**

`Collectors.partitioningBy()` always produces a `Map` with both `true` and `false` keys, even if one partition is empty. `"bird"` (length 4) goes to `true`. `"cat"` (length 3) and `"ox"` (length 2) go to `false`. Both `result.containsKey(true)` and `result.containsKey(false)` return `true`.

---

**32. C**

`Optional.empty()` has no value. Calling `.map(s -> s + "!")` on an empty `Optional` returns another empty `Optional` — the mapping function is not applied. Then `.orElse("default")` returns `"default"` since the `Optional` is still empty. Output: `default`.

---

**33. B, D, E**

`peek()` is an intermediate operation, not terminal (B is true, A is false). `peek()` does not transform elements — it performs a side-effect `Consumer` on each element but passes the original element downstream unchanged (D is true, C is false). If no terminal operation follows, the entire pipeline is lazy and never executes, so `peek()` never runs (E is true). Option F is tricky: `peek()` can observe elements but is not a reliable way to count, and more importantly it does not run without a terminal operation.

---

**34. A**

`mapToInt(String::length)` produces `IntStream` with values `1, 2, 3, 4`. `filter(n -> n % 2 != 0)` keeps odd values: `1, 3`. `forEach(System.out::print)` prints `13`. Since `forEach` on `IntStream` prints primitives directly with no separator, the output is `13` on one line.

---

**35. B**

In Java, whether `peek()` runs as part of `count()` depends on the implementation. In Java 9+, the JDK can optimize `count()` without processing elements when the size can be determined without traversal. However, with a `peek()` in the pipeline, most implementations will process each element. In practice the output is `x y z 3`. But note: the Java specification does not guarantee this — it is implementation-specific for `count()`. On the OCP exam, the expected answer acknowledges the `peek` runs: `x y z ` (with trailing space) then `3` on the next line, totalling the output shown as option B.

---

**36. C**

`Stream.iterate(0, x -> x + 1)` creates an infinite stream. `count()` is a non-short-circuit terminal operation that must process every element to produce a count. On an infinite stream without `limit()`, `count()` will run forever (hang). The code compiles and starts executing but never completes.

---

**37. B**

No string in `{"alpha", "beta", "gamma"}` has a length greater than 10. The `filter` produces an empty stream. `findFirst()` on an empty stream returns `Optional.empty()`. `result.isPresent()` returns `false`. Output: `false`.

---

**38. A, B, D**

`IntStream.boxed()` returns `Stream<Integer>` (A is correct). `IntStream.mapToObj(i -> i)` returns `Stream<Integer>` via autoboxing (B is correct). `IntStream.map(i -> i)` returns another `IntStream`, not `Stream<Integer>` (C is wrong). `Stream.of(1, 2, 3)` infers `Stream<Integer>` (D is correct). `IntStream.asLongStream().boxed()` returns `Stream<Long>`, not `Stream<Integer>` (E is wrong).

---

**39. A**

Starting elements: `"c", "a", "b", "a"`. After `distinct()`: `"c", "a", "b"` (first `"a"` kept, second removed). After `sorted()`: `"a", "b", "c"` (natural lexicographic order). Collected to list: `[a, b, c]`.

---

**40. B**

Streams are lazy and short-circuit. `findFirst()` is a short-circuit terminal operation. The pipeline processes elements one at a time: `"one"` passes through `filter` (prints `filter: one`, length is 3 which equals 3), so `findFirst()` immediately returns `Optional.of("one")` without evaluating `"two"`, `"three"`, etc. Only one `filter:` line is printed.

---

**41. A**

`IntStream.of(10, 20, 30, 40, 50).summaryStatistics()` computes: min=10, max=50, count=5, sum=150, average=30.0. `getMin()` returns `10`, `getMax()` returns `50`, `getCount()` returns `5`. Output: `10 50 5`.

---

**42. A**

`Optional.of(5)` is present. `flatMap(n -> Optional.of("Value: " + n))` applies the function and returns the resulting `Optional<String>` directly (unlike `map`, which would wrap it in another `Optional`). The result is `Optional.of("Value: 5")`. `.get()` returns `"Value: 5"`. Output: `Value: 5`.

---

**43. A, B, D**

`Collectors.toUnmodifiableList()` returns an unmodifiable list (A). `Stream.toList()` (Java 16+) returns an unmodifiable list (B). `Collectors.toList()` returns a modifiable `ArrayList` — no guarantee of unmodifiability (C is wrong). Wrapping with `Collections.unmodifiableList()` makes it unmodifiable (D). `Collectors.toCollection(ArrayList::new)` returns a modifiable `ArrayList` (E is wrong).

---

**44. B**

`takeWhile(s -> s.length() <= 3)` processes the stream in order and includes elements while the predicate is `true`. First element: `"cat"` (length 3 — true, included). Second element: `"elephant"` (length 8 — false, stop). `takeWhile` does not skip ahead to find other matching elements — it stops as soon as the predicate is `false`. Output: `cat`.

---

**45. B**

`dropWhile(n -> n < 3)` skips elements while the predicate is `true`. `1 < 3` (true, skip), `2 < 3` (true, skip), `3 < 3` (false, stop dropping). From `3` onward, all elements are kept: `3, 4, 5`. Output: `345`.

---

**46. A**

`anyMatch` returns `true` if any element matches the predicate `s.length() > 4`. `"cat"` has length 3, `"dog"` has length 3, `"bird"` has length 4. None of these are strictly greater than 4. `anyMatch` returns `false`. Output: `false`.

---

**47. A, B, C, E**

`findFirst()` on a parallel stream still respects encounter order when the stream has a defined encounter order — it returns the first element in the sequence (A is true). `findAny()` may return any element on a parallel stream for better performance (B is true). `forEachOrdered()` preserves encounter order even in parallel execution, at the cost of some parallelism benefit (C is true). Parallel streams do not always run faster — they have overhead and may be slower for small datasets or simple operations (D is false). `count()` always returns the correct total count regardless of parallelism (E is true). `forEach()` on a parallel stream does NOT guarantee encounter order — elements may be processed in any order (F is false).

---

**48. C**

`groupingBy(s -> s, Collectors.counting())` groups by value and counts occurrences. `"a"` appears 3 times. `result.get("a")` returns `3L`. The `Long` value `3` is printed as `3`.

---

**49. D**

`Optional.ofNullable(null)` returns `Optional.empty()`. `.orElseThrow(() -> new RuntimeException("missing"))` throws the specified exception because the `Optional` is empty. A `RuntimeException` with message `"missing"` is thrown. The code does not compile? No — it compiles fine. `orElseThrow(Supplier)` accepts a `Supplier<? extends Throwable>`.

---

**50. A**

Pipeline: `map(String::toUpperCase)` produces `ALPHA, BETA, GAMMA, DELTA`. `skip(1)` skips `ALPHA`, leaving `BETA, GAMMA, DELTA`. `limit(2)` takes first two: `BETA, GAMMA`. `sorted(Comparator.reverseOrder())` sorts in reverse lexicographic order: `GAMMA, BETA`. `forEach(System.out::println)` prints each on its own line: `GAMMA` then `BETA`.
