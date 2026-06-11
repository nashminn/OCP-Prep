# Chapter 12: Modules — Practice Questions

---

**1.** Given the following `module-info.java`, which statement is true?

```java
module com.zoo.feeding {
   exports com.zoo.feeding.api;
   requires com.zoo.animals;
   exports com.zoo.feeding.internal to com.zoo.staff;
}
```

A. The code does not compile because `requires` must appear before any `exports` directive.

B. The code does not compile because directives must be grouped by type.

C. The code compiles; directives may appear in any order.

D. The code does not compile because a module cannot have two `exports` directives.

E. The code does not compile because `exports ... to` must appear before plain `exports`.

---

**2.** Which `module-info.java` fails to compile?

A.
```java
module a {
   requires b;
   requires c;
}
```

B.
```java
module a {
   exports a.one;
   exports a.two;
}
```

C.
```java
module a {
   requires b;
   requires transitive b;
}
```

D.
```java
module a {
   opens a.one;
   opens a.two to b;
}
```

E.
```java
module a {
   requires b;
   exports a.one to b;
}
```

---

**3.** Given the following module declaration, what is the result?

```java
module com.example.app {
   requires com.example.util;
   requires com.example.util;
}
```

A. The module compiles; the duplicate `requires` is simply ignored.

B. The module compiles only if `com.example.util` exports at least one package.

C. The code does not compile because the same module is required twice.

D. The code does not compile because `requires` cannot reference a module starting with `com.example`.

E. The module compiles, but a runtime warning is printed about the duplicate.

---

**4.** Which of the following are valid directive keywords inside a `module-info.java` declaration? (Choose three.)

A. `imports`

B. `exports`

C. `requires`

D. `includes`

E. `opens`

F. `extends`

---

**5.** What is the correct order requirement, if any, for directives in a module declaration such as `requires`, `exports`, `opens`, `uses`, and `provides`?

A. `requires` directives must come first, followed by `exports`, then `opens`, then `uses`, then `provides`.

B. `exports` and `opens` must come before any `requires` directives.

C. `uses` must immediately precede any matching `provides` directive.

D. There is no required order; directives may appear in any sequence as long as there are no duplicates for the same target.

E. `provides` must be the last directive in the file.

---

**6.** Given three modules:

```java
// module-info.java for zoo.alpha
module zoo.alpha {
   exports zoo.alpha.api;
}
```

```java
// module-info.java for zoo.beta
module zoo.beta {
   exports zoo.beta.api;
   requires zoo.alpha;
}
```

```java
// module-info.java for zoo.gamma
module zoo.gamma {
   requires zoo.beta;
}
```

Can code in `zoo.gamma` directly reference a public class in the `zoo.alpha.api` package?

A. Yes, because `zoo.gamma` requires `zoo.beta`, which requires `zoo.alpha`.

B. Yes, because all `requires` relationships are transitive by default.

C. No, because `zoo.beta` uses plain `requires` for `zoo.alpha`, so the dependency is not passed on.

D. No, because `zoo.gamma` does not export `zoo.alpha.api`.

E. Yes, but only if `zoo.gamma` also declares `opens zoo.alpha.api`.

---

**7.** Given the same three modules as the previous question, which single change to `zoo.beta`'s `module-info.java` would allow `zoo.gamma` to reference `zoo.alpha.api` types without `zoo.gamma` declaring `requires zoo.alpha` itself?

A. Change `exports zoo.beta.api;` to `exports zoo.beta.api to zoo.gamma;`

B. Change `requires zoo.alpha;` to `requires transitive zoo.alpha;`

C. Add `opens zoo.alpha.api;` to `zoo.beta`

D. Add `exports zoo.alpha.api;` to `zoo.beta`

E. Change `requires zoo.alpha;` to `requires static zoo.alpha;`

---

**8.** A library module declares:

```java
module com.example.metrics {
   requires static com.fasterxml.jackson.databind;
   exports com.example.metrics.api;
}
```

Which statement best describes the effect of `requires static`?

A. `com.fasterxml.jackson.databind` is required at both compile time and runtime, but cannot be re-exported transitively.

B. `com.fasterxml.jackson.databind` is required at compile time only; at runtime the application will fail to start if it is missing.

C. `com.fasterxml.jackson.databind` is required at compile time, but is optional at runtime — the application can run without it as long as the optional code path is not exercised.

D. `com.fasterxml.jackson.databind` is required at runtime only; it is not visible to the compiler.

E. `requires static` is not legal syntax inside a module declaration.

---

**9.** Module `report.engine` declares:

