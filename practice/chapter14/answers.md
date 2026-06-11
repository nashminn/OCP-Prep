# Chapter 14: I/O — Answers

---

**1. A**

`getNameCount()` returns the number of elements in the path, not counting the root. For `/data/reports/2024/jan.txt`, the elements are `data`, `reports`, `2024`, `jan.txt` — that's `4`. `getName(0)` is zero-indexed and returns `data`. `getName(2)` returns `2024`. Option B and C miscount the elements or shift the index by treating `data` as the root. Option D omits `jan.txt` from the count. No exception is thrown since indices `0` and `2` are both within the valid range `[0, 3]`.

---

**2. B, D**

`Path` is an interface with no public constructors — instances are obtained via factory methods like `Path.of()`, never `new Path(...)` (B is correct). `File.toPath()` converts a `File` to a `Path` (D is correct). Option A is wrong: `File.delete()` returns a `boolean` and declares no checked exception — it is `Files.delete(Path)` (NIO.2) that throws `IOException`. Option C is wrong: `Path.of("data.txt")` and `new File("data.txt")` can refer to the exact same location on disk if they resolve to the same relative path against the same working directory. Option E is wrong: `Files.exists(Path, LinkOption...)` is a static method on `Files` taking a `Path` plus varargs, while `File.exists()` is a no-argument instance method on `File` — the signatures differ.

---

**3. C**

`p1` is the relative path `animals` and `p2` is the absolute path `/home/zoo/animals`. `Path.equals()` compares path values directly without resolving against the working directory, so a relative path never equals an absolute path — `p1.equals(p2)` is `false`. However, `p1.toAbsolutePath()` combines `p1` with the current working directory. Given the stated working directory of `/home/zoo`, this produces `/home/zoo/animals`, which equals `p2` — so the second comparison is `true`. The result is `false` then `true`.

---

**4. B**

`normalize()` removes redundant `.` and `..` segments using only the textual path — it does not touch the file system or require the path to exist. `/zoo/../safari/./lions.txt` normalizes to `/safari/lions.txt`. Since `Path` instances are immutable, `normalize()` returns a *new* `Path` and does not modify `p`. Therefore the second `println(p)` still prints the original, un-normalized string `/zoo/../safari/./lions.txt`. Option E is wrong because `normalize()` is a purely textual/structural operation and never throws or accesses the file system — that's the role of `toRealPath()`.

---

**5. C**

`startsWith()` and `endsWith()` compare whole path *segments*, not raw character prefixes/suffixes of the string representation. `/habitat/forest/oak` does **not** start with `/habitat/for` because `for` is not a complete segment — it's only part of the segment `forest` — so the first call is `false`. It **does** start with `/habitat/forest` because that is a complete sequence of leading segments — `true`. For `endsWith()`, `oak` is the final complete segment, so `p.endsWith("oak")` is `true`. `forest/oak` represents the final two complete segments, so `p.endsWith("forest/oak")` is also `true`. The result is `false`, `true`, `true`, `true`.

---

**6. A**

`p1` is `/zoo/mammals/../birds` and `p2` is `/zoo/birds`. `equals()` compares the path values literally, including the unresolved `..` segment, so `p1.equals(p2)` is `false`. After `normalize()`, `p1` becomes `/zoo/birds`, which equals `p2` — `true`. `Files.isSameFile()` resolves both paths against the real file system (following symbolic links and resolving `..`); since both `/zoo/mammals` and `/zoo/birds` exist as real directories under `/zoo`, `p1` resolves to the same real location as `p2` — `true`. The result is `false`, `true`, `true`.

---

**7. A**

`resolve()` concatenates the argument onto the path it's called on, *unless* the argument is itself an absolute path, in which case the argument is returned unchanged (think of `resolve()` as concatenation with an absolute-path override). `path1.resolve(path2)` appends the relative path `food/feed.txt` onto `/habitat/zone1`, producing `/habitat/zone1/food/feed.txt`. `path2.resolve(path1)` is called with `path1` (`/habitat/zone1`) as the argument; since `path1` is absolute, the result is simply `path1` itself, `/habitat/zone1`. No exception occurs — mixing absolute and relative arguments to `resolve()` is perfectly legal (it is `relativize()`, not `resolve()`, that throws when mixing absolute and relative paths).

---

**8. B**

`relativize()` computes the steps needed to navigate from one path to another. `p1` is `/zebra/stripes/pattern.txt` (3 segments) and `p2` is `/zebra/legs` (2 segments). To get from `p1` to `p2`, you must go up two levels — out of `pattern.txt` (the file itself counts as one level) and out of `stripes` — and then down into `legs`, giving `../../legs`. To get from `p2` to `p1`, you go up one level — out of `legs` — and then down into `stripes/pattern.txt`, giving `../stripes/pattern.txt`. Both paths are absolute and share the same root, so `relativize()` succeeds without throwing `IllegalArgumentException`.

