package org.fransanchez.usecases.logger;

public class LoggerMain {
    public static void main(String[] args) {
        final var logger = Logger.getInstance();
        logger.setAppender(new FileLoggerAppender());

        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                if (Math.random() > 0.4) {
                    logger.log(LogLevel.INFO, "Hello world");
                } else {
                    logger.log(LogLevel.DEBUG, "Hello world");
                }
            }).start();
        }
    }
}
