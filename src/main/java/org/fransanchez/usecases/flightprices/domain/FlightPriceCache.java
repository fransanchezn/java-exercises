package org.fransanchez.usecases.flightprices.domain;

import java.time.Duration;
import java.util.Optional;
import java.util.function.Supplier;

public interface FlightPriceCache {
    Optional<FlightPrice> get(final String from, final String to);
    Optional<FlightPrice> put(final FlightPrice flightPrice, final Duration ttl);
    Optional<FlightPrice> compute(final String from, final String to, final Supplier<Optional<FlightPrice>> function, final Duration ttl);
}
