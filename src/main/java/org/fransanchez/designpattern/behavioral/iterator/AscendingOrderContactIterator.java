package org.fransanchez.designpattern.behavioral.iterator;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class AscendingOrderContactIterator implements ContactIterator {
    private final List<Contact> sortedContact;
    private int index;

    public AscendingOrderContactIterator(final List<Contact> contacts) {
        final var list = new ArrayList<>(contacts);
        list.sort(Comparator.comparing(Contact::name));
        this.sortedContact = list;
    }

    @Override
    public boolean hasNext() {
        return index < sortedContact.size();
    }

    @Override
    public Contact next() {
        return sortedContact.get(index++);
    }

    @Override
    public void reset() {
        index = 0;
    }
}
