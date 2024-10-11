package org.fransanchez.designpattern.structural.decorator.datasource;

public class DecoratorMain {

    public static void main(String[] args) {
        final var writer = new EncryptionDecorator(new FileDataSource("test.md"));
        writer.writeData("# Header One");

        final var result = writer.readData();
        System.out.println(result);
    }
}
