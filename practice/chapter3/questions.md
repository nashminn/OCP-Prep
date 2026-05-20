# Chapter 3: Making Decisions — Practice Questions

---

**1.** What is the output of the following code?

```java
public class Dangling {
    public static void main(String[] args) {
        int x = 5;
        if (x > 0)
            if (x > 10)
                System.out.println("big");
        else
            System.out.println("small");
    }
}
```

A. `big`

B. `small`

C. Nothing is printed.

D. The code does not compile.

E. A runtime exception is thrown.

---

**2.** What is the output of the following code?

```java
public class Switch {
    public static void main(String[] args) {
        int x = 2;
        switch (x) {
            case 1: System.out.print("one ");
            case 2: System.out.print("two ");
            case 3: System.out.print("three ");
            default: System.out.print("default");
        }
    }
}
```

A. `two`

B. `two three default`

C. `two three`

D. `one two three default`

E. The code does not compile.

---

**3.** Which of the following types can be used in a traditional `switch` statement? (Choose all that apply.)

A. `int`

B. `long`

C. `String`

D. `double`

E. `byte`

F. `char`

G. `boolean`

---

**4.** What is the output of the following?

```java
public class Loop {
    public static void main(String[] args) {
        int i = 0;
        while (i++ < 3) {
            System.out.print(i + " ");
        }
    }
}
```

A. `0 1 2`

B. `1 2 3`

C. `0 1 2 3`

D. `1 2 3 4`

E. The code does not compile.

---

**5.** What is the output of the following?

```java
public class DoWhile {
    public static void main(String[] args) {
        int i = 5;
        do {
            System.out.print(i + " ");
            i--;
        } while (i > 5);
    }
}
```

A. `5`

B. `5 4 3 2 1`

C. Nothing is printed.

D. The code does not compile.

E. An infinite loop occurs.

---

**6.** What is the output of the following?

```java
public class ForLoop {
    public static void main(String[] args) {
        for (int i = 0; i < 5; i += 2) {
            System.out.print(i + " ");
        }
    }
}
```

A. `0 1 2 3 4`

B. `0 2 4`

C. `1 3 5`

D. `0 2 4 6`

E. The code does not compile.

---

**7.** What is the output of the following?

```java
public class Break {
    public static void main(String[] args) {
        OUTER: for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (j == 1) break OUTER;
                System.out.print(i + "" + j + " ");
            }
        }
    }
}
```

A. `00 01 02 10 11 12 20 21 22`

B. `00`

C. `00 10 20`

D. The code does not compile because labels are not allowed.

E. `00 01 10 11 20 21`

---

**8.** What is the output of the following?

```java
public class Continue {
    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            if (i % 2 == 0) continue;
            System.out.print(i + " ");
        }
    }
}
```

A. `0 2 4`

B. `1 3`

C. `1 2 3 4`

D. `0 1 2 3 4`

E. The code does not compile.

---

**9.** What is the output of the following?

```java
public class Pattern {
    public static void main(String[] args) {
        Object obj = "Hello";
        if (obj instanceof String s && s.length() > 3) {
            System.out.println(s.toUpperCase());
        }
    }
}
```

A. `hello`

B. `HELLO`

C. `Hello`

D. The code does not compile.

E. Nothing is printed.

---

**10.** What is the output of the following switch expression?

```java
int day = 3;
String name = switch (day) {
    case 1 -> "Monday";
    case 2 -> "Tuesday";
    case 3 -> "Wednesday";
    default -> "Other";
};
System.out.println(name);
```

A. `Tuesday`

B. `Wednesday`

C. `Other`

D. The code does not compile.

E. `null`

---

**11.** Which of the following are valid `switch` expression patterns in Java 21? (Choose all that apply.)

A. `case 1 -> "one";`

B. `case 1: yield "one";`

C. `case 1: return "one";`

D. `case 1, 2 -> "low";`

E. `default -> throw new RuntimeException();`

---

**12.** What is the output of the following?

```java
public class FlowScope {
    public static void main(String[] args) {
        Object obj = "Java";
        if (!(obj instanceof String s)) {
            System.out.println("not a string");
        } else {
            System.out.println(s.length());
        }
    }
}
```

A. `not a string`

B. `4`

C. `Java`

D. The code does not compile.

E. A `ClassCastException` is thrown.

---

**13.** What is the output of the following?

