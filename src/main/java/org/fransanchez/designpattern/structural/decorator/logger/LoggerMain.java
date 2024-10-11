package org.fransanchez.designpattern.structural.decorator.logger;

public class LoggerMain {
    public static void main(String[] args) {
        final var logger = new ThreadNameLoggerDecorator(
                new UniqueIdLoggerDecorator(
                        new DateTimeLoggerDecorator(
                                new ConsoleLogger())
                )
        );

        logger.log("Hello world");
    }
}
