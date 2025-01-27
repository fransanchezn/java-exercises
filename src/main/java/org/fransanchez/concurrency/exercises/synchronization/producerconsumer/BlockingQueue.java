package org.fransanchez.concurrency.exercises.synchronization.producerconsumer;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BlockingQueue {
    private final Queue<Integer> queue;
    private final int capacity;
    private boolean finished;

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
            while (queue.isEmpty() && !finished) { // While need to be used to check the queue size after awakening
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

    public void finished() {
        synchronized (queue) {
            this.finished = true;
            queue.notifyAll();
        }
    }

    public static void main(final String[] args) {
        final var queue = new BlockingQueue(3);
        final var start = System.currentTimeMillis();

        final var producers = new ArrayList<Thread>();
        for (int i = 0; i < 10; i++) {
            final int finalI = i;
            final var producer = new Thread(() -> {
                //final var item = randomInt.nextInt();
                queue.add(finalI);
                System.out.println("Item pushed: " + finalI + " duration: " + (System.currentTimeMillis() - start));
            });

            producer.start();
            producers.add(producer);
        }

        // Set Queue as terminate when all producers finished
        new Thread(() -> {
            for (final Thread producer: producers) {
                try {
                    producer.join();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                queue.finished();
            }
        }).start();

        // Start consumers: 3 Consumers
        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                while (true) {
                    final var item = queue.poll();
                    if (item == null) {
                        break;
                    }
                    System.out.println("Item polled: " + item + " duration: " + (System.currentTimeMillis() - start));
                }
            }).start();
        }
    }
}
