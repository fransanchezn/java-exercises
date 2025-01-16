package org.fransanchez.concurrency.synchronization;

import java.util.concurrent.Executors;

public class MySingletonObject {
    public static MySingletonObject mySingletonObject;

    private MySingletonObject(final String threadName) {
        System.out.println("Creating singleton object: " + threadName);
    }

    public static MySingletonObject getMySingletonObject() {
        synchronized(MySingletonObject.class) {
            mySingletonObject = mySingletonObject == null
                    ? new MySingletonObject(Thread.currentThread().getName())
                    : mySingletonObject;
        }

        return mySingletonObject;
    }

    public synchronized static MySingletonObject getMySingletonObject2() {
        if (mySingletonObject == null) {
            mySingletonObject = new MySingletonObject(Thread.currentThread().getName());
        }

        return mySingletonObject;
    }

    public static void main(final String[] args) {
        try (final var executor = Executors.newFixedThreadPool(1_000)) {
            for (int i = 0; i < 10_000; i++) {
                executor.submit(() -> {
                    var singleton = MySingletonObject.getMySingletonObject2();
                    System.out.println("MySingleton: " + singleton);
                });
            }
        }
    }
}
