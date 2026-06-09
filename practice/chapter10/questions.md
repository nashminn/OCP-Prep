# Chapter 10: Streams — Practice Questions

---

**1.** What is the output of the following code?

```java
Optional<String> opt = Optional.of("hello");
System.out.println(opt.map(String::toUpperCase).orElse("none"));
```

A. `hello`

B. `HELLO`

C. `none`

D. The code does not compile.

E. A `NullPointerException` is thrown at runtime.

---

**2.** Which of the following statements about `Optional.of()` and `Optional.ofNullable()` are true? (Choose all that apply.)

A. `Optional.of(null)` returns an empty `Optional`.

B. `Optional.of(null)` throws a `NullPointerException`.

C. `Optional.ofNullable(null)` returns an empty `Optional`.

D. `Optional.ofNullable(null)` throws a `NullPointerException`.

E. `Optional.of("x").get()` returns `"x"`.

F. Calling `get()` on an empty `Optional` throws `IllegalStateException`.

---

**3.** What is the output of the following code?

```java
Optional<Integer> opt = Optional.empty();
System.out.println(opt.orElseGet(() -> 42));
```

A. `0`

B. `null`

C. `42`

D. The code does not compile.

E. A `NoSuchElementException` is thrown at runtime.

---

**4.** Which of the following creates an infinite stream of random doubles?

A. `Stream.of(Math::random)`

B. `Stream.generate(Math::random)`

C. `Stream.iterate(0.0, Math::random)`

D. `DoubleStream.generate(Math::random)`

E. Both B and D

---

**5.** What is the output of the following code?

```java
Stream<String> stream = Stream.of("a", "b", "c");
stream.forEach(System.out::print);
stream.forEach(System.out::print);
```

A. `abcabc`

B. `abc`

C. The code does not compile.

D. An `IllegalStateException` is thrown at runtime.

E. A `NullPointerException` is thrown at runtime.

---

**6.** What does the following code print?

```java
Stream<Integer> stream = Stream.iterate(1, n -> n * 2);
stream.limit(4).forEach(System.out::print);
```

A. `1248`

B. `1234`

C. `2468`

D. The code hangs indefinitely.

E. The code does not compile.

---

**7.** Which terminal operations return an `Optional`? (Choose all that apply.)

A. `findFirst()`

B. `count()`

C. `min(Comparator)`

D. `anyMatch(Predicate)`

E. `findAny()`

F. `reduce(BinaryOperator)`

---

**8.** What is the output of the following code?

```java
List<String> list = List.of("banana", "apple", "cherry");
Optional<String> result = list.stream()
    .filter(s -> s.startsWith("a"))
    .findFirst();
System.out.println(result.orElse("not found"));
```

A. `apple`

B. `not found`

C. `Optional[apple]`

D. The code does not compile.

E. A `NoSuchElementException` is thrown at runtime.

---

**9.** Which of the following statements about intermediate stream operations are true? (Choose all that apply.)

A. Intermediate operations are executed eagerly.

B. Intermediate operations are lazy and only execute when a terminal operation is called.

C. `filter()` is a stateful intermediate operation.

D. `sorted()` is a stateful intermediate operation.

E. `distinct()` is a stateless intermediate operation.

F. `map()` is a stateless intermediate operation.

---

**10.** What is the output of the following code?

```java
long count = Stream.of("cat", "dog", "bird", "fish")
    .filter(s -> s.length() == 3)
    .count();
System.out.println(count);
```

A. `1`

B. `2`

C. `3`

D. `4`

E. The code does not compile.

---

**11.** What is the output of the following code?

```java
Stream<String> stream = Stream.of("one", "two", "three");
stream.peek(System.out::println);
```

A. `one two three` (each on its own line)

B. Nothing is printed.

C. The code does not compile.

D. An `IllegalStateException` is thrown.

E. A `NullPointerException` is thrown.

---

