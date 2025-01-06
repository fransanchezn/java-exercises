package org.fransanchez.usecases.circuitbreaker;

public class CircuitBreakerConfig {
    public enum SlidingWindowType {
        COUNT,
        TIME
    }

    private final SlidingWindowType slidingWindowType;
    private final int slidingWindowSize;
    private final int threshold;
    private final long resetTimeInMs;

    public CircuitBreakerConfig(final SlidingWindowType slidingWindowType, final int slidingWindowSize, final int threshold, final long resetTimeInMs) {
        this.slidingWindowType = slidingWindowType;
        this.slidingWindowSize = slidingWindowSize;
        this.threshold = threshold;
        this.resetTimeInMs = resetTimeInMs;
    }

    public SlidingWindowType slidingWindowType() {
        return slidingWindowType;
    }

    public int slidingWindowSize() {
        return slidingWindowSize;
    }

    public int threshold() {
        return threshold;
    }

    public long resetTimeInMs() {
        return resetTimeInMs;
    }
}
