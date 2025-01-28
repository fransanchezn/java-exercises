package org.fransanchez.concurrency.course.multithreading.ep5.concurrencychallengues;

import java.util.Random;

public class AtomicOperationMain {

    public static void main(String[] args) {
        final var metric = new Metric();
        final var metricsPrinter = new MetricsPrinter(metric);
        final var businessLogic1 = new BusinessLogic(metric);
        final var businessLogic2 = new BusinessLogic(metric);

        businessLogic1.start();
        businessLogic2.start();
        metricsPrinter.start();
    }

    public static class MetricsPrinter extends Thread {
        private final Metric metric;

        public MetricsPrinter(final Metric metric) {
            this.metric = metric;
        }

        @Override
        public void run() {
            while (true) {
                System.out.println("Metrics: " + metric.average());
            }
        }
    }

    public static class BusinessLogic extends Thread {
        private final Metric metric;
        private final Random random;

        public BusinessLogic(final Metric metric) {
            this.metric = metric;
            random = new Random();
        }

        @Override
        public void run() {
            while (true) {
                long start = System.currentTimeMillis();
                try {
                    Thread.sleep(random.nextInt(10));
                } catch(InterruptedException e) {
                    // Do nothing.
                }

                long end = System.currentTimeMillis();
                metric.addSample(end - start);
            }
        }
    }

    public static class Metric {
        private long count;
        // volatile = Makes read/write atomic to this variable
        private volatile double average;

        public Metric() {
            count = 0;
            average = 0.0d;
        }

        public synchronized void addSample(final long sample) {
            final double currentSum = average * count;
            count++;
            average = (currentSum + sample) / count;
        }


        // No need for synchronized since the average is volatile
        public double average() {
            return average;
        }
    }
}
