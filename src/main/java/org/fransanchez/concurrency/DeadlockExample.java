package org.fransanchez.concurrency;

import java.util.concurrent.Executors;

public class DeadlockExample {
    public static void main(String[] args) {
        final var executor = Executors.newFixedThreadPool(5);

        final var lock1 = new Object();
        final var lock2 = new Object();

        final Runnable t1 = () -> {
            synchronized(lock1) {
                sleep(100L);
                synchronized(lock2) {
                    System.out.println("T1 running!");
                }
            }
        };

        final Runnable t2 = () -> {
            synchronized(lock2) {
                sleep(100L);
                synchronized(lock1) {
                    System.out.println("T2 running!");
                };
            };
        };

        executor.submit(t1);
        executor.submit(t2);

        System.out.println("End!");

        executor.shutdown();
    }

    public static void sleep(final Long timeMs) {
        try {
            System.out.println(Thread.currentThread().threadId() + " - Sleeping");
            Thread.sleep(timeMs);
            System.out.println(Thread.currentThread().threadId() + " - Finished");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
