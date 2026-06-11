# Chapter 13: Concurrency — Answers

---

**1. A**

Calling `run()` directly on a `Thread` (or `Runnable`) does **not** start a new thread — it simply invokes the method like any other method call, executing synchronously on the current thread. So `g.run()` prints `Hello ` first, completing entirely before `main()` continues to the next line, and then `World` is printed. The output is deterministic: `Hello World`. Option C would be true if `g.start()` had been used instead, since then the new thread and the main thread would race. The code compiles fine, and no exception is thrown.

---

**2. D**

`t.start()` is called twice on the same `Thread` instance. The first call successfully starts the thread (which prints `Working `). The second call to `start()` on a thread that has already been started (and has either completed or is still running) throws `IllegalThreadStateException` — a `Thread` can only be started once during its lifetime. The code compiles fine (A and C are wrong), and `IllegalThreadStateException` is a `RuntimeException`, distinct from the more general `IllegalStateException` (E is wrong).

---

**3. A, B**

Calling `start()` causes the JVM to allocate a new call stack and execute the `run()` method on a separate thread of execution (A is correct). Calling `run()` directly executes the method body on the calling thread, just like any normal method invocation (B is correct). A class can implement `Runnable` while also extending another class, since `Runnable` is an interface (C is false — this is actually one advantage of `implements Runnable` over `extends Thread`). Calling `start()` twice on the same instance throws `IllegalThreadStateException` rather than running the task twice (D is false). `Runnable.run()` returns `void`, not `Object` (E is false).

---

**4. B**

`Thread.sleep(long)` declares `throws InterruptedException`, which is a checked exception. The `main()` method here does not declare `throws InterruptedException` and does not wrap the call in a try/catch, so the code fails to compile. To fix this, `main()` would need either `throws InterruptedException` in its signature or a try/catch block around the `sleep()` call. `Thread.sleep()` is in fact a static method (C is wrong), so that's not the issue.

---

**5. B**

`Thread.join()` causes the calling thread (here, `main`) to wait until the target thread (`t`) has finished execution before continuing. Since `t.join()` is called before printing `"Done"`, the loop inside `t` (printing `0 1 2 `) is guaranteed to fully complete before `"Done"` is printed. The output is therefore deterministically `0 1 2 Done`. `join()` does throw a checked `InterruptedException`, but `main()` declares `throws InterruptedException`, so the code compiles fine (D is wrong).

---

**6. A**

When `interrupt()` is called on a thread that is currently blocked in `Thread.sleep()` (or `wait()`/`join()`), the sleeping thread immediately wakes up and throws `InterruptedException` from within the `sleep()` call — this is the standard mechanism for interrupting a sleeping thread. Calling `interrupt()` does not forcibly terminate a thread (B is false) — it merely sets a flag (or throws `InterruptedException` if currently blocked); the thread's code decides how to respond. `Thread.interrupted()` is a **static** method that checks **and clears** the current thread's interrupted status (C is false on both counts). Interrupting a `RUNNABLE` thread does not throw any exception — it simply sets the interrupt flag, which the thread can check later via `isInterrupted()` or `Thread.interrupted()` (D is false). Any thread with a reference to another thread can call `interrupt()` on it (E is false).

---

**7. B**

`Thread.currentThread().interrupt()` sets the interrupt flag on the main thread. `Thread.interrupted()` is a static method that returns the current interrupt status **and clears it** as a side effect. The first call returns `true` (the flag was set) and clears the flag. The second call returns `false` because the flag was already cleared by the first call. Output: `true` then `false`.

---

**8. A**

`Executors.newSingleThreadExecutor()` creates an executor backed by a single worker thread that processes submitted tasks sequentially, in the order they were submitted (FIFO). Even though the main thread that calls `execute()` is different from the worker thread, the worker thread itself can only run one task at a time, and it pulls tasks from its internal queue in submission order. Therefore `A`, `B`, and `C` are guaranteed to print in that order: `ABC`. The try-with-resources block calls `close()` on exit, which waits for queued tasks to complete before the block exits, so all three tasks do run (D is wrong). `execute(Runnable)` is a valid method that accepts a `Runnable` lambda (C is wrong), and submitting tasks to an open executor does not throw (E is wrong).

---

**9. D**

`Executors.newVirtualThreadPerTaskExecutor()` (introduced in Java 21) creates a new virtual thread for every submitted task. Virtual threads are extremely lightweight (cheap to create in the millions) and are not pooled, since pooling them would provide no benefit. `newCachedThreadPool()` (A) reuses platform threads when possible but still pools them. `newFixedThreadPool(1)` (B) and `newSingleThreadExecutor()` (C) use exactly one platform thread for all tasks, executing them sequentially — not "a new thread for each task." `newWorkStealingPool()` (E) creates a pool of platform threads sized to the number of available processors, using a work-stealing algorithm — also pooled.

---

**10. C**

`execute(Runnable)` has a return type of `void` — it does not return a `Future`. The line `Future<?> f1 = service.execute(...)` attempts to assign a `void` result to a `Future<?>` variable, which is a type mismatch and causes a compile error. To get a `Future<?>` back, the code would need to call `submit(Runnable)` instead of `execute(Runnable)`.

---

**11. C**

The `Callable` lambda `() -> { int x = 10/0; return x; }` throws an `ArithmeticException` ("/ by zero") when executed. When an exception is thrown inside a task submitted via `submit()`, the exception is captured and stored in the `Future`. Calling `get()` on that `Future` re-throws it wrapped inside a checked `ExecutionException`, where `getCause()` returns the original `ArithmeticException`. The exception is **not** thrown directly from `get()` (B is wrong) — it must be unwrapped via `getCause()`. `Callable.call()` is explicitly allowed to throw `Exception` (A is wrong), so the code compiles fine.