---

**9. A, D**

`resolveSibling()` replaces the last segment of the path with the given argument — conceptually equivalent to `path.getParent().resolve(other)`. For `p = /data/reports/jan.txt`, `p.resolveSibling("feb.txt")` replaces `jan.txt` with `feb.txt`, producing `/data/reports/feb.txt` (A is correct, B is incorrect — it does not append onto the full original path). `resolveSibling()` is overloaded to accept either a `String` or a `Path` argument, so option E is incorrect. For the second call, the argument `/data/archive/feb.txt` is itself an absolute path; just like `resolve()`, when the argument to `resolveSibling()` is absolute, that absolute path is returned directly, producing `/data/archive/feb.txt` (D is correct, C is incorrect).

---

**10. A**

`subpath(beginIndex, endIndex)` is zero-indexed (like `getName()`) and excludes the root, working similarly to `String.substring()`. For `/wildlife/preserve/animals/lion.dat`, the segments at indices 0–3 are `wildlife`, `preserve`, `animals`, `lion.dat`. `subpath(0, 2)` returns segments 0 through 1 (exclusive of 2): `wildlife/preserve`. `subpath(1, 3)` returns segments 1 through 2: `preserve/animals`. `getName(3)` returns the segment at index 3, `lion.dat` — a valid index since the maximum index here is 3, so no exception is thrown.

---

**11. A**

`getParent()` is a purely structural, string-based operation that does not resolve `.`/`..` symbols — it simply removes the last path element. For `p = /safari/./trucks/../jeep.txt`, removing the trailing element `jeep.txt` leaves `/safari/./trucks/..`. After calling `normalize()` on `p`, the redundant `./trucks/..` portion cancels out, leaving `/safari/jeep.txt`, whose parent is `/safari`. The result is `/safari/./trucks/..` then `/safari`.

---

**12. A**

The `while` loop repeatedly calls `getParent()` until it returns `null`. For an absolute path, `getParent()` strips off one trailing element at a time: `/zoo/exhibits/reptiles/snake.txt` → `/zoo/exhibits/reptiles` → `/zoo/exhibits` → `/zoo` → `/`. Calling `getParent()` on the root path `/` itself returns `null`, terminating the loop — but `/` is printed first, as the last non-null value produced. Option B is incomplete because it omits this final root element. Option E is wrong because `getParent()` on the root eventually does return `null`, so the loop terminates normally.

---

**13. B, D**

`Files.exists()` and `Files.notExists()` are not strict logical opposites: if a path's existence cannot be determined (for example, due to a permissions error or I/O failure while checking), *both* methods can return `false` simultaneously (B is correct). Neither method declares a checked `IOException` — they're designed to never throw for a missing or indeterminate path, since throwing would prevent them from ever returning `false` (C is incorrect). By default (without `LinkOption.NOFOLLOW_LINKS`), `Files.exists()` follows symbolic links; if a symbolic link's target does not exist (a "broken" link), `Files.exists()` on the link itself returns `false` (D is correct). Option A is false precisely because of the "cannot be determined" edge case in B. Option E is too narrow — `notExists()` returning `true` simply means the path can be confirmed not to exist; it isn't restricted to paths that "used to be" regular files.

---

**14. C**

`Files.createFile(p)` creates a new, empty file at the given path and throws `FileAlreadyExistsException` (a subclass of `IOException`) if a file already exists at that path. The first call succeeds, creating `/data/output.txt`. The second call attempts to create the same file again — since it now exists, this throws `FileAlreadyExistsException`, whose simple class name is printed by the `catch` block. `Files.createFile()` does declare `throws IOException`, so option E is incorrect.

---

**15. B**

`Files.createDirectory()` creates exactly one directory level and throws an exception if any parent directory in the path does not already exist. Since neither `/data` nor `/data/logs` exists, `createDirectory(Path.of("/data/logs"))` fails with `NoSuchFileException` because its parent `/data` is missing — printing `createDirectory failed: NoSuchFileException`. By contrast, `Files.createDirectories()` (plural) creates all missing parent directories as needed, so the second call succeeds and prints `Created with createDirectories`. Both methods declare `throws IOException`, so the code compiles fine, ruling out option E.

---

**16. B**

