# Chapter 5: Methods — Practice Questions

---

**1.** Which of the following are valid method declarations? (Choose all that apply.)

A. `public void method() {}`

B. `void public method() {}`

C. `static public final void method() {}`

D. `public static void method() {}`

E. `final static public void method() {}`

F. `void method() static {}`

---

**2.** What is the output of the following?

```java
public class Pass {
    static void change(int x) {
        x = 99;
    }
    public static void main(String[] args) {
        int a = 5;
        change(a);
        System.out.println(a);
    }
}
```

A. `99`

B. `5`

C. `0`

D. The code does not compile.

E. A `RuntimeException` is thrown.

---

**3.** What is the output of the following?

```java
public class PassRef {
    static void append(StringBuilder sb) {
        sb.append(" World");
    }
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder("Hello");
        append(sb);
        System.out.println(sb);
    }
}
```

A. `Hello`

B. `Hello World`

C. `World`

D. The code does not compile.

E. A `NullPointerException` is thrown.

---

**4.** Which access modifier allows access from subclasses in a different package?

A. `private`

B. Package-private (no modifier)

C. `protected`

D. `public`

E. Both C and D

---

**5.** What is the output of the following?

```java
public class Varargs {
    static int sum(int... nums) {
        int total = 0;
        for (int n : nums) total += n;
        return total;
    }
    public static void main(String[] args) {
        System.out.println(sum(1, 2, 3));
        System.out.println(sum());
    }
}
```

A. `6` then `0`

B. `6` then `null`

C. `0` then `6`

D. The code does not compile.

E. `6` then throws an exception.

---

**6.** Which of the following are valid varargs declarations? (Choose all that apply.)

A. `void method(int... nums)`

B. `void method(int... nums, String s)`

C. `void method(String s, int... nums)`

D. `void method(int[]... arrs)`

E. `void method(int... a, int... b)`

---

**7.** What is the output of the following?

```java
public class Overload {
    static void print(int x)    { System.out.println("int: " + x);    }
    static void print(double x) { System.out.println("double: " + x); }
    public static void main(String[] args) {
        print(5);
        print(5.0);
        print(5L);
    }
}
```

A. `int: 5` then `double: 5.0` then `int: 5`

B. `int: 5` then `double: 5.0` then `double: 5.0`

C. `double: 5.0` then `double: 5.0` then `double: 5.0`

D. The code does not compile.

E. `int: 5` then `double: 5.0` then a compile error on `print(5L)`

---

**8.** Which elements make up a method signature in Java?

A. Method name and return type

B. Method name and parameter types

C. Method name, return type, and parameter types

D. Method name, return type, parameter types, and access modifier

E. Method name, parameter types, and throws clause

---

**9.** What is the output of the following?

```java
public class Static {
    static int count = 0;
    static void increment() { count++; }
    public static void main(String[] args) {
        Static s1 = new Static();
        Static s2 = new Static();
        s1.increment();
        s1.increment();
        System.out.println(s2.count);
    }
}
```

A. `0`

B. `1`

C. `2`

D. The code does not compile.

E. A `NullPointerException` is thrown.

---

**10.** What is the output of the following?

```java
public class Scope {
    private int x = 10;
    void print() {
        int x = 20;
        System.out.println(x);
        System.out.println(this.x);
    }
    public static void main(String[] args) {
        new Scope().print();
    }
}
```

A. `10` then `10`

B. `20` then `20`

C. `20` then `10`

D. `10` then `20`

E. The code does not compile.

---

**11.** Which of the following correctly demonstrates method overloading? (Choose all that apply.)

A. Same name, different number of parameters

B. Same name, different parameter types

C. Same name, different parameter order when types differ

D. Same name, different return type only

E. Same name, different access modifier only

---

**12.** What is the output of the following?

```java
public class Initializer {
    static int x;
    static {
        x = 10;
        System.out.print("static ");
    }
    {
        System.out.print("instance ");
    }
    public static void main(String[] args) {
        System.out.print("main ");
        new Initializer();
        new Initializer();
    }
}
```

A. `static main instance instance`

B. `main static instance instance`

C. `static instance instance main`

D. `static main`

E. The code does not compile.

---

**13.** What is the output of the following?

```java
public class AutoBox {
    static void print(Integer i) { System.out.println("Integer: " + i); }
    static void print(long l)    { System.out.println("long: " + l);    }
    public static void main(String[] args) {
        int x = 5;
        print(x);
    }
}
```

A. `Integer: 5`