```java
module report.engine {
   requires static report.charts;
   exports report.engine.api;
}
```

Class `ReportBuilder` in `report.engine.api` has a method that references a type from `report.charts` only inside an `if` branch that is rarely executed. The `report.charts` module is present at compile time but is **not** placed on the module path at runtime. What happens?

A. The application fails to launch because `report.charts` is missing.

B. The application launches successfully; a `NoClassDefFoundError` (or similar) only occurs if and when the code path referencing `report.charts` types is actually executed.

C. The compiler rejects `requires static` because optional dependencies are not permitted in named modules.

D. The JVM silently substitutes an empty stub module for `report.charts`.

E. The application launches, but every method in `ReportBuilder` throws `NoClassDefFoundError` immediately upon class loading.

---

**10.** Which statement about `requires transitive` is correct?

A. It allows a module to be required twice without a compile error.

B. It makes the required module's packages automatically exported to all other modules, regardless of the `exports` directive.

C. Any module that `requires` the module declaring `requires transitive X` will also be able to read `X`, without declaring its own dependency on `X`.

D. It is equivalent to `opens ... to` for reflection purposes.

E. It is only valid when the required module is `java.base`.

---

**11.** Given the following module declaration, which modules (other than `zoo.core` itself) can access **public** types in the package `zoo.core.internal`?

```java
module zoo.core {
   exports zoo.core.api;
   exports zoo.core.internal to zoo.admin, zoo.audit;
}
```

A. Every module that requires `zoo.core`.

B. Only `zoo.admin`.

C. Only `zoo.admin` and `zoo.audit`, and only if they declare `requires zoo.core`.

D. `zoo.admin`, `zoo.audit`, and any module that uses reflection.

E. No module, because qualified exports never grant compile-time access.

---

**12.** A module declares:

```java
module zoo.config {
   exports zoo.config.api;
   opens zoo.config.model to zoo.persistence;
}
```

Which statement is true?

A. Code in `zoo.persistence` can call `new SomeModelClass()` directly at compile time on a class in `zoo.config.model`.

B. A framework in `zoo.persistence` that uses reflection (e.g., to set private fields) can access `zoo.config.model` types at runtime, but `zoo.config.model` is not exported for normal compile-time use by any module.

C. `opens ... to` grants both compile-time and runtime access exclusively to `zoo.persistence`.

D. `zoo.config.model` is automatically exported to all modules because it is opened to at least one module.

E. The declaration does not compile because a module cannot both `exports` and `opens` different packages.

---

**13.** What is the difference, if any, between these two declarations?

```java
// Version 1
module zoo.reports {
   exports zoo.reports.api;
}

// Version 2
open module zoo.reports {
   exports zoo.reports.api;
}
```

A. There is no difference; `open` is a no-op modifier kept for backward compatibility.

B. In Version 2, every package in the module (not just `zoo.reports.api`) is open for deep reflection at runtime, in addition to the normal export of `zoo.reports.api`.

C. Version 2 does not compile because `open module` cannot also use `exports`.

D. In Version 2, `zoo.reports.api` is opened but no longer exported.

E. Version 2 makes every package in the module exported, not just opened.

---

**14.** Which of the following does **not** compile?

A.
```java
open module zoo.alpha {
   exports zoo.alpha.api;
}
```

B.
```java
open module zoo.alpha {
   opens zoo.alpha.internal;
}
```

C.
```java
module zoo.alpha {
   opens zoo.alpha.internal to zoo.beta;
}
```

D.
```java
module zoo.alpha {
   exports zoo.alpha.api to zoo.beta, zoo.gamma;
}
```

E.
```java
module zoo.alpha {
   opens zoo.alpha.internal;
   exports zoo.alpha.api;
}
```

---

**15.** A module declares `exports zoo.medical.records;` (an unqualified export). According to Table 12.3-style access rules, which members of a `public` class in `zoo.medical.records` are visible to code in another module that `requires` this module?

A. Only `public` members.

B. `public` and `protected` members.

C. `public`, `protected`, and package-private members.

D. All members, including `private`, because `exports` removes all access restrictions.

E. No members; `exports` only makes the class name visible, not its members.

---

**16.** Given:

```java
module zoo.alpha {
   exports zoo.alpha.api;
   exports zoo.alpha.api to zoo.beta;
}
```

What is the result?

A. The code compiles; `zoo.alpha.api` is exported to everyone, and the second line is redundant but harmless.

B. The code does not compile because the same package cannot be the target of two `exports` directives.

C. The code compiles; `zoo.alpha.api` is exported only to `zoo.beta`, overriding the unqualified export.

