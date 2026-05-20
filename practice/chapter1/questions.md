# Chapter 1: Building Blocks — Practice Questions

---

**1.** Which of the following is a valid `main()` method signature that can serve as an entry point for a Java program? (Choose all that apply.)

A. `public static void main(String[] args)`

B. `public static void main(String args[])`

C. `public static void main(String... args)`

D. `static public void main(String[] args)`

E. `public void main(String[] args)`

F. `public static int main(String[] args)`

---

**2.** What is the output of the following code? The program is run with: `java Puzzle Hello World`

```java
public class Puzzle {
    public static void main(String[] args) {
        System.out.println(args[0]);
    }
}
```

A. `Hello World`

B. `Hello`

C. `World`

D. The code does not compile.

E. A `NullPointerException` is thrown at runtime.

F. An `ArrayIndexOutOfBoundsException` is thrown at runtime.

---

**3.** Which of the following statements about imports are correct? (Choose all that apply.)

A. `import java.util.*;` imports all classes in `java.util` and its subpackages.

B. `import java.util.*;` imports all classes directly in `java.util` but not subpackages.

C. `import java.lang.String;` is redundant because `java.lang` is automatically imported.

D. Given `import java.util.ArrayList;` and `import java.util.*;`, the wildcard is redundant.

E. You can have at most one wildcard import per file.

F. Wildcard imports slow down program execution because more classes are loaded.

---

**4.** Given the following two files, which lines in `Cat.java` give a compiler error? (Choose all that apply.)

```java
// Dog.java
package pets;
public class Dog { }

// Cat.java
1: package pets;
2: import pets.*;
3: import pets.Dog;
4: public class Cat {
5:     Dog d;
6:     pets.Dog d2;
7: }
```

A. Line 2

B. Line 3

C. Line 5

D. Line 6

E. None — all lines compile.

---

**5.** What is the output of the following code?

```java
public class Counter {
    static int count = 0;
    public static void main(String[] args) {
        Counter c1 = new Counter();
        Counter c2 = new Counter();
        c1.count++;
        System.out.println(c2.count);
    }
}
```

A. `0`

B. `1`

C. `2`

D. The code does not compile.

E. A `NullPointerException` is thrown at runtime.

---

**6.** How many objects are eligible for garbage collection immediately after line 8 executes?

```java
1: public class GC {
2:     public static void main(String[] args) {
3:         String a = new String("alpha");
4:         String b = new String("beta");
5:         String c = a;
6:         a = b;
7:         b = null;
8:         c = null;
9:     }
10: }
```

A. 0

B. 1

C. 2

D. 3

E. 4

---

**7.** Which of the following are valid single-line variable declarations? (Choose all that apply.)

A. `int num1, num2;`

B. `int num1 = 5, num2;`

C. `int num1, num2 = 5;`

D. `int num1 = 5, int num2 = 3;`

E. `int num1 = 5; int num2 = 3;`

F. `int num1 = num2 = 5;`

---

**8.** Which of the following are valid Java identifiers? (Choose all that apply.)

A. `_myVar`

B. `$amount`

C. `2fast`

D. `my-var`

E. `public`

F. `myVar2`

G. `MY_CONST`

---

**9.** What is the output of the following code?

```java
public class Scope {
    public static void main(String[] args) {
        int x = 10;
        {
            int y = 20;
            x = y;
        }
        System.out.println(x);
    }
}
```

A. `10`

B. `20`

C. The code does not compile because `y` is used after its scope ends.

D. The code does not compile because `x` cannot be reassigned inside a block.

E. A `RuntimeException` is thrown.

---

**10.** Which primitive type is used to store a single Unicode character?

A. `byte`

B. `short`

C. `int`

D. `char`

E. `String`

---

**11.** What is the default value of an instance variable of type `boolean`?

A. `true`

B. `false`

C. `0`

D. `null`

E. Instance variables don't have default values; they must be explicitly assigned.

---

**12.** What is the output of the following code?

```java
public class Literals {
    public static void main(String[] args) {
        int million = 1_000_000;
        int hex = 0xFF;
        System.out.println(million + " " + hex);
    }
}
```

