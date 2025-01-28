package org.fransanchez.usecases.client;

import org.fransanchez.usecases.retryer.DefaultRetryer;
import org.fransanchez.usecases.retryer.ExponentialBackoffStrategy;
import org.fransanchez.usecases.retryer.Retryer;
import org.fransanchez.usecases.retryer.RetryerConfiguration;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

public class RetryClient implements Client {
    private final Client client;
    private final Retryer retryer;

    public RetryClient(final Client client) {
        this.client = client;

        final var retryerConfiguration = new RetryerConfiguration(
                20,
                new ExponentialBackoffStrategy(
                        Duration.of(200, ChronoUnit.MILLIS),
                        Duration.of(10, ChronoUnit.SECONDS)
                )
        );
        this.retryer = new DefaultRetryer(retryerConfiguration);
    }

    @Override
    public String execute(String input) {
        return retryer.execute(() -> client.execute(input));
    }
}
