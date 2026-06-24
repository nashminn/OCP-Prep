# Chapter 6: Class Design — Review Questions Evaluation

**Score: 10/26 fully correct | 6/26 partially correct | 10/26 incorrect**

---

## Legend
- ✅ Correct
- ⚠️ Partial (right answer present but extra wrong choice, or missing a choice)
- ❌ Incorrect

---

## Question-by-Question

### Q1 — ✅
**Your answer:** E &nbsp;&nbsp; **Correct:** E

---

### Q2 — ✅
**Your answer:** A, B, F &nbsp;&nbsp; **Correct:** A, B, F

---

### Q3 — ✅
**Your answer:** B, C &nbsp;&nbsp; **Correct:** B, C

---

### Q4 — ⚠️ (included wrong option E)
**Your answer:** E, F &nbsp;&nbsp; **Correct:** F

> F is correct — `Platypus` has no no-argument super constructor, so the first line of its constructor must be `super(int)`.  
> **E is wrong** — line 7 compiles fine. The `sneeze()` method in `Mammal` is `private`, so it is not inherited and not overridden in `Platypus`; the Platypus `sneeze()` is free to have any return type.

*Your note: confused about overridden method and class initialization — the key here is that `private` methods are **not inherited**, so there is no override contract to satisfy.*

---

### Q5 — ❌
**Your answer:** G &nbsp;&nbsp; **Correct:** E

> Instance variables with the same name as an inherited one are **hidden**, not overridden. Both variables coexist; which one is accessed depends on the **reference type** (not the object type). Because `main()` uses a `Speedster` reference, `Speedster.numSpots` must be set to 50 — only option E does that.

---

### Q6 — ⚠️ (included wrong option A)
**Your answer:** A, D, E &nbsp;&nbsp; **Correct:** D, E

> D and E are the two immutable classes: both are `final` with only `private final` fields.  
> **A (Moose) doesn't compile** — the `final` field `antlers` is never initialized (not at declaration, not in an instance initializer, not in a constructor).  
> Caribou/Reindeer are not immutable because they are not `final` (a subclass could add mutable state).

---

### Q7 — ❌
**Your answer:** F &nbsp;&nbsp; **Correct:** A

> `Arthropod` has two **overloaded** `printName()` methods. The `printName(int)` version is correctly **overridden** in `Spider` (protected > package access is valid). Due to polymorphism, the overridden method is called on lines 14 & 15 → prints "Spider" twice. Line 16 passes a `long` (5L), which does **not** match the overridden `int` version, so it falls back to `Arthropod.printName(long)` → prints "Arthropod". Output: **Spider Spider Arthropod** → option A.

---

### Q8 — ❌
**Your answer:** E &nbsp;&nbsp; **Correct:** D

> Abstract-class constructors work the same as regular class constructors. The call chain:
> - Line 9 → constructor on line 6 → compiler inserts `super()` → line 3 prints `Wow-`
> - Returns to line 6, prints `Oh-`
> - `fly()` on line 10 is `private` in `Pelican`; `var` resolves to `Pelican`, so it calls `Pelican.fly()` → prints `Pelican`
>
> Final output: **Wow-Oh-Pelican** → option D.

---

### Q9 — ✅
**Your answer:** B, E &nbsp;&nbsp; **Correct:** B, E

---

### Q10 — ✅
**Your answer:** A, C &nbsp;&nbsp; **Correct:** A, C

---

### Q11 — ✅
**Your answer:** C &nbsp;&nbsp; **Correct:** C

---

### Q12 — ✅ *(you were right despite the confusion!)*
**Your answer:** C &nbsp;&nbsp; **Correct:** C

> Line 8: `Rodent` has no no-arg constructor, so `Beaver` needs an explicit `super(...)` call.  
> Line 9 has **two** errors: `Number` is a *supertype* of `Integer` (not covariant), and the parent method is `static` but this one isn't (invalid override).  
> Total: 3 compilation errors on 2 lines → **C**.

---

### Q13 — ⚠️ (missing option G)
**Your answer:** A &nbsp;&nbsp; **Correct:** A, G