B. `long: 5`

C. The code does not compile — ambiguous method call.

D. The code does not compile — no matching method.

E. `Integer: 5` or `long: 5` depending on JVM implementation.

---

**14.** Which of the following can access `private` members of a class? (Choose all that apply.)

A. Methods in the same class

B. Methods in a subclass

C. Methods in the same package

D. Methods in a different package that have a reference to the object

E. Static methods in the same class

---

**15.** What is the output of the following?

```java
public class PassObj {
    static void reassign(StringBuilder sb) {
        sb = new StringBuilder("changed");
    }
    public static void main(String[] args) {
        StringBuilder s = new StringBuilder("original");
        reassign(s);
        System.out.println(s);
    }
}
```

A. `changed`

B. `original`

C. `null`

D. The code does not compile.

E. A `NullPointerException` is thrown.

---

**16.** What is the output of the following?

```java
public class VarargsArr {
    static void show(int... nums) {
        System.out.println(nums.length);
    }
    public static void main(String[] args) {
        show(new int[]{1, 2, 3});
        show(1, 2, 3);
    }
}
```

A. `3` then `3`

B. `1` then `3`

C. `3` then `1`

D. The code does not compile.

E. `0` then `3`

---

**17.** What is the output of the following?

```java
public class StaticMethod {
    int x = 5;
    static void test() {
        System.out.println(x);   // line 4
    }
    public static void main(String[] args) {
        test();
    }
}
```

A. `5`

B. `0`

C. The code does not compile — line 4 references an instance variable from a static context.

D. A `NullPointerException` is thrown.

E. `null`

---

**18.** Which of the following correctly imports a static member? (Choose all that apply.)

A. `import static java.lang.Math.PI;`

B. `import static java.lang.Math.*;`

C. `import java.lang.Math.PI;`

D. `static import java.lang.Math.PI;`

E. `import java.lang.Math static PI;`

---

**19.** What is the output of the following?

```java
public class Default {
    int x;
    public static void main(String[] args) {
        Default d = new Default();
        System.out.println(d.x);
    }
}
```

A. `null`

B. `0`

C. The code does not compile — `x` was never initialized.

D. A `NullPointerException` is thrown.

E. Unpredictable.

---

**20.** Which of the following method declarations would NOT compile? (Choose all that apply.)

A. `public abstract void method();`

B. `public abstract void method() {}`

C. `public final abstract void method();`

D. `public native void method();`

E. `public static abstract void method();`

---

**21.** What is the output of the following?

```java
public class Overload2 {
    static String test(Object o) { return "Object";  }
    static String test(String s) { return "String";  }
    public static void main(String[] args) {
        System.out.println(test("hello"));
        System.out.println(test(null));
    }
}
```

A. `Object` then `Object`

B. `String` then `Object`

C. `String` then `String`

D. `String` then ambiguous compile error

E. The code does not compile.

---

**22.** What is the output of the following?

```java
public class Instance {
    int x = 5;
    Instance() { x = 10; }
    {
        x = 7;
    }
    public static void main(String[] args) {
        System.out.println(new Instance().x);
    }
}
```

A. `5`

B. `7`

C. `10`

D. The code does not compile.

E. `0`

---

**23.** What is the output of the following?

```java
public class Widen {
    static void print(long x) { System.out.println("long");  }
    static void print(int x)  { System.out.println("int");   }
    public static void main(String[] args) {
        byte b = 5;
        print(b);
    }
}
```

A. `long`

B. `int`

C. The code does not compile — ambiguous method call.

D. The code does not compile — no matching method.

E. `byte`

---

**24.** What is the output of the following?

```java
public class StaticBlock {
    static int x = initX();
    static int initX() {
        System.out.print("init ");
        return 5;
    }
    public static void main(String[] args) {
        System.out.print("main");
    }
}
```

A. `main`

B. `init main`

C. `main init`

D. The code does not compile.

E. `init`

---

**25.** Which of the following statements about `static` methods are true? (Choose all that apply.)

A. A `static` method can access instance variables directly.

B. A `static` method can call instance methods directly.

C. A `static` method can be called without creating an instance of the class.

D. A `static` method can access other `static` variables and methods.

E. A `static` method can be overridden in a subclass.

---

**26.** What is the output of the following?

```java
public class VarargOverload {
    static void go(int x, int y)  { System.out.println("two ints");  }
    static void go(int... nums)   { System.out.println("varargs");   }
    public static void main(String[] args) {
        go(1, 2);
    }
}
```