```java
public class Inf {
    public static void main(String[] args) {
        int i = 0;
        for ( ; ; ) {
            if (i++ == 3) break;
        }
        System.out.println(i);
    }
}
```

A. `3`

B. `4`

C. `0`

D. The code does not compile.

E. An infinite loop occurs.

---

**14.** What is the output of the following?

```java
public class LabelContinue {
    public static void main(String[] args) {
        int total = 0;
        OUTER: for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (j == 1) continue OUTER;
                total++;
            }
        }
        System.out.println(total);
    }
}
```

A. `9`

B. `6`

C. `3`

D. `0`

E. The code does not compile.

---

**15.** Which of the following can be used as the condition of an `if` statement in Java?

A. `if (1)` — since 1 is non-zero

B. `if (true)`

C. `if (x = true)` where `x` is a `boolean`

D. `if (x == 1)` where `x` is an `int`

E. `if (null)`

---

**16.** What is the output of the following?

```java
public class SwitchStr {
    public static void main(String[] args) {
        String s = "b";
        switch (s) {
            case "a": System.out.print("A");
            case "b": System.out.print("B");
            case "c": System.out.print("C"); break;
            default:  System.out.print("D");
        }
    }
}
```

A. `B`

B. `BC`

C. `BCD`

D. `ABCD`

E. The code does not compile.

---

**17.** What is the output of the following?

```java
public class ForEach {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5};
        int sum = 0;
        for (int n : nums) {
            sum += n;
        }
        System.out.println(sum);
    }
}
```

A. `10`

B. `15`

C. `12`

D. The code does not compile.

E. `5`

---

**18.** What happens with the following code at compile time?

```java
int x = 5;
if (x = 5) {
    System.out.println("yes");
}
```

A. Prints `yes`

B. Prints nothing

C. Does not compile — the condition `x = 5` is an `int`, not a `boolean`

D. Does not compile — assignment is not allowed inside an `if`

E. Throws a runtime exception

---

**19.** What is the output of the following switch expression using `yield`?

```java
int x = 2;
int result = switch (x) {
    case 1 -> 10;
    case 2 -> {
        int y = x * 10;
        yield y + 5;
    }
    default -> 0;
};
System.out.println(result);
```

A. `20`

B. `25`

C. `15`

D. The code does not compile.

E. `10`

---

**20.** What is the output of the following?

```java
public class While {
    public static void main(String[] args) {
        int x = 10;
        while (x > 0) {
            x -= 3;
        }
        System.out.println(x);
    }
}
```

A. `0`

B. `-1`

C. `-2`

D. `1`

E. The code does not compile.

---

**21.** Which of the following is true about the pattern variable in `instanceof` pattern matching?

A. The pattern variable can be used anywhere in the enclosing method after the `instanceof` check.

B. The pattern variable is scoped by flow — it is only available where the compiler can prove the check passed.

C. The pattern variable must be declared with `final`.

D. The pattern variable shadows any outer variable with the same name and causes a compile error.

E. The pattern variable is only available inside the `if` block body.

---

**22.** What is the output of the following?

```java
public class FlatSwitch {
    public static void main(String[] args) {
        int x = 5;
        switch (x) {
            case 5:
            case 6:
                System.out.println("five or six");
                break;
            default:
                System.out.println("other");
        }
    }
}
```

A. `five or six` then `other`

B. `five or six`

C. `other`

D. The code does not compile.

E. Nothing is printed.

---

**23.** What is the output of the following?

```java
public class Nested {
    public static void main(String[] args) {
        for (int i = 1; i <= 3; i++) {
            for (int j = 1; j <= 3; j++) {
                if (i == j) System.out.print(i + " ");
            }
        }
    }
}
```

A. `1 2 3`

B. `1 1 2 2 3 3`

C. `1 4 9`

D. The code does not compile.

E. `1 2 3 1 2 3 1 2 3`

---

**24.** What is the output of the following?

```java
public class ReturnLoop {
    static int find(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) return i;
        }
        return -1;
    }
    public static void main(String[] args) {
        int[] data = {3, 1, 4, 1, 5};
        System.out.println(find(data, 4));
    }
}
```

A. `-1`

B. `2`

C. `3`

D. `4`

E. The code does not compile.

---

**25.** Which statement about `break` and `continue` are true? (Choose all that apply.)

A. `break` exits the innermost enclosing loop or switch.

