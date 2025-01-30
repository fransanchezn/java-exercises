package org.fransanchez.usecases.flightprices.infrastructure.caches;

import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.Executors;

public class LastRecentlyUseCache<K, V> {
    private final Map<K, Node<Entry<K,V>>> cache;
    private final DoublyLinkedList<Entry<K, V>> linkedList;
    private final int capacity;

    public LastRecentlyUseCache(final int capacity) {
        this.capacity = capacity;
        this.cache = new HashMap<>();
        this.linkedList = new DoublyLinkedList<>();
    }

    public synchronized void put(final K key, final V value) {
        if (cache.containsKey(key)) {
            linkedList.remove(cache.get(key));
        } else if (cache.size() >= capacity) {
            removeOldestEntry();
        }

        final var newEntry = new Entry<>(key, value);
        final var node = linkedList.addToHead(newEntry);
        cache.put(key, node);
    }

    public synchronized Optional<V> get(final K key) {
        return Optional.ofNullable(cache.get(key))
                .map(node -> {
                    linkedList.remove(node);
                    final var newNode = linkedList.addToHead(node.value);
                    cache.put(key, newNode);
                    return newNode.value.value;
                });
    }

    private void removeOldestEntry() {
        final var removedNode = linkedList.removeFromTail();
        cache.remove(removedNode.value.key);
    }

    private static class DoublyLinkedList<V> {
        private final Node<V> head;
        private final Node<V> tail;

        private DoublyLinkedList() {
            this.head = new Node<>();
            this.tail = new Node<>();

            head.next = tail;
            tail.previous = head;
        }

        public Node<V> addToHead(final V value) {
            final var nodeToInsert = new Node<>(value);
            final var next = this.head.next;

            // Previous
            this.head.next = nodeToInsert;
            nodeToInsert.previous = this.head;

            // Next
            next.previous = nodeToInsert;
            nodeToInsert.next = next;

            return nodeToInsert;
        }

        public Node<V> remove(final Node<V> node) {
            final var next = node.next;
            final var previous = node.previous;

            previous.next = next;
            next.previous = previous;

            return node;
        }

        public Node<V> removeFromTail() {
            return remove(tail.previous);
        }
    }

    private record Entry<K, V>(K key, V value) {}

    private static class Node<V> {
        Node<V> next;
        Node<V> previous;
        V value;

        public Node() {
            // Default constructor
        }

        public Node(final V value) {
            this.value = value;
        }
    }

    public static void main(String[] args) {
        final var lruCache = new LastRecentlyUseCache<Integer, String>(2);

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
