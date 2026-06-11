# Chapter 14: I/O — Practice Questions

---

**1.** Given the following file system layout (all paths relative to `/data`):

```
/data
/data/reports
/data/reports/2024
/data/reports/2024/jan.txt
/data/archive
```

What is the output of the following code?

```java
Path p = Path.of("/data/reports/2024/jan.txt");
System.out.println(p.getNameCount());
System.out.println(p.getName(0));
System.out.println(p.getName(2));
```

A. `4`, `data`, `2024`

B. `3`, `reports`, `jan.txt`

C. `4`, `reports`, `jan.txt`

D. `3`, `data`, `2024`

E. The code throws an `IllegalArgumentException`.

---

**2.** Which of the following statements about `java.io.File` and `java.nio.file.Path` are true? (Choose two.)

A. `File` has a `delete()` method that throws a checked `IOException`.

B. `Path` is an interface, so it cannot be instantiated directly with `new`.

C. `Path.of("data.txt")` and `new File("data.txt")` always refer to different files on disk.

D. A `File` object can be converted to a `Path` object via `toPath()`.

E. `Files.exists(path)` and `file.exists()` have identical method signatures.

---

**3.** What is the output of the following code, given that the current working directory is `/home/zoo` and `/home/zoo/animals` exists as a directory?

```java
Path p1 = Path.of("animals");
Path p2 = Path.of("/home/zoo/animals");
System.out.println(p1.equals(p2));
System.out.println(p1.toAbsolutePath().equals(p2));
```

A. `true` then `true`

B. `false` then `false`

C. `false` then `true`

D. `true` then `false`

E. The code does not compile.

---

**4.** What is the output of the following code?

```java
Path p = Path.of("/zoo/../safari/./lions.txt");
System.out.println(p.normalize());
System.out.println(p);
```

A. `/safari/lions.txt` then `/safari/lions.txt`

B. `/safari/lions.txt` then `/zoo/../safari/./lions.txt`

C. `/zoo/../safari/./lions.txt` then `/safari/lions.txt`

D. `/zoo/../safari/./lions.txt` then `/zoo/../safari/./lions.txt`

E. The code throws an exception because `..` cannot be resolved without accessing the file system.

---

**5.** Which of the following correctly describes the behavior of `Path.startsWith()` and `Path.endsWith()`?

```java
Path p = Path.of("/habitat/forest/oak");
System.out.println(p.startsWith("/habitat/for"));
System.out.println(p.startsWith("/habitat/forest"));
System.out.println(p.endsWith("oak"));
System.out.println(p.endsWith("forest/oak"));
```

A. `true`, `true`, `true`, `true`

B. `false`, `true`, `true`, `true`

C. `false`, `true`, `false`, `true`

D. `true`, `true`, `true`, `false`

E. `false`, `false`, `true`, `true`

---

**6.** What is the output of the following code?

```java
Path p1 = Path.of("/zoo/mammals/../birds");
Path p2 = Path.of("/zoo/birds");
System.out.println(p1.equals(p2));
System.out.println(p1.normalize().equals(p2));
System.out.println(Files.isSameFile(p1, p2));
```

Assume both `/zoo/mammals` and `/zoo/birds` exist as directories in the file system.

A. `false`, `true`, `true`

B. `true`, `true`, `true`

C. `false`, `false`, `false`

D. `false`, `true`, `false`

E. The code throws `NoSuchFileException` at runtime.

---

**7.** What is printed by the following code?

```java
Path path1 = Path.of("/habitat/zone1");
Path path2 = Path.of("food/feed.txt");
System.out.println(path1.resolve(path2));
System.out.println(path2.resolve(path1));
```

A. `/habitat/zone1/food/feed.txt` then `/habitat/zone1`

B. `/habitat/zone1/food/feed.txt` then `food/feed.txt/habitat/zone1`

C. `food/feed.txt` then `/habitat/zone1`

D. `/habitat/zone1` then `food/feed.txt`

E. The code throws `IllegalArgumentException` because absolute and relative paths cannot be combined.

---

**8.** What is the output of the following code?

```java
Path p1 = Path.of("/zebra/stripes/pattern.txt");
Path p2 = Path.of("/zebra/legs");
System.out.println(p1.relativize(p2));
System.out.println(p2.relativize(p1));
```

A. `../legs` then `../stripes/pattern.txt`

B. `../../legs` then `../stripes/pattern.txt`

C. `../legs` then `stripes/pattern.txt`

D. `legs` then `pattern.txt`

E. `../../legs` then `../../stripes/pattern.txt`

---

**9.** Which of the following statements about `resolveSibling()` are true? (Choose two.)

