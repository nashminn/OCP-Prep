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

---

**51.** What is the output of the following code?

```java
List<String> list = Stream.of("red", "green", "blue")
    .toList();
list.add("yellow");
System.out.println(list);
```

A. `[red, green, blue, yellow]`

B. `[red, green, blue]`

C. The code does not compile.

D. An `UnsupportedOperationException` is thrown at runtime.

E. A `ConcurrentModificationException` is thrown at runtime.

---

**52.** What is the output of the following code?

```java
List<String> words = List.of("ant", "bee", "cat", "dog", "emu");
Map<Integer, String> map = words.stream()
    .collect(Collectors.toMap(String::length, w -> w));
System.out.println(map);
```

A. `{3=ant}`

B. `{3=emu}`

C. `{3=ant, 3=bee, 3=cat, 3=dog, 3=emu}`

D. The code does not compile.

E. An `IllegalStateException` is thrown at runtime.

---

**53.** Given the following record and code, what is the output?

```java
record Sale(String region, String product, int units) {}

List<Sale> sales = List.of(
    new Sale("East", "Widget", 10),
    new Sale("West", "Gadget", 5),
    new Sale("East", "Widget", 7),
    new Sale("West", "Gadget", 3)
);

Map<String, Integer> totals = sales.stream()
    .collect(Collectors.toMap(
        s -> s.region() + "-" + s.product(),
        Sale::units,
        Integer::sum));
System.out.println(totals.get("East-Widget"));
System.out.println(totals.get("West-Gadget"));
```

A. `10` then `5`

B. `17` then `8`

C. `7` then `3`

D. The code does not compile.

E. An `IllegalStateException` is thrown at runtime.

---

**54.** What is the output of the following code?

```java
Map<Integer, Long> result = Stream.of("kit", "cat", "dog", "bird", "owl", "ox")
    .collect(Collectors.groupingBy(String::length, Collectors.counting()));
System.out.println(result);
```

A. `{2=1, 3=3, 4=1}`

B. `{2=[ox], 3=[kit, cat, dog, owl], 4=[bird]}`

C. `{2=1, 3=4, 4=1}`

D. The code does not compile.

E. `{2=1, 3=2, 4=1}`

---

**55.** What is the output of the following code?

```java
Map<Integer, String> result = Stream.of("apple", "ant", "bear", "bat", "cat")
    .collect(Collectors.groupingBy(
        String::length,
        Collectors.mapping(s -> s.substring(0, 1), Collectors.joining(","))));
System.out.println(result.get(3));
System.out.println(result.get(4));
```

A. `a,b,c` then `b`

B. `a,c` then `a,b`

C. `[a, b, c]` then `[b]`

D. `b` then `a,c`

E. The code does not compile.

---

**56.** Given the following record and code, what is the output?

```java
record Employee(String dept, String name, double salary) {}

List<Employee> emps = List.of(
    new Employee("Eng", "Ann", 90_000),
    new Employee("Eng", "Bob", 95_000),
    new Employee("HR", "Cid", 60_000)
);

Map<String, Optional<Employee>> top = emps.stream()
    .collect(Collectors.groupingBy(
        Employee::dept,
        Collectors.maxBy(Comparator.comparingDouble(Employee::salary))));

System.out.println(top.get("Eng").get().name());
System.out.println(top.get("HR").get().name());
```

A. `Ann` then `Cid`

B. `Bob` then `Cid`

C. The code does not compile because `maxBy` cannot be used as a downstream collector.

D. A `NoSuchElementException` is thrown for the `"HR"` entry.

E. `Bob` then an empty `Optional` is printed for `"HR"`.

---

**57.** What is the output of the following code?

```java
Map<Boolean, List<Integer>> result = Stream.of(2, 4, 6, 8)
    .collect(Collectors.partitioningBy(n -> n > 100));
System.out.println(result.get(true));
System.out.println(result.containsKey(false));
```

A. `null` then `false`

B. `[]` then `true`

C. `null` then `true`

