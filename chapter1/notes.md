# Chapter 1: Building Blocks

---

## Class & File Structure

- One public top-level type per `.java` file; file name **must** match the public class name
- A file **can** contain multiple classes as long as at most one is `public`
- Top-level type = class, interface, enum, record, annotation (anything that can be defined independently)

### Required order inside a file (PIC mnemonic)

| Element | Required? | Position |
|---|---|---|
| `package` declaration | No | First non-comment line |
| `import` statements | No | After package |
| Type declaration (class/etc.) | Yes | After imports |
| Fields and methods | No | Any order inside the class |

```java
// DOES NOT COMPILE - import before package is wrong
import java.util.*;
package structure;  // package must come first
```

---

## main() Method

- Entry point: `public static void main(String[] args)`
- Must be `public` and `static`; `void` return type is required
- Parameter can be written as `String[] args`, `String args[]`, or `String... args`
- `final` is an optional modifier: `public final static void main(final String[] args) {}` — still valid
- Parameter **name** (`args`, `x`, `friends`) does NOT matter; the type does
- Arguments are passed space-separated after the class name and indexed from `args[0]`

---

## Packages & Imports

- Wildcard `*` imports all **classes directly** in the package — does NOT include subpackages, fields, or methods
- Only one wildcard per import statement; it must be at the end
- `java.lang` is auto-imported — `System`, `String`, `Object`, etc. never need importing
- Classes in the **same package** as the importing class do NOT need an import statement

```java
import java.nio.*;           // NO GOOD — doesn't reach java.nio.file.Files
import java.nio.*.* ;        // NO GOOD — only one wildcard allowed
import java.nio.file.Paths.* // NO GOOD — cannot import methods, only class names
```

### Naming Conflicts

When a class exists in two imported packages, specific import wins over wildcard:
```java
import java.util.Date;   // specific — wins
import java.sql.*;        // wildcard — Date from here is ignored
```
If both are explicit imports of the same short name → compiler error (ambiguous).

When both packages are needed: use the fully qualified class name inline:
```java
java.util.Date d1;
java.sql.Date d2;
```

### Command-line compilation

```
javac -d <dir>           # output .class files to target dir (package structure preserved)
javac -cp / -classpath / --class-path <path>   # classpath for compilation
java  -cp <path> packageb.ClassB               # classpath for running
```
Note: `-D` (capital) is wrong; java options are case-sensitive.

---

## Constructors

> **EXAM TRAP**: A constructor has the **same name as the class** and **NO return type** — not even `void`.

```java
public class Chick {
    public Chick() { }          // CONSTRUCTOR — no return type
    public void Chick() { }     // NOT a constructor — this is a regular method!
}
```
- A method named like the class but with a return type **compiles** but is never called as a constructor
- If no constructor is defined, the compiler provides a default no-arg constructor

### Order of initialization

1. Fields and instance initializer blocks run **in the order they appear**
2. Constructor runs **after** all fields and instance initializers have run

```java
public class Chick {
    private String name = "Fluffy";  // 1st
    { System.out.println("setting field"); }  // 2nd (instance initializer)
    public Chick() { name = "Tiny"; }  // 3rd
}
// Output when new Chick() is called: "setting field", then name = "Tiny"
```

---

## Primitive Types

8 built-in primitives — they store a **single value**, not an object:

| Type | Size | Signed? | Default | Notes |
|---|---|---|---|---|
| `boolean` | JVM-dependent | n/a | `false` | `true` or `false` only |
| `byte` | 8-bit | Yes | `0` | -128 to 127 |
| `short` | 16-bit | Yes | `0` | -32,768 to 32,767 |
| `int` | 32-bit | Yes | `0` | default for integer literals |
| `long` | 64-bit | Yes | `0L` | needs `L` or `l` suffix |
| `float` | 32-bit | Yes | `0.0f` | **needs `f` or `F` suffix** |
| `double` | 64-bit | Yes | `0.0` | default for decimal literals |
| `char` | 16-bit | **Unsigned** | `'\u0000'` | 0 to 65,535 |

Key facts:
- `boolean`'s bit size is **unspecified** — JVM-dependent
- `short` and `char` are both 16-bit but `short` is signed, `char` is unsigned
- `float` **requires `f` or `F`** — without it, a decimal literal is treated as `double`
- `long` requires `L` (prefer uppercase — lowercase `l` looks like `1`)
- `String` is NOT a primitive — it's an object/reference type

### Numeric Literals

