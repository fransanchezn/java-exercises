package org.fransanchez.concurrency.course.multithreading.ep6.locking;

import java.util.ArrayList;
import java.util.Random;
import java.util.TreeMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReentrantRWLockMain {
    private static final int HIGHEST_PRICE = 1_000;

    public static void main(String[] args) throws InterruptedException {
        final var db = new InventoryDatabase();

        // Populate DB
        final var random = new Random();
        for (int i = 0; i < 100_000; i++) {
            db.addItem(random.nextInt(HIGHEST_PRICE));
        }

        final var write = new Thread(() -> {
            while(true) {
                db.addItem(random.nextInt(HIGHEST_PRICE));
                db.removeItem(random.nextInt(HIGHEST_PRICE));

                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        write.setDaemon(true);

        final var readers = 7;
        final var readerThreads = new ArrayList<Thread>();
        for (int ri = 0; ri < readers; ri++) {
            final var thread = new Thread(() -> {
                for (int i = 0; i < 100_000; i++) {
                    final var start = random.nextInt(HIGHEST_PRICE);
                    final var end = random.nextInt(start, HIGHEST_PRICE);
                    final var count = db.getNumberOfItemsInPriceRange(start, end);
                    // System.out.println("Count [start: " + start + "] [end: " + end + "] -> " + count);
                }
            });
            thread.setDaemon(true);
            readerThreads.add(thread);
        }

        write.start();

        final var start = System.currentTimeMillis();

        for (var reader: readerThreads) {
            reader.start();
        }

        for (var reader : readerThreads) {
            reader.join();
        }

        final var end = System.currentTimeMillis();

        System.out.println("All reads completed: " + (end - start) + " ms");

    }

    public static class InventoryDatabase {
        private final TreeMap<Integer, Integer> priceToCountMap;
        private final ReentrantReadWriteLock lock;

        public InventoryDatabase() {
            this.priceToCountMap = new TreeMap<>();
            this.lock = new ReentrantReadWriteLock();
        }

        public int getNumberOfItemsInPriceRange(final int start, final int end) {
            lock.readLock().lock();
            try {
                return priceToCountMap
                        .subMap(start, true, end, true)
                        .values()
                        .stream()
                        .reduce(0, Integer::sum);
            } finally {
                lock.readLock().unlock();
            }
        }

        public void addItem(final int price) {
            lock.writeLock().lock();
            try {
                final var count = priceToCountMap.getOrDefault(price, 0) + 1;
                priceToCountMap.put(price, count);
            } finally {
                lock.writeLock().unlock();
            }
        }

        public void removeItem(final int price) {
            lock.writeLock().lock();
            try {
                final var count = priceToCountMap.get(price);
                if (count == null || count == 1) {
                    priceToCountMap.remove(price);
                } else {
                    priceToCountMap.put(price, count - 1);
                }
            } finally {
                lock.writeLock().unlock();
            }
        }
    }
}