> The compiler inserts a default no-arg constructor only when the class compiles **and** defines **no** constructors.  
> **A** — valid (no constructors defined).  
> **G** — valid too: `bird()` has no return type and doesn't match the class name (`Bird`), so the compiler treats it as a **method** with a missing return type → compilation error. Therefore no constructors are declared and the compiler inserts one.  
> Options B and C have the same issue (constructor name ≠ class name) but don't compile at all. D, E, F all define at least one constructor, so no default is inserted.

---

### Q14 — ❌
**Your answer:** C &nbsp;&nbsp; **Correct:** B, E, F

> - A: wrong — a class can only directly **extend** one class.  
> - **B: correct** — a class can implement any number of interfaces.  
> - C: wrong — primitive variables do *not* inherit `java.lang.Object`.  
> - D: wrong — a class that extends another is a *sub*class, not a superclass.  
> - **E: correct** — a class that implements an interface is a subtype of that interface.  
> - **F: correct** — accurately describes multiple inheritance of state, which Java does not permit for classes.

---

### Q15 — ❌
**Your answer:** G &nbsp;&nbsp; **Correct:** C

> The `isBlind()` method in `Nocturnal` is **not** marked `abstract` but has **no body** — that's the only compile error. Everything else compiles fine → **C** (one compile error).

---

### Q16 — ❌
**Your answer:** G &nbsp;&nbsp; **Correct:** D

> Initialization order:
> 1. Static initializers in order: `Arachnid` → sb = `"u"`; then `Scorpion` → sb = `"uq"`. Lines 13 & 14 print `"uq uq "`.
> 2. Instance of `Arachnid` (line 15): two instance initializers append `cr` → sb = `"uqcr"`.
> 3. Instance of `Scorpion` (line 16): superclass (`Arachnid`) instance initializers run first, appending `cr` → `"uqcrcr"`; then Scorpion's appends `m` → `"uqcrcrm"`.
>
> Final output: **uq uq uqcrcrm** → option D.

---

### Q17 — ⚠️ (missing option F)
**Your answer:** C &nbsp;&nbsp; **Correct:** C, F

> **C: correct** — `this.variableName` can be used from any instance method, constructor, or instance initializer (but not static context).  
> **F: correct** — the `main()` method is in the same class, so it can call `private` methods of that class.  
> A & B: wrong — `this()` can only be the **first line** of a constructor.  
> D: wrong — `this.variableName` is not allowed in static methods/initializers.  
> E: wrong — if a default constructor was created by the compiler, no user-defined constructors exist; `this()` can only be called from a constructor, so this scenario is impossible.

---

### Q18 — ❌
**Your answer:** B, G &nbsp;&nbsp; **Correct:** D, F

> - `eat()` is `private` in `Mammal` → not inherited → neither overridden nor overloaded in `Primate`/`Monkey`. A & B wrong.  
> - `drink()` is correctly **hidden** in `Monkey`: same signature, both `static` → **D correct**. The new unchecked exception is allowed.  
> - `dance()` is **overloaded** in `Monkey` (different signature) → **F correct**.  
> - Line 12 (`void` → `int`) is an invalid override → does not compile; G & H wrong.

---

### Q19 — ❌
**Your answer:** E &nbsp;&nbsp; **Correct:** F

> `Reptile` declares a constructor but it's **not** no-arg. Therefore `Lizard`'s constructor must explicitly call `super(int)`. Line 9 doesn't do this → compile error → **F**.  
> (If corrected, the output would be `BALizard` — static initializer first, then instance initializer, then overridden method call.)

---

### Q20 — ✅
**Your answer:** E &nbsp;&nbsp; **Correct:** E

*You noted confusion about covariant return types — but got it right. The key: `Macaw` → `Parrot` → `Bird` is a valid covariance chain.*

---

### Q21 — ⚠️ (missing option B)
**Your answer:** G &nbsp;&nbsp; **Correct:** B, G

> **B: correct** — an immutable class must be `final` *or* have only private constructors to prevent mutable subclasses.  
> **G: correct** — callers may access data in mutable elements of an immutable object, as long as they cannot *modify* those elements.  
> A: wrong — immutable objects have no setters.  
> C, E: wrong — immutable classes *can* have both instance and static variables.  
> D: wrong — `static` is not a property of immutable objects.  
> F: wrong — private constructors are *allowed* but not required.

---

### Q22 — ❌
**Your answer:** B &nbsp;&nbsp; **Correct:** D

