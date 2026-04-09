# Chapter 3: Making Decisions

---

## if / else Statements

```java
if (condition) statement;                   // single-statement body — no braces needed
if (condition) { /* block */ }              // block body
if (c1) { } else if (c2) { } else { }      // chained
```

- Condition **must be a `boolean` expression** — not an integer (unlike C/C++)
- Braces are optional for single statements but required for blocks
- `else` attaches to the **nearest** `if` — the "dangling else" trap

```java
if (x > 0)
    if (x > 5)
        System.out.println("big");
else                       // this else belongs to the INNER if (x > 5), NOT the outer one
    System.out.println("nope");
```

---

## Pattern Matching with instanceof

```java
// Old way — check then cast manually
if (number instanceof Integer) {
    Integer data = (Integer) number;  // redundant cast
}

// New way (Java 16+) — pattern variable auto-cast
if (number instanceof Integer data) {
    System.out.println(data.intValue());  // data is Integer, ready to use
}
```

- The **pattern variable** (`data`) is auto-cast; no manual cast needed
- Avoids `ClassCastException` by combining check + cast atomically
- Before Java 21: `subtype instanceof SuperType var` was **not allowed** — checking if an object is its own supertype is meaningless and the compiler rejects it

### Flow Scoping

> **EXAM CRITICAL**: Flow scoping is not block scoping. The pattern variable is in scope wherever the compiler can **definitively prove** the instanceof check was true.

```java
void printLength(Object obj) {
    if (obj instanceof String s) {
        System.out.println(s.length());   // in scope — inside the true branch
    }
    // s is NOT in scope here — outside the if block
}
```

**Negation trick** — the variable IS in scope in the `else` branch... no wait, it's in scope in the **remaining code when the condition is negated with early return**:

```java
// s is in scope AFTER the if when the if exits early
if (!(obj instanceof String s)) return;
System.out.println(s.length());   // IN SCOPE — compiler knows we only reach here if instanceof was true
```

**With logical AND (`&&`)** — right side of `&&` is in scope:
```java
if (obj instanceof String s && s.length() > 5) {   // s valid on right side of &&
    System.out.println(s);
}
```

**With logical OR (`||`)** — right side of `||` is NOT in scope (left may have been false):
```java
if (obj instanceof String s || s.length() > 5) {   // DOES NOT COMPILE — s might not be a String
}
```

**Scope outside both branches** — only when both branches guarantee it:
```java
if (obj instanceof String s) {
    // ...
} else {
    // s is NOT in scope here — instanceof was false
}
// s is NOT in scope here — compiler can't guarantee instanceof was true
```

**The tricky one** — variable CAN be in scope outside the if when the compiler is sure:
```java
boolean result = obj instanceof String s;
// s is NOT in scope here even though result holds the boolean — NOT guaranteed true
```

---

## switch Statements

Two flavors:
1. **Traditional (colon syntax)** — fall-through by default, needs explicit `break`
2. **Arrow syntax** — executes one branch only, no fall-through, no `break` needed

### Supported switch Variable Types

| Supported | NOT Supported |
|---|---|
| `int`, `Integer` | `boolean`, `Boolean` |
| `byte`, `Byte` | `long`, `Long` |
| `short`, `Short` | `float`, `Float` |
| `char`, `Character` | `double`, `Double` |
| `String` | |
| Enum types | |
| Any object (Java 21+, with pattern matching) | |

> **CRITICAL**: `boolean`, `long`, `float`, `double` are **not** supported as switch variable types.

### Case Value Rules

- Must be a **compile-time constant**: literals, enum constants, `final` variables initialized with a literal
- **No method calls** — even if the method returns a constant value
- Data type of case values must be **compatible** with the switch variable type

```java
final int x = 5;
int y = 10;
switch (val) {
    case x:        // OK — final variable
    case y:        // DOES NOT COMPILE — y is not final
    case x * 2:    // OK — compile-time constant expression
    case someMethod(): // DOES NOT COMPILE — method call not allowed
}
```

### Colon Syntax — Fall-Through

```java
switch (day) {
    case 1:
    case 2:
        System.out.println("Mon or Tue");  // both fall into here
        break;
    case 3:
        System.out.println("Wed");
        break;      // without break, falls into the next case
    default:
        System.out.println("Other");
}
```

- Multiple `case` labels can share the same body by stacking them (fall-through)
- A missing `break` causes execution to fall into the **next case's body**, not just the next label
- `default` can appear **anywhere** in the switch, but only runs if no case matches

### Arrow Syntax — No Fall-Through

```java
switch (day) {
    case 1 -> System.out.println("Mon");
    case 2 -> System.out.println("Tue");
    default -> System.out.println("Other");
}
```

- Arrow cases **never fall through** — only the matched branch runs
- Multiple values in one case: `case 1, 2 -> ...`

> **CRITICAL**: You **cannot mix** `:` and `->` syntax in the same switch. Compiler error.

### switch vs Expression

- **switch statement**: does NOT return a value; used for side effects
- **switch expression**: returns a value; can be assigned or used inline

