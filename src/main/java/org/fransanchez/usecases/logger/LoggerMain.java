package org.fransanchez.usecases.logger;

import java.util.concurrent.Executors;

public class LoggerMain {
    public static void main(String[] args) {
        try(final var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            for (int i = 0; i < 100_000; i++) {
                executor.submit(() -> {
                    final var logger = Logger.getInstance();
                });
            }
        }
    }
}
