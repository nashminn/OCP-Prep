# Chapter 13: Concurrency — Practice Questions

---

**1.** What is the output of the following code?

```java
public class Greeter extends Thread {
    public void run() {
        System.out.print("Hello ");
    }

    public static void main(String[] args) {
        Greeter g = new Greeter();
        g.run();
        System.out.print("World");
    }
}
```

A. `Hello World`

B. `World Hello`

C. The output order between `Hello` and `World` cannot be determined.

D. The code does not compile because `run()` cannot be called directly.

E. An `IllegalThreadStateException` is thrown at runtime.

---

**2.** What is the output of the following code?

```java
public class Worker implements Runnable {
    public void run() {
        System.out.print("Working ");
    }

    public static void main(String[] args) {
        Thread t = new Thread(new Worker());
        t.start();
        t.start();
    }
}
```

A. `Working Working `

B. `Working `

C. The code does not compile.

D. The code compiles but throws `IllegalThreadStateException` at runtime.

E. The code compiles but throws `IllegalStateException` at runtime.

---

**3.** Which of the following statements about creating threads are correct? (Choose two.)

A. Calling `start()` on a `Thread` instance causes the JVM to allocate a new call stack and execute `run()` on a separate thread.

B. Calling `run()` on a `Thread` instance executes the task on the same thread that made the call.

C. A class that implements `Runnable` may not also extend another class.

D. Calling `start()` twice on the same `Thread` instance runs the task twice on two different threads.

E. The `Runnable` interface declares a method that returns `Object`.

---

**4.** What is the output of the following code?

```java
public class SleepDemo {
    public static void main(String[] args) {
        System.out.println("A");
        Thread.sleep(1000);
        System.out.println("B");
    }
}
```

A. `A` is printed, then after one second `B` is printed.

B. The code does not compile because `Thread.sleep()` throws a checked exception that is not handled or declared.

C. The code does not compile because `Thread.sleep()` is not a static method.

D. `A` and `B` are printed immediately with no delay.

E. The code compiles but throws `InterruptedException` at runtime.

---

**5.** What is the output of the following code?

```java
public class JoinDemo {
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(() -> {
            for (int i = 0; i < 3; i++) {
                System.out.print(i + " ");
            }
        });
        t.start();
        t.join();
        System.out.println("Done");
    }
}
```

A. `Done` is always printed first, followed by `0 1 2 `.

B. `0 1 2 Done` is always printed in that order.

C. The order between `0 1 2` and `Done` cannot be determined.

D. The code does not compile because `join()` throws a checked exception.

E. The code compiles but `join()` causes an infinite loop.

---

**6.** Which statement about `Thread.interrupt()` is correct?

A. Calling `interrupt()` on a thread that is currently sleeping causes `InterruptedException` to be thrown inside the sleeping thread.

B. Calling `interrupt()` on a thread always immediately terminates that thread.

C. `Thread.interrupted()` is an instance method that does not modify the interrupted status.

D. Calling `interrupt()` on a thread in the `RUNNABLE` state always throws `IllegalThreadStateException`.

E. `interrupt()` can only be called by the thread on itself.

---

**7.** What is the output of the following code?

```java
public class InterruptedFlagDemo {
    public static void main(String[] args) {
        Thread.currentThread().interrupt();
        System.out.println(Thread.interrupted());
        System.out.println(Thread.interrupted());
    }
}
```

A. `true` then `true`

B. `true` then `false`

C. `false` then `false`

D. `false` then `true`

E. The code does not compile.

---

**8.** What is the output of the following code?

```java
import java.util.concurrent.*;

public class OrderDemo {
    public static void main(String[] args) {
        try (ExecutorService service = Executors.newSingleThreadExecutor()) {
            service.execute(() -> System.out.print("A"));
            service.execute(() -> System.out.print("B"));
            service.execute(() -> System.out.print("C"));
        }
    }
}
```

A. `ABC` is always printed in that order.

B. `ABC` is printed, but the order of `A`, `B`, and `C` cannot be guaranteed.

C. The code does not compile because `execute()` requires a `Callable`.

D. Nothing is printed because the executor is closed before the tasks run.

E. An `IllegalStateException` is thrown because tasks are submitted after the executor is created.

---

**9.** Which `Executors` factory method returns an `ExecutorService` that creates a new thread for each submitted task and does not reuse or pool any threads, with each new thread being extremely lightweight?

A. `Executors.newCachedThreadPool()`

B. `Executors.newFixedThreadPool(1)`

C. `Executors.newSingleThreadExecutor()`

D. `Executors.newVirtualThreadPerTaskExecutor()`

E. `Executors.newWorkStealingPool()`

---

**10.** What is the output of the following code?

```java
import java.util.concurrent.*;

public class SubmitDemo {
    public static void main(String[] args) throws Exception {
        try (ExecutorService service = Executors.newSingleThreadExecutor()) {
            Future<?> f1 = service.execute(() -> System.out.println("Task1"));
            System.out.println(f1.get());
        }
    }
}
```

