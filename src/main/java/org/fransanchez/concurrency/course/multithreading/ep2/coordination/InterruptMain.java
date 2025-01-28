package org.fransanchez.concurrency.course.multithreading.ep2.coordination;

import java.math.BigInteger;

public class InterruptMain {

    public static void main(String[] args) {
        final var thread = new Thread(new LongComputationTask(new BigInteger("2"), new BigInteger("100000000000000000")));
        thread.start();

        //thread.setDaemon(true);
        thread.interrupt();
    }

    public static class BlockingTask implements Runnable {
        @Override
        public void run() {
            try {
                Thread.sleep(100_000);
            } catch (InterruptedException e) {
                System.out.println("Thread is interrupted");
            }
        }
    }

    public static class LongComputationTask implements Runnable {
        private final BigInteger base;
        private final BigInteger power;

        public LongComputationTask(final BigInteger base, final BigInteger power) {
            this.base = base;
            this.power = power;
        }

        @Override
        public void run() {
            final var result = pow(base, power);
            System.out.println(base + "^" + power + "=" + result);
        }

        private BigInteger pow(final BigInteger base, final BigInteger power) {
            var result = BigInteger.ONE;

            for (var i = BigInteger.ZERO; i.compareTo(power) != 0; i = i.add(BigInteger.ONE)) {
                // Safety to allow this thread to be interrupted.
                // Not required in thread.setDaemon(true)
                if (Thread.currentThread().isInterrupted()) {
                    return BigInteger.ZERO;
                }
                result = result.multiply(base);
            }

            return result;
        }
    }
}
