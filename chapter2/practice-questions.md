# Chapter 2 Practice Questions — Operators

Questions target your actual mistakes (Q3 missed d, Q8 wrong, Q10 wrong) and the two
topics you flagged as still fuzzy: **operator precedence** and **casting**.

---

## Q1 — Pre-fix vs Post-fix: What Prints?

What is the output? (Choose all that apply.)

```java
int x = 5;
System.out.println(x++);   // line A
System.out.println(++x);   // line B
System.out.println(x--);   // line C
System.out.println(--x);   // line D
```

A) Line A prints 5
B) Line A prints 6
C) Line B prints 6
D) Line B prints 7
E) Line C prints 7
F) Line C prints 6
G) Line D prints 5
H) Line D prints 6

---

## Q2 — Unary Operators: Which Compile?

Which of the following compile without error? (Choose all that apply.)

```java
int a = !5;           // A
boolean b = ~true;    // B
int c = ~5;           // C
boolean d = !true;    // D
boolean e = !false;   // E
int f = -(-5);        // F
```

---

## Q3 — Numeric Promotion: What Type Is the Result?

What type does `var` infer for each variable? (Choose all that apply.)

```java
byte b1 = 10, b2 = 20;
var result1 = b1 + b2;          // A — int

short s1 = 5;
float f1 = 1.5f;
var result2 = s1 + f1;          // B — float

int i = 3;
long l = 4L;
var result3 = i * l;            // C — long

double d1 = 1.0;
int i2 = 2;
var result4 = i2 / d1;          // D — double
```

A) result1 is byte
B) result1 is int
C) result2 is float
D) result2 is double
E) result3 is int
F) result3 is long
G) result4 is int
H) result4 is double

---

## Q4 — Compound Assignment Auto-Cast

Which of the following compile? (Choose all that apply.)

```java
long x = 10L;
int y = 5;

y = y * x;    // A
y *= x;       // B
y = (int)(y * x);  // C

byte b = 5;
b = b + 1;    // D
b += 1;       // E
```

---

## Q5 — Operator Precedence: Trace the Output

What is the output?

```java
int a = 2 + 3 * 4;
int b = 10 - 2 + 3;
int c = 10 / 2 * 2;
System.out.println(a + ", " + b + ", " + c);
```

A) 14, 5, 5
B) 14, 11, 10
C) 20, 11, 10
D) 14, 8, 10
E) The code does not compile.

---

## Q6 — Pre/Post-fix in Expressions

What is the value of `result` after this code runs?

```java
int a = 5;
int result = a++ + ++a;
```

A) 10
B) 11
C) 12
D) 13
E) The code does not compile.

---

## Q7 — Modulo with Negative Numbers

What is the output?

```java
System.out.println(-7 % 3);
System.out.println(7 % -3);
System.out.println(-7 % -3);
```

A) -1, 1, -1
B) 2, -2, -1
C) -1, 1, 1
D) 2, 2, -1
E) The code does not compile.

---

## Q8 — Short-Circuit vs Logical AND

Given `String s = null;`, which of the following throw a NullPointerException at runtime? (Choose all that apply.)

```java
if (s != null & s.length() > 0) { }    // A
if (s != null && s.length() > 0) { }   // B
if (s != null | s.length() > 0) { }    // C
if (s != null || s.length() > 0) { }   // D
```

---

## Q9 — XOR Operator

What is the output?

```java
boolean a = true, b = false, c = true;
System.out.println(a ^ b);
System.out.println(a ^ c);
System.out.println(b ^ b);
System.out.println(a ^ b ^ c);
```

A) true, false, false, false
B) true, false, false, true
C) false, true, true, false
D) true, true, false, false
E) The code does not compile.

---

## Q10 — instanceof: Compile-Time Check

Which of the following produce a compile error? (Choose all that apply.)

```java
Object obj = "hello";
Integer num = 5;

if (obj instanceof String) { }      // A
if (num instanceof Integer) { }     // B
if (num instanceof String) { }      // C
if (obj instanceof Integer) { }     // D
if (obj instanceof Number) { }      // E
```

---

## Q11 — Casting: Truncation vs Rounding

What is the output?

```java
double d1 = 9.99;
double d2 = -0.9;
double d3 = -3.5;

System.out.println((int) d1);
System.out.println((int) d2);
System.out.println((int) d3);
```

A) 10, -1, -4
B) 9, 0, -3
C) 10, 0, -3
D) 9, -1, -4
E) The code does not compile.

---

## Q12 — Assignment as an Expression

Which of the following compile, and what do they do? (Choose all that apply.)

```java
boolean healthy = false;

if (healthy = true) {          // A
    System.out.println("A");
}

int x = 5;
if (x = 5) {                  // B
    System.out.println("B");
}

int y;
int z = (y = 10);             // C
```

A) Line A compiles and always prints "A"
B) Line A compiles and prints "A" only if healthy was true
C) Line A does not compile
D) Line B compiles and always prints "B"
E) Line B does not compile
F) Line C compiles and sets both y and z to 10

---

## Q13 — == With References vs Primitives

What is the output?

```java
int a = 5;
int b = 5;
String s1 = new String("hi");
String s2 = new String("hi");
String s3 = s1;

System.out.println(a == b);
System.out.println(s1 == s2);
System.out.println(s1 == s3);
System.out.println(s1.equals(s2));
```

A) true, true, true, true
B) true, false, true, true
C) true, false, false, true
D) false, false, true, true
E) The code does not compile.

---

## Q14 — Unary Operators: Which Statements Are True?

Which of the following statements about unary operators are true? (Choose all that apply.)

A) The `!` operator can be applied to numeric types
B) The `~` operator can be applied to boolean types
C) The `~` operator can be applied to int types
D) `~x` is equivalent to `-(x + 1)` for integral types
E) The post-increment operator (`x++`) returns the value after incrementing
F) The pre-increment operator (`++x`) returns the value after incrementing
G) `0` and `false` can be used interchangeably in Java

---

## Q15 — Byte Overflow

What is the output?

```java
byte b = 127;
b++;
System.out.println(b);

int i = Integer.MAX_VALUE;
i++;
System.out.println(i);
```

A) 128, 2147483648
B) -128, -2147483648
C) Compiler error on line 2
D) Runtime exception on line 2
E) 128, Integer.MIN_VALUE is printed

---

## Q16 — Compound Assignment with Post-fix

What is the value of `k` after this code runs?

```java
int k = 0;
k += k++;
System.out.println(k);
```

A) 0
B) 1
C) 2
D) The code does not compile.

---

## Q17 — Precedence: Post-fix vs Pre-fix

What is the output?

```java
int x = 3;
int y = ++x * 2 + x--;
System.out.println("x=" + x + ", y=" + y);
```

A) x=3, y=12
B) x=4, y=12
C) x=3, y=10
D) x=4, y=10
E) The code does not compile.

---

## Q18 — Ternary Operator

What is the output?

```java
int x = 5;
int y = 1;
int z = (x > 3) ? y++ : y--;
System.out.println("y=" + y + ", z=" + z);
```

A) y=2, z=2
B) y=2, z=1
C) y=0, z=1
D) y=1, z=1
E) The code does not compile.

---

*Total: 18 questions | Focus: pre/post-fix, unary operators, numeric promotion, casting, compound assignment, precedence, short-circuit operators, ==*
