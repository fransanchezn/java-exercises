package org.fransanchez.usecases.lrucache.op2;

import org.fransanchez.usecases.lrucache.Cache;

import java.time.Duration;
import java.time.Instant;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class LruCache<K, V> implements Cache<K, V> {
    private final Map<K, V> cache;

    public LruCache(final int capacity) {
        this.cache = new LinkedHashMap<>(capacity, 0.75f, true) {
            @Override
            protected boolean removeEldestEntry(final Map.Entry<K, V> eldest) {
                return size() > capacity;
            }
        };
    }

    @Override
    public synchronized Optional<V> get(final K key) {
        return Optional.ofNullable(cache.get(key));
    }

    @Override
    public synchronized Optional<V> put(final K key, final V value) {
        return Optional.ofNullable(cache.put(key, value));
    }

    public static void main(final String[] args) {
        final var lruCache = new LruCache<Integer, Integer>(2);
        System.out.println(lruCache.get(2));
        lruCache.put(2, 1);
        lruCache.put(1, 1);
        lruCache.put(2, 3);
        lruCache.put(4, 1);
        lruCache.get(1);
        System.out.println(lruCache.get(2));

        final var start = Instant.now();
        final var writes = new AtomicInteger(0);
        final var reads = new AtomicInteger(0);
        try (final var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            for (int i = 0; i < 500_000; i++) {
                executor.submit(() -> {
                    final var random = (int) (Math.random() * 1000);
                    if (random > 900) {
                        lruCache.put(random, random);
                        writes.getAndIncrement();
                    } else {
                        lruCache.get(random);
                        reads.getAndIncrement();
                    }
                });
            }
        }

        final var end = Instant.now();
        System.out.println("Duration: " + Duration.between(start, end) + " [reads:" + reads.get() + "] [writes:" + writes.get() + "]");
    }
}
