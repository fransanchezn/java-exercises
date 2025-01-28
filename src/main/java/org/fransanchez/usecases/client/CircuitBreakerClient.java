package org.fransanchez.usecases.client;

import org.fransanchez.usecases.circuitbreaker.op1.CircuitBreaker;
import org.fransanchez.usecases.circuitbreaker.op1.CircuitBreakerConfiguration;
import org.fransanchez.usecases.circuitbreaker.op1.DefaultCircuitBreaker;

import java.time.Clock;
import java.time.temporal.ChronoUnit;

public class CircuitBreakerClient implements Client {
    private final Client client;
    private final CircuitBreaker circuitBreaker;

    public CircuitBreakerClient(final Client client) {
        this.client = client;

        final var circuitBreakerConfiguration = new CircuitBreakerConfiguration(2,
                10, ChronoUnit.SECONDS,
                8, ChronoUnit.SECONDS);
        this.circuitBreaker = new DefaultCircuitBreaker(Clock.systemDefaultZone(), circuitBreakerConfiguration);
    }

    @Override
    public String execute(String input) {
        return circuitBreaker.execute(() -> client.execute(input));
    }
}
