package org.fransanchez.designpattern.structural.decorator.logger;

// Decorator
public class LoggerDecorator implements Logger {
    private final Logger decorator;

    public LoggerDecorator(final Logger decorator) {
        this.decorator = decorator;
    }

    @Override
    public void log(final String message) {
        decorator.log(message);
    }
}