A. `varargs`

B. `two ints`

C. The code does not compile — ambiguous method call.

D. The code does not compile — varargs clash.

E. `two ints` and `varargs` are both printed.

---

**27.** What is the output of the following?

```java
public class AccessDemo {
    private static int x = 5;
    static int getX() { return x; }
    public static void main(String[] args) {
        System.out.println(AccessDemo.getX());
    }
}
```

A. `5`

B. `0`

C. The code does not compile — `x` is private.

D. A `NullPointerException` is thrown.

E. The code does not compile — static method cannot access static field.

---

**28.** Which of the following are true about method parameters in Java? (Choose all that apply.)

A. Primitive parameters are passed by value.

B. Object references are passed by value (the reference is copied).

C. Changes to the reference inside the method affect the original variable.

D. Changes to the object's state via the reference are visible to the caller.

E. Object parameters are passed by reference.

---

**29.** What is the output of the following?

```java
public class MultiReturn {
    static int compute(int x) {
        if (x > 0) return x * 2;
        return -1;
    }
    public static void main(String[] args) {
        System.out.println(compute(5));
        System.out.println(compute(-3));
    }
}
```

A. `10` then `6`

B. `10` then `-1`

C. `-1` then `-1`

D. The code does not compile — multiple return statements.

E. `5` then `-3`

---

**30.** What is the output of the following?

```java
public class Overload3 {
    static void go(int x)    { System.out.println("int");    }
    static void go(Integer x){ System.out.println("Integer");}
    public static void main(String[] args) {
        go(5);
    }
}
```

A. `int`

B. `Integer`

C. The code does not compile — ambiguous call.

D. Depends on the JVM.

E. `int` then `Integer`

---

**31.** Which of the following method signatures constitute overloading (not a compile error)? (Choose all that apply.)

A. `void go(int x)` and `void go(int y)`

B. `void go(int x)` and `int go(int x)`

C. `void go(int x, String s)` and `void go(String s, int x)`

D. `void go(int x)` and `void go(long x)`

E. `void go(int... x)` and `void go(int[] x)`

---

**32.** What is the output of the following?

```java
public class FinalParam {
    static void print(final int x) {
        x = 10;   // line 3
        System.out.println(x);
    }
    public static void main(String[] args) {
        print(5);
    }
}
```

A. `5`

B. `10`

C. The code does not compile — line 3 reassigns a `final` parameter.

D. A `RuntimeException` is thrown.

E. `0`

---

**33.** What is the output of the following?

```java
public class InstanceInit {
    int a = 1;
    {
        a = 2;
    }
    int b = a + 1;
    public static void main(String[] args) {
        InstanceInit obj = new InstanceInit();
        System.out.println(obj.a + " " + obj.b);
    }
}
```

A. `1 2`

B. `2 3`

C. `2 2`

D. The code does not compile.

E. `1 3`

---

**34.** What happens when a `void` method contains a `return;` statement?

A. Compile error — `return` is only valid in methods with a return type.

B. The method exits immediately at that point.

C. The `return;` is ignored and execution continues.

D. A `ReturnException` is thrown.

E. The compiler removes the statement automatically.

---

**35.** What is the output of the following?

```java
public class StaticAccess {
    static int x = 10;
    public static void main(String[] args) {
        StaticAccess obj = null;
        System.out.println(obj.x);
    }
}
```

A. `10`

B. `0`

C. A `NullPointerException` is thrown.

D. The code does not compile.

E. `null`

---

**36.** Which of the following are true about the order of initialization? (Choose all that apply.)

A. Static fields and blocks execute in the order they appear, before any instance is created.

B. Instance initializers run before the constructor body.

C. The constructor body runs before instance initializers.

D. Static blocks run each time a new object is created.

E. Instance fields are initialized to their default values before any initializer runs.

---

**37.** What is the output of the following?

```java
public class Args {
    public static void main(String[] args) {
        System.out.println(args.length);
    }
}
```

The program is run with: `java Args`

A. `0`

B. `1`

C. `null`

D. The code does not compile.

E. An `ArrayIndexOutOfBoundsException` is thrown.

---

**38.** Which of the following describes `protected` access correctly?

A. Accessible only within the same class.

B. Accessible within the same package only.

C. Accessible within the same package AND from subclasses, even in different packages.

D. Accessible from any class anywhere.

E. Accessible within the same package AND subclasses, but only through inheritance (not direct reference in different packages).

---

**39.** What is the output of the following?

