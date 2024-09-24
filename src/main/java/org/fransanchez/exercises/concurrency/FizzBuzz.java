package org.fransanchez.exercises.concurrency;

import java.util.function.IntConsumer;

class FizzBuzz {
    private final int n;
    private int nextNumber = 1;

    public FizzBuzz(final int n) {
        this.n = n;
    }

    public void fizz(final Runnable printFizz) throws InterruptedException {
        synchronized (this) {
            while (nextNumber <= n) {
                if (nextNumber % 3 == 0 && nextNumber % 5 != 0) {
                    printFizz.run();
                    nextNumber++;
                    this.notifyAll();
                } else {
                    this.wait();
                }
            }
        }
    }

    public void buzz(final Runnable printBuzz) throws InterruptedException {
        synchronized (this) {
            while(nextNumber <= n) {
                if (nextNumber % 3 != 0 && nextNumber % 5 == 0) {
                    printBuzz.run();
                    nextNumber++;
                    this.notifyAll();
                } else {
                    this.wait();
                }
            }
        }
    }

    public void fizzbuzz(final Runnable printFizzBuzz) throws InterruptedException {
        synchronized (this) {
            while(nextNumber <= n) {
                if (nextNumber % 3 == 0 && nextNumber % 5 == 0) {
                    printFizzBuzz.run();
                    nextNumber++;
                    this.notifyAll();
                } else {
                    this.wait();
                }
            }
        }
    }

    public void number(final IntConsumer printNumber) throws InterruptedException {
        synchronized (this) {
            while(nextNumber <= n) {
                if (nextNumber % 3 != 0 && nextNumber % 5 != 0) {
                    printNumber.accept(nextNumber);
                    nextNumber++;
                    this.notifyAll();
                } else {
                    this.wait();
                }
            }
        }
    }

    public static void main(final String[] args) {
        final var fb = new FizzBuzz(15);

        new Thread(() -> {
            try {
                fb.fizz(() -> System.out.println("fizz"));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();

        new Thread(() -> {
            try {
                fb.buzz(() -> System.out.println("buzz"));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();

        new Thread(() -> {
            try {
                fb.fizzbuzz(() -> System.out.println("fizzbuzz"));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();

        new Thread(() -> {
            try {
                fb.number(System.out::println);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }
}
