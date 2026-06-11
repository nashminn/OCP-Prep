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

---

**51. D**

`Stream.toList()` (added in Java 16) collects the stream elements into a `List` that is **unmodifiable** — it is not necessarily an `ArrayList` and does not support `add()`, `remove()`, or `set()`. The first two lines compile fine, and `[red, green, blue]` would print correctly if reached, but `list.add("yellow")` throws `UnsupportedOperationException` before the `println` executes. This is different from `Collectors.toList()`, which returns a mutable `ArrayList` with no such restriction.

---

**52. E**

`Collectors.toMap(keyMapper, valueMapper)` (the two-argument overload) throws `IllegalStateException` at runtime if the key mapper produces duplicate keys, because there is no merge function to resolve the conflict. Here, `"ant"`, `"bee"`, `"cat"`, and `"dog"` all map to key `3`, immediately causing a duplicate-key collision at runtime. This is **not** a compile-time error — the code compiles fine and fails only when the stream is processed.

---

**53. B**

`Collectors.toMap(keyMapper, valueMapper, mergeFunction)` uses `Integer::sum` to combine values when keys collide. The composite key `"East-Widget"` is produced for both `Sale("East","Widget",10)` and `Sale("East","Widget",7)`, so they merge to `10 + 7 = 17`. Similarly `"West-Gadget"` merges `5 + 3 = 8`. Output: `17` then `8`.

---

**54. C**

`Collectors.groupingBy(String::length, Collectors.counting())` groups elements by their length and counts how many fall into each group, producing a `Map<Integer, Long>`. Lengths: `"kit"`=3, `"cat"`=3, `"dog"`=3, `"bird"`=4, `"owl"`=3, `"ox"`=2. That gives one 2-letter word (`ox`), four 3-letter words (`kit`, `cat`, `dog`, `owl`), and one 4-letter word (`bird`). The map is `{2=1, 3=4, 4=1}` — option C. Counting collectors always return `Map<K, Long>` with a count per group, never `List`s of the original elements (ruling out option B, which shows grouped lists — that would be the result of `groupingBy` with no downstream collector at all).

---

**55. A**

`Collectors.groupingBy(String::length, Collectors.mapping(s -> s.substring(0,1), Collectors.joining(",")))` first groups strings by length, then for each group applies `mapping()` to extract the first character of each string and joins those characters with `,`. The input list is `"apple"`(5), `"ant"`(3), `"bear"`(4), `"bat"`(3), `"cat"`(3). Length 3 → `"ant"`, `"bat"`, `"cat"` → first letters `"a"`, `"b"`, `"c"` → joined `"a,b,c"`. Length 4 → `"bear"` → `"b"`. Length 5 → `"apple"` → `"a"`. So `result.get(3)` is `"a,b,c"` and `result.get(4)` is `"b"`. The downstream `mapping()` + `joining()` combination produces a single `String` per group (not a `List`), ruling out option C. Output: `a,b,c` then `b`.

---

**56. B**

`Collectors.maxBy(Comparator)` is a valid downstream collector for `groupingBy` and produces `Map<K, Optional<T>>` — each group's maximum is wrapped in an `Optional` because a group could theoretically be empty (though `groupingBy` never actually creates empty groups, since a key only exists if at least one element produced it). For `"Eng"`, comparing salaries `90_000` (Ann) vs `95_000` (Bob), the max is Bob. For `"HR"`, the only employee is Cid, so `top.get("HR")` is `Optional.of(Cid)`, not empty — `.get()` returns Cid successfully. Output: `Bob` then `Cid`.

---

**57. B**

`Collectors.partitioningBy()` always produces a `Map` with both `true` and `false` keys, even if one partition has no matching elements. None of `2, 4, 6, 8` is greater than `100`, so the `true` partition is an empty list — `result.get(true)` returns `[]`, never `null`. `result.containsKey(false)` is `true` because the `false` key always exists in a partitioned map (here it holds all four elements). Output: `[]` then `true`.

