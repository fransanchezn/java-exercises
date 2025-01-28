package org.fransanchez.concurrency.course.multithreading.ep5.concurrencychallengues;

import java.util.ArrayList;

public class InventoryCounterMain {

    public static void main(String[] args) throws InterruptedException {
        final var inventoryCounter = new SynchronizedObjectInventoryCounter();

        final var threads = new ArrayList<Thread>();
        threads.add(new Thread(() -> {
            for (int i = 0; i < 10_000; i++) {
                inventoryCounter.increase();
            }
            System.out.println(Thread.currentThread().getName() + " done!");
        }));

        threads.add(new Thread(() -> {
            for (int i = 0; i < 10_000; i++) {
                inventoryCounter.decrease();
            }
            System.out.println(Thread.currentThread().getName() + " done!");
        }));

        for (var thread: threads) {
            thread.start();
        }

        for (var thread: threads) {
            thread.join();
        }

        // Not printing zero since two threads are running concurrently modifying the same shared object
        System.out.println("inventoryCounter: " + inventoryCounter.items());
    }

    public static class SynchronizedInventoryCounter {
        private int items;

        public SynchronizedInventoryCounter() {
            this.items = 0;
        }

        public synchronized void increase() {
            items++;
        }

        public synchronized void decrease() {
            items--;
        }

        public synchronized int items() {
            return items;
        }
    }

    public static class SynchronizedObjectInventoryCounter {
        private int items;
        private final Object lock;

        public SynchronizedObjectInventoryCounter() {
            this.items = 0;
            this.lock = new Object();
        }

        public void increase() {
            synchronized (lock) {
                items++;
            }
        }

        public synchronized void decrease() {
            synchronized (lock) {
                items--;
            }
        }

        public int items() {
            synchronized (lock) {
                return items;
            }
        }
    }
}