A. `1_000_000 0xFF`

B. `1000000 255`

C. `1000000 0xFF`

D. The code does not compile.

E. `1_000_000 255`

---

**13.** Which of the following numeric literals are valid in Java? (Choose all that apply.)

A. `long x = 123456789L;`

B. `float f = 3.14;`

C. `float f = 3.14f;`

D. `double d = 3_14.1_5;`

E. `int i = 0b1010;`

F. `int i = 0XFF;`

G. `int i = _1000;`

---

**14.** What is the output of the following code?

```java
public class Types {
    public static void main(String[] args) {
        var x = 5;
        var y = 3.0;
        var z = x + y;
        System.out.println(z);
    }
}
```

A. `8`

B. `8.0`

C. The code does not compile.

D. `5`

E. `3.0`

---

**15.** Which of the following uses of `var` are valid? (Choose all that apply.)

A. `var x = null;`

B. `var x = 5;`

C. `var x = new ArrayList<>();`

D. `var x;`

E. `var x = (String) null;`

F. As an instance field: `var x = 5;` declared directly inside a class body.

---

**16.** What does the following code print?

```java
public class Overflow {
    public static void main(String[] args) {
        byte b = 127;
        b++;
        System.out.println(b);
    }
}
```

A. `127`

B. `128`

C. `-128`

D. The code does not compile.

E. An overflow exception is thrown at runtime.

---

**17.** Which statements about the `var` keyword are correct? (Choose all that apply.)

A. `var` can be used for local variables, instance fields, and method parameters.

B. `var` can be used only for local variables.

C. The type inferred by `var` is determined at compile time.

D. `var` is a reserved keyword and therefore cannot be used as a variable name.

E. `var` can be used in a multi-variable declaration: `var x = 1, y = 2;`

---

**18.** What is the output of the following code?

```java
public class Init {
    static int x;
    int y;
    public static void main(String[] args) {
        Init obj = new Init();
        System.out.println(x + " " + obj.y);
    }
}
```

A. `0 0`

B. `null null`

C. The code does not compile.

D. A `NullPointerException` is thrown at runtime.

E. `0 null`

---

**19.** A local variable of type `int` is declared but never initialized. What happens when you try to read it?

A. It defaults to `0`.

B. It defaults to `null`.

C. The code does not compile.

D. A `NullPointerException` is thrown at runtime.

E. A `RuntimeException` is thrown at runtime.

---

**20.** Which of the following correctly describes Java's runtime and compilation tools?

A. The JVM contains the JRE, which contains the JDK.

B. The JDK contains tools to compile Java source; the JVM executes bytecode.

C. The JRE is required to compile Java programs.

D. The JVM is specific to the operating system but bytecode is platform-independent.

E. Both B and D are correct.

---

**21.** What is the output of the following code?

```java
public class Imports {
    public static void main(String[] args) {
        java.util.ArrayList<String> list = new java.util.ArrayList<>();
        list.add("Java");
        System.out.println(list.get(0));
    }
}
```

A. `Java`

B. The code does not compile because `ArrayList` is not imported.

C. `null`

D. An `IndexOutOfBoundsException` is thrown.

E. The code does not compile because of the diamond operator.

---

**22.** Given the following code, which options would each independently make it compile?

```java
import java.util.*;
import java.sql.*;
public class Ambiguous {
    Date d;
}
```

A. Remove `import java.util.*;`

B. Remove `import java.sql.*;`

C. Change `Date d;` to `java.util.Date d;`

D. Change `Date d;` to `java.sql.Date d;`

E. Options A, B, C, and D are all valid independent fixes.

F. Options C and D are valid independent fixes; A and B are also valid independent fixes.

---

**23.** Which of the following correctly declares a `long` literal? (Choose all that apply.)

A. `long x = 1234567890;`

B. `long x = 1234567890l;`

C. `long x = 1234567890L;`

D. `long x = 1_234_567_890L;`

E. `long x = 1234567890.0L;`

---

**24.** What is the output of the following code?

```java
1: public class Order {
2:     public static void main(String[] args) {
3:         int i = 0;
4:         int[] arr = new int[3];
5:         arr[i] = ++i;
6:         System.out.println(arr[0] + " " + arr[1]);
7:     }
8: }
```