---

**58. B**

`Collectors.partitioningBy(predicate, downstream)` partitions into `true`/`false` groups and applies `Collectors.counting()` to each group instead of collecting elements into a list. Even numbers in `{1,2,3,4,5,6,7}` are `2, 4, 6` (3 elements), and odd numbers are `1, 3, 5, 7` (4 elements). So `result.get(false) = 4L` and `result.get(true) = 3L`. The partitioned map always prints with the `false` key first, giving `{false=4, true=3}`. The downstream `counting()` collector produces `Long` counts, not `List`s of elements, ruling out option C.

---

**59. B**

`flatMap(List::stream)` flattens each inner `List<String>` into a single stream of strings, including the empty list (which contributes zero elements). The combined stream is `"a", "b", "c"` — 3 elements total. `count()` returns `3`.

---

**60. B**

`Optional::stream` (added in Java 9) converts an `Optional<T>` into a `Stream<T>` containing zero elements if empty, or one element if present. Used inside `flatMap`, this elegantly filters out empty `Optional`s while unwrapping the present ones. The flattened stream contains `"alpha"` and `"beta"` (the two empty `Optional`s contribute nothing). `Collectors.joining(",")` with no prefix/suffix joins them with a comma: `alpha,beta`.

---

**61. B**

`Stream.iterate(seed, hasNext, next)` (the three-argument, Java 9+ overload) is a **bounded** stream: it generates `seed`, then keeps applying `next` and yielding values as long as `hasNext` returns `true` for the *current* value, checked *before* that value is emitted. Sequence: `1` (`1 <= 50`, keep), `3` (`3 <= 50`, keep), `9` (`9 <= 50`, keep), `27` (`27 <= 50`, keep), `81` (`81 <= 50` is `false`, stop — `81` is **not** included). Result: `[1, 3, 9, 27]`.

---

**62. A**

`Stream.iterate(2, n -> n * n)` is the two-argument (infinite) overload, generating `2, 4, 16, 256, 65536, ...` (each term is the square of the previous). `limit(4)` takes the first four: `2, 4, 16, 256`. `map(n -> n + 1)` adds 1 to each: `3, 5, 17, 257`. Output: `[3, 5, 17, 257]`.

---

**63. A, C**

`Stream.generate(Supplier<T>)` always produces an infinite stream (A is true). `Math::random` is a valid method reference for `Supplier<Double>` because `Math.random()` returns `double`, which autoboxes to `Double` (B is false — it compiles fine). Without `limit(5)`, calling a non-short-circuiting terminal operation like `toList()` directly on an infinite stream would never terminate (C is true). `values` will contain 5 elements, but they are not *guaranteed* to be distinct — `Math.random()` could theoretically (if astronomically unlikely) produce a duplicate, so "always exactly 5 distinct values" is not guaranteed (D is false). `Stream.generate` takes a `Supplier<T>`, not a `UnaryOperator<T>` — that's `Stream.iterate` (E is false).

---

**64. B**

The pipeline consists entirely of intermediate operations: `peek()`, `filter()`, and `map()`. There is no terminal operation, so the entire pipeline remains unexecuted — none of the lambdas ever run. Only `"end"` is printed (from the unrelated `System.out.println("end")` statement, which executes regardless).

---

**65. B**

This is a multi-step trace. The pipeline processes one element at a time through the *entire* chain (element-by-element, not stage-by-stage). For `"aa"` (length 2): first `peek` prints `1:aa `, `filter` (length > 1) passes, second `peek` runs *before* `map(String::toUpperCase)`, so it sees the original-case string and prints `2:aa ` — then `map` produces `AA`. For `"b"` (length 1): `1:b ` → filter fails (length not > 1) → stops, no second peek. For `"ccc"`: `1:ccc ` → filter passes → `2:ccc ` → mapped to `CCC`. For `"dd"`: `1:dd ` → filter passes → `2:dd ` → mapped to `DD`. For `"e"`: `1:e ` → filter fails → stops. The full print sequence is `1:aa 2:aa 1:b 1:ccc 2:ccc 1:dd 2:dd 1:e ` and the final list is `[AA, CCC, DD]`. The key trap is that the second `peek` sees the **pre-map** (lowercase) value, since `map` runs after it in the pipeline — ruling out option A, which incorrectly shows the second `peek` printing uppercase values.

