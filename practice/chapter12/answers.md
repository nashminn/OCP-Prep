# Chapter 12: Modules — Answers

---

**1. C**

Module directives (`exports`, `requires`, `opens`, `uses`, `provides`) can appear in any order within a `module-info.java` file, and a module can have multiple `exports` directives, including a mix of qualified (`exports ... to`) and unqualified exports for different packages. There is no rule requiring `requires` before `exports`, no rule against multiple `exports` directives (B and D are wrong), and no ordering requirement between qualified and unqualified exports targeting different packages (E is wrong).

---

**2. C**

Java does not allow the same module to appear in more than one `requires` clause, even if one is `requires transitive`. Option C declares both `requires b;` and `requires transitive b;` for the same module `b`, which is a duplicate and fails to compile. Option A requires two different modules (`b` and `c`), which is fine. Option B exports two different packages, which is allowed. Option D opens two different packages with different qualifiers, which is allowed. Option E both requires a module and exports a package to it, which is legal.

---

**3. C**

Java does not permit the same module to be the target of two `requires` directives, even if they are textually identical. This is treated as a duplicate/redundant declaration and is a compile error. It has nothing to do with whether `com.example.util` exports any packages (B is wrong), and there is no special restriction on module names containing `com.example` (D is wrong). This is a compile error, not a warning (E is wrong).

---

**4. B, C, E**

The valid directive keywords in a `module-info.java` are `exports`, `requires` (and `requires transitive`/`requires static`), `opens`, `uses`, and `provides ... with`. `imports`, `includes`, and `extends` are not module directives — `imports` is confused with the `import` statement used in regular classes (which is also not used in module declarations), and `extends`/`includes` do not exist in this context.

---

**5. D**

Module directives may appear in any order in the module declaration. There is no requirement that `requires` come before `exports`, that `uses` immediately precede its corresponding `provides`, or that `provides` be last. The only ordering-related restriction concerns duplicates — e.g., you cannot `requires` the same module twice, or `exports`/`opens` the same package twice — not the relative order of different directive types.

---

**6. C**

`zoo.gamma` declares `requires zoo.beta`, but `zoo.beta` declares only plain `requires zoo.alpha` (not `requires transitive`). Implied readability is only granted when the intermediate module uses `requires transitive`. Since `zoo.beta` does not use `transitive`, `zoo.gamma` does **not** automatically read `zoo.alpha`, and therefore cannot reference types in `zoo.alpha.api` even though `zoo.alpha` exports that package — `zoo.gamma` itself would need to declare `requires zoo.alpha`. Option A is the trap: it describes how `requires transitive` *would* work, but `zoo.beta` doesn't use it. Option B is false — `requires` is not transitive by default. Option D is irrelevant to readability via the chain. Option E confuses `opens` (a reflection mechanism) with normal compile-time readability.

---

**7. B**

Changing `zoo.beta`'s `requires zoo.alpha;` to `requires transitive zoo.alpha;` grants implied readability: any module that `requires zoo.beta` (such as `zoo.gamma`) automatically reads `zoo.alpha` as well, without declaring its own dependency. Since `zoo.alpha` already exports `zoo.alpha.api` (unqualified), `zoo.gamma` would then have compile-time access to it. Option A only affects access to `zoo.beta.api`, not `zoo.alpha.api`. Option C (`opens`) only affects reflective access, not normal compile-time references. Option D would work too in isolation, but the question asks for implied readability via `zoo.beta`'s existing `requires` relationship — adding a redundant export of another module's package in `zoo.beta` is not how implied readability is expressed (and `zoo.beta` does not own `zoo.alpha.api`'s types). Option E (`requires static`) would make the dependency optional at runtime, not transitive.

---

**8. C**

`requires static` declares an *optional* dependency: the required module must be present at **compile time** (so the code compiles against its types), but at **runtime** the module is not mandatory — if it's absent, the JVM will still launch, and only code paths that actually touch the optional module's types will fail. Option A incorrectly states it's required at runtime. Option B is the closest trap but is wrong because the application does *not* fail to start if it's missing — that's the whole point of "static" (optional) dependencies. Option D reverses compile-time/runtime. Option E is wrong; `requires static` is valid syntax.

---

**9. B**

Because `report.charts` is declared with `requires static`, it is an optional runtime dependency. The module compiles and launches successfully even without `report.charts` on the runtime module path. Only if and when the rarely executed branch referencing `report.charts` types is actually run will the JVM attempt to load those classes and fail (e.g., with `NoClassDefFoundError`). Option A is wrong because `requires static` makes the dependency optional at runtime — the app does launch. Option C is wrong; optional dependencies via `requires static` are explicitly supported. Option D and E describe behaviors the JVM does not perform.

---

**10. C**

`requires transitive X` means that any module `M` that declares `requires` (plain or transitive) on the module containing this directive will also automatically read `X`, without `M` declaring its own dependency on `X`. This is "implied readability." Option A is false — duplicate `requires` for the same module is still a compile error regardless of `transitive`. Option B is false — `requires transitive` does not change what is exported; it only affects what dependent modules can read. Option D confuses `transitive` with `opens`, which governs reflection. Option E is false — `requires transitive` works for any module, not just `java.base`.