By default, `Files.copy(source, target)` throws `FileAlreadyExistsException` if `target` already exists, unless `StandardCopyOption.REPLACE_EXISTING` is supplied. The first call succeeds because `/zoo/backup.txt` does not yet exist, creating it as a copy of `visitors.txt`. The second call attempts to copy to the same target, which now exists — since no `REPLACE_EXISTING` option was given, this throws `FileAlreadyExistsException`. `copy()`'s `CopyOption...` parameter is an optional varargs, so the zero-argument call shown is perfectly legal, ruling out option E.

---

**17. A**

`StandardCopyOption.REPLACE_EXISTING` instructs `copy()` to overwrite the target if it already exists, instead of throwing `FileAlreadyExistsException`. The first call creates `/zoo/backup.txt` (it didn't exist yet, so `REPLACE_EXISTING` has no visible effect). The second call overwrites the now-existing `backup.txt` with the contents of `visitors.txt` again — since `REPLACE_EXISTING` is specified, no exception is thrown. Both calls succeed, and `Done` is printed.

---

**18. C**

When `Files.copy(source, target)` is called and `target` is an existing directory, the method does **not** place a copy of `source` *inside* that directory. Instead, it treats `target` as the literal destination path for the copied file — effectively trying to create a new entry named `/park/shelter`. Since `/park/shelter` already exists (as a directory) and `REPLACE_EXISTING` was not specified, `Files.copy()` throws `FileAlreadyExistsException` (option C). Option A describes the intuitive-but-wrong expectation — `copy()` never automatically appends the source's filename onto a directory target; to copy *into* a directory, you must explicitly write `Files.copy(file, dir.resolve(file.getFileName()))`.

---

**19. A**

`Files.move()` can move (rename) an entire directory — including a non-empty one — as a single operation; unlike `delete()`/`deleteIfExists()`, `move()` does not require the directory to be empty first. Since `/wolves-archive` does not already exist, the move succeeds: `/wolves` is renamed to `/wolves-archive`, taking all of its contents along with it. After the move, `/wolves` no longer exists (`false`) and `/wolves-archive` does (`true`). The `ATOMIC_MOVE` option only guarantees the rename appears as a single indivisible step to any external observer — it does not change these existence results.

---

**20. A**

`Files.delete(p)` throws `NoSuchFileException` (a subclass of `IOException`) if the path does not exist — caught and printed as `delete: NoSuchFileException`. `Files.deleteIfExists(p)`, by contrast, returns a `boolean`: `true` if a file was actually deleted, `false` if the path did not exist — no exception is thrown either way. Since `/data/temp.txt` does not exist, `deleteIfExists()` returns `false`. `Files.delete()` does declare `throws IOException` (so option E is wrong), and the exception thrown for a missing path is the NIO.2 `NoSuchFileException`, not the legacy I/O `FileNotFoundException` (so option B is wrong).

---

**21. A**

`Files.list(dir)` returns a `Stream<Path>` containing only the *immediate children* of `dir` — it is not recursive, unlike `Files.walk()`. For `/park`, the immediate children are `/park/trail.txt` and `/park/lake`. It does not include `/park` itself, and it does not descend into `/park/lake` to enumerate `dock.txt`, `boats`, or `canoe.txt`. `Files.list()` takes only a `Path` argument with no `maxDepth` parameter (unlike one of the `Files.walk()` overloads), so option D is incorrect, and the stream is properly closed via try-with-resources, so option E is incorrect.

---

**22. B**

`Files.walk(start)` performs a depth-first traversal and, importantly, **includes the starting path itself** as the first element of the resulting stream. For the tree rooted at `/park` — `/park`, `/park/trail.txt`, `/park/lake`, `/park/lake/dock.txt`, `/park/lake/boats`, `/park/lake/boats/canoe.txt` — that's a total of 6 paths (the starting directory plus 5 descendants). `count()` is a perfectly valid terminal operation on a `Stream<Path>` produced by `Files.walk()`, so option E is incorrect.

---

**23. A**

`Files.walk(start, maxDepth)` limits how many levels below `start` are visited. A `maxDepth` of `1` includes `start` itself (depth 0) and its immediate children (depth 1), but nothing deeper. Relative to `/park`: `/park` itself is at depth 0 (a directory, filtered out by `Files.isRegularFile`); `/park/trail.txt` and `/park/lake` are at depth 1 (`trail.txt` is a regular file and passes the filter; `lake` is a directory and is filtered out). `dock.txt` (depth 2) and `canoe.txt` (depth 3) are beyond the depth limit and are never visited at all. Only `/park/trail.txt` is printed. A `maxDepth` of `1` is a perfectly valid non-negative `int`, so option E is incorrect.

---

**24. A**

`Files.find(start, maxDepth, matcher)` walks the directory tree like `walk()` (including the start path) and applies the supplied `BiPredicate<Path, BasicFileAttributes>` to each entry, with Java automatically supplying each path's attributes — so the lambda never needs to call any exception-declaring method itself, and ordinary lambdas are perfectly valid here (option D is incorrect). With `maxDepth = 10` (well beyond the tree's actual depth of 3), every regular file ending in `.txt` is matched: `trail.txt`, `dock.txt`, and `canoe.txt` — exactly three lines are printed. The directories `park`, `lake`, and `boats` are excluded because `attrs.isRegularFile()` is `false` for them. The exact relative order of `trail.txt`, `dock.txt`, and `canoe.txt` depends on the underlying file system's directory-entry ordering and is not guaranteed by the API, but the *set* of three printed names is fixed.

