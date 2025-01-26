package org.fransanchez.concurrency.course.multithreading.gInterthreadcoms;

public class SimpleCountDownLatch {
    private int count;

    public SimpleCountDownLatch(final int count) {
        this.count = count;
        if (count < 0) {
            throw new IllegalArgumentException("count cannot be negative");
        }
    }

    /**
     * Causes the current thread to wait until the latch has counted down to zero.
     * If the current count is already zero then this method returns immediately.
     */
    public synchronized void await() throws InterruptedException {
        while (count > 0) {
            wait();
        }
    }

    /**
     *  Decrements the count of the latch, releasing all waiting threads when the count reaches zero.
     *  If the current count already equals zero then nothing happens.
     */
    public synchronized void countDown() {
        if (count > 0) {
            count--;
            if (count == 0) {
                notifyAll();
            }
        }
    }

    /**
     * Returns the current count.
     */
    public int getCount() {
        return count;
    }

    public static void main(final String[] args) throws InterruptedException {
        final var latch = new SimpleCountDownLatch(2);

        for (int i = 0; i < 10; i++) {
            final var t1 = new Thread(() -> {
                try {
                    latch.await();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("Thread woke: " + Thread.currentThread().getName());
            });

            t1.start();
        }

        Thread.sleep(2_000L);

        new Thread(() -> {
            latch.countDown();
            System.out.println("Thread count down: " + Thread.currentThread().getName());
        }).start();

        Thread.sleep(2_000L);

        new Thread(() -> {
            latch.countDown();
            System.out.println("Thread count down: " + Thread.currentThread().getName());
        }).start();

    }
}