A. `Task1` then `null`

B. `null` then `Task1`

C. The code does not compile because `execute()` returns `void`, not `Future<?>`.

D. The code compiles but throws `ExecutionException` at runtime.

E. `Task1` is printed and then the program hangs.

---

**11.** What is the output of the following code?

```java
import java.util.concurrent.*;

public class CallableDemo {
    public static void main(String[] args) throws Exception {
        try (ExecutorService service = Executors.newSingleThreadExecutor()) {
            Future<Integer> result = service.submit(() -> {
                int x = 10 / 0;
                return x;
            });
            System.out.println(result.get());
        }
    }
}
```

A. The code does not compile because `Callable` cannot throw exceptions.

B. `ArithmeticException` is thrown directly from `result.get()`.

C. `ExecutionException` is thrown from `result.get()`, wrapping the `ArithmeticException`.

D. The program prints `0`.

E. The program hangs indefinitely at `result.get()`.

---

**12.** What is the output of the following code?

```java
import java.util.concurrent.*;

public class TimeoutDemo {
    public static void main(String[] args) {
        try (ExecutorService service = Executors.newSingleThreadExecutor()) {
            Future<String> result = service.submit(() -> {
                Thread.sleep(5000);
                return "done";
            });
            System.out.println(result.get(1, TimeUnit.SECONDS));
        } catch (TimeoutException e) {
            System.out.println("Timed out");
        } catch (Exception e) {
            System.out.println("Other exception");
        }
    }
}
```

A. `done`

B. `Timed out`

C. `Other exception`

D. The code does not compile because `get()` does not accept two arguments.

E. The program hangs for 5 seconds and then prints `done`.

---

**13.** Which statements about `Future` methods are true? (Choose two.)

A. `isDone()` returns `true` only if the task completed successfully.

B. `isDone()` returns `true` if the task completed normally, threw an exception, or was cancelled.

C. `cancel(false)` will never interrupt a task that is currently running.

D. `cancel(true)` always guarantees the task stops immediately.

E. `isCancelled()` returns `true` for any task that has completed, whether cancelled or not.

---

**14.** What is the output of the following code?

```java
import java.util.concurrent.*;

public class ShutdownDemo {
    public static void main(String[] args) throws Exception {
        ExecutorService service = Executors.newSingleThreadExecutor();
        service.shutdown();
        Future<String> result = service.submit(() -> "Hello");
        System.out.println(result.get());
    }
}
```

A. `Hello`

B. `null`

C. `RejectedExecutionException` is thrown when `submit()` is called.

D. The code does not compile because `shutdown()` cannot be called before `submit()`.

E. The program hangs indefinitely.

---

**15.** Which statement correctly distinguishes `shutdown()` and `shutdownNow()`?

A. `shutdown()` immediately stops all running tasks, while `shutdownNow()` waits for them to finish.

B. `shutdown()` allows previously submitted tasks to complete and rejects new tasks, while `shutdownNow()` attempts to stop all actively executing tasks and returns a list of tasks that were never started.

C. `shutdown()` returns a `List<Runnable>` of pending tasks, while `shutdownNow()` returns `void`.

D. Both methods block until all tasks have completed before returning.

E. `shutdownNow()` can be called safely without first calling `shutdown()`, but `shutdown()` requires `shutdownNow()` to be called first.

---

**16.** What is the output of the following code, assuming each task takes about 3 seconds to run?

```java
import java.util.concurrent.*;

public class AwaitDemo {
    static void work() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {}
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(2);
        service.execute(AwaitDemo::work);
        service.execute(AwaitDemo::work);
        service.shutdown();
        boolean finished = service.awaitTermination(1, TimeUnit.SECONDS);
        System.out.println(finished);
    }
}
```

A. `true`

B. `false`

C. The code does not compile because `awaitTermination()` does not exist.

D. The program hangs indefinitely waiting for the tasks.

E. `TimeoutException` is thrown.

---

**17.** What is the output of the following code?

```java
import java.util.concurrent.*;

public class ScheduleDemo {
    public static void main(String[] args) throws Exception {
        ScheduledExecutorService service = Executors.newScheduledThreadPool(1);
        Callable<String> task = () -> "Zoo opens";
        ScheduledFuture<String> future = service.schedule(task, 2, TimeUnit.SECONDS);
        System.out.println(future.get());
        service.shutdown();
    }
}
```

A. `Zoo opens` is printed immediately.

B. `Zoo opens` is printed after waiting approximately 2 seconds.

C. The code does not compile because `schedule()` does not accept a `Callable`.

D. `null` is printed immediately.

E. `TimeoutException` is thrown.

---

**18.** A monitoring task must run repeatedly such that each new run begins exactly 10 seconds after the previous run **finishes**, regardless of how long that run took. Which method should be used?

A. `scheduleAtFixedRate(task, 0, 10, TimeUnit.SECONDS)`

B. `scheduleWithFixedDelay(task, 0, 10, TimeUnit.SECONDS)`

