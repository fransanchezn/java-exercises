package org.fransanchez.usecases.circuitbreaker.op1;

import java.time.Clock;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Deque;
import java.util.LinkedList;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.function.Supplier;

public class DefaultCircuitBreaker implements CircuitBreaker {
    private enum State { OPEN, HALF_OPEN, CLOSED }

    private State state;
    private final Clock clock;
    private final CircuitBreakerConfiguration configuration;
    private final Deque<Instant> queue;
    private Instant lastOpenStateTime;

    public DefaultCircuitBreaker(final Clock clock, final CircuitBreakerConfiguration configuration) {
        this.state = State.CLOSED;
        this.clock = clock;
        this.configuration = configuration;
        this.queue = new LinkedList<>();
        this.lastOpenStateTime = null;
    }

    @Override
    public synchronized <T> T execute(final Callable<T> function) {
        if (allowRequest()) {
            try {
                return function.call();
            } catch(final Exception e) {
                onFailure();
                throw new RuntimeException(e);
            }
        } else {
            throw new RuntimeException("Circuit breaker OPEN");
        }
    }

    @Override
    public synchronized boolean allowRequest() {
        if (State.OPEN == this.state) {
            if (isCooldownPeriodOver()) {
                changeState(State.HALF_OPEN);
            }
        }

        return State.OPEN != this.state;
    }

    @Override
    public synchronized void onFailure() {
        queue.add(clock.instant());
        removeOldEntries();

        System.out.println("onFailure: " + queue.size());
        if (State.HALF_OPEN == this.state || queue.size() == configuration.failureThreshold()) {
            changeState(State.OPEN);
        }
    }

    private boolean isCooldownPeriodOver() {
        if (lastOpenStateTime == null) {
            return true;
        }

        final var now = Instant.now();
        return lastOpenStateTime.plus(configuration.cooldownTime(), configuration.cooldownTimeUnit()).compareTo(now) < 0;
    }

    private void changeState(final State state) {
        if (this.state == state) {
            return;
        }

        if (State.OPEN == state) {
            lastOpenStateTime = clock.instant();
        }

        this.state = state;
    }

    private void removeOldEntries() {
        final var now = clock.instant();
        var head = this.queue.peek();
        while(head != null && head.plus(configuration.slidingWindowTime(), configuration.slidingWindowTimeUnit()).compareTo(now) < 0) {
            this.queue.remove();
            head = this.queue.peek();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        final var clock = Clock.systemDefaultZone();
        final var config = new CircuitBreakerConfiguration(2,
                3, ChronoUnit.SECONDS,
                10, ChronoUnit.SECONDS);
        final var circuitBreaker = new DefaultCircuitBreaker(clock, config);

        try (final var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            for (int i = 0; i< 10_000; i++) {
                executor.submit(() -> {
                    try {
                        circuitBreaker.execute(() -> {
                            throw new RuntimeException("boom!");
                        });
                    } catch (final Exception e) {
                        System.out.println("Exception: " + e);
                    }
                });
            }

            Thread.sleep(11_000L);

            for (int i = 0; i< 10_000; i++) {
                executor.submit(() -> {
                    try {
                        circuitBreaker.execute(() -> {
                            System.out.println("Working!");
                            return null;
                        });
                    } catch (final Exception e) {
                        System.out.println("Exception: " + e);
                    }
                });
            }
        }



    }
}
