# Chapter 9: Collections and Generics — Practice Questions

---

**1.** What is the output of the following code?

```java
import java.util.*;

public class Demo {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("a", "b", "c");
        list.set(1, "B");
        list.add("d");
        System.out.println(list);
    }
}
```

A. `[a, B, c, d]`

B. `[a, B, c]`

C. A `UnsupportedOperationException` is thrown at `list.set(1, "B")`.

D. A `UnsupportedOperationException` is thrown at `list.add("d")`.

E. The code does not compile.

---

**2.** Which statements about `List.of()` and `Arrays.asList()` are correct? (Choose all that apply.)

A. Both return a fixed-size list that throws `UnsupportedOperationException` on `add()`.

B. `Arrays.asList()` allows `set()` but `List.of()` does not.

C. Both allow `null` elements.

D. `List.of()` does not allow `null` elements; `Arrays.asList()` does allow `null` elements.

E. `Arrays.asList()` returns a list backed by the original array.

F. `List.of()` guarantees insertion-order iteration.

---

**3.** What is the output of the following code?

```java
import java.util.*;

public class Demo {
    public static void main(String[] args) {
        Set<String> set = new TreeSet<>();
        set.add("banana");
        set.add("apple");
        set.add("cherry");
        set.add("apple");
        System.out.println(set.size() + " " + set.iterator().next());
    }
}
```

A. `4 banana`

B. `3 banana`

C. `3 apple`

D. `4 apple`

E. The code does not compile.

---

**4.** What is the output of the following code?

```java
import java.util.*;

public class Demo {
    public static void main(String[] args) {
        TreeSet<String> set = new TreeSet<>();
        set.add("dog");
        set.add(null);
        System.out.println(set);
    }
}
```

A. `[null, dog]`

B. `[dog, null]`

C. A `NullPointerException` is thrown at runtime.

D. The code does not compile.

E. `[dog]`

---

**5.** Which of the following correctly describes the behavior of `Queue` methods? (Choose all that apply.)

A. `offer()` throws `NoSuchElementException` if the queue is full.

B. `poll()` returns `null` if the queue is empty.

C. `remove()` throws `NoSuchElementException` if the queue is empty.

D. `peek()` returns `null` if the queue is empty.

E. `element()` returns `null` if the queue is empty.

F. `add()` returns `false` if the queue is full.

---

**6.** What is the output of the following code?

```java
import java.util.*;

public class Demo {
    public static void main(String[] args) {
        Deque<Integer> deque = new ArrayDeque<>();
        deque.push(1);
        deque.push(2);
        deque.push(3);
        System.out.println(deque.pop() + " " + deque.peek());
    }
}
```

A. `1 2`

B. `3 2`

C. `1 3`

D. `3 1`

E. The code does not compile.

---

**7.** Which of the following will compile without error? (Choose all that apply.)

A. `List<int> list = new ArrayList<>();`

B. `List<Integer> list = new ArrayList<>();`

C. `List<?> list = new ArrayList<String>();`

D. `List<Object> list = new ArrayList<String>();`

E. `List<? extends Number> list = new ArrayList<Integer>();`

F. `List<Number> list = new ArrayList<Integer>();`

---

**8.** What is the output of the following code?

```java
import java.util.*;

public class Demo {
    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>();
        map.put("a", 1);
        map.put("b", 2);
        map.put("a", 3);
        System.out.println(map.size() + " " + map.get("a"));
    }
}
```

A. `3 1`

B. `3 3`

C. `2 1`

D. `2 3`

E. The code does not compile.

---

**9.** Which statement about `HashMap` vs `TreeMap` regarding null keys is correct?

A. Both allow one null key.

B. Neither allows null keys.

C. `HashMap` allows one null key; `TreeMap` throws `NullPointerException` when a null key is used with natural ordering.

D. `TreeMap` allows null keys; `HashMap` does not.

E. `HashMap` allows multiple null keys.

---

**10.** What is the output of the following code?

```java
import java.util.*;

public class Demo {
    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>();
        map.put("x", 10);
        int val = map.getOrDefault("y", 99);
        System.out.println(val);
    }
}
```

A. `0`

B. `10`

C. `99`

D. A `NullPointerException` is thrown.

E. The code does not compile.