```java
Path p = Path.of("/data/reports/jan.txt");
System.out.println(p.resolveSibling("feb.txt"));
System.out.println(p.resolveSibling("/data/archive/feb.txt"));
```

A. The first line prints `/data/reports/feb.txt`.

B. The first line prints `/data/reports/jan.txt/feb.txt`.

C. The second line prints `/data/reports/data/archive/feb.txt`.

D. The second line prints `/data/archive/feb.txt`.

E. The code does not compile because `resolveSibling()` requires a `Path` argument, not a `String`.

---

**10.** What is the output of the following code?

```java
var p = Path.of("/wildlife/preserve/animals/lion.dat");
System.out.println(p.subpath(0, 2));
System.out.println(p.subpath(1, 3));
System.out.println(p.getName(3));
```

A. `wildlife/preserve`, `preserve/animals`, `lion.dat`

B. `/wildlife/preserve`, `/preserve/animals`, `lion.dat`

C. `wildlife/preserve/animals`, `preserve/animals`, `lion.dat`

D. `wildlife/preserve`, `preserve/animals/lion.dat`, `lion.dat`

E. The code throws `IllegalArgumentException` on the third line because the index is out of bounds.

---

**11.** What does the following code print, assuming all referenced paths exist?

```java
var p = Path.of("/safari/./trucks/../jeep.txt");
System.out.println(p.getParent());
System.out.println(p.normalize().getParent());
```

A. `/safari/./trucks/..` then `/safari`

B. `/safari` then `/safari`

C. `/safari/./trucks/..` then `/safari/./trucks/..`

D. `null` then `/safari`

E. The code does not compile.

---

**12.** Consider the following directory layout:

```
/zoo
/zoo/exhibits
/zoo/exhibits/reptiles
/zoo/exhibits/reptiles/snake.txt
/zoo/staff.txt
```

What is the output of the following code?

```java
var p = Path.of("/zoo/exhibits/reptiles/snake.txt");
Path current = p;
while (current != null) {
    System.out.println(current);
    current = current.getParent();
}
```

A.
```
/zoo/exhibits/reptiles/snake.txt
/zoo/exhibits/reptiles
/zoo/exhibits
/zoo
/
```

B.
```
/zoo/exhibits/reptiles/snake.txt
/zoo/exhibits/reptiles
/zoo/exhibits
/zoo
```

C.
```
snake.txt
reptiles
exhibits
zoo
```

D. The code throws a `NullPointerException`.

E. The loop never terminates because `getParent()` always returns a non-null `Path`.

---

**13.** Which of the following statements about `Files.exists()` and `Files.notExists()` are true? (Choose two.)

A. `Files.exists(p)` always returns the opposite of `Files.notExists(p)`.

B. Both `Files.exists(p)` and `Files.notExists(p)` can return `false` for the same path if the file's existence cannot be verified (e.g., due to a permissions error).

C. `Files.exists(p)` declares a checked `IOException`.

D. `Files.exists(p)` returns `false` if `p` refers to a broken symbolic link, by default.

E. `Files.notExists(p)` returns `true` only if `p` refers to a regular file that has been deleted.

---

**14.** What is the result of the following code if `/data/output.txt` does not currently exist?

```java
Path p = Path.of("/data/output.txt");
try {
    Files.createFile(p);
    Files.createFile(p);
    System.out.println("Both created");
} catch (IOException e) {
    System.out.println("Exception: " + e.getClass().getSimpleName());
}
```

A. `Both created`

B. `Exception: IOException`

C. `Exception: FileAlreadyExistsException`

D. `Exception: NoSuchFileException`

E. The code does not compile because `Files.createFile()` does not declare a checked exception.

---

**15.** Given that `/data/logs` does not exist (nor does `/data`), what is the result of executing the following code?

```java
try {
    Files.createDirectory(Path.of("/data/logs"));
    System.out.println("Created with createDirectory");
} catch (IOException e) {
    System.out.println("createDirectory failed: " + e.getClass().getSimpleName());
}

try {
    Files.createDirectories(Path.of("/data/logs"));
    System.out.println("Created with createDirectories");
} catch (IOException e) {
    System.out.println("createDirectories failed: " + e.getClass().getSimpleName());
}
```

A.
```
Created with createDirectory
Created with createDirectories
```

B.
```
createDirectory failed: NoSuchFileException
Created with createDirectories
```

C.
```
createDirectory failed: IOException
createDirectories failed: IOException
```

D.
```
Created with createDirectory
createDirectories failed: FileAlreadyExistsException
```

E. The code does not compile.

---

**16.** What is the result of the following code, assuming `/zoo/visitors.txt` exists and `/zoo/backup.txt` does not exist?