---

**66. B**

`anyMatch` is short-circuiting: it stops as soon as it finds an element matching the predicate (or the stream ends). The pipeline processes elements one at a time: `"a"` (peek prints `checking: a`, length 1 ≠ 2, no match), `"bb"` (peek prints `checking: bb`, length 2 == 2, match found — stop). `"ccc"` and `"dddd"` are never touched. Output: `checking: a`, `checking: bb`, then `found = true`.

---

**67. B**

`findFirst()` is short-circuiting, but the `filter` predicate (with its `println` side effect) must still be evaluated for each element until one passes the filter. Processing in order: `1` → `testing 1`, `1 % 2 != 0`, fails. `3` → `testing 3`, fails. `5` → `testing 5`, fails. `6` → `testing 6`, `6 % 2 == 0`, passes — `findFirst()` returns `Optional.of(6)` and stops; `9` is never tested. Output: `testing 1`, `testing 3`, `testing 5`, `testing 6`, then `found: 6`.

---

**68. B**

Each call to `Stream.iterate(1, n -> n + 1)` creates a fresh, independent infinite stream `1, 2, 3, 4, 5, ...`; the second statement is unaffected by the first. `anyMatch(n -> n == 5)` is short-circuiting: it stops as soon as it finds `5`, returning `true` — `b1 = true`. `noneMatch(n -> n == 5)` is also short-circuiting: it returns `false` as soon as it finds an element that *does* match the predicate (since "none match" is now disproven), without needing to scan the rest of the infinite stream. It encounters `1, 2, 3, 4` (no match, keep going) and then `5` (matches, stop) — returning `false` immediately. Neither call hangs because both predicates are satisfied within the first five elements. Output: `b1 = true` then `b2 = false`.

---

**69. B**

`Stream.toList()` is a terminal operation. After `stream.map(n -> n * 2).toList()` executes and prints `[2, 4, 6]`, the original `stream` has been consumed ("operated upon"). Calling `stream.map(n -> n * 3)` again on the same `Stream` reference throws `IllegalStateException: stream has already been operated upon or closed` — this happens when the second pipeline's terminal operation (`toList()`) is invoked, but since `map()` itself is lazy, the actual exception is thrown as soon as any operation is attempted on the already-closed stream's pipeline. In practice, the `IllegalStateException` is thrown when `toList()` (or even `map()`, depending on JDK internals) is called on the spent stream. Output: `[2, 4, 6]` then an `IllegalStateException` is thrown.

---

**70. A**

`IntStream.average()` returns `OptionalDouble` (a primitive-specialized `Optional`), not `Optional<Double>`. `avg.getClass().getSimpleName()` returns `"OptionalDouble"`. The average of `1, 2, 3, 4` is `(1+2+3+4)/4 = 10/4 = 2.5`. `avg.getAsDouble()` returns `2.5`, and `Optional.of(2.5)` wraps it as `Optional<Double>`; `.get()` returns `2.5`. Output: `OptionalDouble` then `2.5`.

---

**71. A, B, D, E**

`is.boxed()` converts `IntStream` to `Stream<Integer>` (A is true). `asLongStream()` converts each `int` element to a `long`, preserving the element count (B is true). `mapToObj(Integer::toString)` converts `IntStream` to `Stream<String>` via the supplied mapper, **not** `IntStream` (C is false). All four declarations compile without error (D is true). `IntStream.rangeClosed(1, 3)` is inclusive of both bounds, producing `1, 2, 3` (E is true).

---

**72. A**