A. `0 0`

B. `0 1`

C. `1 0`

D. `1 1`

E. The code does not compile.

---

**25.** An object becomes eligible for garbage collection when:

A. The programmer calls `System.gc()`.

B. There are no more reachable references pointing to it.

C. Its enclosing method's scope ends.

D. Its `finalize()` method has been called.

E. The JVM decides it should be collected.

---

**26.** Which of the following is the correct required order of elements in a Java source file?

A. imports → package → class

B. class → package → imports

C. package → imports → class

D. imports → class → package

E. package → class → imports

---

**27.** What is printed?

```java
public class Char {
    public static void main(String[] args) {
        char c = 'A';
        c += 1;
        System.out.println(c);
    }
}
```

A. `A`

B. `B`

C. `66`

D. The code does not compile.

E. `A1`

---

**28.** Which of the following ranges correctly describes `int` and `long` respectively?

A. `-2^15` to `2^15 - 1` and `-2^31` to `2^31 - 1`

B. `-2^31` to `2^31 - 1` and `-2^63` to `2^63 - 1`

C. `-2^31` to `2^31` and `-2^63` to `2^63`

D. `-2^32` to `2^32 - 1` and `-2^64` to `2^64 - 1`

E. 0 to `2^32 - 1` and 0 to `2^64 - 1`

---

**29.** What is the output of this code?

```java
public class Casting {
    public static void main(String[] args) {
        double d = 9.99;
        int i = (int) d;
        System.out.println(i);
    }
}
```

A. `10`

B. `9`

C. `9.99`

D. The code does not compile without an explicit cast.

E. An exception is thrown at runtime.

---

**30.** Which of the following statements about packages are true? (Choose all that apply.)

A. A class without a package declaration is in the default package.

B. The default package can be imported using `import default.*;`

C. Classes in the same package can access each other without an import statement.

D. The package statement must be the first non-comment, non-blank line in a file.

E. A file can have multiple package declarations as long as they specify the same package.

---

**31.** Given the following code, which line(s) cause a compiler error? (Choose all that apply.)

```java
1: public class Variables {
2:     public static void main(String[] args) {
3:         int x = 1;
4:         int y;
5:         int z = x + y;
6:         System.out.println(z);
7:     }
8: }
```

A. Line 3

B. Line 4

C. Line 5

D. Line 6

E. None of the above.

---

**32.** What is the range of a `byte` in Java?

A. 0 to 255

B. -128 to 127

C. -127 to 128

D. -256 to 255

E. -32768 to 32767

---

**33.** Which of the following correctly initializes a `char` variable? (Choose all that apply.)

A. `char c = 'A';`

B. `char c = 65;`

C. `char c = 'A';`

D. `char c = "A";`

E. `char c = (char) -1;`

---

**34.** What happens when you compile and run the following code?

```java
// File: TwoClasses.java
public class TwoClasses {
    class Inner {}
}
class Outer {}
```

A. Compiler error — you cannot have two top-level types in one file.

B. Compiler error — the filename must match `Outer` since it appears last.

C. Compiles and runs successfully; two `.class` files are produced.

D. Compiler error — `Inner` is not allowed inside `TwoClasses`.

E. Compiles but throws a runtime exception.

---

**35.** How many times does a call to `System.gc()` guarantee garbage collection?

A. Once per call.

B. Zero — it is only a suggestion to the JVM.

C. Immediately and exactly once.

D. Only when the heap is more than 75% full.

E. It depends on the GC algorithm in use.

---

**36.** What is the output of the following?

```java
public class Wrapper {
    public static void main(String[] args) {
        Integer x = 5;
        int y = x;
        System.out.println(x == y);
    }
}
```

A. `true`

B. `false`

C. The code does not compile.

D. A `NullPointerException` is thrown.

E. Unpredictable — depends on the JVM implementation.

---

**37.** Which of the following are primitive types in Java? (Choose all that apply.)

A. `int`

B. `Integer`

C. `boolean`

D. `Boolean`

E. `char`

F. `String`

G. `double`

