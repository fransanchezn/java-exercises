package org.fransanchez.concurrency.threads;

public class MyRunnable implements Runnable {
    @Override
    public void run() {
        final var start = System.currentTimeMillis();
        while (System.currentTimeMillis() - start < 5_000L) {
            System.out.println(Thread.currentThread().getName() + " is working");
            threadSleep(1_000);
        }
    }

    private void threadSleep(final long delay) {
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
