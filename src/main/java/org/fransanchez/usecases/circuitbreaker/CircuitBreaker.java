package org.fransanchez.usecases.circuitbreaker;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

public class CircuitBreaker {
    private final CircuitBreakerConfig config;
    private final SlidingWindowStrategy slidingWindowStrategy;

    private CircuitBreakerState state;
    private Instant stateUpdatedTime;

    CircuitBreaker(final CircuitBreakerConfig config) {
        this.config = config;
        this.slidingWindowStrategy = getSlidingWindowStrategy();
        this.state = CircuitBreakerState.CLOSED;
        this.stateUpdatedTime = Instant.now();
    }

    public void onSuccess() {
        slidingWindowStrategy.addEntry(new SlidingWindowStrategy.Entry(UUID.randomUUID().toString(), false));
    }

    public synchronized RuntimeException onFailure(final Throwable t) {
        final var count = slidingWindowStrategy.addEntry(new SlidingWindowStrategy.Entry(UUID.randomUUID().toString(), true));
        if (count >= config.threshold()) {
            openCircuitBreaker();
            return new RuntimeException("Circuit breaker OPEN", t);
        }

        return new RuntimeException(t);
    }

    public synchronized boolean allowRequest() {
        if (CircuitBreakerState.CLOSED == state) {
            return true;
        }

        if (stateUpdatedTime.plus(config.resetTimeInMs(), ChronoUnit.MILLIS).compareTo(Instant.now()) < 0) {
            closeCircuitBreaker();
            return true;
        }

        return false;
    }

    private synchronized void openCircuitBreaker() {
        state = CircuitBreakerState.OPEN;
        stateUpdatedTime = Instant.now();
    }

    private synchronized void closeCircuitBreaker() {
        state = CircuitBreakerState.CLOSED;
        stateUpdatedTime = Instant.now();
    }

    private SlidingWindowStrategy getSlidingWindowStrategy() {
        return switch (config.slidingWindowType()) {
            case COUNT -> new SlidingWindowCount(config.slidingWindowSize());
            case TIME -> throw new IllegalArgumentException("Time not implemented");
        };
    }

    private enum CircuitBreakerState {
        OPEN,
        CLOSED
    }
}
