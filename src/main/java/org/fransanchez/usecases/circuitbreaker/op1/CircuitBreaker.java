package org.fransanchez.usecases.circuitbreaker.op1;

import java.util.concurrent.Callable;

public interface CircuitBreaker {
    <T> T execute(final Callable<T> function);
    boolean allowRequest();
    void onFailure();
}
