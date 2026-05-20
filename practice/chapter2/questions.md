# Chapter 2: Operators — Practice Questions

---

**1.** What is the output of the following code?

```java
public class Prec {
    public static void main(String[] args) {
        int x = 2 + 3 * 4;
        System.out.println(x);
    }
}
```

A. `20`

B. `14`

C. `24`

D. The code does not compile.

E. `12`

---

**2.** What is the output of the following code?

```java
public class Inc {
    public static void main(String[] args) {
        int x = 5;
        int y = x++ + ++x;
        System.out.println(x + " " + y);
    }
}
```

A. `6 11`

B. `7 12`

C. `7 13`

D. `6 10`

E. The code does not compile.

---

**3.** What is the output of the following?

```java
int moon = 9, star = 2 + 2 * 3;
float sun = star > 10 ? 1 : 3;
double jupiter = (sun + moon) - 1.0f;
int mars = --moon <= 8 ? 2 : 3;
System.out.println(sun + ", " + jupiter + ", " + mars);
```

A. `1, 11, 2`

B. `3.0, 11.0, 2`

C. `1.0, 11.0, 3`

D. `3.0, 13.0, 3`

E. `3.0f, 12, 2`

F. The code does not compile because one of the assignments requires an explicit numeric cast.

---

**4.** What is the output of the following?

```java
public class Assign {
    public static void main(String[] args) {
        int x = 5;
        x += 3;
        x *= 2;
        x -= 4;
        System.out.println(x);
    }
}
```

A. `10`

B. `12`

C. `14`

D. The code does not compile.

E. `16`

---

**5.** What is the result of evaluating the following expression?

```java
boolean result = (3 > 2) && (5 < 3) || (1 == 1);
System.out.println(result);
```

A. `true`

B. `false`

C. The code does not compile.

D. Throws a runtime exception.

E. `null`

---

**6.** What is the output of the following?

```java
public class Ternary {
    public static void main(String[] args) {
        int x = 5;
        String s = x > 3 ? x > 4 ? "big" : "medium" : "small";
        System.out.println(s);
    }
}
```

A. `big`

B. `medium`

C. `small`

D. The code does not compile.

E. `null`

---

**7.** Which of the following assignments compile without an explicit cast? (Choose all that apply.)

A. `int x = 5L;`

B. `long x = 5;`

C. `float x = 5;`

D. `double x = 5.0f;`

E. `int x = 'A';`

F. `byte x = 300;`

---

**8.** What is the output of the following?

```java
public class Bitwise {
    public static void main(String[] args) {
        int a = 6;   // binary: 110
        int b = 3;   // binary: 011
        System.out.println(a & b);
    }
}
```

A. `0`

B. `2`

C. `6`

D. `7`

E. The code does not compile.

---

**9.** What is the output of the following?

```java
public class Shift {
    public static void main(String[] args) {
        int x = 8;
        System.out.println(x >> 1);
    }
}
```

A. `16`

B. `4`

C. `2`

D. `1`

E. The code does not compile.

---

**10.** What is the result of the following compound assignment?

```java
byte b = 10;
b += 5;
System.out.println(b);
```

A. The code does not compile because `b += 5` would require an explicit cast.

B. `15`

C. `5`

D. An overflow occurs and the result is `-116`.

E. `10`

---

**11.** What is the output of the following?

```java
public class Compare {
    public static void main(String[] args) {
        Integer a = 127;
        Integer b = 127;
        Integer c = 128;
        Integer d = 128;
        System.out.println((a == b) + " " + (c == d));
    }
}
```

A. `true true`

B. `true false`

C. `false false`

D. `false true`

E. The code does not compile.

---

**12.** Which of the following expressions result in a `boolean`? (Choose all that apply.)

A. `5 > 3`

B. `5 = 3`

C. `5 == 3`

D. `5 != 3`

E. `"a" == "a"`

F. `true & false`

---

**13.** What is the output of the following?

```java
public class Mod {
    public static void main(String[] args) {
        System.out.println(10 % 3);
        System.out.println(-10 % 3);
    }
}
```

A. `1` then `1`

B. `1` then `-1`

C. `-1` then `1`

D. `3` then `-3`

E. The code does not compile.

---

**14.** What is the output of the following?

```java
int a = 5;
int b = a-- - --a;
System.out.println(a + " " + b);
```

A. `3 2`

B. `3 3`

C. `4 1`

D. `4 2`

E. `3 4`

---

**15.** Given `int x = 5;`, which of the following are valid boolean expressions? (Choose all that apply.)

