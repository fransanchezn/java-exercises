package org.fransanchez.usecases.circuitbreaker.op2;

public record CircuitBreakerConfig(
        SlidingWindowType slidingWindowType,
        int slidingWindowSize,
        int threshold,
        long resetTimeInMs) {
    public enum SlidingWindowType {
        COUNT,
        TIME
    }
}
