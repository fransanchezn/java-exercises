package org.fransanchez.designpattern.structural.decorator.logger;

// Concrete Component
public class ConsoleLogger implements Logger {

    public ConsoleLogger() {}

    @Override
    public void log(final String message) {
        System.out.println(message);
    }
}
