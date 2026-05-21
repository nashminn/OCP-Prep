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

## Week 3 — Ch. 6 + Ch. 7 *(revised from May 20)*
*Chapters 6–7*

> **Status:** Ch. 6 in progress (page 536). Ch. 11 was skipped; Ch. 14 notes exist, gap fill still pending. Fridays and Saturdays added as light days from here on.

| Day | Task |
|-----|------|
| Wed May 20 | Finish Ch. 6 reading + review questions Ch. 6 |
| Thu May 21 | Ch. 7 (part 1): Interfaces — default/static/private methods, multiple inheritance rules, `@FunctionalInterface` |
| Fri May 22 *(light)* | Ch. 7 (part 2): Enums + sealed classes — read only |
| Sat May 23 *(light)* | Code: sealed hierarchy with `permits`; record with compact constructor; define and call an enum with abstract method |
| Sun May 24 | Ch. 7 (part 3): Records + nested classes (static nested, inner, local, anonymous) + review questions Ch. 7 |

**Watch out for:** Object initialization order in Ch. 6. For Ch. 7: records can't extend classes, compact constructors can't assign fields directly, sealed class subclasses must be in same package/module.

---

## Week 4 — Ch. 8 + Ch. 9 + Ch. 10 (start)
*Chapters 8–10*

| Day | Task |
|-----|------|
| Mon May 25 | Ch. 8 (part 1): Lambda syntax, effectively final, method references (all 4 forms) |
| Tue May 26 | Ch. 8 (part 2): Built-in functional interfaces (`Predicate`, `Function`, `Supplier`, `Consumer`, `BiFunction`, etc.) + composition + review questions Ch. 8 |
| Wed May 27 | Ch. 9 (part 1): `List`, `Set`, `Queue`, `Deque`, `Map`, `SequencedCollection`, `SequencedMap` |
| Thu May 28 | Ch. 9 (part 2): Generics, bounded wildcards (`? extends`, `? super`), `Comparable`/`Comparator` + review questions Ch. 9 |
| Fri May 29 *(light)* | Ch. 10 (part 1): Stream pipeline model, lazy evaluation, `filter`, `map`, `flatMap`, `sorted`, `limit` — read only |
| Sat May 30 *(light)* | Code: write stream pipelines end-to-end — practice `filter` → `map` → `collect`; experiment with `Optional` |

**Watch out for:** Which functional interface returns what — know the signatures cold. Wildcard rules: `? extends` = read, `? super` = write. `TreeMap`/`TreeSet` reject null keys.

---

## Week 5 — Ch. 10 (finish) + Ch. 11 + Ch. 12
*Chapters 10–12*

| Day | Task |
|-----|------|
| Sun May 31 | Ch. 10 (part 2): Terminal ops, `Collectors` (`groupingBy`, `partitioningBy`, `joining`), primitive streams, `Optional` + review questions Ch. 10 |
| Mon Jun 1 | Ch. 11 **DEEP DIVE**: Exception hierarchy, checked/unchecked, multi-catch, try-with-resources, exception chaining + review questions Ch. 11 |
| Tue Jun 2 | Ch. 12 (part 1): Module system — `module-info.java`, `requires`, `exports`, `opens` |
| Wed Jun 3 | Ch. 12 (part 2): Services (`provides`/`uses`), `jlink`, `jdeps`, unnamed/automatic modules, migration + review questions Ch. 12 |
| Thu Jun 4 | Ch. 13 (part 1): Concurrency fundamentals — thread lifecycle, `Runnable`/`Callable`, `ExecutorService`, `Future` |
| Fri Jun 5 *(light)* | Ch. 13 (part 2): Atomic classes, `CyclicBarrier`, concurrent collections, **virtual threads** — read only |
| Sat Jun 6 *(light)* | **Code day**: write `ExecutorService` tasks, retrieve via `Future.get()`, use `AtomicInteger`, spin up virtual threads with `Thread.ofVirtual()` |

**Watch out for:** Streams are lazy — nothing runs without a terminal op. Modules: `exports` is compile-time, `opens` is runtime. Resources in try-with-resources close in reverse order.

---

## Week 6 — Ch. 13 Review + Ch. 14 + Exam Prep
*Chapter 13 wrap-up + Ch. 14 gap fill + Practice*

| Day | Task |
|-----|------|
| Sun Jun 7 | Ch. 13 review questions + Ch. 14 gap fill (notes exist in `chapter14/notes.md` — questions and gaps only, no full re-read) |
| Mon Jun 8 | **Practice test #1** — 50 questions, 90 min, no book; then review every wrong answer |
| Tue Jun 9 | Targeted re-read of weak chapters from practice test #1 |
| Wed Jun 10 | Java 21 blitz: sealed classes, records, virtual threads, pattern matching in `switch`, `SequencedCollection` |
| Thu Jun 11 | **Practice test #2** — 50 questions, 90 min, no book; then review every wrong answer |
| Fri Jun 12 *(light)* | Final pass on weakest areas only |
| Sat Jun 13 *(light)* | Rest — or very light read of your own notes if needed |

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