---

**25. B, C**

`Files.lines(path)` returns a lazily-populated `Stream<String>` backed by an open file handle; because it holds a system resource, it must be closed (typically via try-with-resources) to avoid a resource leak (B is correct). `Files.readAllLines(path)` reads the entire file into memory immediately and returns a `List<String>` (C is correct; A is incorrect because `readAllLines()` returns `List`, not `Stream`). Option D has the methods backwards: it's `readAllLines()`'s `List<String>` result that lacks `.filter()` directly (you'd need `.stream()` first) — `Files.lines(path)` returns a `Stream` and supports `.filter()` directly, so D is incorrect. Option E is also backwards: `readAllLines()` is the one that risks `OutOfMemoryError` on very large files; `Files.lines()` is the memory-efficient, lazy alternative — so E is incorrect.

---

**26. A**

`Files.readAllLines(path)` returns a `List<String>`, and `List` provides a `.stream()` method, so calling `.stream().filter(...)` on the result compiles and runs correctly — option B is incorrect (calling `.filter()` *directly* on the `List` without `.stream()` would fail to compile, but that's not what this code does). The file's three lines are `"INFO start"`, `"WARN low memory"`, and `"INFO done"`. The filter retains only lines starting with `"WARN"` — just `"WARN low memory"` — which is the single line printed.

---

**27. A, B, E**

`Files.writeString(path, content)` writes the given `String` to the file, creating it if necessary and overwriting (truncating) it if it already exists — by default it applies `CREATE`, `TRUNCATE_EXISTING`, and `WRITE` (A is correct). `Files.write(path, byte[])` similarly creates-or-overwrites by default and writes the raw bytes of `"Hello, Zoo!"` (B is correct). `Files.write(path, List<? extends CharSequence>)` writes each element of the list as a line (with a trailing line separator), also creating-or-overwriting by default; `List.of("Hello, Zoo!")` satisfies the type bound (E is correct). Option C is wrong: `StandardOpenOption.CREATE_NEW` causes `Files.writeString()` to throw `FileAlreadyExistsException` if the file already exists — the opposite of "overwriting it if it does." Option D is wrong: `FileWriter`'s constructor and `write()` method both declare checked `IOException`, which must be caught or declared — "with no checked exception handling" would not compile.

---

**28. A**

`StringReader` is a character stream that reads from an in-memory `String`, and `BufferedReader` can wrap any `Reader`, including a `StringReader` — so option D is incorrect. `readLine()` reads a line of text with its line terminator stripped, returning `null` only once the end of the stream is reached (it does not throw an exception to signal end-of-stream — option E is incorrect). The input `"alpha\nbeta\n"` produces exactly two lines, `"alpha"` and `"beta"` (a trailing `\n` does not produce a third, empty line). Each line is appended to `sb` followed by `"-"`, producing `"alpha-beta-"`.

---

**29. A, C, E, F**

`new BufferedReader(new FileReader("data.txt"))` is valid: `BufferedReader` wraps any `Reader`, and `FileReader` is a `Reader` (A). `new BufferedInputStream(new FileInputStream("data.txt"))` is valid: `BufferedInputStream` wraps any `InputStream`, and `FileInputStream` is an `InputStream` (C). `new InputStreamReader(new FileInputStream("data.txt"))` is valid: `InputStreamReader` is the special bridge class that converts a byte-based `InputStream` into a character-based `Reader` (E). `new PrintWriter(new FileWriter("data.txt"))` is valid: `PrintWriter` has a constructor that accepts any `Writer`, and `FileWriter` is a `Writer` (F). Option B does not compile: `BufferedWriter` requires a `Writer`, but `FileOutputStream` is an `OutputStream` — mixing character-stream and byte-stream types. Option D does not compile: `ObjectInputStream` requires an `InputStream`, but `FileOutputStream` is an `OutputStream` — mixing input and output types as well as stream families.

---

**30. A**

`ObjectOutputStream.writeObject()` can serialize any `Serializable` object, including the autoboxed `Integer` (from the literal `42`) and the immutable `List<String>` returned by `List.of(...)`, both of which implement `Serializable`. Three objects are written in sequence: the `String` `"hello"`, the `Integer` `42`, and the list `["a", "b"]`. `ObjectInputStream.readObject()` reads them back in the same order they were written. `println()` invokes `toString()` on each: `"hello"`, `"42"`, and `"[a, b]"`. No cast to `Serializable` is required when calling `writeObject(Object obj)` — the parameter type is `Object`, and serializability is checked at runtime, not compile time — so option C is incorrect. Since exactly three objects were written and exactly three are read, no `EOFException` occurs.

---

**31. B**

For an object to be serializable, every non-`transient`, non-`static` instance field must itself be `Serializable` (or hold a `null` value at the time of serialization, applied recursively). `Lion` has a `habitat` field of type `Habitat`, and `Habitat` does **not** implement `Serializable`. Crucially, this is **not** a compile-time error — `Serializable` is a marker interface, and the compiler does not statically verify that all reachable field types are serializable (option C is incorrect). The failure surfaces only at *runtime*, when `writeObject()` attempts to serialize the non-serializable `habitat` field and throws `NotSerializableException`.

---

**32. B**

The root problem is that `Lion.habitat` is of type `Habitat`, which does not implement `Serializable`, causing `NotSerializableException` at runtime (per the previous question). Making `Habitat implements Serializable` (B) directly fixes this: `habitat` becomes a normal serializable field, written and restored along with the rest of `Lion`'s state — each `Lion` instance keeps its own `habitat` data after deserialization. Option A (marking `habitat` `transient`) would also avoid the exception, but `habitat` would become `null` on every deserialized `Lion`, failing the requirement that each instance retain its *own* `habitat` data. Option D (`static`) would likewise avoid the exception, but `habitat` would then be shared across *all* `Lion` instances rather than being per-instance state. Option C (adding `serialVersionUID` to `Habitat`) has no bearing on whether `Habitat` is serializable. Option E (removing `Lion`'s `serialVersionUID`) doesn't address the `Habitat` problem at all.

---

**33. B**

`Car` is `Serializable`, but its superclass `Vehicle` is not — this is allowed as long as `Vehicle` has an accessible no-arg constructor, which it does. During deserialization: `color` is marked `transient`, so it is never written to the stream and reverts to its default value `null` on read-back. `topSpeed` is a normal serializable `int` field, so its value `200` is preserved across the round trip. `wheels` is inherited from the non-serializable `Vehicle`; because `Vehicle` is not serialized at all, Java reconstructs the inherited portion of the object by invoking `Vehicle`'s no-arg constructor during deserialization (not `Car`'s constructor), which sets `wheels = 4`. The result is `null`, `200`, `4`.

