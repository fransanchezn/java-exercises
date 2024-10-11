package org.fransanchez.designpattern.structural.composite;

import java.util.List;

public class CompositeMain {
    public static void main(final String[] args) {
        final var compositeBox = new CompositeBox(
                List.of(
                        new Book("Book1", 10),
                        new VideoGame("VideoGame1", 10, 2),
                        new CompositeBox(
                                List.of(
                                        new Book("Book2", 10),
                                        new Book("Book3", 10)
                                )
                        )
                )
        );

        System.out.println(compositeBox.price());
    }
}
