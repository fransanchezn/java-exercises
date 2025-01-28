package org.fransanchez.concurrency.course.multithreading.ep2.coordination;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class ComplexCalculation {
    public BigInteger calculateResult(
            final BigInteger base1, final BigInteger power1,
            final BigInteger base2, final BigInteger power2
    ) throws InterruptedException {
        final List<PowerCalculatingThread> threads = new ArrayList<>();
        threads.add(new PowerCalculatingThread(base1, power1));
        threads.add(new PowerCalculatingThread(base2, power2));

        for (Thread thread : threads) {
            thread.start();
        }

        for (Thread thread : threads) {
            thread.join(5_000L);
        }

        BigInteger result = BigInteger.ZERO;
        for (PowerCalculatingThread thread : threads) {
            if (thread.isAlive()) {
                thread.interrupt();
            }
            result = result.add(thread.getResult());
        }

        return result;
    }

    private static class PowerCalculatingThread extends Thread {
        private BigInteger result = BigInteger.ONE;
        private BigInteger base;
        private BigInteger power;

        public PowerCalculatingThread(final BigInteger base, final BigInteger power) {
            this.base = base;
            this.power = power;
        }

        @Override
        public void run() {
            result = power(base, power);
        }

        private BigInteger power(final BigInteger base, final BigInteger power) {
            BigInteger result = BigInteger.ONE;

            for (BigInteger i = power; i.compareTo(BigInteger.ZERO) > 0; i = i.subtract(BigInteger.ONE)) {
                if (this.isInterrupted()) {
                    return BigInteger.ZERO;
                }
                result = result.multiply(base);
            }

            return result;
        }

        public BigInteger getResult() { return result; }
    }
}
