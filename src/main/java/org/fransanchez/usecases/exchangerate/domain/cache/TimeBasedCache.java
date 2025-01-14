package org.fransanchez.usecases.exchangerate.domain.cache;

import java.util.Optional;

public interface TimeBasedCache<K, V> {
    Optional<V> get(K key);
    V put(K key, V value, long ttl);
}
