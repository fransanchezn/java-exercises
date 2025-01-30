package org.fransanchez.usecases.flightprices.infrastructure.caches;

import java.time.Duration;
import java.time.Instant;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.Executors;

public class LastRecentlyUseCacheV3<K, V> {
    private final Map<K, V> cache;

    public LastRecentlyUseCacheV3(final int capacity) {
        this.cache = new LinkedHashMap<>(capacity, 0.75f, true) {
            @Override
            protected boolean removeEldestEntry(final Map.Entry<K, V> eldest) {
                return this.size() > capacity;
            }
        };
    }

    public synchronized void put(final K key, final V value) {
        cache.put(key, value);
    }

    public synchronized Optional<V> get(final K key) {
        return Optional.ofNullable(cache.get(key));
    }

    public static void main(final String[] args) {
        final var lruCache = new LastRecentlyUseCacheV3<Integer, String>(2);

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
