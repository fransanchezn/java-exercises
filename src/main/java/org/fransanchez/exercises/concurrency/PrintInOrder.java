package org.fransanchez.exercises.concurrency;

import java.util.concurrent.Semaphore;

public class PrintInOrder {
    private final Semaphore lock;

    public PrintInOrder() {
        lock = new Semaphore(0);
    }

    public void first(final Runnable printFirst) throws InterruptedException {
        // printFirst.run() outputs "first". Do not change or remove this line.
        printFirst.run();
        lock.release(1);
    }

    public void second(final Runnable printSecond) throws InterruptedException {
        while(!lock.tryAcquire(1)); // tries to acquire N permits at once
        printSecond.run();
        lock.release(2);
    }

    public void third(final Runnable printThird) throws InterruptedException {
        while(!lock.tryAcquire(2)); // tries to acquire N permits at once
        printThird.run();
    }

    public static void main(String[] args) throws InterruptedException {
        final var order = new PrintInOrder();
        final Runnable thread1 = new Thread(() -> System.out.print("first"));
        final Runnable thread2 = new Thread(() -> System.out.print("second"));
        final Runnable thread3 = new Thread(() -> System.out.print("third"));

        new Thread(() -> {
            try {
                order.third(thread3);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();

        new Thread(() -> {
            try {
                order.second(thread2);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();

        new Thread(() -> {
            try {
                order.first(thread1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }
}