---

**11.** What is the output of the following code?

```java
import java.util.*;

public class Demo {
    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>();
        map.put("a", 1);
        map.putIfAbsent("a", 99);
        map.putIfAbsent("b", 5);
        System.out.println(map.get("a") + " " + map.get("b"));
    }
}
```

A. `99 5`

B. `1 5`

C. `1 null`

D. `99 null`

E. The code does not compile.

---

**12.** What does the following method return?

```java
import java.util.*;

public class Demo {
    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>();
        map.put("a", 1);
        map.compute("a", (k, v) -> v == null ? 1 : v + 10);
        map.compute("b", (k, v) -> v == null ? 1 : v + 10);
        System.out.println(map.get("a") + " " + map.get("b"));
    }
}
```

A. `11 1`

B. `1 1`

C. `11 null`

D. `1 null`

E. The code does not compile.

---

**13.** What is the output of the following code?

```java
import java.util.*;

public class Demo {
    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>();
        map.put("key", 5);
        map.compute("key", (k, v) -> null);
        System.out.println(map.containsKey("key"));
    }
}
```

A. `true`

B. `false`

C. A `NullPointerException` is thrown.

D. The code does not compile.

---

**14.** What is the output of the following code?

```java
import java.util.*;

public class Demo {
    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>();
        map.put("a", 3);
        map.merge("a", 2, Integer::sum);
        map.merge("b", 2, Integer::sum);
        System.out.println(map.get("a") + " " + map.get("b"));
    }
}
```

A. `3 2`

B. `5 2`

C. `5 null`

D. `3 null`

E. The code does not compile.

---

**15.** What happens when `merge()` is called and the merge function returns `null`?

A. The entry is updated to `null`.

B. The entry is removed from the map.

C. A `NullPointerException` is thrown.

D. The original value is kept.

E. The behavior is undefined.

---

**16.** Which of the following correctly describes the PECS principle? (Choose all that apply.)

A. `? extends T` is used when the collection is a producer (you read from it).

B. `? super T` is used when the collection is a consumer (you write to it).

C. You can add elements to a `List<? extends Number>`.

D. You can read elements as type `Number` from a `List<? extends Number>`.

E. You can read elements as type `Object` from a `List<? super Integer>`.

F. You can add `Integer` elements to a `List<? super Integer>`.

---

**17.** What is the output of the following code?

```java
import java.util.*;

public class Demo {
    static void addNumbers(List<? super Integer> list) {
        list.add(42);
    }

    public static void main(String[] args) {
        List<Number> numbers = new ArrayList<>();
        addNumbers(numbers);
        System.out.println(numbers.get(0));
    }
}
```

A. `42`

B. The code does not compile.

C. A `ClassCastException` is thrown.

D. A `NullPointerException` is thrown.

---

**18.** What is the output of the following code?

```java
import java.util.*;

public class Demo {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>(List.of("c", "a", "b"));
        Collections.sort(list);
        System.out.println(list);
    }
}
```

A. `[c, a, b]`

B. `[a, b, c]`

C. `[b, a, c]`

D. The code does not compile.

E. A `UnsupportedOperationException` is thrown.

---

**19.** Which of the following will cause a compilation error? (Choose all that apply.)

A. `new T[]` inside a generic method where `T` is a type parameter.

B. `List<String> list = new ArrayList<String>();`

C. `List<String> list = new ArrayList<>();`

D. `new ArrayList<int>()`

E. `List<? extends Object> list = new ArrayList<String>();`

F. `List<String>[] arr = new ArrayList[5];`

---

**20.** What is the output of the following code?

```java
import java.util.*;

public class Demo {
    public static void main(String[] args) {
        Comparator<String> comp = Comparator.comparing(String::length)
                                            .thenComparing(Comparator.naturalOrder());
        List<String> list = new ArrayList<>(List.of("bb", "aaa", "a", "cc"));
        list.sort(comp);
        System.out.println(list);
    }
}
```

A. `[a, bb, cc, aaa]`

B. `[aaa, bb, cc, a]`

C. `[a, bb, aaa, cc]`

D. `[a, cc, bb, aaa]`

E. The code does not compile.

---

**21.** What is the output of the following code?

