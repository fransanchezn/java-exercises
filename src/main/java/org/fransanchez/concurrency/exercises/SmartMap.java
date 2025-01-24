package org.fransanchez.concurrency.exercises;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SmartMap {
    public Map<String, String> map = new HashMap<>();
    private final Lock lock = new ReentrantLock();

    public String add(final String key, final String value) {
        try {
            if (lock.tryLock(1L, TimeUnit.SECONDS)) {
                try {
                    sleep(900L);
                    System.out.println("Inserting: [key: " + key + "] [value: " + value + "]");
                    map.put(key, value);
                } finally {
                    lock.unlock();
                }
            } else {
                throw new RuntimeException("Lock cannot be acquired");
            }
        } catch (final InterruptedException e) {
            throw new RuntimeException(e);
        }

        return value;
    }

    private void sleep(final long timeMs) {
        try {
            Thread.sleep(timeMs);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        try (final var executor = Executors.newFixedThreadPool(10)) {
            final var sMap = new SmartMap();

            executor.submit(() -> {
                try {
                    sMap.add("EN", "Hi");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

            executor.submit(() -> {
                try {
                    sMap.add("IT", "Ciao");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

            executor.submit(() -> {
                try {
                    sMap.add("ES", "Hola");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

            executor.shutdown();
            executor.awaitTermination(10L, TimeUnit.MINUTES);

            System.out.println(sMap.map);
        }
    }
}
