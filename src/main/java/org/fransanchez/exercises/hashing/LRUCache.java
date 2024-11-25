package org.fransanchez.exercises.hashing;

import java.util.LinkedHashMap;
import java.util.Map;

// 146. LRU Cache
public class LRUCache {
    final Map<Integer, Integer> cache;

    public LRUCache(final int capacity) {
        cache = new LinkedHashMap<>(capacity, 0.75f, true) {
            @Override
            protected boolean removeEldestEntry(final Map.Entry<Integer, Integer> eldest) {
                return size() > capacity;
            }
        };
    }

    public int get(final int key) {
        return cache.getOrDefault(key, -1);
    }

    public void put(final int key, final int value) {
        cache.put(key, value);
    }

    public static void main(String[] args) {
        LRUCache lRUCache = new LRUCache(2);
        lRUCache.get(2);
        lRUCache.put(2, 1);
        lRUCache.put(1, 1);
        lRUCache.put(2, 3);
        lRUCache.put(4, 1);
        lRUCache.get(1);
        System.out.println(lRUCache.get(2));;
    }
}