B. `continue` skips to the next iteration of the innermost enclosing loop.

C. `break LABEL` exits the labeled statement and resumes after it.

D. `continue LABEL` continues the labeled loop's next iteration.

E. `break` can be used inside an `if` statement without an enclosing loop.

---

**26.** What is the output of the following switch expression?

```java
int score = 85;
String grade = switch (score / 10) {
    case 10, 9 -> "A";
    case 8     -> "B";
    case 7     -> "C";
    default    -> "F";
};
System.out.println(grade);
```

A. `A`

B. `B`

C. `C`

D. `F`

E. The code does not compile.

---

**27.** What is the output of the following?

```java
public class Unreachable {
    public static void main(String[] args) {
        int i = 0;
        for (i = 0; i < 5; i++) { }
        System.out.println(i);
    }
}
```

A. `4`

B. `5`

C. `0`

D. The code does not compile.

E. `6`

---

**28.** What is the output of the following?

```java
public class GuardedPattern {
    public static void main(String[] args) {
        Object o = 42;
        String result = switch (o) {
            case Integer i when i > 100 -> "big";
            case Integer i              -> "small int";
            default                     -> "other";
        };
        System.out.println(result);
    }
}
```

A. `big`

B. `small int`

C. `other`

D. The code does not compile.

E. `42`

---

**29.** Which of the following loop constructs runs the body at least once regardless of the condition?

A. `for` loop

B. `while` loop

C. `do-while` loop

D. Enhanced `for` loop

E. None of the above

---

**30.** What is the output of the following?

```java
public class MultiCase {
    public static void main(String[] args) {
        int x = 1;
        String s = switch (x) {
            case 1, 2 -> "low";
            case 3, 4 -> "high";
            default   -> "other";
        };
        System.out.println(s);
    }
}
```

A. `low`

B. `high`

C. `other`

D. The code does not compile.

E. `null`

---

**31.** What is the output of the following?

```java
int x = 0;
for (int i = 0; i < 10; i++) {
    if (i == 5) break;
    x++;
}
System.out.println(x);
```

A. `4`

B. `5`

C. `6`

D. `10`

E. The code does not compile.

---

**32.** What is the output of the following?

```java
public class SwitchNull {
    public static void main(String[] args) {
        String s = null;
        switch (s) {
            case "a" -> System.out.println("a");
            default  -> System.out.println("default");
        }
    }
}
```

A. `default`

B. Nothing is printed.

C. The code does not compile.

D. A `NullPointerException` is thrown.

E. `null`

---

**33.** Which of the following are true about switch expressions vs. switch statements? (Choose all that apply.)

A. Switch expressions must cover all possible values (be exhaustive) or have a `default`.

B. Switch statements must also be exhaustive.

C. Switch expressions can use `->` (arrow) labels.

D. Switch statements cannot fall through when using `->` (arrow) labels.

E. Switch expressions return a value; switch statements do not.

---

**34.** What is the output of the following?

```java
public class Scope {
    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) { }
        System.out.println(i);   // line 4
    }
}
```

A. `3`

B. `0`

C. The code does not compile — `i` is out of scope on line 4.

D. `2`

E. The code does not compile — `i` was never initialized outside the loop.

---

**35.** What is the output of the following?

```java
public class Tricky {
    public static void main(String[] args) {
        int x = 1;
        if (x > 0)
            System.out.println("positive");
            System.out.println("done");
    }
}
```

A. `positive`

B. `positive` then `done`

C. `done`

D. The code does not compile.

E. Nothing is printed.

---

**36.** Which of the following are valid case labels in a Java 21 switch statement/expression? (Choose all that apply.)

A. A `String` literal

B. A `null` literal (in switch expressions/statements with pattern matching)

C. A `double` literal

D. An `enum` constant

E. A `final int` variable initialized at compile time

---

**37.** What is the output of the following?

```java
public class DoWhile2 {
    public static void main(String[] args) {
        int i = 0;
        do {
            i++;
        } while (i < 0);
        System.out.println(i);
    }
}
```

A. `0`

B. `1`

C. The body never executes.

D. The code does not compile.

E. An infinite loop occurs.

---

**38.** What is the output of the following?

```java
public class PatternNeg {
    public static void main(String[] args) {
        Object obj = "test";
        if (!(obj instanceof String s)) {
            System.out.println("not string");
        } else {
            System.out.println(s.length());
        }
    }
}
```

