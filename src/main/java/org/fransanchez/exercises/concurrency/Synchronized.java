package org.fransanchez.exercises.concurrency;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Synchronized {

    private int counter = 0;
    private static int staticCounter = 0;
    private final Lock lock = new ReentrantLock();

    public void count() {
        setCounter(getCounter() + 1);
    }

    public synchronized void syncCount() {
        count();
    }

    public void lockCount() {
        try {
            if (lock.tryLock(5_000L, TimeUnit.SECONDS)) {
                count();
            }
        } catch (InterruptedException e) {
            System.out.println("Thread " + Thread.currentThread().threadId() + " exeception.");
        } finally {
            lock.unlock();
        }
    }

    public void syncBlockCount() {
        synchronized(this) {
            count();
        }
    }

    public static void staticSyncBlockCount() {
        synchronized (Synchronized.class) {
            staticCounter +=1;
        }
    }

    public static int getStaticCounter() {
        return staticCounter;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }
}
