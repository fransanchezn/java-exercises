package org.fransanchez.usecases.circuitbreaker.op1;

import java.time.temporal.TemporalUnit;

public record CircuitBreakerConfiguration(
        int failureThreshold,
        long slidingWindowTime,
        TemporalUnit slidingWindowTimeUnit,
        long cooldownTime,
        TemporalUnit cooldownTimeUnit
) { }