D. `[]` then `false`

E. The code throws `NoSuchElementException` when calling `result.get(true)`.

---

**58.** What is the output of the following code?

```java
Map<Boolean, Long> result = Stream.of(1, 2, 3, 4, 5, 6, 7)
    .collect(Collectors.partitioningBy(n -> n % 2 == 0, Collectors.counting()));
System.out.println(result);
```

A. `{false=3, true=4}`

B. `{false=4, true=3}`

C. `{false=[1, 3, 5, 7], true=[2, 4, 6]}`

D. The code does not compile.

E. `{true=3, false=4}` — but the order shown depends on insertion, so this output is also valid.

---

**59.** What is the output of the following code?

```java
List<List<String>> nested = List.of(
    List.of("a", "b"),
    List.of(),
    List.of("c")
);
long count = nested.stream()
    .flatMap(List::stream)
    .count();
System.out.println(count);
```

A. `2`

B. `3`

C. `4`

D. `0`

E. The code does not compile.

---

**60.** What is the output of the following code?

```java
List<Optional<String>> data = List.of(
    Optional.of("alpha"),
    Optional.empty(),
    Optional.of("beta"),
    Optional.empty()
);
String result = data.stream()
    .flatMap(Optional::stream)
    .collect(Collectors.joining(","));
System.out.println(result);
```

A. `alpha,,beta,`

B. `alpha,beta`

C. `Optional[alpha],Optional[beta]`

D. The code does not compile.

E. A `NoSuchElementException` is thrown at runtime.

---

**61.** What is the output of the following code?

```java
List<Integer> result = Stream.iterate(1, n -> n <= 50, n -> n * 3)
    .toList();
System.out.println(result);
```

A. `[1, 3, 9, 27, 81]`

B. `[1, 3, 9, 27]`

C. `[1, 3, 9, 27, 81, 243]`

D. The code hangs indefinitely.

E. The code does not compile.

---

**62.** What is the output of the following code?

```java
List<Integer> result = Stream.iterate(2, n -> n * n)
    .limit(4)
    .map(n -> n + 1)
    .toList();
System.out.println(result);
```

A. `[3, 5, 17, 257]`

B. `[2, 4, 16, 256]`

C. `[3, 5, 5, 5]`

D. The code hangs indefinitely.

E. The code does not compile.

---

**63.** Which of the following statements about this code are true? (Choose two.)

```java
Stream<Double> stream = Stream.generate(Math::random);
List<Double> values = stream.limit(5).toList();
```

A. `Stream.generate()` produces an infinite stream.

B. The code does not compile because `Math::random` is not a `Supplier<Double>`.

C. Without `limit(5)`, calling `toList()` directly on `stream` would hang indefinitely.

D. `values` will always contain exactly 5 distinct values.

E. `Stream.generate()` requires a `UnaryOperator<Double>` as its argument.

---

**64.** What is the output of the following code?

```java
Stream<String> stream = Stream.of("one", "two", "three")
    .peek(s -> System.out.println("peek: " + s))
    .filter(s -> s.length() == 3)
    .map(String::toUpperCase);
System.out.println("end");
```

A. `peek: one`, `peek: two`, `peek: three`, then `end`

B. `end` only

C. `peek: one` then `peek: two` then `end`

D. `ONE`, `TWO`, then `end`

E. The code does not compile.

---

**65.** What is the output of the following code?

```java
List<String> result = Stream.of("aa", "b", "ccc", "dd", "e")
    .peek(s -> System.out.print("1:" + s + " "))
    .filter(s -> s.length() > 1)
    .peek(s -> System.out.print("2:" + s + " "))
    .map(String::toUpperCase)
    .toList();
System.out.println();
System.out.println(result);
```

A. `1:aa 2:AA 1:b 1:ccc 2:CCC 1:dd 2:DD 1:e` then `[AA, CCC, DD]`

B. `1:aa 2:aa 1:b 1:ccc 2:ccc 1:dd 2:dd 1:e` then `[AA, CCC, DD]`

