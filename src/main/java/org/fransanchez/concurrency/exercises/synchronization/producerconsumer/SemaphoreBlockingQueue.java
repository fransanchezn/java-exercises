package org.fransanchez.concurrency.exercises.synchronization.producerconsumer;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

public class SemaphoreBlockingQueue {
    private final Queue<Integer> queue;

    private final Semaphore addCapacity;
    private final Semaphore pollCapacity;

    public SemaphoreBlockingQueue(final int capacity) {
        queue = new LinkedList<>();
        addCapacity = new Semaphore(capacity);
        pollCapacity = new Semaphore(0);
    }

    public boolean add(final int item) {
        try {
            addCapacity.acquire();
            boolean added;
            synchronized (queue) {
                added = queue.add(item);
            }
            pollCapacity.release();
            return added;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public Integer poll() {
        try {
            pollCapacity.acquire();
            Integer item;
            synchronized (queue) {
                item = queue.poll();
            }
            addCapacity.release();
            return item;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(final String[] args) {
        final var queue = new SemaphoreBlockingQueue(3);
        final var count = new AtomicInteger(0);
        final var start = System.currentTimeMillis();

        for (int i = 0; i < 5000; i++) {
            final int finalI = i;
            new Thread(() -> {
                final var item = queue.poll();
                count.addAndGet(item);
                System.out.println("Item polled[" + finalI  + "]: " + count + " duration: " + (System.currentTimeMillis() - start));
            }).start();
        }

        for (int i = 0; i < 5000; i++) {
            final int finalI = i;
            new Thread(() -> {
                queue.add(1);
                System.out.println("Item pushed[" + finalI + "]: " + 1+ " duration: " + (System.currentTimeMillis() - start));
            }).start();
        }
    }
}
