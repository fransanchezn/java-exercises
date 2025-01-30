package org.fransanchez.usecases.flightprices.infrastructure;

import org.fransanchez.usecases.flightprices.domain.FlightPrice;
import org.fransanchez.usecases.flightprices.domain.FlightPriceCache;
import org.fransanchez.usecases.flightprices.domain.FlightPriceClient;

import java.time.Duration;
import java.util.Optional;

public class CachedFlightPriceClient implements FlightPriceClient {
    private final FlightPriceClient client;
    private final FlightPriceCache cache;

    public CachedFlightPriceClient(final FlightPriceClient client, final FlightPriceCache cache) {
        this.client = client;
        this.cache = cache;
    }

    @Override
    public Optional<FlightPrice> fetch(final String from, final String to) {
        return cache.compute(from, to, () -> client.fetch(from, to), Duration.ofMinutes(1));
    }
}
