package org.fransanchez.usecases.lrucache.op1;

import org.fransanchez.usecases.lrucache.Cache;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class LruCache<K, V> implements Cache<K, V> {
    private final Map<K, DoublyLinkedList.Node<CacheEntry<K,V>>> cache;
    private final DoublyLinkedList<CacheEntry<K,V>> list;
    private final int capacity;

    public LruCache(final int capacity) {
        cache = new HashMap<>();
        list = new DoublyLinkedList<>();
        this.capacity = capacity;
    }

    @Override
    public synchronized Optional<V> get(final K key) {
        final var node = cache.get(key);
        if (node != null) {
            list.remove(node);
            list.addToHead(new CacheEntry<>(node.value.key, node.value.value));

            return Optional.of(node.value.value);
        }

        return Optional.empty();
    }

    @Override
    public synchronized Optional<V> put(final K key, final V value) {
        if (cache.containsKey(key)) {
            list.remove(cache.get(key));
        } else if (cache.size() >= capacity) {
            final var node = list.removeFromTail();
            cache.remove(node.value.key);
        }

        final var node = list.addToHead(new CacheEntry<>(key, value));
        return Optional.ofNullable(cache.put(key, node))
                .map(e -> e.value.value);
    }

    private record CacheEntry<K, V>(K key, V value) {}

    public static void main(final String[] args) {
        final var lruCache = new LruCache<Integer, Integer>(2);
        System.out.println(lruCache.get(2));
        lruCache.put(2, 1);
        lruCache.put(1, 1);
        lruCache.put(2, 3);
        lruCache.put(4, 1);
        lruCache.get(1);
        System.out.println(lruCache.get(2));
    }
}
