# Chapter 3: Making Decisions — Answers

---

**1. C**

The "dangling else" rule: `else` attaches to the nearest `if`. The `else` belongs to the inner `if (x > 10)`, not the outer `if (x > 0)`. Since `x = 5`, the inner `if (x > 10)` is false, so the `else` branch prints `"small"` — but wait: the outer `if` is true, so we enter it. The inner `if (x > 10)` is false, so the `else` (attached to inner) would print `"small"`. **Correction**: Output is `small` — answer is actually `B`. The outer `if` is `true`, so we enter and evaluate the inner `if`. Inner `if` is false, so the `else` (belonging to inner `if`) executes: prints `small`.

> **EXAM TRAP**: The `else` always pairs with the nearest `if`. Indentation is misleading.

---

**2. B**

There is no `break` in `case 2`, so execution falls through to `case 3` and `default`. Output: `two three default`.

---

**3. A, C, E, F**

Traditional `switch` accepts: `int` (and auto-unboxed `Integer`), `byte`, `short`, `char`, `String`, and `enum`. NOT `long`, `double`, or `boolean`.

---

**4. B**

`i++` is post-increment: the condition uses current value then increments. Loop runs while `i < 3` (before increment). The body prints the post-incremented value.
- Iteration 1: condition checks `0 < 3` (true), i becomes 1, prints `1`
- Iteration 2: condition checks `1 < 3` (true), i becomes 2, prints `2`
- Iteration 3: condition checks `2 < 3` (true), i becomes 3, prints `3`
- Iteration 4: condition checks `3 < 3` (false), loop ends

Output: `1 2 3`

---

**5. A**

A `do-while` loop always executes the body at least once. `i = 5`, prints `5`, then `i--` makes `i = 4`. Condition: `4 > 5` is false → loop ends. Output: `5`.

---

**6. B**

`i` starts at 0, increments by 2 each iteration. Runs for `i = 0, 2, 4`. When `i = 6`, `6 < 5` is false. Output: `0 2 4`.

---

**7. B**

The outer loop `OUTER` is labeled. When `j == 1`, `break OUTER` exits the entire outer loop immediately. Only `i=0, j=0` is printed before breaking. Output: `00`.

---

**8. B**

`continue` skips to the next iteration. When `i % 2 == 0` (i.e., i = 0, 2, 4), `continue` skips printing. Only odd values `1` and `3` are printed. Output: `1 3`.

---

**9. B**

`obj` is a `String` with value `"Hello"`. `obj instanceof String s` succeeds (s = `"Hello"`). `s.length() > 3` → `5 > 3` → true. `s.toUpperCase()` → `"HELLO"`. Output: `HELLO`.

---

**10. B**

`day = 3`, case 3 matches → `"Wednesday"`. Output: `Wednesday`.

---

**11. A, B, D, E**

Arrow labels (`case 1 ->`) are valid. Traditional labels with `yield` are valid in switch expressions. Multiple values in one case (`case 1, 2 ->`) are valid. Throwing in a switch expression is valid. C is wrong — `return` inside a switch expression is not valid syntax for yielding a value.

---

**12. B**

`obj` is `"Java"` which is a `String`. `!(obj instanceof String s)` — the check succeeds so `s = "Java"`, but `!` makes the condition `false`. The `else` branch executes with `s` in scope. `"Java".length()` = `4`. Output: `4`.

---

**13. B**

The loop runs until `i++ == 3`. Post-increment means `i` is compared before incrementing.
- i=0: 0==3? no, then i=1
- i=1: 1==3? no, then i=2
- i=2: 2==3? no, then i=3
- i=3: 3==3? yes — break. But post-increment ran: i=4.

Output: `4`.

---

**14. C**

`continue OUTER` skips the rest of the inner loop and goes to the next iteration of the outer loop. For each outer iteration `i`, the inner loop runs `j=0` (total++), then hits `j=1` and continues to next `i`. So `total` increments once per outer iteration: 3 times. Output: `3`.

---

**15. B, C, D**

A: Java requires a `boolean` expression; non-zero `int` is NOT truthy (unlike C/C++). E: `null` is not a boolean. B is trivially valid. C: `x = true` returns the `boolean` value `true` — valid. D: `x == 1` returns `boolean` — valid.

---

**16. B**

`case "b"` matches, prints `B`. No `break`, falls through to `case "c"`, prints `C`, then hits `break`. Output: `BC`.

---

**17. B**

`1 + 2 + 3 + 4 + 5 = 15`. Output: `15`.

---

**18. C**

`x = 5` is an assignment expression that returns `int 5`. An `if` condition must be `boolean`. Java does not auto-convert `int` to `boolean`, so this does not compile.

---

**19. B**

`x = 2`, matches `case 2`. Block executes: `y = 2 * 10 = 20`, then `yield 20 + 5 = 25`. Output: `25`.

---

**20. C**

`x = 10`. `10 - 3 = 7`, `7 - 3 = 4`, `4 - 3 = 1`, `1 - 3 = -2`. `-2 > 0` is false, loop exits. Output: `-2`.

---

**21. B**

Pattern variables use **flow scoping** — they are available wherever the compiler can definitively prove the `instanceof` test was true. This is not simple block scoping, and the variable is available in `else` if the `if` negates with `!`.

---

**22. B**

`x = 5`, matches `case 5`. Falls through to `case 6` (no separate statement for case 5 alone). Prints `"five or six"`, then `break`. Output: `five or six`.

