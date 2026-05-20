# Chapter 4: Core APIs — Practice Questions

---

**1.** What is the output of the following?

```java
System.out.println("c" + 1 + 2);
System.out.println(1 + 2 + "c");
```

A. `c12` then `3c`

B. `c3` then `3c`

C. `c12` then `12c`

D. `3c` then `c12`

E. The code does not compile.

---

**2.** What is the output of the following?

```java
String s = "Hello World";
System.out.println(s.length());
System.out.println(s.charAt(6));
```

A. `11` then `W`

B. `10` then `W`

C. `11` then `o`

D. `10` then `o`

E. The code does not compile.

---

**3.** What is the output of the following?

```java
String a = "Java";
String b = "Java";
String c = new String("Java");
System.out.println(a == b);
System.out.println(a == c);
System.out.println(a.equals(c));
```

A. `true` then `true` then `true`

B. `true` then `false` then `true`

C. `false` then `false` then `true`

D. `true` then `false` then `false`

E. The code does not compile.

---

**4.** What is the output of the following?

```java
String s = "abcde";
System.out.println(s.substring(2));
System.out.println(s.substring(2, 4));
```

A. `cde` then `cd`

B. `cde` then `cde`

C. `bcd` then `bc`

D. `cde` then `c`

E. The code does not compile.

---

**5.** Strings in Java are immutable. What does the following code print?

```java
String s = "hello";
s.toUpperCase();
System.out.println(s);
```

A. `HELLO`

B. `hello`

C. `Hello`

D. The code does not compile.

E. `null`

---

**6.** What is the output of the following?

```java
String s = "  Hello  ";
System.out.println(s.strip().length());
```

A. `9`

B. `7`

C. `5`

D. `8`

E. The code does not compile.

---

**7.** What is the output of the following?

```java
StringBuilder sb = new StringBuilder("Java");
sb.append(" 21");
sb.insert(4, " SE");
System.out.println(sb);
```

A. `Java SE 21`

B. `Java 21 SE`

C. `JavaSE 21`

D. `SE Java 21`

E. The code does not compile.

---

**8.** What is the output of the following?

```java
StringBuilder sb = new StringBuilder("abcdef");
sb.delete(2, 4);
System.out.println(sb);
```

A. `abef`

B. `abcef`

C. `abcdef`

D. `abde`

E. The code does not compile.

---

**9.** What is the output of the following?

```java
StringBuilder sb = new StringBuilder("Hello");
sb.reverse();
System.out.println(sb);
```

A. `Hello`

B. `olleH`

C. `Helo`

D. The code does not compile.

E. `hELLO`

---

**10.** Which of the following are valid ways to create a `StringBuilder`? (Choose all that apply.)

A. `new StringBuilder()`

B. `new StringBuilder("text")`

C. `new StringBuilder(16)`

D. `StringBuilder.of("text")`

E. `new StringBuilder('a')`

---

**11.** What is the output of the following?

```java
String s = "Hello";
System.out.println(s.indexOf('l'));
System.out.println(s.indexOf('l', 4));
```

A. `2` then `3`

B. `2` then `-1`

C. `3` then `-1`

D. `2` then `2`

E. The code does not compile.

---

**12.** What is the output of the following?

```java
String s = null;
System.out.println("Hello" + s);
```

A. `Hello`

B. `Hellonull`

C. The code does not compile.

D. A `NullPointerException` is thrown.

E. `null`

---

**13.** What is the output of the following?

```java
String s = "Java SE 21";
System.out.println(s.startsWith("Java"));
System.out.println(s.contains("SE"));
System.out.println(s.endsWith("21"));
```

A. `true` then `true` then `true`

B. `true` then `false` then `true`

C. `false` then `true` then `false`

D. The code does not compile.

E. `true` then `true` then `false`

---

**14.** What is the output of the following?

```java
int[] arr = {5, 3, 1, 4, 2};
java.util.Arrays.sort(arr);
System.out.println(java.util.Arrays.binarySearch(arr, 4));
```

A. `2`

B. `3`

C. `1`

D. `-1`

E. The code does not compile.

---

**15.** What is the output of the following?

```java
int[] arr1 = {1, 2, 3};
int[] arr2 = {1, 2, 3};
System.out.println(arr1 == arr2);
System.out.println(java.util.Arrays.equals(arr1, arr2));
```

A. `true` then `true`

B. `false` then `false`

C. `false` then `true`

D. `true` then `false`

E. The code does not compile.

---

**16.** What is the output of the following?

```java
var list = new java.util.ArrayList<String>();
list.add("a");
list.add("b");
list.add("c");
list.remove(1);
System.out.println(list);
```

A. `[a, c]`

B. `[b, c]`

C. `[a, b]`

D. `[a, b, c]`

E. The code does not compile.

---

**17.** What is the output of the following?

```java
String s = "abcabc";
System.out.println(s.replace("a", "X"));
```

A. `XbcXbc`

B. `Xbcabc`

C. `abcXbc`

D. `XbcXbc` and only the first `a` is replaced.

E. The code does not compile.

---

**18.** What is the result of the following?

