package org.fransanchez.designpattern.structural.decorator.logger;

import java.util.UUID;

// Concrete Decorator
public class UniqueIdLoggerDecorator extends LoggerDecorator {

    @Override
    public void log(String message) {
        super.log(UUID.randomUUID() + " - " + message);
    }
}
