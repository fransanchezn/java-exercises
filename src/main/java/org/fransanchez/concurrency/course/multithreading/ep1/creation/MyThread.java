package org.fransanchez.concurrency.course.multithreading.ep1.creation;

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