---

**34. B**

`value` is marked `transient`, so it is never written to the serialized stream; on deserialization it reverts to its default `int` value, `0`, regardless of its value (`99`) at the time of serialization. `instanceCount` is `static`, and `static` fields are never part of an object's serialized state — they belong to the class as a whole, not to any individual instance. Deserializing `c2` does **not** invoke any constructor of `Counter` (so `instanceCount++` is not executed again) and does not touch the static field at all. After serialization, the code explicitly sets `Counter.instanceCount = 500`; this assignment is the value still in effect when `c2.value` and `Counter.instanceCount` are printed. The output is `0 500`. Accessing the `private` field `value` is permitted here because both `Counter` and `Test` are declared in the same source file (private members are accessible throughout the same top-level compilation unit), so option E is incorrect.

---

**35. B**

`Mammal` is not `Serializable`. `Elephant`'s `name` field is `transient`, so it is never serialized and becomes `null` on deserialization. The `species` field is inherited from `Mammal`. Because `Mammal` is not `Serializable`, none of `Mammal`'s state is part of the serialized stream for an `Elephant`. During deserialization, Java invokes `Mammal`'s **no-arg constructor** — not the `Mammal(String species)` constructor that was originally used to set `species = "African"` — to initialize the inherited portion of the object. `Mammal()` sets `species = "Unknown"`. So after deserialization, `species` is `"Unknown"` and `name` is `null`. `Mammal` does have an accessible no-arg constructor, so `InvalidClassException` is not thrown (option E is incorrect).

---

**36. B, C, D, E**

