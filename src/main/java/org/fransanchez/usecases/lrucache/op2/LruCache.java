package org.fransanchez.usecases.lrucache.op2;

import org.fransanchez.usecases.lrucache.Cache;

import java.time.Duration;
import java.time.Instant;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class LruCache<K, V> implements Cache<K, V> {
    private final Map<K, V> cache;
    private final ReadWriteLock lock;

    public LruCache(final int capacity) {
        this.cache = new LinkedHashMap<>(capacity, 0.75f, true) {
            @Override
            protected boolean removeEldestEntry(final Map.Entry<K, V> eldest) {
                return size() > capacity;
            }
        };
        this.lock = new ReentrantReadWriteLock();
    }

    @Override
    public Optional<V> get(final K key) {
        lock.readLock().lock();
        try {
            return Optional.ofNullable(cache.get(key));
        } finally {
            lock.readLock().unlock();
        }
    }

    @Override
    public Optional<V> put(final K key, final V value) {
        lock.writeLock().lock();
        try {
            return Optional.ofNullable(cache.put(key, value));
        } finally {
            lock.writeLock().unlock();
        }
    }

    public static void main(final String[] args) {
        final var lruCache = new LruCache<Integer, Integer>(2);
        lruCache.put(2, 1);
        System.out.println(lruCache.cache);
        lruCache.put(1, 1);
        System.out.println(lruCache.cache);
        System.out.println(lruCache.get(2));
        System.out.println(lruCache.cache);
        lruCache.put(4, 1);
        System.out.println(lruCache.cache);
        lruCache.get(1);
        System.out.println(lruCache.get(2));

        final var start = Instant.now();
        try (final var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            for (int i = 0; i < 500_000; i++) {
                executor.submit(() -> {
                    final var random = (int) (Math.random() * 1000);
                    if (random > 700) {
                        lruCache.put(random, random);
                    } else {
                        lruCache.get(random);
                    }
                });
            }
        }

        final var end = Instant.now();
        System.out.println("Duration: " + Duration.between(start, end));
    }
}