```java
long hex    = 0xFF;          // hexadecimal (0x or 0X prefix)
long binary = 0b1010;        // binary (0b or 0B prefix)
long octal  = 017;           // octal (leading 0)

// Underscores for readability
int million = 1_000_000;     // OK
double d    = 1_00_0.0_0;    // OK (ugly but compiles)
double bad1 = _1000.0;       // DOES NOT COMPILE — cannot start with _
double bad2 = 1000.0_;       // DOES NOT COMPILE — cannot end with _
double bad3 = 1000_.0;       // DOES NOT COMPILE — cannot be adjacent to decimal point
```

---

## Reference Types vs. Primitives

- Reference types **point to** an object on the heap via a memory address (pointer)
- **All references are the same size** regardless of the object type they point to
- Reference types **can be assigned `null`**; primitives cannot
- Reference types **can call methods** (e.g., `str.length()`); primitives have no methods
- Primitive type names are **lowercase**; class names start **uppercase**
- The **object** gets garbage collected — not its reference

```java
int value = null;     // DOES NOT COMPILE
String name = null;   // fine
```

### Wrapper Classes

Each primitive has a corresponding wrapper (all are reference types):

| Primitive | Wrapper | Inherits Number? |
|---|---|---|
| `boolean` | `Boolean` | No |
| `byte` | `Byte` | Yes |
| `short` | `Short` | Yes |
| `int` | `Integer` | Yes |
| `long` | `Long` | Yes |
| `float` | `Float` | Yes |
| `double` | `Double` | Yes |
| `char` | `Character` | No |