`Serializable` is a marker interface — it declares **no methods at all**, so a class can implement it trivially even with zero fields (B is correct), and option A is incorrect since there is no `writeObject()` method declared by the interface (custom `writeObject`/`readObject` methods are an optional convention recognized by the serialization machinery, not interface overrides). If a superclass implements `Serializable`, that interface is inherited by all subclasses automatically — a subclass need not redeclare `implements Serializable` to be considered a `Serializable` type (C is correct). Unlike ordinary classes, a `record` is not automatically serializable just because its components happen to be serializable — it must explicitly declare `implements Serializable`, exactly like any other class (D is correct). Marking a field `static` removes it from serialization entirely (static fields are never part of an object's serialized state), but this has no bearing on whether the *class itself* can be serialized (E is correct).

---

**37. A**

A `List` (here, an `ArrayList` constructed via `new ArrayList<>(List.of(...))`) implements `Serializable`, so the entire list — `["text", 100]` — is written as a *single* object via one `writeObject()` call. On the read side, the first `readObject()` call retrieves the whole `List<Object>`, whose `toString()` prints `[text, 100]`. The second `readObject()` call attempts to read a *second* object from the stream, but only one object was ever written — this triggers `EOFException`, caught by the matching `catch` block, which prints `Done`. The `while (true)` loop combined with catching `EOFException` is the standard idiom for reading an unknown number of serialized objects until the stream is exhausted; it compiles and runs as intended (option E is incorrect).

---

**38. A**

`PrintWriter` has a constructor that accepts any `Writer`, so wrapping a `FileWriter` compiles without issue (option E is incorrect). `pw.println("Line 1")` writes `"Line 1"` followed by a line separator. `pw.print(42)` writes `"42"` with no line separator. `pw.println()` (no arguments) writes just a line separator, ending the line containing `"42"`. `pw.printf("Value: %.2f%n", 3.14159)` formats `3.14159` to two decimal places as `3.14`, followed by a platform line separator from `%n`. `printf()` is indeed defined on `PrintWriter` (option D is incorrect). Reading the file back line by line yields exactly three lines — `"Line 1"`, `"42"`, and `"Value: 3.14"` — each wrapped in brackets by the reading loop.

---

**39. B, D**

Both `PrintStream` and `PrintWriter` expose a `checkError()` method, which flushes the stream and reports whether an error occurred during a previous operation — this is how these classes surface problems instead of throwing exceptions (B is correct). `PrintWriter` provides constructors that accept an `OutputStream` directly (in addition to `Writer`-based constructors), as a convenience (D is correct). Option A is false: the defining characteristic of `PrintStream`/`PrintWriter` methods like `println()` is that they declare **no** checked exceptions — errors are tracked silently via `checkError()` instead. Option C is false: `System.out` is a `PrintStream`, not a `PrintWriter`. Option E is false: there is no `PrintReader` class — `PrintWriter` and `PrintStream` are output-only classes with no input counterpart.

---

**40. B**

In a try-with-resources statement that declares multiple resources in a single statement (`out1; out2`), the resources are closed in **reverse order of declaration** — so `out2` is closed before `out1`. Try-with-resources fully supports declaring multiple resources separated by semicolons in one statement, so option D is incorrect. The printed *output*, however, is unaffected by close order: `"Opened both"` is printed inside the `try` block; both resources are then closed silently (in `out2`, then `out1` order) as the block exits; finally, the `finally` block runs and prints `"Finally"`. The overall printed sequence is `Opened both` followed by `Finally`, with the close order `out2` then `out1` — matching option B.

---

**41. A, B, E**

`Closeable.close()` declares `throws IOException`, a *narrowed* version of `AutoCloseable.close()`'s `throws Exception` (A is correct — this is exactly the relationship: `Closeable` exists specifically to provide a more specific, I/O-oriented `close()` signature). `Closeable extends AutoCloseable` (B is correct; option C reverses this relationship and is incorrect). `BufferedReader`, like nearly all I/O stream classes, implements `Closeable` (E is correct). Option D is false: any class implementing `Closeable` automatically also satisfies `AutoCloseable` (since `Closeable extends AutoCloseable`), and can therefore be used in a try-with-resources statement — that's the entire purpose of the interface hierarchy.

---

**42. C**

`System.console()` returns `null` in many common scenarios — for example, when a program's standard input or output has been redirected (such as piping from a file), or when running inside many IDEs that don't attach a real interactive console. Well-written code must check for `null` before using the returned `Console`, exactly as this snippet does (C is correct, directly contradicting B's claim that it's "always" non-null). `Console` has only private constructors and is obtainable solely via the `System.console()` factory method — but the code shown correctly uses that factory method rather than attempting `new Console()`, so it compiles fine (option D is incorrect). Because the `null` check happens before any method is invoked on `console`, no `NullPointerException` can occur in this code (option E is incorrect).

---

**43. B, C**

`Console.readPassword()` returns a `char[]`, not a `String` — a deliberate security measure that avoids placing sensitive password data into the `String` pool, where it could persist in memory longer than intended (B is correct, A is incorrect). As the user types, the characters are not echoed back to the screen, preventing anyone looking at the screen from seeing the password (C is correct). The returned `char[]` is **not** automatically encrypted — it contains the raw characters typed, in a mutable array the caller can zero out after use (D is incorrect). `readPassword()` is overloaded as `readPassword(String fmt, Object... args)`, which *does* accept a format string for displaying a prompt (E is incorrect).

---

**44. A**

`Files.isSymbolicLink(link)` returns `true` because `current.log` is indeed a symbolic link, regardless of whether its target exists. `Files.isRegularFile(link)` follows symbolic links by default, and since the link's target, `2024.log`, is a regular file, this returns `true`. `Files.isRegularFile(link, LinkOption.NOFOLLOW_LINKS)` does *not* follow the symbolic link — it inspects the link entry itself, which is a symbolic link rather than a regular file, so this returns `false`. The result is `true`, `true`, `false`.

---

**45. A**

`Files.readSymbolicLink(link)` returns the *literal target* recorded inside the symbolic link itself — here, the relative path `2024.log` (not the resolved absolute path, and not the link's own name). `Files.exists(link)` follows symbolic links by default; since the target `2024.log` exists, this returns `true`. `Files.exists(link, LinkOption.NOFOLLOW_LINKS)` checks whether the symbolic link *entry itself* exists in the file system (independent of whether its target exists) — since the link was created and exists, this also returns `true`. The result is `2024.log`, `true`, `true`. NIO.2 has full support for creating, detecting, and navigating symbolic links, so option E is incorrect.

---

**46. A**

`Files.readAttributes(path, BasicFileAttributes.class)` retrieves a `BasicFileAttributes` object in a single file-system round trip, exposing methods like `isDirectory()`, `isRegularFile()`, `size()`, and `lastModifiedTime()` — so option D's claim that `size()` doesn't exist is incorrect. For a regular 2,048-byte file, `isDirectory()` returns `false` and `isRegularFile()` returns `true`. `attrs.size()` returns `2048`, matching `Files.size(p)`, also `2048` — so the equality check is `true`. `readAttributes()` works perfectly well on regular files (it isn't restricted to directories), so option E is incorrect.

---

**47. A**

`Files.newDirectoryStream(dir, glob)` returns a `DirectoryStream<Path>` that implements `Iterable<Path>` (so it can be used directly in a for-each loop, as shown — option D is incorrect) and filters the entries of `dir` according to the glob pattern. The directory `/data/exports/2024` contains two subdirectories (`q1`, `q2`) and one file (`summary.txt`). The glob `"*.txt"` matches only entries whose name ends in `.txt`; neither `q1` nor `q2` matches, so only `summary.txt` is printed. `newDirectoryStream()` is specifically designed to operate on directories, so option E is incorrect.

---

**48. B**

`Box<T extends Serializable>` itself implements `Serializable`, and its type parameter `T` is bounded by `Serializable`; a `Box<String>` (where `String` is `Serializable`) serializes without any issue — generic type erasure does not interfere here, so `NotSerializableException` is not thrown (option E is incorrect). The `contents` field (`"Treasure"`) is an ordinary serializable field and is preserved across the round trip. The `createdAt` field is marked `transient`, so it is never written to the stream; on deserialization it reverts to its default `long` value, `0`, regardless of the original timestamp. A field can legally be both `final` and `transient` simultaneously — `final` only constrains assignment (the field is set once, in the constructor), while `transient` only affects serialization, and the two modifiers do not conflict — so option D is incorrect. The output is `Treasure` then `0`.

---

**49. B**

`Files.createDirectory(targetDir)` creates exactly one directory level and requires that the *parent* of `targetDir` already exist. Here `targetDir` is `/processed/archive`, but its parent, `/processed`, does not exist (only `/incoming` exists in this scenario). Therefore `Files.createDirectory(targetDir)` throws `NoSuchFileException` (a subclass of `IOException`), which propagates out of `process()` since the method declares `throws IOException`. To make this code robust against a missing `/processed` directory, `Files.createDirectories()` (plural) should be used instead, since it creates all missing intermediate directories. The code compiles fine as written — `Files.delete()`'s checked `IOException` is already covered by the enclosing method's `throws IOException` clause, so no additional `try`/`catch` is required (option E is incorrect).

---

**50. B**

`Pair` defines custom `writeObject()`/`readObject()` methods with `private` visibility — this is the *correct and required* visibility for these special serialization callback methods, since they are invoked reflectively by the serialization framework rather than via normal polymorphic dispatch. This means option D (claiming they must be `public`) is incorrect, and this combination does not trigger `InvalidClassException` — custom serialization methods coexist normally with `serialVersionUID` (option E is incorrect). `writeObject()` simply calls `out.defaultWriteObject()`, performing standard serialization of `a=3` and `b=4`. `readObject()` calls `in.defaultReadObject()` first, restoring `a=3` and `b=4` from the stream, and then runs custom logic: `this.b = this.a + this.b`, i.e., `b = 3 + 4 = 7`. The final state is `a=3`, `b=7`, printed via `toString()` as `(3,7)`.

---

**51. A**

`ByteArrayInputStream` supports `mark()`/`reset()` (`markSupported()` returns `true`), so the `if` block executes. Reading through `"LIONESS"` byte by byte: the first `read()` returns `'L'`, appended to `sb` → `"L"`. `mark(20)` marks the current position (immediately before `'I'`). `read()` returns `'I'` → `sb = "LI"`. `skip(2)` advances past `'O'` and `'N'`. `read()` returns `'E'` → `sb = "LIE"`. `reset()` then rewinds the stream back to the marked position — immediately before `'I'`. The final `read()` (outside the `if` block) reads `'I'` again → `sb = "LIEI"`. The output is `LIEI`.

---

**52. A, B, D, E**

`File.mkdir()` creates a single directory level and returns `false` (without throwing) if the operation cannot be completed — including the common case where the parent directory does not exist (A is correct; C is incorrect because `mkdir()` never declares or throws `IOException`, signaling failure purely through its `boolean` return value). `File.mkdirs()` creates the target directory along with any necessary, currently-nonexistent parent directories (B is correct). `File.mkdirs()` returns `false` if the entire target directory structure already exists — since in that case it created nothing (D is correct, and is a classic exam trap, since it's easy to assume "already exists" means `true`). Both `mkdir()` and `mkdirs()` return a `boolean` indicating whether the directory (or directory chain) was actually created (E is correct).

---

**53. A**

`new File("/zoo/data.txt")`, `new File("/zoo", "data.txt")`, and `new File(parent, "data.txt")` (where `parent` is `new File("/zoo")`) all construct `File` objects representing the same path, `/zoo/data.txt`. `File.equals()` compares the underlying path strings (case-sensitively on Linux), so `f1.equals(f2)` and `f2.equals(f3)` are both `true`. For `f4`, the two-argument constructor `File(String parent, String child)` has special handling: if `parent` is `null`, it is simply ignored — behaving as if only the single-argument `File(String pathname)` constructor had been called — and since `child` (`/zoo/data.txt`) is itself an absolute path, the resulting path is `/zoo/data.txt`. No `NullPointerException` is thrown for a `null` `parent` argument in this constructor (option D is incorrect), and `File` does have a `(String, String)` constructor (option E is incorrect).

---

**54. B**

`Files.walkFileTree()` performs a depth-first traversal, invoking `preVisitDirectory()` before entering each directory and `visitFile()` for each file encountered. For `/root`, it enters `root` (printing `Enter: root`), and visits both `a.txt` and `c.txt` (printing `File: a.txt` and `File: c.txt`, in some order depending on directory-entry iteration order, which is not specified by the API). When the traversal reaches the `skip` directory, `preVisitDirectory()` prints `Enter: skip` and returns `FileVisitResult.SKIP_SUBTREE` — this instructs the walker to skip the entire contents of `skip` without visiting them, so `visitFile()` is never called for `b.txt`. `SimpleFileVisitor` provides default no-op implementations for all four visitor callback methods, so a subclass may override only the ones it needs — overriding all four is not required (option D is incorrect). No symbolic links are involved in this tree, so `FileSystemLoopException` cannot occur (option E is incorrect).

---

**55. A**

`Path.of("reports", "2024", "summary.txt")` and `Path.of("reports/2024/summary.txt")` both represent the identical logical path — `reports/2024/summary.txt` — built from the same three segments in the same order, joined with the platform's separator. `Path.equals()` compares the segment structure, so `p1.equals(p2)` is `true`. Their `toString()` representations are also identical strings (`"reports/2024/summary.txt"`), so `p1.toString().equals(p2.toString())` is also `true`. Neither path begins with a leading separator, so both are *relative* paths: `getRoot()` returns `null` and `isAbsolute()` returns `false`. The result is `true`, `true`, `null`, `false`. `Path.of(String, String...)` explicitly accepts a varargs list of additional path segments, so option E is incorrect.

---
