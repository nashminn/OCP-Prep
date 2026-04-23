# OCP Java SE 21 (1Z0-830) — 7-Week Study Plan

**Book:** OCP Oracle Certified Professional Java SE 21 Developer Study Guide — Boyarsky & Selikoff (1,596 pages, 14 chapters)

**Assumptions:** ~1.5 hrs/day of free office time, 5 days/week ≈ 52 hrs total.
As an active Java developer, Chapters 1–5 will move fast. Extra week added to write code alongside every chapter — reading without coding doesn't stick.

---

## Assessment Test Results (Apr 1, 2026)

**Score: 6/28 (~21%)** — expected at this stage, but gaps are now mapped.

| Priority | Weak Area | Notes |
|----------|-----------|-------|
| CRITICAL | Threads / Concurrency | "Next to zero knowledge" — needs ground-up study |
| CRITICAL | Streams | Weak, needs hands-on code |
| HIGH | Exceptions | Need deep dive, not just a skim |
| HIGH | Records | Unfamiliar with syntax and rules |
| HIGH | Sealed classes | Unfamiliar |
| MEDIUM | Access modifiers | `protected` behavior unclear |
| MEDIUM | `@Override` | Unsure what happens without annotation |
| MEDIUM | `.intern()` / String pool | Confused |
| MEDIUM | SequencedMap | Unfamiliar API |

---

## Before You Start

- ~~Take the **Assessment Test**~~ — **Done. See results above.**
- Register for the exam now to lock in a deadline (forces commitment).
- Passing score: **68%**, 50 questions, 90 minutes.

---

## Week 1 — Foundations
*Chapters 1–3*

| Day | Task | Score |
|-----|------|-------|
| Wed Apr 1 | ~~Assessment Test~~ ✓ | — |
| Mon Apr 6 | ~~Ch. 1: Building Blocks~~ ✓ | — |
| Tue Apr 7 | ~~Ch. 2: Operators~~ ✓ | — |
| Wed Apr 8 | ~~Ch. 3: Making Decisions~~ ✓ | — |
| Thu Apr 9 | ~~Review questions Ch. 1–3~~ ✓ | Ch.1: ~39% / Ch.2: ~85% / Ch.3: ~43% |
| Sat Apr 11 | **Ch. 1 + Ch. 3 reinforcement** — re-read both sets of refined notes; code the 3 targets below | — |

**Result: "Fast Track" assumption was wrong for Ch. 1 and Ch. 3. Ch. 2 is solid.**

Ch. 1 and Ch. 3 notes already incorporate all the review mistakes — re-reading them is fast. Saturday is for coding, not re-reading the book.

**Saturday code targets:**
1. **Ch. 1** — `var` edge cases (`null`, multi-var, instance vs local); write a class that forces all 4 default initialization scenarios; replicate the scope trap from Q12/Q22
2. **Ch. 3** — `instanceof` + flow scoping (5 scenarios from notes); `switch` with type patterns + `when` guards; `var` in `for` and `for-each` over `int[]`, `String[]`, and `List<String>`; labeled `break` and `continue`

Week 2 starts on schedule — Ch. 4–5 are independent of the Ch. 1/3 weak areas.

---

## Week 2 — Core Language Mechanics (revised)
*Chapters 4–5 + Ch. 14 front-loaded for presentation*

| Day | Task |
|-----|------|
| ~~Sun Apr 12~~ | ~~Ch. 4 (part 1): Strings, StringBuilder, equality traps~~ *(on break)* |
| ~~Mon Apr 13~~ | *(on break)* |
| ~~Tue Apr 14~~ | *(on break)* |
| ~~Wed Apr 15~~ | ~~Ch. 4 (part 1 + part 2)~~: Strings, StringBuilder, Arrays, Math API, Date/Time API ✓ |
| ~~Thu Apr 16 – Sun Apr 19~~ | *(sick days)* |
| ~~Sun Apr 20~~ | ~~Ch. 4 review questions~~ ✓ |
| ~~Mon Apr 21 – Wed Apr 23~~ | *(missed / sick)* |
| Thu Apr 24 | Ch. 14 (part 1): Path, Files, NIO.2 basics |
| Fri Apr 25 | Ch. 14 (part 2): I/O streams, serialization, `Console` |
| Sat Apr 26 | Ch. 5 (full day): Method design, access modifiers, varargs, static members, overloading, pass-by-value |
| Sun Apr 27 | Review questions Ch. 5 + **code**: String pool traps, access modifier scenarios |

**Watch out for:** String pool behavior, `==` vs `.equals()`, overloading resolution order.

> **Note on Ch. 14:** Covered early for the presentation — no need to re-study from scratch at the end. A short review session before the practice test is all that's needed.

---

## Week 3 — OOP Deep Dive
*Chapters 6–7*

| Day | Task |
|-----|------|
| Mon Apr 28 | Ch. 6 (part 1): Inheritance, constructor chaining, `super` |
| Tue Apr 29 | Ch. 6 (part 2): Abstract classes, immutable objects, initialization order |
| Wed Apr 30 | Ch. 7 (part 1): Interfaces (default/static/private methods), enums |
| Thu May 1  | Ch. 7 (part 2): **Sealed classes**, **records**, nested classes — Java 21 key topics |
| Fri May 2  | Review questions Ch. 6–7, draw inheritance diagrams by hand + **code**: sealed hierarchy with `permits`, record with compact constructor, `@Override` scenarios |

