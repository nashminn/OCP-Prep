---
name: practice-questions-quality
description: Quality assessment of generated OCP practice questions for chapters 1-14, what's good, what's missing, and what "harder" means for future generation
metadata:
  type: project
---

## What's been generated

- Chapters 1–9: 50 questions + answers each, stored in `practice/chapter{1-9}/questions.md` and `answers.md`.
- Chapter 10 (Streams): 100 questions (Q1-50 original pass, Q51-100 "harder pass" added 2026-06-11).
- Chapters 11 (Exceptions & Localization, 70 Q), 12 (Modules, 60 Q), 13 (Concurrency, 60 Q), 14 (I/O, 55 Q): generated 2026-06-11, harder pass from the start.

All files follow the same markdown format: `**N.**` question stems, ```java code fences, lettered options with inline-code backticks, `---` separators, and `**N. <letters>**` answer headers with trace-through explanations.

### Generation process for ch10 (51-100) and ch11-14 (2026-06-11)
Five parallel agents, one per chapter, each given: PDF-extracted chapter text + official review-question/answer-key sections (from the OCP Java SE 21 Study Guide, Boyarsky & Selikoff) as grounding, the "harder pass" constraints below, and a detailed topic/exam-trap checklist. Each agent compiled and ran real Java snippets against JDK 21 to verify "what is the output" answers before finalizing — this caught real errors (wrong option letters, miscomputed outputs) during drafting. Verified post-hoc: correct sequential numbering 1-N, no duplicates, headers match `# Chapter NN: <Title> — Practice/Answers` format, "(Choose N)" counts match answer-key letter counts.

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

## Status
All 14 chapters now have practice question sets (1-9: 50 each, 10: 100, 11: 70, 12: 60, 13: 60, 14: 55). Total ~865 questions across the project.
