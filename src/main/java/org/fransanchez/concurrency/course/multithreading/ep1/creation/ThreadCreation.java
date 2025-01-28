package org.fransanchez.concurrency.course.multithreading.ep1.creation;

public class ThreadCreation {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            System.out.println("Thread: " + Thread.currentThread().getName() + " with priority " + Thread.currentThread().getPriority());
        });

        thread.setName("MyThread");
        thread.setPriority(Thread.MAX_PRIORITY);
        thread.setUncaughtExceptionHandler((t, e) -> {
            System.out.println("Error occurred in thread: " + t.getName() + " with exception: " + e);
        });


        System.out.println("Main Thread: " + Thread.currentThread().getName());
        thread.start();
        System.out.println("Main Thread: " + Thread.currentThread().getName());
    }
}