C. `1:aa 1:b 1:ccc 1:dd 1:e 2:aa 2:ccc 2:dd` then `[AA, CCC, DD]`

D. `1:aa 2:aa 1:ccc 2:ccc 1:dd 2:dd` then `[AA, CCC, DD]`

E. The code does not compile.

---

**66.** What is the output of the following code?

```java
boolean found = Stream.of("a", "bb", "ccc", "dddd")
    .peek(s -> System.out.println("checking: " + s))
    .anyMatch(s -> s.length() == 2);
System.out.println("found = " + found);
```

A. `checking: a`, `checking: bb`, `checking: ccc`, `checking: dddd`, then `found = true`

B. `checking: a`, `checking: bb`, then `found = true`

C. `checking: a`, then `found = false`

D. `found = true` only (peek does not run before a short-circuit match)

E. The code does not compile.

---

**67.** What is the output of the following code?

```java
List<Integer> nums = List.of(1, 3, 5, 6, 7, 9);
Optional<Integer> result = nums.stream()
    .filter(n -> {
        System.out.println("testing " + n);
        return n % 2 == 0;
    })
    .findFirst();
System.out.println("found: " + result.orElse(-1));
```

A. All six `testing` lines print, then `found: 6`

B. `testing 1`, `testing 3`, `testing 5`, `testing 6`, then `found: 6`

C. `testing 1` only, then `found: -1`

D. `testing 1`, `testing 3`, `testing 5`, then `found: -1`

E. The code does not compile.

---

**68.** What is the result of executing the following code?

```java
boolean b1 = Stream.iterate(1, n -> n + 1).anyMatch(n -> n == 5);
System.out.println("b1 = " + b1);
boolean b2 = Stream.iterate(1, n -> n + 1).noneMatch(n -> n == 5);
System.out.println("b2 = " + b2);
```

A. `b1 = true` is printed, then the program hangs indefinitely on the second statement.

B. `b1 = true` then `b2 = false` are both printed.

C. `b1 = true` then `b2 = true` are both printed.

D. The program hangs on the first statement and prints nothing.

E. The code does not compile.

---

**69.** What is the output of the following code?

```java
Stream<Integer> stream = Stream.of(1, 2, 3);
List<Integer> doubled = stream.map(n -> n * 2).toList();
System.out.println(doubled);
List<Integer> tripled = stream.map(n -> n * 3).toList();
System.out.println(tripled);
```

A. `[2, 4, 6]` then `[3, 6, 9]`

B. `[2, 4, 6]` then an `IllegalStateException` is thrown.

C. `[2, 4, 6]` then `[2, 4, 6]`

D. The code does not compile.

E. An `IllegalStateException` is thrown before anything is printed.

---

**70.** What is the output of the following code?

```java
OptionalDouble avg = IntStream.of(1, 2, 3, 4).average();
Optional<Double> opt = Optional.of(avg.getAsDouble());
System.out.println(avg.getClass().getSimpleName());
System.out.println(opt.get());
```

A. `OptionalDouble` then `2.5`

B. `Optional` then `2.5`

C. `OptionalDouble` then `2`

D. The code does not compile because `avg` cannot be assigned from `average()`.

E. The code does not compile because `Optional.of()` cannot accept a `double`.

---

**71.** Which of the following statements about this code are true? (Choose all that apply.)

```java
IntStream is = IntStream.rangeClosed(1, 3);
Stream<Integer> boxedStream = is.boxed();
LongStream ls = IntStream.rangeClosed(1, 3).asLongStream();
Stream<String> strs = IntStream.rangeClosed(1, 3).mapToObj(Integer::toString);
```

A. `is.boxed()` returns a `Stream<Integer>`.

B. `asLongStream()` converts each `int` to a `long` without changing the count of elements.

C. `mapToObj(Integer::toString)` returns an `IntStream`.

D. All four lines compile successfully.

E. `IntStream.rangeClosed(1, 3)` produces the values `1, 2, 3`.