---

**38.** What is the output of the following?

```java
public class Str {
    public static void main(String[] args) {
        String s = null;
        System.out.println("Value: " + s);
    }
}
```

A. `Value: null`

B. `Value:`

C. The code does not compile.

D. A `NullPointerException` is thrown.

E. `null`

---

**39.** Which of the following are correct default values for instance variables? (Choose all that apply.)

A. `int` defaults to `0`

B. `double` defaults to `0.0`

C. `boolean` defaults to `false`

D. `char` defaults to `' '` (a space character)

E. `String` defaults to `""`

F. Object references default to `null`

---

**40.** What is the result of the following?

```java
public class Max {
    public static void main(String[] args) {
        int max = Integer.MAX_VALUE;
        System.out.println(max + 1);
    }
}
```

A. `Integer.MAX_VALUE` (unchanged)

B. An `ArithmeticException` is thrown.

C. The code does not compile.

D. `Integer.MIN_VALUE`

E. `2147483648`

---

**41.** Given:

```java
// File: Animal.java
package zoo;
public class Animal {}
class Helper {}
```

Which statement is true?

A. This code does not compile — only one class is allowed per file.

B. `Helper` can be used from another package by importing `zoo.Helper`.

C. `Helper` is package-private and accessible only within the `zoo` package.

D. Both `Animal` and `Helper` must be `public` for the code to compile.

E. The file must be named `Helper.java` because `Helper` appears last.

---

**42.** Which is the correct syntax to import static members of a class?

A. `import static java.lang.Math.PI;`

B. `static import java.lang.Math.PI;`

C. `import java.lang.Math.PI;`

D. `import static java.lang.Math.*;`

E. Both A and D

---

**43.** What is the output of the following?

```java
public class Octal {
    public static void main(String[] args) {
        int x = 010;
        System.out.println(x);
    }
}
```

A. `10`

B. `8`

C. `2`

D. The code does not compile.

E. `010`

---

**44.** Which of the following assignments are examples of widening primitive conversion? (Choose all that apply.)

A. Assigning a `long` to an `int`

B. Assigning an `int` to a `long`

C. Assigning a `float` to a `double`

D. Assigning a `double` to a `float`

E. Assigning a `byte` to a `short`

---

**45.** What is the output of the following?

```java
public class Local {
    public static void main(String[] args) {
        int x;
        if (true) {
            x = 10;
        }
        System.out.println(x);
    }
}
```

A. `10`

B. `0`

C. The code does not compile.

D. A `RuntimeException` is thrown.

E. The output is unpredictable.

---

**46.** Object `o` is referenced by both variables `a` and `b`. After `a = null;` executes, is `o` eligible for garbage collection?

A. Yes, because `a` is set to null.

B. No, because `b` still holds a reference to `o`.

C. Yes, because local variable `a` went out of scope.

D. No, because `System.gc()` has not been called.

E. It depends on the JVM implementation.

---

**47.** Which of the following create a `String` that uses the string pool? (Choose all that apply.)

A. `String s = "hello";`

B. `String s = new String("hello");`

C. `String s = "hel" + "lo";`

D. `String s = new String("hel") + "lo";`

E. `String s = String.intern("hello");`

---

**48.** What is the output of the following code?

```java
public class Binary {
    public static void main(String[] args) {
        int x = 0b1010;
        System.out.println(x);
    }
}
```

A. `0b1010`

B. `1010`

C. `10`

D. The code does not compile.

E. `8`

---

**49.** Which of the following statements about `var` are true? (Choose all that apply.)

A. `var` can be used as a class name.

B. `var` can be used as a method name.

C. `var` can be used as a variable name.

D. `var` is a reserved type name but not a reserved keyword.

E. `var x = 1, y = 2;` is a valid multi-variable declaration.

---

**50.** What is the output of the following?

```java
public class MultiDecl {
    public static void main(String[] args) {
        int a, b, c;
        a = b = c = 10;
        System.out.println(a + " " + b + " " + c);
    }
}
```

A. The code does not compile.

B. `10 10 10`

C. `0 0 10`

D. A `RuntimeException` is thrown.

E. `10 0 0`
