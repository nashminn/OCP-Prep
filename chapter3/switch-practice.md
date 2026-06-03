# Chapter 3 — Switch Practice Questions (20 Questions)

Focus: switch statements, switch expressions, pattern matching, yield, exhaustiveness, fall-through, null handling.

---

### Q1
What is the result of the following code?

```java
int val = 2;
String result = switch (val) {
    case 1 -> "one"
    case 2 -> "two";
    default -> "other";
};
System.out.println(result);
```

- a) one
- b) two
- c) other
- d) Does not compile
- e) Throws NullPointerException at runtime

---

### Q2
What is the result of the following code?

```java
int val = 2;
String result = switch (val) {
    case 1 -> "one";
    case 2 -> {
        yield "two";
    };
    default -> "other";
};
System.out.println(result);
```

- a) one
- b) two
- c) other
- d) Does not compile
- e) Compiles and runs correctly

---

### Q3
What is the output?

```java
int x = 2;
switch (x) {
    case 1:
    case 2:
        System.out.print("A");
    case 3:
        System.out.print("B");
        break;
    case 4:
        System.out.print("C");
    default:
        System.out.print("D");
}
```

- a) A
- b) AB
- c) ABD
- d) B
- e) Does not compile

---

### Q4
What is the result?

```java
int a = 5;
final int b = 10;
int c = 15;
switch (c) {
    case a: System.out.println("a"); break;
    case b: System.out.println("b"); break;
    default: System.out.println("default");
}
```

- a) a
- b) b
- c) default
- d) Does not compile — `case a` is invalid
- e) Does not compile — `case b` is invalid

---

### Q5
What is the result?

```java
int n = 1;
switch (n) {
    case 1 -> System.out.println("one");
    case 2: System.out.println("two"); break;
    default -> System.out.println("other");
}
```

- a) one
- b) two
- c) other
- d) Does not compile
- e) Throws an exception at runtime

---

### Q6
What is the result?

```java
enum Season { SPRING, SUMMER, FALL, WINTER }
Season s = Season.FALL;
switch (s) {
    case SPRING -> System.out.println("warm");
    case FALL   -> System.out.println("cool");
}
```

- a) cool
- b) warm
- c) Does not compile — switch statement must be exhaustive
- d) Does not compile — enum requires all values covered without `default`
- e) Compiles but throws an exception at runtime

---

### Q7
What is the result?

```java
enum Season { SPRING, SUMMER, FALL, WINTER }
Season s = Season.FALL;
String label = switch (s) {
    case SPRING -> "warm";
    case FALL   -> "cool";
};
System.out.println(label);
```

- a) cool
- b) warm
- c) Does not compile — switch expression is not exhaustive
- d) Compiles and prints "cool"
- e) Throws NullPointerException at runtime

---

### Q8
What is the result?

```java
int x = 3;
switch (x) {
    case 1 -> {
        yield 10;
    }
    default -> System.out.println("other");
}
```

- a) 10
- b) other
- c) Does not compile — `yield` is not valid in a switch statement
- d) Does not compile — arrow syntax requires a single expression
- e) Compiles but the `yield` value is discarded and "other" prints

---

### Q9
What is the result?

```java
void describe(Number n) {
    String msg = switch (n) {
        case Number num  -> "Unknown: " + num;
        case Integer i   -> "Integer: " + i;
        case Double d    -> "Double: " + d;
    };
    System.out.println(msg);
}
```

- a) Compiles; prints "Integer: ..." when called with an Integer
- b) Compiles; always prints "Unknown: ..." since Number matches everything
- c) Does not compile — switch expression is not exhaustive
- d) Does not compile — `case Integer i` and `case Double d` are dominated by `case Number num`
- e) Does not compile — you must use a `default` clause with type patterns

---

### Q10
What is the result?

```java
String classify(Number n) {
    return switch (n) {
        case Integer i              -> "any int";
        case Integer i when i > 100 -> "big int";
        case Number num             -> "other number";
    };
}
```

- a) Compiles; returns "big int" when called with 200
- b) Compiles; always returns "any int" when n is an Integer
- c) Does not compile — `case Integer i when i > 100` is dominated by the preceding `case Integer i`
- d) Does not compile — `when` guards are not valid in switch expressions
- e) Does not compile — switch expression is not exhaustive

---

### Q11
What is the result?

```java
String fish = null;
System.out.print(switch (fish) {
    case "ClownFish" -> "Hello!";
    case "BlueTang"  -> "Hello again!";
    default          -> "Goodbye";
});
```

- a) Goodbye
- b) Does not compile — switch expression needs `case null`
- c) Does not compile — `null` cannot be a switch variable
- d) Throws NullPointerException at runtime
- e) Prints an empty string

---

### Q12
What is the result?

```java
String fish = null;
switch (fish) {
    case "ClownFish": System.out.println("Hello!"); break;
    case "BlueTang":  System.out.println("Hello again!"); break;
    case null:        System.out.println("null!"); break;
}
```

- a) null!
- b) Does not compile — `case null` is not valid with colon syntax
- c) Does not compile — using `case null` implies pattern matching; the switch statement must be exhaustive but has no `default`
- d) Compiles and prints "null!"
- e) Throws NullPointerException at runtime

---

### Q13
What is the result?