---

**12. B**

`Future.get(long timeout, TimeUnit unit)` blocks for at most the given duration waiting for the task to complete. Here the task sleeps for 5 seconds, but `get()` is given only 1 second. Since the task is not done within 1 second, `get(1, TimeUnit.SECONDS)` throws a checked `TimeoutException`, which is caught and prints `Timed out`. `Future.get(long, TimeUnit)` is a valid two-argument overload (D is wrong), and the task itself never completes within the timeout window, so `"done"` is never printed (A and E are wrong).

---

**13. B, C**

`isDone()` returns `true` if the task has reached any terminal state — completed normally, threw an exception, or was cancelled (B is correct; A is too narrow because it excludes exception/cancellation cases). `cancel(false)` requests cancellation without interrupting a currently running task — if the task is already running, it will be allowed to complete, and `cancel()` returns `false` in that case rather than stopping it (C is correct). `cancel(true)` attempts to interrupt the running thread, but this is not a guarantee — a task that ignores `InterruptedException` or doesn't check `isInterrupted()` may continue running regardless (D is wrong). `isCancelled()` returns `true` only if the task was cancelled before it completed normally — a task that finished normally or threw an exception returns `false` from `isCancelled()` even though `isDone()` is `true` (E is wrong).

---

**14. C**

`shutdown()` puts the executor into a state where it no longer accepts new tasks — any task submitted afterward via `execute()` or `submit()` is rejected by throwing `RejectedExecutionException` (an unchecked exception) immediately at the call site. Here, `shutdown()` is called *before* `submit()`, so `submit(() -> "Hello")` immediately throws `RejectedExecutionException`, and `result.get()` is never reached. The code compiles without issue — there's no rule against calling `shutdown()` before `submit()` from a syntax perspective (D is wrong); it's simply a runtime problem.

---

**15. B**

`shutdown()` initiates an orderly shutdown: previously submitted tasks (including queued ones) are allowed to run to completion, but no new tasks are accepted (`isShutdown()` becomes `true` immediately, while `isTerminated()` becomes `true` only once all tasks finish). `shutdownNow()` attempts to stop all actively executing tasks (typically via `interrupt()`), halts the processing of queued tasks, and **returns** a `List<Runnable>` of the tasks that were awaiting execution and never started — so C has the return types backwards. Neither method blocks waiting for tasks to finish — that's the job of `awaitTermination()` (D is wrong). `shutdownNow()` can be called directly without a prior `shutdown()` call, but `shutdown()` does not require `shutdownNow()` first either (E is wrong).

---

**16. B**

`awaitTermination(timeout, unit)` blocks until either all tasks complete following a shutdown request, or the timeout elapses, returning `true` if the executor terminated and `false` if the timeout elapsed first. Here, two 3-second tasks are submitted to a pool of 2 threads (so they run concurrently), `shutdown()` is called (which stops new tasks but lets the running ones finish), and then `awaitTermination(1, TimeUnit.SECONDS)` waits only 1 second — far less than the ~3 seconds needed. The method returns `false` after that 1 second without throwing any exception. `awaitTermination()` is a real method on `ExecutorService` (C is wrong) and does not throw `TimeoutException` (E is wrong) — it simply returns a boolean.

---

**17. B**

`ScheduledExecutorService.schedule(Callable<V>, long, TimeUnit)` schedules the given `Callable` to run after the specified delay and returns a `ScheduledFuture<V>`. Calling `future.get()` blocks until the task has run and completed — including waiting out the scheduled delay if it hasn't elapsed yet. Since the delay is 2 seconds, `get()` blocks for approximately 2 seconds before returning `"Zoo opens"`, which is then printed. `schedule()` does have an overload that accepts `Callable` (C is wrong), and the result is not `null` (D is wrong) since the `Callable` returns a non-null `String`.

---

**18. B**

`scheduleWithFixedDelay(command, initialDelay, delay, unit)` schedules the next execution to begin `delay` time units after the **previous execution finishes** (i.e., the delay is measured from the end of one run to the start of the next). This is exactly the "begins exactly 10 seconds after the previous run finishes" requirement. By contrast, `scheduleAtFixedRate(command, initialDelay, period, unit)` (A) schedules each new execution at fixed intervals measured from the **start** of the previous execution, regardless of how long that execution took — which can cause overlapping or back-to-back execution if a run takes longer than the period. `schedule()` (C) only runs the task once. Manually looping with `Thread.sleep()` (D) is a valid but inferior, error-prone alternative that doesn't use the Concurrency API's scheduling features as intended — and it is not what's described as "best." A and B are explicitly different in their timing semantics (E is wrong).

---

**19. B**

`scheduleAtFixedRate()` submits a new task execution every `period` regardless of whether the previous execution has finished. If each execution of `task` consistently takes 3 seconds but a new one is scheduled every 1 second, executions will pile up faster than they can be completed (since a single-threaded scheduled executor processes them one at a time, or even with multiple threads, the backlog grows). Over time, this can result in an ever-growing internal queue of pending tasks, eventually exhausting available memory. The Concurrency API does not throw an exception for this scenario up front (A is wrong), nor does it automatically skip overlapping executions (C is wrong) or silently convert to `scheduleWithFixedDelay()` semantics (D is wrong). The code compiles fine (E is wrong) — this is purely a runtime design/performance hazard.

---

**20. A**