---

**11. C**

A qualified export (`exports zoo.core.internal to zoo.admin, zoo.audit;`) grants compile-time and runtime access to the listed package **only** to the named modules (`zoo.admin` and `zoo.audit`), and only if those modules also declare `requires zoo.core` so they can read the `zoo.core` module in the first place. Option A is wrong because qualified exports restrict access to the listed modules only — not "every module that requires." Option B is incomplete (it omits `zoo.audit`). Option D incorrectly suggests reflection grants access regardless of the qualified export list. Option E is wrong — qualified exports do grant compile-time access to the listed modules.

---

**12. B**

`opens pkg to module` grants **runtime reflective access only** — frameworks (like ORMs or serialization libraries) in `zoo.persistence` can use reflection (e.g., `setAccessible(true)`, instantiate via reflection, access private fields) on `zoo.config.model` types at runtime. It does **not** grant normal compile-time access — `zoo.config.model` is not exported, so no module (including `zoo.persistence`) can write `import zoo.config.model.SomeClass;` and use it directly in source code. Option A is wrong because `opens` does not provide compile-time access. Option C is wrong because `opens ... to` does not grant compile-time access at all (to anyone). Option D is false — `opens` does not imply `exports`. Option E is false — a module can freely mix `exports` (for one package) and `opens` (for a different package).

---

**13. B**

The `open` modifier on a module declaration (`open module zoo.reports { ... }`) makes **every package in the module** open for deep reflection at runtime — in addition to whatever `exports` directives are present. It does not replace or interfere with `exports`; both can coexist. Option A is wrong — `open` has a real, significant effect (opening all packages reflectively). Option C is wrong — an `open module` can absolutely also use `exports` for normal compile-time access; what it *cannot* do is also use the `opens` directive (since everything is already open). Option D is wrong — `exports` and the module-wide `open` are independent; `zoo.reports.api` remains exported. Option E is wrong — `open module` does not turn opens into exports; opened packages are still only available reflectively unless separately exported.

---

**14. B**

A module declared with the `open` modifier cannot also use the `opens` directive — since `open module` already makes every package in the module open for reflection, an explicit `opens` directive for one of its packages is redundant and is a **compile error**. Option B (`open module` combined with `opens zoo.alpha.internal;`) violates this rule and does not compile. Option A is fine — `open module` plus `exports` is legal (the module-wide `open` and per-package `exports` directives serve different, compatible purposes). Option C (plain module with a qualified `opens ... to`) is valid. Option D is valid — a single `exports ... to` directive can list multiple target modules separated by commas (`exports zoo.alpha.api to zoo.beta, zoo.gamma;`), which is the correct way to export one package to several modules. Option E is valid — a plain module can freely mix `opens` for one package and `exports` for a different package.

---

**15. B**

