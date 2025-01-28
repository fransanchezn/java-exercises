package org.fransanchez.concurrency.course.multithreading.ep4.datasharing;

import java.util.ArrayList;

public class InventoryCounterMain {

    public static void main(String[] args) throws InterruptedException {
        final var inventoryCounter = new InventoryCounter();

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
        System.out.println("inventoryCounter: " + inventoryCounter.items);
    }

    public static class InventoryCounter {
        private int items;

        public InventoryCounter() {
            this.items = 0;
        }

        public void increase() {
            // This is NOT an atomic operation
            // 1. Get value
            // 2. Increase value + 1
            // 3. Store value back into the variable
            items++;
        }

        public void decrease() {
            items--;
        }
    }
}
