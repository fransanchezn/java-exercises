package org.fransanchez.concurrency.course.multithreading.hDatastrcutures;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class InventoryCounterMain {

    public static void main(String[] args) throws InterruptedException {
        final var inventoryCounter = new AtomicInventoryCounter();

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

    public static class AtomicInventoryCounter {
        private final AtomicInteger items;

        public AtomicInventoryCounter() {
            this.items = new AtomicInteger(0);
        }

        public void increase() {
            items.incrementAndGet();
        }

        public void decrease() {
            items.decrementAndGet();
        }

        public int items() {
            return items.get();
        }
    }
}