```java
System.out.println(Math.round(3.5));
System.out.println(Math.round(3.4));
System.out.println(Math.round(-3.5));
```

A. `4` then `3` then `-3`

B. `4` then `3` then `-4`

C. `3` then `3` then `-4`

D. `4` then `4` then `-3`

E. The code does not compile.

---

**19.** What is the output of the following?

```java
System.out.println(Math.min(3, 7));
System.out.println(Math.max(3, 7));
System.out.println(Math.abs(-5));
```

A. `3` then `7` then `5`

B. `7` then `3` then `-5`

C. `3` then `7` then `-5`

D. `7` then `7` then `5`

E. The code does not compile.

---

**20.** What is the output of the following?

```java
String s = "  ";
System.out.println(s.isEmpty());
System.out.println(s.isBlank());
```

A. `true` then `true`

B. `false` then `true`

C. `true` then `false`

D. `false` then `false`

E. The code does not compile.

---

**21.** Which of the following correctly declares and initializes a 2D array? (Choose all that apply.)

A. `int[][] matrix = new int[3][3];`

B. `int[][] matrix = {{1,2},{3,4}};`

C. `int[3][3] matrix = new int[][];`

D. `int[][] matrix = new int[3][];`

E. `int matrix[][] = new int[2][2];`

---

**22.** What is the output of the following?

```java
int[][] grid = new int[2][3];
System.out.println(grid.length);
System.out.println(grid[0].length);
```

A. `6` then `6`

B. `2` then `3`

C. `3` then `2`

D. `2` then `2`

E. The code does not compile.

---

**23.** What is the output of the following?

```java
String s = "Hello World";
String[] parts = s.split(" ");
System.out.println(parts.length);
System.out.println(parts[1]);
```

A. `1` then `Hello World`

B. `2` then `Hello`

C. `2` then `World`

D. `11` then `W`

E. The code does not compile.

---

**24.** What is the output of the following?

```java
import java.time.LocalDate;
LocalDate date = LocalDate.of(2024, 1, 15);
System.out.println(date.plusMonths(2));
```

A. `2024-03-15`

B. `2024-01-17`

C. `2024-02-15`

D. `2026-01-15`

E. The code does not compile.

---

**25.** What is the output of the following?

```java
import java.time.*;
LocalDate d1 = LocalDate.of(2024, 3, 10);
LocalDate d2 = LocalDate.of(2024, 3, 15);
Period p = Period.between(d1, d2);
System.out.println(p.getDays());
```

A. `5`

B. `-5`

C. `0`

D. `144` (hours)

E. The code does not compile.

---

**26.** Which of the following are immutable? (Choose all that apply.)

A. `String`

B. `StringBuilder`

C. `LocalDate`

D. `LocalDateTime`

E. `ArrayList`

---

**27.** What is the output of the following?

```java
StringBuilder sb = new StringBuilder("Hello");
StringBuilder sb2 = sb;
sb.append(" World");
System.out.println(sb2);
```

A. `Hello`

B. `Hello World`

C. `World`

D. The code does not compile.

E. `null`

---

**28.** What is the output of the following?

```java
int[] arr = new int[3];
System.out.println(arr[0]);
System.out.println(arr[3]);
```

A. `0` then `0`

B. `0` then throws `ArrayIndexOutOfBoundsException`

C. `null` then throws an exception

D. The code does not compile.

E. `0` then `null`

---

**29.** What is the output of the following?

```java
import java.time.LocalTime;
LocalTime t = LocalTime.of(13, 30);
System.out.println(t);
```

A. `1:30 PM`

B. `13:30`

C. `13:30:00`

D. `01:30 PM`

E. The code does not compile.

---

**30.** What is the output of the following?

```java
String s = "Java";
System.out.println(s.toUpperCase());
System.out.println(s);
```

A. `JAVA` then `JAVA`

B. `JAVA` then `Java`

C. `Java` then `JAVA`

D. `Java` then `Java`

E. The code does not compile.

---

**31.** Which of the following correctly creates a `LocalDateTime`? (Choose all that apply.)

A. `LocalDateTime.now()`

B. `LocalDateTime.of(2024, 1, 15, 10, 30)`

C. `LocalDateTime.of(LocalDate.now(), LocalTime.now())`

D. `new LocalDateTime(2024, 1, 15, 10, 30)`

E. `LocalDateTime.parse("2024-01-15T10:30:00")`

---

**32.** What is the output of the following?

```java
String s = "Hello";
System.out.println(s.compareTo("Hello"));
System.out.println(s.compareTo("hello"));
```

A. `0` then `0`

B. `0` then `-32`

C. `0` then `32`

D. `1` then `-1`

E. The code does not compile.

---

**33.** What does `Arrays.binarySearch()` return when the element is not found?

A. `-1` always

B. `0`

C. A negative number indicating the insertion point: `-(insertion point) - 1`

D. `Integer.MIN_VALUE`

E. It throws an exception.

---

**34.** What is the output of the following?

```java
import java.time.*;
LocalDate date = LocalDate.of(2024, 2, 28);
System.out.println(date.plusDays(1));
```