A. `boolean b = (x = 5);`

B. `boolean b = (x == 5);`

C. `boolean b = x;`

D. `boolean b = (x != 0);`

E. `boolean b = (x > 0) ? true : false;`

---

**16.** What is the result of the following?

```java
int x = 10;
int y = 3;
double result = x / y;
System.out.println(result);
```

A. `3.3333333333333335`

B. `3.0`

C. `3`

D. The code does not compile.

E. `3.33`

---

**17.** What is printed?

```java
int x = 1;
x += x++ + x;
System.out.println(x);
```

A. `3`

B. `4`

C. `5`

D. `2`

E. The code does not compile.

---

**18.** Which operator has the highest precedence among the options below?

A. `+` (addition)

B. `*` (multiplication)

C. `++` (postfix increment)

D. `(type)` cast

E. `!` (logical NOT)

---

**19.** What is the output of the following?

```java
public class Logic {
    static boolean check(String s) {
        System.out.print(s);
        return true;
    }
    public static void main(String[] args) {
        if (check("A") || check("B")) {
            System.out.print("C");
        }
    }
}
```

A. `AC`

B. `ABC`

C. `BC`

D. `C`

E. The code does not compile.

---

**20.** What is the output of the following?

```java
public class Logic2 {
    static boolean check(String s) {
        System.out.print(s);
        return false;
    }
    public static void main(String[] args) {
        if (check("A") && check("B")) {
            System.out.print("C");
        }
    }
}
```

A. `AC`

B. `ABC`

C. `A`

D. `C`

E. The code does not compile.

---

**21.** What is the output of the following?

```java
public class Cast {
    public static void main(String[] args) {
        int x = (int) 3.9;
        System.out.println(x);
    }
}
```

A. `4` (rounds up)

B. `3` (truncates toward zero)

C. `3.9`

D. The code does not compile.

E. An exception is thrown.

---

**22.** What is the numeric result of `~5` in Java?

A. `5`

B. `-5`

C. `-6`

D. `6`

E. `4`

---

**23.** Which of the following is true about the ternary operator? (Choose all that apply.)

A. The condition must evaluate to a `boolean`.

B. The two result expressions can be of different types.

C. The ternary operator can be used as a standalone statement.

D. The ternary operator is right-associative when nested.

E. The ternary operator cannot be nested.

---

**24.** What is the output of the following?

```java
public class Promote {
    public static void main(String[] args) {
        byte b = 10;
        byte c = 20;
        var result = b + c;
        System.out.println(result);
    }
}
```

A. `30` — the type of `result` is `byte`

B. `30` — the type of `result` is `int`

C. The code does not compile because `byte + byte` is not assignable.

D. `30` — the type of `result` is `long`

E. The code does not compile because `var` cannot be used here.

---

**25.** What is the output of the following?

```java
int x = 5;
System.out.println(x > 4 ? x < 6 ? "A" : "B" : "C");
```

A. `A`

B. `B`

C. `C`

D. The code does not compile.

E. `null`

---

**26.** What is the output of the following?

```java
int a = 0b101;   // binary 5
int b = 07;      // octal 7
System.out.println(a + b);
```

A. `5`

B. `12`

C. `57`

D. `107`

E. The code does not compile.

---

**27.** Which of the following does NOT compile?

A. `double d = 1_000.0;`

B. `int i = 0b1111_1111;`

C. `long l = 100_000_000_000L;`

D. `float f = 1._5f;`

E. `int i = 0xFF;`

---

**28.** What is the output of the following?

```java
boolean x = true;
boolean y = !x;
System.out.println(x ^ y);
```

A. `true`

B. `false`

C. `0`

D. `1`

E. The code does not compile.

---

**29.** What is the output of the following?

```java
int i = 6;
i <<= 2;
System.out.println(i);
```

A. `2`

B. `8`

C. `12`

D. `24`

E. The code does not compile.

---

**30.** Which of the following statements about the `==` operator are true? (Choose all that apply.)

A. For primitives, `==` compares values.

B. For objects, `==` compares the content/values of the objects.

C. For objects, `==` compares references (memory addresses).

D. `==` can compare a primitive `int` and an `Integer` object after unboxing.

E. `"hello" == new String("hello")` is reliably `true`.

---

**31.** What is the output of the following?

```java
int x = 10;
int y = x;
x = 20;
System.out.println(y);
```

A. `10`

B. `20`

C. `0`

D. The code does not compile.

E. Unpredictable.

---

**32.** What is the output of the following?