The access-control table for exported packages says: `public` and `protected` members of `public` types in an exported package are visible outside the module (the same way they would be visible to subclasses/other packages within a single-module program). `private` members remain inaccessible (they're never accessible outside their class), and package-private members remain inaccessible outside their package — `exports` does not change those access levels. Option A is too restrictive (omits `protected`). Option C is too permissive (package-private members stay hidden). Option D is wildly incorrect. Option E misunderstands `exports` — it does make member-level access (subject to existing `public`/`protected` modifiers) usable outside the module.

---

**16. B**

A module cannot have more than one `exports` directive for the **same package** — whether both are unqualified, both qualified, or one of each. Declaring `exports zoo.alpha.api;` and then `exports zoo.alpha.api to zoo.beta;` both target the same package `zoo.alpha.api`, which is a duplicate/conflicting export and fails to compile. Option A and C describe plausible-sounding "merge" behaviors that Java does not implement. Option D invents an ordering rule that doesn't exist. Option E incorrectly assumes silent conflict resolution.

---

**17. B**

`requires` grants the requiring module's code (all of it, including non-exported packages like `zoo.animal.care.details`) the ability to read the **exported** packages of the required module. `zoo.animal.feeding` exports `zoo.animal.feeding`, so any class anywhere inside `zoo.animal.care` — exported or not — can `import` and use `zoo.animal.feeding.Task`. Option A incorrectly assumes only exported packages within the requiring module get access to dependencies — but readability operates at the module level, not the package level. Option C is irrelevant — only the module needs `module-info.java`, not individual packages. Option D inverts the logic — `zoo.animal.feeding` is exported by the *other* module, not `zoo.animal.care.details`. Option E is a non-sequitur — having a `main` method doesn't prevent a class from being used as a field type.

---

**18. C**

Module names follow Java identifier/package naming conventions and may contain letters, digits, underscores, and dots — but **not dashes (-)**. `zoo-animal-feeding` contains dashes and is therefore an illegal module name. `zoo.animal.feeding`, `com.example.app2`, `zoo`, and `com.example.v2.utils` are all valid (dots are allowed, and segments can contain digits as long as they don't start with one in a way that breaks identifier rules — `app2` and `v2` are fine since they don't start with a digit).

---

**19. C**

Following the automatic module naming algorithm: start with `commons-collections-4.4.jar`. Remove the `.jar` extension → `commons-collections-4.4`. Remove the trailing version information (`-4.4`, which is digits and dots) → `commons-collections`. Replace remaining special characters (the dash) with dots → `commons.collections`. There are no adjacent dots or leading/trailing dots to clean up. The final automatic module name is `commons.collections`. Option A keeps the version and dashes (wrong). Option B keeps the version as part of the name (wrong — versions are stripped). Option D keeps the dash instead of converting to a dot. Option E removes all separators entirely, which is not how the algorithm works.

---

**20. C**

Starting with `data-sync-2.1.0-RC1.jar`: remove `.jar` → `data-sync-2.1.0-RC1`. Remove the trailing version information — a version is "digits and dots with possible extra information at the end" such as `-1.0.0` or `-1.0-RC`, so `-2.1.0-RC1` is recognized and stripped → `data-sync`. Replace the dash with a dot → `data.sync`. No further cleanup needed. The result is `data.sync`. Option A retains the raw filename. Option B fails to strip the version (incorrectly treats `2.1.0.RC1` as part of the name). Option D incorrectly retains `RC1` as part of the name. Option E retains the dash instead of converting it to a dot.

---

**21. C**

Starting with `util_$pkg-1.0.jar`: remove `.jar` → `util_$pkg-1.0`. Remove the trailing version `-1.0` → `util_$pkg`. Replace any run of non-alphanumeric characters with a single dot — both `_` and `$` are special characters, and they are adjacent, so the run `_$` becomes a single dot → `util.pkg`. There are no leading/trailing dots to remove. The final name is `util.pkg`. Option A keeps the special characters unchanged. Option B treats `_` and `$` as two separate dots before merging — but the rule replaces a *sequence* of special characters with one dot directly (and even if it produced `util..pkg` first, the "merge sequences of dots" step would still collapse it to `util.pkg`). Option D only converts one of the two characters. Option E uses underscores instead of dots, which is backwards.

---

**22. C**

If a `module-info.java` declares `requires foo.bar;` and no module — named, automatic, or derivable — named `foo.bar` exists anywhere on the module path, this is a hard compile error: `javac` cannot resolve the required module and the module fails to compile. This is analogous to importing a class that doesn't exist on the classpath, but for modules it's enforced at compile time as part of module resolution. Options A, B, D, and E all describe the module compiling despite the missing dependency, which contradicts one of the core benefits of JPMS — dependency problems are caught at compile/launch time, not buried until runtime class loading.

---

**23. B**

You cannot `exports` a package that does not exist (i.e., contains no compiled classes) within the module. If `zoo.alpha.widgets` was a typo and no such package exists in the module's source/output, the `exports zoo.alpha.widgets;` directive causes a compile error — `javac` reports that the package is empty or does not exist. Options A, C, D, and E all describe ways the compiler might "look past" or auto-correct the error, none of which Java does — this is treated as a hard compile-time failure.

---

**24. B**

The Java module system does not allow cyclic (circular) dependencies between modules — if `zoo.a` requires `zoo.b` and `zoo.b` requires `zoo.a`, neither module can be compiled, because each would need the other to already exist/compile first. This is a compile-time error for **both** modules, not just one. Option A is wrong — JPMS strictly forbids module cycles regardless of whether actual class references form a cycle. Option C is wrong — both fail, not just the second. Option D is wrong — the failure happens at compile time, before any runtime launch is possible. Option E describes behavior Java does not implement.

---

**25. B**

A cycle of any length — two, three, or more modules — is disallowed by the Java module system as long as following the `requires` arrows leads back to the starting module. Here, `ballA` → `ballB` → `ballC` → `ballA` forms exactly such a cycle, so none of these modules can compile. Option A incorrectly claims only direct two-module cycles are disallowed — the chapter explicitly addresses this scenario and confirms three-module (and longer) cycles are equally forbidden. Option C, D, and E all describe the code compiling in some form, which is incorrect — the entire set of modules fails to compile due to the cycle.

---

**26. B**

A "split package" occurs when the same package name is provided by more than one module on the module path. The Java module system does not allow this — it results in a compile-time error (when compiling against both modules) or a runtime error (module resolution failure) because Java cannot determine which module's version of `zoo.animal.shared.Constants` should be used. Option A describes a "merge" behavior Java does not implement. Option C and D describe runtime "last one wins" or "must be identical" rules that don't exist for split packages on the module path — it's simply disallowed. Option E is wrong — even non-exported packages cannot be split across named modules on the module path; the conflict is detected during module resolution regardless of export status.

---

**27. B**

Split-package detection applies to modules on the **module path**. A package that exists both in an unnamed module (classpath) and in a named module (module path) is a different situation — the unnamed module and named modules are resolved through separate mechanisms, so this configuration is technically allowed (though it's discouraged because it can cause confusing shadowing/visibility behavior, and the named module's version of the package is generally what's used by other named modules). Option A overstates the restriction — the strict split-package error specifically concerns the module path. Option C invents an automatic-renaming behavior that doesn't exist. Option D and E focus on irrelevant details — the classpath/module-path split itself is the deciding factor, not whether classes are exported or public.

---

**28. B**

Every module automatically reads (has an implicit `requires` on) `java.base` — this is "mandated" and cannot be turned off. You are permitted to explicitly write `requires java.base;` in your `module-info.java`, and it will compile fine, but it is completely redundant since it's already implied. Option A is wrong — explicitly requiring `java.base` is legal, just unnecessary. Option C invents a positional rule that doesn't exist. Option D confuses `java.base` (a module) with `java.util` (a package within it) — exporting isn't relevant here since `java.base` already exports `java.util` to everyone. Option E is backwards — `java.base` doesn't need any special directive to be readable; it's automatic for all modules.

---

**29. C**

When describing a module (`--describe-module` or `-d`), the output always includes a line like `requires java.base mandated`, even though no `module-info.java` ever needs to (or should) explicitly declare this — it's automatically added by the JVM/compiler to reflect the implicit dependency every module has on `java.base`. Options A and B reflect content that *is* literally present in the source `module-info.java` (the module name and the `exports` directive). Option D (`opens`) and E (`provides`) are not present in this module's declaration at all and wouldn't appear in the output either.

---

**30. C**

When a module contains a package that is part of the module (has compiled classes) but is **not** mentioned in any `exports` or `opens` directive, the `--describe-module` output includes a `contains <package>` line to indicate that the package exists in the module but is not accessible from outside. Since `zoo.animal.care.details` is not exported (only `zoo.animal.care.medical` is, via a qualified export), it would be reported with `contains zoo.animal.care.details`. Option A is wrong because the package is explicitly *not* exported. Option B is wrong because there's no `opens` directive for it. Option D confuses `requires` (used for module dependencies, not package containment). Option E invents output that doesn't exist.

---

**31. B**

For `ServiceLoader.load(Tour.class)` to work from within `zoo.tours.reservations` (the service locator), that module must declare `uses zoo.tours.api.Tour;` in addition to `requires zoo.tours.api;`. Both directives serve different purposes — `requires` is needed for compilation (to reference the `Tour` type), and `uses` is needed so the module system knows this module is a service consumer that should have provider modules made visible to its `ServiceLoader` calls. Without `uses`, `ServiceLoader.load()` will not find any providers even if they exist. Option A is unnecessary — the implementation package doesn't need to be exported. Option C is backwards — `provides ... with` belongs in the *provider* module (`zoo.tours.agency`), not the consumer. Option D is wrong — `uses` belongs to the module performing the lookup (the service locator), not the service provider interface module. Option E is unnecessary and would create an undesirable dependency from the provider to the locator.

---

**32. B**

For `ServiceLoader` to instantiate a service provider, the implementation class named in `provides ... with` must either (a) have a `public` no-argument constructor, or (b) provide a `public static provider()` method that returns an instance of the service type. Here, `TourImpl` has only a `private` no-arg constructor and a differently named static factory method (`getInstance()`, not `provider()`), so neither condition is met. At lookup/iteration time, `ServiceLoader` throws a `ServiceConfigurationError` because it cannot instantiate the declared provider. Option A is wrong — `ServiceLoader` looks specifically for a method named `provider()`, not just any static factory method. Option C and D describe behaviors that don't occur — this is a runtime error, not a silent skip or compile error. Option E is wrong — `provides` does not grant any special reflective bypass of access modifiers.

---

**33. B**

`ServiceLoader` recognizes a `public static provider()` method as an alternative to a `public` no-arg constructor — if present, `ServiceLoader` calls `provider()` to obtain an instance, bypassing the constructor-accessibility requirement entirely. Adding `public static Tour provider() { return TourImpl.getInstance(); }` to `TourImpl` would make it usable by `ServiceLoader` even though the constructor remains `private`. Option A (`opens`) does not help — `ServiceLoader`'s standard instantiation logic explicitly checks for `provider()` or a public constructor; opening the package for reflection doesn't change which constructors `ServiceLoader` is willing to call by default. Option C invents a directive (`uses ... with`) that doesn't exist. Option D would technically allow some reflective access but is not the documented/expected mechanism and `protected` is still not directly callable without `setAccessible`. Option E is incorrect — `ServiceLoader` does not have special-case logic for a method named `getInstance()`.

---

**34. B**

`ServiceLoader.load(Tour.class)` returns a `ServiceLoader<Tour>`. Calling `.stream()` returns a `Stream<Provider<Tour>>`. `.map(Provider::get)` converts each `Provider<Tour>` into the actual `Tour` instance, producing a `Stream<Tour>`. `.mapToInt(Tour::length)` converts to an `IntStream` of lengths — here, just `120`. `.max()` on an `IntStream` returns an `OptionalInt`, which is `OptionalInt.of(120)` since the stream is non-empty. `OptionalInt.ifPresent(IntConsumer)` invokes the consumer with the *unwrapped primitive `int`* — not the `OptionalInt` wrapper — so `System.out::println` is called with `120` and prints `120`. Option A misreads `Provider` as the printed value. Option C is wrong — `stream()` does exist on `ServiceLoader`, returning `Stream<Provider<S>>`. Option D incorrectly assumes `ifPresent` passes the `OptionalInt` object itself (that would require printing `optional` directly, e.g., via `System.out::println` on the `OptionalInt` reference, not via `ifPresent`'s unwrapped consumer). Option E is wrong — `mapToInt` is a valid `Stream<Tour>` method given a `ToIntFunction<Tour>` like `Tour::length`.

---

**35. B**

The `uses` directive declares that a module is a consumer of a particular service interface via `ServiceLoader`. Without `uses zoo.tours.api.Tour;`, `ServiceLoader.load(Tour.class)` will compile (since `requires zoo.tours.api;` makes the `Tour` type visible) but will not find any service providers at runtime — the module system uses the `uses` declaration to wire up provider visibility for that consumer. Option A (`provides ... with`) belongs to a service provider module, not a consumer performing lookups. Option C invents a directive/target combination that isn't meaningful here. Option D (`exports`) is about making packages available to other modules, not about consuming services. Option E (`requires transitive`) doesn't add the "I am a service consumer" declaration that `uses` provides.

---

**36. A**

One of the key benefits of the services mechanism is loose coupling: a new service provider module can be added to the module path, and `ServiceLoader` will automatically discover it at runtime without any of the existing modules (service provider interface, service locator, consumer, or other providers) needing to be recompiled. The service locator's `ServiceLoader.load()` call dynamically finds whatever providers are present on the module path at runtime. Options B through E all assume some existing module needs changes, which defeats the purpose of the service-based architecture's extensibility.

---

**37. B**

Per the OCP definitions, "the service" is composed of the **service provider interface** (the contract) and the **service locator** (the lookup mechanism, typically using `ServiceLoader`) — these two parts together define what the service is and how to find implementations. The **service provider** (the actual implementation, e.g., `TourImpl`) and the **consumer** (the code that calls the service) are separate roles that are *not* considered part of "the service" itself. Options A, C, D, and E all pair at least one of "service provider" or "consumer" as part of "the service," which contradicts this definition.

---

**38. A**

`javac -d feeding-out -p mods feeding/zoo/animal/feeding/*.java feeding/module-info.java` correctly specifies `-d` for the output directory, `-p` (short for `--module-path`) for dependency resolution, and lists both the package's `.java` files and the `module-info.java` file to compile. Option B uses `-cp` (classpath), which is for non-modular compilation and won't properly process `module-info.java`/module dependencies, and it omits `module-info.java` entirely. Option C invents a `--module` flag used incorrectly as a directory target. Option D omits the actual source files to compile — only `module-info.java` is listed. Option E puts `feeding-out` in the wrong position (it's not preceded by `-d`, so it would be interpreted as another file to compile).

---

**39. A**

`java -p mods -m zoo.animal.feeding/zoo.animal.feeding.Task` correctly uses `-p` (module path) to locate the module, and `-m` (module) with the `moduleName/fully.qualified.ClassName` syntax (module name, a single slash, then the fully qualified class name using dots) to specify what to run. Option B treats this as non-modular code with a classpath, which won't find the module structure correctly. Option C omits `-m` entirely — without it, `java` doesn't know this argument is a module/class specifier. Option D swaps `-m` and `-p` and their arguments incorrectly. Option E replaces the required slash with a colon (`:`), which is not valid syntax — exactly one slash must separate the module name from the fully qualified class name.

---

**40. B**

`--module-path` and `-p` are simply two spellings of the *same* option. Specifying both `--module-path mods` and `-p extra` in the same command is equivalent to specifying the same option twice with different values, which `java` does not allow — this results in a command-line error. Option A incorrectly assumes the two values get merged into a combined search path. Options C and D both assume one occurrence "wins" silently, which is not how `java` handles a duplicated option. Option E invents an unrelated restriction about `-m` requiring the long form.

---

**41. A**

`jar -cvf mods/zoo.animal.care.jar -C care/ .` uses `-c` (create), `-v` (verbose), `-f` (specify the output filename), and `-C care/ .` to change to the `care/` directory and include everything in it (including `module-info.class` at its root) in the archive. Option B has the arguments backwards — `-f` expects the JAR filename, not a source directory as the first positional argument. Option C invents a `--module` flag for `jar` that doesn't exist for this purpose. Option D uses `-p` (not a valid `jar` creation flag in this context — `-p` is for module path in `javac`/`java`, not `jar`). Option E omits `-C`, so `care/*` would be interpreted relative to the current directory without changing into `care/`, producing an archive with a `care/` prefix on every entry rather than the contents at the root.

---

**42. A, C**

`java -p mods -d zoo.animal.feeding` and `java -p mods --describe-module zoo.animal.feeding` are documented as equivalent — `-d` is the short form of `--describe-module` for the `java` command when used with a module name (not a directory, in this specific context). Option B uses `--module`, which is for *running* a module (requires a `moduleName/className` argument), not describing one. Option D invents a `--list-modules` flag combined with `--file`, which isn't the correct describe syntax for `jar` (the correct form is `jar --file mods/zoo.animal.feeding.jar --describe-module` or `jar -f ... -d`). Option E uses `jdeps` with a `--describe-module` flag that doesn't exist for `jdeps`.

---

**43. B**

`jdeps -s legacy.jar` (or the equivalent `jdeps -summary legacy.jar` / `jdeps --summary legacy.jar`) prints just the summary lines showing which modules `legacy.jar` depends on (e.g., `legacy.jar -> java.base`, `legacy.jar -> java.sql`), which is exactly the high-level "what would I need to `requires`" information without the full per-package dependency table. Option A (plain `jdeps`) prints the summary *plus* a detailed table of every package-to-module mapping — more output than needed. Option C (`--jdk-internals`) focuses specifically on internal/unsupported API usage, not the general module dependency list. Option D invents a `jmod describe` subcommand signature that doesn't operate on `.jar` files this way. Option E (`java --describe-module`) describes a module's own declared `module-info`, not a non-modular JAR's *external* dependencies.

---

**44. A**

`jlink --module-path mods --add-modules zoo.animal.talks --output zooApp` is the correct syntax: `--module-path` (or `-p`) specifies where to find custom modules, `--add-modules` lists the module(s) to include (their dependencies are pulled in automatically if resolvable), and `--output` names the destination directory for the runtime image. Option B omits `--add-modules` and places the module name as a bare positional argument, which `jlink` does not accept this way. Option C invents `-m` and `-o` as short forms for `jlink`, which don't exist (`jlink` uses `--add-modules` and `--output` without short equivalents for those two). Option D uses `jpackage`, which creates an application image/installer, not a runtime image. Option E invents a `jmod create ... zooApp` syntax that doesn't match `jmod`'s actual modes (`create`, `extract`, `describe`, `list`, `hash`) or `jlink`'s purpose.

---

**45. C**

`jlink` produces a **runtime image** — a directory structure (with `bin`, `conf`, `lib`, etc.) containing a custom, trimmed-down JRE plus your modules — and it only works with **modular** applications. `jpackage` produces a **self-contained application image**, a single platform-specific artifact (e.g., `.exe` on Windows, `.dmg`/`.pkg` on Mac, `.deb`/`.rpm` on Linux), and it can package **both modular and non-modular** applications. Option A reverses the modular/non-modular capability. Option B reverses which tool produces a directory vs. an executable. Option D is false — they are distinct tools with different outputs (though `jlink` output can feed into `jpackage`). Option E is false — neither tool requires a full JDK on the *target* machine; that's the point of both tools (bundling a runtime).

---

**46. B**

`--add-reads zoo.reports=zoo.charts.legacy` tells the module system, at launch time, that module `zoo.reports` should be treated as if it has an additional `requires` (read) edge to `zoo.charts.legacy`, without modifying any `module-info.java` source. Option A (`--add-exports`) is for making a *specific package* in one module accessible to another — it addresses the "exports" side, not the "requires"/readability side, and isn't what's needed if `zoo.charts.legacy` already exports its API package. Option C invents incorrect syntax (`--add-modules` doesn't take a `module=module` form for granting reads). Option D (`--add-opens`) is for reflective access, not normal compile/runtime readability. Option E (`--patch-module`) is for injecting additional classes/resources into a module, unrelated to readability between existing modules.

---

**47. B**

`--add-exports com.vendor.lib/com.vendor.lib.internal=com.app` makes the otherwise non-exported package `com.vendor.lib.internal` accessible — at both compile time (when passed to `javac`) and runtime (when passed to `java`) — specifically to module `com.app`, without modifying the vendor's `module-info.java`. This is the "exports" counterpart to `--add-opens` (which is for reflection only). Option A (`--add-opens`) only grants *reflective* access (e.g., via `setAccessible`), not normal compile-time `import`/reference access — code in `com.app` couldn't directly reference the type at compile time with only `--add-opens`. Option C invents incorrect syntax for `--add-reads` (which grants module-level readability, not package-level export access — and the dependency here, `com.vendor.lib`, is presumably already required). Option D misuses `--add-modules`, which adds *root modules* to the module graph, not package-level exports. Option E (`--patch-module`) is for adding/overriding classes within a module, not for cross-module package access.

---

**48. B**

`--add-opens com.app/com.app.entities=ALL-UNNAMED` (or naming a specific module instead of `ALL-UNNAMED` if Hibernate is itself a named module) grants deep reflective access — including to `private` fields and constructors — to the package `com.app.entities` for the calling code, without modifying `com.app`'s `module-info.java`. This is precisely the `--add-opens` use case: enabling reflection-based frameworks to work with modules that haven't explicitly used `opens`. Option A (`--add-exports`) only affects normal compile/runtime type accessibility, not deep reflection into private members. Option C (`--add-reads`) only affects module readability for normal references, not reflective access to non-public members. Option D misuses `--patch-module` (which injects classes into a module, unrelated to reflection permissions). Option E misuses `--add-modules` (which adds root modules to resolution, not reflection permissions).

---

**49. C**

`--patch-module <module>=<path-to-additional-classes-or-jar>` overlays additional classes and resources onto an existing module at runtime (or compile time), commonly used in testing to inject compiled test classes into the same module as the code under test so that package-private members remain accessible to the tests. Option A (`--add-modules`) adds extra root modules to the module graph but doesn't inject classes into an existing module. Option B (`--add-reads`) affects module readability relationships, not class/resource injection. Option D (`--upgrade-module-path`) is used to provide newer versions of upgradeable modules (mostly JDK modules), a different mechanism. Option E (`--add-exports`) affects package export visibility, not class injection.

---

**50. B**

This is a textbook bottom-up migration scenario: the team controls all JARs and wants to fully modularize each one (proper `exports`/`requires`) before moving on, working from the modules with the fewest dependencies upward. `core.jar` has no dependencies on the others, making it the lowest-level project — it should be migrated first in a bottom-up strategy, becoming a named module on the module path while `service.jar` and `web.jar` remain on the classpath as unnamed modules until their turn. Option A and D describe top-down migration, which is preferred when you do *not* control all the JARs (here the team controls everything, so bottom-up is more appropriate and lets them do a careful, fully-specified migration of each module). Option C starts with the highest-level JAR under a "bottom-up" label, which is contradictory. Option E is wrong — the strategies have different starting points and different intermediate states (named modules vs. automatic modules), so they are not interchangeable even without cycles.

---

**51. B**

A top-down migration starts by placing **all** JARs on the module path (so `vendor.jar`, lacking a `module-info.java`, becomes an *automatic module* with an automatically derived or manifest-specified name), and then converts the highest-level project (`app.jar`, which depends on everything else) into a named module first by adding a `module-info.java` that uses `requires` referencing `vendor.jar`'s automatic module name. `vendor.jar` remains an automatic module, which is acceptable for top-down migration since named modules on the module path *can* read automatic modules. Option A leaves things on the classpath and modularizes the wrong JAR — this isn't a meaningful top-down step. Option C and E describe mixed classpath/module-path setups that don't match top-down's "everything on the module path" starting point. Option D modularizes both JARs at once, which contradicts the premise that the team doesn't control `vendor.jar`'s source.

---

**52. B**

In a bottom-up migration, you start at the bottom of the dependency graph and move modules to the module path (as named modules with `module-info.java`) one at a time, while everything not yet migrated remains on the classpath as unnamed modules. Critically, named modules on the module path **cannot** read the unnamed module (classpath) — readability only flows in the other direction (the unnamed module can read everything). This is why bottom-up migration must proceed carefully from modules with no remaining unmet dependencies. Option A reverses the named/unnamed assignment. Option C and D describe automatic modules, which characterize *top-down* migration's intermediate state, not bottom-up. Option E contradicts the defining characteristic of bottom-up migration (gradual movement to the module path).

---

**53. C**

Implied readability only forwards through a `requires transitive` declaration on the module you *directly* require. `zoo.delta` declares plain `requires zoo.gamma;`, so it directly reads `zoo.gamma`. Whether `zoo.delta` gains anything *beyond* `zoo.gamma` depends on whether `zoo.gamma`'s own requirement is transitive — and `zoo.gamma` declares plain `requires zoo.beta` (not transitive). Because that link in the chain is non-transitive, `zoo.gamma`'s readability of `zoo.beta` (and, through `zoo.beta`'s `requires transitive zoo.alpha`, of `zoo.alpha`) is **not** forwarded on to `zoo.delta`. So `zoo.delta` ends up reading only `zoo.gamma` — the chain of implied readability is broken at the `zoo.gamma` → `zoo.beta` link. Options A, B, and D all assume the transitivity from `zoo.beta`'s declaration somehow "skips past" `zoo.gamma`'s non-transitive requirement, which it does not. Option E is wrong because `requires transitive` chains can in fact extend further than one level — *if* every link in the chain uses `transitive` (see the next question for that scenario).

---

**54. C**

Now `zoo.gamma` declares `requires transitive zoo.beta;`. Tracing the chain: `zoo.beta` has `requires transitive zoo.alpha` (so anything requiring `zoo.beta` reads `zoo.alpha`), and `zoo.gamma` has `requires transitive zoo.beta` (so anything requiring `zoo.gamma` reads `zoo.beta` — **and**, because `zoo.gamma` itself reads `zoo.alpha` via `zoo.beta`'s transitive export, and `zoo.gamma` re-exposes `zoo.beta` transitively, `zoo.delta` also transitively reads `zoo.alpha`). `zoo.delta` declares only `requires zoo.gamma;`, but because `zoo.gamma`'s requirement on `zoo.beta` is now `transitive`, `zoo.delta` automatically reads `zoo.beta`. And because `zoo.beta`'s requirement on `zoo.alpha` is *also* `transitive`, that readability chains through as well, so `zoo.delta` automatically reads `zoo.alpha` too. The result: `zoo.delta` automatically reads `zoo.gamma` (direct), `zoo.beta`, and `zoo.alpha` (both via the chained `requires transitive` relationships). Option A and B under-count the chain. Option D misdescribes the mechanism (transitive requirements don't "skip" to the bottom while excluding the middle). Option E is wrong because this is exactly the scenario `requires transitive` chains are designed to avoid.

---

**55. B**

Reflective access — even to `public` classes, constructors, and methods — requires the containing package to be **opened** (via `opens`, `opens ... to`, or the whole module being declared `open`) to the module performing the reflection (or opened unconditionally). Simply `exports`-ing a package grants normal compile-time/runtime accessibility for code that directly references the types, but it does **not** grant permission for `setAccessible`-based or constructor-reflection-based access from another module — attempting `Constructor.newInstance()` reflectively from a module that hasn't been granted `opens` access results in `InaccessibleObjectException` (or `IllegalAccessException` in older reflection APIs) at runtime. Option A is the common misconception — `exports` and `opens` serve different purposes (compile-time linking vs. runtime reflection) and are not interchangeable. Option C is too narrow — `opens pkg to specificModule` (without making the whole module `open`) would also work, not just a fully `open module`. Option D is false — records absolutely can be accessed via reflection (this is heavily used by JSON/serialization libraries); the issue here is purely about module access, not records specifically. Option E is therefore incorrect because C alone (without B) is too restrictive, but B correctly captures the general rule.

---

**56. B**

Readability between the module path and classpath is asymmetric: the **unnamed module** (classpath) can read everything — both other classpath JARs and all named/automatic modules on the module path. However, **named modules on the module path cannot read the unnamed module** — there's no mechanism for a named module to declare `requires` on "the classpath." So `zoo.alpha` cannot call into `helper.jar` on the classpath. Option A states the reverse of the actual rule. Option C is wrong — `opens` controls reflective access *into* a module, not a named module's ability to read *out* to the classpath. Option D is wrong — an `Automatic-Module-Name` only matters for JARs placed on the *module path*; it has no effect on classpath JARs (which remain part of the unnamed module). Option E is wrong — mixing module path and classpath is common and explicitly supported (it's central to migration scenarios), just with this directional readability restriction.

---

**57. A, D**

An automatic module (a plain JAR with no `module-info.java`, placed on the module path) automatically **exports all of its packages** to every other named module that can read it (A is correct), and other named modules can reference it in a `requires` directive using its derived or manifest-declared automatic module name (D is correct). Option B is wrong — automatic modules export everything, unlike the unnamed module which exports nothing to named modules. Option C is wrong — the manifest's `Automatic-Module-Name` entry takes priority over filename-based derivation when present. Option E is wrong — automatic modules *are* readable by other modules on the module path (that's the whole point of placing them there during migration).

---

**58. A, C**

The unnamed module (classpath) can read from **both** the classpath and the module path (A is correct) — this is part of why it's useful during migration, as legacy code can still see new modular code. However, the unnamed module exports **no packages** to named modules (C is correct) — named modules cannot see classpath classes at all. Option B is wrong — there is no `requires ALL-UNNAMED` directive available to named modules in `module-info.java`; `ALL-UNNAMED` is used only as a *target* of command-line flags like `--add-opens`/`--add-exports`, not as something a named module can declare `requires` on. Option D is wrong — a `module-info.class` inside a JAR placed on the *classpath* is ignored; the JAR remains part of the unnamed module regardless. Option E bundles a true observation (classpath JARs can read each other) with the readability rule from C into a single combined statement, which is not how this property is presented — A and C are the two standard, independently true statements.

---

**59. A**

`inventory.warehouse` declares `exports inventory.warehouse.ops to inventory.reports;` — a qualified export naming `inventory.reports` specifically. Since `inventory.reports` also declares `requires inventory.warehouse;`, it satisfies both conditions (named in the qualified export, and reads the exporting module), so it can access types in `inventory.warehouse.ops` at compile time. Option B is wrong — `inventory.plugin` provides an *implementation* of a service interface defined in `inventory.api`; it has no need to depend on `inventory.warehouse`, which is merely one possible *consumer* of that service. Option C is wrong — `uses` belongs in the module performing the `ServiceLoader` lookup (`inventory.warehouse`, which already correctly declares it), not in the service provider interface module (`inventory.api`). Option D is wrong — `ServiceLoader` discovery doesn't require the consumer to `requires` the provider module; that's the entire point of the loose coupling that `provides`/`uses` enables. Option E is wrong — `provides X with Y` does not require exporting the package containing `Y`; the implementation class is intentionally not exported, only made available via the service mechanism.

---

**60. B**

An unqualified `exports inventory.warehouse.ops;` makes the package available — at both compile time and runtime — to **any** module that declares `requires inventory.warehouse;`, not just a specific named module. Since `inventory.audit` declares `requires inventory.warehouse;`, it gains compile-time access to the now-unqualified-exported `inventory.warehouse.ops` package and compiles successfully. Option A incorrectly assumes the qualified-export restriction persists after the change to an unqualified export. Option C is wrong — `opens` is for reflection, not normal compile-time references; `exports` alone is sufficient. Option D is wrong — `uses inventory.api.StockChecker` is unrelated to accessing the `inventory.warehouse.ops` package; it's about service lookups for a completely different interface. Option E invents a "permanent qualified export" rule that does not exist — a module's `exports` directives can be freely changed (subject to recompilation) between qualified and unqualified forms.

---
