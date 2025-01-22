package org.fransanchez.concurrency.multithreadingcourse.aCreation;

public class MyThread extends Thread {
    @Override
    public void run() {
        System.out.println("Running thread");
    }

    public static void main(String[] args) {
        final var thread = new MyThread();
        thread.start();
    }
}
