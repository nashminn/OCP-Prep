# Chapter 9: Collections and Generics — Answers

---

**1. D**

`Arrays.asList()` returns a fixed-size list backed by the original array. You CAN call `set()` on it — that works fine. However, `add()` throws `UnsupportedOperationException` because the size is fixed. So `list.set(1, "B")` succeeds, and then `list.add("d")` throws the exception.

---

**2. A, B, D, E**

Both `List.of()` and `Arrays.asList()` return lists that throw `UnsupportedOperationException` on `add()` and `remove()` (A is correct). However, `Arrays.asList()` allows `set()` while `List.of()` does not — even `set()` on a `List.of()` list throws `UnsupportedOperationException` (B is correct). `List.of()` rejects nulls at creation time with a `NullPointerException`; `Arrays.asList()` accepts nulls (D is correct). `Arrays.asList()` is backed by the original array, so changes to the array are reflected in the list and vice versa (E is correct). C is wrong because `List.of()` does not allow nulls. F is wrong because `List.of()` does not guarantee insertion order — the specification only says elements are present.

---

**3. C**

`TreeSet` stores elements in natural (sorted) order and does not allow duplicates. The four strings are "banana", "apple", "cherry", and a duplicate "apple". After insertion, the set contains `[apple, banana, cherry]` — size 3. `iterator().next()` returns the first element in sorted order, which is "apple" (A < B alphabetically).

---

**4. C**