**12.** What is the result of the following code?

```java
List<List<Integer>> nested = List.of(
    List.of(1, 2),
    List.of(3, 4),
    List.of(5)
);
nested.stream()
    .flatMap(Collection::stream)
    .forEach(System.out::print);
```

A. `[[1, 2], [3, 4], [5]]`

B. `12345`

C. `[1, 2][3, 4][5]`

D. The code does not compile.

E. The code throws a `ClassCastException`.

---

**13.** Which statements about `reduce()` are correct? (Choose all that apply.)

A. `reduce(identity, BinaryOperator)` returns an `Optional<T>`.

B. `reduce(identity, BinaryOperator)` returns `T`.

C. `reduce(BinaryOperator)` returns an `Optional<T>`.

D. If `reduce(identity, BinaryOperator)` is called on an empty stream, it returns the identity value.

E. If `reduce(BinaryOperator)` is called on an empty stream, it throws `NoSuchElementException`.

F. If `reduce(BinaryOperator)` is called on an empty stream, it returns `Optional.empty()`.

---

**14.** What is the output of the following code?

```java
int result = Stream.of(1, 2, 3, 4, 5)
    .reduce(0, (a, b) -> a + b);
System.out.println(result);
```

A. `0`

B. `14`

C. `15`

D. The code does not compile.

E. An `Optional` is returned, so it does not directly assign to `int`.

---

**15.** What does `Collectors.groupingBy()` return when used with no downstream collector?

A. `Map<K, T>`

B. `Map<K, Set<T>>`

C. `Map<K, List<T>>`

D. `Map<K, Optional<T>>`

E. It depends on the stream type.

---

**16.** What is the output of the following code?

```java
Map<Integer, List<String>> map = Stream.of("hi", "hello", "hey", "ok")
    .collect(Collectors.groupingBy(String::length));
System.out.println(map.get(2));
System.out.println(map.get(5));
```

A. `[hi, ok]` then `[hello]`

B. `[hi, ok]` then `null`

C. `[ok, hi]` then `[hello]`

D. The code does not compile.

E. The output order of the list elements is guaranteed.

---

**17.** What is the output of the following code?

```java
Map<Boolean, List<Integer>> result = Stream.of(1, 2, 3, 4, 5)
    .collect(Collectors.partitioningBy(n -> n % 2 == 0));
System.out.println(result.get(false).size());
```

A. `2`

B. `3`

C. `4`

D. `5`

E. The code does not compile.

---

**18.** Which of the following will throw an exception at runtime?

```java
// Option A
Map<String, Integer> mapA = Stream.of("a", "b", "c")
    .collect(Collectors.toMap(s -> s, String::length));

// Option B
Map<String, Integer> mapB = Stream.of("a", "b", "a")
    .collect(Collectors.toMap(s -> s, String::length));

// Option C
Map<String, Integer> mapC = Stream.of("a", "b", "a")
    .collect(Collectors.toMap(s -> s, String::length, (v1, v2) -> v1));
```

A. Only A throws an exception.

B. Only B throws an exception.

C. Only C throws an exception.

D. Both A and B throw an exception.

E. None of them throw an exception.

---

**19.** What is the output of the following code?

```java
IntStream.range(1, 5).forEach(System.out::print);
```

A. `12345`

B. `1234`

C. `2345`

D. `12`

E. The code does not compile.

---

**20.** What is the output of the following code?

```java
OptionalDouble avg = IntStream.of(2, 4, 6).average();
System.out.println(avg.getAsDouble());
```

A. `4.0`

B. `6.0`

C. `12.0`

D. The code does not compile.

E. A `NoSuchElementException` is thrown.

---

**21.** What is the result of calling `allMatch` on an empty stream?

```java
boolean result = Stream.empty().allMatch(x -> false);
System.out.println(result);
```

A. `false`

B. `true`

C. The code does not compile.

D. A `NoSuchElementException` is thrown.