`Stream.concat(a, b)` lazily concatenates two streams: elements of `a` are consumed first, then elements of `b`. Here `finite` (`100, 200, 300`) is exhausted first, then `infinite` (`1, 2, 3, ...`) begins supplying elements. `limit(5)` only needs the first 5 elements overall: `100, 200, 300` (from `finite`) plus `1, 2` (the first two from `infinite`). Because `limit` is short-circuiting, the infinite stream is never fully evaluated. Output: `[100, 200, 300, 1, 2]`.

---

**73. A**

`Comparator.comparingInt(Player::score).reversed()` sorts by descending score first. `Cid` has score 70 (highest), so `Cid` comes first. `Ann` and `Ben` are tied at score 50, so the tiebreaker `.thenComparing(Player::age)` sorts them by ascending age: `Ben` (19) before `Ann` (22). Final order: `Cid`, `Ben`, `Ann`.

---

**74. A**

`Comparator.comparingInt(String::length).reversed()` sorts by descending length first: `"apple"`(5) is longest, then the length-4 words `"kiwi"`, `"date"`, `"plum"`, then `"fig"`(3) is shortest. `.thenComparing(Comparator.naturalOrder())` breaks ties among same-length words using natural (lexicographic) `String` ordering. The three length-4 words sorted naturally are `"date"`, `"kiwi"`, `"plum"`. Final order: `["apple", "date", "kiwi", "plum", "fig"]`.

---

**75. A**