```java
Path source = Path.of("/zoo/visitors.txt");
Path target = Path.of("/zoo/backup.txt");
Files.copy(source, target);
Files.copy(source, target);
System.out.println("Done");
```

A. `Done`

B. The first `copy()` succeeds; the second throws `FileAlreadyExistsException`.

C. Both calls succeed silently because `copy()` always overwrites by default.

D. The first `copy()` throws `NoSuchFileException`.

E. The code does not compile because `copy()` requires a `CopyOption` argument.

---

**17.** What is the result of the following code, assuming `/zoo/visitors.txt` exists and `/zoo/backup.txt` does not exist?

```java
Path source = Path.of("/zoo/visitors.txt");
Path target = Path.of("/zoo/backup.txt");
Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);
Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);
System.out.println("Done");
```

A. `Done`

B. The first call succeeds; the second throws `FileAlreadyExistsException`.

C. Both calls throw `NoSuchFileException`.

D. The code does not compile.

E. Only the first call succeeds; the second is a no-op but does not throw.

---

**18.** Given a file `/park/bench.txt` and a directory `/park/shelter` (both already exist), what happens when the following code runs?

```java
var file = Path.of("/park/bench.txt");
var dir = Path.of("/park/shelter");
Files.copy(file, dir);
```

A. A new file `/park/shelter/bench.txt` is created with the contents of `bench.txt`.

B. The contents of `bench.txt` overwrite the directory `/park/shelter`, converting it into a file.

C. A `FileAlreadyExistsException` is thrown because `/park/shelter` already exists.

D. A `NotDirectoryException` is thrown.

E. The code does not compile.

---

**19.** What is the output of the following code, assuming `/wolves` exists as a non-empty directory and `/wolves-archive` does not exist?

```java
try {
    Files.move(Path.of("/wolves"), Path.of("/wolves-archive"));
    System.out.println("Moved: " + Files.exists(Path.of("/wolves")) + " "
        + Files.exists(Path.of("/wolves-archive")));
} catch (IOException e) {
    System.out.println("Failed: " + e.getClass().getSimpleName());
}
```

A. `Moved: false true`

B. `Moved: true true`

C. `Failed: NoSuchFileException`

D. `Failed: DirectoryNotEmptyException`

E. The code does not compile.

---

**20.** What is the output of the following code, assuming `/data/temp.txt` does not exist?

```java
Path p = Path.of("/data/temp.txt");
try {
    Files.delete(p);
} catch (IOException e) {
    System.out.println("delete: " + e.getClass().getSimpleName());
}
boolean result = Files.deleteIfExists(p);
System.out.println("deleteIfExists: " + result);
```

A.
```
delete: NoSuchFileException
deleteIfExists: false
```

B.
```
delete: FileNotFoundException
deleteIfExists: true
```

C.
```
deleteIfExists: false
```

D.
```
delete: NoSuchFileException
deleteIfExists: true
```

E. The code does not compile because `Files.delete()` does not declare a checked exception.

---

**21.** Consider the following directory tree:

```
/park
/park/trail.txt
/park/lake
/park/lake/dock.txt
/park/lake/boats
/park/lake/boats/canoe.txt
```

What is printed by the following code (order within each level may vary, but the set of paths printed must be considered)?

```java
try (Stream<Path> s = Files.list(Path.of("/park"))) {
    s.forEach(System.out::println);
}
```

A.
```
/park/trail.txt
/park/lake
```

B.
```
/park
/park/trail.txt
/park/lake
/park/lake/dock.txt
/park/lake/boats
/park/lake/boats/canoe.txt
```

C.
```
/park/trail.txt
/park/lake
/park/lake/dock.txt
/park/lake/boats
/park/lake/boats/canoe.txt
```

D. The code throws an exception because `Files.list()` requires a `maxDepth` argument.

E. The code does not compile because the `Stream<Path>` is never closed.

---

**22.** Using the same directory tree as the previous question:

```
/park
/park/trail.txt
/park/lake
/park/lake/dock.txt
/park/lake/boats
/park/lake/boats/canoe.txt
```

What is printed by the following code?

```java
try (Stream<Path> s = Files.walk(Path.of("/park"))) {
    long count = s.count();
    System.out.println(count);
}
```

A. `5`

B. `6`

C. `4`

D. `3`

E. The code throws an exception because `Files.walk()` cannot be used with `count()`.

---

**23.** What is the output of the following code, given the directory tree from the previous two questions?

```java
try (Stream<Path> s = Files.walk(Path.of("/park"), 1)) {
    s.filter(Files::isRegularFile)
     .forEach(System.out::println);
}
```

A.
```
/park/trail.txt
```

