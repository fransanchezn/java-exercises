package org.fransanchez.designpattern.behavioral.iterator;

import java.util.List;

public class IteratorMain {
    public static void main(String[] args) {
        final var contacts = List.of(
                new Contact("b", "123"),
                new Contact("c", "123"),
                new Contact("a", "123"),
                new Contact("z", "123"),
                new Contact("h", "123")
                );
        final var orderedIte = new AscendingOrderContactIterator(contacts);

        while (orderedIte.hasNext()) {
            System.out.println(orderedIte.next());
        }

        System.out.println("#######");
        final var randomIte = new RandomOrderContactIterator(contacts);
        while (randomIte.hasNext()) {
            System.out.println(randomIte.next());
        }
    }
}
