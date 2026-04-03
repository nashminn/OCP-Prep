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

## Week 1 — Foundations (Fast Track)
*Chapters 1–3*

| Day | Task |
|-----|------|
| Wed Apr 1 | ~~Assessment Test~~ ✓ Done |
| Mon Apr 6 | Ch. 1: Building Blocks — JVM mechanics, primitives, `var`, text blocks |
| Tue Apr 7 | Ch. 2: Operators — ternary, bitwise, compound assignment traps |
| Wed Apr 8 | Ch. 3: Making Decisions — `switch` expressions, pattern matching (Java 21) |
| Thu Apr 9 | Review questions Ch. 1–3 + **code**: write `switch` expressions with pattern matching, text blocks |

**Goal:** Chapters 1–3 are mostly revision. If you score >80% on review questions, you're on track.

---

## Week 2 — Core Language Mechanics
*Chapters 4–5*

| Day | Task |
|-----|------|
| Sun Apr 12 | Ch. 4 (part 1): Strings, StringBuilder, equality traps |
| Mon Apr 13 | Ch. 4 (part 2): Arrays, Math API, Date/Time API |
| Tue Apr 14 | Ch. 5 (part 1): Method design, access modifiers, varargs |
| Wed Apr 15 | Ch. 5 (part 2): Static members, overloading rules, pass-by-value |
| Thu Apr 16 | Review questions Ch. 4–5 + **code**: String pool traps, Date/Time manipulation, access modifier scenarios |

**Watch out for:** String pool behavior, `==` vs `.equals()`, overloading resolution order.

---

## Week 3 — OOP Deep Dive
*Chapters 6–7*

| Day | Task |
|-----|------|
| Sun Apr 20 | Ch. 6 (part 1): Inheritance, constructor chaining, `super` |
| Mon Apr 21 | Ch. 6 (part 2): Abstract classes, immutable objects, initialization order |
| Tue Apr 22 | Ch. 7 (part 1): Interfaces (default/static/private methods), enums |
| Wed Apr 23 | Ch. 7 (part 2): **Sealed classes**, **records**, nested classes — Java 21 key topics |
| Thu Apr 24 | Review questions Ch. 6–7, draw inheritance diagrams by hand + **code**: sealed hierarchy with `permits`, record with compact constructor, `@Override` scenarios |

**Watch out for:** Object initialization order, sealed class rules, record constructors.
> **Assessment gap:** Records and sealed classes are both unfamiliar. Do NOT just read — write code for each. Define a record, use compact constructors, create a sealed hierarchy with `permits`. Get hands-on before moving on.

---

## Week 4 — Functional Java: Lambdas
*Chapter 8*

| Day | Task |
|-----|------|
| Sun Apr 27 | Ch. 8 (part 1): Lambda syntax, functional interfaces, method references |
| Mon Apr 28 | Ch. 8 (part 2): Built-in functional interfaces (`Predicate`, `Function`, `Supplier`, `Consumer`, etc.) |
| Tue Apr 29 | **Code day** — chain predicates, write method references for all 4 forms, implement `Comparator.comparing()` |
| Wed Apr 30 | Review questions Ch. 8 |
| Thu May 1  | Ch. 9 (part 1): Collections API — `List`, `Set`, `Queue`, `Deque`, `Map`, `SequencedMap` |

**Watch out for:** Which functional interface to use when — know the return type signatures cold.

---

## Week 5 — Collections, Generics & Streams
*Chapters 9–10*

| Day | Task |
|-----|------|
| Sun May 4  | Ch. 9 (part 2): Generics, bounded wildcards (`? extends`, `? super`), `Comparable`/`Comparator` |
| Mon May 5  | **Code day** — write generic classes, wildcard methods, `Comparator` chaining; review questions Ch. 9 |
| Tue May 6  | Ch. 10 (part 1): Stream pipeline, lazy evaluation, terminal vs intermediate ops |
| Wed May 7  | Ch. 10 (part 2): Collectors, primitive streams, `Optional` |
| Thu May 8  | **Code day** — build stream pipelines, collect to maps/groups, chain `Optional`, review questions Ch. 10 |

**Watch out for:** Wildcard rules (covariance/contravariance), `Comparator` chaining, `Collectors.groupingBy` vs `partitioningBy`.

---

## Week 6 — Exceptions & Modules
*Chapters 11–12*

| Day | Task |
|-----|------|
| Sun May 11 | Ch. 11 **DEEP DIVE** — Exceptions: hierarchy, checked/unchecked, multi-catch, try-with-resources |
| Mon May 12 | **Code day** — write custom exceptions, chained exceptions, try-with-resources with multiple resources; review questions Ch. 11 |
| Tue May 13 | Ch. 12 (part 1): Module system basics — `module-info.java`, `requires`, `exports` |
| Wed May 14 | Ch. 12 (part 2): Services, open modules, `jlink`, `jdeps`, migration strategies |
| Thu May 15 | Review questions Ch. 12 + localization/i18n from Ch. 11 |

**Watch out for:** Modules are exam-heavy and unfamiliar to most developers — give it extra time.

---

## Week 7 — Concurrency, I/O & Exam Prep
*Chapters 13–14 + Full Review*

| Day | Task |
|-----|------|
| Sun May 18 | Ch. 13 (part 1): Concurrency fundamentals — threads, `Runnable`/`Callable`, `ExecutorService`, thread lifecycle |
| Mon May 19 | Ch. 13 (part 2): Atomic classes, `CyclicBarrier`, concurrent collections, **virtual threads** (Java 21) |
| Tue May 20 | **Code day** — write `ExecutorService` tasks, use `AtomicInteger`, spin up virtual threads; Ch. 14: I/O condensed *(active devs usually know Path/Files)* |
| Wed May 21 | Full timed practice test (simulate exam: 50 questions, 90 min, no book) |
| Thu May 22 | Review wrong answers; "Exam Essentials" for Concurrency, Streams, and Exceptions only |

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
