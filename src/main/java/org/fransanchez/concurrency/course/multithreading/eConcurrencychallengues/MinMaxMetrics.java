package org.fransanchez.concurrency.course.multithreading.eConcurrencychallengues;

public class MinMaxMetrics {
    private volatile long minMetric;
    private volatile long maxMetric;

    /**
     * Initializes all member variables
     */
    public MinMaxMetrics() {
        minMetric = Long.MAX_VALUE;
        maxMetric = Long.MIN_VALUE;
    }

    /**
     * Adds a new sample to our metrics.
     */
    public synchronized void addSample(final long newSample) {
        this.minMetric = Math.min(minMetric, newSample);
        this.maxMetric = Math.max(maxMetric, newSample);
    }

    /**
     * Returns the smallest sample we've seen so far.
     */
    public long getMin() {
        return minMetric;
    }

    /**
     * Returns the biggest sample we've seen so far.
     */
    public long getMax() {
        return maxMetric;
    }
}