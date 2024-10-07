package org.fransanchez.usecases.logger;

public class DatabaseLoggerAppender implements LoggerAppender {
    @Override
    public void append(String content) {
        System.out.println("DATABASE: " + content);
    }
}
