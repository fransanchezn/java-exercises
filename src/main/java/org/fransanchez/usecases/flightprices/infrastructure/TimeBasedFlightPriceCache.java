package org.fransanchez.usecases.flightprices.infrastructure;

import org.fransanchez.usecases.flightprices.domain.FlightPrice;
import org.fransanchez.usecases.flightprices.domain.FlightPriceCache;
import org.fransanchez.usecases.flightprices.infrastructure.caches.TimeBasedCache;

import java.time.Clock;
import java.time.Duration;
import java.util.Optional;
import java.util.function.Supplier;

public class TimeBasedFlightPriceCache implements FlightPriceCache {
    private final TimeBasedCache<String, FlightPrice> cache;

    public TimeBasedFlightPriceCache(final Clock clock) {
        this.cache = new TimeBasedCache<>(clock);
    }

    @Override
    public Optional<FlightPrice> get(String from, String to) {
        return cache.get(getKey(from, to));
    }

    @Override
    public Optional<FlightPrice> put(FlightPrice flightPrice, Duration ttl) {
        return cache.put(getKey(flightPrice.from(), flightPrice.to()), flightPrice, ttl);
    }

    @Override
    public Optional<FlightPrice> compute(String from, String to, Supplier<Optional<FlightPrice>> function, Duration ttl) {
        return cache.compute(getKey(from, to), function, ttl);
    }

    private String getKey(final String from, final String to) {
        return String.format("%s:%s", from, to);
    }
}