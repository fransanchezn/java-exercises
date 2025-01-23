package org.fransanchez.concurrency.multithreadingcourse.gInterthreadcoms;

import java.util.LinkedList;
import java.util.Optional;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.random.RandomGenerator;

public class QueueModelMain {

    public static void main(String[] args) {
        final var queue = new BlockingQueue();
        final var queueCapacity = 10;

        final var producerLock = new Semaphore(queueCapacity);
        final var consumerLock = new Semaphore(0);

        final var consumer = new Consumer(queue, producerLock, consumerLock);
        final var producer = new Producer(queue, producerLock, consumerLock);

        consumer.start();
        producer.start();
    }

    public static class Consumer extends Thread {
        private final BlockingQueue queue;
        private final Semaphore producerLock;
        private final Semaphore consumerLock;

        public Consumer(final BlockingQueue queue, final Semaphore producerLock, final Semaphore consumerLock) {
            this.queue = queue;
            this.producerLock = producerLock;
            this.consumerLock = consumerLock;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    consumerLock.acquire();
                    final var entry = queue.remove();
                    entry.ifPresent(this::consume);
                } catch (InterruptedException e) {
                    // Do Nothing
                } finally {
                    producerLock.release();
                }
            }
        }

        private void consume(final String entry) {
            System.out.println("[" + Thread.currentThread().getName() + "] consuming: " + entry);
        }
    }

    public static class Producer extends Thread {
        private final BlockingQueue queue;
        private final RandomGenerator random;

        private final Semaphore producerLock;
        private final Semaphore consumerLock;

        public Producer(final BlockingQueue queue, final Semaphore producerLock, final Semaphore consumerLock) {
            this.queue = queue;
            this.producerLock = producerLock;
            this.consumerLock = consumerLock;
            this.random = new Random();
        }

        @Override
        public void run() {
            while (true) {
                sleepMs(2_000);
                try {
                    producerLock.acquire();
                    produce();
                } catch (InterruptedException e) {
                    // Do Nothing
                } finally {
                    consumerLock.release();
                }
            }
        }

        private void produce() {
            final var randomInt = random.nextInt(Integer.MAX_VALUE);
            queue.add(String.valueOf(randomInt));
            System.out.println("[" + Thread.currentThread().getName() + "] producing: " + randomInt);
        }

        private void sleepMs(final long ms) {
            try {
                Thread.sleep(1_000L);
            } catch (InterruptedException e) {
                // Do Nothing
            }
        }
    }

    public static class BlockingQueue {
        private final Queue<String> queue;

        public BlockingQueue() {
            this.queue = new LinkedList<>();
        }

        public synchronized boolean add(final String entry) {
            return this.queue.add(entry);
        }

        public synchronized Optional<String> remove() {
            return queue.isEmpty() ? Optional.empty() : Optional.ofNullable(queue.remove());
        }
    }
}