C. `schedule(task, 10, TimeUnit.SECONDS)`

D. `execute(task)` called in a loop with `Thread.sleep(10000)`

E. Both A and B behave identically in this respect.

---

**19.** What is a key risk of using `scheduleAtFixedRate(task, 0, 1, TimeUnit.SECONDS)` if `task` consistently takes 3 seconds to execute?

A. The `ScheduledExecutorService` will throw `IllegalArgumentException` immediately.

B. Tasks will queue up faster than they can complete, potentially exhausting memory over time.

C. The executor automatically skips overlapping executions, so there is no risk.

D. `scheduleAtFixedRate()` automatically converts to `scheduleWithFixedDelay()` behavior in this case.

E. The program will not compile.

---

**20.** What is the output of the following code?

```java
public class Counter {
    private int count = 0;

    public synchronized void increment() {
        count++;
    }

    public int getCount() {
        return count;
    }

    public static void main(String[] args) throws InterruptedException {
        Counter c1 = new Counter();
        Counter c2 = new Counter();

        Runnable task1 = () -> {
            for (int i = 0; i < 1000; i++) c1.increment();
        };
        Runnable task2 = () -> {
            for (int i = 0; i < 1000; i++) c2.increment();
        };

        Thread t1 = new Thread(task1);
        Thread t2 = new Thread(task2);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(c1.getCount() + " " + c2.getCount());
    }
}
```

A. `1000 1000` always.

B. `2000 0` always.

C. The output cannot be determined; the values may be less than 1000 each.

D. The code does not compile because `synchronized` cannot be applied to instance methods.

E. A `ConcurrentModificationException` is thrown at runtime.

---

**21.** What is the output of the following code?

```java
public class StaticLockDemo {
    private static int value = 0;

    public static synchronized void update() {
        value++;
    }

    public synchronized void instanceMethod() {
        System.out.println("instance");
    }

    public static void main(String[] args) {
        System.out.println("Lock for update(): " + (StaticLockDemo.class));
        StaticLockDemo obj = new StaticLockDemo();
        obj.instanceMethod();
    }
}
```

A. A thread calling `update()` and a thread calling `instanceMethod()` on the same object will always block each other.

B. A thread calling `update()` acquires a lock on the `StaticLockDemo` class object, while a thread calling `instanceMethod()` acquires a lock on the instance — these are different locks.

C. The code does not compile because `static` methods cannot be `synchronized`.

D. Static synchronized methods synchronize on the first argument passed to the method.

E. Both methods synchronize on the same lock by default.

---

**22.** Two threads, T1 and T2, each call a `synchronized` instance method named `process()` — but T1 calls it on object `a` and T2 calls it on object `b`, where `a` and `b` are different instances of the same class. What happens?

A. T1 and T2 will block each other because `process()` is synchronized.

B. T1 and T2 can execute `process()` concurrently because each synchronizes on a different object's monitor.

C. A `ConcurrentModificationException` is thrown because two threads access the same method simultaneously.

D. The JVM automatically serializes all calls to any synchronized method across all instances of a class.

E. The code does not compile.

---

**23.** What is the output of the following code?

```java
import java.util.concurrent.atomic.*;

public class AtomicDemo {
    public static void main(String[] args) {
        AtomicInteger value = new AtomicInteger(5);
        System.out.println(value.getAndIncrement());
        System.out.println(value.incrementAndGet());
        System.out.println(value.get());
    }
}
```

A. `5`, `7`, `7`

B. `5`, `7`, `6`

C. `5`, `6`, `6`

D. `6`, `7`, `7`

E. `6`, `6`, `7`

---

**24.** What is the output of the following code?

```java
import java.util.concurrent.atomic.*;

public class CompareAndSetDemo {
    public static void main(String[] args) {
        AtomicInteger value = new AtomicInteger(10);
        boolean result1 = value.compareAndSet(10, 20);
        boolean result2 = value.compareAndSet(10, 30);
        System.out.println(result1 + " " + result2 + " " + value.get());
    }
}
```

A. `true true 30`

B. `true false 20`

C. `false true 30`

D. `true true 20`

E. `false false 10`

---

**25.** Which of the following are valid reasons to prefer `AtomicInteger` over a plain `int` field with a `synchronized` method for a simple counter shared across threads? (Choose two.)

A. `AtomicInteger` operations like `incrementAndGet()` are performed without acquiring a traditional lock, which can reduce contention.

B. `AtomicInteger` automatically makes any sequence of operations on it, no matter how complex, thread-safe.

C. `AtomicInteger.incrementAndGet()` performs the read-modify-write as a single atomic operation.

D. `AtomicInteger` removes the need for the `volatile` keyword on any other shared variables in the class.

E. `AtomicInteger` cannot be used inside a `synchronized` block.

---

**26.** What is the output of the following code?

```java
import java.util.concurrent.*;

public class MapDemo {
    public static void main(String[] args) {
        ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();
        map.put("a", "apple");
        map.put("b", null);
        System.out.println(map.get("a"));
    }
}
```

A. `apple`