F. `is.boxed()` returns an `IntStream`.

---

**72.** What is the output of the following code?

```java
Stream<Integer> finite = Stream.of(100, 200, 300);
Stream<Integer> infinite = Stream.iterate(1, n -> n + 1);
List<Integer> result = Stream.concat(finite, infinite)
    .limit(5)
    .toList();
System.out.println(result);
```

A. `[100, 200, 300, 1, 2]`

B. `[100, 200, 300]`

C. The code hangs indefinitely because `infinite` never terminates.

D. `[1, 2, 100, 200, 300]`

E. The code does not compile because `Stream.concat()` cannot accept an infinite stream.

---

**73.** Given the following record and code, what is the output?

```java
record Player(String name, int score, int age) {}

List<Player> players = List.of(
    new Player("Ann", 50, 22),
    new Player("Ben", 50, 19),
    new Player("Cid", 70, 25)
);

players.stream()
    .sorted(Comparator.comparingInt(Player::score).reversed()
        .thenComparing(Player::age))
    .forEach(p -> System.out.println(p.name()));
```

A. `Cid` then `Ben` then `Ann`

B. `Cid` then `Ann` then `Ben`

C. `Ann` then `Ben` then `Cid`

D. `Ben` then `Ann` then `Cid`

E. The code does not compile.

---

**74.** What is the output of the following code?

```java
List<String> words = new ArrayList<>(List.of("kiwi", "fig", "apple", "date", "plum"));
words.sort(Comparator.comparingInt(String::length)
    .reversed()
    .thenComparing(Comparator.naturalOrder()));
System.out.println(words);
```

A. `[apple, date, kiwi, plum, fig]`

B. `[apple, date, kiwi, plum, fig]` is wrong; the correct order is `[apple, date, plum, kiwi, fig]`

C. `[fig, kiwi, plum, date, apple]`

D. `[fig, date, kiwi, plum, apple]`

E. The code does not compile.

---

**75.** What is the output of the following code?

```java
List<String> names = new ArrayList<>(Arrays.asList("Ann", null, "cid", null, "Bob"));
names.sort(Comparator.nullsFirst(Comparator.naturalOrder()));
System.out.println(names);
```

A. `[null, null, Ann, Bob, cid]`

B. `[Ann, Bob, cid, null, null]`

C. A `NullPointerException` is thrown at runtime.

D. `[null, null, cid, Ann, Bob]`

E. The code does not compile.

---

**76.** Given the following class and code, what happens?

```java
class Box {
    int weight;
    Box(int weight) { this.weight = weight; }
}

List<Box> boxes = new ArrayList<>(List.of(new Box(3), new Box(1), new Box(2)));
boxes.stream().sorted().forEach(b -> System.out.println(b.weight));
```

A. The output is `1`, `2`, `3` (sorted by weight).

B. The output is `3`, `1`, `2` (original order).

C. The code does not compile because `sorted()` requires a type argument.

D. The code compiles but throws a `ClassCastException` at runtime because `Box` does not implement `Comparable`.

E. The code throws a `NullPointerException` at runtime.

---

**77.** What is the output of the following code?

```java
IntSummaryStatistics stats = Stream.of("pear", "fig", "apple", "kiwi")
    .collect(Collectors.summarizingInt(String::length));
System.out.println(stats.getSum() + " " + stats.getMax() + " " + stats.getAverage());
```

A. `16 5 4.0`

B. `4 5 4.0`

C. `16 5 4`

D. The code does not compile because `summarizingInt` requires an `IntStream`.

E. `16 4 4.0`

---

**78.** What is the output of the following code?

```java
Optional<String> name = Optional.of("river");
Optional<Optional<Integer>> nested = name.map(s -> Optional.of(s.length()));
Optional<Integer> flat = name.flatMap(s -> Optional.of(s.length()));
System.out.println(nested);
System.out.println(flat);
System.out.println(flat.get());
```

A. `Optional[5]` then `Optional[5]` then `5`

