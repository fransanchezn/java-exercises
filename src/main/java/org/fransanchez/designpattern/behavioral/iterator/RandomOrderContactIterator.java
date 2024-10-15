package org.fransanchez.designpattern.behavioral.iterator;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.random.RandomGenerator;

public class RandomOrderContactIterator implements ContactIterator {
    private final List<Contact> contacts;
    private Set<Integer> previousIndex;
    private final RandomGenerator randomGenerator;

    public RandomOrderContactIterator(final List<Contact> contacts) {
        this.contacts = contacts;
        this.previousIndex = new HashSet<>();
        this.randomGenerator = new Random();
    }

    @Override
    public boolean hasNext() {
        return previousIndex.size() < contacts.size();
    }

    @Override
    public Contact next() {
        final var index = getNextRandom();
        previousIndex.add(index);
        return contacts.get(index);
    }

    @Override
    public void reset() {
        previousIndex = new HashSet<>();
    }

    private int getNextRandom() {
        if (!hasNext()) {
            throw new IllegalStateException("All indexes already visited");
        }

        int index;
        do {
            index = randomGenerator.nextInt(contacts.size());
        } while (previousIndex.contains(index));

        return index;
    }
}
