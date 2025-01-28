package org.fransanchez.usecases.client;

import java.util.concurrent.atomic.AtomicInteger;

public class BasicClient implements Client {

    private final AtomicInteger counter;

    public BasicClient() {
        this.counter = new AtomicInteger(0);
    }

    @Override
    public String execute(String input) {
        if (counter.getAndIncrement() > 4) {
            return input;
        } else {
            throw new RuntimeException("Boom!");
        }
    }
}