B. `Optional[Optional[5]]` then `Optional[5]` then `5`

C. `Optional[Optional[5]]` then `Optional[Optional[5]]` then `Optional[5]`

D. The code does not compile because `map` cannot return an `Optional`.

E. A `NoSuchElementException` is thrown when calling `flat.get()`.

---

**79.** What is the output of the following code?

```java
Optional<String> primary = Optional.of("primary-value");
Optional<String> result = primary.or(() -> {
    System.out.println("computing fallback");
    return Optional.of("fallback-value");
});
System.out.println(result.get());
```

A. `computing fallback` then `primary-value`

B. `primary-value` only (the supplier is never invoked)

C. `computing fallback` then `fallback-value`

D. The code does not compile because `Optional` has no `or()` method.

E. `fallback-value` only

---

**80.** What is the output of the following code?

```java
List<String> words = List.of("a", "bb", "ccc");
int total = words.stream()
    .reduce(0,
        (partial, s) -> partial + s.length(),
        (a, b) -> {
            System.out.println("combiner: " + a + "," + b);
            return a + b;
        });
System.out.println("total = " + total);
```

A. `combiner: ` lines are printed multiple times, then `total = 6`

B. `total = 6` only — the combiner is never invoked for a sequential stream

C. `total = 0` because the combiner overrides the accumulated result

D. The code does not compile because the identity type does not match the return type.

E. A `ClassCastException` is thrown at runtime.

---

**81.** What is the output of the following code?

```java
List<Runnable> tasks = new ArrayList<>();
for (int i = 1; i <= 3; i++) {
    int current = i;
    tasks.add(() -> System.out.println("Task " + current));
}
tasks.forEach(Runnable::run);
```

A. `Task 1`, `Task 2`, `Task 3`

B. `Task 3`, `Task 3`, `Task 3`

C. The code does not compile because `current` is not effectively final.

D. The code does not compile because `i` is captured directly.

E. `Task 1`, `Task 1`, `Task 1`

---

**82.** Which of the following are valid replacements for the lambda `s -> s.toUpperCase()` in this code? (Choose all that apply.)

```java
Stream<String> stream = Stream.of("a", "b", "c");
stream.map(s -> s.toUpperCase()).forEach(System.out::println);
```

A. `String::toUpperCase`

B. `s::toUpperCase`

C. `String.valueOf::toUpperCase`

D. `(String s) -> s.toUpperCase()`

E. `s -> { return s.toUpperCase(); }`

F. `toUpperCase()`

---

**83.** Given the following class, what is the output?

```java
class Point {
    int x, y;
    Point(int x, int y) { this.x = x; this.y = y; }
    public String toString() { return "(" + x + "," + y + ")"; }
}

List<Integer> nums = List.of(1, 2, 3);
List<Point> points = nums.stream()
    .map(n -> new Point(n, n * n))
    .toList();
System.out.println(points);
```

A. `[(1,1), (2,4), (3,9)]`

B. `[1, 2, 3]`

C. The code does not compile because `Point::new` must be used instead of a lambda.

D. `[Point@<hash1>, Point@<hash2>, Point@<hash3>]`

E. The code does not compile because `map` cannot return a custom type.

---

**84.** Consider the following functional interface and code. What is true?

```java
@FunctionalInterface
interface Converter<T, R> {
    R convert(T t);
}

List<String> nums = List.of("1", "2", "3");
List<Integer> result = nums.stream()
    .map(Integer::valueOf)
    .toList();
System.out.println(result);
```

A. The output is `[1, 2, 3]` (as `Integer` values).

B. The code does not compile because `Integer::valueOf` is ambiguous between `valueOf(String)` and `valueOf(int)`.

C. The code does not compile because `Converter` is unused and unrelated to `map`.

D. The output is `["1", "2", "3"]` (unchanged Strings).

E. A `NumberFormatException` is thrown at runtime.

---

**85.** What is the output of the following code?