B. `null`

C. `NullPointerException` is thrown when `put("b", null)` is called.

D. The code does not compile because `ConcurrentHashMap` does not accept `String` values.

E. `apple` then a separate `NullPointerException` is thrown when printing `map.get("b")`.

---

**27.** What is the output of the following code?

```java
import java.util.concurrent.*;
import java.util.*;

public class CowDemo {
    public static void main(String[] args) {
        List<String> animals = new CopyOnWriteArrayList<>(List.of("lion", "tiger", "bear"));
        for (String animal : animals) {
            System.out.print(animal + " ");
            animals.add("newAnimal");
        }
        System.out.println();
        System.out.println(animals.size());
    }
}
```

A. `lion tiger bear ` then `3`

B. `lion tiger bear ` then `6`

C. The code throws `ConcurrentModificationException`.

D. `lion tiger bear newAnimal newAnimal newAnimal ` then `6`

E. The code does not compile.

---

**28.** Which statements about `CopyOnWriteArrayList` are true? (Choose two.)

A. Iterating over a `CopyOnWriteArrayList` while another thread modifies it will never throw `ConcurrentModificationException`.

B. Modifications made to the list during iteration will be immediately visible to an iterator that was already created.

C. `CopyOnWriteArrayList` is most efficient when writes are far more frequent than reads.

D. Each modification (add, set, remove) creates a new copy of the underlying array.

E. `CopyOnWriteArrayList` does not implement the `List` interface.

---

**29.** What is the output of the following code?

```java
import java.util.concurrent.*;

public class BlockingQueueDemo {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(2);
        queue.put(1);
        queue.put(2);
        System.out.println(queue.offer(3));
        System.out.println(queue.poll());
        System.out.println(queue.size());
    }
}
```

A. `true`, `1`, `2`

B. `false`, `1`, `1`

C. `false`, `1`, `2`

D. `true`, `1`, `1`

E. The code throws `IllegalStateException` because the queue is full.

---

**30.** A producer thread must add elements to a shared queue, blocking if the queue is full, while a consumer thread removes elements, blocking if the queue is empty. Which `BlockingQueue` methods should the producer and consumer use, respectively?

A. `add()` and `remove()`

B. `offer()` and `poll()`

C. `put()` and `take()`

D. `push()` and `pop()`

E. `enqueue()` and `dequeue()`

---

**31.** What is the output of the following code?

```java
import java.util.concurrent.*;

public class CyclicBarrierDemo {
    public static void main(String[] args) throws Exception {
        CyclicBarrier barrier = new CyclicBarrier(2, () -> System.out.println("Barrier reached!"));

        Runnable task = () -> {
            try {
                System.out.println("Working...");
                barrier.await();
                System.out.println("Continuing...");
                barrier.await();
                System.out.println("Done");
            } catch (Exception e) {}
        };

        try (ExecutorService service = Executors.newFixedThreadPool(2)) {
            service.submit(task);
            service.submit(task);
        }
    }
}
```

A. `Barrier reached!` is printed exactly once in total.

B. `Barrier reached!` is printed exactly twice in total.

C. `Barrier reached!` is never printed because `CyclicBarrier` can only be used once.

D. The code does not compile because `CyclicBarrier`'s constructor cannot accept a `Runnable`.

E. The program hangs indefinitely.

---

**32.** What is the output of the following code?

```java
import java.util.concurrent.*;

public class LatchDemo {
    public static void main(String[] args) throws Exception {
        CountDownLatch latch = new CountDownLatch(3);

        Runnable worker = () -> {
            System.out.println("Working");
            latch.countDown();
        };

        try (ExecutorService service = Executors.newFixedThreadPool(3)) {
            service.submit(worker);
            service.submit(worker);
            service.submit(worker);
            latch.await();
            System.out.println("All workers done");
        }
    }
}
```

A. `All workers done` is always printed before any `Working` lines.

B. The three `Working` lines are always printed in submission order, followed by `All workers done`.

C. The three `Working` lines are printed in an unpredictable order, but `All workers done` is always printed last.

D. The program hangs indefinitely because `CountDownLatch` cannot be reused.

E. The code does not compile because `await()` throws a checked exception that is not handled.

---

**33.** Which statement correctly distinguishes `CountDownLatch` from `CyclicBarrier`?

A. `CountDownLatch` can be reset and reused after its count reaches zero, while `CyclicBarrier` cannot.

B. `CyclicBarrier` can be reset and reused for multiple cycles of waiting threads, while `CountDownLatch`'s counter cannot be reset once it reaches zero.

C. `CountDownLatch` requires the same number of threads to call `await()` as `countDown()`.

D. `CyclicBarrier` does not support an action to run when the barrier is tripped.

E. Both classes are interchangeable in all scenarios.

---

**34.** What is the output of the following code?

