package org.fransanchez.exercises.linkedlist;

import java.util.HashMap;
import java.util.Map;

// 146. LRU Cache
public class LRUCache {
    final Map<Integer, CacheNode> cache;
    final int capacity;

    final CacheNode head;
    final CacheNode tail;

    public LRUCache(final int capacity) {
        cache = new HashMap<>();
        this.capacity = capacity;

        head = new CacheNode();
        tail = new CacheNode();
        head.next = tail;
        tail.prev = head;
    }

    public int get(final int key) {
        final var entry = cache.get(key);
        if (entry == null) {
            return -1;
        }

        removeNode(entry);
        addNode(entry);
        return entry.value;
    }

    public void put(final int key, final int value) {
        if (cache.containsKey(key)) {
            removeNode(cache.get(key));
        } else if (cache.size() >= capacity) {
            final var node = removeLast();
            cache.remove(node.key);
        }

        final var node = new CacheNode(key, value);
        addNode(node);
        cache.put(key, node);
    }

    private CacheNode removeLast() {
        final var tailPrev = tail.prev;
        removeNode(tailPrev);
        return tailPrev;
    }

    private void addNode(final CacheNode node) {
        final var headNext = head.next;
        node.next = headNext;
        node.prev = head;

        head.next = node;
        headNext.prev = node;
    }

    private void removeNode(final CacheNode node) {
        final var nodeNext = node.next;
        final var nodePrev = node.prev;

        nodeNext.prev = nodePrev;
        nodePrev.next = nodeNext;
    }

    public static class CacheNode {
        public CacheNode next;
        public CacheNode prev;
        public int key;
        public int value;

        public CacheNode() {
            this.next = null;
            this.prev = null;
            this.key = -1;
            this.value = -1;
        }

        public CacheNode(final int key, final int value) {
            this.next = null;
            this.prev = null;
            this.key = key;
            this.value = value;
        }
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
