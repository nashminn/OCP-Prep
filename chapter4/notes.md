# Chapter 4: Core APIs

---

## String

- Implements `CharSequence` interface
- **Immutable** — every method that "changes" a String actually returns a new one

### Concatenation Rules (in order, evaluated left to right)

1. If both operands are numeric → **addition**
2. If either operand is a String → **concatenation**
3. Evaluated **left to right**

```java
System.out.println("c" + 1 + 2);   // c12  — "c" makes everything after it concat
System.out.println(1 + 2 + "c");   // 3c   — 1+2 evaluates to 3 first, then "3"+"c"
```

**`null` in concatenation** — doesn't explode, just becomes the string `"null"`:
```java
String s = null;
System.out.println("hello" + s);   // "hellonull"
```

**`+=` works too:**
```java
String s = "a";
s += "b";   // s is now "ab"
```

> **EXAM TRAP**: `String x = 1;` does NOT compile. You'd need `String.valueOf(1)`. This is not automatic.

---

## String Methods

`length()` is a **method** for String (not a property like arrays — don't mix them up).

| Method | Description |
|---|---|
| `length()` | number of characters |
| `charAt(int index)` | character at index (0-based) |
| `indexOf(char)` | first occurrence, or -1 |
| `indexOf(char, int fromIndex)` | starts searching from fromIndex |
| `indexOf(String)` | first occurrence of substring |
| `indexOf(String, int fromIndex)` | starts searching from fromIndex |
| `codePointAt(int index)` | Unicode code point at index (code points > char size) |
| `codePointBefore(int index)` | code point before index |
| `codePointCount(int begin, int end)` | count of code points in range |

### substring — "the trickiest"

```java
substring(int startIndex)                   // from startIndex to end
substring(int startIndex, int endIndex)     // [startIndex, endIndex) — endIndex is EXCLUSIVE
```

```java
String s = "animal";
s.substring(3);      // "mal"
s.substring(3, 4);   // "m"
s.substring(3, 3);   // "" — same index → EMPTY STRING (not an error!)
s.substring(3, 2);   // throws StringIndexOutOfBoundsException — backward = bad
```

> **EXAM TRAP**: Same start and end index → **empty string**, not an exception. Reversed (start > end) → exception.

### Case, Equality, Search

```java
toLowerCase()
toUpperCase()
// returns NEW string — originals untouched (immutable, remember)

equals(Object obj)          // logical equality — checks actual content
equalsIgnoreCase(String s)

startsWith(String prefix)
startsWith(String prefix, int fromIndex)   // starts checking from fromIndex
endsWith(String suffix)

contains(CharSequence seq)
```

### replace

```java
replace(char oldChar, char newChar)
replace(CharSequence target, CharSequence replacement)
```

> No regex here — that's `replaceAll()` / `replaceFirst()`. Plain `replace()` does literal replacement.

### Whitespace removal

```java
trim()             // removes \t, \r, \n, and space from start/end
strip()            // same as trim() + Unicode whitespace support (the upgrade)
stripLeading()     // start only
stripTrailing()    // end only
```

Unicode whitespace character: `'\u2000'` — `strip()` handles it, `trim()` doesn't.

### indent and stripIndent

```java
indent(int n)       // adds n spaces to each line; also NORMALIZES whitespace:
                    //   1. adds \n to end if missing
                    //   2. converts all line breaks → \n (rip Windows \r\n)

stripIndent()       // removes incidental whitespace (common leading spaces)
                    // also normalizes line breaks, but DOES NOT add \n at end
```

> **EXAM TRAP**: `indent()` adds `\n` at the end if missing. `stripIndent()` does NOT. They both normalize, but differ on this one thing.

### isEmpty vs isBlank

```java
isEmpty()    // true only for ""  — absolutely nothing
isBlank()    // true for "" or "   " or "\t" — anything that's all whitespace
```

### Formatting

```java
String.format("Hello %s, you are %d years old", name, age);
"Hello %s, you are %d years old".formatted(name, age);   // instance method version
```

| Format flag | Meaning |
|---|---|
| `%s` | String |
| `%d` | integer |
| `%f` | float (default: 6 decimal places) |
| `%n` | system-dependent line separator (fu Windows) |
| `%.2f` | 2 decimal places, rounding applied (NOT truncation) |
| `%10.2f` | total width 10, 2 decimal places |
| `%010.2f` | total width 10 with zero-padding |

> **EXAM TRAP**: Mismatching format type (e.g., `%d` with a String) throws `IllegalFormatConversionException` at runtime, not compile time.

### Method Chaining

String methods return new Strings, so you can chain them:
```java
String result = "  Hello World  ".strip().toLowerCase().replace("hello", "hi");
```

---

## StringBuilder — NOT Immutable

Unlike String, StringBuilder modifies itself in place. All mutating methods return a reference to the **same object** (useful for chaining, but don't be fooled — it's the same one).

`StringBuffer` — thread-safe version, but slower. Not really on the exam.

### Constructors

```java
new StringBuilder()               // empty, default capacity 16
new StringBuilder("initial")      // initialized with string
new StringBuilder(int capacity)   // empty with specified capacity
```

### Methods

```java
append(...)           // adds to end, many overloads
appendCodePoint(int)  // appends Unicode code point
insert(int offset, ...) // inserts at position

delete(int start, int end)    // removes [start, end)
deleteCharAt(int index)       // removes single character

replace(int start, int end, String replacement)   // delete + insert (different signature from String!)
reverse()             // REVERSE EXISTS FOR STRINGBUILDER, NOT FOR STRING

substring(int start)                  // returns a NEW String — does NOT modify StringBuilder
substring(int start, int end)         // same — returns String, SB unchanged

length()
charAt(int index)
indexOf(String str)
toString()
```

> **CRITICAL**: `StringBuilder.equals()` is NOT overridden. It does reference equality (like `==`). To compare content, do `.toString().equals(other.toString())`.

> **EXAM TRAP**: `String name = "x"; StringBuilder sb = new StringBuilder("x"); name == sb;` → **DOES NOT COMPILE**. You can't use `==` between different types like String and StringBuilder.

---

## String Pool (Intern Pool)

Location in the JVM that holds string literals and compile-time constants.

```java
String x = "Hello World";          // goes into the pool
String y = new String("Hello World");  // bypasses pool — new object on heap
System.out.println(x == y);        // false — different references
System.out.println(x.equals(y));   // true — same content
```

**Compile-time constants** go into the pool automatically:
```java
String a = "rat" + 1;               // compile-time constant → pool
String b = "r" + "a" + "t";         // compile-time constant → pool

String num = "1";
String c = "rat" + num;             // NOT compile-time constant (num is a variable) → heap
String d = "rat" + new String("1"); // NOT compile-time constant → heap
```

**`intern()`** — forces use of pool reference if one exists:
```java
String x = new String("hello").intern();   // now points to pool version
```

> **NOTE**: Don't use `intern()` or `==` for string comparison in real code. They're exam bait. Always use `.equals()` in the real world.

---

## Arrays

An array is an area of memory on the **heap**. `char[] letters` is a **reference variable**, not a primitive.

### Creation

```java
int[] nums = new int[3];           // default values: 0 for int, null for objects
int[] more = {12, 13, 53};         // anonymous array — type inferred
int[] nums2 = new int[]{1, 2, 3};  // explicit anonymous array

int[] ids, types;     // TWO int[] arrays
int ids[], types;     // ids is int[], types is plain int — sneaky!
```

> **EXAM TRAP**: The bracket placement matters. `int[] a, b` = two arrays. `int a[], b` = one array, one int.

### Properties

```java
arr.length   // PROPERTY, not a method (unlike String's length())
             // returns declared size regardless of how many elements are filled
```

### Printing

```java
Arrays.toString(arr);     // [1, 2, 3] — nice
arr.toString();           // [I@4e50df2e — garbage, don't use
```

### Sorting

```java
Arrays.sort(arr);
```

String sort order: **numbers < uppercase < lowercase**. Mnemonic: **"7Up"** (7 < U < p... sort of).

```java
String[] words = {"cat", "Apple", "123"};
Arrays.sort(words);   // ["123", "Apple", "cat"]
```

### Binary Search

```java
Arrays.binarySearch(arr, target)
```

- Array **must be sorted** first — otherwise result is undefined (garbage)
- Found → returns the **index**
- Not found → returns **negative**: `-(insertion point) - 1`

```java
int[] arr = {2, 4, 6, 8};
Arrays.binarySearch(arr, 4);    // 1 (found at index 1)
Arrays.binarySearch(arr, 3);    // -2 (would insert at index 1, so -(1)-1 = -2)
```

### Comparing Arrays

```java
Arrays.equals(arr1, arr2)
// checks same size + same elements + same order
// unlike arr1 == arr2 which is just reference equality (same object)
// note: arrays don't override equals() — so arr.equals(other) is also reference equality!
```

```java
Arrays.compare(arr1, arr2)
// 0      → equal
// < 0    → arr1 is "smaller" (first array is lexicographically first)
// > 0    → arr1 is "larger"
// null is the smallest
// shorter array that is a prefix of a longer one → shorter is smaller
```

```java
Arrays.mismatch(arr1, arr2)
// equal arrays → returns -1
// otherwise → returns first index where they differ
```

### Multi-Dimensional / Jagged Arrays

```java
int[][] grid = new int[3][4];   // 3 rows, 4 columns — rectangular

int[][] jagged = new int[2][];  // 2 rows, column count per row varies
jagged[0] = new int[5];
jagged[1] = new int[3];
```

### Varargs

A varargs parameter (`String... args`) can be used just like an array — iterate it, check `.length`, pass an array directly.

---

## Math API

All methods are `static` on `java.lang.Math`.

```java
Math.min(a, b)    // returns the smaller — same type as inputs
Math.max(a, b)    // returns the larger — same type as inputs

Math.round(double d)   // → long  (rounds .5 up, i.e., toward positive infinity)
Math.round(float f)    // → int

Math.ceil(double d)    // → double  (rounds UP to next whole number)
Math.floor(double d)   // → double  (rounds DOWN to next whole number)

Math.pow(double base, double exp)   // → double

Math.random()   // → double in [0.0, 1.0)
```

> **CRITICAL**: `round()` return type depends on input type. float → int. double → long. Don't mix these up. `ceil` and `floor` always return double regardless.

---

## BigInteger and BigDecimal

Both are **immutable** classes. Use them when you need:
- `BigInteger`: arbitrarily large integers
- `BigDecimal`: precise decimal math (money calculations — floating-point error is real: `64.1 * 100 = 6409.999...`)

### Creation

```java
BigInteger big = BigInteger.valueOf(100L);   // use valueOf, not new
BigDecimal dec = BigDecimal.valueOf(19.99);  // use valueOf

// BigInteger accepts long; BigDecimal also accepts double
```

### Constants

```java
BigInteger.ZERO   // 0
BigInteger.ONE    // 1
BigInteger.TEN    // 10
// BigDecimal has the same
```

### Methods (returns new object — immutable)

```java
add(BigDecimal other)
subtract(BigDecimal other)
multiply(BigDecimal other)
divide(BigDecimal other)
max(BigDecimal other)
```

> **EXAM TRAP**: `BigDecimal result = a + b;` DOES NOT COMPILE. No operator overloading in Java. Use `.add()`.

---

## Date and Time API

`import java.time.*;`

All use the **factory pattern** — private constructors, static factory methods.

```java
new LocalDate()   // DOES NOT COMPILE — constructor is private
LocalDate.now()   // correct
LocalDate.of(2026, 4, 20)   // correct
```

All are **immutable**. Modifying methods return new objects.

### The Four Classes

| Class | Has Date? | Has Time? | Has Time Zone? |
|---|---|---|---|
| `LocalDate` | Yes | No | No |
| `LocalTime` | No | Yes | No |
| `LocalDateTime` | Yes | Yes | No |
| `ZonedDateTime` | Yes | Yes | Yes |

### Creating Instances

```java
LocalDate.of(int year, int month, int dayOfMonth)
LocalDate.of(int year, Month month, int dayOfMonth)   // Month enum: JANUARY etc.
LocalTime.of(int hour, int minute)
LocalTime.of(int hour, int minute, int second, int nanosecond)
LocalDateTime.of(LocalDate, LocalTime)
ZonedDateTime.of(LocalDateTime, ZoneId)

ZoneId.of("US/Eastern")
ZoneId.of("Europe/Paris")
```

Months count from **1** in this API (January = 1). Or use the `Month` enum: `Month.JANUARY`.

```java
LocalDate.of(2026, 1, 32)   // DateTimeException — Jan only has 31 days
```

`+02:00 == GMT+2 == UTC+2`

### Manipulating Date/Time

All return new objects (immutable):

| Method type | LocalDate | LocalTime | LocalDateTime | ZonedDateTime |
|---|---|---|---|---|
| `plusDays/minusDays` | ✓ | | ✓ | ✓ |
| `plusWeeks/minusWeeks` | ✓ | | ✓ | ✓ |
| `plusMonths/minusMonths` | ✓ | | ✓ | ✓ |
| `plusYears/minusYears` | ✓ | | ✓ | ✓ |
| `plusHours/minusHours` | | ✓ | ✓ | ✓ |
| `plusMinutes/minusMinutes` | | ✓ | ✓ | ✓ |
| `plusSeconds/minusSeconds` | | ✓ | ✓ | ✓ |
| `plusNanos/minusNanos` | | ✓ | ✓ | ✓ |

`with*` methods create a copy with a specific field set to a given value (e.g., `withDayOfMonth(15)`).

### Comparing

```java
date1.isBefore(date2)
date1.isAfter(date2)
```

### Converting Between Types

```java
LocalDate.atTime(LocalTime)          // → LocalDateTime
LocalDate.atStartOfDay()             // → LocalDateTime (at 00:00)
LocalTime.atDate(LocalDate)          // → LocalDateTime (NOT LocalDate — easy to mix up)
LocalDateTime.atZone(ZoneId)         // → ZonedDateTime
ZonedDateTime.toInstant()            // → Instant (shows as GMT, loses time zone)
```

> **EXAM TRAP**: `LocalTime.atDate()` returns `LocalDateTime`, not `LocalDate`.

---

## Period

Date-based amounts — years, months, days. No time units.

```java
Period.ofYears(1)
Period.ofMonths(3)
Period.ofWeeks(2)
Period.ofDays(5)
Period.of(1, 2, 3)    // 1 year, 2 months, 3 days
```

> **CRITICAL**: Static factory methods — **cannot be chained**. `Period.ofYears(1).ofMonths(2)` just gives you `ofMonths(2)` — `ofYears` result is discarded. Use `Period.of(1, 2, 0)` instead.

Output format: `P` prefix, then `[n]Y[n]M[n]D`. Zero parts omitted:
```
Period.ofYears(1)         → P1Y
Period.of(1, 2, 3)        → P1Y2M3D
Period.ofDays(5)          → P5D
```

Periods can be **positive or negative**.

Works with: `LocalDate`, `LocalDateTime`

---

## Duration

Time-based amounts — days, hours, minutes, seconds, nanoseconds.

```java
Duration.ofHours(2)
Duration.ofMinutes(30)
Duration.ofSeconds(45)
Duration.ofDays(1)    // 24 hours, not calendar days
```

Output format: `PT` prefix, e.g., `PT2H30M`.

Works with: `LocalTime`, `LocalDateTime`, `ZonedDateTime`

> **CRITICAL**: Period and Duration are **not interchangeable**. Duration has time units — won't work with `LocalDate`. Period has date units — won't work with `LocalTime`.

---

## ChronoUnit

For calculating differences:

```java
ChronoUnit.HOURS.between(startTime, endTime)    // truncates, does NOT round
ChronoUnit.DAYS.between(date1, date2)
```

`ChronoUnit` implements `TemporalUnit`. Notable value: `ChronoUnit.HALF_DAYS` (12 hours, because apparently someone needed that).

---

## Instant

A specific **point in time** (think epoch milliseconds but with nanosecond precision).

```java
Instant now = Instant.now();
Instant fromZoned = zonedDateTime.toInstant();   // strips time zone, shows as GMT/UTC
```

> **CRITICAL**: Cannot convert `LocalDateTime` directly to `Instant` — it has no time zone info, so the JVM can't determine the absolute point in time.

---

## DST — Daylight Saving Time

Creating a time that doesn't exist (e.g., 2:30 AM when clocks jump to 3 AM) → Java **rolls forward** to the valid time.

2026 transitions (US):
- **March 8** — spring forward (clocks jump from 2:00 → 3:00, 2:xx doesn't exist)
- **November 1** — fall back (clocks go 1:59 → 1:00, 1:xx happens twice)

`ZonedDateTime.getOffset()` → returns a `ZoneOffset` (e.g., `-05:00`)

---

## Review Mistakes — Key Reminders

**Q1** — Answer is `f`. You can't just do `String x = 1;` — assigning an `int` to a `String` without `String.valueOf()` or `"" + 1` doesn't compile.

**Q2** — Answered `b, c, e`. Missed `f`, wrongly included `b`. Multi-answer: go through every option independently. Don't assume b and c are a pair just because they appeared near each other.

**Q4** — Missed `c`. Core `intern()` mechanic:
```java
String a = new String("hi").intern();   // forces pool reference
String b = "hi";                         // already in pool
System.out.println(a == b);             // TRUE — both point to same pool object
```
`intern()` returns the pooled version if it exists, or adds the string to the pool and returns the new pooled reference. Two interned equal strings will `==` each other.

**Q8** — Wrongly included `e`. The confusion: `length` is a **property** for arrays (`arr.length`), but a **method** for String (`str.length()`). `arr.length()` with parentheses → **DOES NOT COMPILE** for arrays.
```java
int[] arr = {1, 2, 3};
arr.length    // correct — no parens
arr.length()  // DOES NOT COMPILE
"hello".length()  // correct — parens required for String
```

**Q9** — Answered `a, g`. No `g`, missed `c` and `f`. The key thing:
```java
int[] a = {1, 2, 3};
int[] b = {1, 2, 3};
a.equals(b);           // false — arrays don't override equals(), uses Object reference equality
Arrays.equals(a, b);   // true — this one actually compares elements
```
Arrays are objects, so you *can* call `.equals()` — it just doesn't do what you want.

**Q12** — Missed `e`. Slow down on multi-answer questions and check every option.

**Q14** — Answered `c, f`. Wrong: no `c`, missed `b`. The date conversion methods:
```java
LocalDate date = LocalDate.of(2026, 4, 20);
date.atTime(LocalTime.of(9, 0))    // → LocalDateTime  ✓ (atTime, not withTime!)
date.atStartOfDay()                // → LocalDateTime at 00:00  ✓
```
It's `atTime()`, NOT `withTime()`. `with*` methods are for setting fields (e.g., `withDayOfMonth(15)`), not for conversion.

**Q16** — Answered `a, f, h`. Missed `g`. Same index in `substring()`:
```java
"animal".substring(3, 3)   // "" — empty string, NOT an error
"animal".substring(3, 2)   // StringIndexOutOfBoundsException — backward IS an error
```
Same start/end → empty string. Start > end → exception. Don't mix these up.

**Q17** — Wrongly included `e`. Read each option in isolation — just because `c` and `f` are right doesn't mean `e` is.

**Q20** — Missed `a`. `StringBuilder.reverse()` modifies the **original object** (it's mutable):
```java
StringBuilder sb = new StringBuilder("cat");
sb.reverse();
System.out.println(sb);   // "tac" — the SAME object is now reversed
```
String has no `reverse()`. For String you'd need to wrap it in a StringBuilder.

---

**Confusion Topics from Test — Resolved:**

**`intern()`** — returns the pooled reference; forced `==` equality between strings with same content. Only matters for exam questions.

**`Math.round()` return types:**
```java
Math.round(3.7f)    // returns int  — float input → int output
Math.round(3.7)     // returns long — double input → long output
Math.random()       // returns double in [0.0, 1.0)
```

**`StringBuilder.replace()` vs `String.replace()`:**
```java
// String:
str.replace('a', 'b')                      // char to char
str.replace("old", "new")                  // CharSequence to CharSequence

// StringBuilder:
sb.replace(int start, int end, String str) // totally different — it's delete(start,end) + insert
```

**Arrays and mutability** — arrays ARE mutable in content (you can change `arr[0] = 5`), but their **size** is fixed after creation. You can't add or remove elements from an array.

**`StringBuilder` constructors:**
```java
new StringBuilder()          // empty, capacity 16
new StringBuilder("hello")   // initialized with "hello"
new StringBuilder(32)        // empty, capacity 32
```

**`StringBuilder.substring()`** — yes, StringBuilder has `substring()`, but it returns a **new String**, NOT a modified StringBuilder:
```java
StringBuilder sb = new StringBuilder("hello");
String s = sb.substring(1, 3);   // "el" — sb is still "hello"
```

**`Arrays.mismatch()`** — equal arrays return `-1` (counterintuitive but that's what it is):
```java
Arrays.mismatch(new int[]{1,2,3}, new int[]{1,2,3})   // -1 (equal)
Arrays.mismatch(new int[]{1,2,3}, new int[]{1,9,3})   // 1 (first diff at index 1)
```

**`getOffset()`** — returns a `ZoneOffset` object (e.g., `ZoneOffset` of `-05:00`), not a String or int.
