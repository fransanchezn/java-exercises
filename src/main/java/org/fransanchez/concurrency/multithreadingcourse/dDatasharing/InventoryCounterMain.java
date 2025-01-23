package org.fransanchez.concurrency.multithreadingcourse.dDatasharing;

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

        System.out.println("inventoryCounter: " + inventoryCounter.items);
    }

    public static class InventoryCounter {
        private int items;

        public InventoryCounter() {
            this.items = 0;
        }

        public void increase() {
            items++;
        }

        public void decrease() {
            items--;
        }
    }
}
