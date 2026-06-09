---
name: practice-questions-quality
description: Quality assessment of generated OCP practice questions for chapters 6-10, what's good, what's missing, and what "harder" means for future generation
metadata:
  type: project
---

## What's been generated

50 OCP-style questions + answers for each of chapters 6–10 (250 total), stored in `practice/chapter{6-10}/questions.md` and `answers.md`. Format matches the existing chapter 1–5 files exactly.

## Quality verdict: ~70–75% of actual OCP 1Z0-830 difficulty

### What IS genuinely OCP-level (keep doing this)
- Variable hiding vs overriding traps (Ch6 Q3: `Parent p = new Child(); p.x` uses reference type)
- Method override rules crammed into one question: covariant return + access widening + checked exceptions (Ch6 Q18)
- Interface static methods not callable via instance reference (Ch7 Q3)
- `list.remove(1)` on `List<Integer>` removes by index not value (Ch9 Q21)
- `reduce()` with identity returns `T`; without identity returns `Optional` (Ch10 Q13)
- `partitioningBy` always produces both `true` and `false` keys (Ch10 Q17)
- `Comparator.comparing(...).thenComparing(...)` chaining questions (Ch9 Q20)

### What's MISSING / too easy compared to real exam
1. **Some early questions are too basic** — e.g. Ch6 Q1 (simple init order), Ch6 Q2 (basic inheritance facts)
2. **Distractors are too obviously wrong** — real OCP wrong answers are plausibly correct, not just filler
3. **Code snippets are too short** — real exam often gives 20–30 line snippets with multiple interacting classes
4. **Missing hardest edge cases:**
   - Sealed class + pattern matching exhaustiveness (switch with no default because hierarchy is covered)
   - `var` in lambda context (not allowed)
   - Record implementing interface with conflicting default method
   - Generics with multiple bounds: `<T extends Comparable<T> & Serializable>`
   - `Stream.toList()` (Java 16, unmodifiable) vs `Collectors.toList()` (modifiable) behavior difference
   - `SequencedCollection` new Java 21 methods (`getFirst()`, `getLast()`, `reversed()`)
   - Complex wildcard PECS scenarios with method signatures
   - Streams with stateful ops on parallel pipelines
   - `compute()` / `merge()` on Map returning null removes the entry
5. **Missing multi-class interaction questions** — real exam often has 3+ classes interacting

## How to generate harder questions

When asked to "amp up difficulty" or generate a "second harder set", use these constraints:
- Minimum 20 lines of code per code-snippet question
- Wrong answers must be things a well-prepared person could plausibly choose
- At least 5 questions per chapter must involve 3+ interacting classes/interfaces
- Include at least 3 questions per chapter from the "missing edge cases" list above
- Add questions that combine two topics (e.g. lambda capturing a variable from a sealed-class switch, or a stream pipeline using a custom Comparator with generics)
- For ch10 specifically: more pipeline questions where you must trace exactly what each op produces

## Chapters not yet generated
- Chapters 11–14 (Exceptions & Localization, Modules, Concurrency, I/O) — not started as of 2026-06-09

**Why:** User asked for 6–10 first. Same format and difficulty target applies. When generating 11–14, apply the harder constraints from the start.