Each `Counter` instance (`c1` and `c2`) has its own `count` field and its own intrinsic lock. The `increment()` method is `synchronized`, meaning calls to `c1.increment()` from `task1` acquire the monitor on `c1`, ensuring that the `count++` operations on `c1` are properly serialized — even though only one thread (`t1`) ever calls `c1.increment()` here, so there isn't even cross-thread contention on `c1`. Similarly, `t2` exclusively increments `c2` 1000 times via its own synchronized calls. Because each counter is only ever incremented by a single dedicated thread, and `synchronized` (even when uncontended) still ensures visibility of the final value when `getCount()` is called after `join()`, the result is deterministically `1000 1000`. Instance methods absolutely can be `synchronized` (D is wrong), and no `ConcurrentModificationException` applies here since no collections are involved (E is wrong).

---

**21. B**

A `static synchronized` method synchronizes on the `Class` object associated with the class (here, `StaticLockDemo.class`) — this lock is shared across **all** instances and is sometimes called the "class lock." A non-static `synchronized` instance method synchronizes on `this` — the specific object instance the method was called on. These are two completely different monitor objects: the `Class` object versus an instance of `StaticLockDemo`. Therefore, a thread calling the static method `update()` and a thread calling the instance method `instanceMethod()` do **not** block each other, because they are competing for different locks (A and E are wrong). Static methods absolutely can be declared `synchronized` (C is wrong), and synchronization does not depend on method arguments (D is wrong).

---

**22. B**

`process()` is an instance method marked `synchronized`, which is equivalent to wrapping the method body in `synchronized(this) { ... }`. The monitor being acquired is the **specific object instance** on which the method is called — `this`. Since T1 calls `a.process()` and T2 calls `b.process()` on two *different* objects, they are acquiring locks on two different monitors (`a`'s monitor and `b`'s monitor). There is no shared lock between them, so the `synchronized` modifier provides no mutual exclusion between these two calls — both threads can execute `process()` at the same time. This is a classic exam trap: students often assume `synchronized` always serializes *all* calls to a method across *all* objects, but per-instance synchronization only protects access to a single shared object.

---

**23. B**

`AtomicInteger.getAndIncrement()` is the atomic equivalent of the post-increment operator `value++` — it returns the value **before** incrementing, then increments. Starting at `5`, `getAndIncrement()` returns `5` and the value becomes `6`. `incrementAndGet()` is the equivalent of pre-increment `++value` — it increments first, then returns the new value. The value `6` becomes `7`, and `incrementAndGet()` returns `7`. Finally, `get()` simply returns the current value, `7`. Output: `5`, `7`, `7`.

---

**24. B**

`compareAndSet(expectedValue, newValue)` atomically checks whether the current value equals `expectedValue`; if so, it updates the value to `newValue` and returns `true`. If the current value does not equal `expectedValue`, it does nothing and returns `false`. Initially the value is `10`. The first call `compareAndSet(10, 20)` finds the current value (`10`) matches the expected value (`10`), so it updates to `20` and returns `true`. The second call `compareAndSet(10, 30)` checks whether the current value (now `20`) equals `10` — it does not, so the update is rejected, the value remains `20`, and the call returns `false`. Final state: `result1=true`, `result2=false`, `value=20`. Output: `true false 20`.

---

**25. A, C**

`AtomicInteger` and the other atomic classes use low-level CPU compare-and-swap (CAS) instructions rather than acquiring a traditional monitor lock, which can reduce thread contention compared to `synchronized` for simple operations (A is correct). Methods like `incrementAndGet()` perform the entire read-modify-write sequence as a single, indivisible (atomic) operation — this is precisely what makes them safe without explicit locking for that single operation (C is correct). However, `AtomicInteger` only guarantees atomicity for its individual method calls — it does **not** make an arbitrary sequence of multiple operations on it (or other variables) atomic as a whole; for that, you'd still need `synchronized` or a `Lock` (B is wrong). Using `AtomicInteger` does not eliminate the need for `volatile` on unrelated shared fields elsewhere in a class (D is wrong). `AtomicInteger` instances can be freely used inside `synchronized` blocks alongside other logic — there's no restriction against it (E is wrong).

---

**26. C**

Unlike `HashMap`, `ConcurrentHashMap` does not permit `null` keys or `null` values. Calling `map.put("b", null)` throws a `NullPointerException` immediately when `put()` is invoked — execution never reaches the `System.out.println(map.get("a"))` line. This is a deliberate design decision in `ConcurrentHashMap`: since `get(key)` returning `null` is used to mean "the key is not present," allowing `null` values would create ambiguity in a concurrent context (you couldn't distinguish "absent" from "present with a null value" reliably across threads). The code compiles fine — `String` is a perfectly valid value type for `ConcurrentHashMap<String, String>` (D is wrong).

---

**27. B**

`CopyOnWriteArrayList` creates a fresh copy of its internal array on every mutating operation (`add`, `remove`, `set`, etc.), and any iterator created from the list operates over a **snapshot** of the array taken at the time the iterator was created. The enhanced `for` loop here creates one such iterator over the original 3-element snapshot (`["lion", "tiger", "bear"]`). As the loop executes, it prints each of those three original elements (`lion tiger bear `) — the three `animals.add("newAnimal")` calls modify the underlying list but do **not** affect the snapshot the iterator is traversing, and crucially, they do **not** throw `ConcurrentModificationException` (unlike a regular `ArrayList`). After the loop, `animals` contains the original 3 elements plus 3 newly added `"newAnimal"` entries, for a total size of `6`.

---

**28. A, D**

`CopyOnWriteArrayList`'s iterators operate over an immutable snapshot of the underlying array taken when the iterator was created, so concurrent modifications by other threads (or even the same thread during iteration) never cause `ConcurrentModificationException` (A is correct). Because the iterator works on a snapshot, modifications made *during* iteration are **not** visible to that iterator (B is wrong — it's the opposite). Every mutating operation (`add`, `set`, `remove`) copies the entire underlying array, which is expensive — this makes `CopyOnWriteArrayList` efficient only when reads vastly outnumber writes, not the reverse (C is wrong; it should say reads are far more frequent than writes). Each modification does indeed create a new copy of the underlying array — this is the defining characteristic that gives the class its name (D is correct). `CopyOnWriteArrayList` does implement `List` (and `SequencedCollection`) (E is wrong).