`TreeSet` uses natural ordering (String's `compareTo`) to position elements. When `null` is added to a `TreeSet` with natural ordering, it tries to compare `null` with existing elements using `compareTo`, which throws `NullPointerException`. This applies even if the set is empty — `TreeSet` checks comparability on insertion.

---

**5. B, C, D**

Queue has two sets of methods: exception-throwing (add, remove, element) and null/false-returning (offer, poll, peek).
- `offer()` returns `false` if the queue is full (not an exception) — A is wrong.
- `poll()` returns `null` if the queue is empty — B is correct.
- `remove()` throws `NoSuchElementException` if empty — C is correct.
- `peek()` returns `null` if the queue is empty — D is correct.
- `element()` throws `NoSuchElementException` if empty (not null) — E is wrong.
- `add()` throws an exception if the queue is full (not false) — F is wrong.

---

**6. B**

`ArrayDeque.push()` inserts at the front (addFirst). So after three pushes:
- deque: `[3, 2, 1]` (front to back)

`pop()` removes from the front → returns `3`. After pop, deque is `[2, 1]`. `peek()` returns the front without removing → `2`. Output: `3 2`.

---

**7. B, C, E**

- A: `List<int>` — compile error; primitives cannot be used as type arguments.
- B: `List<Integer>` — valid.
- C: `List<?>` — a wildcard type; any `ArrayList<X>` can be assigned to it.
- D: `List<Object>` vs `List<String>` — generic types are invariant; this is a compile error even though `String extends Object`.
- E: `List<? extends Number>` — `ArrayList<Integer>` is assignable because `Integer extends Number`.
- F: Same invariance issue as D.

---

**8. D**

`HashMap.put()` replaces the value if the key already exists. After `put("a", 1)` and `put("a", 3)`, the map has only one entry for "a" with value `3`. So size is `2` (keys "a" and "b"), and `map.get("a")` is `3`.

---

**9. C**

`HashMap` allows exactly one `null` key (and multiple `null` values). `TreeMap` uses `compareTo` (natural ordering) to sort keys; comparing `null` with any key throws `NullPointerException`. A custom `Comparator` that handles nulls could be provided to a `TreeMap`, but by default null keys cause a `NullPointerException`.

---

**10. C**

`getOrDefault(key, defaultValue)` returns the value mapped to the key, or `defaultValue` if the key is not present. Key `"y"` is not in the map, so `99` is returned.

---

**11. B**

`putIfAbsent("a", 99)` — key "a" already has a value (`1`), so nothing changes. `putIfAbsent("b", 5)` — key "b" does not exist, so it is added with value `5`. Result: `map.get("a")` = `1`, `map.get("b")` = `5`. Output: `1 5`.

---

**12. A**

`compute("a", ...)` — "a" exists with value `1`, so `v` is `1`. The lambda returns `1 + 10 = 11`. Map now has `"a" -> 11`.
`compute("b", ...)` — "b" does not exist, so `v` is `null`. The lambda returns `1`. Map now has `"b" -> 1`.
Output: `11 1`.

---

**13. B**

When `compute()` is called and the remapping function returns `null`, the entry is **removed** from the map (if it was present). So after `compute("key", (k, v) -> null)`, the key "key" is removed. `map.containsKey("key")` returns `false`.

---

**14. B**

`merge("a", 2, Integer::sum)` — key "a" exists with value `3`. The merge function receives `(3, 2)` and returns `3 + 2 = 5`. Key "a" is updated to `5`.
`merge("b", 2, Integer::sum)` — key "b" does not exist, so the value `2` is simply put (the function is not called). `map.get("b")` is `2`.
Output: `5 2`.

---

**15. B**

Per the `Map.merge()` contract: if the remapping function returns `null`, the entry is removed from the map. This is the same behavior as `compute()` when the function returns `null`. The entry is not kept and no `NullPointerException` is thrown.

---

**16. A, B, D, E, F**

PECS: Producer Extends, Consumer Super.
- A: Correct — `? extends T` means you read (produce) values of type T.
- B: Correct — `? super T` means you write (consume) values of type T.
- C: Wrong — you CANNOT add anything (except `null`) to a `List<? extends Number>` because the compiler doesn't know the exact type.
- D: Correct — you can read elements as `Number` from `List<? extends Number>`.
- E: Correct — from `List<? super Integer>` you can only read as `Object`, since the actual type could be `Integer`, `Number`, or `Object`.
- F: Correct — you can add `Integer` (or subtypes) to `List<? super Integer>`.

---

**17. A**

`List<? super Integer>` accepts `List<Integer>`, `List<Number>`, or `List<Object>`. `List<Number>` satisfies this bound. Adding `42` (autoboxed to `Integer`) to a `List<Number>` is valid. `numbers.get(0)` returns the `Integer` `42`, which prints as `42`.

---

**18. B**

`List.of("c", "a", "b")` creates an immutable list. `new ArrayList<>(...)` creates a mutable copy. `Collections.sort(list)` sorts in natural (alphabetical) order. Output: `[a, b, c]`.

---

**19. A, D**

- A: `new T[]` — cannot create a generic array; compile error.
- B: Valid, though verbose (diamond inference not used).
- C: Valid with diamond operator.
- D: `new ArrayList<int>()` — primitives cannot be type arguments; compile error.
- E: Valid; `String` is a subtype of `Object`.
- F: This actually compiles with an unchecked warning (raw type), not a compile error. The array type itself uses a raw `ArrayList`.

---

**20. A**

The comparator first compares by `String::length`, then by natural order for ties.
- "a" → length 1
- "bb" → length 2
- "cc" → length 2
- "aaa" → length 3

Sorted by length: "a" (1) < "bb"/"cc" (2, tie broken by natural order: "bb" < "cc") < "aaa" (3).
Result: `[a, bb, cc, aaa]`.

---

**21. A**

`List.remove(int index)` removes the element at the given index. `list.remove(1)` removes index 1 (value `1`). The list becomes `[3, 4, 1, 5]`. When the argument is a plain `int`, the index-based overload is called, not the `remove(Object)` overload.

---

**22. A**

`list.remove(Integer.valueOf(1))` calls `remove(Object)`, which removes the **first occurrence** of the object `1`. The list is `[3, 1, 4, 1, 5]`; the first occurrence of `1` is at index 1. After removal: `[3, 4, 1, 5]`.

---

**23. B**

`lower(e)` returns the greatest element strictly less than `e` (does not include `e` itself). `floor(e)` returns the greatest element less than or equal to `e` (includes `e` if present). `ceiling(e)` is the smallest element >= e. `higher(e)` is the smallest element strictly > e.

---

**24. A**

The `TreeSet` contains `[1, 3, 5, 7, 9]` in sorted order.
- `floor(6)`: greatest element <= 6 → `5`
- `ceiling(6)`: smallest element >= 6 → `7`

Output: `5 7`.

---

**25. B**

`ll.add("a")`, `ll.add("b")`, `ll.add("c")` → list is `[a, b, c]`. `ll.addFirst("z")` → list is `[z, a, b, c]`. `peekLast()` returns the last element without removing it → `"c"`. `size()` is `4`. Output: `c 4`.

---

**26. B, C, E, F**

- A: Wrong — `Map` does NOT extend `Collection`. It is a separate hierarchy.
- B: Correct — `Map.of()` throws `NullPointerException` if any key or value is null.
- C: Correct — `Map.of()` throws `IllegalArgumentException` if duplicate keys are provided.
- D: Wrong — `HashMap` does not maintain insertion order.
- E: Correct — `LinkedHashMap` maintains insertion order.
- F: Correct — `TreeMap` iterates entries in ascending key order.

---

**27. C**

`computeIfAbsent("key", k -> new ArrayList<>())` — on the first call, "key" is absent, so a new `ArrayList` is created, stored, and returned. `.add(1)` adds `1` to this list.

On the second call, "key" is now present (with `[1]`), so the lambda is NOT called. The existing list `[1]` is returned. `.add(2)` adds `2` to the same list.

Result: `map.get("key")` = `[1, 2]`.

---

**28. A**

`computeIfPresent("a", (k, v) -> v * 2)` — "a" exists with value `1`; new value = `2`. `computeIfPresent("b", ...)` — "b" does not exist; nothing happens, no entry is created. `map.get("b")` returns `null`. Output: `2 null`.

---

**29. E**

Both B and C correctly accept a list of any type:
- `List<?>` (unbounded wildcard) allows any list regardless of type parameter.
- `<T> void print(List<T> list)` is a generic method that works for any type T.

Option A only accepts `List<Object>` — a `List<String>` cannot be passed due to invariance. Option D uses a raw type, which works but suppresses generics entirely.

---

**30. A**

`List.copyOf(original)` creates an **immutable snapshot** of `original` at the time of the call. It is not backed by the original list. When `original.add("z")` is called afterward, the copy is unaffected. `copy.size()` remains `2`.

---

**31. C**

`Collections.singletonList()` returns an immutable single-element list. Any structural or element-modifying operation — including `set()` — throws `UnsupportedOperationException`. (Contrast with `Arrays.asList()` which allows `set()`.)

---

**32. B, D**

`retainAll(s2)` modifies `s1` in place, keeping only elements that are also in `s2`. The intersection of `{1,2,3,4}` and `{3,4,5,6}` is `{3,4}`. So `s1` becomes `[3, 4]` (iteration order not guaranteed for `HashSet`, but the content is `{3,4}`). B and D both correctly describe this outcome.

---

**33. A, C, D**

- A: Correct — at runtime, both are just `List` (raw type); the generic parameter is erased.
- B: Wrong — you cannot use parameterized types with `instanceof`; `list instanceof List<String>` is a compile error. You can only write `list instanceof List<?>` or `list instanceof List`.
- C: Correct — the compiler replaces generic type parameters with `Object` (or the upper bound if bounded).
- D: Correct — casting to a parameterized type (e.g., `(List<String>) rawList`) produces an unchecked cast warning.
- E: Wrong — `new T[]` is a compile error; you cannot create a generic array.

---

**34. B**

`Comparator.comparing(String::length).reversed()` creates a comparator that first compares by length, then reverses the entire result. This gives descending order by length.
- "abc" → length 3
- "ab" → length 2
- "a" → length 1

Descending: `[abc, ab, a]`.

---

**35. B**

Sorting by `age` ascending, then by `name` ascending for ties:
- Bob: age 25
- Alice: age 30
- Carol: age 30 (tie with Alice, broken by name: "Alice" < "Carol")

Order: Bob (25), Alice (30), Carol (30).
`people.get(0).name()` = "Bob", `people.get(1).name()` = "Alice". Output: `Bob Alice`.

---

**36. B**

`TreeMap` stores keys in natural sorted order. The three string keys in alphabetical order are: "apple", "banana", "cherry". `forEach` iterates in key-sorted order. Output: `apple banana cherry `.

---

**37. A, C, D, F**

- A: Correct — `List` extends `SequencedCollection` in Java 21.
- B: Wrong — `HashSet` does NOT implement `SequencedCollection` (no defined encounter order).
- C: Correct — `LinkedHashSet` implements `SequencedSet` (which extends `SequencedCollection` and `Set`).
- D: Correct — `SequencedCollection` adds `getFirst()`, `getLast()`, `addFirst()`, `addLast()`, `removeFirst()`, `removeLast()`, and `reversed()`.
- E: Wrong — `ArrayDeque` implements `Deque`, which extends `SequencedCollection`.
- F: Correct — `TreeSet` implements `SequencedSet` (sorted order gives a defined first/last).

---

**38. B**

`SequencedCollection` is a superinterface of `List`, so the assignment compiles. `addFirst("z")` inserts "z" at the front. The list (and `sc` reference the same object) becomes `[z, a, b, c]`. `list.getFirst()` returns "z". `list.size()` is `4`. Output: `z 4`.

---

**39. A**

`LinkedHashMap` maintains insertion order. `SequencedMap` (Java 21) provides `firstEntry()` and `lastEntry()`. The first inserted key is "one" and the last is "three". Output: `one three`.

---

**40. B**

`list.reversed()` returns a **reverse-ordered view** backed by the original list. Adding to index 0 of the reversed view inserts at the front of the reversed view — which corresponds to the **end** of the original list. So `reversed.add(0, "z")` effectively appends "z" to the original `list`. The original list becomes `[a, b, c, z]`. Output: `[a, b, c, z]`.

---

**41. E**

- A: `T super Number` is not valid syntax for a type parameter bound (lower bounds are only valid for wildcards `? super T`, not for type parameter declarations).
- B: Valid — `class Box<T extends Number>` is a valid upper-bounded type parameter.
- C: Wildcards (`?`) cannot be used as type parameter names in class declarations.
- D: Valid — intersection bounds (`T extends Number & Comparable<T>`) are allowed in type parameter declarations.

Both B and D are syntactically and semantically valid, so E is correct.

---

**42. C**

`Collections.max(list)` returns the maximum element using natural ordering. The list contains `[3, 1, 4, 1, 5, 9]`. The maximum value is `9`. The generic bound `<T extends Comparable<T>>` ensures T can be compared. `Integer` implements `Comparable<Integer>`, so this compiles and works correctly.

---

**43. C**

`Set.of()` returns an immutable set. Any attempt to mutate it — including `add()`, `remove()`, or `clear()` — throws `UnsupportedOperationException` at runtime.

---

**44. C**

`Map.of()` returns an immutable map. Calling `put()` on it throws `UnsupportedOperationException` at runtime, just like `List.of()` and `Set.of()`.

---

**45. B**

`Set.of()` checks for duplicate elements at creation time. If duplicate elements are detected, it throws `IllegalArgumentException` at runtime (not a compile error, since values are runtime expressions). The method signature accepts varargs, so duplicates can only be detected at runtime.

---

**46. B**

`offer()` adds to the back of the deque (FIFO queue behavior). After three `offer()` calls, the deque is `[first, second, third]` (front to back). `poll()` removes and returns the front element → `"first"`. After the poll, deque is `[second, third]`. `peekLast()` returns the last element without removing → `"third"`. Output: `first third`.

---

**47. B**

Java's type system erases generic information at runtime, but the actual objects are still `String`, `Integer`, and `Double` instances — the runtime types of the objects are not affected. `getClass().getSimpleName()` reports the actual runtime class of each object. Output: `String Integer Double `.

---

**48. C**

`TreeSet` uses natural string ordering, which is case-sensitive. In Unicode, all uppercase letters ('A'-'Z') come before lowercase letters ('a'-'z'). So `"HELLO" < "hello"` lexicographically. Both strings are distinct according to `compareTo`, so both are stored. Size is `2`. `first()` returns the smallest element — `"HELLO"`. Output: `2 HELLO`.

---

**49. B**

`Collections.unmodifiableList(list)` creates a **view** — it wraps the original list without copying it. Mutations through `list` (the backing list) are still reflected in `unmod`. Calling `list.add("d")` adds to the underlying list, and `unmod.size()` reports `4`. (A `UnsupportedOperationException` would only be thrown if you tried to mutate through `unmod` itself.)

---

**50. A, B, C, D, F**

- A: Correct — a class implements `Comparable<T>` to define its natural ordering via `compareTo()`.
- B: Correct — `Comparator<T>` is a `@FunctionalInterface` with the SAM `compare(T o1, T o2)`.
- C: Correct — `compareTo()` returns negative if `this < argument`, 0 if equal, positive if `this > argument`.
- D: Correct — `Comparator.comparing(keyExtractor)` creates a comparator that sorts by the key extractor's result using natural order.
- E: Wrong — `reversed()` does NOT modify the original comparator in place; it returns a **new** `Comparator` that imposes the reverse ordering. `Comparator` instances are effectively immutable.
- F: Correct — `new TreeSet<>(comparator)` accepts a custom `Comparator` and uses it instead of natural ordering.

---