D. The code does not compile because qualified exports must come before unqualified exports.

E. The code compiles; the second `exports` is silently ignored.

---

**17.** Consider this multi-module project layout:

```
mods/
  zoo.animal.feeding/
    module-info.java     -> module zoo.animal.feeding { exports zoo.animal.feeding; }
    zoo/animal/feeding/Task.java
  zoo.animal.care/
    module-info.java     -> module zoo.animal.care {
                                requires zoo.animal.feeding;
                                exports zoo.animal.care.medical;
                             }
    zoo/animal/care/medical/Diet.java
    zoo/animal/care/details/HippoBirthday.java
```

`HippoBirthday.java` (in package `zoo.animal.care.details`, which is **not** exported) imports `zoo.animal.feeding.Task` and uses it as a field type. Will `zoo.animal.care` compile?

A. No, because `zoo.animal.care.details` is not exported, so it cannot use types from `zoo.animal.feeding`.

B. Yes — `requires` grants access to exported packages of the required module to **all** code inside the requiring module, exported or not.

C. No, because `HippoBirthday` would need its own `module-info.java`.

D. Yes, but only if `zoo.animal.care.details` is also listed in an `exports` directive.

E. No, because `zoo.animal.feeding.Task` contains a `main` method and cannot be referenced as a field type.

---

**18.** Which of the following module names is **illegal**?

A. `zoo.animal.feeding`

B. `com.example.app2`

C. `zoo-animal-feeding`

D. `zoo`

E. `com.example.v2.utils`

---

**19.** A developer places a JAR named `commons-collections-4.4.jar` (with no `module-info.class` and no `Automatic-Module-Name` entry in its manifest) on the **module path**. What module name will Java derive for it?

A. `commons-collections-4.4`

B. `commons.collections.4.4`

C. `commons.collections`

D. `commons-collections`

E. `commonscollections`

---

**20.** A JAR file is named `data-sync-2.1.0-RC1.jar` and its `META-INF/MANIFEST.MF` does **not** contain an `Automatic-Module-Name` entry. What automatic module name does Java derive when this JAR is placed on the module path?

A. `data-sync-2.1.0-RC1`

B. `data.sync.2.1.0.RC1`

C. `data.sync`

D. `data.sync.RC1`

E. `data-sync`

---

**21.** A JAR file `util_$pkg-1.0.jar` is placed on the module path. Its manifest has no `Automatic-Module-Name` entry. Following the algorithm (strip extension, strip trailing version, replace non-alphanumeric runs with a single dot, strip leading/trailing dots), what is the resulting module name?

A. `util_$pkg`

B. `util..pkg`

C. `util.pkg`

D. `util.$pkg`

E. `util_pkg`

---

**22.** A module's `module-info.java` declares `requires foo.bar;`, but no JAR on the module path provides a module named `foo.bar`, nor does any JAR on the module path have a manifest entry or filename that would produce that automatic module name. What happens when you try to compile this module?

A. The module compiles, but fails at runtime with `NoClassDefFoundError`.

B. The module compiles with a warning; `foo.bar` is treated as an empty module.

C. The module fails to compile — `javac` reports that module `foo.bar` cannot be found.

D. The module compiles only if `foo.bar` is also on the classpath.

E. The module compiles, and `foo.bar` is treated as the unnamed module.

---

**23.** A module `zoo.alpha` declares `exports zoo.alpha.widgets;`, but the `zoo.alpha` module's source tree contains no package (and thus no compiled classes) named `zoo.alpha.widgets` — it was a typo for `zoo.alpha.widget`. What happens when compiling `zoo.alpha`?

A. The module compiles; `exports` of an empty/non-existent package is simply ignored.

B. The module fails to compile because you cannot export a package that does not exist in the module.

C. The module compiles, but any module that requires `zoo.alpha` will fail at runtime.

D. The module compiles, and `zoo.alpha.widgets` is created as an empty package automatically.

E. The compiler renames the export to the closest matching package name.

---

**24.** Module `zoo.a` and module `zoo.b` are on the module path. `zoo.a`'s `module-info.java` contains `requires zoo.b;`, and `zoo.b`'s `module-info.java` contains `requires zoo.a;`. Neither module exports anything used reflectively. What happens?

A. Both modules compile fine; circular dependencies between modules are allowed as long as no actual classes reference each other.

B. Neither module can compile — the Java module system disallows cyclic dependencies between modules.

C. Only the module compiled second fails; the first one compiles successfully.

D. Both compile, but `java` refuses to launch any class from either module at runtime.

E. The compiler automatically merges the two modules into one.

---

