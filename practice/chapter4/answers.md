# Chapter 4: Core APIs — Answers

---

**1. A**

String concatenation evaluates left to right. `"c" + 1` → `"c1"` (String), then `+ 2` → `"c12"`. For `1 + 2 + "c"`: `1 + 2 = 3` (both ints), then `3 + "c"` → `"3c"`. Output: `c12` then `3c`.

---

**2. A**

`"Hello World"` has 11 characters (indices 0–10). `length()` = `11`. `charAt(6)` is `W` (H=0, e=1, l=2, l=3, o=4, (space)=5, W=6). Output: `11` then `W`.

---

**3. B**

String literals are interned. `a` and `b` both point to the same pool object → `a == b` is `true`. `c = new String("Java")` creates a new heap object → `a == c` is `false`. `a.equals(c)` compares content → `true`. Output: `true false true`.

---

**4. A**

`substring(2)` returns from index 2 to end: `"cde"`. `substring(2, 4)` returns from index 2 (inclusive) to 4 (exclusive): characters at index 2 and 3 → `"cd"`. Output: `cde` then `cd`.

---

**5. B**

`String` is immutable. `s.toUpperCase()` returns a NEW String but the result is discarded. `s` still refers to `"hello"`. Output: `hello`.

---

**6. C**

`"  Hello  "` has 9 characters. `strip()` removes leading and trailing whitespace → `"Hello"`. `"Hello".length()` = `5`. Output: `5`.

---

**7. A**

`sb = "Java"`. `append(" 21")` → `"Java 21"`. `insert(4, " SE")` inserts at index 4 → `"Java SE 21"`. Output: `Java SE 21`.

---

**8. A**

`delete(2, 4)` removes characters from index 2 (inclusive) to 4 (exclusive) — that's `'c'` and `'d'`. Result: `"abef"`. Output: `abef`.

---

**9. B**

`reverse()` reverses the content in place. `"Hello"` → `"olleH"`. Output: `olleH`.

---

**10. A, B, C**

`new StringBuilder()` creates empty with default capacity. `new StringBuilder("text")` initializes with a string. `new StringBuilder(16)` sets initial capacity. D doesn't exist (`StringBuilder.of` is not a method). E: `new StringBuilder('a')` — `char` is widened to `int` (97), setting initial capacity to 97 — valid but unusual; this IS valid.

> **Corrected answer: A, B, C, E**

---

**11. B**

`indexOf('l')` in `"Hello"`: first `l` is at index 2. `indexOf('l', 4)` starts searching from index 4 — only `o` remains at index 4. No `l` found → `-1`. Output: `2 -1`.

---

**12. B**

Concatenating a `String` with `null` converts `null` to the string `"null"`. Output: `Hellonull`.

---

**13. A**

`"Java SE 21".startsWith("Java")` → `true`. `contains("SE")` → `true`. `endsWith("21")` → `true`. Output: `true true true`.

---

**14. B**

After sorting, `arr = {1, 2, 3, 4, 5}`. `binarySearch` for `4` finds it at index `3`. Output: `3`.

---

**15. C**

`arr1 == arr2` compares array references → `false` (two different objects). `Arrays.equals(arr1, arr2)` compares element by element → `true`. Output: `false true`.

---

**16. A**

`remove(1)` removes the element at index 1 (which is `"b"`). List becomes `["a", "c"]`. Output: `[a, c]`.

---

**17. A**

`replace` replaces ALL occurrences. Both `"a"` occurrences become `"X"`. Output: `XbcXbc`.

---

**18. A**

`Math.round(3.5)` = `4` (rounds half-up). `Math.round(3.4)` = `3`. `Math.round(-3.5)` = `-3` (rounds toward positive infinity for .5 cases). Output: `4 3 -3`.

---

**19. A**

`Math.min(3, 7)` = `3`. `Math.max(3, 7)` = `7`. `Math.abs(-5)` = `5`. Output: `3 7 5`.

---

**20. B**

`"  ".isEmpty()` → `false` (length is 2, not 0). `"  ".isBlank()` → `true` (contains only whitespace). Output: `false true`.

---

**21. A, B, D, E**

C is wrong — you can't specify dimensions in the type portion and leave the initializer empty like that. A, B, D, and E are all valid 2D array declarations.

---

**22. B**

`grid.length` is the number of rows → `2`. `grid[0].length` is the number of columns in row 0 → `3`. Output: `2 3`.

---

**23. C**

`split(" ")` splits on single space. Parts: `["Hello", "World"]`. `parts.length = 2`. `parts[1] = "World"`. Output: `2 World`.

---

**24. A**

`LocalDate.of(2024, 1, 15).plusMonths(2)` → `2024-03-15`. Output: `2024-03-15`.

---

**25. A**

