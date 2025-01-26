package org.fransanchez.concurrency.course.multithreading.iThreadmodelhighperformanceio;

import java.util.ArrayList;

public class VirtualThreadMain {

    private static final int NUMBER_OF_THREADS = 100;

    public static void main(final String[] args) throws InterruptedException {
        final Runnable runnable = () -> System.out.println("Inside thread: " + Thread.currentThread());

        final Thread platformThread = Thread.ofPlatform().unstarted(runnable);
        final Thread virtualThread = Thread.ofVirtual().unstarted(runnable);

        platformThread.start();
        platformThread.join(); // Main thread waits for completion

        virtualThread.start();
        virtualThread.join();

        System.out.println("#################################");

        final var virtualThreads = new ArrayList<Thread>();
        for (int i = 0; i < NUMBER_OF_THREADS; i++) {
            final Thread vt = Thread.ofVirtual().unstarted(new BlockingTask());
            virtualThreads.add(vt);
        }

        for (final var thread : virtualThreads) {
            thread.start();
        }

        for (final var thread : virtualThreads) {
            thread.join();
        }
    }

    private static class BlockingTask implements Runnable {

        @Override
        public void run() {
            System.out.println("Inside thread: " + Thread.currentThread() + " before blocking call");
            try {
                Thread.sleep(1_000);
            } catch (final InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Inside thread: " + Thread.currentThread() + " after blocking call");
        }
    }
}