**25.** Modules `ballA`, `ballB`, and `ballC` have the following declarations:

```java
module ballA { requires ballB; }
module ballB { requires ballC; }
module ballC { requires ballA; }
```

What is the result?

A. This compiles fine — a three-module chain is not considered "cyclic," only direct two-module cycles are disallowed.

B. This does not compile — `ballA` → `ballB` → `ballC` → `ballA` forms a cyclic dependency, which is disallowed regardless of chain length.

C. This compiles, but only `ballA` can be run with `java -m`.

D. This compiles because none of the modules use `requires transitive`.

E. This does not compile, but only because there are exactly three modules — a two-module or four-module cycle would be allowed.

---

**26.** Two separate modules, `zoo.animal.feeding` and `zoo.animal.care`, both place a class named `zoo.animal.shared.Constants` in the package `zoo.animal.shared`, and both modules are placed on the module path of the same application. What happens?

A. Java merges the two `zoo.animal.shared` packages into one at runtime.

B. This is a "split package" and results in a compile-time or runtime error — a package cannot exist in more than one module on the module path.

C. Both versions of `Constants` are loaded, and the last one on the module path wins.

D. This is allowed as long as the two `Constants` classes have identical bytecode.

E. This is allowed because `zoo.animal.shared` is not exported by either module.

---

**27.** A class in package `com.example.legacy` exists in `legacy.jar`, which is placed on the **classpath**. A separate named module `com.example.modern`, placed on the **module path**, also defines a package `com.example.legacy` (not exported). Is this a split-package error?

A. Yes — split packages are disallowed regardless of whether the JARs are on the module path or classpath.

B. No — split package checks only apply between modules on the module path; a classpath/module-path split of the same package name is allowed (though discouraged), because the unnamed module and named modules are resolved differently.

C. No, because packages on the classpath are renamed automatically to avoid conflicts.

D. Yes, but only if both packages export public classes.

E. No, because `com.example.legacy` is not exported by `com.example.modern`.

---

**28.** Which statement about the `java.base` module is correct?

A. `java.base` cannot be referenced at all in a `module-info.java` file — doing so is a compile error.

B. Every module automatically reads `java.base`; explicitly writing `requires java.base;` is legal but redundant.

C. `requires java.base;` must be the first line of every `module-info.java`.

D. `java.base` must be exported explicitly by every module that uses `java.util` types.

E. `java.base` is only available to modules that declare `requires transitive java.base;`.

---

**29.** Given:

```java
module zoo.animal.feeding {
   exports zoo.animal.feeding;
}
```

When you run `java -p mods -d zoo.animal.feeding` (or `--describe-module`), which line would you expect to see in the output that does **not** appear literally in the source `module-info.java`?

A. `exports zoo.animal.feeding`

B. `module zoo.animal.feeding`

C. `requires java.base mandated`

D. `opens zoo.animal.feeding`

E. `provides zoo.animal.feeding`

---

**30.** A module `zoo.animal.care` has the following declaration:

```java
module zoo.animal.care {
   exports zoo.animal.care.medical to zoo.staff;
   requires transitive zoo.animal.feeding;
}
```

and contains two packages: `zoo.animal.care.medical` and `zoo.animal.care.details`. When described with `java -p mods -d zoo.animal.care`, which line would correctly describe the `zoo.animal.care.details` package?

A. `exports zoo.animal.care.details`

B. `opens zoo.animal.care.details`

C. `contains zoo.animal.care.details`

D. `requires zoo.animal.care.details`

E. `hidden zoo.animal.care.details`

---

**31.** A service-based application has the following four modules. Which directive(s) are missing for the service to function correctly via `ServiceLoader`?

```java
// zoo.tours.api
module zoo.tours.api {
   exports zoo.tours.api;
}

// zoo.tours.agency (service provider)
module zoo.tours.agency {
   requires zoo.tours.api;
   provides zoo.tours.api.Tour with zoo.tours.agency.TourImpl;
}

// zoo.tours.reservations (service locator)
module zoo.tours.reservations {
   exports zoo.tours.reservations;
   requires zoo.tours.api;
}

// zoo.visitor (consumer)
module zoo.visitor {
   requires zoo.tours.api;
   requires zoo.tours.reservations;
}
```

A. `zoo.tours.agency` is missing `exports zoo.tours.agency;`

B. `zoo.tours.reservations` is missing `uses zoo.tours.api.Tour;`

C. `zoo.visitor` is missing `provides zoo.tours.api.Tour with zoo.tours.agency.TourImpl;`

D. `zoo.tours.api` is missing `uses zoo.tours.api.Tour;`

