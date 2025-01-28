package org.fransanchez.usecases.retryer;

import java.time.Duration;

public interface BackoffStrategy {
    Duration getDelay(int attempts);
}
