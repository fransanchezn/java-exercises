package org.fransanchez.usecases.logger;

import java.time.Instant;

public class Logger {
    public static Logger instance;
    private LoggerAppender appender;

    private Logger() {
        this.appender = new ConsoleLoggerAppender();
    }

    public static Logger getInstance() {
        synchronized(Logger.class) {
            if (instance == null) {
                System.out.println("Logger new instance");
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