E. `zoo.tours.agency` is missing `requires zoo.tours.reservations;`

---

**32.** A `TourImpl` class is the sole implementation of the `Tour` service interface, declared as:

```java
package zoo.tours.agency;

public class TourImpl implements zoo.tours.api.Tour {
   private TourImpl() { }

   public static TourImpl getInstance() {
      return new TourImpl();
   }
   // implementations of Tour methods omitted
}
```

The module declares `provides zoo.tours.api.Tour with zoo.tours.agency.TourImpl;`. At runtime, `ServiceLoader.load(Tour.class)` is called. What happens?

A. `TourImpl` is found and instantiated normally because `getInstance()` is treated as the provider method.

B. `ServiceLoader` throws a `ServiceConfigurationError` at lookup/iteration time because `TourImpl` has no `public` no-arg constructor and no `public static provider()` method.

C. `ServiceLoader` silently skips `TourImpl` and returns an empty result with no error.

D. The module fails to compile because `provides ... with` requires a `public` no-arg constructor at compile time.

E. `ServiceLoader` calls the `private` constructor via reflection without issue, because `provides` grants implicit reflective access.

---

**33.** Which change to the `TourImpl` class from the previous question would make it usable by `ServiceLoader` **without** providing a `public` no-arg constructor?

A. Add `opens zoo.tours.agency;` to the module declaration.

B. Add a `public static Tour provider()` method to `TourImpl` that returns `TourImpl.getInstance()`.

C. Change `provides ... with` to `uses ... with`.

D. Make the `private` constructor `protected` instead.

E. Nothing else is needed; `ServiceLoader` always falls back to `getInstance()` if present.

---

**34.** What does the following code print, given that exactly one provider implementing `Tour` (with `length()` returning `120`) is available on the module path?

```java
ServiceLoader.load(Tour.class)
   .stream()
   .map(Provider::get)
   .mapToInt(Tour::length)
   .max()
   .ifPresent(System.out::println);
```

A. `Provider`

B. `120`

C. Nothing — `stream()` does not exist on `ServiceLoader`.

D. `OptionalInt[120]`

E. A compile error, because `mapToInt` cannot be called on a `Stream<Provider<Tour>>`.

---

**35.** A consumer module wants to use `ServiceLoader.load(Tour.class)` to find implementations of `zoo.tours.api.Tour`. Which directive **must** appear in the consumer's `module-info.java` for this lookup to succeed (assuming `requires zoo.tours.api;` is already present)?

A. `provides zoo.tours.api.Tour with zoo.tours.agency.TourImpl;`

B. `uses zoo.tours.api.Tour;`

C. `opens zoo.tours.api to java.base;`

D. `exports zoo.tours.api;`

E. `requires transitive zoo.tours.api;`

---

**36.** Suppose an application already has working modules for the service provider interface, one service provider, the service locator, and a consumer. A second, independent team builds and deploys a new service provider module implementing the same interface. How many of the **existing** four modules must be recompiled for the application to recognize and use the new provider?

A. Zero — the service locator discovers the new provider via `ServiceLoader` at runtime without recompilation.

B. One — only the service locator.

C. Two — the service locator and the consumer.

D. Three — everything except the service provider interface module.

E. Four — all existing modules must be recompiled.

---

**37.** Which pair of artifacts together make up "the service" (as opposed to a service provider or consumer), per the OCP definitions?

A. The service provider and the consumer.

B. The service provider interface and the service locator.

C. The consumer and the service provider interface.

D. The service provider and the service provider interface.

E. The consumer and the service locator.

---

**38.** Which command correctly compiles a module named `zoo.animal.feeding`, whose source files are under `feeding/zoo/animal/feeding/` plus `feeding/module-info.java`, placing class files into a directory named `feeding-out`, with `mods` as the module path for any dependencies?

A. `javac -d feeding-out -p mods feeding/zoo/animal/feeding/*.java feeding/module-info.java`

B. `javac -cp mods -d feeding-out feeding/zoo/animal/feeding/*.java`

C. `javac --module feeding-out -p mods feeding/module-info.java`

D. `javac -d feeding-out feeding/module-info.java`

E. `javac -p mods feeding-out feeding/zoo/animal/feeding/*.java feeding/module-info.java`

---

**39.** Which command correctly runs the `Task` class (in package `zoo.animal.feeding`) inside the module `zoo.animal.feeding`, using `mods` as the module path?

A. `java -p mods -m zoo.animal.feeding/zoo.animal.feeding.Task`

B. `java -cp mods zoo.animal.feeding.Task`

