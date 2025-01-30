package org.fransanchez.usecases.logger;

import java.time.Instant;

public class Logger {
    public static Logger instance;
    private LoggerAppender appender;

    private Logger() {
        System.out.println("Logger new instance");
        this.appender = new ConsoleLoggerAppender();
    }

    public static Logger getInstance() {
        // Required sync if this is initialized in multiple threads.
        synchronized(Logger.class) {
            if (instance == null) {
                instance = new Logger();
            }
        }

        return instance;
    }

    public void setAppender(final LoggerAppender appender) {
        this.appender = appender;
    }

    public void log(final LogLevel level, final String content) {
        final var logLine = String.format("[%S] - %s - %s", level.name(), Instant.now(), content);
        appender.append(logLine);
    }
}
