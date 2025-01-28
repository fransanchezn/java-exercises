package org.fransanchez.usecases.circuitbreaker.op2;

import java.util.function.Function;

public class CircuitBreakerDecorator {
    static <T,R> Function<T, R> decorateFunction(final CircuitBreaker circuitBreaker, final Function<T, R> function) {
        return (T t) -> {
            if (circuitBreaker.allowRequest()) {
                try {
                    System.out.println("CircuitBreakerDecorator process");
                    final var result = function.apply(t);
                    circuitBreaker.onSuccess();
                    return result;
                } catch(final Exception ex) {
                    circuitBreaker.onFailure(ex);
                    throw ex;
                }
            } else {
                throw new RuntimeException("Circuit breaker OPEN!");
            }
        };
    }
}
