# Chapter 5: Methods

---

## Method Signature

- signature = name + parameter list
- does NOT include return type or access modifiers — those are for *where* the method can be referenced, not its identity — 7 may 10:53 now it makes sense
- parameter names don't matter, types and order do

---

## Method Declaration Structure

```
[access modifier] [optional specifiers] returnType methodName([params]) [throws ExceptionList] { body }
```

> **CRITICAL**: Access modifiers and optional specifiers can appear in any order, but **ALL must come before the return type**.

- Method body is required unless the method is `abstract`
- No limit on the number of exceptions you feel like throwing — comma-separated after `throws`

---

## Access Modifiers

| Modifier | Accessible From |
|---|---|
| `private` | same class only |
| *(no keyword)* | same package — aka package-private or default access |
| `protected` | same package OR subclasses (even from other packages) |
| `public` | everywhere |

> **NOTE**: `protected` is a superset of package access — it grants both. No keyword = package access. That silence is meaningful.

---

## Optional Specifiers

1. `static`
2. `abstract`
3. `final`
4. `default`
5. `synchronized`
6. `native`
7. `strictfp`

> `final` and `abstract` don't go together — should be obvious. Can't be "can't override" AND "must override" at the same time.

---

## Final Variables

- Assignment is NOT mandatory at declaration — just can't change once assigned
- Must be assigned exactly once before use (blank final)
- Making local variables `final` = good practice

```java
final int x;     // declared, no value yet — fine
x = 5;           // first assignment — fine
x = 6;           // DOES NOT COMPILE — already assigned
```

`static final` variables → must be initialized either:
1. At the declaration line: `static final int X = 5;`
2. In a **static initializer block**

```java
static final int X;
static {
    X = 10;   // valid — static initializer
}
// X in constructor or instance initializer → DOES NOT COMPILE
```

---

## Varargs Parameters

1. At most **one** varargs parameter per method
2. Varargs must be the **last** parameter if present
3. Can pass individual args, an array, or nothing

```java
void foo(int... nums) {}
foo(1, 2, 3);               // fine
foo(new int[]{1, 2, 3});    // fine — array passed directly
foo();                      // fine — nums.length == 0
```

> **EXAM TRAP**: For array-typed varargs, use `new boolean[] {true, true}` as the argument, NOT just `{true, true}`. The shorthand `{}` only works for direct array initialization, not as a method call argument.

---

## Static Variables and Methods

`static` → belongs to the class, not a specific instance.

```java
Snake s = new Snake();
System.out.println(s.hiss);   // hiss is static — works fine
s = null;
System.out.println(s.hiss);   // still works — lookup goes to class, not the null reference
```

Also applicable to `import static` statements.

### Static Cannot Access Instance Members

Static methods don't have a `this` — the class doesn't know which instance you mean. You can't reach instance fields or instance methods from a static context without an explicit object reference.

```java
class Foo {
    int x = 5;            // instance variable
    static void bar() {
        System.out.println(x);  // DOES NOT COMPILE — which x? whose x?
    }
}
```

> **EXAM TRAP**: This comes up constantly. `static` method accessing `this` or instance fields directly = compile error. You CAN still access instance stuff from static context if you have an explicit object reference (`Foo f = new Foo(); f.x`).

---

## Initializer Blocks

### Static Initializer

Runs **once** when the class is first loaded. For initializing static variables.

```java
static {
    someStaticVar = computeSomething();
}
```

### Instance Initializer

Runs **every time** a new instance is created, before the constructor body.

```java
{
    instanceVar = computeSomething();
}
```

Execution order: **static initializer → instance initializer → constructor body**

> **EXAM TRAP**: Plain `{ }` block = instance initializer. Only `static { }` is a static initializer. The exam will put a `{ }` block and call it a static one — it isn't. Don't mix up a method body (`void foo() {}`) with an instance initializer (just `{}`).

---

## Static Imports

- `import static` is for importing **static members** (methods, variables)
- `import` (no `static`) is for importing **classes**
- Order of keywords matters: `import static`, NOT `static import`