`Comparator.nullsFirst(Comparator.naturalOrder())` places all `null` elements at the start of the sorted list (in their relative encounter order among themselves, since they're considered equal to each other), followed by the non-null elements sorted naturally (case-sensitive lexicographic order, where uppercase letters sort before lowercase: `"Ann"` < `"Bob"` < `"cid"`). Output: `[null, null, Ann, Bob, cid]`.

---

**76. D**

`sorted()` with no arguments calls `Comparator.naturalOrder()`, which requires the elements to implement `Comparable`. `Box` does not implement `Comparable<Box>`. The code **compiles** because of generic type erasure — the compiler cannot statically verify at the `sorted()` call site that `Box` is `Comparable` (the no-arg `sorted()` method has the signature `sorted()` on `Stream<T>` without a `T extends Comparable` bound enforced at compile time for this overload usage in this context). At runtime, when `sorted()` actually tries to compare two `Box` instances, it attempts to cast them to `Comparable`, which fails with `ClassCastException: class Box cannot be cast to class java.lang.Comparable`.

---

**77. A**

`Collectors.summarizingInt(String::length)` computes statistics over the lengths of `"pear"`(4), `"fig"`(3), `"apple"`(5), `"kiwi"`(4). Sum = `4+3+5+4 = 16`. Max = `5`. Average = `16/4 = 4.0`. Output: `16 5 4.0`. Note that `getAverage()` always returns a `double`, so it prints `4.0` (with the decimal), not `4`.

---

**78. B**

`Optional.map(Function)` wraps the function's return value in a *new* `Optional` — if the function itself returns an `Optional<Integer>`, the result becomes `Optional<Optional<Integer>>` (a nested `Optional`). `Optional.flatMap(Function)` does **not** add an extra wrapping layer — it expects the function to return an `Optional` directly and uses that as the result. `nested` is `Optional[Optional[5]]`, `flat` is `Optional[5]`, and `flat.get()` returns `5`.

---

**79. B**

`Optional.or(Supplier<? extends Optional<? extends T>>)` (Java 9+) returns the original `Optional` unchanged if it is already present — the `Supplier` is **not** invoked in that case (it is only invoked lazily if the original `Optional` is empty). Since `primary` is `Optional.of("primary-value")` (present), `or()` returns `primary` directly without calling the lambda, so `"computing fallback"` is never printed. Output: `primary-value` only.

---

**80. B**

`reduce(identity, accumulator, combiner)` — for a **sequential** stream, the `combiner` function is never invoked. The combiner exists only to merge partial results computed by different threads in a **parallel** stream. The accumulator alone processes all elements sequentially: `0 + "a".length() = 1`, `1 + "bb".length() = 3`, `3 + "ccc".length() = 6`. Output: `total = 6`, with no `combiner:` lines printed.

---

**81. A**

`current` is declared fresh inside each loop iteration and assigned `i`'s value at that point; `current` itself is never reassigned afterward, making it effectively final and legal to capture in the lambda. Each lambda captures its *own* `current` variable with the value it had at creation time: `1`, `2`, and `3` respectively. Output: `Task 1`, `Task 2`, `Task 3`.

---

**82. A, D, E**

`String::toUpperCase` is an unbound instance method reference — for each stream element `s` (the receiver), it calls `s.toUpperCase()`, exactly matching the lambda (A is correct). `s::toUpperCase` does not compile here because `s` is not a variable in scope outside the lambda — there's no such bound instance available (B is wrong). `String.valueOf::toUpperCase` is not valid syntax — `String.valueOf` is a method, not an object reference you can call `::` on (C is wrong). `(String s) -> s.toUpperCase()` is an equivalent lambda with an explicit type (D is correct). `s -> { return s.toUpperCase(); }` is an equivalent lambda using a block body with `return` (E is correct). `toUpperCase()` alone is not a valid lambda or method reference syntax (F is wrong).

---

**83. A**

`map(n -> new Point(n, n * n))` transforms each `Integer` into a new `Point` object via a lambda that calls the constructor — this is perfectly legal (a constructor reference `Point::new` would also work but is not required). The resulting `Stream<Point>` is collected via `toList()`. Since `Point` overrides `toString()` to return `"(x,y)"`, printing the list invokes `toString()` on each element. Output: `[(1,1), (2,4), (3,9)]`.

---

**84. A**

`Integer::valueOf` here is used as a `Function<String, Integer>` (the type required by `map()` on a `Stream<String>`). Although `Integer` has multiple overloads of `valueOf` (`valueOf(String)`, `valueOf(int)`, `valueOf(String, int)`), the target type `Function<String, Integer>` from `map()`'s context disambiguates the reference to `valueOf(String)` — this is a well-known *resolvable* case, not an ambiguous one, because the functional interface's parameter type (`String`) narrows the overload to exactly one match. The `Converter` interface is unrelated and unused but does not cause a compile error by merely being declared. `map(Integer::valueOf)` converts each `String` to an `Integer` via auto-parsing. Output: `[1, 2, 3]` (as boxed `Integer` values, printed without quotes).

---

**85. A**

`Collectors.joining(delimiter, prefix, suffix)` on an empty stream produces just the `prefix` concatenated with the `suffix` (no elements, no delimiters needed) — `"[" + "]" = "[]"`. The no-argument `Collectors.joining()` on an empty stream produces an empty string `""` (not `null` — `joining()` always returns a `String`, never `null`, even for zero elements). Output: `[]` then an empty line (empty string).

---

**86. A**

`Collectors.teeing(downstream1, downstream2, merger)` (Java 12+) applies both downstream collectors to the *same* stream and combines their results with a `BiFunction` — this is exactly the two-`Collector`-plus-`BiFunction` signature used here, so option D's claim is incorrect. `Collectors.counting()` counts all 3 shapes (option B's `2` is wrong). The area sum is `4π + 9 + π = 5π + 9 ≈ 15.708 + 9 ≈ 24.71` (`Circle(2)` → `π·2² ≈ 12.566`, `Square(3)` → `3² = 9`, `Circle(1)` → `π·1² ≈ 3.1416`). The `switch` expression over the sealed `Shape` hierarchy covers both `Circle` and `Square`, making it exhaustive and allowing it to compile as an expression without a `default` branch (ruling out E). Output: `3` then `24.71`.

---

**87. A**