```java
String result1 = Stream.<String>of().collect(Collectors.joining(", ", "[", "]"));
String result2 = Stream.<String>of().collect(Collectors.joining());
System.out.println(result1);
System.out.println(result2);
```

A. `[]` then `` (empty string)

B. `[, ]` then `null`

C. `null` then `null`

D. The code does not compile because `joining()` requires at least one element.

E. A `NoSuchElementException` is thrown at runtime.

---

**86.** Given the following sealed hierarchy and code, what is the output?

```java
sealed interface Shape permits Circle, Square {}
record Circle(double radius) implements Shape {}
record Square(double side) implements Shape {}

List<Shape> shapes = List.of(new Circle(2), new Square(3), new Circle(1));

record Summary(long count, double totalArea) {}

Summary result = shapes.stream()
    .collect(Collectors.teeing(
        Collectors.counting(),
        Collectors.summingDouble(s -> switch (s) {
            case Circle c -> Math.PI * c.radius() * c.radius();
            case Square sq -> sq.side() * sq.side();
        }),
        Summary::new));
System.out.println(result.count());
System.out.printf("%.2f%n", result.totalArea());
```

A. `3` then `24.71`

B. `2` then `24.71`

C. `3` then `9.00`

D. The code does not compile because `teeing` only accepts two `Collector` arguments and a `BiFunction`, not a `BinaryOperator`.

E. The code does not compile because the `switch` expression is not exhaustive.

---

**87.** What is the output of the following code?

```java
List<Integer> nums = new ArrayList<>(List.of(1, 2, 3, 4, 5));
List<Integer> result = nums.reversed().stream()
    .filter(n -> n % 2 == 0)
    .toList();
System.out.println(result);
System.out.println(nums);
```

A. `[4, 2]` then `[1, 2, 3, 4, 5]`

B. `[2, 4]` then `[5, 4, 3, 2, 1]`

C. `[4, 2]` then `[5, 4, 3, 2, 1]`

D. The code does not compile because `List` has no `reversed()` method.

E. `[2, 4]` then `[1, 2, 3, 4, 5]`

---

**88.** Given the following sealed hierarchy and code, what is the output?

```java
sealed interface Event permits Login, Logout, Error {}
record Login(String user, int timestamp) implements Event {}
record Logout(String user, int timestamp) implements Event {}
record Error(String code, int timestamp) implements Event {}

List<Event> events = List.of(
    new Login("alice", 100),
    new Error("E1", 105),
    new Logout("alice", 110),
    new Login("bob", 120),
    new Error("E2", 130),
    new Error("E3", 140)
);

Map<String, List<Event>> grouped = events.stream()
    .collect(Collectors.groupingBy(e -> switch (e) {
        case Login l -> "LOGIN";
        case Logout l -> "LOGOUT";
        case Error er -> "ERROR";
    }));

System.out.println(grouped.get("ERROR").size());
System.out.println(grouped.get("LOGOUT").size());
System.out.println(grouped.containsKey("LOGIN"));
```

A. `3` then `1` then `true`

B. `2` then `1` then `true`

C. `3` then `1` then `false`

D. The code does not compile because `switch` over a sealed interface in `groupingBy` requires a `default` branch.

E. `3` then `0` then `true`

---

**89.** What is the output of the following code?

```java
List<Integer> result = Stream.of(5, 3, 1, 4, 2)
    .collect(Collectors.toUnmodifiableList());
System.out.println(result);
result.set(0, 99);
```

A. `[5, 3, 1, 4, 2]` then the program ends normally.

B. `[5, 3, 1, 4, 2]` then an `UnsupportedOperationException` is thrown.

C. `[1, 2, 3, 4, 5]` then an `UnsupportedOperationException` is thrown.

D. The code does not compile because `Collectors.toUnmodifiableList()` does not exist.

E. `[5, 3, 1, 4, 2]` then a `ClassCastException` is thrown.

---

**90.** What is the output of the following code?

```java
double[] values = {1.5, 2.5, 3.0};
double sum = Arrays.stream(values)
    .mapToObj(d -> d * 2)
    .mapToDouble(Double::doubleValue)
    .sum();
System.out.println(sum);
```