A. `2024-02-29`

B. `2024-03-01`

C. `2024-02-28`

D. `2024-03-28`

E. The code does not compile.

---

**35.** What is the output of the following?

```java
var list = new java.util.ArrayList<Integer>();
list.add(1);
list.add(2);
list.add(3);
System.out.println(list.size());
list.clear();
System.out.println(list.isEmpty());
```

A. `3` then `false`

B. `3` then `true`

C. `0` then `true`

D. `3` then `0`

E. The code does not compile.

---

**36.** What is the output of the following?

```java
String s = "abcde";
System.out.println(s.indexOf("cd"));
System.out.println(s.indexOf("xy"));
```

A. `2` then `-1`

B. `3` then `-1`

C. `2` then `0`

D. `1` then `-1`

E. The code does not compile.

---

**37.** What is the output of the following?

```java
import java.time.*;
Duration d = Duration.ofHours(25);
System.out.println(d.toMinutes());
```

A. `25`

B. `1500`

C. `1440`

D. `86400`

E. The code does not compile.

---

**38.** What is the output of the following?

```java
int[] arr = {3, 1, 4, 1, 5, 9};
System.out.println(java.util.Arrays.binarySearch(arr, 4));
```

A. `2`

B. `-3`

C. `1`

D. The result is unpredictable because the array is not sorted.

E. The code does not compile.

---

**39.** What is the output of the following?

```java
String s = "Hello";
System.out.println(s.charAt(5));
```

A. `null`

B. `o`

C. ` ` (a space)

D. The code does not compile.

E. A `StringIndexOutOfBoundsException` is thrown at runtime.

---

**40.** Which of the following methods are on `String` but NOT on `StringBuilder`? (Choose all that apply.)

A. `length()`

B. `charAt(int)`

C. `matches(String regex)`

D. `append(String)`

E. `toUpperCase()`

---

**41.** What is the output of the following?

```java
StringBuilder sb = new StringBuilder();
sb.append("Java").append(" ").append("21");
System.out.println(sb.length());
```

A. `5`

B. `6`

C. `7`

D. `8`

E. The code does not compile.

---

**42.** What is the output of the following?

```java
import java.time.*;
LocalDate date = LocalDate.of(2024, 1, 31);
System.out.println(date.plusMonths(1));
```

A. `2024-02-31`

B. `2024-02-29`

C. `2024-03-02`

D. `2024-02-28`

E. The code does not compile.

---

**43.** What is the output of the following?

```java
String text = """
        Hello
        World
        """;
System.out.println(text.strip());
```

A. `Hello\nWorld`

B. `Hello\n        World`

C. `Hello\nWorld\n` (trailing newline included)

D. `Hello World`

E. The code does not compile.

---

**44.** What is the output of the following?

```java
var list = java.util.List.of("a", "b", "c");
list.add("d");
System.out.println(list);
```

A. `[a, b, c, d]`

B. `[a, b, c]`

C. The code does not compile.

D. An `UnsupportedOperationException` is thrown at runtime.

E. A `NullPointerException` is thrown.

---

**45.** What is the output of the following?

```java
System.out.println(Math.floor(3.9));
System.out.println(Math.ceil(3.1));
```

A. `3` then `4`

B. `3.0` then `4.0`

C. `4.0` then `4.0`

D. `3.0` then `3.0`

E. The code does not compile.

---

**46.** What is the output of the following?

```java
String s = "Java";
System.out.println(s.concat(" 21"));
System.out.println(s);
```

A. `Java 21` then `Java 21`

B. `Java 21` then `Java`

C. `Java` then `Java 21`

D. `Java` then `Java`

E. The code does not compile.

---

**47.** Which of the following creates an unmodifiable list? (Choose all that apply.)

A. `List.of("a", "b")`

B. `new ArrayList<>(Arrays.asList("a", "b"))`

C. `Collections.unmodifiableList(new ArrayList<>())`

D. `Arrays.asList("a", "b")`

E. `List.copyOf(list)`

---

**48.** What is the output of the following?

```java
import java.time.*;
LocalDate d = LocalDate.of(2024, 6, 15);
System.out.println(d.getDayOfWeek());
```

A. `FRIDAY`

B. `SATURDAY`

C. `SUNDAY`

D. `MONDAY`

E. The code does not compile.

---

**49.** What is the output of the following?

```java
int[] a = {1, 2, 3};
int[] b = java.util.Arrays.copyOf(a, 5);
System.out.println(b.length);
System.out.println(b[4]);
```

A. `3` then `0`

B. `5` then `0`

C. `5` then throws `ArrayIndexOutOfBoundsException`

D. `3` then throws an exception

E. The code does not compile.

---

**50.** What is the output of the following?

```java
import java.time.*;
LocalDateTime ldt = LocalDateTime.of(2024, 3, 10, 2, 30);
ZoneId zone = ZoneId.of("America/New_York");
ZonedDateTime zdt = ZonedDateTime.of(ldt, zone);
System.out.println(zdt.getHour());
```

A. `2`

B. `3`

C. `7`

D. `0`

E. The code does not compile.
