package org.fransanchez.usecases.lrucache;

import java.util.Optional;

public interface Cache<K, V> {
    Optional<V> get(K key);
    Optional<V> put(K key, V value);
}
