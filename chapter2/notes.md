# Chapter 2: Operators

---

## Operator Types

- **Unary**: one operand (`!x`, `~x`, `++x`, `-x`, `(int)x`)
- **Binary**: two operands (`x + y`, `x && y`, `x == y`)
- **Ternary**: three operands (`condition ? expr1 : expr2`)

---

## Operator Precedence

Higher in the table = evaluated first. Same-level operators go **left to right** unless marked otherwise.

| Priority | Operators | Direction |
|---|---|---|
| 1 | `.`, `()` method call | Left → Right |
| 2 | `++`, `--` (post-fix) | Left → Right |
| 3 | `++`, `--` (pre-fix), `+`/`-` (unary sign), `!`, `~`, `(type)` | **Right → Left** |
| 4 | `*`, `/`, `%` | Left → Right |
| 5 | `+`, `-` | Left → Right |
| 6 | `<<`, `>>`, `>>>` | Left → Right |
| 7 | `<`, `>`, `<=`, `>=`, `instanceof` | Left → Right |
| 8 | `==`, `!=` | Left → Right |
| 9 | `&` | Left → Right |
| 10 | `^` | Left → Right |
| 11 | &#124; | Left → Right |
| 12 | `&&` | Left → Right |
| 13 | &#124;&#124; | Left → Right |
| 14 | `? :` ternary | **Right → Left** |
| 15 | `=`, `+=`, `-=`, `*=`, `/=`, `%=`, etc. | **Right → Left** |

> **EXAM TRAP**: Post-fix `++`/`--` binds tighter than pre-fix. Both bind tighter than arithmetic. When in doubt, add parentheses and trace step by step.

> **NOTE**: Java does **not** allow `[]` in place of `()` for grouping — unlike some other languages. `[]` is strictly array access syntax.

---

## Unary Operators

| Operator | Applies to | Effect |
|---|---|---|
| `!` | `boolean` only | Logical inversion |
| `~` | integral types only | Bitwise complement (`~x == -(x+1)`) |
| `+` / `-` | numeric | Unary sign |
| `++` / `--` | numeric | Increment / decrement by 1 |
| `(type)` | any compatible type | Explicit cast |

> **CRITICAL**: In Java, unlike most other languages, **boolean operators cannot be applied to numeric types and vice versa**. `1` and `true` have absolutely nothing to do with each other.

```java
int x = !5;        // DOES NOT COMPILE — ! is for booleans only
boolean b = ~true; // DOES NOT COMPILE — ~ is for integrals only
```

### Pre-fix vs Post-fix

```java
int x = 3;
int y = x++;   // y = 3, x = 4  — old value returned, THEN x is incremented
int z = ++x;   // z = 5, x = 5  — x incremented FIRST, then returned

int a = 0;
System.out.println(a++);  // prints 0, a becomes 1
System.out.println(--a);  // a becomes 0, prints 0
```

> **Rule**: post-fix returns the original value; pre-fix returns the updated value.

### Compound Assignment + Increment Traps

Java evaluates operands **left to right**, and compound assignment **captures the left-hand side first**:

```java
int k = 0;
k += k++;    // left k captured as 0, k++ returns 0 (k becomes 1), 0+0=0, k = 0  → k is 0
k += ++k;    // left k captured as 0, ++k makes k=1 returns 1, 0+1=1              → k is 1
```

### Modulo with Negatives

The result of `%` takes the **sign of the left operand** (the dividend):
```java
System.out.println(-13 % 5);   // -3, NOT 2
System.out.println(13 % -5);   // 3, NOT -2
```

---

## Numeric Promotion Rules

When Java evaluates arithmetic expressions, types are automatically promoted before the operation:

1. **Smaller → Larger**: if operands differ in size, the smaller is promoted to match
2. **Integral → Floating-point**: if one operand is floating-point, the integral is promoted
3. **`byte`, `short`, `char` → `int`**: always promoted when used with **any binary arithmetic operator**, even when both operands are the same smaller type
4. **Result type = the post-promotion type**

```java
short x = 14, y = 3;
var z = x * y;           // z is int — both promoted to int before multiplication

byte b = 10;
var result = b + b;      // int — NOT byte. Rule 3 kicks in regardless
```

> **EXAM TRAP**: `byte + byte` → `int`. You cannot assign the result back to a `byte` without an explicit cast.

---

## Casting

### Widening (implicit) — safe, no cast needed
```java
int x = 5;
double d = x;   // int silently widened to double
```

### Narrowing (explicit) — you're forcing it, data may be lost
```java
double pi = 3.14159;
int truncated = (int) pi;   // truncated = 3 — decimal part is DROPPED, NOT rounded
```

> **CRITICAL**: Casting a floating-point to an integral **truncates** toward zero. No rounding.

