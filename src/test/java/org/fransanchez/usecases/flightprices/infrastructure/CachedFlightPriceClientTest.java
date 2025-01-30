package org.fransanchez.usecases.flightprices.infrastructure;

import org.fransanchez.usecases.flightprices.domain.FlightPrice;
import org.fransanchez.usecases.flightprices.domain.FlightPriceCache;
import org.fransanchez.usecases.flightprices.domain.FlightPriceClient;
import org.javamoney.moneta.Money;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.Duration;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class CachedFlightPriceClientTest {

    private final Clock clock = Clock.systemDefaultZone();
    private FlightPriceCache cache;
    private FlightPriceClient mockClient;

    @BeforeEach
    public void setup() {
        cache = new TimeBasedFlightPriceCache(clock);
        mockClient = (from, to) -> Optional.of(new FlightPrice("ASD", "QWE", Money.of(new BigDecimal("11"), "XOF")));
    }

    @Test
    void givenNonPopulatedCache_whenFetchingEntry_thenCallsApiClient() {
        final var client = new CachedFlightPriceClient(mockClient, cache);

        final var from = "LHR";
        final var to = "MAD";

        final var result = client.fetch(from, to);

        assertThat(result).isEqualTo(Optional.of(new FlightPrice("ASD", "QWE", Money.of(new BigDecimal("11"), "XOF"))));
    }

    @Test
    void givenPopulatedCache_whenFetchingEntry_thenFastResponse() {
        final var client = new CachedFlightPriceClient(mockClient, cache);
        final var from = "LHR";
        final var to = "MAD";
        final var amount = Money.of(new BigDecimal("100"), "GBP");

        cache.put(new FlightPrice(from, to, amount), Duration.ofHours(1));

        final var result = client.fetch(from, to);
        assertThat(result).isEqualTo(Optional.of(new FlightPrice(from, to, amount)));
    }
}