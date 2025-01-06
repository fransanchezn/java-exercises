package org.fransanchez.usecases.circuitbreaker;

public interface SlidingWindowStrategy {
    int addEntry(final Entry entry);

    record Entry(String key, boolean isError) {}
}