---

**29. B**

The `ArrayBlockingQueue` is created with a fixed capacity of `2`. After `queue.put(1)` and `queue.put(2)`, the queue is full (`[1, 2]`). `offer(3)` is the non-blocking variant of `add()` — when the queue is full, `offer()` returns `false` immediately rather than blocking or throwing (unlike `add()`, which would throw `IllegalStateException` on a full bounded queue). `poll()` is the non-blocking variant of `remove()` — it removes and returns the head of the queue, `1`, leaving `[2]`. Finally, `queue.size()` returns `1`. Output: `false`, `1`, `1`.

---

**30. C**

`BlockingQueue` provides `put(e)` and `take()` as the **blocking** insertion and removal operations: `put()` blocks if the queue is full (for bounded queues) until space becomes available, and `take()` blocks if the queue is empty until an element becomes available — exactly the producer/consumer behavior described. `offer()` and `poll()` (B) are the **non-blocking** variants that return immediately (`false`/`null` on failure) rather than waiting — they don't satisfy the "blocking" requirement in the question. `add()` and `remove()` (A) are inherited from `Queue` and throw exceptions (`IllegalStateException`/`NoSuchElementException`) on failure rather than blocking. `push()`/`pop()` (D) and `enqueue()`/`dequeue()` (E) are not standard `BlockingQueue` method names.

---

**31. B**

`CyclicBarrier` is **reusable** — once the specified number of parties (here, 2) have called `await()`, the barrier "trips," its optional barrier action (the `Runnable` passed to the constructor) runs, and the barrier automatically resets so it can be used again for the next "generation" of waiting threads. In this code, both submitted tasks each call `barrier.await()` twice. The first time both tasks reach `barrier.await()` (after printing `Working...`), the barrier trips, `"Barrier reached!"` is printed once, and both tasks proceed to print `Continuing...`. Then both tasks call `barrier.await()` a second time; the barrier trips again, printing `"Barrier reached!"` a second time, and both tasks proceed to print `Done`. In total, `"Barrier reached!"` is printed exactly twice. The `CyclicBarrier(int parties, Runnable barrierAction)` constructor is a valid, documented overload (D is wrong).

---

**32. C**

`CountDownLatch` is initialized with a count of `3`. Three worker tasks are submitted to a 3-thread pool, so they can run concurrently; each prints `"Working"` and then calls `latch.countDown()`, decrementing the shared counter. The main thread calls `latch.await()`, which blocks until the count reaches zero — i.e., until all three workers have called `countDown()`. Because the three worker tasks run on separate threads in a pool, the relative order in which their `"Working"` messages are printed is not guaranteed (they could interleave in any order). However, `"All workers done"` is guaranteed to print only after `latch.await()` returns, which can only happen after all three `countDown()` calls have occurred — meaning all three `"Working"` lines are guaranteed to have already been printed. So the three `"Working"` lines appear in some unpredictable order, but always before `"All workers done"`.

---

**33. B**

`CyclicBarrier` is designed to be reused: after the configured number of parties reach the barrier and it "trips," it automatically resets and can be used again for subsequent waves of threads (this is the "cyclic" in its name). `CountDownLatch`, by contrast, is a one-time-use construct: once its internal counter reaches zero via repeated `countDown()` calls, it stays at zero permanently — there is no method to reset it (A has this backwards). `CountDownLatch` does not require the same threads (or even the same number of threads) to call `await()` as call `countDown()` — they are independent; for example, one thread could call `await()` while five different threads each call `countDown()` (C is wrong). `CyclicBarrier` does support an optional `Runnable` barrier action via its constructor, executed each time the barrier trips (D is wrong, this is a real feature). The two classes solve different coordination problems and are not generally interchangeable (E is wrong).

---

**34. B**

This is the classic two-thread, two-lock deadlock pattern. Thread `t1` acquires `lockA` first, then (after a short delay) tries to acquire `lockB`. Thread `t2` acquires `lockB` first, then tries to acquire `lockA`. If both threads run far enough to acquire their first lock before either attempts to acquire its second lock (which the `Thread.sleep(100)` calls make highly likely), then `t1` holds `lockA` and waits forever for `lockB` (held by `t2`), while `t2` holds `lockB` and waits forever for `lockA` (held by `t1`). Neither thread can proceed — this is a deadlock. The program prints `T1 acquired lockA` and `T2 acquired lockB` (in some order, since they run concurrently) and then hangs indefinitely; `T1 acquired lockB` and `T2 acquired lockA` are never printed. `final` fields can absolutely be used as monitor objects (D is wrong), and a deadlock does not throw any exception — it simply hangs (C is wrong).

---

**35. B**

The root cause of this deadlock is that the two threads acquire the two locks in **opposite orders** (`t1`: A then B; `t2`: B then A). If both threads instead always acquire the locks in the **same, consistent order** (e.g., both always acquire `lockA` first and `lockB` second), then whichever thread acquires `lockA` first will be able to proceed to acquire `lockB` without the other thread already holding it (since the other thread would be blocked waiting for `lockA`). This eliminates the circular-wait condition required for deadlock. Removing the `sleep()` calls (A) might make the deadlock less *likely* to be observed in a particular run (since one thread might grab both locks before the other starts), but the race condition and possibility of deadlock would still exist. `volatile` (C) addresses visibility of single variable writes, not mutual exclusion — it's unrelated to this problem. `Thread.yield()` (D) is a scheduling hint and does not prevent deadlock. Changing `Thread` to `Runnable` (E) is a naming/typing change with no effect on locking behavior.