E. It depends on the predicate.

---

**22.** Which of the following correctly converts an `IntStream` to a `Stream<String>`? (Choose all that apply.)

A. `IntStream.of(1, 2, 3).mapToObj(String::valueOf)`

B. `IntStream.of(1, 2, 3).map(String::valueOf)`

C. `IntStream.of(1, 2, 3).boxed().map(String::valueOf)`

D. `IntStream.of(1, 2, 3).mapToObj(i -> String.valueOf(i))`

E. `IntStream.of(1, 2, 3).asLongStream().mapToObj(String::valueOf)`

---

**23.** What is the output of the following code?

```java
Stream.of("apple", "apricot", "banana", "avocado")
    .filter(s -> s.startsWith("a"))
    .sorted()
    .forEach(System.out::println);
```

A. `apple apricot avocado` (each on its own line, in that order)

B. `apple avocado apricot` (in that order)

C. `apricot apple avocado` (in that order)

D. The code does not compile.

E. Output order is not guaranteed.

---

**24.** Which of the following statements about `Stream.iterate()` are true? (Choose all that apply.)

A. `Stream.iterate(seed, UnaryOperator)` creates an infinite stream.

B. `Stream.iterate(seed, Predicate, UnaryOperator)` creates a finite stream (Java 9+).

C. `Stream.iterate(1, n -> n + 1)` is equivalent to `IntStream.iterate(1, n -> n + 1)`.

D. `Stream.iterate(0, n -> n < 10, n -> n + 1)` stops when the predicate returns `false`.

E. `Stream.iterate(seed, UnaryOperator)` requires `limit()` before any terminal operation to avoid hanging.

---

**25.** What is the output of the following code?

```java
Optional<String> opt = Optional.of("Java");
opt.ifPresentOrElse(
    s -> System.out.println("Present: " + s),
    () -> System.out.println("Empty")
);
```

A. `Empty`

B. `Present: Java`

C. `Present: Optional[Java]`

D. The code does not compile.

E. Nothing is printed.

---

**26.** What is the output of the following code?

```java
Stream<String> s1 = Stream.of("x");
Stream<String> s2 = s1.filter(x -> true);
Stream<String> s3 = s1.filter(x -> true);
System.out.println("done");
```

A. `done`

B. Nothing is printed.

C. An `IllegalStateException` is thrown before `"done"` is printed.

D. An `IllegalStateException` is thrown after `"done"` is printed.

E. The code does not compile.

---

**27.** What is the output of the following code?

```java
List<String> words = List.of("hello", "world", "hi");
String result = words.stream()
    .collect(Collectors.joining(", ", "[", "]"));
System.out.println(result);
```

A. `hello, world, hi`

B. `[hello, world, hi]`

C. `hello world hi`

D. The code does not compile.

E. `[hello world hi]`

---

**28.** Which of the following correctly uses `Collectors.toMap()` to build a `Map<String, Integer>` where the value is the string's length, and duplicate keys should keep the longer-valued entry? (Choose all that apply.)

A. `Collectors.toMap(s -> s, String::length)`

B. `Collectors.toMap(s -> s, String::length, (v1, v2) -> v2)`

C. `Collectors.toMap(s -> s, String::length, Integer::max)`

D. `Collectors.toMap(s -> s, String::length, (v1, v2) -> v1 > v2 ? v1 : v2)`

E. `Collectors.toMap(s -> s, String::length, Math::max)`

---

**29.** What is the output of the following code?

```java
IntStream stream = IntStream.rangeClosed(1, 5);
System.out.println(stream.sum());
System.out.println(stream.sum());
```

A. `15` then `15`

B. `15` then `0`

C. The code does not compile.

D. An `IllegalStateException` is thrown after printing `15`.

E. A `NullPointerException` is thrown.

---

**30.** What is the output of the following code?