```java
import java.util.*;

public class Demo {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>(Arrays.asList(3, 1, 4, 1, 5));
        list.remove(1);
        System.out.println(list);
    }
}
```

A. `[3, 4, 1, 5]`

B. `[3, 1, 4, 5]`

C. `[3, 1, 4, 1, 5]`

D. `[1, 4, 1, 5]`

E. The code does not compile.

---

**22.** What is the output of the following code?

```java
import java.util.*;

public class Demo {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>(Arrays.asList(3, 1, 4, 1, 5));
        list.remove(Integer.valueOf(1));
        System.out.println(list);
    }
}
```

A. `[3, 4, 1, 5]`

B. `[3, 1, 4, 5]`

C. `[3, 4, 5]`

D. `[3, 1, 4, 1, 5]`

E. The code does not compile.

---

**23.** Which `NavigableSet` method returns the greatest element strictly less than the given element?

A. `floor(e)`

B. `lower(e)`

C. `ceiling(e)`

D. `higher(e)`

E. `headSet(e)`

---

**24.** What is the output of the following code?

```java
import java.util.*;

public class Demo {
    public static void main(String[] args) {
        TreeSet<Integer> set = new TreeSet<>(Set.of(1, 5, 3, 7, 9));
        System.out.println(set.floor(6) + " " + set.ceiling(6));
    }
}
```

A. `5 7`

B. `6 6`

C. `5 5`

D. `7 7`

E. `null null`

---

**25.** What is the output of the following code?

```java
import java.util.*;

public class Demo {
    public static void main(String[] args) {
        LinkedList<String> ll = new LinkedList<>();
        ll.add("a");
        ll.add("b");
        ll.add("c");
        ll.addFirst("z");
        System.out.println(ll.peekLast() + " " + ll.size());
    }
}
```

A. `z 4`

B. `c 4`

C. `a 3`

D. `z 3`

E. The code does not compile.

---

**26.** Which of the following statements about `Map` are correct? (Choose all that apply.)

A. `Map` extends `Collection`.

B. `Map.of()` does not allow null keys or null values.

C. `Map.of()` does not allow duplicate keys.

D. `HashMap` maintains insertion order.

E. `LinkedHashMap` maintains insertion order.

F. `TreeMap` iterates keys in sorted order.

---

**27.** What is the output of the following code?

```java
import java.util.*;

public class Demo {
    public static void main(String[] args) {
        Map<String, List<Integer>> map = new HashMap<>();
        map.computeIfAbsent("key", k -> new ArrayList<>()).add(1);
        map.computeIfAbsent("key", k -> new ArrayList<>()).add(2);
        System.out.println(map.get("key"));
    }
}
```

A. `[1]`

B. `[2]`

C. `[1, 2]`

D. `[]`

E. The code does not compile.

---

**28.** What is the output of the following code?

```java
import java.util.*;

public class Demo {
    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>();
        map.put("a", 1);
        map.computeIfPresent("a", (k, v) -> v * 2);
        map.computeIfPresent("b", (k, v) -> v * 2);
        System.out.println(map.get("a") + " " + map.get("b"));
    }
}
```

A. `2 null`

B. `1 null`

C. `2 0`

D. The code does not compile.

---

**29.** Which of the following code snippets correctly defines a generic method that accepts a list of any type and prints its elements?

A. `public void print(List<Object> list)`

B. `public void print(List<?> list)`

C. `public <T> void print(List<T> list)`

D. `public void print(List list)`

E. Both B and C are correct.

---

**30.** What is the output of the following code?

```java
import java.util.*;

public class Demo {
    public static void main(String[] args) {
        List<String> original = new ArrayList<>(List.of("x", "y"));
        List<String> copy = List.copyOf(original);
        original.add("z");
        System.out.println(copy.size());
    }
}
```

A. `2`

B. `3`

C. A `UnsupportedOperationException` is thrown.

D. The code does not compile.

---

**31.** What is the output of the following code?

```java
import java.util.*;

public class Demo {
    public static void main(String[] args) {
        List<String> list = Collections.singletonList("hello");
        list.set(0, "world");
        System.out.println(list.get(0));
    }
}
```

A. `hello`

B. `world`

C. A `UnsupportedOperationException` is thrown.

D. The code does not compile.

---

