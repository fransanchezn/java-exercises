package org.fransanchez.usecases.retryer;

public record RetryerConfiguration(
        int maxAttempts,
        BackoffStrategy backoffStrategy
) {}