```java
double d = -0.9;
int i = (int) d;   // i = 0, NOT -1 — "toward zero" means the decimal just falls off
```

### Overflow & Underflow

When an integral exceeds its type's range, it **wraps around** — no exception, no warning:
```java
byte max = 127;
max++;   // max is now -128 — wrapped around silently

long a = 100_000_000L;
long b = 100_000_000L;
System.out.println(a * b);   // 10000000000000000 — fits in long, no overflow here
                              // but int * int of the same values would overflow
```

### Compound Assignment Operators Auto-Cast

`+=`, `-=`, `*=`, `/=`, `%=` automatically apply the narrowing cast back to the left-hand type:
```java
long x = 10;
int y = 5;
y *= x;      // COMPILES — implicitly: y = (int)(y * x)
y = y * x;   // DOES NOT COMPILE — explicit cast required: y = (int)(y * x)
```

---

## Literals: Default Types

```java
float f = 1.1;          // DOES NOT COMPILE — 1.1 is double by default
float g = 1.1f;         // OK

long z = 1234567890123; // DOES NOT COMPILE — integer literal too large for int
long w = 1234567890123L; // OK
```

---

## Assignment as an Expression

An assignment returns the value that was assigned — allowing chaining and use in conditions:

```java
int x = 5;
int y = (x = 10);   // y = 10, x = 10

// the batshit crazy stuff has been valid all along apparently:
boolean healthy = false;
if (healthy = true) {            // COMPILES and ALWAYS prints — this is assignment, not comparison!
    System.out.println("Apparently this prints");
}
```

> **EXAM TRAP**: `if (boolVar = true)` compiles and always executes the body. For non-booleans it's a compile error. Classic gotcha.

---

## Relational Operators

`<`, `>`, `<=`, `>=` work on numeric types.

### instanceof

`a instanceof B` — checks if `a` is a `B` (or a subtype of `B`).

```java
if (obj instanceof String s) {   // pattern matching (Java 16+) — s is auto-cast
    System.out.println(s.length());
}
```

> **CRITICAL**: If it is **impossible** for the left-hand side to ever be the right-hand type (no inheritance relationship), the **compiler throws an error** — it doesn't even wait until runtime.

```java
Integer i = 5;
if (i instanceof String) { }   // DOES NOT COMPILE — Integer can never be a String
```

---

## Logical & Conditional (Short-Circuit) Operators

| Operator | Name | Behavior |
|---|---|---|
| `&` | Logical AND | **Always** evaluates both sides |
| &#124; | Logical OR | **Always** evaluates both sides |
| `^` | Logical XOR | True only when operands **differ** |
| `&&` | Conditional AND | Short-circuits: skips right if left is `false` |
| &#124;&#124; | Conditional OR | Short-circuits: skips right if left is `true` |

When applied to **numeric** types → these become **bitwise** operators operating on individual bits.

```java
String s = null;
if (s != null & s.length() > 0) { }    // NPE — & always evaluates both sides
if (s != null && s.length() > 0) { }   // safe — short-circuits when s is null
```

**XOR truth table**:

| A | B | A ^ B |
|---|---|---|
| true | true | false |
| true | false | true |
| false | true | true |
| false | false | false |

---

## Ternary Operator

```
booleanExpression ? expressionIfTrue : expressionIfFalse
```

- Only **one** of the two expressions is evaluated — the one matching the condition
- Both expressions must be **compatible** with the result type

```java
int x = 5;
String result = (x > 3) ? "big" : "small";   // "big"

int y = 1;
int z = (y > 0) ? y++ : y--;   // z = 1, y = 2 — only y++ runs
```

---

## Equality Operators

`==` and `!=`:
- **Primitives**: compare by **value**
- **References**: compare by **memory address** — whether they point to the **same object**, not equal content

```java
int a = 5, b = 5;
System.out.println(a == b);         // true — same value

String s1 = new String("hi");
String s2 = new String("hi");
System.out.println(s1 == s2);       // false — different objects
System.out.println(s1.equals(s2));  // true — same content
```

---

## Review Mistakes — Key Reminders

**Q3** — Answered `b, c, f` but missed `d`. On multi-answer questions, go through every option individually before locking in.

**Q8** — Answered `e`, correct is `a`. 

**Q10** — Answered `a, b, d`, correct is `e`.

**General — Precedence & Casting** (still fuzzy, keep this close):
- Post-fix `++`/`--` has **higher** precedence than pre-fix
- `byte`/`short`/`char` **always promote to `int`** with binary operators — even `byte + byte`
- Casting float/double to int **truncates** (drops the decimal, toward zero) — does **not** round
- Compound assignment (`+=`, etc.) **automatically casts** the result back to the left-hand type — plain assignment does not
- `%` result sign follows the **left operand** (the dividend)