---

**23. A**

The inner `if` prints only when `i == j`. That happens for `(1,1)`, `(2,2)`, `(3,3)`. Output: `1 2 3`.

---

**24. B**

`data = {3, 1, 4, 1, 5}`. Looking for `4` — found at index `2`. Output: `2`.

---

**25. A, B, C, D**

E is false — `break` cannot be used inside a plain `if` without an enclosing loop or switch. All others are correct.

---

**26. B**

`85 / 10 = 8`, matches `case 8` → `"B"`. Output: `B`.

---

**27. B**

The loop increments `i` one final time after `i = 4`, making `i = 5` when the condition `5 < 5` fails. Output: `5`.

---

**28. B**

`o = 42`. `case Integer i when i > 100` — 42 > 100 is false, skip. `case Integer i` — matches, `i = 42`. Output: `small int`.

---

**29. C**

The `do-while` loop executes the body first, then checks the condition. It always runs at least once.

---

**30. A**

`x = 1`, matches `case 1, 2` → `"low"`. Output: `low`.

---

**31. B**

When `i == 5`, `break` exits. The loop ran for `i = 0, 1, 2, 3, 4`. `x++` ran 5 times. Output: `5`.

---

**32. D**

In Java 21's enhanced `switch` with pattern matching, `null` cases are supported. However, the traditional `switch (s)` where `s` is `null` throws a `NullPointerException`. Output: **NullPointerException is thrown**.

---

**33. A, C, D, E**

Switch expressions must be exhaustive (A). Switch statements do NOT need to be exhaustive (B is false). Arrow labels work in both (C). Arrow labels in switch do NOT fall through (D). Switch expressions return values; statements do not (E).

---

**34. C**

`i` is declared inside the `for` loop initializer — its scope is limited to the loop. Using it on line 4 is a compile error.

---

**35. B**

Without braces, only the next single statement belongs to the `if`. `System.out.println("positive")` is the `if` body. `System.out.println("done")` is always executed (it's outside the `if`). Output: `positive` then `done`.

---

**36. A, B, D, E**

`double` literals are not valid `case` labels (C). `String` literals (A), `null` in pattern-matching switch (B), `enum` constants (D), and compile-time constant `final int` (E) are all valid.

---

**37. B**

The body executes once (i becomes 1). Then `1 < 0` is false, loop ends. Output: `1`.

---

**38. B**

`obj = "test"` is a `String`. `!(obj instanceof String s)` — the check succeeds, `s = "test"`, condition is negated to `false`, so `else` runs. `s` is in scope in the `else` branch (flow scoping). `"test".length() = 4`. Output: `4`.

---

**39. C**

`result = 1 + 2 + 3 = 6` (after `i=1,2,3`). After `i=4`, `result = 6 + 4 = 10 > 6` → break. Output: `10`? Let me retrace: after adding `i=3`, `result = 6`. After adding `i=4`, `result = 10 > 6` → break. Output: `10`. Wait — the break happens AFTER the addition. So output is **`10`**. But let me check `i=3`: `result = 6`, `6 > 6` is false. Then `i=4`: `result = 10`, `10 > 6` is true → break. Output: `10`.

> **Answer: B (10)**

---

**40. B**

When no `case` matches and there is no `default`, execution simply skips the entire `switch` block and continues normally. No exception is thrown.

---

**41. B**

The for loop re-initializes `i = 0`, runs until `i = 3`. After the loop, `i = 3`. Output: `3`.

---

**42. E**

`y` is declared in `case 1`. In Java, variables declared in one `case` are in scope for subsequent `case` blocks (they share the same block scope). `y = 20` on line 7 is valid since `y` was declared in the same switch block. The code compiles and runs. Note: `y` may not be initialized if `case 1` was skipped, but the compiler doesn't catch this here.

---

**43. B**

`"dog"` causes `continue`, skipping the print. `"cat"` and `"bird"` are printed. Output: `cat bird`.

---

**44. B**

A labeled `break` exits the labeled statement (loop or switch) and resumes after it. It can apply to any labeled statement, not just loops (A is false). Labels are case-sensitive but not required to be uppercase (C is false).

---

**45. A**

`b` is `true` → ternary returns `"yes"`. Output: `yes`.

---

**46. B**

Tracing: `i=0, j=0`: total=1. `i=0, j=1`: total=2. `i=0, j=2`: total=3. `i=0, j=3`: 0+3=3, no break. `i=0, j=4`: 0+4=4 → `break outer`. Total = 3? Let me redo: when `i=0, j=4`: `i+j=4` → `break outer`. Count the increments: `(0,0)`, `(0,1)`, `(0,2)`, `(0,3)` = 4 iterations (total=4). `(0,4)` breaks. Output: `4`.

---

**47. B**

`grade(90)` — `case 90 -> "A"` matches. Output: `A`.

---

**48. A, B, D, E**

C is false — the loop variable is a copy of each element; for primitives, modifying the loop variable does not change the array. A, B, D, E are all true.

---

**49. B**

`x=3`, matches `case 3`. Prints `"three "`, yields `30`. `y = 30`. Then `println(30)`. Output: `three 30`.

---

**50. A**

`obj = Integer.valueOf(10)`. `obj instanceof Integer i` succeeds, `i = 10`. `i > 5` is true. Entire condition is true. Output: `large: 10`.