**32.** What is the result of the following code? (Choose all that apply.)

```java
import java.util.*;

public class Demo {
    public static void main(String[] args) {
        Set<Integer> s1 = new HashSet<>(Arrays.asList(1, 2, 3, 4));
        Set<Integer> s2 = new HashSet<>(Arrays.asList(3, 4, 5, 6));
        s1.retainAll(s2);
        System.out.println(s1);
    }
}
```

A. `[1, 2, 3, 4]`

B. `[3, 4]`

C. `[1, 2, 5, 6]`

D. The output contains only elements that were in both sets.

E. `[3, 4, 5, 6]`

---

**33.** Which of the following is true about type erasure in Java? (Choose all that apply.)

A. `List<String>` and `List<Integer>` are the same type at runtime.

B. You can check `if (list instanceof List<String>)` at runtime.

C. Generic type information is erased at compile time and replaced with `Object` or bounds.

D. Casting to a generic type can cause an unchecked warning.

E. You can create a `new T[]` inside a generic method without a warning.

---

**34.** What is the output of the following code?

```java
import java.util.*;

public class Demo {
    public static void main(String[] args) {
        Comparator<String> comp = Comparator.comparing(String::length).reversed();
        List<String> list = new ArrayList<>(List.of("ab", "a", "abc"));
        list.sort(comp);
        System.out.println(list);
    }
}
```

A. `[a, ab, abc]`

B. `[abc, ab, a]`

C. `[ab, a, abc]`

D. The code does not compile.

---

**35.** What is the output of the following code?

```java
import java.util.*;

public class Demo {
    record Person(String name, int age) {}

    public static void main(String[] args) {
        List<Person> people = new ArrayList<>(List.of(
            new Person("Alice", 30),
            new Person("Bob", 25),
            new Person("Carol", 30)
        ));
        people.sort(Comparator.comparingInt(Person::age)
                              .thenComparing(Person::name));
        System.out.println(people.get(0).name() + " " + people.get(1).name());
    }
}
```

A. `Alice Bob`

B. `Bob Alice`

C. `Carol Alice`

D. `Bob Carol`

E. The code does not compile.

---

**36.** What is the output of the following code?

```java
import java.util.*;

public class Demo {
    public static void main(String[] args) {
        Map<String, Integer> map = new TreeMap<>();
        map.put("banana", 2);
        map.put("apple", 1);
        map.put("cherry", 3);
        map.forEach((k, v) -> System.out.print(k + " "));
    }
}
```

A. `banana apple cherry `

B. `apple banana cherry `

C. `cherry banana apple `

D. The order is unspecified.

E. The code does not compile.

---

**37.** Which of the following statements about `SequencedCollection` (Java 21) are correct? (Choose all that apply.)

A. `List` implements `SequencedCollection`.

B. `HashSet` implements `SequencedCollection`.

C. `LinkedHashSet` implements `SequencedSet`.

D. `SequencedCollection` provides `getFirst()` and `getLast()` methods.

E. `ArrayDeque` does NOT implement `SequencedCollection`.

F. `TreeSet` implements `SequencedSet`.

---

**38.** What is the output of the following code?

```java
import java.util.*;

public class Demo {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>(List.of("a", "b", "c"));
        SequencedCollection<String> sc = list;
        sc.addFirst("z");
        System.out.println(list.getFirst() + " " + list.size());
    }
}
```

A. `a 3`

B. `z 4`

C. `z 3`

D. `a 4`

E. The code does not compile.

---

**39.** What is the output of the following code?

```java
import java.util.*;

public class Demo {
    public static void main(String[] args) {
        LinkedHashMap<String, Integer> map = new LinkedHashMap<>();
        map.put("one", 1);
        map.put("two", 2);
        map.put("three", 3);
        SequencedMap<String, Integer> sm = map;
        System.out.println(sm.firstEntry().getKey() + " " + sm.lastEntry().getKey());
    }
}
```

A. `one three`

B. `three one`

C. The output order is unspecified.

D. The code does not compile.

---

**40.** What is the output of the following code?

```java
import java.util.*;

public class Demo {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>(List.of("a", "b", "c"));
        List<String> reversed = list.reversed();
        reversed.add(0, "z");
        System.out.println(list);
    }
}
```