`List.reversed()` (Java 21, part of `SequencedCollection`) returns a **view** of the list in reverse order — it does not mutate the original list, it just provides a reversed-order view backed by the same data. `nums.reversed()` produces a view ordered `[5, 4, 3, 2, 1]`; `.stream()` creates a stream over that reversed view. Filtering for even numbers from `[5, 4, 3, 2, 1]` (in that order) gives `[4, 2]`. The original `nums` list reference itself is unchanged by calling `reversed()` — it still prints as `[1, 2, 3, 4, 5]`. Output: `[4, 2]` then `[1, 2, 3, 4, 5]`.

---

**88. A**

The `groupingBy` classifier uses a `switch` expression over the sealed `Event` hierarchy with all three permitted subtypes (`Login`, `Logout`, `Error`) covered — this is exhaustive and compiles without a `default` branch (ruling out D). Counting the events: `Login` appears for `"alice"` and `"bob"` (2 total), `Logout` appears once (`"alice"`), `Error` appears 3 times (`"E1"`, `"E2"`, `"E3"`). `grouped.get("ERROR").size()` is `3`. `grouped.get("LOGOUT").size()` is `1`. `grouped.containsKey("LOGIN")` is `true` (2 logins exist, so the key is present). Output: `3` then `1` then `true`.

---

**89. B**

`Collectors.toUnmodifiableList()` (Java 10+) collects into an immutable `List`. The list prints normally — `[5, 3, 1, 4, 2]` (in encounter order; `toUnmodifiableList()` does **not** sort) — but `result.set(0, 99)` throws `UnsupportedOperationException` because the list does not support structural or element modification.

---

**90. A**

`Arrays.stream(values)` creates a `DoubleStream` from `{1.5, 2.5, 3.0}`. `.mapToObj(d -> d * 2)` converts each `double` to a boxed `Double` (via the lambda computing `d * 2`), producing a `Stream<Double>` with values `3.0, 5.0, 6.0`. `.mapToDouble(Double::doubleValue)` converts back to a `DoubleStream` with the same values. `.sum()` computes `3.0 + 5.0 + 6.0 = 14.0`. Output: `14.0`.

---

**91. A**

`Stream.iterate(1, n -> n * 2)` is the infinite two-argument overload, generating `1, 2, 4, 8, 16, 32, 64, 128, ...`. `takeWhile(n -> n < 50)` is short-circuiting on infinite streams: it includes elements while the predicate holds and stops as soon as it encounters an element where the predicate is `false`. `64 < 50` is `false`, so `takeWhile` stops there (excluding `64`). Result: `[1, 2, 4, 8, 16, 32]`.

---

**92. A**

`Collectors.groupingBy(classifier, mapFactory, downstream)` (the three-argument overload) lets you specify the `Map` implementation — here `TreeMap::new` produces a `Map` with keys sorted in natural order (ascending `Integer` order: `3, 4, 5`). Lengths: `"pear"`(4), `"fig"`(3), `"apple"`(5), `"kiwi"`(4), `"plum"`(4), `"date"`(4). Group 3 → `[fig]`, group 4 → `[pear, kiwi, plum, date]` (in encounter order), group 5 → `[apple]`. Since it's a `TreeMap`, `toString()` prints keys in ascending order: `{3=[fig], 4=[pear, kiwi, plum, date], 5=[apple]}`.

---

**93. B**

`Task` implements `Comparable<Task>`, comparing by `priority` via `compareTo`. `Comparator.naturalOrder()` is valid for any type that implements `Comparable` and works correctly with `Stream.max()`, which returns `Optional<T>` of the maximum element according to the given comparator. The highest `priority` value is `3` (`"low"`). `result.get()` returns the `Task` with `priority=3`, whose `toString()` returns `"low"`. Output: `low`.

---

**94. D**

`Collectors.toMap()` (without specifying a map factory) returns a `HashMap` by default, which is **mutable** — `map.put("date", 4)` succeeds without exception. After the put, the map contains `"apple"→5, "banana"→6, "cherry"→6, "date"→4` (4 entries). `map.get("date")` returns `4`, and `map.size()` returns `4`.

