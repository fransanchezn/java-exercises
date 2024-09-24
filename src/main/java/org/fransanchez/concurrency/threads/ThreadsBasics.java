package org.fransanchez.concurrency.threads;

public class ThreadsBasics {
    public static void main(String[] args) {
        System.out.println("Main start!");

        final var myThread = new MyThread();
        myThread.start(); // Async method JVM will decide when to start the thread

        final var myRunnableThread = new Thread(new MyRunnable());
        myRunnableThread.start();

        final var myLambdaThread = new Thread(() -> System.out.println("Lambda thread"));
        myLambdaThread.start();

        System.out.println("Main end!");
    }
}
