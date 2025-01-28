package org.fransanchez.usecases.circuitbreaker.op2;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;

public class SlidingWindowCount implements SlidingWindowStrategy {
    private final Queue<Entry> window;
    private final int limit;
    private final AtomicInteger errorCount;

    public SlidingWindowCount(final int size) {
         this.window = new LinkedList<>();
         this.limit = Math.max(0, size - 1);
         this.errorCount = new AtomicInteger(0);
    }

    @Override
    public synchronized int addEntry(final Entry entry) {
        if (window.size() >= limit) {
            final var removedEntry = window.remove();
            if (removedEntry.isError()) {
                errorCount.decrementAndGet();
            }
        }

        window.add(entry);
        if (entry.isError()) {
            errorCount.incrementAndGet();
        }

        return errorCount.get();
    }
}
