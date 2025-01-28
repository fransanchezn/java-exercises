package org.fransanchez.usecases.retryer;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Random;
import java.util.random.RandomGenerator;

public class ExponentialBackoffStrategy implements BackoffStrategy {
    private final Duration initialDelay;
    private final Duration maxDelay;
    private final RandomGenerator randomGenerator;

    public ExponentialBackoffStrategy(final Duration initialDelay, final Duration maxDelay) {
        this.initialDelay = initialDelay;
        this.maxDelay = maxDelay;
        this.randomGenerator = new Random();
    }

    @Override
    public Duration getDelay(final int attempts) {
        // Depends on the implementation for Exponential Backup
        final var baseDelay = initialDelay.multipliedBy((long) Math.pow(2, (attempts - 1)));
        final var jitter = randomGenerator.nextInt(100 , 800);
        final var totalDuration = baseDelay.plus(jitter, ChronoUnit.MILLIS);
        return maxDelay.compareTo(totalDuration) > 0 ? totalDuration : maxDelay;
    }
}