C. `java -p mods zoo.animal.feeding/zoo.animal.feeding.Task`

D. `java -m mods -p zoo.animal.feeding/zoo.animal.feeding.Task`

E. `java -p mods -m zoo.animal.feeding:zoo.animal.feeding.Task`

---

**40.** What is the result of the following command, where `cat-1.2.3-RC1.jar` exists in `mods` and is a valid module JAR?

```bash
java --module-path mods -p extra -m zoo.animal.feeding/zoo.animal.feeding.Task
```

A. The command runs normally; `--module-path` and `-p` are simply combined into one search path.

B. The command fails because `--module-path` and `-p` are the same option specified twice, which is not allowed.

C. The command runs, but only `extra` is searched because the second occurrence wins.

D. The command runs, but only `mods` is searched because the first occurrence wins.

E. The command fails because `-m` requires `--module-path` to use the long form.

---

**41.** Which command creates a modular JAR file named `mods/zoo.animal.care.jar` from compiled classes in the `care/` directory (including `care/module-info.class`)?

A. `jar -cvf mods/zoo.animal.care.jar -C care/ .`

B. `jar -cvf care/ mods/zoo.animal.care.jar`

C. `jar --create --module mods/zoo.animal.care.jar care/`

D. `jar -p mods/zoo.animal.care.jar -C care/ .`

E. `jar -cvf mods/zoo.animal.care.jar care/*`

---

**42.** Which two commands are equivalent ways to describe a module named `zoo.animal.feeding` whose JAR is in the `mods` directory? (Choose two.)

A. `java -p mods -d zoo.animal.feeding`

B. `java -p mods --module zoo.animal.feeding`

C. `java -p mods --describe-module zoo.animal.feeding`

D. `jar --file mods/zoo.animal.feeding.jar --list-modules`

E. `jdeps --describe-module mods/zoo.animal.feeding.jar`

---

**43.** A team has a non-modular `legacy.jar` and wants to know which JDK modules it would need to declare with `requires` if it were converted into a module, without seeing the full per-class dependency table. Which command best accomplishes this?

A. `jdeps legacy.jar`

B. `jdeps -s legacy.jar` (or `jdeps -summary legacy.jar`)

C. `jdeps --jdk-internals legacy.jar`

D. `jmod describe legacy.jar`

E. `java --describe-module legacy.jar`

---

**44.** Which command builds a custom runtime image containing module `zoo.animal.talks` and its dependencies, placing the result in a directory named `zooApp`, given that the custom modules are in `mods`?

A. `jlink --module-path mods --add-modules zoo.animal.talks --output zooApp`

B. `jlink --module-path mods --output zooApp zoo.animal.talks`

C. `jlink -p mods -m zoo.animal.talks -o zooApp`

D. `jpackage --module-path mods --add-modules zoo.animal.talks --output zooApp`

E. `jmod create --module-path mods --add-modules zoo.animal.talks zooApp`

---

**45.** What is the key practical difference between `jlink` and `jpackage`?

A. `jlink` can package non-modular applications, while `jpackage` cannot.

B. `jpackage` produces a runtime image (a directory of folders), while `jlink` produces a single platform-specific executable/installer.

C. `jlink` produces a runtime image (a directory), while `jpackage` produces a self-contained, platform-specific application image (e.g., `.exe`, `.dmg`); `jpackage` can also handle non-modular apps.

D. `jlink` and `jpackage` are aliases for the same underlying tool.

E. `jlink` requires a full JDK on the target machine, while `jpackage` does not.

---

**46.** A class in module `zoo.reports` (which does **not** declare `requires zoo.charts.legacy`) needs read access to module `zoo.charts.legacy` for a one-time diagnostic build, without modifying any `module-info.java` files. Which `java` launcher flag accomplishes this?

A. `--add-exports zoo.charts.legacy/zoo.charts.legacy.api=zoo.reports`

B. `--add-reads zoo.reports=zoo.charts.legacy`

C. `--add-modules zoo.charts.legacy=zoo.reports`

D. `--add-opens zoo.reports/zoo.reports.api=zoo.charts.legacy`

E. `--patch-module zoo.reports=zoo.charts.legacy`

---

**47.** A third-party library module `com.vendor.lib` does **not** export its internal package `com.vendor.lib.internal`, but your application module `com.app` needs to call a public method in a class in that package at compile time and runtime, and you cannot modify the vendor's `module-info.java`. Which `javac`/`java` flag combination allows this (for both compiling and running)?

A. `--add-opens com.vendor.lib/com.vendor.lib.internal=com.app`