A. `not string`

B. `4`

C. `test`

D. The code does not compile — `s` cannot be used in the else branch.

E. A `ClassCastException` is thrown.

---

**39.** What is the output of the following?

```java
int result = 0;
for (int i = 1; i <= 5; i++) {
    result += i;
    if (result > 6) break;
}
System.out.println(result);
```

A. `6`

B. `10`

C. `7`

D. `15`

E. The code does not compile.

---

**40.** What happens when a `switch` statement has no matching `case` and no `default`?

A. A `NoSuchCaseException` is thrown.

B. The program terminates normally; no output from the switch.

C. The first `case` is executed by default.

D. The code does not compile.

E. An `IllegalStateException` is thrown.

---

**41.** What is the output of the following?

```java
public class ForInit {
    public static void main(String[] args) {
        int i = 5;
        for (i = 0; i < 3; i++) { }
        System.out.println(i);
    }
}
```

A. `5`

B. `3`

C. `2`

D. `0`

E. The code does not compile.

---

**42.** Which of the following cause a compile error? (Choose all that apply.)

```java
1: int x = 5;
2: switch (x) {
3:     case 1:
4:         int y = 10;
5:         break;
6:     case 5:
7:         y = 20;       // line 7
8:         System.out.println(y);
9: }
```

A. Line 3

B. Line 4

C. Line 7

D. Line 8

E. None — the code compiles.

---

**43.** What is the output of the following?

```java
public class Enhanced {
    public static void main(String[] args) {
        String[] words = {"cat", "dog", "bird"};
        for (String w : words) {
            if (w.equals("dog")) continue;
            System.out.print(w + " ");
        }
    }
}
```

A. `cat dog bird`

B. `cat bird`

C. `dog`

D. The code does not compile.

E. `cat dog`

---

**44.** Which of the following is true about labeled `break`?

A. A labeled `break` can only break out of loops, not switch statements.

B. A labeled `break` exits the labeled statement and resumes execution after it.

C. Labels must be in UPPERCASE to be valid.

D. A labeled `break` and an unlabeled `break` behave identically.

E. Labels are not supported in Java.

---

**45.** What is the output of the following?

```java
public class TernaryVoid {
    public static void main(String[] args) {
        boolean b = true;
        System.out.println(b ? "yes" : "no");
    }
}
```

A. `yes`

B. `no`

C. `true`

D. The code does not compile.

E. `null`

---

**46.** What is the output of the following?

```java
public class MultiBreak {
    public static void main(String[] args) {
        int total = 0;
        outer:
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (i + j == 4) break outer;
                total++;
            }
        }
        System.out.println(total);
    }
}
```

A. `25`

B. `4`

C. `9`

D. `5`

E. The code does not compile.

---

**47.** What is the output of the following?

```java
public class SwitchReturn {
    static String grade(int score) {
        return switch (score) {
            case 100 -> "A+";
            case 90  -> "A";
            default  -> "Other";
        };
    }
    public static void main(String[] args) {
        System.out.println(grade(90));
    }
}
```

A. `A+`

B. `A`

C. `Other`

D. The code does not compile.

E. `null`

---

**48.** Which of the following are true about the enhanced for loop? (Choose all that apply.)

A. It can iterate over arrays.

B. It can iterate over `Iterable` types such as `List`.

C. It allows modifying the original array elements via the loop variable for primitives.

D. The loop variable is a copy of each element; reassigning it does not affect the source.

E. You can use `break` and `continue` inside an enhanced for loop.

---

**49.** What is the output of the following?

```java
public class SwitchExpr {
    public static void main(String[] args) {
        int x = 3;
        int y = switch (x) {
            case 1, 2 -> 10;
            case 3    -> { System.out.print("three "); yield 30; }
            default   -> 0;
        };
        System.out.println(y);
    }
}
```

A. `30`

B. `three 30`

C. `three`

D. The code does not compile.

E. `0`

---

**50.** What is the output of the following?

```java
public class Scope2 {
    public static void main(String[] args) {
        Object obj = Integer.valueOf(10);
        if (obj instanceof Integer i && i > 5) {
            System.out.println("large: " + i);
        } else {
            System.out.println("small or not int");
        }
    }
}
```

A. `large: 10`

B. `small or not int`

C. `10`

D. The code does not compile.

E. A `ClassCastException` is thrown.