```java
Stream.of(3, 1, 4, 1, 5, 9, 2, 6)
    .distinct()
    .sorted()
    .limit(4)
    .forEach(System.out::print);
```

A. `1234`

B. `3145`

C. `1245`

D. `1235`

E. The code does not compile.

---

**31.** What is the result of the following code?

```java
Map<Boolean, List<String>> result = Stream.of("cat", "bird", "ox")
    .collect(Collectors.partitioningBy(s -> s.length() > 3));
System.out.println(result.containsKey(true));
System.out.println(result.containsKey(false));
```

A. `true` then `false`

B. `false` then `true`

C. `true` then `true`

D. `false` then `false`

E. The code does not compile.

---

**32.** What is the output of the following code?

```java
Optional<String> opt = Optional.empty();
String result = opt.map(s -> s + "!")
                   .orElse("default");
System.out.println(result);
```

A. `null`

B. `!`

C. `default`

D. The code does not compile.

E. A `NoSuchElementException` is thrown.

---

**33.** Which of the following about `peek()` are true? (Choose all that apply.)

A. `peek()` is a terminal operation.

B. `peek()` is an intermediate operation.

C. `peek()` transforms each element in the stream.

D. `peek()` does not change the stream elements.

E. If no terminal operation follows `peek()`, nothing executes.

F. `peek()` can be used to count elements before a filter is applied.

---

**34.** What is the output of the following code?

```java
Stream.of("a", "bb", "ccc", "dddd")
    .mapToInt(String::length)
    .filter(n -> n % 2 != 0)
    .forEach(System.out::print);
```

A. `13`

B. `1 3`

C. `13` (on separate lines)

D. `24`

E. The code does not compile.

---

**35.** What is the output of the following code?

```java
long result = Stream.of("x", "y", "z")
    .peek(s -> System.out.print(s + " "))
    .count();
System.out.println(result);
```

A. `3`

B. `x y z 3`

C. `x y z` then `3`

D. The output depends on the JVM implementation.

E. The code does not compile.

---

**36.** What is wrong with the following code?

```java
Stream<Integer> stream = Stream.iterate(0, x -> x + 1);
long count = stream.count();
System.out.println(count);
```

A. `Stream.iterate` requires an initial predicate.

B. The code compiles and prints `0`.

C. The code compiles but hangs indefinitely because `count()` tries to process an infinite stream.

D. The code does not compile because `Stream.iterate` returns a primitive stream.

E. An `IllegalStateException` is thrown.

---

**37.** What is the output of the following code?

```java
Stream<String> stream = Stream.of("alpha", "beta", "gamma");
Optional<String> result = stream
    .filter(s -> s.length() > 10)
    .findFirst();
System.out.println(result.isPresent());
```

A. `true`

B. `false`

C. The code throws `NoSuchElementException`.

D. The code does not compile.

E. `Optional.empty`

---

**38.** Which of the following will compile and produce a `Stream<Integer>`? (Choose all that apply.)

A. `IntStream.of(1, 2, 3).boxed()`

B. `IntStream.of(1, 2, 3).mapToObj(i -> i)`

C. `IntStream.of(1, 2, 3).map(i -> i)`

D. `Stream.of(1, 2, 3)`

E. `IntStream.of(1, 2, 3).asLongStream().boxed()`

---

**39.** What is the output of the following code?

```java
List<String> result = Stream.of("c", "a", "b", "a")
    .distinct()
    .sorted()
    .collect(Collectors.toList());
System.out.println(result);
```

A. `[a, b, c]`

B. `[c, a, b]`

C. `[a, a, b, c]`

D. The code does not compile.

E. `[a, b, c, a]`

---

**40.** What is the output of the following code?

```java
Stream<String> stream = Stream.of("one", "two", "three", "four", "five");
stream.filter(s -> {
    System.out.println("filter: " + s);
    return s.length() == 3;
}).findFirst();
```

A. All five `filter:` lines are printed.