B.
```
/park/trail.txt
/park/lake/dock.txt
/park/lake/boats/canoe.txt
```

C.
```
/park/trail.txt
/park/lake
```

D.
```
```
(nothing is printed)

E. The code throws an exception because `1` is not a valid `maxDepth`.

---

**24.** What is the output of the following code, given the directory tree from the previous questions?

```java
try (Stream<Path> s = Files.find(Path.of("/park"), 10,
        (path, attrs) -> attrs.isRegularFile() && path.toString().endsWith(".txt"))) {
    s.map(Path::getFileName).forEach(System.out::println);
}
```

A. It prints exactly three lines — `trail.txt`, `dock.txt`, and `canoe.txt` — though not necessarily in that order.

B. It prints exactly one line, `trail.txt`, since `find()` does not search subdirectories by default.

C. It prints six lines, including the directory names `park`, `lake`, and `boats`.

D. The code does not compile because `find()` requires a `BiPredicate<Path, BasicFileAttributes>`, and lambdas cannot be used here.

E. The code throws an exception because `find()` cannot filter on file extensions.

---

**25.** Which of the following statements about `Files.readAllLines()` and `Files.lines()` are correct? (Choose two.)

A. `Files.readAllLines(path)` returns a `Stream<String>`.

B. `Files.lines(path)` returns a `Stream<String>` that should be closed via try-with-resources.

C. `Files.readAllLines(path)` loads the entire file into memory as a `List<String>`.

D. `Files.lines(path).filter(...)` does not compile because `Files.lines()` returns a `List`.

E. `Files.readAllLines(path)` is preferred for very large files because it streams data lazily.

---

**26.** What is the output of the following code, assuming `/notes/log.txt` contains exactly these three lines:

```
INFO start
WARN low memory
INFO done
```

```java
List<String> lines = Files.readAllLines(Path.of("/notes/log.txt"));
lines.stream()
     .filter(l -> l.startsWith("WARN"))
     .forEach(System.out::println);
```

A. `WARN low memory`

B. The code does not compile because `lines` is a `List<String>`, not a `Stream<String>`.

C. Nothing is printed.

D. All three lines are printed.

E. A `NoSuchFileException` is thrown at runtime.

---

**27.** Which of the following correctly writes the string `"Hello, Zoo!"` to a new file at `/data/greeting.txt`, creating the file if it doesn't exist and overwriting it if it does? (Choose three.)

A. `Files.writeString(Path.of("/data/greeting.txt"), "Hello, Zoo!");`

B. `Files.write(Path.of("/data/greeting.txt"), "Hello, Zoo!".getBytes());`

C. `Files.writeString(Path.of("/data/greeting.txt"), "Hello, Zoo!", StandardOpenOption.CREATE_NEW);` (when the file already exists)

D. `new FileWriter("/data/greeting.txt").write("Hello, Zoo!");` with no checked exception handling

E. `Files.write(Path.of("/data/greeting.txt"), List.of("Hello, Zoo!"));`

---

**28.** What is the output of the following code?

```java
var sb = new StringBuilder();
try (BufferedReader br = new BufferedReader(new StringReader("alpha\nbeta\n"))) {
    String line;
    while ((line = br.readLine()) != null) {
        sb.append(line).append("-");
    }
}
System.out.println(sb);
```

A. `alpha-beta-`

B. `alpha\nbeta\n-`

C. `alpha-beta-null-`

D. The code does not compile because `BufferedReader` cannot wrap a `StringReader`.

E. An `IOException` is thrown because `readLine()` never returns `null`.

---

**29.** Which of the following statements correctly compile? (Choose four.)

A. `new BufferedReader(new FileReader("data.txt"))`

B. `new BufferedWriter(new FileOutputStream("data.txt"))`

C. `new BufferedInputStream(new FileInputStream("data.txt"))`

D. `new ObjectInputStream(new FileOutputStream("data.txt"))`

E. `new InputStreamReader(new FileInputStream("data.txt"))`

F. `new PrintWriter(new FileWriter("data.txt"))`

---

**30.** What is the output of the following code?

```java
try (var os = new ObjectOutputStream(new FileOutputStream("/tmp/seed.ser"))) {
    os.writeObject("hello");
    os.writeObject(42);
    os.writeObject(List.of("a", "b"));
} 

try (var is = new ObjectInputStream(new FileInputStream("/tmp/seed.ser"))) {
    System.out.println(is.readObject());
    System.out.println(is.readObject());
    System.out.println(is.readObject());
}
```

A.
```
hello
42
[a, b]
```

B. The code throws `NotSerializableException` because `42` is a primitive.

