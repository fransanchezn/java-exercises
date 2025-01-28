package org.fransanchez.concurrency.course.multithreading.ep9.threadmodelhighperformanceio;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ThreadNonBlockingIo {
    private static final int NUMBER_OF_TASK = 10_000;

    public static void main(final String[] args) {
        try (final var executor = Executors.newFixedThreadPool(1_000)) {

            final var start = System.currentTimeMillis();

            final var futures = performTasksAsync(executor);

            CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]))
                    .thenRun(() -> {
                        final var end = System.currentTimeMillis();
                        System.out.printf("Tasks too %dms to complete\n", (end - start));
                    })
                    .join(); // This will block the main thread until all tasks complete
        }
    }

    public static List<CompletableFuture<Void>> performTasksAsync(final Executor executor) {
        final var futures = new ArrayList<CompletableFuture<Void>>();

        for (int i = 0; i < NUMBER_OF_TASK; i++) {
            final var future = CompletableFuture.runAsync(() -> {
                for (int j = 0; j < 100; j++) {
                    blockingIoOperation();
                }
            }, executor);

            futures.add(future);
        }

        return futures;
    }

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
