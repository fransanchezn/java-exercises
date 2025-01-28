package org.fransanchez.concurrency.course.multithreading.ep5.concurrencychallengues;

import java.util.Random;

public class DeadlockSolutionMain {

    public static void main(final String[] args) {
        final var intersection = new Intersection();
        final var trainA = new TrainA(intersection);
        final var trainB = new TrainB(intersection);

        trainA.start();
        trainB.start();
    }

    public static abstract class Train extends Thread {
        protected final Intersection intersection;
        private final Random random;

        public Train(final Intersection intersection) {
            this.intersection = intersection;
            this.random = new Random();
        }

        @Override
        public void run() {
            while (true) {
                long sleepingTime = random.nextInt(5);
                try {
                    Thread.sleep(sleepingTime);
                } catch (InterruptedException e) {
                    // Do nothing
                }

                takeRoad();
            }
        }

        public abstract void takeRoad();
    }

    public static class TrainA extends Train {
        public TrainA(final Intersection intersection) {
            super(intersection);
        }

        @Override
        public void takeRoad() {
            this.intersection.takeRoadA();
        }
    }

    public static class TrainB extends Train {
        public TrainB(final Intersection intersection) {
            super(intersection);
        }

        @Override
        public void takeRoad() {
            this.intersection.takeRoadB();
        }
    }

    public static class Intersection {
        private final Object roadA = new Object();
        private final Object roadB = new Object();

        public void takeRoadA() {
            synchronized (roadA) {
                System.out.println("Road A is locked by thread " + Thread.currentThread().getName());
                synchronized (roadB) {
                    System.out.println("Train passing through road A " + Thread.currentThread().getName());
                    try {
                        Thread.sleep(1_000);
                    } catch (InterruptedException e) {
                        // Do nothing
                    }
                }
            }
        }

        public void takeRoadB() {
            synchronized (roadA) {
                System.out.println("Road A is locked by thread " + Thread.currentThread().getName());
                synchronized (roadB) {
                    System.out.println("Train passing through road B " + Thread.currentThread().getName());
                    try {
                        Thread.sleep(1_000);
                    } catch (InterruptedException e) {
                        // Do nothing
                    }
                }
            }
        }
    }
}
