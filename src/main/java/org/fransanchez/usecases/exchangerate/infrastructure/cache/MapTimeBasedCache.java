package org.fransanchez.usecases.exchangerate.infrastructure.cache;

import org.fransanchez.usecases.exchangerate.domain.cache.TimeBasedCache;

import java.io.Closeable;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MapTimeBasedCache<K, V> implements TimeBasedCache<K, V>, Closeable {
    private final Map<K, ValueEntry<V>> cache;
    private final Executor cleaner;

    public MapTimeBasedCache() {
        this.cache = new HashMap<>();
        cleaner = Executors.newSingleThreadScheduledExecutor();
        ((ScheduledExecutorService) cleaner).scheduleAtFixedRate(this::cleanUp, 0,1, TimeUnit.SECONDS);
    }

    @Override
    public void close() {
        ((ScheduledExecutorService) cleaner).close();
    }

    @Override
    public synchronized Optional<V> get(final K key) {
        final var entryOpt = Optional.ofNullable(cache.get(key));

        if (entryOpt.isPresent() && entryOpt.get().isExpired()) {
            cache.remove(key);
            return Optional.empty();
        }

        return entryOpt.map(ValueEntry::value);
    }

    @Override
    public synchronized V put(final K key, final V value, final long ttl) {
        return Optional.ofNullable(cache.put(key, new ValueEntry<>(value, Instant.now().plus(ttl, ChronoUnit.SECONDS))))
                .map(ValueEntry::value)
                .orElse(null);
    }

    private synchronized void cleanUp() {
        try {
            final var expired = cache.entrySet().stream()
                    .filter(entry -> entry.getValue().timestamp.compareTo(Instant.now()) < 0)
                    .toList();

            final var expiredCount = expired.size();
            System.out.println("cleanUp expiring: " + expiredCount);

            expired.forEach(i -> cache.remove(i.getKey()));
        } catch(Exception e) {
            throw new RuntimeException(e);
        }
    }

    record ValueEntry<V>(V value, Instant timestamp) {
        public boolean isExpired() {
            return timestamp.isBefore(Instant.now());
        }
    }
}

