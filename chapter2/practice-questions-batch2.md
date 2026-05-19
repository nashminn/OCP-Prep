# Chapter 2 Practice Questions — Batch 2

---

## Q1 — Pre/Post-fix in a Complex Expression

What is printed? (Choose one.)

```java
int x = 10;
int y = x-- - --x;
System.out.println("x=" + x + ", y=" + y);
```

A) x=9, y=1
B) x=8, y=2
C) x=9, y=2
D) x=8, y=1
E) The code does not compile.

---

## Q2 — char Arithmetic and Promotion

Which of the following compile, and what type does `var` infer for `r4`? (Choose all that apply.)

```java
char c = 'A';              // 'A' is Unicode 65
char r1 = c + 1;          // A
int  r2 = c + 1;          // B
char r3 = (char)(c + 1);  // C
var  r4 = c;              // D
```

A) Line A compiles
B) Line B compiles
C) Line C compiles
D) `r4` is of type `char`
E) `r4` is of type `int`

---

## Q3 — String Concatenation with `+`

What is the output? (Choose one.)

```java
int a = 1, b = 2;
System.out.println("sum: " + a + b);
System.out.println(a + b + " is the sum");
System.out.println("val: " + (a + b));
```

A) sum: 3, 3 is the sum, val: 3
B) sum: 12, 3 is the sum, val: 3
C) sum: 12, 12 is the sum, val: 12
D) sum: 3, 12 is the sum, val: 3
E) The code does not compile.

---

## Q4 — instanceof Pattern Matching (Java 16+)

Which of the following are true? (Choose all that apply.)

```java
Object obj = "Hello";

if (obj instanceof String s) {                        // block A
    System.out.println(s.length());
}

if (obj instanceof String s && s.startsWith("H")) {  // block B
    System.out.println(s);
}

Integer num = 42;
if (num instanceof String s) {                        // C
    System.out.println(s);
}

Object x = "test";
boolean check = x instanceof String;                  // D
```

A) Block A prints 5
B) Block B compiles and prints "Hello"
C) Line C compiles but the body never executes
D) Line C does not compile
E) Line D compiles and `check` is `true`

---

## Q5 — Literal Type Defaults

Which of the following compile without error? (Choose all that apply.)

```java
float  f1 = 1.5;         // A
float  f2 = 1.5f;        // B
double d1 = 1.5f;        // C
long   l1 = 9999999999;  // D
long   l2 = 9999999999L; // E
int    i1 = 1_000_000;   // F
```

A) Line A
B) Line B
C) Line C
D) Line D
E) Line E
F) Line F

---

## Q6 — Nested Ternary

What is the output? (Choose one.)

```java
int score = 75;
String grade = score >= 90 ? "A" : score >= 80 ? "B" : score >= 70 ? "C" : "F";
System.out.println(grade);

int x = 5;
String size = x > 10 ? "big" : x > 3 ? "medium" : "small";
System.out.println(size);
```

A) C, medium
B) C, small
C) F, medium
D) B, medium
E) The code does not compile.

---

## Q7 — Bitwise Operators on Integers

What is the output? (Choose one.)

```java
int a = 0b1010;  // 10
int b = 0b1100;  // 12

System.out.println(a & b);
System.out.println(a | b);
System.out.println(a ^ b);
System.out.println(~a);
```

A) 10, 12, 6, -11
B) 8, 14, 6, -11
C) 8, 14, 4, -11
D) 8, 12, 6, -10
E) The code does not compile.

---

## Q8 — Shift Operators

What is the output? (Choose one.)

```java
int x = 4;
System.out.println(x << 2);
System.out.println(x >> 1);
System.out.println(-4 >>> 1);
```

A) 16, 2, 2
B) 16, 2, 2147483646
C) 8, 2, 2147483646
D) 16, 2, -2
E) The code does not compile.

---

*8 questions | Mix: pre/post-fix (new scenario), char promotion, String + precedence, instanceof pattern matching, literal defaults, nested ternary, bitwise on integers, shift operators*
