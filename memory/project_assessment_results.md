---
name: OCP Assessment Test Results
description: Assessment test baseline score and identified weak areas from Apr 1 2026
type: project
---

Score: 6/28 (~21%) on the OCP Java SE 21 assessment test (Apr 1, 2026). Only 28 questions attempted, not the full test.

Confirmed strong enough to move forward but with clear priority gaps:

**CRITICAL (near-zero knowledge):**
- Threads / Concurrency (Ch. 13) — user said "next to zero knowledge"
- Streams (Ch. 10) — weak

**HIGH (unfamiliar, needs hands-on):**
- Exceptions (Ch. 11) — needs deep dive
- Records (Ch. 7) — unfamiliar syntax and rules
- Sealed classes (Ch. 7) — unfamiliar

**MEDIUM (shaky):**
- Access modifiers, especially `protected`
- `@Override` — unclear what happens without it
- `.intern()` / String pool behavior
- SequencedMap (new Java 21 API)

**Why:** Baseline assessment before structured study. Plan updated to give threads 2 days instead of 1, flag exceptions as deep dive, and add hands-on code requirement for records/sealed.

**How to apply:** When explaining concurrency or streams topics, assume no existing mental model — build from scratch. For records/sealed, always prefer worked examples over abstract rules.
