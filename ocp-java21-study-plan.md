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
| ~~Thu Apr 24 – Sun Apr 27~~ | *(on break)* |
| ~~Tue Apr 28 – Wed May 6~~ | *(running behind)* |
| Thu May 7 | ~~Ch. 5 (full): Method design, access modifiers, varargs, static members, overloading, pass-by-value~~ ✓ |
| Fri May 8 – Sun May 10 | *(skipping)* |

**Watch out for:** String pool behavior, `==` vs `.equals()`, overloading resolution order.

---

## Week 3 — OOP Deep Dive + Ch. 14
*Chapters 6–7 + Ch. 14 front-load*

| Day | Task |
|-----|------|
| Mon May 11 | Ch. 14 review questions + gap fill (notes already exist in chapter14/notes.md — gaps only, not a re-read) |
| Tue May 12 | Ch. 6 (part 1): Inheritance, constructor chaining, `super` |
| Wed May 13 | Ch. 6 (part 2): Abstract classes, immutable objects, initialization order + review questions Ch. 6 |
| Thu May 14 | Ch. 7 (part 1): Interfaces (default/static/private methods), enums |
| Fri May 15 | Ch. 7 (part 2): **Sealed classes**, **records**, nested classes + review questions Ch. 7 |

**Watch out for:** Object initialization order, sealed class rules, record constructors.
> **Assessment gap:** Records and sealed classes are both unfamiliar. Do NOT just read — write code for each. Define a record, use compact constructors, create a sealed hierarchy with `permits`. Get hands-on before moving on.

---

## Week 4 — Functional Java: Lambdas + Collections
*Chapters 8–9*

| Day | Task |
|-----|------|
| Mon May 18 | Ch. 8 (part 1): Lambda syntax, functional interfaces, method references |
| Tue May 19 | Ch. 8 (part 2): Built-in functional interfaces (`Predicate`, `Function`, `Supplier`, `Consumer`, etc.) + review questions Ch. 8 |
| Wed May 20 | Ch. 9 (part 1): Collections API — `List`, `Set`, `Queue`, `Deque`, `Map`, `SequencedMap` |
| Thu May 21 | Ch. 9 (part 2): Generics, bounded wildcards (`? extends`, `? super`), `Comparable`/`Comparator` + review questions Ch. 9 |
| Fri May 22 | Ch. 10 (part 1): Stream pipeline, lazy evaluation, terminal vs intermediate ops |

**Watch out for:** Which functional interface to use when — know the return type signatures cold. Wildcard rules (covariance/contravariance).

---

## Week 5 — Streams, Exceptions & Modules
*Chapters 10–12*

| Day | Task |
|-----|------|
| Mon May 25 | Ch. 10 (part 2): Collectors, primitive streams, `Optional` + review questions Ch. 10 |
| Tue May 26 | Ch. 11 **DEEP DIVE** — Exceptions: hierarchy, checked/unchecked, multi-catch, try-with-resources + review questions Ch. 11 |
| Wed May 27 | **Code day** — custom exceptions, chained exceptions, try-with-resources with multiple resources |
| Thu May 28 | Ch. 12 (part 1): Module system basics — `module-info.java`, `requires`, `exports` |
| Fri May 29 | Ch. 12 (part 2): Services, open modules, `jlink`, `jdeps`, migration strategies + review questions Ch. 12 |

**Watch out for:** Modules are exam-heavy and unfamiliar to most developers — give it extra time. `Collectors.groupingBy` vs `partitioningBy`, `Comparator` chaining.

---

## Week 6 — Concurrency & Exam Prep
*Chapter 13 + Full Review*

| Day | Task |
|-----|------|
| Mon Jun 2 | Ch. 13 (part 1): Concurrency fundamentals — threads, `Runnable`/`Callable`, `ExecutorService`, thread lifecycle |
| Tue Jun 3 | Ch. 13 (part 2): Atomic classes, `CyclicBarrier`, concurrent collections, **virtual threads** (Java 21) + review questions Ch. 13 |
| Wed Jun 4 | **Code day** — write `ExecutorService` tasks, use `AtomicInteger`, spin up virtual threads |
| Thu Jun 5 | Full timed practice test (simulate exam: 50 questions, 90 min, no book) |
| Fri Jun 6 | Review wrong answers + final pass on weak areas |

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
