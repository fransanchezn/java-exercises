package org.fransanchez.usecases.logger;

public class ConsoleLoggerAppender implements LoggerAppender {
    @Override
    public void append(String content) {
        System.out.println("CONSOLE: " + content);
    }
}
