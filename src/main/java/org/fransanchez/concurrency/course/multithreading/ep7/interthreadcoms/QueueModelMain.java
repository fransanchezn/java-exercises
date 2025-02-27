package org.fransanchez.concurrency.course.multithreading.ep7.interthreadcoms;

import java.util.ArrayList;
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

        final var numberOfConsumers = 1;
        final var numberOfProducers = 100;

        final var consumers = new ArrayList<Consumer>();
        for (int i = 0; i < numberOfConsumers; i++) {
            final var consumer = new Consumer(queue, producerLock, consumerLock);
            consumers.add(consumer);
        }

        final var producers = new ArrayList<Producer>();
        for (int i = 0; i < numberOfProducers; i++) {
            final var producer = new Producer(queue, producerLock, consumerLock);
            producers.add(producer);
        }

        for (final var con : consumers) {
            con.start();
        }

        for (final var pro : producers) {
            pro.start();
        }
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
                    consume();
                } catch (InterruptedException e) {
                    // Do Nothing
                } finally {
                    producerLock.release();
                }
            }
        }

        private void consume() {
            final var entry = queue.remove();
            entry.ifPresent((e) -> System.out.println("[" + Thread.currentThread().getName() + "] consuming: " + e + " queueSize: " + queue.size()));
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
            System.out.println("[" + Thread.currentThread().getName() + "] producing: " + randomInt + " queueSize: " + queue.size());
        }

        private void sleepMs(final long ms) {
            try {
                Thread.sleep(ms);
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

        public synchronized int size() {
            return this.queue.size();
        }
    }
}
