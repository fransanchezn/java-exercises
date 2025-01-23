package org.fransanchez.concurrency.multithreadingcourse.bCoordination;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;

public class JoinThreadMain {
    public static void main(String[] args) throws InterruptedException {
        final List<Long> inputNumbers = Arrays.asList(10000000000L, 0L, 3435L, 2343L, 4523L, 54L, 5566L);

        final List<FactorialTask> tasks = inputNumbers.stream()
                .map(FactorialTask::new)
                .toList();

        for (final var task: tasks) {
            task.start();
        }

        for (final var task: tasks) {
            task.join(3_000L);
        }

        for (final var task: tasks) {
            if (task.isFinished()) {
                System.out.println("Result for : " + task.factorial + "! = " + task.result);
            } else {
                System.out.println("Result for : " + task.factorial + " not finished!");
                task.interrupt();
            }
        }
    }

    public static class FactorialTask extends Thread {
        private final long factorial;
        private BigInteger result;
        private boolean isFinished;

        public FactorialTask(final long factorial) {
            this.factorial = factorial;
            this.result = BigInteger.ZERO;
            this.isFinished = false;
        }

        @Override
        public void run() {
            result = factorial(factorial);
            isFinished = true;
        }

        public BigInteger factorial(final long n) {
            BigInteger result = BigInteger.ONE;

            for (long i = n; i > 0; i--) {
                if (this.isInterrupted()) {
                    return BigInteger.ZERO;
                }
                result = result.multiply(new BigInteger(String.valueOf(i)));
            }

            return result;
        }

        public BigInteger getResult() {
            return result;
        }

        public boolean isFinished() {
            return isFinished;
        }
    }
}
