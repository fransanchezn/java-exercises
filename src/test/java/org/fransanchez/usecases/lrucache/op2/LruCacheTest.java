package org.fransanchez.usecases.lrucache.op2;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class LruCacheTest {

    @Test
    void givenAEmptyCache_whenGettingAKey_thenReturnEmpty() {
        final var cache = new LruCache<Integer, String>(2);

        cache.get(1);

        assertThat(cache.get(1)).isEmpty();
    }

    @Test
    void givenAFullCache_whenInsertingNewRecord_thenDeleteOldestRecord() {
        final var cache = new LruCache<Integer, String>(2);
        cache.put(1, "One");
        cache.put(2, "Two");

        cache.put(3, "Three");

        assertThat(cache.get(1)).isEmpty();
    }

    @Test
    void givenAFullCache_whenGettingAKey_thenRefreshesTheOrder() {
        final var cache = new LruCache<Integer, String>(2);
        cache.put(1, "One");
        cache.put(2, "Two");

        // Refresh order
        cache.get(1);

        cache.put(3, "Three");

        assertThat(cache.get(1)).isEqualTo(Optional.of("One"));
    }
}