A. `[z, a, b, c]`

B. `[a, b, c, z]`

C. `[a, b, c]`

D. A `UnsupportedOperationException` is thrown.

E. The code does not compile.

---

**41.** Which of the following correctly declares a bounded generic class?

A. `class Box<T super Number> {}`

B. `class Box<T extends Number> {}`

C. `class Box<? extends Number> {}`

D. `class Box<T extends Number & Comparable<T>> {}`

E. Both B and D are correct.

---

**42.** What is the output of the following code?

```java
import java.util.*;

public class Demo {
    public static <T extends Comparable<T>> T max(List<T> list) {
        return Collections.max(list);
    }

    public static void main(String[] args) {
        System.out.println(max(List.of(3, 1, 4, 1, 5, 9)));
    }
}
```

A. `3`

B. `1`

C. `9`

D. The code does not compile.

E. A `ClassCastException` is thrown.

---

**43.** What is the output of the following code?

```java
import java.util.*;

public class Demo {
    public static void main(String[] args) {
        Set<Integer> set = Set.of(1, 2, 3);
        set.add(4);
        System.out.println(set);
    }
}
```

A. `[1, 2, 3, 4]`

B. `[1, 2, 3]`

C. A `UnsupportedOperationException` is thrown.

D. The code does not compile.

---

**44.** What is the output of the following code?

```java
import java.util.*;

public class Demo {
    public static void main(String[] args) {
        Map<String, Integer> map = Map.of("a", 1, "b", 2);
        map.put("c", 3);
        System.out.println(map.size());
    }
}
```

A. `3`

B. `2`

C. A `UnsupportedOperationException` is thrown.

D. The code does not compile.

---

**45.** What happens when you try to create a `Set.of(1, 2, 1)` (with duplicate elements)?

A. The set silently ignores the duplicate and contains `[1, 2]`.

B. An `IllegalArgumentException` is thrown at runtime.

C. A compilation error occurs.

D. The set contains `[1, 2, 1]`.

---

**46.** What is the output of the following code?

```java
import java.util.*;

public class Demo {
    public static void main(String[] args) {
        ArrayDeque<String> deque = new ArrayDeque<>();
        deque.offer("first");
        deque.offer("second");
        deque.offer("third");
        System.out.println(deque.poll() + " " + deque.peekLast());
    }
}
```

A. `third second`

B. `first third`

C. `first second`

D. `third first`

E. The code does not compile.

---

**47.** What is the output of the following code?

```java
import java.util.*;

public class Demo {
    public static void main(String[] args) {
        List<Object> list = new ArrayList<>();
        list.add("hello");
        list.add(42);
        list.add(3.14);
        for (Object o : list) {
            System.out.print(o.getClass().getSimpleName() + " ");
        }
    }
}
```

A. `Object Object Object `

B. `String Integer Double `

C. The code does not compile.

D. A `ClassCastException` is thrown.

---

**48.** What is the output of the following code?

```java
import java.util.*;

public class Demo {
    public static void main(String[] args) {
        TreeSet<String> ts = new TreeSet<>();
        ts.add("hello");
        ts.add("HELLO");
        System.out.println(ts.size() + " " + ts.first());
    }
}
```

A. `1 hello`

B. `1 HELLO`

C. `2 HELLO`

D. `2 hello`

E. The code does not compile.

---

**49.** What is the output of the following code?

```java
import java.util.*;

public class Demo {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>(List.of("c", "b", "a"));
        List<String> unmod = Collections.unmodifiableList(list);
        list.add("d");
        System.out.println(unmod.size());
    }
}
```

A. `3`

B. `4`

C. A `UnsupportedOperationException` is thrown.

D. The code does not compile.

---

**50.** Which of the following statements about `Comparator` and `Comparable` are correct? (Choose all that apply.)

A. A class implements `Comparable` to define its natural ordering.

B. `Comparator` is a functional interface with the single abstract method `compare(T o1, T o2)`.

C. `compareTo()` returns a negative value if the current object is less than the argument.

D. `Comparator.comparing(Function)` creates a comparator that compares by the result of the function using natural order.

E. `Comparator.reversed()` modifies the original `Comparator` in place.

F. A `TreeSet` can be created with a custom `Comparator` to override natural ordering.

---
