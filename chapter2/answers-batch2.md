# Chapter 2 — Batch 2 Answer Key

---

## Q1 — Pre/Post-fix in a Complex Expression
**Answer: B) x=8, y=2**

Trace left to right:
- `x` starts at `10`
- `x--` — post-fix: **returns 10** (the current value), then `x` becomes `9`
- `--x` — pre-fix: `x` decrements first (`9 → 8`), then **returns 8**
- `y = 10 - 8 = 2`
- Final state: `x=8`, `y=2`

**Rule reminder**: post-fix returns the old value *before* the change; pre-fix changes first, then returns. When they appear in the same expression, Java evaluates left to right — so `x--` runs fully (including the side-effect on `x`) before `--x` starts.

---

## Q2 — char Arithmetic and Promotion
**Answer: B, C, D**

**A** (`char r1 = c + 1`): `c` is `char` and `1` is `int`. Binary arithmetic promotes `char → int`; the result is `int`. Assigning `int` to `char` without an explicit cast is a **narrowing conversion** → **does not compile**.

**B** (`int r2 = c + 1`): result is `int`, target is `int` → **compiles**. Value: `65 + 1 = 66`.

**C** (`char r3 = (char)(c + 1)`): explicit narrowing cast supplied → **compiles**. Value: Unicode 66 = `'B'`.

**D** (`var r4 = c`): no arithmetic, just a direct assignment from a `char` variable. `var` mirrors the declared type of the right-hand side → `r4` is **`char`**. ✓

**E**: false — see D.

**Rule**: `char` follows numeric promotion Rule 3 (`byte`/`short`/`char` → `int`) the moment it participates in *any* binary arithmetic. But plain assignment with no operator does not trigger promotion.

---

## Q3 — String Concatenation with `+`
**Answer: B) sum: 12, 3 is the sum, val: 3**

`+` is left-associative (left → right). As soon as one operand in a `+` chain is a `String`, the other side is converted to `String` from that point on.

- Line 1: `"sum: " + a` → `"sum: 1"` (String), then `"sum: 1" + b` → `"sum: 12"`
- Line 2: `a + b` → `3` (both ints, pure int addition), then `3 + " is the sum"` → `"3 is the sum"`
- Line 3: parentheses force `a + b = 3` first, then `"val: " + 3` → `"val: 3"`

**Trap**: whether `+` does addition or concatenation depends on the *type of the left operand at that step*, not the whole expression.

---

## Q4 — instanceof Pattern Matching (Java 16+)
**Answer: A, B, D, E**

**A**: `obj` is `"Hello"` (length 5). The pattern variable `s` is cast automatically. Prints `5`. ✓

**B**: The pattern variable `s` is in scope on the right-hand side of `&&` because `&&` short-circuits — if the `instanceof` check fails, the right side never runs, so `s` is guaranteed to be valid when `s.startsWith("H")` evaluates. Compiles and prints `"Hello"`. ✓

**C**: `Integer` is final; `String` is final; they have no inheritance relationship. The compiler can prove this check is *impossible* at compile time → **compile-time error**, same rule as old-style `instanceof`. The check would not merely "return false" — it is rejected outright.

**D**: Line C does not compile. ✓

**E**: Old-style `instanceof` (no pattern variable) is still valid. `"test"` is a `String` → `check` is `true`. ✓

**Rule**: with pattern matching, the scope of the pattern variable extends to the right side of `&&` (because `&&` guarantees the cast succeeded before evaluating further). The "impossible cast → compile error" rule from Chapter 2 applies to pattern matching `instanceof` as well.

---

## Q5 — Literal Type Defaults
**Answer: B, C, E, F**

**A** (`float f1 = 1.5`): `1.5` is a `double` literal by default. Assigning `double` to `float` is a narrowing conversion — requires an explicit cast → **does not compile**.

**B** (`float f2 = 1.5f`): `f` suffix makes it a `float` literal → **compiles**.

**C** (`double d1 = 1.5f`): `float → double` is widening → **compiles**.

**D** (`long l1 = 9999999999`): no `L` suffix, so Java parses it as an `int` literal. The value `9_999_999_999` exceeds `Integer.MAX_VALUE` (2,147,483,647) → **does not compile**.

**E** (`long l2 = 9999999999L`): `L` suffix → long literal → **compiles**.

**F** (`int i1 = 1_000_000`): underscores in numeric literals are valid since Java 7 (they are stripped at compile time) → **compiles**.

**Rules**:
- Floating-point literals default to `double`; use `f`/`F` for `float`.
- Integer literals default to `int`; use `l`/`L` for `long`. If the value is too big for `int` and has no `L`, it is a compile error — not a silent overflow.

---

## Q6 — Nested Ternary
**Answer: A) C, medium**

Ternary is right-associative, so the chain parses as:
```
score >= 90 ? "A" : (score >= 80 ? "B" : (score >= 70 ? "C" : "F"))
```
- `75 >= 90` → false → evaluate right side
- `75 >= 80` → false → evaluate right side
- `75 >= 70` → true → **"C"**

```
x > 10 ? "big" : (x > 3 ? "medium" : "small")
```
- `5 > 10` → false → evaluate right side
- `5 > 3`  → true  → **"medium"**

Output: `C` then `medium`.

**Note**: nesting ternaries is valid Java (though often flagged as a style issue in real code). The OCP exam expects you to trace them correctly without getting confused by the chaining.

---

## Q7 — Bitwise Operators on Integers
**Answer: B) 8, 14, 6, -11**

When `&`, `|`, and `^` are applied to **integer types** they operate bit-by-bit on the binary representation:

```
a = 1 0 1 0  (10)
b = 1 1 0 0  (12)
    --------
&   1 0 0 0  =  8
|   1 1 1 0  = 14
^   0 1 1 0  =  6
```

`~a` (bitwise complement) inverts every bit. For any integral `x`: `~x == -(x + 1)`.  
`~10 = -11` ✓

**Key distinction**: `&`, `|`, `^` are *context-sensitive* in Java:
- Applied to **booleans** → non-short-circuit logical operators (always evaluate both sides).
- Applied to **integer types** → bitwise operators (work on individual bits).

The `~` operator is integral-only (recall from Q2: `~true` does not compile).

---

## Q8 — Shift Operators
**Answer: B) 16, 2, 2147483646**

- `x << 2`: left shift = multiply by 2² = `4 × 4 = 16`
- `x >> 1`: signed right shift = divide by 2 (with sign preservation) = `4 / 2 = 2`
- `-4 >>> 1`: unsigned right shift — always fills the vacated leftmost bit with **0**

  `-4` in 32-bit two's complement: `11111111 11111111 11111111 11111100`  
  After `>>> 1`:                    `01111111 11111111 11111111 11111110` = **2,147,483,646**

**Difference between `>>` and `>>>`**:
- `>>` (signed): preserves the sign bit (fills left with the existing sign bit).
- `>>>` (unsigned): always fills left with `0`, turning a negative number into a large positive.

`<<` and `>>` appear in the precedence table below `+`/`-`, so arithmetic runs before shifts unless you add parentheses.

---

*Batch 2 total: 8 questions*
