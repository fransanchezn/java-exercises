package org.fransanchez.designpattern.structural.decorator.logger;

public class LoggerMain {
    public static void main(String[] args) {
        final var logger = new ThreadNameLoggerDecorator()
                .withDecorator(new UniqueIdLoggerDecorator()
                        .withDecorator(new DateTimeLoggerDecorator()
                                .withDecorator(new ConsoleLogger())));

        logger.log("Hello world");
    }
}
