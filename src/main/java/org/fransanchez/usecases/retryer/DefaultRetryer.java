package org.fransanchez.usecases.retryer;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class DefaultRetryer implements Retryer {
    private final RetryerConfiguration configuration;

    public DefaultRetryer(final RetryerConfiguration configuration) {
        this.configuration = configuration;
    }

    @Override
    public <T> T execute(final Callable<T> function) {
        int currentAttempt = 0;
        Exception lastException = null;
        while (currentAttempt <= configuration.maxAttempts()) {
            try {
                System.out.println("Executing...");
                return function.call();
            } catch (final Exception e) {
                System.out.println("Executing gone wrong!");
                currentAttempt++;
                lastException = e;
                if (currentAttempt <= configuration.maxAttempts()) {
                    sleep(currentAttempt);
                }
            }
        }

        throw new RuntimeException(lastException);
    }

    private void sleep(final int attempt) {
        try {
            final var sleepTime = configuration.backoffStrategy().getDelay(attempt);
            System.out.println("Sleeping for: " + sleepTime);
            Thread.sleep(sleepTime);
        } catch (final InterruptedException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static void main(final String[] args) {
        final var retryer = new DefaultRetryer(new RetryerConfiguration(
                20,
                new ExponentialBackoffStrategy(
                        Duration.of(200, ChronoUnit.MILLIS),
                        Duration.of(10, ChronoUnit.SECONDS)
                )
        ));

        try (final var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            for (int i = 0; i < 1; i++) {
                executor.submit(() -> {
                    final var counter = new AtomicInteger();
                    final var result = retryer.execute(() -> {
                        final var attempt = counter.getAndIncrement();
                        if (attempt > 4) {
                            return "Done!";
                        } else {
                            throw new RuntimeException("Boom!");
                        }
                    });

                    System.out.println("Result: " + result);
                });
            }
        }

    }
}
