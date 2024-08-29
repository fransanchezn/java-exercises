package learning.concurrency.synchronization;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SynchroLocks {
    public int counter;

    private final Object lock = new Object();

    private final Lock reentrantLock = new ReentrantLock();
    private final Semaphore semaphore = new Semaphore(1);

    public synchronized int incrementCounterSync() {
        return ++counter;
    }

    public int incrementCounterSyncBlock() {
        synchronized(this) {
            return ++counter;
        }
    }

    public int incrementCounterSyncBlockLock() {
        synchronized(lock) {
            return ++counter;
        }
    }

    public int incrementCounterLock() {
        reentrantLock.lock();
        try {
            return ++counter;
        } finally {
            reentrantLock.unlock();
        }
    }

    public int incrementCounterSemaphore() throws InterruptedException {
        semaphore.acquire(1);
        try {
            return ++counter;
        } finally {
            semaphore.release(1);
        }
    }


    public static void main(String[] args) throws InterruptedException {
        final var sut = new SynchroLocks();

        for (int i = 0 ; i < 10_000; i++) {
            new Thread(() -> {
                System.out.println(sut.incrementCounterSync());
            }).start();
        }

        System.out.println("Sleeping...");
        Thread.sleep(1000);
        System.out.println("Main: " + sut.counter);
    }
}