---

**95. A**

`Stream.of((String) null)` creates a stream containing exactly **one** element, which happens to be `null` — `Stream.of(T t)` (the single-element overload) is selected here due to the explicit `(String)` cast, so it wraps the single `null` reference rather than treating it as a `null` varargs array. `.count()` returns `1`. `Stream.ofNullable(null)` (Java 9+) returns an **empty** stream when given `null` — `.count()` returns `0`. Output: `1` then `0`.

---

**96. A**

The pipeline processes elements one at a time through the *entire* chain (not stage-by-stage across all elements). For `1`: first `peek` prints `see: 1`, `map` produces `10`, second `peek` prints `mapped: 10` — `10` is added to the result (1 of 2 needed by `limit(2)`). For `2`: `see: 2`, `mapped: 20` — `20` is added (2 of 2). `limit(2)` is now satisfied, so the pipeline short-circuits and elements `3`, `4`, `5` are never processed by either `peek`. Output: `see: 1`, `mapped: 10`, `see: 2`, `mapped: 20`, then `[10, 20]`.

---

**97. A**

The `groupingBy` classifier extracts the owner name via a `switch` expression covering both `Checking` and `Savings` (exhaustive over the sealed `Account` hierarchy, compiles without `default`). The downstream `Collectors.summingDouble` extracts and sums the `balance` via a second `switch` expression — nesting two independent `switch` expressions inside the same `collect()` call is perfectly legal (ruling out D). For `"Ann"`: `Checking` balance `500.0` + `Savings` balance `2000.0` = `2500.0`. For `"Ben"`: `Checking` balance `-50.0` + `Savings` balance `500.0` = `450.0` (the negative balance is summed normally — `summingDouble` does not filter or clamp values). Output: `2500.0` then `450.0`.

---

**98. A**

`Comparator.comparing(String::toLowerCase)` derives a sort key by lowercasing each string, then compares those lowercase keys using their natural (`Comparable<String>`) order. Lowercased keys: `"banana"`, `"apple"`, `"cherry"`, `"date"`. Sorted lexicographically: `"apple" < "banana" < "cherry" < "date"`. The original-case strings are reordered to match: `["apple", "Banana", "Cherry", "date"]`.

---

**99. B**

`record Coord(int x, int y)` automatically generates `equals()` and `hashCode()` based on its component values, so two `Coord(1,1)` instances are considered equal by `distinct()` — the duplicate is removed, leaving 2 distinct elements (`Coord(1,1)` and `Coord(2,2)`). `MutablePoint` is a plain class with **no** overridden `equals()`/`hashCode()`, so it uses `Object`'s default identity-based comparison — even though two `MutablePoint(1,1)` instances have the same field values, they are different object references and are *not* considered equal by `distinct()`. All 3 `MutablePoint` instances remain distinct. Output: `2` then `3`.

---

**100. A**

The pipeline filters for `rating >= 4.2`, which excludes `"Modern Java"` (rating 4.1) but keeps the other four books. `groupingBy(Book::year, ...)` groups the remaining books by year: 2018 → `["Effective Java", "Refactoring"]` (both rating ≥ 4.2; `"Modern Java"` was already filtered out), 2008 → `["Clean Code"]`, 2006 → `["Java Concurrency"]`. The downstream `mapping(Book::title, collectingAndThen(toList(), list -> ...sorted().joining(" & ")))` extracts titles, collects to a list, sorts them alphabetically, and joins with `" & "`. For 2018: titles `["Effective Java", "Refactoring"]` sorted alphabetically are already in that order, joined as `"Effective Java & Refactoring"`. `result.containsKey(2006)` is `true` (one book, `"Java Concurrency"`, has rating 4.5 ≥ 4.2). `result.get(2006)` is `"Java Concurrency"` (a single-element list joined trivially produces just that title, with no `" & "` needed). Output: `Effective Java & Refactoring` then `true` then `Java Concurrency`.
