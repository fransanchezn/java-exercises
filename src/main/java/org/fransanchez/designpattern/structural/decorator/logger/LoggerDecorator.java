package org.fransanchez.designpattern.structural.decorator.logger;

// Decorator
public class LoggerDecorator implements Logger {
    private Logger decorator;

    public LoggerDecorator withDecorator(final Logger decorator) {
        this.decorator = decorator;
        return this;
    }

    @Override
    public void log(final String message) {
        decorator.log(message);
    }
}