> `Child` **overrides** `setName()` (instance method) but **hides** the static `name` variable. Variable hiding means *two* distinct `name` variables exist:
> - Line 10 (`Child` ref): sets `Child.name = "Elysia"`
> - Line 11 (`Person` ref): sets `Person.name = "Sophia"`
> - Lines 12 & 13 both call the **overridden** `setName()` instance method (polymorphism): sets `Child.name = "Webby"`, then `"Olivia"`
>
> Final: `Child.name = "Olivia"`, `Person.name = "Sophia"` → **D**.

*Your note: confused about variable hiding — static variables are hidden (not overridden), and the variable used depends on the **reference type**, not the object type.*

---

### Q23 — ✅
**Your answer:** B &nbsp;&nbsp; **Correct:** B

> Constructor chain (called child-upward, executed top-down):
> `Fennec()` → `Fox(int)` → `Fox()` → compiler inserts `Canine()` → prints `q` → unwinds: `Fox()` prints `p` → `Fox(int)` prints `z` → `Fennec()` prints `j` → final: **qpzj** → B.

*You noted confusion about initialization order — you still got this one right! The rule: trace the chain upward to find the pathway, then execute top-down.*

---

### Q24 — ✅
**Your answer:** C &nbsp;&nbsp; **Correct:** C

> Order: static init of `Antelope` (prints `1`) → static init of `Gazelle` (prints `8`) → instance of `Antelope`: instance initializers print `24` → instance of `Gazelle`: superclass instance initializers first (`24`... wait, re-reading the PDF):
>
> Output: `1` `8` `24` `93` → **182493** → C.

*You noted confusion about init order again — you still got it correct!*

---

### Q25 — ⚠️ (included wrong option E)
**Your answer:** B, C, E &nbsp;&nbsp; **Correct:** B, C

> **B: correct** — a concrete class must implement all inherited abstract methods.  
> **C: correct** — concrete classes can optionally be `final`.  
> **E: wrong** — a concrete subclass only needs to *override* the abstract method, not match the declaration exactly (e.g., covariant return types are allowed).  
> A: wrong — concrete classes are by definition not abstract.  
> D: wrong — concrete classes need not be immutable.

---

### Q26 — ❌
**Your answer:** B &nbsp;&nbsp; **Correct:** D

> The class structure is fine. The compile error is in `main()`: `Orca` is implicitly cast to `Whale` on line 7. The `whale` reference (type `Whale`) does **not** have access to `dive(int depth)`, which is defined only in `Orca`. Line 8 does not compile → **D**.

---

## Summary

| Result | Questions | Count |
|--------|-----------|-------|
| ✅ Fully correct | 1, 2, 3, 9, 10, 11, 12, 20, 23, 24 | 10 |
| ⚠️ Partially correct | 4, 6, 13, 17, 21, 25 | 6 |
| ❌ Incorrect | 5, 7, 8, 14, 15, 16, 18, 19, 22, 26 | 10 |

---

## Patterns to Review

### 1. Initialization Order (Q16, Q23, Q24)
You flagged this multiple times. The rule to drill:
1. **Static** variable declarations + static initializers (parent first, then child), in source order.
2. **Instance** variable declarations + instance initializers (parent first, then child), in source order.
3. **Constructor** body executes (after instance init).

For constructor chains: trace the path *upward* (child → parent) to find what runs, but execute *top-down*.

### 2. Variable Hiding vs. Overriding (Q5, Q22)
- Instance methods → **overridden** (polymorphism applies, reference type doesn't matter).
- Static methods → **hidden** (which one runs depends on **reference type**).
- Instance/static **variables** → **hidden** (which one is accessed depends on **reference type**).

### 3. Covariant Return Types & Overriding Rules (Q7, Q20, Q25)
- Overriding requires: same signature, access modifier ≥ parent's, return type must be *covariant* (same type or subtype), no broader checked exceptions.
- `private` methods are **never** inherited → cannot be overridden (Q7 key insight).

### 4. Immutable Classes (Q21)
Must be `final` **or** have only `private` constructors + all fields `private final` + no mutating methods.

### 5. Multi-select Precision (Q4, Q6, Q13, Q17, Q21, Q25)
Several partials came from either adding a wrong option or missing one. On the real exam, each wrong option in a multi-select costs you the question. Practice eliminating options systematically using the explanations.
