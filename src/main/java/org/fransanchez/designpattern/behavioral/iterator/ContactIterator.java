package org.fransanchez.designpattern.behavioral.iterator;

public interface ContactIterator {
    boolean hasNext();
    Contact next();
    void reset();
}