`Period.between(d1, d2)` where d1 is March 10 and d2 is March 15. The period is 5 days. `getDays()` = `5`. Output: `5`.

---

**26. A, C, D**

`String`, `LocalDate`, and `LocalDateTime` are immutable. `StringBuilder` and `ArrayList` are mutable.

---

**27. B**

`sb2 = sb` — both variables reference the SAME `StringBuilder` object. Mutating via `sb` is visible through `sb2`. Output: `Hello World`.

---

**28. B**

`arr[0]` is `0` (default int value). `arr[3]` is out of bounds (valid indices: 0, 1, 2) → `ArrayIndexOutOfBoundsException` at runtime. Output: `0` then exception.

---

**29. C**

`LocalTime.of(13, 30)` → the `toString()` format includes seconds when they are zero: `13:30`. Output: `13:30`.

---

**30. B**

`toUpperCase()` returns a new `String`. `s` is still `"Java"`. Output: `JAVA` then `Java`.

---

**31. A, B, C, E**

D is wrong — `LocalDateTime` does not have a public constructor (it's created via factory methods). A, B, C, and E are valid creation methods.

---

**32. B**

`"Hello".compareTo("Hello")` = `0` (equal). `"Hello".compareTo("hello")` compares `'H'` (72) vs `'h'` (104): `72 - 104 = -32`. Output: `0 -32`.

---

**33. C**

When an element is not found, `binarySearch` returns `-(insertion point) - 1`. For example, if the element would be inserted at index 3, it returns `-4`.

---

**34. A**

2024 is a leap year. `2024-02-28.plusDays(1)` → `2024-02-29`. Output: `2024-02-29`.

---

**35. B**

`list.size()` = `3`. After `clear()`, `list.isEmpty()` = `true`. Output: `3 true`.

---

**36. A**

`"abcde".indexOf("cd")` — `cd` starts at index 2. `indexOf("xy")` — not found → `-1`. Output: `2 -1`.

---

**37. B**

`Duration.ofHours(25).toMinutes()` = `25 * 60 = 1500`. Output: `1500`.

---

**38. D**

`binarySearch` requires the array to be sorted first. The array `{3, 1, 4, 1, 5, 9}` is NOT sorted. The result is unpredictable/undefined.

---

**39. E**

`"Hello".charAt(5)` — valid indices are 0–4 (length 5). Index 5 is out of bounds → `StringIndexOutOfBoundsException`.

---

**40. C, E**

`String` has `matches(String regex)` (C) and `toUpperCase()` (E) that `StringBuilder` does not. `length()`, `charAt()`, and `append()` — note `append` is on `StringBuilder`, not on `String`. `toUpperCase()` is on `String`, not `StringBuilder`.

> **Corrected: C and E** are on `String` but not `StringBuilder`.

---

**41. C**

`"Java" + " " + "21"` = `"Java 21"`. Length = 7. Output: `7`.

---

**42. B**

`2024-01-31.plusMonths(1)` → February 31 doesn't exist. Java automatically adjusts to the last valid day: `2024-02-29` (2024 is a leap year). Output: `2024-02-29`.

---

**43. A**

The text block content is `Hello\nWorld\n`. The closing `"""` on its own line determines indentation stripping (8 spaces are stripped). After stripping, content is `Hello\nWorld\n`. `strip()` removes the trailing newline. Output: `Hello\nWorld` (printed on two lines).

---

**44. D**

`List.of()` creates an unmodifiable list. Calling `add()` throws `UnsupportedOperationException` at runtime.

---

**45. B**

`Math.floor(3.9)` = `3.0` (rounds toward negative infinity). `Math.ceil(3.1)` = `4.0` (rounds toward positive infinity). Both return `double`. Output: `3.0 4.0`.

---

**46. B**

`concat` returns a NEW String. `s` still refers to `"Java"`. The concatenated string `"Java 21"` is printed once (the return value). Output: `Java 21` then `Java`.

---

**47. A, C, E**

`List.of()` (A) is unmodifiable. `Collections.unmodifiableList()` (C) wraps a list in an unmodifiable view. `List.copyOf()` (E) creates an unmodifiable copy. `new ArrayList<>(Arrays.asList(...))` (B) creates a modifiable list. `Arrays.asList()` (D) creates a fixed-size list (can set elements but not add/remove).

---

**48. A**

June 15, 2024 was a Saturday. Output: `SATURDAY`.

---

**49. B**

`Arrays.copyOf(a, 5)` creates a new array of length 5. The first 3 elements are copied from `a`; the remaining 2 default to `0`. `b.length = 5`, `b[4] = 0`. Output: `5 0`.

---

**50. A**

`ZonedDateTime.of(ldt, zone)` assigns the zone without adjusting the time. `getHour()` returns `2` as stored. (Note: if you used `ZonedDateTime.ofInstant()`, DST would be applied differently.) Output: `2`.