C. The code does not compile because `writeObject()` requires the argument to be cast to `Serializable`.

D. The code throws `ClassNotFoundException` at runtime.

E. The code throws `EOFException` after the third `readObject()` call.

---

**31.** Consider the following classes:

```java
class Habitat {
    String name = "Savannah";
}

public class Lion implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private Habitat habitat = new Habitat();

    public Lion(String name) {
        this.name = name;
    }
}
```

What happens when the following code is executed?

```java
Lion lion = new Lion("Simba");
try (var os = new ObjectOutputStream(new FileOutputStream("/tmp/lion.ser"))) {
    os.writeObject(lion);
    System.out.println("Serialized");
} catch (IOException e) {
    System.out.println("Failed: " + e.getClass().getSimpleName());
}
```

A. `Serialized`

B. `Failed: NotSerializableException`

C. The code does not compile because `Habitat` does not implement `Serializable`.

D. `Failed: InvalidClassException`

E. `Failed: IOException`

---

**32.** Using the classes from the previous question, which of the following changes would, **on its own**, prevent `NotSerializableException` from being thrown at runtime, while still allowing each `Lion` instance to retain its **own** `habitat` data after deserialization?

A. Mark the `habitat` field as `transient`.

B. Make `Habitat` implement `Serializable`.

C. Add a `serialVersionUID` field to `Habitat`.

D. Change `habitat` to `static`.

E. Remove the `serialVersionUID` field from `Lion`.

---

**33.** Given the following classes:

```java
class Vehicle {
    protected int wheels;
    public Vehicle() {
        this.wheels = 4;
    }
}

public class Car extends Vehicle implements Serializable {
    private static final long serialVersionUID = 1L;
    private transient String color;
    private int topSpeed;

    public Car(String color, int topSpeed) {
        this.color = color;
        this.topSpeed = topSpeed;
        this.wheels = 4;
    }
}
```

After serializing a `Car` with `color = "Red"`, `topSpeed = 200`, and `wheels = 4`, then deserializing it, what are the values of `color`, `topSpeed`, and `wheels`?

A. `"Red"`, `200`, `4`

B. `null`, `200`, `4`

C. `null`, `200`, `0`

D. `null`, `0`, `4`

E. The code does not compile because `Vehicle` is not `Serializable`.

---

**34.** What is the output of the following code?

```java
class Counter implements Serializable {
    private static final long serialVersionUID = 1L;
    static int instanceCount = 0;
    private transient int value;

    public Counter(int value) {
        this.value = value;
        instanceCount++;
    }
}

public class Test {
    public static void main(String[] args) throws Exception {
        Counter c = new Counter(99);
        try (var os = new ObjectOutputStream(new FileOutputStream("/tmp/c.ser"))) {
            os.writeObject(c);
        }
        Counter.instanceCount = 500;
        try (var is = new ObjectInputStream(new FileInputStream("/tmp/c.ser"))) {
            Counter c2 = (Counter) is.readObject();
            System.out.println(c2.value + " " + Counter.instanceCount);
        }
    }
}
```

A. `99 500`

B. `0 500`

C. `0 1`

D. `99 1`

E. The code does not compile because `value` is `private`.

---

**35.** What is the output of the following code, assuming `Mammal` is **not** `Serializable`?

```java
class Mammal {
    protected String species;
    public Mammal() {
        this.species = "Unknown";
    }
    public Mammal(String species) {
        this.species = species;
    }
}

class Elephant extends Mammal implements Serializable {
    private static final long serialVersionUID = 1L;
    private transient String name;

    public Elephant(String species, String name) {
        super(species);
        this.name = name;
    }
}

public class Test {
    public static void main(String[] args) throws Exception {
        Elephant e = new Elephant("African", "Dumbo");
        try (var os = new ObjectOutputStream(new FileOutputStream("/tmp/e.ser"))) {
            os.writeObject(e);
        }
        try (var is = new ObjectInputStream(new FileInputStream("/tmp/e.ser"))) {
            Elephant e2 = (Elephant) is.readObject();
            System.out.println(e2.species + " " + e2.name);
        }
    }
}
```

A. `African Dumbo`

B. `Unknown null`

C. `null null`

D. `African null`

E. `InvalidClassException` is thrown because `Mammal` lacks a no-arg constructor accessible to `Elephant`.

---

**36.** Which of the following statements about the `Serializable` marker interface are true? (Choose four.)

A. `Serializable` declares a single method, `writeObject()`, that classes must implement.

B. A class can implement `Serializable` even if it has no fields at all.

C. If a superclass is `Serializable`, a subclass that does not redeclare `implements Serializable` is still considered a `Serializable` type.