A. `14.0`

B. `7.0`

C. The code does not compile because `mapToObj` on a `DoubleStream` cannot be followed by `mapToDouble`.

D. The code does not compile because `mapToObj` requires a method reference.

E. `7.0` is printed twice due to re-evaluation.

---

**91.** What is the output of the following code?

```java
List<Integer> result = Stream.iterate(1, n -> n * 2)
    .takeWhile(n -> n < 50)
    .toList();
System.out.println(result);
```

A. `[1, 2, 4, 8, 16, 32]`

B. `[1, 2, 4, 8, 16, 32, 64]`

C. The code hangs indefinitely because `takeWhile` cannot short-circuit an infinite stream.

D. `[]`

E. The code does not compile.

---

**92.** What is the output of the following code?

```java
Map<Integer, List<String>> result = Stream.of("pear", "fig", "apple", "kiwi", "plum", "date")
    .collect(Collectors.groupingBy(String::length, TreeMap::new, Collectors.toList()));
System.out.println(result);
```

A. `{3=[fig], 4=[pear, kiwi, plum, date], 5=[apple]}`

B. `{4=[pear, kiwi, plum, date], 3=[fig], 5=[apple]}`

C. `{3=[fig], 4=[pear, kiwi, plum, date], 5=[apple]}` but as a `HashMap` with unspecified order

D. The code does not compile because `groupingBy` does not have a three-argument overload.

E. `{5=[apple], 4=[pear, kiwi, plum, date], 3=[fig]}`

---

**93.** Given the following class implementing `Comparable`, what is the output?

```java
class Task implements Comparable<Task> {
    String name;
    int priority;
    Task(String name, int priority) {
        this.name = name;
        this.priority = priority;
    }
    public int compareTo(Task other) {
        return Integer.compare(this.priority, other.priority);
    }
    public String toString() { return name; }
}

List<Task> tasks = List.of(
    new Task("low", 3),
    new Task("high", 1),
    new Task("mid", 2)
);

Optional<Task> result = tasks.stream().max(Comparator.naturalOrder());
System.out.println(result.get());
```

A. `low`

B. `high`

C. `mid`

D. The code does not compile because `max()` requires an explicit `Comparator`, not `Comparator.naturalOrder()`.

E. The code throws a `ClassCastException` because `Task` does not implement `Comparable`.

---

**94.** What is the output of the following code?

```java
Map<String, Integer> map = Stream.of("apple", "banana", "cherry")
    .collect(Collectors.toMap(s -> s, String::length));
map.put("date", 4);
System.out.println(map.get("date"));
System.out.println(map.size());
```

A. `4` then `4`

B. The code throws an `UnsupportedOperationException` when calling `map.put`.

C. `null` then `3`

D. `4` then `3`

E. The code does not compile.

---

**95.** What is the output of the following code?

```java
Stream<String> s1 = Stream.of((String) null);
Stream<String> s2 = Stream.ofNullable(null);
System.out.println(s1.count());
System.out.println(s2.count());
```

A. `1` then `0`

B. `0` then `0`

C. `1` then `1`

D. A `NullPointerException` is thrown at `Stream.of((String) null)`.

E. The code does not compile.

---

**96.** What is the output of the following code?

```java
List<Integer> result = Stream.of(1, 2, 3, 4, 5)
    .peek(n -> System.out.println("see: " + n))
    .map(n -> n * 10)
    .peek(n -> System.out.println("mapped: " + n))
    .limit(2)
    .toList();
System.out.println(result);
```

A. `see: 1`, `mapped: 10`, `see: 2`, `mapped: 20`, then `[10, 20]`

B. `see: 1`, `see: 2`, `see: 3`, `see: 4`, `see: 5`, `mapped: 10`, `mapped: 20`, then `[10, 20]`

C. `see: 1`, `mapped: 10`, `see: 2`, `mapped: 20`, `see: 3`, `mapped: 30`, then `[10, 20]`

