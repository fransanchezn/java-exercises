package org.fransanchez.usecases.lrucache.op2;

import org.fransanchez.usecases.lrucache.Cache;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

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
    public Optional<V> get(final K key) {
        return Optional.ofNullable(cache.get(key));
    }

    @Override
    public Optional<V> put(final K key, final V value) {
        return Optional.ofNullable(cache.put(key, value));
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
    }
}