D. A `record` must explicitly declare `implements Serializable` to be serializable.

E. Marking a field `static` has no effect on whether the containing class can be serialized.

---

**37.** What is the output of the following code?

```java
List<Object> items = new ArrayList<>(List.of("text", 100));
try (var os = new ObjectOutputStream(new FileOutputStream("/tmp/items.ser"))) {
    os.writeObject(items);
} 

try (var is = new ObjectInputStream(new FileInputStream("/tmp/items.ser"))) {
    while (true) {
        Object obj = is.readObject();
        System.out.println(obj);
    }
} catch (EOFException e) {
    System.out.println("Done");
} catch (ClassNotFoundException e) {
    System.out.println("Class not found");
}
```

A.
```
[text, 100]
Done
```

B.
```
text
100
Done
```

C.
```
[text, 100]
```
followed by an infinite loop

D. `Class not found`

E. The code does not compile because `readObject()` is called inside a `while(true)` loop without a break.

---

**38.** What is the output of the following code?

```java
try (var pw = new PrintWriter(new FileWriter("/tmp/out.txt"))) {
    pw.println("Line 1");
    pw.print(42);
    pw.println();
    pw.printf("Value: %.2f%n", 3.14159);
}
try (var br = new BufferedReader(new FileReader("/tmp/out.txt"))) {
    String line;
    while ((line = br.readLine()) != null) {
        System.out.println("[" + line + "]");
    }
}
```

A.
```
[Line 1]
[42]
[Value: 3.14]
```

B.
```
[Line 1]
[42]
[Value: 3.14159]
```

C.
```
[Line 142]
[Value: 3.14]
```

D. The code does not compile because `printf()` is not a method on `PrintWriter`.

E. The code throws `IOException` at runtime because `PrintWriter` cannot wrap `FileWriter`.

---

**39.** Which of the following statements about `PrintStream` and `PrintWriter` are true? (Choose two.)

A. `System.out.println()` can throw a checked `IOException` that must be caught or declared.

B. `PrintStream` and `PrintWriter` both expose a `checkError()` method that can be used to detect whether an internal error occurred.

C. `System.out` is an instance of `PrintWriter`.

D. `PrintWriter` has a constructor that accepts an `OutputStream`.

E. `PrintWriter` has a corresponding `PrintReader` class for reading formatted text.

---

**40.** What is the output of the following code?

```java
try (var out1 = new FileOutputStream("/tmp/a.txt");
     var out2 = new FileOutputStream("/tmp/b.txt")) {
    System.out.println("Opened both");
} finally {
    System.out.println("Finally");
}
```

Assuming both files can be opened successfully, in what order are the resources closed, and what is printed?

A. `out1` is closed first, then `out2`; output is `Opened both` then `Finally`.

B. `out2` is closed first, then `out1`; output is `Opened both` then `Finally`.

C. Both are closed simultaneously; output is `Opened both` then `Finally`.

D. The code does not compile because try-with-resources only allows a single resource.

E. `out1` is closed first, then `out2`; output is `Finally` then `Opened both`.

---

**41.** Which of the following statements correctly describe the `AutoCloseable` and `Closeable` interfaces? (Choose three.)

A. `Closeable.close()` declares `throws IOException`, while `AutoCloseable.close()` declares `throws Exception`.

B. `Closeable` extends `AutoCloseable`.

C. `AutoCloseable` extends `Closeable`.

D. A class that implements `Closeable` cannot be used in a try-with-resources statement.

E. `BufferedReader` implements `Closeable`.

---

**42.** What is the output of the following code, given that `console` may or may not be available at runtime?

```java
Console console = System.console();
if (console == null) {
    System.out.println("No console");
} else {
    String name = console.readLine("Name: ");
    System.out.println("Hello " + name);
}
```

A. The code always prints `Hello ` followed by user input.

B. `System.console()` always returns a non-null instance when run from any environment, including IDEs.

C. `System.console()` may return `null`, for example when the program's input/output has been redirected or it is run from many IDEs; the code correctly handles that case.

D. The code does not compile because `Console` has no public constructor.

E. The code throws `NullPointerException` if no console is attached.

---

**43.** Which of the following statements about `Console.readPassword()` are true? (Choose two.)

A. It returns a `String`.

B. It returns a `char[]`.

C. The characters typed by the user are not echoed to the screen.

D. It automatically encrypts the returned value.

E. It can never accept a format string argument.

---

**44.** Consider the following directory layout, where `current.log` is a symbolic link pointing to `2024.log`:

```
/var/log
/var/log/2024.log
/var/log/current.log -> 2024.log
```

What is the output of the following code?