B. `--add-exports com.vendor.lib/com.vendor.lib.internal=com.app`

C. `--add-reads com.app=com.vendor.lib.internal`

D. `--add-modules com.vendor.lib.internal`

E. `--patch-module com.app=com.vendor.lib.internal`

---

**48.** A framework like Hibernate needs to use reflection to access **private fields and constructors** of entity classes in package `com.app.entities`, which is in module `com.app` but is not opened in `module-info.java`, and you cannot modify the application's source. Which JVM flag at launch time grants this reflective access?

A. `--add-exports com.app/com.app.entities=ALL-UNNAMED`

B. `--add-opens com.app/com.app.entities=ALL-UNNAMED` (or to the specific Hibernate module)

C. `--add-reads com.app=hibernate.core`

D. `--patch-module com.app.entities=hibernate.core`

E. `--add-modules com.app.entities`

---

**49.** Which JVM flag is used to add additional classes or resources into an existing module's runtime package structure, commonly used for testing by overlaying compiled test classes onto a module?

A. `--add-modules`

B. `--add-reads`

C. `--patch-module`

D. `--upgrade-module-path`

E. `--add-exports`

---

**50.** A team is migrating a four-JAR application. JAR `core.jar` has no dependencies on the other three. JAR `service.jar` depends on `core.jar`. JAR `web.jar` depends on `service.jar` and `core.jar`. The team controls all four JARs and wants to migrate carefully, fully modularizing each JAR (with proper `exports`/`requires`) before moving to the next. Which migration strategy and starting point are correct?

A. Top-down migration, starting with `web.jar`.

B. Bottom-up migration, starting with `core.jar`.

C. Bottom-up migration, starting with `web.jar`.

D. Top-down migration, starting with `core.jar`.

E. Either strategy works identically since there are no cyclic dependencies.

---

**51.** A team does **not** control the source code of `vendor.jar`, which their application (`app.jar`) depends on. They want `app.jar` to become a proper named module as soon as possible, even though `vendor.jar` cannot be modularized yet. Which approach matches a top-down migration?

A. Leave both JARs on the classpath; add a `module-info.java` only to `vendor.jar`.

B. Place both JARs on the module path; add a `module-info.java` to `app.jar` (referencing `vendor.jar` by its automatic module name), leaving `vendor.jar` as an automatic module.

C. Place `app.jar` on the classpath and `vendor.jar` on the module path.

D. Add `module-info.java` files to both JARs simultaneously.

E. Leave `app.jar` on the classpath as an unnamed module and put only `vendor.jar` on the module path.

---

**52.** During a bottom-up migration, which statement correctly describes the state of modules partway through the process?

A. Modules already migrated are unnamed modules on the classpath; modules not yet migrated are named modules on the module path.

B. Modules already migrated are named modules on the module path; modules not yet migrated remain unnamed modules on the classpath, and named modules cannot access the unnamed modules.

C. All modules, migrated or not, are automatic modules on the module path.

D. Modules already migrated are automatic modules; modules not yet migrated are named modules.

E. Bottom-up migration requires all modules to be placed on the module path from the start.

---

**53.** Three modules exist: `zoo.alpha` (no dependencies), `zoo.beta` (depends on `zoo.alpha` via `requires transitive`), and `zoo.gamma` (depends on `zoo.beta` via plain `requires`). A fourth module, `zoo.delta`, adds `requires zoo.gamma;`. Which modules does `zoo.delta` automatically read as a result of implied readability (`requires transitive`), without declaring its own `requires` for them?

A. `zoo.alpha`, `zoo.beta`, and `zoo.gamma`

B. `zoo.beta` and `zoo.gamma` only

C. `zoo.gamma` only — implied readability does not propagate further because `zoo.gamma` uses plain `requires` for `zoo.beta`

D. `zoo.alpha` and `zoo.gamma` only

E. None — `requires transitive` only affects modules one level away

---

**54.** Given the chain in the previous question (`zoo.alpha` ← `requires transitive` ← `zoo.beta` ← `requires` ← `zoo.gamma`), if `zoo.gamma`'s declaration is changed to `requires transitive zoo.beta;`, and `zoo.delta` still has only `requires zoo.gamma;`, which modules does `zoo.delta` now automatically read?

A. Only `zoo.gamma`

B. `zoo.gamma` and `zoo.beta`, but not `zoo.alpha`

C. `zoo.gamma`, `zoo.beta`, and `zoo.alpha` — because `requires transitive` relationships chain together

D. Only `zoo.alpha`, because transitive requirements skip directly to the bottom of the chain

E. None of the above — `zoo.delta` must explicitly require all three