D. `[10, 20]` only — `peek` does not execute because `limit` short-circuits everything.

E. The code does not compile.

---

**97.** Given the following sealed hierarchy and code, what is the output?

```java
sealed interface Account permits Checking, Savings {}
record Checking(String owner, double balance, double overdraftLimit) implements Account {}
record Savings(String owner, double balance, double interestRate) implements Account {}

List<Account> accounts = List.of(
    new Checking("Ann", 500.0, 100.0),
    new Savings("Ann", 2000.0, 0.02),
    new Checking("Ben", -50.0, 200.0),
    new Savings("Ben", 500.0, 0.01)
);

Map<String, Double> totalsByOwner = accounts.stream()
    .collect(Collectors.groupingBy(
        a -> switch (a) {
            case Checking c -> c.owner();
            case Savings s -> s.owner();
        },
        Collectors.summingDouble(a -> switch (a) {
            case Checking c -> c.balance();
            case Savings s -> s.balance();
        })));

System.out.println(totalsByOwner.get("Ann"));
System.out.println(totalsByOwner.get("Ben"));
```

A. `2500.0` then `450.0`

B. `2500.0` then `-450.0`

C. `2500.0` then `450.0` — but as `Float` values, causing a compile error

D. The code does not compile because two `switch` expressions cannot be used inside the same `collect()` call.

E. `2500.0` then `550.0`

---

**98.** What is the output of the following code?

```java
List<String> words = new ArrayList<>(List.of("Banana", "apple", "Cherry", "date"));
words.sort(Comparator.comparing(String::toLowerCase));
System.out.println(words);
```

A. `[apple, Banana, Cherry, date]`

B. `[Banana, Cherry, apple, date]`

C. `[date, apple, Banana, Cherry]`

D. The code does not compile because `Comparator.comparing` requires two arguments here.

E. `[apple, date, Banana, Cherry]`

---

**99.** Given the following two classes, what is the output?

```java
record Coord(int x, int y) {}

class MutablePoint {
    int x, y;
    MutablePoint(int x, int y) { this.x = x; this.y = y; }
}

List<Coord> coords = Stream.of(new Coord(1,1), new Coord(1,1), new Coord(2,2))
    .distinct()
    .toList();
System.out.println(coords.size());

List<MutablePoint> points = Stream.of(new MutablePoint(1,1), new MutablePoint(1,1), new MutablePoint(2,2))
    .distinct()
    .toList();
System.out.println(points.size());
```

A. `2` then `2`

B. `2` then `3`

C. `3` then `3`

D. `2` then `1`

E. The code does not compile because `MutablePoint` does not implement `Comparable`.

---

**100.** Given the following record and code, what is the output?

```java
record Book(String title, String author, int year, double rating) {}

List<Book> books = List.of(
    new Book("Effective Java", "Bloch", 2018, 4.8),
    new Book("Clean Code", "Martin", 2008, 4.2),
    new Book("Java Concurrency", "Goetz", 2006, 4.5),
    new Book("Refactoring", "Fowler", 2018, 4.3),
    new Book("Modern Java", "Urma", 2018, 4.1)
);

Map<Integer, String> result = books.stream()
    .filter(b -> b.rating() >= 4.2)
    .collect(Collectors.groupingBy(
        Book::year,
        Collectors.mapping(Book::title,
            Collectors.collectingAndThen(
                Collectors.toList(),
                list -> list.stream().sorted().collect(Collectors.joining(" & "))))));

System.out.println(result.get(2018));
System.out.println(result.containsKey(2006));
System.out.println(result.get(2006));
```

A. `Effective Java & Refactoring` then `true` then `Java Concurrency`

B. `Effective Java & Modern Java & Refactoring` then `true` then `Java Concurrency`

C. `Effective Java & Refactoring` then `false` then `null`

D. `Effective Java & Refactoring` then `true` then `null`

E. The code does not compile because `collectingAndThen` cannot be nested inside `mapping`.