```java
Path link = Path.of("/var/log/current.log");
System.out.println(Files.isSymbolicLink(link));
System.out.println(Files.isRegularFile(link));
System.out.println(Files.isRegularFile(link, LinkOption.NOFOLLOW_LINKS));
```

A. `true`, `true`, `false`

B. `true`, `false`, `false`

C. `false`, `true`, `true`

D. `true`, `true`, `true`

E. The code does not compile.

---

**45.** What is the output of the following code, assuming `/var/log/2024.log` exists and is a regular file, and `/var/log/current.log` is a symbolic link pointing to it?

```java
Path link = Path.of("/var/log/current.log");
Path target = Files.readSymbolicLink(link);
System.out.println(target);
System.out.println(Files.exists(link));
System.out.println(Files.exists(link, LinkOption.NOFOLLOW_LINKS));
```

A. `2024.log`, `true`, `true`

B. `/var/log/2024.log`, `true`, `false`

C. `2024.log`, `false`, `true`

D. `current.log`, `true`, `true`

E. The code throws `UnsupportedOperationException` because symbolic links are not supported by NIO.2.

---

**46.** What is the output of the following code, assuming `/data/report.csv` exists, is 2,048 bytes in size, and was last modified at some point in the past?

```java
Path p = Path.of("/data/report.csv");
BasicFileAttributes attrs = Files.readAttributes(p, BasicFileAttributes.class);
System.out.println(attrs.isDirectory());
System.out.println(attrs.isRegularFile());
System.out.println(attrs.size());
System.out.println(attrs.size() == Files.size(p));
```

A. `false`, `true`, `2048`, `true`

B. `true`, `false`, `2048`, `true`

C. `false`, `true`, `2048`, `false`

D. The code does not compile because `BasicFileAttributes` has no `size()` method.

E. The code throws `IOException` because `readAttributes()` cannot be used on regular files.

---

**47.** What is the output of the following code?

```java
Path dir = Path.of("/data/exports");
Files.createDirectories(dir.resolve("2024/q1"));
Files.createDirectories(dir.resolve("2024/q2"));
Files.createFile(dir.resolve("2024/summary.txt"));

try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir.resolve("2024"), "*.txt")) {
    for (Path entry : stream) {
        System.out.println(entry.getFileName());
    }
}
```

A. `summary.txt`

B. `q1`, `q2`, `summary.txt`

C. `q1`, `q2`

D. The code does not compile because `DirectoryStream` does not implement `Iterable`.

E. The code throws an exception because `newDirectoryStream()` requires a regular file, not a directory.

---

**48.** What is the output of the following code?

```java
class Box<T extends Serializable> implements Serializable {
    private static final long serialVersionUID = 1L;
    private T contents;
    private final transient long createdAt = System.currentTimeMillis();

    public Box(T contents) {
        this.contents = contents;
    }

    public T getContents() { return contents; }
    public long getCreatedAt() { return createdAt; }
}

public class Test {
    public static void main(String[] args) throws Exception {
        Box<String> box = new Box<>("Treasure");
        try (var os = new ObjectOutputStream(new FileOutputStream("/tmp/box.ser"))) {
            os.writeObject(box);
        }
        try (var is = new ObjectInputStream(new FileInputStream("/tmp/box.ser"))) {
            Box<String> box2 = (Box<String>) is.readObject();
            System.out.println(box2.getContents());
            System.out.println(box2.getCreatedAt());
        }
    }
}
```

A. `Treasure` then a non-zero timestamp matching the original

B. `Treasure` then `0`

C. `null` then `0`

D. The code does not compile because `final` fields cannot be `transient`.

E. `NotSerializableException` is thrown because `T` is a generic type.

---

**49.** Examine the following method:

```java
void process(Path source, Path targetDir) throws IOException {
    if (!Files.exists(targetDir)) {
        Files.createDirectory(targetDir);
    }
    Path target = targetDir.resolve(source.getFileName());
    Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);
    Files.delete(source);
}
```

Assume `/incoming/data.csv` exists, `/processed` does not exist, and `/incoming` exists. What happens when `process(Path.of("/incoming/data.csv"), Path.of("/processed/archive"))` is called?

A. The file is moved successfully to `/processed/archive/data.csv`.

B. A `NoSuchFileException` is thrown by `createDirectory()` because `/processed` does not exist.

C. A `FileAlreadyExistsException` is thrown by `createDirectory()`.

D. The file is copied but not deleted, due to a missing `flush()`.

E. The code does not compile because `Files.delete()` requires a `try`/`catch` for `IOException` even though the method declares `throws IOException`.

---

**50.** What is the output of the following code?