```java
int x = 3;
int y = ++x * 2 + x--;
System.out.println(x + " " + y);
```

A. `3 12`

B. `4 12`

C. `3 13`

D. `4 13`

E. The code does not compile.

---

**33.** Which of the following compound assignments compile for `int i = 5;`? (Choose all that apply.)

A. `i += 3.0;`

B. `i *= 2L;`

C. `i /= 2.5;`

D. `i -= true;`

E. `i %= 3;`

---

**34.** What is the output of the following?

```java
double a = 5.5;
double b = 2.5;
System.out.println((int)(a + b));
System.out.println((int)a + (int)b);
```

A. `8` then `7`

B. `7` then `7`

C. `8` then `8`

D. The code does not compile.

E. `8.0` then `7`

---

**35.** What is the type of the expression `3 + 4L`?

A. `int`

B. `long`

C. `double`

D. `float`

E. Depends on the target variable it is assigned to.

---

**36.** What is the output of the following?

```java
int x = 10;
System.out.println(x > 5 ? "yes" : x > 3 ? "maybe" : "no");
```

A. `yes`

B. `maybe`

C. `no`

D. The code does not compile.

E. `null`

---

**37.** Which operator always evaluates both operands regardless of the left-side result?

A. `&&`

B. `||`

C. `&`

D. `^`

E. `?:`

---

**38.** What is the output of the following?

```java
int a = 5;
int b = 10;
System.out.println(a++ + ++b);
```

A. `15`

B. `16`

C. `17`

D. `18`

E. The code does not compile.

---

**39.** Which of the following assignments compiles without a cast?

A. `int i = (long) 5;`

B. `byte b = 127 + 1;`

C. `short s = 32767 + 1;`

D. `char c = (char) -1;`

E. `float f = 3.14;`

---

**40.** What is the output of the following?

```java
int x = 5;
boolean b = x > 3 & x++ > 5;
System.out.println(x + " " + b);
```

A. `5 false`

B. `6 false`

C. `5 true`

D. `6 true`

E. The code does not compile.

---

**41.** Which of the following correctly uses the `instanceof` pattern matching feature from Java 16+? (Choose all that apply.)

A. `if (obj instanceof String s) { System.out.println(s.length()); }`

B. `if (obj instanceof String) { System.out.println(obj.length()); }`

C. `if (obj instanceof null) { }`

D. `if (obj instanceof String s && s.length() > 0) { }`

E. Both A and D

---

**42.** What is the output of the following?

```java
double d = 1 / 2;
System.out.println(d);
```

A. `0.5`

B. `0.0`

C. `0`

D. The code does not compile.

E. `1`

---

**43.** What is the output of the following?

```java
int a = 5;   // binary: 101
int b = 3;   // binary: 011
System.out.println(a | b);
```

A. `1`

B. `7`

C. `8`

D. `2`

E. The code does not compile.

---

**44.** What is the type of `z` in the following code?

```java
int x = 10;
long y = 20L;
var z = x + y;
```

A. `int`

B. `long`

C. `double`

D. The code does not compile.

E. `var`

---

**45.** What is the output of the following?

```java
int i = Integer.MAX_VALUE;
System.out.println(i + 1 == Integer.MIN_VALUE);
```

A. `true`

B. `false`

C. The code does not compile.

D. An `ArithmeticException` is thrown.

E. Unpredictable.

---

**46.** What is the output of the following?

```java
int x = 5;
int y = 2;
System.out.println(x / y + " " + x % y);
```

A. `2 1`

B. `2.5 1`

C. `2 0`

D. `3 1`

E. The code does not compile.

---

**47.** Which of the following casts are lossless (no data is lost)? (Choose all that apply.)

A. Casting `int` to `byte`

B. Casting `byte` to `int`

C. Casting `double` to `long`

D. Casting `int` to `long`

E. Casting `int` to `float` (when the value fits)

---

**48.** What is the output of the following?

```java
int x = 10;
x = x++ + x;
System.out.println(x);
```

A. `20`

B. `21`

C. `22`

D. The code does not compile.

E. `11`

---

**49.** What is the output of the following?

```java
boolean a = true;
boolean b = false;
System.out.println(a | b);
System.out.println(a || b);
```

A. `true` then `true`

B. `false` then `true`

C. `true` then `false`

D. The code does not compile.

E. `false` then `false`

---

**50.** Given `int x = 5;`, what is the value of the expression `x > 4 && x < 10 ? x * 2 : x / 2`?

A. `2`

B. `10`

C. `5`

D. The code does not compile.

E. `2.5`
