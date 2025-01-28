package org.fransanchez.usecases.circuitbreaker.op2;

public interface SlidingWindowStrategy {
    int addEntry(final Entry entry);

    record Entry(String key, boolean isError) {}
}
