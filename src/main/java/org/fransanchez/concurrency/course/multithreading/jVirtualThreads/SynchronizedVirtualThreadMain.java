package org.fransanchez.concurrency.course.multithreading.jVirtualThreads;

import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SynchronizedVirtualThreadMain {

    public static void main(String[] args) {
        final var counter = new Counter(0);

        final var start = System.currentTimeMillis();
        try (final var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            for (int i = 0; i < 10_000; i++) {
                executor.submit(() -> {
                    final var taskStart = System.currentTimeMillis();
                    System.out.println("Thread add start: "+ Thread.currentThread());
                    counter.add();
                    final var taskEnd = System.currentTimeMillis();
                    System.out.println("Thread add end: "+ Thread.currentThread() + " Duration: " + (taskEnd - taskStart) + "ms");
                });
            }

            for (int i = 0; i< 10_000; i++) {
                executor.submit(() -> {
                    final var taskStart = System.currentTimeMillis();
                    System.out.println("Thread remove start: "+ Thread.currentThread());
                    counter.remove();
                    final var taskEnd = System.currentTimeMillis();
                    System.out.println("Thread remove end: "+ Thread.currentThread() + " Duration: " + (taskEnd - taskStart) + "ms");
                });
            }
        }

        final var end = System.currentTimeMillis();
        System.out.println("Counter: " + counter.count + " and took " + (end - start) + "ms");
    }

    private static class Counter {
        private int count;
        private final Lock lock;

        public Counter(int count) {
            this.count = count;
            this.lock = new ReentrantLock();
        }

        public void add() {
            lock.lock();
            try {
                count++;
            } finally {
                lock.unlock();
            }
        }

        public void remove() {
            lock.lock();
            try {
                count--;
            } finally {
                lock.unlock();
            }
        }
    }
}