---

**55.** A framework uses reflection to instantiate a `record` type `com.app.dto.UserDto` declared in module `com.app`, calling its canonical constructor reflectively. The package `com.app.dto` is `exports`-ed but **not** `opens`-ed. The record's components are all `private final` (as records require), and the canonical constructor is implicitly `public` (matching the record's access level). What happens when the framework (in a different module) attempts `Constructor.newInstance(...)` on the canonical constructor via reflection at runtime?

A. It succeeds, because `exports` alone is sufficient for a `public` constructor to be invoked reflectively.

B. It throws `InaccessibleObjectException` (or similar), because reflective access — even to `public` members — requires the package to be `opens`-ed to the calling module (or opened generally).

C. It succeeds only if the record is declared in an `open module`.

D. It fails to compile, because records cannot be accessed via reflection.

E. Both B and C are correct, but A is not.

---

**56.** Module `zoo.alpha` is on the module path with:

```java
module zoo.alpha {
   exports zoo.alpha.api;
}
```

A separate JAR, `helper.jar`, has no `module-info.class` and is placed on the **classpath** (not the module path) of the same application. Code in `zoo.alpha` wants to call a public class in `helper.jar`. Is this possible?

A. Yes — named modules on the module path can freely read classes from JARs on the classpath.

B. No — code in a named module on the module path cannot read the unnamed module (classpath); only the reverse is true.

C. Yes, but only if `zoo.alpha` declares `opens` for the relevant package.

D. Yes, but only if `helper.jar` declares an `Automatic-Module-Name`.

E. No — mixing the module path and classpath in the same application is not permitted at all.

---

**57.** Which of the following are true about an **automatic module** (a JAR with no `module-info.java`, placed on the module path)? (Choose two.)

A. It exports all of its packages to every other named module.

B. It exports no packages at all, similar to an unnamed module.

C. Its module name is always derived solely from the JAR filename, ignoring the manifest.

D. A named module can declare `requires` on an automatic module's name.

E. It cannot be read by any other module on the module path.

---

**58.** Which of the following are true about the **unnamed module** (classpath JARs with no module declaration, or whose `module-info.java` is ignored)? (Choose two.)

A. It can read classes from both the classpath and the module path.

B. Named modules can declare `requires` on the unnamed module by name `ALL-UNNAMED`.

C. It exports no packages to named modules.

D. If a JAR on the classpath happens to contain a `module-info.class`, that file is honored and the JAR becomes a named module.

E. It is always readable by other JARs on the classpath, but not by named modules on the module path (without special flags).

---

**59.** Consider this multi-module scenario:

```java
// module-info.java for inventory.api
module inventory.api {
   exports inventory.api;
}

// module-info.java for inventory.warehouse
module inventory.warehouse {
   requires inventory.api;
   exports inventory.warehouse.ops to inventory.reports;
   uses inventory.api.StockChecker;
}

// module-info.java for inventory.reports
module inventory.reports {
   requires inventory.warehouse;
   requires inventory.api;
}

// module-info.java for inventory.plugin
module inventory.plugin {
   requires inventory.api;
   provides inventory.api.StockChecker with inventory.plugin.FastStockChecker;
}
```

Which statement is true?

A. `inventory.reports` can access types in `inventory.warehouse.ops` because it declares `requires inventory.warehouse` and is named in the qualified export.

B. `inventory.plugin` must declare `requires inventory.warehouse` because it provides a service used by `inventory.warehouse`.

C. `inventory.api` must declare `uses inventory.api.StockChecker` for the service to be locatable.

D. `inventory.warehouse` cannot use `ServiceLoader` to find `FastStockChecker` because it does not declare `requires inventory.plugin`.

E. The code does not compile because `inventory.plugin` does not export the package containing `FastStockChecker`.

---

**60.** In the scenario from the previous question, suppose `inventory.warehouse`'s `exports inventory.warehouse.ops to inventory.reports;` is changed to a plain `exports inventory.warehouse.ops;`. A new module `inventory.audit` is added with `requires inventory.warehouse;` and references a public class in `inventory.warehouse.ops`. Does `inventory.audit` compile?

A. No — only `inventory.reports` was ever granted access, regardless of the change.

B. Yes — an unqualified `exports` makes the package available at compile time and runtime to any module that requires the exporting module.

C. No — `inventory.audit` would also need `opens inventory.warehouse.ops` declared in `inventory.warehouse`.

D. Yes, but only if `inventory.audit` also declares `uses inventory.api.StockChecker`.

E. No — once a package has been the target of a qualified export, it can never become an unqualified export.

---