```java
public class CallStatic {
    static int x = 5;
    public static void main(String[] args) {
        modify();
        System.out.println(x);
    }
    static void modify() {
        x = 20;
    }
}
```

A. `5`

B. `20`

C. `0`

D. The code does not compile.

E. A `RuntimeException` is thrown.

---

**40.** Which of the following would cause a compile error related to overloading? (Choose all that apply.)

A. Two methods with the same name and same parameter types but different return types.

B. Two methods with the same name and different parameter types.

C. Two methods with the same name and same parameter count but different parameter names.

D. Two methods with the same name and same erasure after generics are applied.

E. Two methods with same name, same parameters, different `throws` declarations.

---

**41.** What is the output of the following?

```java
public class Chain {
    static int doubleIt(int x) { return x * 2; }
    static int addTen(int x)   { return x + 10; }
    public static void main(String[] args) {
        System.out.println(addTen(doubleIt(5)));
    }
}
```

A. `20`

B. `30`

C. `25`

D. `15`

E. The code does not compile.

---

**42.** What is the output of the following?

```java
public class VarargsNull {
    static void print(String... args) {
        System.out.println(args == null ? "null" : args.length);
    }
    public static void main(String[] args) {
        print((String[]) null);
        print((String)  null);
    }
}
```

A. `null` then `1`

B. `0` then `1`

C. `null` then `null`

D. The code does not compile.

E. `1` then `1`

---

**43.** What is the output of the following?

```java
public class Protected {
    protected static int value = 42;
    public static void main(String[] args) {
        System.out.println(value);
    }
}
```

A. `42`

B. `0`

C. The code does not compile — `protected` members cannot be `static`.

D. The code does not compile — `protected` cannot be accessed from `main`.

E. A `SecurityException` is thrown.

---

**44.** Which of the following are true about `static` imports? (Choose all that apply.)

A. `import static java.lang.Math.*;` allows using `PI` without `Math.`.

B. `import static java.lang.Math.*;` allows using `Math.sqrt()` without the class prefix.

C. `import static java.lang.Math.*;` imports the class `Math` itself.

D. Static imports can cause ambiguity if the same member name exists in two imported classes.

E. Static imports work for constants and methods, but not for inner classes.

---

**45.** What is the output of the following?

```java
public class Overload4 {
    static void go(Object o) { System.out.println("Object"); }
    static void go(int... x) { System.out.println("varargs");}
    public static void main(String[] args) {
        go(5);
    }
}
```

A. `Object`

B. `varargs`

C. The code does not compile — ambiguous method call.

D. Depends on the JVM.

E. `Object` then `varargs`

---

**46.** What is the output of the following?

```java
public class ReturnVoid {
    static void print(int x) {
        if (x < 0) return;
        System.out.println(x);
    }
    public static void main(String[] args) {
        print(5);
        print(-1);
        print(3);
    }
}
```

A. `5` then `3`

B. `5` then `-1` then `3`

C. `5`

D. The code does not compile.

E. `5` then `3` then `-1`

---

**47.** What is the output of the following?

```java
public class PackagePrivate {
    int x = 10;
    public static void main(String[] args) {
        PackagePrivate obj = new PackagePrivate();
        System.out.println(obj.x);
    }
}
```

A. `10`

B. `0`

C. The code does not compile — `x` has no access modifier and cannot be accessed.

D. A `SecurityException` is thrown.

E. `null`

---

**48.** Which of the following is the correct method overloading resolution order in Java?

A. Exact match → widening → autoboxing → varargs

B. Autoboxing → widening → varargs → exact match

C. Exact match → autoboxing → widening → varargs

D. Varargs → widening → autoboxing → exact match

E. Exact match → varargs → widening → autoboxing

---

**49.** What is the output of the following?

```java
public class TwoInit {
    static int x = 1;
    static { x = 2; }
    static { x = 3; }
    public static void main(String[] args) {
        System.out.println(x);
    }
}
```

A. `1`

B. `2`

C. `3`

D. The code does not compile — multiple static blocks are not allowed.

E. Unpredictable.

---

**50.** What is the output of the following?

```java
public class Overload5 {
    static void method(int a, double b) { System.out.println("int, double"); }
    static void method(double a, int b) { System.out.println("double, int"); }
    public static void main(String[] args) {
        method(1, 2);
    }
}
```

A. `int, double`

B. `double, int`

C. The code does not compile — ambiguous method call.

D. `int, double` then `double, int`

E. Depends on the JVM implementation.