---

**36. C**

`numbers.parallelStream().forEach(results::add)` submits the `add` calls for each of the 1000 elements to be processed concurrently across multiple threads (via the common `ForkJoinPool`), each calling `results.add(...)` on the **same shared, non-thread-safe `ArrayList`**. `ArrayList` is not safe for concurrent modification: simultaneous calls to `add()` from multiple threads can corrupt its internal array and size bookkeeping. The result is genuinely unpredictable — the final size could equal `1000` (if by luck no corrupting interleaving occurs), could be less than `1000` (if some additions are silently lost due to a lost update on the size field), or the corrupted internal state could even cause an exception such as `ArrayIndexOutOfBoundsException` or `ConcurrentModificationException` to be thrown during one of the `add()` calls. This is a textbook example of why mutable shared state must never be modified directly from a parallel stream's lambda. The code compiles fine — `results::add` is a valid method reference for `Consumer<Integer>` (D is wrong).

---

**37. A**

`forEachOrdered()` is specifically designed to process elements of a stream in their defined **encounter order**, even on a parallel stream — this is the whole purpose of the method, as a (potentially performance-costing) alternative to `forEach()`, which makes no ordering guarantee on parallel streams. Since `List.of(1, 2, 3, 4, 5)` is an ordered source, `forEachOrdered()` guarantees the numbers are printed in order: `1 2 3 4 5 `, regardless of how the underlying parallel computation is distributed across threads. `forEachOrdered()` is available on both serial and parallel streams (C is wrong) — calling it on a serial stream behaves identically to `forEach()`.

---

**38. A**

`AtomicInteger.incrementAndGet()` performs its read-modify-write as a single atomic (indivisible) operation using low-level CPU compare-and-swap instructions, which makes it safe to call from multiple threads concurrently without any data being lost — unlike a plain `int` with `count++`, which involves a non-atomic read-then-write sequence that can drop updates under concurrent access. Even though `IntStream.range(0, 1000).parallel().forEach(...)` distributes the 1000 increments across multiple threads, each call to `counter.incrementAndGet()` is guaranteed to see a consistent, up-to-date value and apply its increment without interference, so all 1000 increments are reflected in the final count. The output is always `1000`. `AtomicInteger` instances are effectively-final references and are commonly captured in lambdas without issue (D is wrong); no `ConcurrentModificationException` applies here since no collection is being modified (E is wrong).

---

**39. C**

`CompletableFuture.supplyAsync(() -> 10)` produces a `CompletableFuture<Integer>` that will complete with the value `10`. `.thenApply(n -> n * 2)` chains a transformation that runs once the previous stage completes, taking `10` and producing `20`. `.thenApply(n -> n + 5)` chains another transformation, taking `20` and producing `25`. `thenApply()` (and `thenApplyAsync()`) can be chained any number of times, with each stage's result feeding into the next (E is wrong). `future.get()` blocks until the entire chain completes and returns the final result, `25`.

---

**40. B**

`thenCompose()` is used when the function you provide *itself returns a `CompletableFuture<U>`* — without `thenCompose()`, chaining such a function with `thenApply()` would produce a nested `CompletableFuture<CompletableFuture<U>>`. `thenCompose()` "flattens" this into a single `CompletableFuture<U>`, similar to how `Optional.flatMap()` avoids `Optional<Optional<T>>`. `thenCombine()`, on the other hand, is used to combine the results of **two independent** `CompletableFuture` instances (each computed separately, potentially in parallel) using a `BiFunction` once both have completed. Option A describes the two methods backwards. Both methods are genuinely different in purpose, not just in name (C is wrong). Neither method is restricted to `Runnable`-only or `Callable`-only usage (D is wrong) — both operate on `CompletableFuture<T>` stages regardless of how they were created. Whether execution is synchronous or asynchronous depends on whether you use the `Async` variant (`thenComposeAsync`/`thenCombineAsync`) and which executor is used, not an inherent property of the base method names (E is wrong).

---

**41. B**

`supplyAsync()` runs the supplier asynchronously; here, the supplier unconditionally throws a `RuntimeException("Boom")`, causing that stage of the `CompletableFuture` to complete *exceptionally* rather than with a normal value. `.exceptionally(ex -> -1)` provides a fallback function that runs **only if** the previous stage completed exceptionally, producing a recovery value (`-1`) and allowing the resulting `CompletableFuture` to complete *normally* with that value. As a result, `future.get()` returns `-1` without throwing any exception — `exceptionally()` effectively "catches" the exception and substitutes a value, much like a catch block returning a default. `exceptionally()` accepts a `Function<Throwable, ? extends T>` — it is not restricted to checked exception types (E is wrong).

---

**42. B**

`CompletableFuture.join()` is the unchecked counterpart to `get()`. When the underlying computation completes exceptionally (here, the supplier throws `RuntimeException("Failure")`), `join()` does not throw the original exception directly, nor does it throw a checked exception that must be declared — instead, it throws an unchecked `CompletionException` that **wraps** the original exception (accessible via `getCause()`). This is one of the key differences between `join()` and `get()`: `get()` throws a checked `ExecutionException` (which must be caught or declared), while `join()` throws the unchecked `CompletionException`, making it convenient to use in contexts like stream pipelines (e.g., inside `map()`) where checked exceptions are awkward. `join()` is indeed a real method on `CompletableFuture` (D is wrong), and the program does not hang — it terminates abruptly due to the uncaught `CompletionException` (E is wrong).

