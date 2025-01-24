package org.fransanchez.concurrency.exercises.synchronization.producerconsumer;

import java.util.LinkedList;
import java.util.Queue;

public class BlockingQueue {
    private final Queue<Integer> queue;
    private final int capacity;

    public BlockingQueue(final int capacity) {
        queue = new LinkedList<>();
        this.capacity = capacity;
    }

    public boolean add(final int item) {
        synchronized (queue) {
            while (queue.size() == capacity) { // While need to be used to check the queue size after awakening
                try {
                    queue.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            var added = queue.add(item);
            queue.notifyAll();
            return added;
        }
    }

    public Integer poll() {
        synchronized (queue) {
            while (queue.isEmpty()) { // While need to be used to check the queue size after awakening
                try {
                    queue.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

            final var item = queue.poll();
            queue.notifyAll();
            return item;
        }
    }

    public static void main(final String[] args) {
        final var queue = new BlockingQueue(3);
        final var start = System.currentTimeMillis();
        for (int i = 0; i < 5_000; i++) {
            final int finalI = i;
            new Thread(() -> {
                //final var item = randomInt.nextInt();
                queue.add(finalI);
                System.out.println("Item pushed: " + finalI + " duration: " + (System.currentTimeMillis() - start));
            }).start();
        }

        for (int i = 0; i < 5_000; i++) {
            new Thread(() -> {
                final var item = queue.poll();
                System.out.println("Item polled: " + item + " duration: " + (System.currentTimeMillis() - start));
            }).start();
        }
    }
}
