package org.fransanchez.concurrency.multithreadingcourse.cPerformanceoptimization;

import java.util.concurrent.Executors;

public class ThreadPooling {
    public static void main(String[] args) {
        try (final var executor = Executors.newFixedThreadPool(10)) {
            final Runnable runnable = () -> {
                System.out.println("Runnable task: " + Thread.currentThread().getName());
            };

            for (int i = 0; i < 100; i++) {
                // Execute = fire and forget
                // executor.execute(runnable);

                // submit = returns Future to fetch result
                executor.submit(runnable);
            }
        }

    }
}