---

**43. A**

`ReentrantLock` is, as the name implies, **reentrant**: the same thread that already holds the lock can call `lock()` again without blocking, incrementing an internal hold count. Here, the main thread calls `lock()` once (hold count becomes 1), then calls `lock()` again before releasing it (hold count becomes 2) — this second call does **not** block, since the calling thread already owns the lock. `"Inner block"` is printed, and `unlock()` is called once, decrementing the hold count back to 1 (the lock is still held by this thread, since the count isn't zero yet). Then `"Outer block"` is printed, and the final `unlock()` in the outer `finally` block decrements the hold count to 0, fully releasing the lock. Output: `Inner block` then `Outer block`. No exception is thrown, and the lock count is correctly balanced (one `lock()`/`unlock()` pair per nesting level).

---

**44. B**

The main thread calls `lock.lock()` and acquires the lock, but never releases it before starting thread `t`. Inside `t`, `lock.tryLock()` attempts to acquire the lock **without blocking** — since the lock is currently held by the main thread (a different thread than `t`), `tryLock()` immediately returns `false` rather than waiting. The thread prints `Acquired: false` and, since `acquired` is `false`, does **not** call `unlock()` (correctly, since it never held the lock). After `t.join()`, the main thread calls `lock.unlock()`, releasing the lock it held since the start (this call is balanced — the main thread acquired it once via `lock()` and releases it once via `unlock()`, so no `IllegalMonitorStateException` occurs). `tryLock()` with no arguments is a valid zero-argument method that returns immediately (D is wrong); it does not block waiting for the lock (C is wrong).

---

**45. A, D**

The `volatile` modifier guarantees that any write to the field by one thread is immediately visible to subsequent reads of that field by other threads — it prevents threads from caching stale values of the variable in CPU registers or local caches (A is correct). However, `volatile` does **not** make compound operations like `count++` (which is really a read, then a modify, then a write — three separate steps) atomic; two threads can still interleave these steps and lose an update, exactly as with a non-volatile field (B is wrong). `volatile` can only be applied to fields (instance or static variables), not local variables — local variables are inherently thread-confined since each thread has its own stack, so `volatile` would be meaningless there (C is wrong). `volatile` provides only a visibility guarantee — it does **not** provide any mutual exclusion or locking, meaning multiple threads can still read and write the field "simultaneously" with no blocking (D is correct, and this is precisely why it is not equivalent to wrapping accesses in `synchronized`, making E wrong).

---

**46. B**

Before `start()` is called, a `Thread` object is in the `NEW` state — it has been created but its `run()` method has not yet begun. After `t.start()` and a brief pause (`Thread.sleep(500)`), the thread is inside `Thread.sleep(2000)` — calling `Thread.sleep()` puts the thread into the `TIMED_WAITING` state (a waiting state with a specified wait time), not `RUNNABLE` (which would mean it's eligible to run but not actually blocked on a timed wait) and not `WAITING` (which is for indefinite waits like `Thread.join()` with no timeout, or `Object.wait()` with no timeout). After `t.join()` returns (meaning the thread has finished, having slept for its full 2 seconds), the thread is in the `TERMINATED` state. `RUNNING` and `DEAD` (options A and D) are not valid `Thread.State` enum constants — the six valid states are `NEW`, `RUNNABLE`, `BLOCKED`, `WAITING`, `TIMED_WAITING`, and `TERMINATED`.

---

**47. C**

A thread that is actively trying to enter a `synchronized` block or method but cannot because another thread currently holds the monitor lock is placed in the `BLOCKED` state. `BLOCKED` specifically refers to this "waiting to acquire a monitor lock" scenario. `WAITING` (A) and `TIMED_WAITING` (B) describe a thread that has voluntarily given up the CPU to wait for a condition (e.g., via `Object.wait()`, `Thread.join()`, or `Thread.sleep()`) — not a thread contending for a lock it doesn't yet hold. `RUNNABLE` (D) means the thread is eligible to run (or actually running) — T1 is explicitly *not* eligible to proceed since it cannot enter the synchronized method. `NEW` (E) applies only before `start()` has been called.

---

**48. A**

`Thread.ofVirtual()` returns a builder for creating virtual threads (a real, Java 21 API), and `.start(runnable)` immediately starts the virtual thread and returns the `Thread` instance — so option D is incorrect. `Thread.currentThread().isVirtual()` is a real instance method (added alongside virtual threads) that returns `true` when called from within a virtual thread's task — so it prints `Running: true`, and option E is incorrect. After `t.join()`, the main thread queries `t.getPriority()`. Virtual threads always report a priority of `Thread.NORM_PRIORITY` (which is `5`) — this value is fixed and cannot be changed for virtual threads (calling `setPriority()` on a virtual thread has no effect). Output: `Running: true` then `Priority: 5`.

---

**49. B, D**

`Executors.newVirtualThreadPerTaskExecutor()` creates a brand-new virtual thread for each task submitted to it, rather than reusing a fixed-size pool of threads — this is by design, since virtual threads are cheap enough that pooling provides no benefit (B is correct). Virtual threads shine for I/O-bound or blocking workloads (e.g., waiting on network calls, file I/O, or other blocking operations) because while a virtual thread is blocked, its carrier (platform) thread is freed up to run other virtual threads — allowing huge numbers of concurrent blocking operations with very few actual OS threads (D is correct). Virtual threads are *not* generally beneficial for CPU-intensive work with no blocking — for pure computation, there's no advantage over (and potentially overhead compared to) platform threads, since CPU-bound tasks don't free up their carrier thread (A is wrong, and C's blanket "always faster" claim is also wrong). A virtual thread is **not** permanently bound to one carrier thread — each time it becomes runnable after being blocked, it may be scheduled onto a different available carrier thread (E is wrong).

---

**50. B**

`sell(int count)` is `synchronized`, meaning calls to it from different tasks on the same `booth` instance are mutually exclusive — only one thread can execute the check-and-decrement (`if (ticketsAvailable >= count) { ticketsAvailable -= count; ... }`) at a time. This eliminates the race condition that would otherwise occur if two threads both read `ticketsAvailable` before either updated it. Since `ticketsAvailable` starts at `100` and each successful `sell(2)` call removes exactly `2`, and `100 / 2 = 50` calls are submitted (exactly enough to sell all 100 tickets with no remainder, assuming all 50 calls succeed before `ticketsAvailable` drops below `2`), the synchronized method guarantees that exactly 50 successful sales of 2 tickets each will occur, leaving `ticketsAvailable` at exactly `0`. The `try`-with-resources block ensures `service.close()` is called, which waits for all 50 submitted tasks to finish before `getTicketsAvailable()` is called. The synchronization on `getTicketsAvailable()` itself is not what makes the result `0` — `0` is the correct mathematical result of selling all 100 tickets in groups of 2 (C is a true-but-misleading distractor: even an unsynchronized `getTicketsAvailable()` reading after all tasks finish would correctly observe `0`, because `synchronized` on `sell()` already guarantees both correctness of the final value *and* its visibility once all writer threads have completed and been joined via executor shutdown).

---

**51. B**

`itemsProcessed` is an `AtomicInteger`, and `incrementAndGet()` is an atomic, thread-safe operation — every one of the 500 calls to `processItem()` (across an 8-thread pool) is guaranteed to be correctly counted, so `itemsProcessed.get()` will always be exactly `500`. `unsafeCounter`, however, is a plain `int` field updated via the non-atomic `unsafeCounter++` (a read-modify-write sequence). With 8 threads concurrently calling `processItem()` 500 times total, multiple threads can read the same value of `unsafeCounter` before either writes back its incremented result, causing some increments to be "lost." The final value of `unsafeCounter` is therefore likely to be **less than or equal to** `500`, and is not guaranteed to equal `500` — this is the classic race condition. The code compiles fine: `itemsProcessed` is declared `final`, but `final` only prevents the *reference* from being reassigned, not mutation of the object it points to (`AtomicInteger`'s internal value can still change) (E is wrong).

---

**52. B**

`futures` is a `List<Future<String>>` populated in submission order: index 0 corresponds to `id=1`, index 1 to `id=2`, index 2 to `id=3`. Although the three tasks may execute on different threads in any relative order (or even concurrently, since the pool has 3 threads), the **iteration over `futures`** processes them in the fixed order they were added to the list. `f.get()` for the first `Future` (corresponding to `id=1`) blocks until *that specific task* completes, regardless of whether the other tasks have finished yet. So the first `println` always prints `Report-1` (once that task finishes), the second always prints `Report-2`, and the third always prints `Report-3` — in that fixed order, even though the underlying tasks might *complete* in a different real-time order. This is a key exam trap: parallel execution order is unpredictable, but `Future.get()` calls made in a fixed sequence on a `List<Future<T>>` will always report results in that sequence's order, each blocking as needed. The code compiles fine — `Thread.sleep()`'s checked `InterruptedException` is covered because `buildReport()` itself declares `throws InterruptedException`, and `Callable.call()` is permitted to throw `Exception` (C is wrong); `Future.get()` would only throw `ExecutionException` if the task itself threw, which it does not here (D is wrong).

---

**53. A**

`invokeAll(tasks)` submits all the given `Callable` tasks for execution and blocks until **all** of them have completed, returning a `List<Future<T>>` in the same order as the input collection. Here, the three tasks compute `1+1=2`, `2+2=4`, and `3+3=6`. Since `invokeAll()` waits for all tasks to finish before returning, every `Future` in `results` is guaranteed to be done, so each `f.get()` call returns immediately without blocking and without throwing (none of the tasks throw exceptions). The sum is `2 + 4 + 6 = 12`. Although the three tasks might execute concurrently and complete in any real-time order, `invokeAll()`'s guarantee that *all* tasks are done by the time it returns, combined with the fixed list order, makes the final sum deterministically `12`. `invokeAll()` requires a `Collection<? extends Callable<T>>`, not `Runnable` (D is wrong).

---

**54. A**

`scheduleAtFixedRate(command, initialDelay=0, period=100, TimeUnit.MILLISECONDS)` runs the task immediately (delay 0), then again at approximately 100ms, 200ms, 300ms, 400ms, and so on, measured from the start time — regardless of how long each individual execution of `holder.count++` takes (which here is essentially instantaneous). After waiting 450ms in the main thread, the task should have fired at approximately t=0, 100, 200, 300, and 400ms — that's 5 executions within 450ms (the next one wouldn't fire until t=500ms). So `holder.count` should be at least `4` (allowing for minor timing variance, it's typically `4` or `5`), making `holder.count >= 4` evaluate to `true`. The `count` field is `volatile`, ensuring the main thread sees the latest value written by the scheduler's thread without needing additional synchronization for this simple read. `scheduleAtFixedRate()` accepts a `Runnable`, and `() -> holder.count++` is a valid `Runnable` lambda (its "return value" `int` from the post-increment expression is simply discarded, since `Runnable.run()` returns `void` and the lambda body is treated as a statement) (C is wrong).

---

**55. B**

`methodA()` synchronizes on `lockA`, and `methodB()` synchronizes on `lockB` — these are two completely separate, independent `Object` instances serving as two distinct monitors. Thread `t1` (running `methodA()`) acquires `lockA`, and thread `t2` (running `methodB()`) acquires `lockB` — neither thread needs the lock the other holds, so they do not block each other and can run concurrently. Each method takes about 200ms (due to its internal `Thread.sleep(200)`), so if they run concurrently, the total wall-clock time for both `t1.join()` and `t2.join()` to return is approximately 200ms (the time for the longer-running of the two, run in parallel) rather than 400ms (which would be the case if they were forced to run sequentially due to a shared lock). The code does not require `this` as the synchronization target — any object reference, including `lockA` and `lockB`, can serve as a monitor (D is wrong). Because `t1` and `t2` run concurrently, the interleaving of their print statements (`A-start`, `A-end`, `B-start`, `B-end`) is not fixed — for example, `A-start B-start A-end B-end` is also a plausible interleaving (E is wrong, it's not guaranteed to be in that exact order).

---

**56. B**

`reduce(identity, accumulator)` with `""` as the identity and `(s1, s2) -> s1 + s2` as the accumulator concatenates all four single-character strings (`"a"`, `"b"`, `"c"`, `"d"`) into one combined `String`. Regardless of the order in which the parallel stream processes and combines the elements, the **final result is always a 4-character string** — every one of the four original characters appears in the result exactly once (since string concatenation here is simply combining substrings, and the accumulator/combiner pairing is associative for *length* purposes, even though the *character order* within the result string is not guaranteed on a parallel stream). So `result.length()` is always `4`, but the actual string (e.g., `"abcd"` vs. `"acbd"` vs. some other permutation) can vary depending on how the parallel reduction partitions and recombines the elements. This differs from the "problematic identity" trap shown with `reduce("X", String::concat)` in the chapter, where `"X"` is *not* a true identity value (concatenating `"X"` with anything changes the result), causing the identity to be incorrectly applied multiple times and producing a *longer*, incorrect result. Here, `""` genuinely is the identity for string concatenation (`"" + s == s`), so no extra characters are introduced, and the length is always exactly `4`. The two-argument `reduce(identity, accumulator)` overload is valid without an explicit combiner when the types align (D is wrong).

---

**57. A**

`Worker` extends `Thread` and overrides `run()` to print its `name`. In `main()`, `w1.start()` begins `w1`'s thread, and `w1.join()` immediately blocks the main thread until `w1` has fully completed — guaranteeing `"X"` is printed and `w1` has terminated *before* `w2.start()` is even called. The same pattern repeats for `w2`: `w2.start()` followed by `w2.join()` guarantees `"Y"` is printed and `w2` terminates before `main()` proceeds to `System.out.println("Z")`. Because each thread is started and fully joined before the next one starts, there is no concurrent execution at all here — effectively, this code runs `w1`, then `w2`, then prints `"Z"`, sequentially. The output is therefore deterministically `XYZ`. Each `Worker` instance's `start()` is called exactly once, so no `IllegalThreadStateException` occurs (D is wrong).

---

**58. B**

`acquire()`, `release()`, and `getActive()` are all `synchronized` on the same `pool` instance, so all reads and modifications of `activeConnections` are properly serialized — there's no race condition on the counter itself. Each of the 20 submitted tasks calls `pool.acquire()`; if it succeeds (returns `true`), the task sleeps briefly and then calls `pool.release()`, decrementing `activeConnections` back down. Crucially, the try-with-resources block on `service` calls `close()` when the block exits, which waits for **all 20 submitted tasks to fully complete** before proceeding to `System.out.println(pool.getActive())`. By the time every task has finished, every successful `acquire()` has been matched by a corresponding `release()` (since `release()` is called unconditionally whenever `acquire()` returned `true`, and `release()` is never called when `acquire()` returned `false`). Therefore, `activeConnections` returns to its starting value of `0` by the time `getActive()` is called. The maximum of 5 concurrent connections (C) describes a transient *peak* during execution, not the final value after all tasks complete. No `ConcurrentModificationException` applies — there's no collection being iterated here, just synchronized access to an `int` field (E is wrong).

---

**59. A**

`CompletableFuture.runAsync(Runnable)` runs the given `Runnable` asynchronously and returns a `CompletableFuture<Void>`. `.thenRun(Runnable)` chains another `Runnable` to execute *after* the first stage completes (ignoring any result, since both are `Void`). The chain therefore guarantees that the `Runnable` printing `"Running "` completes before the `Runnable` printing `"Then "` begins — `thenRun()` establishes a happens-before, sequential dependency between the two stages. `future.get()` then blocks the main thread until both chained stages have completed, after which `"Done"` is printed. The output is deterministically `Running Then Done`. `CompletableFuture<Void>` does have a `get()` method (inherited from the `Future<Void>` interface it implements) — calling `get()` on it simply returns `null` upon completion, but it is a perfectly valid call (D is wrong).

---

**60. B**

`invokeAny(tasks)` submits all the given tasks and returns the result of **whichever task completes first without throwing an exception** — as soon as one task succeeds, `invokeAny()` returns its result and (typically) cancels the still-running tasks, since their results are no longer needed. This is in contrast to `invokeAll(tasks)` (A describes `invokeAll()`, not `invokeAny()`), which waits for *all* tasks to complete and returns a `List<Future<T>>` for every one of them. `invokeAny()` is generally used precisely *because* tasks run concurrently/in parallel — racing them against each other to get the fastest successful result (C is wrong). `invokeAny()` returns a value of type `T` (the type parameter of the `Callable<T>` tasks), not `void` (D is wrong), and it does not impose any restriction limiting it to a single task — submitting multiple tasks is its entire purpose (E is wrong).

---