**Watch out for:** Object initialization order, sealed class rules, record constructors.
> **Assessment gap:** Records and sealed classes are both unfamiliar. Do NOT just read — write code for each. Define a record, use compact constructors, create a sealed hierarchy with `permits`. Get hands-on before moving on.

---

## Week 4 — Functional Java: Lambdas
*Chapter 8*

| Day | Task |
|-----|------|
| Mon May 5  | Ch. 8 (part 1): Lambda syntax, functional interfaces, method references |
| Tue May 6  | Ch. 8 (part 2): Built-in functional interfaces (`Predicate`, `Function`, `Supplier`, `Consumer`, etc.) |
| Wed May 7  | **Code day** — chain predicates, write method references for all 4 forms, implement `Comparator.comparing()`; review questions Ch. 8 |
| Thu May 8  | Ch. 9 (part 1): Collections API — `List`, `Set`, `Queue`, `Deque`, `Map`, `SequencedMap` |
| Fri May 9  | Ch. 9 (part 2): Generics, bounded wildcards (`? extends`, `? super`), `Comparable`/`Comparator` |

**Watch out for:** Which functional interface to use when — know the return type signatures cold.

---

## Week 5 — Collections, Generics & Streams
*Chapters 9–10*

| Day | Task |
|-----|------|
| Sun May 11 | **Code day** — write generic classes, wildcard methods, `Comparator` chaining; review questions Ch. 9 |
| Mon May 12 | Ch. 10 (part 1): Stream pipeline, lazy evaluation, terminal vs intermediate ops |
| Tue May 13 | Ch. 10 (part 2): Collectors, primitive streams, `Optional` |
| Wed May 14 | **Code day** — build stream pipelines, collect to maps/groups, chain `Optional`; review questions Ch. 10 |

**Watch out for:** Wildcard rules (covariance/contravariance), `Comparator` chaining, `Collectors.groupingBy` vs `partitioningBy`.

---

## Week 6 — Exceptions & Modules
*Chapters 11–12*

| Day | Task |
|-----|------|
| Thu May 15 | Ch. 11 **DEEP DIVE** — Exceptions: hierarchy, checked/unchecked, multi-catch, try-with-resources |
| Fri May 16 | **Code day** — write custom exceptions, chained exceptions, try-with-resources with multiple resources; review questions Ch. 11 |
| Sun May 18 | Ch. 12 (part 1): Module system basics — `module-info.java`, `requires`, `exports` |
| Mon May 19 | Ch. 12 (part 2): Services, open modules, `jlink`, `jdeps`, migration strategies; review questions Ch. 12 |

**Watch out for:** Modules are exam-heavy and unfamiliar to most developers — give it extra time.

---

## Week 7 — Concurrency & Exam Prep
*Chapter 13 + Ch. 14 revisit + Full Review*

| Day | Task |
|-----|------|
| Tue May 20 | Ch. 13 (part 1): Concurrency fundamentals — threads, `Runnable`/`Callable`, `ExecutorService`, thread lifecycle |
| Wed May 21 | Ch. 13 (part 2): Atomic classes, `CyclicBarrier`, concurrent collections, **virtual threads** (Java 21) |
| Thu May 22 | **Code day** — write `ExecutorService` tasks, use `AtomicInteger`, spin up virtual threads; review questions Ch. 13 |
| Fri May 23 | Ch. 14 revisit — review questions only (already studied; 1 hr max) + "Exam Essentials" for Concurrency, Streams, and Exceptions |
| Sat May 24 | Full timed practice test (simulate exam: 50 questions, 120 min, no book) |
| Sun May 25 | Review wrong answers |

---

## General Rules

- **Do every "Review Questions" section** — they mirror real exam style closely.
- **Write code** for anything functional/streams/concurrency. Don't just read it.
- The **Appendix** has all answers with explanations — use it actively, not passively.
- Java 21 additions to focus on: **virtual threads**, **sealed classes**, **records**, **pattern matching in switch** — these will appear on the exam.

---

## Java 21 Exam Topics to Prioritize

| Topic | Chapter | Why It Matters |
|-------|---------|----------------|
| Pattern matching in `switch` | Ch. 3 | New in Java 21, guaranteed exam question |
| Sealed classes | Ch. 7 | New in Java 21, exam-heavy |
| Records | Ch. 7 | New in Java 21, exam-heavy |
| Virtual threads | Ch. 13 | Java 21 flagship feature |
| Sequenced collections | Ch. 9 | New API in Java 21 |

---

## Time Reality Check

| Experience Level | Realistic Prep |
|-----------------|----------------|
| Active Java dev | 7 weeks (with code practice) |
| Junior / non-daily Java | 8–10 weeks recommended |

You're well-positioned. The extra week is specifically for writing code alongside reading — the assessment showed that reading alone won't be enough for streams, concurrency, records, and sealed classes.
