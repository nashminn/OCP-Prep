# Chapter 2: Operators — Answers

---

**1. B**

Multiplication (`*`) has higher precedence than addition (`+`). So `3 * 4 = 12` first, then `2 + 12 = 14`.

---

**2. B**

`x++` uses `x` (5) then increments to 6. `++x` increments to 7 then uses 7. So `y = 5 + 7 = 12`. Final `x = 7`. Output: `7 12`.

---

**3. B**

`star = 2 + 2 * 3 = 8`. `star > 10` is false → `sun = 3` (as float: `3.0f`). `jupiter = (3.0f + 9) - 1.0f = 11.0`. `--moon` decrements moon to 8, `8 <= 8` is true → `mars = 2`. Output: `3.0, 11.0, 2`.

---

**4. B**

`x = 5`, then `+= 3` → `8`, then `*= 2` → `16`, then `-= 4` → `12`. Output: `12`.

---

**5. A**

`&&` has higher precedence than `||`. So: `(3 > 2) && (5 < 3)` = `true && false` = `false`. Then `false || (1 == 1)` = `false || true` = `true`.

---

**6. A**

Outer ternary: `x > 3` is true → evaluate `x > 4 ? "big" : "medium"`. `5 > 4` is true → `"big"`. Output: `big`.

---

**7. B, C, D, E**

A: `int x = 5L` — narrowing from `long` to `int`, requires cast. F: `byte x = 300` — 300 is out of byte range, requires cast. B, C, D, E are widening conversions that compile without a cast.

---

**8. B**

`6 = 110`, `3 = 011`, AND: `110 & 011 = 010` = decimal `2`. Output: `2`.

---

**9. B**

`>>` is signed right shift. `8 >> 1` = `8 / 2` = `4`. Output: `4`.

---

**10. B**

Compound assignment operators include an implicit cast. `b += 5` is equivalent to `b = (byte)(b + 5)`. `10 + 5 = 15`, which fits in a `byte`. Output: `15`.

---

**11. B**

Java caches `Integer` objects for values -128 to 127 in the integer cache. `a` and `b` both reference the cached `127` object → `a == b` is `true`. `c` and `d` are `128` which is outside the cache → two different objects → `c == d` is `false`. Output: `true false`.

---

**12. A, C, D, E, F**

B (`5 = 3`) is an assignment, not a comparison — does not compile. All others produce `boolean` results.

---

**13. B**

`10 % 3 = 1`. `-10 % 3 = -1` (the sign of the result matches the sign of the dividend in Java). Output: `1` then `-1`.

---

**14. A**

`a--` uses `a` (5) then decrements to 4. `--a` decrements `a` to 3 then uses 3. `b = 5 - 3 = 2`. Final `a = 3`. Output: `3 2`.

---

**15. B, D, E**

A: `(x = 5)` is an assignment returning an `int`, not a `boolean` — compile error. C: `boolean b = x` where `x` is `int` — compile error (Java does not auto-convert `int` to `boolean`). B, D, E all produce `boolean` values.

---

**16. B**

`x / y` is integer division: `10 / 3 = 3`. The `int` result `3` is then widened to `double` `3.0` for assignment. Output: `3.0`.

---

**17. B**

Right-hand side is evaluated first: `x++ + x`. `x` starts at 1. `x++` uses 1, then increments to 2. Then `x` is now 2. So RHS = `1 + 2 = 3`. Then `x += 3` means `x = 2 + 3 = 5`. Wait — let me re-check: `x += x++ + x`. The compound assignment loads `x` (1), evaluates `x++ + x` — `x++` gives 1 (x becomes 2), then `x` is 2. RHS = 3. Then assignment: `x = 1 + 3 = 4`. Output: `4`.

---

**18. C**

Postfix `++` and `--` have the highest precedence (level 2). The cast `(type)` is level 3 (right-to-left), `!` is also level 3. Among these choices, postfix `++` wins.

---

**19. A**

`||` short-circuits. `check("A")` returns `true`, so `check("B")` is never called. After the condition, `"C"` is printed. Output: `AC`.

---

**20. C**

`&&` short-circuits. `check("A")` returns `false`, so `check("B")` is never called. The `if` body is skipped. Output: `A`.

---

**21. B**

Casting `double` to `int` truncates toward zero (no rounding). `3.9` → `3`. Output: `3`.

---

**22. C**

The bitwise complement `~n` = `-(n+1)`. `~5 = -6`.

---

**23. A, B, D**

The condition must be `boolean` (A). The two expressions can have different types — the compiler finds a common type (B). D: nested ternary is right-associative. C is false — the ternary must be used as part of an expression, not a standalone statement. E is false — nesting is allowed.

---

**24. B**

When `byte` operands are used in arithmetic, they are promoted to `int` via binary numeric promotion. So `b + c` produces an `int`. `var result` infers `int`. Value is `30`. Output: `30` as `int`.