```java
import static java.util.Arrays.sort;   // imports the static sort method
sort(arr);                             // can now use without Arrays.sort()
```

---

## Pass-by-Value

Java is a **pass-by-value** language — always.

- **Primitives**: a copy is passed, changes inside method don't affect caller
- **Object references**: the reference is copied — can mutate object internals, but reassigning the reference inside the method doesn't affect the caller

```java
void modifyArr(int[] arr) {
    arr[0] = 99;               // affects caller — same underlying object
    arr = new int[]{1, 2};     // does NOT affect caller — just changed local copy of reference
}
```

---

## Autoboxing and Unboxing

- **Autoboxing**: primitive → wrapper class (e.g., `int` → `Integer`)
- **Unboxing**: wrapper class → primitive (e.g., `Integer` → `int`)

```java
Integer x = 5;   // autoboxed
int y = x;       // unboxed
```

### The Cast + Autobox Rule

Java will **cast OR autobox** automatically — but **NOT both at the same time**.

```java
void meth(Long x) {}
meth(8);        // DOES NOT COMPILE — 8 is int, would need: widen int→long AND autobox long→Long
meth(8L);       // fine — already long, then autoboxes to Long
meth((long) 8); // fine — explicit cast, then autoboxes
```

Autoboxing also works when initializing arrays:

```java
Integer[] arr = {1, 2, 3};   // ints autoboxed to Integers
```

---

## Method Overloading

Same name, different signature (different parameter list). NOT the same as overriding.

Everything else can differ: parameters, access modifiers, specifiers, return types, exception list.

### Overload Resolution Order

```
exact match → wider primitive → autoboxing → varargs
```

Java picks the **most specific** version it can.

```java
void foo(long x) {}
void foo(Integer x) {}

foo(5);    // picks foo(long) — widening preferred over autoboxing
```

> **NOTE**: `String` and `StringBuilder` both implement `CharSequence` — this comes up in overloading. Forgot this one.

### Ambiguous Calls

```java
void moo(int x, int... y) {}
void moo(int... x) {}

moo(1, 2, 3);   // DOES NOT COMPILE — ambiguous, both could match
```

---

## Constructors

Constructors have access modifiers too. No modifier on a constructor = package-private access.

```java
class Foo {
    Foo() {}             // package-private — only same-package classes can call new Foo()
    public Foo(int x) {} // public
}
```

> **EXAM TRAP**: Easy to miss that a constructor with no modifier has package access, not public. If caller is in a different package, it won't compile.

---

## Review Mistakes — Key Reminders

**Q4** — An `int` can be assigned to a `double` variable (widening, happens automatically). But widening and autoboxing are separate — Java won't do both in one step. Double-check what promotion is actually happening.

**Q8** — Only D. Read each overload option on its own. Don't assume a pattern from the ones you already confirmed.

**Q9** — Missed the constructor with **package access**. No modifier on a constructor = package-private. Being in the same package is required to call it from outside.

**Q10** — High confusion = slow down. Re-read each option independently.

**Q13** — Only D. That was an **instance initializer** `{ }`, not a static one. `static { }` = static initializer. Plain `{ }` = instance initializer. Don't get these swapped.

**Q14** — Only E. `static final` variables must be set at **declaration** or in a **static initializer block**. Constructors and instance initializers run per-object — they can't set a class-level constant.

```java
static final int X = 5;         // fine — declaration
static final int Y;
static { Y = 10; }              // fine — static initializer
```

**Q17** — Only B. "A dumb one." Slow down.

**Q18** — Answer: B, D, E. Eliminate wrong options explicitly rather than pattern-matching from what looks right.

**Q19** — Answer: B, C, E (not F). **Cannot access instance stuff from a static context.** Again. Drilling it in:

```java
class Foo {
    int instanceVar = 1;
    static void staticMethod() {
        System.out.println(instanceVar);  // DOES NOT COMPILE
    }
}
```

**Q21** — Missed D. The answer explanation was confusing, but stay methodical — go through each option on its own.

---

foundational chapter oooh