```java
// switch expression
String label = switch (val) {
    case 1 -> "one";
    case 2 -> "two";
    default -> "other";
};    // <-- note the semicolon
```

---

## switch Expressions

### yield

`yield` = "return a value from this switch expression block". Required when a case body is a **block** (braces) rather than a single expression.

```java
int result = switch (x) {
    case 1 -> 10;                         // no yield needed — single expression
    case 2 -> {
        System.out.println("processing");
        yield 20;                          // yield required in block
    }
    default -> 0;
};
```

- `yield` is only valid **inside a switch expression** — not in switch statements
- `break` in a switch expression would exit the expression entirely without a value — use `yield`

### Exhaustiveness

A switch **expression** must be exhaustive — it must cover every possible value of the variable. A switch **statement** does NOT need to be exhaustive (though sometimes it effectively must be for correctness).

Three ways to make a switch exhaustive:
1. List **all** enum values (for an enum type)
2. Add a `default` case
3. With pattern matching — cover all possible types

```java
// Enum exhaustiveness without default — lists all values
enum Season { SPRING, SUMMER, FALL, WINTER }
String result = switch (season) {
    case SPRING -> "warm";
    case SUMMER -> "hot";
    case FALL   -> "cool";
    case WINTER -> "cold";
    // No default needed — all 4 values covered
};
```

> **EXAM TRAP**: If you add a new enum constant later, the switch expression breaks at compile time — one of the advantages of exhaustive switches.

---

## Pattern Matching in switch (Java 21)

Java 21 allows **type patterns** in switch cases:

```java
Object obj = /* something */;
String result = switch (obj) {
    case Integer i -> "int: " + i;
    case String s  -> "str: " + s;
    case null      -> "null value";      // explicit null case
    default        -> "other";
};
```

### Guarded Patterns (`when`)

Add a condition to a pattern case with `when`:

```java
switch (obj) {
    case Integer i when i > 0 -> "positive int";
    case Integer i when i < 0 -> "negative int";
    case Integer i             -> "zero";
    default                    -> "not an int";
}
```

- `when` is checked **after** the type match — so `i` is safely cast before the condition runs
- Cases are evaluated **top to bottom** — more specific `when` conditions must come first
- Without `when`, a bare `case Integer i` matches **any** Integer including the ones with conditions above — so ordering matters

### null in switch (Java 21)

- By default, passing `null` to a switch throws `NullPointerException`
- You can handle it explicitly with `case null ->`
- `case null, default ->` combines both into one branch

```java
switch (obj) {
    case null    -> System.out.println("null!");
    case String s -> System.out.println("string");
    default      -> System.out.println("other");
}
```

### Enums in switch

Both forms compile:
```java
case Season.WINTER -> ...   // fully qualified
case WINTER        -> ...   // short form (preferred inside switch on that enum)
```

---

## while Loop

```java
while (condition) {
    // body
}
```

- Condition checked **before** each iteration — body may run zero times
- Condition must be a `boolean` expression
- `var` can be used as the loop variable type

```java
int i = 0;
while (i < 5) {
    System.out.println(i++);
}
```

---

## do-while Loop

```java
do {
    // body
} while (condition);   // <-- semicolon required
```

- Body executes **at least once** — condition checked **after** each iteration
- The semicolon after `)` is **required** — common source of compile errors

```java
int count = 5;
do {
    System.out.println(count);
    count--;
} while (count > 0);
// Always prints 5 even if count started at 0
```

---

## for Loop

```java
for (initialization; condition; update) {
    // body
}
```

All three sections are **optional**:

```java
for (;;) { }    // infinite loop — all three sections empty
```

- **Initialization**: runs once before the loop starts; can declare multiple variables of the **same type**
- **Condition**: checked before each iteration — if false from the start, body never runs
- **Update**: runs after each iteration

```java
for (int i = 0, j = 10; i < j; i++, j--) { }   // two variables, same type
for (int i = 0, long j = 0; ...) { }             // DOES NOT COMPILE — different types
```

### var as Loop Variable

`var` can be used in `for` and `for-each` loops:

```java
for (var i = 0; i < 5; i++) { }           // var inferred as int
for (var item : list) { }                  // var inferred as the element type
```

> **CRITICAL EXAM TRAP — Q15**: When `var` is the loop controller type in a `for-each`, the inferred type is the **element type** of the array/collection being iterated — not `int`. If iterating `int[]`, `var` is `int`; if iterating `String[]`, `var` is `String`. Also: `var` is **not** allowed as the variable type in a multi-variable `for` initialization.

```java
var items = List.of("a", "b", "c");
for (var item : items) {           // item inferred as String
    System.out.println(item.toUpperCase());
}

int[] numbers = {1, 2, 3};
for (var n : numbers) {            // n inferred as int (primitive)
    System.out.println(n);
}
```

---

## for-each Loop (Enhanced for)

```java
for (Type element : array_or_iterable) { }
```