```java
class Pair implements Serializable {
    private static final long serialVersionUID = 1L;
    private int a;
    private int b;

    public Pair(int a, int b) {
        this.a = a;
        this.b = b;
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        this.b = this.a + this.b;
    }

    public String toString() {
        return "(" + a + "," + b + ")";
    }
}

public class Test {
    public static void main(String[] args) throws Exception {
        Pair p = new Pair(3, 4);
        try (var os = new ObjectOutputStream(new FileOutputStream("/tmp/pair.ser"))) {
            os.writeObject(p);
        }
        try (var is = new ObjectInputStream(new FileInputStream("/tmp/pair.ser"))) {
            Pair p2 = (Pair) is.readObject();
            System.out.println(p2);
        }
    }
}
```

A. `(3,4)`

B. `(3,7)`

C. `(0,0)`

D. The code does not compile because `writeObject()` and `readObject()` must be `public`.

E. `InvalidClassException` is thrown because custom serialization methods conflict with `serialVersionUID`.

---

**51.** What is the output of the following code?

```java
var input = new ByteArrayInputStream("LIONESS".getBytes());
StringBuilder sb = new StringBuilder();
sb.append((char) input.read());          // L
if (input.markSupported()) {
    input.mark(20);
    sb.append((char) input.read());      // I
    input.skip(2);                       // skip O, N
    sb.append((char) input.read());      // E
    input.reset();
}
sb.append((char) input.read());          // ?
System.out.println(sb);
```

A. `LIEI`

B. `LIESE`

C. `LIE` followed by whatever character follows `L` after reset (i.e., `I`), giving `LIEI`

D. `LIESS`

E. The code does not compile because `ByteArrayInputStream` does not support `mark()`.

---

**52.** Which of the following statements about the `File` class methods `mkdir()` and `mkdirs()` are true? (Choose four.)

A. `mkdir()` creates the target directory only if its parent directory already exists; otherwise it returns `false`.

B. `mkdirs()` creates the target directory along with any missing parent directories.

C. `mkdir()` throws `IOException` if the directory cannot be created.

D. `mkdirs()` returns `false` if the directory structure already fully exists.

E. Both `mkdir()` and `mkdirs()` return a `boolean` indicating success.

---

**53.** What is the output of the following code?

```java
File f1 = new File("/zoo/data.txt");
File f2 = new File("/zoo", "data.txt");
File parent = new File("/zoo");
File f3 = new File(parent, "data.txt");
File f4 = new File((String) null, "/zoo/data.txt");

System.out.println(f1.equals(f2));
System.out.println(f2.equals(f3));
System.out.println(f4.getPath());
```

A. `true`, `true`, `/zoo/data.txt`

B. `false`, `false`, `/zoo/data.txt`

C. `true`, `true`, `null/zoo/data.txt`

D. The code throws a `NullPointerException` constructing `f4`.

E. The code does not compile because `File` has no constructor taking `(String, String)`.

---

**54.** What is the output of the following code?

```java
public class FileVisitorDemo extends SimpleFileVisitor<Path> {
    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) {
        System.out.println("Enter: " + dir.getFileName());
        if (dir.getFileName().toString().equals("skip")) {
            return FileVisitResult.SKIP_SUBTREE;
        }
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
        System.out.println("File: " + file.getFileName());
        return FileVisitResult.CONTINUE;
    }
}
```

Given the directory tree:

```
/root
/root/a.txt
/root/skip
/root/skip/b.txt
/root/c.txt
```

What is printed by `Files.walkFileTree(Path.of("/root"), new FileVisitorDemo())`?

A. `Enter: root`, `Enter: skip`, `File: a.txt`, `File: b.txt`, and `File: c.txt` are all printed (in some order).

B. `Enter: root`, `Enter: skip`, `File: a.txt`, and `File: c.txt` are printed, but `File: b.txt` is never printed.

C. Only `Enter: root`, `File: a.txt`, and `File: c.txt` are printed; `skip` is never entered at all.

D. The code does not compile because `SimpleFileVisitor` requires all four methods to be overridden.

E. The code throws `FileSystemLoopException`.

---

**55.** What is the output of the following code?

```java
Path p1 = Path.of("reports", "2024", "summary.txt");
Path p2 = Path.of("reports/2024/summary.txt");
System.out.println(p1.equals(p2));
System.out.println(p1.toString().equals(p2.toString()));
System.out.println(p1.getRoot());
System.out.println(p1.isAbsolute());
```

A. `true`, `true`, `null`, `false`

B. `false`, `false`, `null`, `false`

C. `true`, `true`, ``, `true`

D. `true`, `false`, `null`, `false`

E. The code does not compile because `Path.of()` cannot take varargs `String` parameters.

---