---

**25. A**

Inner ternary: `x < 6` is true → `"A"`. The outer ternary (`x > 4`) is also true so it returns the inner result `"A"`. Output: `A`.

---

**26. B**

`0b101` = binary 5, `07` = octal 7 = decimal 7. `5 + 7 = 12`. Output: `12`.

---

**27. D**

`1._5f` — an underscore cannot appear immediately after the decimal point. Underscores can appear only between digits. This does not compile.

---

**28. A**

`x = true`, `y = !true = false`. `true ^ false = true` (XOR: different values → true). Output: `true`.

---

**29. D**

`<<= 2` is a left shift by 2. `6 * 4 = 24`. Output: `24`.

---

**30. A, C, D**

For primitives `==` compares values (A). For objects `==` compares references (C). After unboxing, an `int` and `Integer` can be compared with `==` (D). B is false — `==` does NOT compare object content. E is false — `new String("hello")` creates a separate object; `==` with a literal is `false`.

---

**31. A**

Primitives are copied by value. `y = x` copies the value `10`. Changing `x` to `20` does not affect `y`. Output: `10`.

---

**32. A**

`++x` increments to 4, returns 4. `4 * 2 = 8`. `x--` uses 4 then decrements to 3. `y = 8 + 4 = 12`. Final `x = 3`. Output: `3 12`.

---

**33. A, B, C, E**

Compound assignment operators include an implicit narrowing cast. `i += 3.0`, `i *= 2L`, `i /= 2.5`, and `i %= 3` all compile because the result is implicitly cast back to `int`. D fails because `true` is a `boolean` and cannot be used in arithmetic.

---

**34. A**

`(int)(5.5 + 2.5)` = `(int)(8.0)` = `8`. `(int)5.5 + (int)2.5` = `5 + 2` = `7`. Output: `8` then `7`.

---

**35. B**

Binary numeric promotion: `int + long` → `long`. The result of `3 + 4L` is of type `long`.

---

**36. A**

`x > 5` is true → returns `"yes"`. The nested ternary is never evaluated. Output: `yes`.

---

**37. C**

`&` (single ampersand) is the non-short-circuit logical AND — both sides always evaluate. `&&` and `||` short-circuit. `^` (XOR) also evaluates both sides but is not short-circuit in the same sense.

---

**38. B**

`a++` uses 5 then increments to 6. `++b` increments to 11 then uses 11. `5 + 11 = 16`. Output: `16`.

---

**39. D**

A: `(long) 5` is `long`, cannot assign to `int` without cast. B: `127 + 1 = 128`, out of `byte` range. C: `32767 + 1 = 32768`, out of `short` range. D: `(char) -1` is an explicit cast — valid. E: `3.14` is a `double` literal, cannot assign to `float` without `f` suffix or cast.

---

**40. B**

`&` is not short-circuit — both sides evaluate. `x > 3` is true. `x++` uses 5 (evaluates to false since 5 > 5 is false) then increments x to 6. `b = true & false = false`. `x = 6`. Output: `6 false`.

---

**41. A, D**

A: Pattern matching with `instanceof` in Java 16+ — correct. D: Pattern variable in a `&&` compound condition — correct. C: `instanceof null` is not valid Java syntax.

---

**42. B**

`1 / 2` is integer division → `0`. The `int` result `0` is widened to `double` → `0.0`. Output: `0.0`.

---

**43. B**

`5 = 101`, `3 = 011`. OR: `101 | 011 = 111` = decimal `7`. Output: `7`.

---

**44. B**

`int + long` → binary numeric promotion → `long`. `var z` infers `long`.

---

**45. A**

`Integer.MAX_VALUE + 1` overflows to `Integer.MIN_VALUE`. The comparison `Integer.MIN_VALUE == Integer.MIN_VALUE` is `true`. Output: `true`.

---

**46. A**

`5 / 2 = 2` (integer division), `5 % 2 = 1`. Output: `2 1`.

---

**47. B, D**

B: `byte` (8-bit) to `int` (32-bit) — widening, always lossless. D: `int` (32-bit) to `long` (64-bit) — widening, always lossless. A and C are narrowing (may lose data). E: `int` to `float` — widening but `float` has fewer significant digits than `int` for large values, so precision may be lost.

---

**48. B**

`x = x++ + x`. `x++` evaluates the original value `10` (x becomes 11 after post-increment). Then `x` is now `11`. RHS = `10 + 11 = 21`. `x = 21`. Output: `21`.

---

**49. A**

`|` is non-short-circuit OR. `true | false = true`. `||` is short-circuit OR — since `a` is `true`, result is `true` without evaluating `b`. Both print `true`.

---

**50. B**

`x > 4 && x < 10` → `true && true` → `true`. `x * 2 = 10`. Output: `10`.
