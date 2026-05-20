# Chapter 5: Methods — Answers

---

**1. A, C, D, E**

Access modifiers and optional specifiers can appear in any order, but ALL must come before the return type. B fails because `void` (return type) appears before `public`. F fails because `static` appears after the parameter list.

---

**2. B**

Java is pass-by-value for primitives. The method receives a COPY of `a`. Changing `x` inside the method does not affect `a`. Output: `5`.

---

**3. B**

The method receives a copy of the reference to the `StringBuilder` object. Both the caller's `sb` and the parameter `sb` point to the SAME object. `append` mutates the object, so the change is visible to the caller. Output: `Hello World`.

---

**4. E**

`protected` grants access to the same package AND to subclasses in any package. `public` grants access everywhere. Both C and D are correct.

---

**5. A**

`sum(1, 2, 3)` → `1+2+3 = 6`. `sum()` → no elements, `total = 0`. Varargs can accept zero arguments. Output: `6 0`.

---

**6. A, C, D**

Only one varargs parameter is allowed per method (rules out B and E). Varargs must be the last parameter (rules out B). A, C, and D are valid. D (`int[]... arrs`) is an array of arrays as varargs — valid.

---

**7. B**

`print(5)` — exact match to `int`. `print(5.0)` — exact match to `double`. `print(5L)` — `long` cannot match `int` exactly, so it widens to `double`. Output: `int: 5` then `double: 5.0` then `double: 5.0`.

---

**8. B**

A method signature consists of the **method name** and the **parameter types** (and their order). Return type, access modifiers, and `throws` are NOT part of the signature.

---

**9. C**

`count` is `static` — shared across all instances. `s1.increment()` twice → `count = 2`. `s2.count` is the same static field → `2`. Output: `2`.

---

**10. C**

Inside `print()`, the local `x = 20` shadows the instance field. `this.x` explicitly refers to the instance field `10`. Output: `20 10`.

---

**11. A, B, C**

D and E are wrong — changing only the return type or only the access modifier does NOT constitute overloading and causes a compile error (duplicate method).

---

**12. A**

Static blocks run once when the class is loaded. Instance initializers run each time an object is created. Execution: static block runs first (prints `"static "`), then `main` prints `"main "`, then two objects are created (each prints `"instance "`). Output: `static main instance instance`.

---

**13. B**

When choosing between widening (to `long`) and autoboxing (to `Integer`), Java prefers widening over autoboxing. `int` widens to `long`. Output: `long: 5`.

---

**14. A, E**

`private` members are accessible only within the same class — including `static` methods of that same class (E). Subclasses (B), same-package (C), and other packages (D) cannot access `private` members.

---

**15. B**

Java passes object references by value — the reference itself is copied. Reassigning `sb` inside the method changes the local copy of the reference, not the caller's reference. The caller's `s` still points to the original object. Output: `original`.

---

**16. A**

A varargs `int...` parameter is treated as `int[]`. Passing `new int[]{1,2,3}` passes a single array → `nums.length = 3`. Passing `1, 2, 3` also creates a single array → `nums.length = 3`. Output: `3 3`.

---

**17. C**

`test()` is `static`. Instance variable `x` belongs to an instance, not the class. A `static` method cannot access instance variables directly. Compile error on line 4.

---

**18. A, B**

The correct syntax is `import static package.Class.member;` or `import static package.Class.*;`. C is wrong (not static import). D has the order reversed. E is invalid syntax.

---

**19. B**

Instance field `int x` defaults to `0`. Output: `0`.

---

**20. B, C, E**

B: `abstract` methods cannot have a body. C: `final abstract` is contradictory — `final` prevents overriding, `abstract` requires it. E: `static abstract` is invalid — static methods cannot be abstract. A and D compile.

---

**21. C**

`test("hello")` — `String` is more specific than `Object` → calls `test(String s)` → `"String"`. `test(null)` — `null` is compatible with both `Object` and `String`, but `String` is more specific → `"String"`. Output: `String String`.

---

**22. C**

Instance initialization order:
1. `x` initialized to `5` (field initializer)
2. Instance initializer `{ x = 7; }` runs
3. Constructor `{ x = 10; }` runs

Final value: `10`. Output: `10`.

---

**23. B**

Overload resolution: `byte` can widen to `short`, `int`, `long`, etc. Both `int` and `long` versions exist. Java prefers the most specific — `int` is closer to `byte` than `long`. Output: `int`.

---

**24. B**

Static field initializers and static blocks run in top-to-bottom order when the class is loaded, before `main` runs. `initX()` prints `"init "` and returns `5`. Then `main` prints `"main"`. Output: `init main`.

---

**25. C, D**

A and B are false — `static` methods cannot access instance variables or call instance methods directly (without an object reference). C: static methods can be called without an instance. D: static methods can access other static members. E is false — static methods can be hidden in subclasses but not overridden (they are resolved at compile time via the declared type).

