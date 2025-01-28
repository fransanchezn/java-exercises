package org.fransanchez.usecases.circuitbreaker.op2;

import java.net.URI;
import java.util.function.Function;

public class Main {
    public static void main(final String[] args) throws InterruptedException {
        final Function<URI, Integer> client = (uri) ->  {
            System.out.println("Client process: " + uri);
            throw new RuntimeException("Boom!");
        };

        final var circuitBreakerConfig = new CircuitBreakerConfig(CircuitBreakerConfig.SlidingWindowType.COUNT, 5, 2, 5_000L);
        final var circuitBreaker = new CircuitBreaker(circuitBreakerConfig);

        final var cbFunction = CircuitBreakerDecorator.decorateFunction(circuitBreaker, client);

        final Function<URI, Integer> process = (u) -> {
            try {
                return cbFunction.apply(u);
            } catch (Exception e) {
                e.printStackTrace();
                return 0;
            }
        };

        var uri = URI.create("http://asd123$-1.com/");

        uri = URI.create("http://asd123$-2.com/");
        process.apply(uri);

        uri = URI.create("http://asd123$-3.com/");
        process.apply(uri);

        uri = URI.create("http://asd123$-4.com/");
        process.apply(uri);

        Thread.sleep(5_000L);
        uri = URI.create("http://asd123$-5.com/");
        process.apply(uri);

        uri = URI.create("http://asd123$-6.com/");
        process.apply(uri);
    }
}
