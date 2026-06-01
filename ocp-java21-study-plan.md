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

## Week 3 — Ch. 7 + Ch. 8 *(revised Jun 1 — exam this month)*
*Chapters 7–8*

> **Status:** Ch. 1–6 done. Ch. 14 notes exist. Target: sit the exam by Jun 25–27. Sessions are heavy — no light days until the final rest day.

| Day | Task |
|-----|------|
| ~~Mon Jun 1~~ | ~~Ch. 7 (part 1): Interfaces — default/static/private methods, multiple inheritance rules, `@FunctionalInterface`~~ |
| Tue Jun 2 | Ch. 7 (part 2): Enums + sealed classes |
| Wed Jun 3 | Ch. 7 (part 3): Records + nested classes (static nested, inner, local, anonymous) + review questions Ch. 7 |
| Thu Jun 4 | Ch. 8 (part 1): Lambda syntax, effectively final, method references (all 4 forms) |
| Fri Jun 5 | Ch. 8 (part 2): Built-in functional interfaces (`Predicate`, `Function`, `Supplier`, `Consumer`, `BiFunction`, etc.) + composition + review questions Ch. 8 |
| Sat Jun 6 | **Code day**: sealed hierarchy with `permits`; record with compact constructor; enum with abstract method; lambda pipelines |
| Sun Jun 7 | Ch. 9 (part 1): `List`, `Set`, `Queue`, `Deque`, `Map`, `SequencedCollection`, `SequencedMap` |

**Watch out for:** Ch. 7 — records can't extend classes, compact constructors can't assign fields directly, sealed subclasses must be in same package/module. Ch. 8 — know every functional interface signature cold.

---

## Week 4 — Ch. 9 + Ch. 10 + Ch. 11 + Ch. 12
*Chapters 9–12*

| Day | Task |
|-----|------|
| Mon Jun 8 | Ch. 9 (part 2): Generics, bounded wildcards (`? extends`, `? super`), `Comparable`/`Comparator` + review questions Ch. 9 |
| Tue Jun 9 | Ch. 10 (part 1): Stream pipeline model, lazy evaluation, `filter`, `map`, `flatMap`, `sorted`, `limit` |
| Wed Jun 10 | Ch. 10 (part 2): Terminal ops, `Collectors` (`groupingBy`, `partitioningBy`, `joining`), primitive streams, `Optional` + review questions Ch. 10 |
| Thu Jun 11 | **Code day**: write stream pipelines end-to-end — `filter` → `map` → `collect`; `Optional` chaining; `groupingBy` |
| Fri Jun 12 | Ch. 11 **DEEP DIVE**: Exception hierarchy, checked/unchecked, multi-catch, try-with-resources, exception chaining + review questions Ch. 11 |
| Sat Jun 13 | Ch. 12 (part 1): Module system — `module-info.java`, `requires`, `exports`, `opens` |
| Sun Jun 14 | Ch. 12 (part 2): Services (`provides`/`uses`), `jlink`, `jdeps`, unnamed/automatic modules + review questions Ch. 12 |

**Watch out for:** Streams are lazy — nothing runs without a terminal op. Wildcard rules: `? extends` = read, `? super` = write. `TreeMap`/`TreeSet` reject null keys. Modules: `exports` is compile-time, `opens` is runtime.

---

## Week 5 — Ch. 13 + Ch. 14 + Java 21 Blitz + Practice Test #1
*Chapters 13–14 + revision*

| Day | Task |
|-----|------|
| Mon Jun 15 | Ch. 13 (part 1): Thread lifecycle, `Runnable`/`Callable`, `ExecutorService`, `Future` |
| Tue Jun 16 | Ch. 13 (part 2): Atomic classes, `CyclicBarrier`, concurrent collections, **virtual threads** |
| Wed Jun 17 | **Code day** + review questions Ch. 13: `ExecutorService` tasks, `Future.get()`, `AtomicInteger`, `Thread.ofVirtual()` |
| Thu Jun 18 | Ch. 14 gap fill (`chapter14/notes.md` exists — questions and gaps only, no full re-read) |
| Fri Jun 19 | **Java 21 blitz**: sealed classes, records, virtual threads, pattern matching in `switch`, `SequencedCollection` — your own notes only |
| Sat Jun 20 | **Practice test #1** — 50 questions, 90 min, no book; review every wrong answer |
| Sun Jun 21 | Targeted re-read of weak chapters from practice test #1 |

**Watch out for:** Concurrency is the biggest knowledge gap — don't skim the code day. `try-with-resources` closes in reverse order. Resources in multi-catch can't be related by inheritance.

---

## Exam Week — Practice Test #2 + EXAM
*Jun 22–27*

| Day | Task |
|-----|------|
| Mon Jun 22 | **Practice test #2** — 50 questions, 90 min, no book; review every wrong answer |
| Tue Jun 23 | Final weak area review — identified from both practice tests, no new material |
| Wed Jun 24 | Light review — your own notes only |
| Thu Jun 25 | Rest |
| Fri Jun 26 – Sat Jun 27 | **EXAM WINDOW** — book as early as possible in this window |

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
| Active Java dev | 7 weeks (comfortable) / **4 weeks (compressed — this plan)** |
| Junior / non-daily Java | 8–10 weeks recommended |

This plan is compressed to ~4 weeks total (Jun 1–27). It's aggressive but viable for an active Java developer. The only real unknowns are concurrency (Ch. 13 — ground-up study needed) and streams (Ch. 10 — needs hands-on code, not just reading). Do not skip the code days for those two chapters.