---

**26. B**

When both an exact-match method and a varargs method are available, Java prefers the exact match. `go(1, 2)` exactly matches `go(int x, int y)`. Output: `two ints`.

---

**27. A**

`private static int x` is accessible within the same class. `getX()` is a static method in the same class — it can access `x`. Output: `5`.

---

**28. A, B, D**

Java is pass-by-value. Primitive parameters are copied (A). Object references are also copied — the reference copy is passed (B). Reassigning the reference inside the method doesn't affect the caller (C is false; E is false). Changes to the OBJECT's state via the reference ARE visible to the caller (D).

---

**29. B**

`compute(5)`: `5 > 0` → returns `5 * 2 = 10`. `compute(-3)`: condition false → returns `-1`. Output: `10 -1`.

---

**30. A**

Overload resolution prefers the most specific applicable method without boxing/unboxing. `int` matches `int` exactly — no autoboxing needed. Output: `int`.

---

**31. C, D**

A: Same parameters, different names only → same signature → compile error. B: Same parameters, different return type only → compile error. C: Different parameter order with different types → valid overload. D: `int` vs `long` — different types → valid overload. E: `void go(int... x)` and `void go(int[] x)` — these have the same erasure and cause a compile error.

---

**32. C**

`final` parameters cannot be reassigned. Line 3 tries to assign to `x` which is `final`. Compile error.

---

**33. B**

Instance initialization order:
1. `a = 1` (field initializer)
2. Instance initializer `{ a = 2; }` runs
3. `b = a + 1 = 2 + 1 = 3`

Output: `2 3`.

---

**34. B**

A `return;` in a `void` method is valid — it simply exits the method immediately at that point.

---

**35. A**

`obj.x` accesses a `static` field. Accessing a static field via a null reference is allowed in Java (no NPE) — the reference type determines the field, not the object. Output: `10`.

> **EXAM TRAP**: This compiles and runs without NPE because `x` is `static`. The JVM uses the declared type of `obj`.

---

**36. A, B, E**

C is false — constructor body runs AFTER instance initializers. D is false — static blocks run only once when the class loads, not per instantiation. E is correct — fields get default values before any initializer or constructor code runs.

---

**37. A**

When no command-line arguments are passed, `args` is an empty array (not `null`). `args.length = 0`. Output: `0`.

---

**38. E**

`protected` in a different package can only be accessed through inheritance (via the subclass type), not through a direct reference to the parent type. This is the key exam trap — answer E is the most accurate description.

---

**39. B**

`modify()` sets the static field `x = 20`. After the call, `x = 20`. Output: `20`.

---

**40. A, C, E**

A: Same parameters, different return type → compile error (not a valid overload). C: Same parameters, different parameter names only → same signature → compile error. E: Same parameters, different `throws` clause → same signature → compile error. B, D: These are valid overloads.

---

**41. A**

`doubleIt(5) = 10`. `addTen(10) = 20`. Output: `20`.

---

**42. A**

`print((String[]) null)` passes `null` as the array → `args == null` → prints `"null"`. `print((String) null)` passes a single null element → `args` is a `String[]` of length 1 → `args.length = 1`. Output: `null 1`.

---

**43. A**

`protected static int value` is accessible within the same class. `main` is in the same class. Output: `42`.

---

**44. A, B, D**

A: `import static java.lang.Math.*;` allows `PI` without prefix. B: It also allows `sqrt()` without the `Math.` prefix. C is false — it does not import the class itself. D is true — name conflicts cause ambiguity. E: Static imports can also import nested static types.

---

**45. B**

Varargs (`int...`) is the last resort in overload resolution — widening and autoboxing are preferred first. But here the options are `Object` (autoboxing `int` → `Integer` → `Object`) and `int...` (varargs). Widening `int` to `Object` via autoboxing is preferred over varargs. Output: `Object`.

---

**46. A**

`print(5)`: `5 < 0` is false, falls through to `println(5)`. `print(-1)`: `-1 < 0` is true, returns immediately. `print(3)`: prints `3`. Output: `5` then `3`.

---

**47. A**

`x` has package-private (no modifier) access. Since `main` is in the same class (same package), it can access `x`. Output: `10`.

---

**48. A**

Java's overload resolution priority: **exact match → widening → autoboxing → varargs**. The compiler always tries exact match first, then widens primitives, then autoboxes, and uses varargs only as a last resort.

---

**49. C**

Multiple static blocks are allowed. They execute in the order they appear. `x = 1` (field), then `x = 2` (first static block), then `x = 3` (second static block). Output: `3`.

---

**50. C**

`method(1, 2)` — the compiler tries to match: `method(int, double)` requires widening the second arg, and `method(double, int)` requires widening the first. Neither is more specific. The call is ambiguous → compile error.
