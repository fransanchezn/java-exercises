package org.fransanchez.usecases.flightprices.infrastructure.caches;

import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Optional;
import java.util.SequencedSet;
import java.util.concurrent.Executors;

public class LastRecentlyUseCacheV2<K, V> {
    private final Map<K, Entry<K,V>> cache;
    private final SequencedSet<Entry<K, V>> linkedList;
    private final int capacity;

    public LastRecentlyUseCacheV2(final int capacity) {
        this.capacity = capacity;
        this.cache = new HashMap<>();
        this.linkedList = new LinkedHashSet<>();
    }

    public synchronized void put(final K key, final V value) {
        if (cache.containsKey(key)) {
            linkedList.remove(cache.get(key));
        } else if (cache.size() >= capacity) {
            removeOldestEntry();
        }

        final var newEntry = new Entry<>(key, value);
        linkedList.addFirst(newEntry);
        cache.put(key, newEntry);
    }

    public synchronized Optional<V> get(final K key) {
        return Optional.ofNullable(cache.get(key))
                .map(node -> {
                    linkedList.remove(node);
                    linkedList.addFirst(node);
                    cache.put(key, node);
                    return node.value;
                });
    }

    private void removeOldestEntry() {
        final var removedNode = linkedList.removeLast();
        cache.remove(removedNode.key);
    }

    private record Entry<K, V>(K key, V value) {}

    public static void main(final String[] args) {
        final var lruCache = new LastRecentlyUseCacheV2<Integer, String>(2);

        final var start = Instant.now();
        try (final var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            for (int i = 0; i < 10_000_000; i++) {
                final var counter = i;
                executor.submit(() -> {
                    lruCache.put(counter, "A");
                });
            }
        }

        final var end = Instant.now();
        System.out.println("LRU Size: " + lruCache.cache.size());
        System.out.println("Duration: " + Duration.between(start, end));
    }
}