- Works with **arrays** and anything implementing `Iterable` (e.g., `List`, `Set`)
- **`Map` is NOT directly supported** — iterate `map.entrySet()`, `map.keySet()`, or `map.values()` instead
- Cannot modify the underlying array/collection via the loop variable (for arrays — it's a copy)
- `var` can be the element type

---

## Controlling Flow

### break

- **Unlabeled**: exits the **innermost** loop or switch
- **Labeled**: exits the loop with the matching label

```java
OUTER: for (int i = 0; i < 3; i++) {
    for (int j = 0; j < 3; j++) {
        if (j == 1) break OUTER;    // exits the outer loop entirely
    }
}
```

### continue

- **Unlabeled**: skips to the next iteration of the **innermost** loop
- **Labeled**: skips to the next iteration of the loop with the matching label
- **`continue` is NOT allowed in a switch statement** — only in loops

```java
OUTER: for (int i = 0; i < 3; i++) {
    for (int j = 0; j < 3; j++) {
        if (j == 1) continue OUTER;   // skips rest of inner loop AND current outer iteration
    }
}
```

### return

- Exits the **entire method** — breaks out of all loops in that method
- Can be used for early-exit flow scoping tricks with `instanceof`

### Labels

- Labels are placed **before** the loop they apply to, with a colon
- Label naming follows the same rules as variable names
- Labels are optional — only useful when you need `break`/`continue` to target an outer loop

```java
LABEL_NAME: while (condition) { }
```

---

## Flow Scoping — Deep Dive

> **EXAM TRAP**: This is a major confusion point. Flow scoping ≠ block scoping.

The pattern variable is only in scope where the compiler can guarantee the `instanceof` was true **along every code path** reaching that point.

```java
// Scenario 1 — works
if (obj instanceof String s) {
    System.out.println(s);    // in scope
}

// Scenario 2 — works (early return pattern)
if (!(obj instanceof String s)) return;
System.out.println(s);   // in scope — can only reach here if instanceof was true

// Scenario 3 — does NOT work
if (obj instanceof String s) {
    // ...
}
System.out.println(s);   // DOES NOT COMPILE — s not in scope after the if

// Scenario 4 — complex boolean, only && works
if (obj instanceof String s && s.isEmpty()) { }   // OK — s in scope on right of &&
if (obj instanceof String s || s.isEmpty()) { }   // DOES NOT COMPILE — || doesn't guarantee true

// Scenario 5 — when variable scope extends into else... it doesn't
if (obj instanceof String s) {
    System.out.println(s);   // in scope
} else {
    System.out.println(s);   // DOES NOT COMPILE — instanceof was false here
}
```

**Tricky subtype case (pre-Java 21):**
```java
String s = "hello";
if (s instanceof String str) { }   // pre-Java 21: DOES NOT COMPILE — always true, pointless
                                    // Java 21+: allowed
```

---

## Review Mistakes — Key Reminders

**Q2** — Missing answer `c`: likely an instanceof/flow scoping detail. Check every branch carefully.

**Q6** — Revisit the exact rule that was tested here.

**Q7** — "Very dumb one" — no `e`. Read each option independently; don't assume adjacent answers belong together.

**Q8** — Wrong answer (should be `g`): revisit what `g` stated and why it's correct.

**Q9** — Missed `b` and `e`. Pattern matching / instanceof with switch — check both positive and negative path scoping.

**Q10** — Wrong: answer is `e`. Flow scoping was likely the cause — trace code paths carefully.

**Q15** — **CRITICAL**: messed up `var` as loop controller. Remember: `var` in `for-each` infers the **element type**, not `int`. `for (var item : stringArray)` → `item` is `String`, not `int`.

**Q17** — Missed `a`. Multi-answer questions — go through every option one by one.

**Q18** — Wrong: answer is `b, e`. Likely a switch expression/statement exhaustiveness or yield rule.

**Q20** — Wrong: answer is `a, e` (not `c`). Re-check what option `c` said — it likely described something that almost-but-not-quite follows the rule.

**Q21** — Wrong: just `d`. switch with pattern matching — check guarded patterns and their ordering.

**Q22** — Wrong: just `e`. The `when` guard condition in pattern switch cases. Only one option was fully correct.

**Q24** — Wrong: answer is `b`. Labels / break / continue with nested loops.

**Q25** — Wrong: answer is `d`. Likely do-while or loop control.

**Q26** — Wrong: answer is `f`. switch exhaustiveness or missing case coverage.

**Q28** — Flow scoping explanation confused even though you got it right. Revisit the "why" using Scenario 2 above (early return pattern).

**Q29** — Stupid mistake. Answer is `c`. Slow down on this type.

**Q30** — Wrong: answer is `e`. Review the specific rule being tested.

**General — Recurring Confusion Points:**

- `var` in loops: infers **element type** in for-each, `int` in `for (var i = 0; ...)` — NOT always `int`
- instanceof + switch + if: the pattern variable is only in scope where the compiler guarantees it; use the early-return trick for post-if usage
- Guarded patterns (`when`): cases are matched **top to bottom** — put more specific `when` conditions first or they'll be shadowed
- `continue` is not allowed in `switch` — only `break` and `yield`
- switch **expressions** require exhaustiveness; switch **statements** do not (usually)
- Mixing `:` and `->` in one switch → compile error
- `null` in switch throws NPE unless you add `case null`