```java
public class DeadlockDemo {
    private static final Object lockA = new Object();
    private static final Object lockB = new Object();

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            synchronized (lockA) {
                System.out.println("T1 acquired lockA");
                try { Thread.sleep(100); } catch (InterruptedException e) {}
                synchronized (lockB) {
                    System.out.println("T1 acquired lockB");
                }
            }
        });

        Thread t2 = new Thread(() -> {
            synchronized (lockB) {
                System.out.println("T2 acquired lockB");
                try { Thread.sleep(100); } catch (InterruptedException e) {}
                synchronized (lockA) {
                    System.out.println("T2 acquired lockA");
                }
            }
        });

        t1.start();
        t2.start();
    }
}
```

A. The program prints all four lines and terminates normally.

B. The program prints `T1 acquired lockA` and `T2 acquired lockB` (in some order) and then hangs indefinitely — a deadlock.

C. The program throws `IllegalMonitorStateException`.

D. The code does not compile because `lockA` and `lockB` are `final`.

E. The program always prints the four lines in the order T1, T1, T2, T2.

---

**35.** Which change would eliminate the deadlock risk in the code from the previous question while preserving the overall behavior?

A. Remove the `Thread.sleep(100)` calls entirely.

B. Have both threads acquire `lockA` and `lockB` in the same order (e.g., always `lockA` first, then `lockB`).

C. Replace `synchronized` blocks with `volatile` fields.

D. Use `Thread.yield()` before each `synchronized` block.

E. Replace `Thread` with `Runnable` in both task definitions.

---

**36.** What is the output of the following code?

```java
import java.util.*;
import java.util.stream.*;

public class ParallelRaceDemo {
    public static void main(String[] args) {
        List<Integer> numbers = IntStream.rangeClosed(1, 1000)
                .boxed()
                .collect(Collectors.toList());

        List<Integer> results = new ArrayList<>();
        numbers.parallelStream()
               .forEach(results::add);

        System.out.println(results.size());
    }
}
```

A. The output is always exactly `1000`.

B. The output is always less than `1000`.

C. The output is unpredictable — it may be `1000`, less than `1000`, or the program may even throw an exception such as `ArrayIndexOutOfBoundsException`.

D. The code does not compile because `parallelStream()` cannot call `forEach()` with a method reference.

E. The output is always `0` because `ArrayList` rejects concurrent writes silently.

---

**37.** What is the output of the following code?

```java
import java.util.*;
import java.util.stream.*;

public class ForEachOrderedDemo {
    public static void main(String[] args) {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5);
        numbers.parallelStream()
               .forEachOrdered(n -> System.out.print(n + " "));
    }
}
```

A. `1 2 3 4 5 ` is always printed in that order.

B. The numbers are printed but in an unpredictable order.

C. The code does not compile because `forEachOrdered()` is not available on parallel streams.

D. Only the first element is printed.

E. The program hangs indefinitely.

---

**38.** What is the output of the following code?

```java
import java.util.concurrent.atomic.*;
import java.util.stream.*;

public class ParallelAtomicDemo {
    public static void main(String[] args) {
        AtomicInteger counter = new AtomicInteger(0);
        IntStream.range(0, 1000)
                 .parallel()
                 .forEach(i -> counter.incrementAndGet());
        System.out.println(counter.get());
    }
}
```

A. The output is always `1000`.

B. The output is unpredictable, ranging from `1` to `1000`.

C. The output is always `999`.

D. The code does not compile because `AtomicInteger` cannot be used in a lambda.

E. The program throws `ConcurrentModificationException`.

---

**39.** What is the output of the following code?

```java
import java.util.concurrent.*;

public class CompletableFutureDemo {
    public static void main(String[] args) throws Exception {
        CompletableFuture<Integer> future = CompletableFuture
                .supplyAsync(() -> 10)
                .thenApply(n -> n * 2)
                .thenApply(n -> n + 5);

        System.out.println(future.get());
    }
}
```

A. `10`

B. `20`

C. `25`

D. `15`

E. The code does not compile because `thenApply()` cannot be chained.

---

**40.** What is the difference between `thenCompose()` and `thenCombine()` on a `CompletableFuture<T>`?

A. `thenCompose()` is used to combine the results of two independent `CompletableFuture` instances into one, while `thenCombine()` flattens a nested `CompletableFuture` returned by a function.

B. `thenCompose()` flattens a `CompletableFuture<CompletableFuture<U>>` returned by a function into `CompletableFuture<U>`, while `thenCombine()` combines the results of two independent `CompletableFuture` instances using a `BiFunction`.

C. `thenCompose()` and `thenCombine()` are functionally identical and differ only in naming.

D. `thenCompose()` can only be used with `Runnable`, while `thenCombine()` can only be used with `Callable`.

E. `thenCombine()` always runs asynchronously, while `thenCompose()` always runs synchronously.

---

**41.** What is the output of the following code?

```java
import java.util.concurrent.*;

public class ExceptionallyDemo {
    public static void main(String[] args) throws Exception {
        CompletableFuture<Integer> future = CompletableFuture
                .supplyAsync(() -> {
                    if (true) throw new RuntimeException("Boom");
                    return 100;
                })
                .exceptionally(ex -> -1);

        System.out.println(future.get());
    }
}
```