B. `filter: one` is printed, and then execution stops.

C. Nothing is printed.

D. Three `filter:` lines are printed.

E. The output is implementation-dependent but guaranteed to stop after finding the first match.

---

**41.** What is the output of the following code?

```java
IntSummaryStatistics stats = IntStream.of(10, 20, 30, 40, 50)
    .summaryStatistics();
System.out.println(stats.getMin() + " " + stats.getMax() + " " + stats.getCount());
```

A. `10 50 5`

B. `50 10 5`

C. `10 50 150`

D. The code does not compile.

E. A `NullPointerException` is thrown.

---

**42.** What is the output of the following code?

```java
Optional<Integer> opt = Optional.of(5);
Optional<String> result = opt.flatMap(n -> Optional.of("Value: " + n));
System.out.println(result.get());
```

A. `Value: 5`

B. `Optional[Value: 5]`

C. `5`

D. The code does not compile.

E. A `NullPointerException` is thrown.

---

**43.** Which of the following correctly collects stream elements into an unmodifiable list? (Choose all that apply.)

A. `stream.collect(Collectors.toUnmodifiableList())`

B. `stream.toList()`

C. `stream.collect(Collectors.toList())`

D. `Collections.unmodifiableList(stream.collect(Collectors.toList()))`

E. `stream.collect(Collectors.toCollection(ArrayList::new))`

---

**44.** What is the output of the following code?

```java
Stream.of("cat", "elephant", "dog", "ant")
    .takeWhile(s -> s.length() <= 3)
    .forEach(System.out::print);
```

A. `catdogant`

B. `cat`

C. `catdog`

D. `catant`

E. The code does not compile.

---

**45.** What is the output of the following code?

```java
Stream.of(1, 2, 3, 4, 5)
    .dropWhile(n -> n < 3)
    .forEach(System.out::print);
```

A. `12`

B. `345`

C. `45`

D. `12345`

E. The code does not compile.

---

**46.** What is the output of the following code?

```java
boolean result = Stream.of("cat", "dog", "bird")
    .anyMatch(s -> s.length() > 4);
System.out.println(result);
```

A. `false`

B. `true`

C. The code does not compile.

D. A `NullPointerException` is thrown.

E. `Optional[true]`

---

**47.** Which of the following statements about parallel streams are true? (Choose all that apply.)

A. `findFirst()` on a parallel stream always returns the first element in encounter order.

B. `findAny()` on a parallel stream may return any element.

C. `forEachOrdered()` preserves encounter order even on parallel streams.

D. Parallel streams always run faster than sequential streams.

E. `count()` on a parallel stream always returns a correct result.

F. `forEach()` on a parallel stream guarantees encounter order.

---

**48.** What is the output of the following code?

```java
Map<String, Long> result = Stream.of("a", "b", "a", "c", "b", "a")
    .collect(Collectors.groupingBy(s -> s, Collectors.counting()));
System.out.println(result.get("a"));
```

A. `1`

B. `2`

C. `3`

D. The code does not compile.

E. `null`

---

**49.** What is the output of the following code?

```java
Optional<String> opt = Optional.ofNullable(null);
System.out.println(opt.orElseThrow(() -> new RuntimeException("missing")));
```

A. `null`

B. `Optional.empty`

C. The code does not compile.

D. A `RuntimeException` with message `"missing"` is thrown.

E. A `NullPointerException` is thrown.

---

**50.** What is the output of the following code?

```java
Stream<String> stream = Stream.of("alpha", "beta", "gamma", "delta");
stream.map(String::toUpperCase)
      .skip(1)
      .limit(2)
      .sorted(Comparator.reverseOrder())
      .forEach(System.out::println);
```

A. `GAMMA` then `BETA`

B. `BETA` then `GAMMA`

C. `DELTA` then `GAMMA`

D. `ALPHA` then `BETA`

E. The code does not compile.
