# OCP Java SE 21 (1Z0-830) — 6-Week Study Plan

**Book:** OCP Oracle Certified Professional Java SE 21 Developer Study Guide — Boyarsky & Selikoff (1,596 pages, 14 chapters)

**Assumptions:** ~1.5 hrs/day of free office time, 5 days/week ≈ 45 hrs total.
As an active Java developer, Chapters 1–5 will move fast.

---

## Before You Start

- Take the **Assessment Test** in the book's intro — it maps exactly to your weak spots.
- Register for the exam now to lock in a deadline (forces commitment).
- Passing score: **68%**, 50 questions, 90 minutes.

---

## Week 1 — Foundations (Fast Track)
*Chapters 1–3 + Assessment Test review*

| Day | Task |
|-----|------|
| Wed Apr 1 | Assessment Test (score yourself, note gaps) |
| Thu Apr 2 | Ch. 1: Building Blocks — JVM mechanics, primitives, `var`, text blocks |
| Sun Apr 5 | Ch. 2: Operators — ternary, bitwise, compound assignment traps |
| Mon Apr 6 | Ch. 3: Making Decisions — `switch` expressions, pattern matching (Java 21) |
| Tue Apr 7 | Review questions for Ch. 1–3, re-read anything you got wrong |

**Goal:** Chapters 1–3 are mostly revision. If you score >80% on review questions, you're on track.

---

## Week 2 — Core Language Mechanics
*Chapters 4–5*

| Day | Task |
|-----|------|
| Wed Apr 8  | Ch. 4 (part 1): Strings, StringBuilder, equality traps |
| Thu Apr 9  | Ch. 4 (part 2): Arrays, Math API, Date/Time API |
| Sun Apr 12 | Ch. 5 (part 1): Method design, access modifiers, varargs |
| Mon Apr 13 | Ch. 5 (part 2): Static members, overloading rules, pass-by-value |
| Tue Apr 14 | Review questions Ch. 4–5, focus on Date/Time (commonly tricky) |

**Watch out for:** String pool behavior, `==` vs `.equals()`, overloading resolution order.

---

## Week 3 — OOP Deep Dive
*Chapters 6–7*

| Day | Task |
|-----|------|
| Wed Apr 15 | Ch. 6 (part 1): Inheritance, constructor chaining, `super` |
| Thu Apr 16 | Ch. 6 (part 2): Abstract classes, immutable objects, initialization order |
| Sun Apr 19 | Ch. 7 (part 1): Interfaces (default/static/private methods), enums |
| Mon Apr 20 | Ch. 7 (part 2): **Sealed classes**, **records**, nested classes — Java 21 key topics |
| Tue Apr 21 | Review questions Ch. 6–7, draw inheritance diagrams by hand |

**Watch out for:** Object initialization order, sealed class rules, record constructors.

---

## Week 4 — Functional Java (Hardest Week)
*Chapters 8–9*

| Day | Task |
|-----|------|
| Wed Apr 22 | Ch. 8 (part 1): Lambda syntax, functional interfaces, method references |
| Thu Apr 23 | Ch. 8 (part 2): Built-in functional interfaces (`Predicate`, `Function`, `Supplier`, etc.) |
| Sun Apr 26 | Ch. 9 (part 1): Collections API — `List`, `Set`, `Queue`, `Deque`, `Map` |
| Mon Apr 27 | Ch. 9 (part 2): Generics, bounded wildcards (`? extends`, `? super`), `Comparable`/`Comparator` |
| Tue Apr 28 | Review questions Ch. 8–9 — write actual code for any lambdas you got wrong |

**Watch out for:** Wildcard rules (covariance/contravariance), which functional interface to use when, `Comparator` chaining.

---

## Week 5 — Streams, Exceptions, Modules
*Chapters 10–12*

| Day | Task |
|-----|------|
| Wed Apr 29 | Ch. 10 (part 1): Stream pipeline, lazy evaluation, terminal vs intermediate ops |
| Thu Apr 30 | Ch. 10 (part 2): Collectors, primitive streams, `Optional` |
| Sun May 3  | Ch. 11: Exceptions (checked/unchecked), try-with-resources, localization/i18n |
| Mon May 4  | Ch. 12 (part 1): Module system basics — `module-info.java`, `requires`, `exports` |
| Tue May 5  | Ch. 12 (part 2): Services, open modules, `jlink`, `jdeps`, migration strategies |

**Watch out for:** Modules are exam-heavy and unfamiliar to most developers — give it extra time. Streams are also heavily tested.

---

## Week 6 — Concurrency, I/O & Exam Prep
*Chapters 13–14 + Full Review*

| Day | Task |
|-----|------|
| Wed May 6  | Ch. 13: Concurrency — `ExecutorService`, atomic classes, `CyclicBarrier`, **virtual threads** (Java 21) |
| Thu May 7  | Ch. 14: I/O — `Path`/`Files` NIO.2, serialization, `Console` |
| Sun May 10 | Full review of your weakest chapters (check Assessment Test gaps from Week 1) |
| Mon May 11 | Full timed practice test (simulate exam: 50 questions, 90 min, no book) |
| Tue May 12 | Review wrong answers, read "Exam Essentials" sections in weak chapters only |

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
| Active Java dev | 6 weeks is achievable |
| Junior / non-daily Java | 8–10 weeks recommended |

You're well-positioned — the main gaps are likely **modules** and **functional/streams** APIs
which aren't used day-to-day in most projects.