A. `100`

B. `-1`

C. `ExecutionException` is thrown from `get()`.

D. `RuntimeException` is thrown directly from `get()`.

E. The code does not compile because `exceptionally()` requires a checked exception type.

---

**42.** What is the output of the following code?

```java
import java.util.concurrent.*;

public class JoinVsGetDemo {
    public static void main(String[] args) {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            throw new RuntimeException("Failure");
        });

        System.out.println(future.join());
    }
}
```

A. `null`

B. The program throws a `CompletionException` (an unchecked exception) wrapping the `RuntimeException`.

C. The program throws a checked `ExecutionException` that must be declared or caught.

D. The code does not compile because `join()` does not exist on `CompletableFuture`.

E. The program hangs indefinitely.

---

**43.** What is the output of the following code?

```java
import java.util.concurrent.locks.*;

public class ReentrantLockDemo {
    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        lock.lock();
        try {
            lock.lock();
            try {
                System.out.println("Inner block");
            } finally {
                lock.unlock();
            }
            System.out.println("Outer block");
        } finally {
            lock.unlock();
        }
    }
}
```

A. `Inner block` then `Outer block`

B. The program throws `IllegalMonitorStateException` on the second `lock.lock()` call.

C. The program hangs indefinitely on the second `lock.lock()` call.

D. `Inner block` is printed, then the program hangs on `Outer block`.

E. The code does not compile because `lock()` cannot be called twice by the same thread.

---

**44.** What is the output of the following code?

```java
import java.util.concurrent.locks.*;

public class TryLockDemo {
    public static void main(String[] args) throws InterruptedException {
        ReentrantLock lock = new ReentrantLock();
        lock.lock();

        Thread t = new Thread(() -> {
            boolean acquired = lock.tryLock();
            System.out.println("Acquired: " + acquired);
            if (acquired) {
                lock.unlock();
            }
        });

        t.start();
        t.join();
        lock.unlock();
    }
}
```

A. `Acquired: true`

B. `Acquired: false`

C. The program hangs indefinitely because `tryLock()` blocks until the lock is available.

D. The code does not compile because `tryLock()` requires arguments.

E. `IllegalMonitorStateException` is thrown.

---

**45.** Which statements about `volatile` are true? (Choose two.)

A. Declaring a field `volatile` ensures that writes to that field by one thread are immediately visible to other threads reading it.

B. `volatile` makes compound operations like `count++` atomic.

C. `volatile` can be applied to local variables to make them thread-safe.

D. `volatile` does not provide any form of mutual exclusion (locking).

E. `volatile` is equivalent to wrapping every access to the field in a `synchronized` block.

---

**46.** What is the output of the following code?

```java
public class ThreadStateDemo {
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {}
        });
        System.out.println(t.getState());
        t.start();
        Thread.sleep(500);
        System.out.println(t.getState());
        t.join();
        System.out.println(t.getState());
    }
}
```

A. `RUNNABLE`, `RUNNING`, `TERMINATED`

B. `NEW`, `TIMED_WAITING`, `TERMINATED`

C. `NEW`, `RUNNABLE`, `TERMINATED`

D. `NEW`, `WAITING`, `DEAD`

E. `RUNNABLE`, `BLOCKED`, `TERMINATED`

---

**47.** A thread, T1, is blocked attempting to enter a `synchronized` method because thread T2 currently holds the monitor. What state is T1 in?

A. `WAITING`

B. `TIMED_WAITING`

C. `BLOCKED`

D. `RUNNABLE`

E. `NEW`

---

**48.** What is the output of the following code?

```java
public class VirtualThreadDemo {
    public static void main(String[] args) throws InterruptedException {
        Thread t = Thread.ofVirtual().start(() -> {
            System.out.println("Running: " + Thread.currentThread().isVirtual());
        });
        t.join();
        System.out.println("Priority: " + t.getPriority());
    }
}
```

A. `Running: true` then `Priority: 5`

B. `Running: false` then `Priority: 5`

C. `Running: true` then `Priority: 1`

D. The code does not compile because `Thread.ofVirtual()` does not exist.

E. The code does not compile because `isVirtual()` does not exist on `Thread`.

---

**49.** Which statements about virtual threads (Java 21) are true? (Choose two.)

A. Virtual threads are best suited for CPU-intensive tasks that perform heavy computation with no blocking.

B. `Executors.newVirtualThreadPerTaskExecutor()` creates a new virtual thread for every submitted task rather than reusing a fixed pool.

C. Virtual threads always run faster than platform threads for any workload.

D. Virtual threads are particularly well suited for I/O-bound or blocking tasks because they are extremely cheap to create in large numbers.

E. A virtual thread, once started, is permanently bound to a single carrier (platform) thread for its entire lifetime.

---

**50.** Consider the following class used by multiple tasks submitted to an `ExecutorService`:

```java
import java.util.concurrent.*;

public class TicketBooth {
    private int ticketsAvailable = 100;

    public synchronized boolean sell(int count) {
        if (ticketsAvailable >= count) {
            ticketsAvailable -= count;
            return true;
        }
        return false;
    }

    public int getTicketsAvailable() {
        return ticketsAvailable;
    }

    public static void main(String[] args) throws InterruptedException {
        TicketBooth booth = new TicketBooth();
        try (ExecutorService service = Executors.newFixedThreadPool(10)) {
            for (int i = 0; i < 50; i++) {
                service.submit(() -> booth.sell(2));
            }
        }
        System.out.println(booth.getTicketsAvailable());
    }
}
```

What is true about the output?

A. The output is unpredictable and could be any value from `0` to `100`.

B. The output is always exactly `0`, because `sell()` is synchronized and the total tickets sold will always equal exactly 100.

C. The output is always exactly `0`, but only because `getTicketsAvailable()` is also synchronized.

D. The code does not compile because `submit()` requires a `Callable`.

E. A `ConcurrentModificationException` may be thrown.

---

**51.** Consider the following program:

```java
import java.util.concurrent.*;
import java.util.concurrent.atomic.*;

public class InventoryTracker {
    private final AtomicInteger itemsProcessed = new AtomicInteger(0);
    private int unsafeCounter = 0;

    public void processItem() {
        itemsProcessed.incrementAndGet();
        unsafeCounter++;
    }

    public static void main(String[] args) throws InterruptedException {
        InventoryTracker tracker = new InventoryTracker();
        try (ExecutorService service = Executors.newFixedThreadPool(8)) {
            for (int i = 0; i < 500; i++) {
                service.submit(tracker::processItem);
            }
        }
        System.out.println(tracker.itemsProcessed.get() + " " + tracker.unsafeCounter);
    }
}
```

What is true about the output?

A. Both numbers are guaranteed to be `500`.

B. The first number is guaranteed to be `500`, but the second number may be less than `500` due to a race condition on `unsafeCounter`.

C. The first number may be less than `500`, but the second number is guaranteed to be `500`.

D. Both numbers may be less than `500`.

E. The code does not compile because `itemsProcessed` is `final` but its value changes.

---

**52.** Consider the following program:

```java
import java.util.concurrent.*;

public class ReportGenerator {
    public String buildReport(int id) throws InterruptedException {
        Thread.sleep(100);
        return "Report-" + id;
    }

    public static void main(String[] args) throws Exception {
        ReportGenerator generator = new ReportGenerator();
        try (ExecutorService service = Executors.newFixedThreadPool(3)) {
            var futures = new java.util.ArrayList<Future<String>>();
            for (int i = 1; i <= 3; i++) {
                final int id = i;
                futures.add(service.submit(() -> generator.buildReport(id)));
            }
            for (Future<String> f : futures) {
                System.out.println(f.get());
            }
        }
    }
}
```

What is true about the output?

A. The output order is unpredictable because the tasks run on different threads.

B. The output is always `Report-1`, `Report-2`, `Report-3` in that order, because `futures` preserves submission order and `get()` blocks until each respective task completes.

C. The code does not compile because `buildReport()` throws a checked exception inside a lambda.

D. `f.get()` will throw `ExecutionException` for each call because `Thread.sleep()` is involved.

E. The program hangs indefinitely because `Future.get()` cannot be called in a loop.

---

**53.** What is the output of the following code?

```java
import java.util.concurrent.*;

public class InvokeAllDemo {
    public static void main(String[] args) throws Exception {
        try (ExecutorService service = Executors.newFixedThreadPool(3)) {
            var tasks = java.util.List.<Callable<Integer>>of(
                () -> 1 + 1,
                () -> 2 + 2,
                () -> 3 + 3
            );
            var results = service.invokeAll(tasks);
            int sum = 0;
            for (Future<Integer> f : results) {
                sum += f.get();
            }
            System.out.println(sum);
        }
    }
}
```

A. `12`

B. `6`

C. The output cannot be determined ahead of time.

D. The code does not compile because `invokeAll()` requires a `Runnable`.

E. The program hangs indefinitely.

---

**54.** What is the output of the following code?

```java
import java.util.concurrent.*;

public class ScheduledFixedRateDemo {
    public static void main(String[] args) throws Exception {
        ScheduledExecutorService service = Executors.newScheduledThreadPool(1);
        AtomicIntegerHolder holder = new AtomicIntegerHolder();

        ScheduledFuture<?> future = service.scheduleAtFixedRate(
            () -> holder.count++,
            0,
            100,
            TimeUnit.MILLISECONDS
        );

        Thread.sleep(450);
        future.cancel(true);
        service.shutdown();
        System.out.println(holder.count >= 4);
    }

    static class AtomicIntegerHolder {
        volatile int count = 0;
    }
}
```

A. `true`

B. `false`

C. The code does not compile because `scheduleAtFixedRate()` requires a `Callable`.

D. The program hangs indefinitely.

E. `IllegalStateException` is thrown.

---

**55.** Consider the following program that uses two different lock objects:

```java
public class IndependentLocksDemo {
    private final Object lockA = new Object();
    private final Object lockB = new Object();

    public void methodA() {
        synchronized (lockA) {
            System.out.print("A-start ");
            try { Thread.sleep(200); } catch (InterruptedException e) {}
            System.out.print("A-end ");
        }
    }

    public void methodB() {
        synchronized (lockB) {
            System.out.print("B-start ");
            try { Thread.sleep(200); } catch (InterruptedException e) {}
            System.out.print("B-end ");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        IndependentLocksDemo demo = new IndependentLocksDemo();
        Thread t1 = new Thread(demo::methodA);
        Thread t2 = new Thread(demo::methodB);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }
}
```

What is true about this program's execution?

A. `methodA()` and `methodB()` will block each other because they are both synchronized methods of the same class.

B. `methodA()` and `methodB()` synchronize on different monitors (`lockA` and `lockB`), so they can execute concurrently; the total runtime is approximately 200ms, not 400ms.

C. The program will deadlock because `lockA` and `lockB` are both final.

D. The code does not compile because `synchronized` blocks require `this` as the argument.

E. The output is always `A-start A-end B-start B-end `.

---

**56.** What is the output of the following code?

```java
import java.util.*;
import java.util.stream.*;

public class ParallelReduceDemo {
    public static void main(String[] args) {
        String result = List.of("a", "b", "c", "d")
                .parallelStream()
                .reduce("", (s1, s2) -> s1 + s2);
        System.out.println(result.length());
    }
}
```

A. `4`

B. The length is always `4`, but the content of `result` (the order of characters) cannot be determined ahead of time.

C. The output cannot be determined; it might not even be `4` due to the empty-string identity being applied multiple times.

D. The code does not compile because `reduce()` with a `String` identity requires a combiner argument.

E. `0`

---

**57.** What is the output of the following code?

```java
public class ExtendsThreadDemo {
    static class Worker extends Thread {
        private final String name;

        Worker(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            System.out.print(name);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Worker w1 = new Worker("X");
        Worker w2 = new Worker("Y");
        w1.start();
        w1.join();
        w2.start();
        w2.join();
        System.out.println("Z");
    }
}
```

A. The output is always `XYZ`.

B. The output could be `XYZ`, `YXZ`, or `ZXY` depending on scheduling.

C. The code does not compile because `Worker` does not implement `Runnable`.

D. `IllegalThreadStateException` is thrown because `w1` is started twice.

E. The output is always `Z` followed by `XY` in some order.

---

**58.** Examine the following class and its usage across multiple submitted tasks:

```java
import java.util.concurrent.*;

public class ConnectionPool {
    private int activeConnections = 0;
    private final int maxConnections;

    public ConnectionPool(int maxConnections) {
        this.maxConnections = maxConnections;
    }

    public synchronized boolean acquire() {
        if (activeConnections < maxConnections) {
            activeConnections++;
            return true;
        }
        return false;
    }

    public synchronized void release() {
        if (activeConnections > 0) {
            activeConnections--;
        }
    }

    public synchronized int getActive() {
        return activeConnections;
    }

    public static void main(String[] args) throws InterruptedException {
        ConnectionPool pool = new ConnectionPool(5);
        try (ExecutorService service = Executors.newFixedThreadPool(20)) {
            for (int i = 0; i < 20; i++) {
                service.submit(() -> {
                    if (pool.acquire()) {
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {}
                        pool.release();
                    }
                });
            }
        }
        System.out.println(pool.getActive());
    }
}
```

What is the output?

A. The output is unpredictable and could be any value from `0` to `5`.

B. The output is always `0`, because every successful `acquire()` is matched by a `release()`, and `try`-with-resources waits for all submitted tasks to finish.

C. The output is always `5`.

D. The output is always `20`.

E. A `ConcurrentModificationException` may be thrown because multiple threads call synchronized methods on the same object.

---

**59.** What is the output of the following code?

```java
import java.util.concurrent.*;

public class CompletableFutureRunAsyncDemo {
    public static void main(String[] args) throws Exception {
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            System.out.print("Running ");
        }).thenRun(() -> System.out.print("Then "));

        future.get();
        System.out.println("Done");
    }
}
```

A. `Running Then Done`

B. `Done Running Then`

C. The order of `Running` and `Then` is guaranteed, but `Done` may appear before either.

D. The code does not compile because `runAsync()` returns `CompletableFuture<Void>`, which has no `get()` method.

E. `Then Running Done`

---

**60.** Which of the following statements about `ExecutorService.invokeAny()` is correct?

A. `invokeAny()` waits for all submitted tasks to complete and returns a `List` of results.

B. `invokeAny()` returns the result of the first task to complete successfully (without throwing an exception) and may cancel the other tasks.

C. `invokeAny()` always executes tasks sequentially, never in parallel.

D. `invokeAny()` returns `void` and cannot be used to retrieve any result.

E. `invokeAny()` throws `UnsupportedOperationException` if more than one task is provided.

---