```java
int x = Integer.parseInt("123");     // returns primitive int
Integer y = Integer.valueOf("123");  // returns Integer wrapper
```
- `Boolean.valueOf("kangaroo")` → `false` (anything that isn't "true" case-insensitive returns false)

---

## Text Blocks

- Multiline `String` using `"""` delimiters
- Opening `"""` must be followed immediately by a line break — cannot be on same line as content
- Type is still `String` — all `String` methods apply

```java
String block = """doe""";     // DOES NOT COMPILE — needs newline after opening """

String valid = """
    doe \
    deer""";     // Output: "doe deer" — \ suppresses the newline
```

- **Essential whitespace**: part of the string value
- **Incidental whitespace**: leading spaces that align code but aren't part of the value (determined by the leftmost non-whitespace character)

---

## Identifiers

4 rules for legal identifiers:
1. Must begin with a **letter, currency symbol (`$`, `¥`, `€`...), or `_`**
2. Can include digits but **cannot start with a digit**
3. **A single underscore `_` alone is NOT a valid identifier** (illegal since Java 9)
4. Cannot use a **Java reserved word** (see Table 1.8); `true`, `false`, `null` are also off-limits

```java
long okidentifier;        // valid
float $OK2Identifier;     // valid
boolean _alsoOK1;         // valid
char __SStillOK$;         // valid

int 3DPointClass;         // INVALID — starts with digit
byte hollywood@vine;      // INVALID — @ not allowed
String *$coffee;          // INVALID — * not a valid start character
short _;                  // INVALID — single underscore not allowed
double public;            // INVALID — reserved word
```

> `var` is **not** a reserved word — it CAN be used as a variable name or method name. However, it is a **reserved type name** meaning you cannot use it as a class, interface, or enum name.

---

## Declaring Variables

- Multiple variables of the **same type** can be declared in one statement
- Each variable in a multi-declaration is its own mini-declaration (only the last initialization applies to that variable)
- Cannot declare variables of different types in the same statement

```java
String s1, s2;            // valid — two declared, neither initialized
String s3 = "yes", s4 = "no";  // valid — both declared and initialized
int i1, i2, i3 = 0;       // valid — only i3 initialized; i1 and i2 are not

int num, String value;    // DOES NOT COMPILE — different types
double d1, double d2;     // DOES NOT COMPILE — type repeated (must be int d1, d2)
int i3; i4;               // DOES NOT COMPILE — i4 has no type
```

---

## var — Local Variable Type Inference

> **CRITICAL**: `var` is for **local variables only**. Cannot be used for:
> - Instance variables (fields)
> - Class/static variables
> - Method parameters
> - Constructor parameters

```java
public class Zoo {
    var tricky = "Hello";  // DOES NOT COMPILE — instance variable
}

public int add(var a, var b) { return a + b; }  // DOES NOT COMPILE — method parameters
```

### var Rules

1. **Must be initialized on the same line as declaration** — compiler needs the value to infer the type
2. Type is inferred **at compile time** and **cannot change**
3. **Cannot be initialized with `null`** (no type to infer, would default to Object)
4. **Cannot be used in multi-variable declarations** on the same line

```java
var n = null;             // DOES NOT COMPILE — can't infer type from null
var a, b = 3;             // DOES NOT COMPILE — can't use var in multi-variable declaration
int a, var b = 3;         // DOES NOT COMPILE — can't mix var with explicit type in same statement
var question;             // DOES NOT COMPILE — must be initialized on same line
question = 1;
```

```java
var number = 7;           // type inferred as int
number = "five";          // DOES NOT COMPILE — type already set to int, can't reassign String
```

After initial assignment, `var` can be reassigned `null` if the underlying type is a reference type:
```java
var name = "hello";       // String
name = null;              // fine — String can be null
```

---

## Initializing Variables

### Local variables
- **No default value** — must be explicitly initialized before use
- The compiler checks all code paths; if a variable might be uninitialized, it's a compile error
- A declared-but-never-used local variable doesn't need to be initialized

```java
int x;
int reply = x + 1;  // DOES NOT COMPILE — x not initialized

// Branch analysis:
int answer;
if (check) { answer = 1; } else { answer = 2; }
System.out.println(answer);  // OK — all paths initialize it

int onlyOne;
if (check) { onlyOne = 1; }
System.out.println(onlyOne);  // DOES NOT COMPILE — else path leaves it uninitialized
```

### Instance and class variables
- **Automatically initialized** to defaults: `null` for objects, `0`/`0L`/`0.0f`/`0.0` for numbers, `false` for boolean, `'\u0000'` for char

### final local variables
```java
final int y = 10;
y = 20;           // DOES NOT COMPILE — final variable cannot be reassigned
```

---

## Variable Scope

| Variable type | Scope |
|---|---|
| Local variable | From declaration to the **end of the block** it's declared in |
| Method parameter | For the **entire method** |
| Instance variable | From declaration until the object is **eligible for GC** |
| Class (static) variable | From declaration until the **program ends** |

Inner blocks can access variables from outer blocks, but NOT vice versa:
```java
if (hungry) {
    int bites = 1;
    { var tiny = true; }           // tiny is in scope here
    System.out.println(tiny);      // DOES NOT COMPILE — tiny out of scope
}
System.out.println(bites);         // DOES NOT COMPILE — bites out of scope
```

> **EXAM TRAP**: Scope questions often appear disguised as questions about complex logic. Always look for variables referenced outside their declaring block.

---

## Garbage Collection

- All Java objects live on the **heap** (also called the free store)
- The JVM's garbage collector automatically frees memory for unreachable objects
- **`System.gc()`** suggests GC but is **not guaranteed** to do anything
- It is the **object** that gets GC'd — not the reference to it

### Object becomes eligible for GC when:
1. **No reference** points to it (all references reassigned or set to `null`)
2. **All references** pointing to it have **gone out of scope**

```java
String one = new String("a");   // one → "a"
String two = new String("b");   // two → "b"
one = two;                       // one now → "b"; "a" has NO references → eligible for GC
String three = one;              // three → "b"
one = null;                      // "b" still has two and three pointing to it — NOT eligible
// "b" becomes eligible at end of method when two and three go out of scope
```

---

## Review Mistakes — Key Reminders

**Q3** — `main` is a **method**, not a class. `bun` is a reference to an object, not a class.

**Q5** — The object from line 9 (`brownBear`) stays reachable until `brownBear = null` on line 13 AND `pandaBear` inside roar still holds a reference — so it's eligible after line 13, not line 12. Always track ALL references including those set inside methods.

**Q7** — When asked "which are true," look for ALL correct answers. Missing one counts as wrong.

**Q8** — `var` **cannot** be used in a multi-variable assignment type declaration. `var a, b = 3` does NOT compile.

**Q11** — Classes in the **same package** as the current class are automatically visible. No import needed even for `protected` or package-private members. Imports are only for classes in **different** packages.

**Q12** — Scope: a variable declared inside an `if` block (or any inner block) is out of scope immediately after that block closes. Count the closing braces carefully.

**Q13** — Read options carefully. Not every plausible statement is true. Cross-reference each claim against actual Java rules.

**Q14** — `var` cannot be an instance variable. `var var = 3` at field level does NOT compile. But `var` as a local variable name (inside a method) IS allowed.

**Q18** — `var` in a multi-variable type declaration does NOT compile: `var a, b = 3` is invalid regardless of which variables are `var`.

**Q20** — **A method with a return type (even `void`) is never a constructor.** Constructor has NO return type at all. A class-named method with `void` compiles but is just a regular method.

**Q22** — Double-check which answer options actually satisfy all stated constraints before selecting.

**Q23** — `float` literals **must** end in `f` or `F`. A bare decimal like `3.14` is a `double` — assigning it to a `float` without explicit cast or `f` suffix does NOT compile.
