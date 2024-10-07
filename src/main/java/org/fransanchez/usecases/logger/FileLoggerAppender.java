package org.fransanchez.usecases.logger;

public class FileLoggerAppender implements LoggerAppender {
    @Override
    public void append(String content) {
        System.out.println("FILE: " + content);
    }
}
