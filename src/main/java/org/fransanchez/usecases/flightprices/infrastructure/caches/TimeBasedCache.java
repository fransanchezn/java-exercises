package org.fransanchez.usecases.flightprices.infrastructure.caches;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.function.Supplier;

public class TimeBasedCache<K, V> {
    private record Entry<V>(V value, Instant expirationTime) {
        public boolean isExpired(final Instant now) {
            return expirationTime.compareTo(now) < 0;
        }
    }

    private final Map<K, Entry<V>> cache;
    private final Clock clock;

    public TimeBasedCache(final Clock clock) {
        this.cache = new ConcurrentHashMap<>();
        this.clock = clock;
    }

    public Optional<V> get(final K key) {
        final var now = clock.instant();

        return Optional.ofNullable(cache.computeIfPresent(key, (s, e) -> e.isExpired(now) ? null : e))
                .map(Entry::value);
    }

    public Optional<V> put(final K key, final V value, final Duration ttl) {
        final var expirationTime = clock.instant().plus(ttl);

        return Optional.ofNullable(cache.put(key, new Entry<>(value, expirationTime)))
                .map(Entry::value);
    }

    public Optional<V> compute(
            final K key,
            final Supplier<Optional<V>> function,
            final Duration ttl) {
        final var now = clock.instant();

        final var value = cache.compute(key, (k, v) -> {
            if (v == null || v.isExpired(now)) {
                final var valueOpt = function.get();
                return valueOpt
                        .map(val -> new Entry<>(val, now.plus(ttl)))
                        .orElse(null);
            }

            return v;
        });

        return Optional.ofNullable(value).map(Entry::value);
    }

    public static void main(String[] args) {
        final var lruCache = new TimeBasedCache<Integer, String>(Clock.systemDefaultZone());

        final var start = Instant.now();
        try (final var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            for (int i = 0; i < 10_000_000; i++) {
                final var counter = i;
                executor.submit(() -> {
                    lruCache.compute(counter, () -> Optional.of("A"), Duration.ofMinutes(1));
                });
            }
        }

        final var end = Instant.now();
        System.out.println("LRU Size: " + lruCache.cache.size());
        System.out.println("Duration: " + Duration.between(start, end));
    }
}
