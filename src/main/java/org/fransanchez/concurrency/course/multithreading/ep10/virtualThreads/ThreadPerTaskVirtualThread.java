package org.fransanchez.concurrency.course.multithreading.ep10.virtualThreads;

import java.util.concurrent.Executors;

public class ThreadPerTaskVirtualThread {
    private static final int NUMBER_OF_TASK = 10_000;

    public static void main(final String[] args) {
        final var start = System.currentTimeMillis();

        performTasks();

        final var end = System.currentTimeMillis();
        System.out.printf("Tasks too %dms to complete\n", (end - start));
    }

    private static void performTasks() {
        // try-with-resource closes the resource so it waits for all tasks to be completed before leaving this block
        try (final var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            for (int i = 0; i < NUMBER_OF_TASK; i++) {

                executor.submit(() -> {
                    // Threadshing = CPU spend too much time context switching
                    // Forces context switching by 100 individual blocking operations
                    for (int j = 0; j < 100; j++) {
                        blockingIoOperation();
                    }
                });
            }
        }
    }

    // Simulate a long blocking IO
    private static void blockingIoOperation() {
        System.out.println("Executing a blocking task from thread: " + Thread.currentThread());
        try {
            // Not an IO but blocking operation
            Thread.sleep(10);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
