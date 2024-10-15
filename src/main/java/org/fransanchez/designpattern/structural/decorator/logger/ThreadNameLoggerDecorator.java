package org.fransanchez.designpattern.structural.decorator.logger;

// Concrete Decorator
public class ThreadNameLoggerDecorator extends LoggerDecorator {

    @Override
    public void log(String message) {
        super.log(Thread.currentThread().getName() + " - " + message);
    }
}