```java
String fish = null;
System.out.print(switch (fish) {
    case "ClownFish" -> "Hello!";
    case "BlueTang"  -> "Hello again!";
    default          -> "Goodbye";
    case null        -> "null!";
});
```

- a) null!
- b) Goodbye
- c) Does not compile — `case null` cannot appear after `default`
- d) Does not compile — switch expression is not exhaustive
- e) Throws NullPointerException at runtime

---

### Q14
What is the result?

```java
Number fish = 10;
String name = switch (fish) {
    case Integer freshWater -> "Bass";
    case Number saltWater   -> "ClownFish";
    case String s           -> "Shark";
};
System.out.println(name);
```

- a) Bass
- b) ClownFish
- c) Does not compile — `case String s` is an unrelated type to `Number`
- d) Does not compile — switch expression is not exhaustive
- e) Throws ClassCastException at runtime

---

### Q15
What is the result?

```java
Number zooPatrons = Integer.valueOf(1_000);
switch (zooPatrons) {
    case Integer count -> System.out.print("Welcome: " + count);
}
```

- a) Welcome: 1000
- b) Does not compile — the switch statement using pattern matching is not exhaustive, because the reference type is `Number` not `Integer`
- c) Compiles and prints "Welcome: 1000" since the object is actually an Integer at runtime
- d) Does not compile — `Integer.valueOf` cannot be used as a switch variable
- e) Throws NullPointerException at runtime

---

### Q16
What is the output?

```java
int val = 5;
switch (val) {
    default:
        System.out.print("D");
    case 1:
        System.out.print("1");
        break;
    case 5:
        System.out.print("5");
}
```

- a) D
- b) D1
- c) 5
- d) D5
- e) Does not compile — `default` must appear last

---

### Q17
What is the output?

```java
int val = 3;
switch (val) {
    default:
        System.out.print("D");
    case 1:
        System.out.print("1");
        break;
    case 5:
        System.out.print("5");
}
```

- a) D
- b) D1
- c) 1
- d) 5
- e) Does not compile

---

### Q18
What is the result?

```java
int x = 4;
int result = switch (x) {
    case 1 -> 10;
    case 2 -> { yield 20; }
    case 3 -> { System.out.println("three"); yield 30; }
    case 4 -> { throw new RuntimeException("bad value"); }
    default -> 0;
};
System.out.println(result);
```

- a) 0
- b) Does not compile — case blocks in a switch expression must always have `yield`
- c) Does not compile — you cannot throw inside a switch expression
- d) Throws RuntimeException at runtime
- e) Compiles but prints 0 because the exception is swallowed

---

### Q19
What is the result?

```java
int day = 6;
String type = switch (day) {
    case 1, 7 -> "Weekend";
    case 2, 3, 4, 5, 6 -> "Weekday";
    default -> "Unknown";
};
System.out.println(type);
```

- a) Weekend
- b) Weekday
- c) Unknown
- d) Does not compile — multiple values in one case require colon syntax
- e) Does not compile — arrow cases with multiple values need `yield`

---

### Q20
Which of the following are valid types for the switch variable in a traditional switch statement (without pattern matching)? Choose all that apply.

- a) `int`
- b) `long`
- c) `byte`
- d) `double`
- e) `char`
- f) `boolean`
- g) `String`
- h) `Long`

---

## Answers

| Q  | Answer         | Key rule                                                                                         |
|----|----------------|--------------------------------------------------------------------------------------------------|
| 1  | d              | Arrow case expressions require a terminating semicolon                                           |
| 2  | d              | Case *blocks* (`{}`) must NOT have a semicolon after the closing `}`                             |
| 3  | b              | `x=2` matches case 2, prints "A", falls through, prints "B", hits `break` → AB                  |
| 4  | d              | `a` is not `final` — only `final` variables or literals are valid case labels                    |
| 5  | d              | Cannot mix `:` and `->` in the same switch                                                       |
| 6  | a              | A switch *statement* (no assignment) does NOT need to be exhaustive — prints "cool"              |
| 7  | c              | A switch *expression* must be exhaustive; `SUMMER` and `WINTER` are not covered                  |
| 8  | c              | `yield` is only valid inside a switch *expression*, not a switch statement                       |
| 9  | d              | `case Number num` dominates all subtypes below it → unreachable code, compile error              |
| 10 | c              | Unguarded `case Integer i` dominates `case Integer i when i > 100` below it                     |
| 11 | d              | `default` does not catch `null` — passing `null` always throws NPE unless `case null` is present |
| 12 | c              | `case null` counts as pattern matching — switch statement using PM must be exhaustive            |
| 13 | c              | `case null` cannot appear after `default`                                                        |
| 14 | c              | `String` is not a subtype of `Number` — unrelated type is a compile error                        |
| 15 | b              | Reference type of variable is `Number`; `case Integer count` doesn't cover all `Number` values  |
| 16 | c              | `val=5` matches `case 5` directly — `default` only runs when nothing else matches               |
| 17 | b              | `val=3` hits `default`, prints "D", falls through to `case 1`, prints "1", breaks → D1          |
| 18 | d              | Throwing in a case block is valid (no `yield` needed); compiles fine, throws at runtime          |
| 19 | b              | Arrow syntax supports comma-separated case values; `day=6` → "Weekday"                          |
| 20 | a, c, e, g     | `long`, `double`, `boolean`, `float` and their wrappers are NOT valid switch types              |
