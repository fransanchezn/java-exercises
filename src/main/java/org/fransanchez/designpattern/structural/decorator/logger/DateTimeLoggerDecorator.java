package org.fransanchez.designpattern.structural.decorator.logger;

import java.time.Clock;

// Concrete Decorator
public class DateTimeLoggerDecorator extends LoggerDecorator {
    private final Clock clock;

    public DateTimeLoggerDecorator() {
        this.clock = Clock.systemDefaultZone();
    }

    public DateTimeLoggerDecorator(final Clock clock) {
        this.clock = clock;
    }

    @Override
    public void log(final String message) {
        super.log("[" + clock.instant().toString() + "] " + message);
    }
}
