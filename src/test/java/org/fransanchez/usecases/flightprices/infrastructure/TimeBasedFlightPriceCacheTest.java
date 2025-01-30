package org.fransanchez.usecases.flightprices.infrastructure;

import org.fransanchez.usecases.flightprices.domain.FlightPrice;
import org.javamoney.moneta.Money;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import static org.assertj.core.api.Assertions.assertThat;

class TimeBasedFlightPriceCacheTest {

    final Clock clock = Clock.systemDefaultZone();

    @Test
    void GivenAnEmptyCache_whenFetchingAKey_returnsEmpty() {
        final var sut = new TimeBasedFlightPriceCache(clock);

        final var result = sut.get("A", "B");

        assertThat(result).isEmpty();
    }

    @Test
    void GivenAnPopulatedCache_whenFetchingAKey_returnsEntry() {
        final var sut = new TimeBasedFlightPriceCache(clock);
        final var from = "LHR";
        final var to = "MAD";
        final var amount = Money.of(new BigDecimal("100"), "EUR");
        sut.put(
                new FlightPrice(from, to, amount),
                Duration.of(1_000, ChronoUnit.SECONDS)
        );

        final var result = sut.get(from, to);
        assertThat(result).isEqualTo(Optional.of(new FlightPrice(from, to, amount)));
    }

    @Test
    void GivenAnExpiredEntry_whenFetchingAKey_returnsEmpty() {
        final var sut = new TimeBasedFlightPriceCache(clock);
        final var from = "LHR";
        final var to = "MAD";
        final var amount = Money.of(new BigDecimal("100"), "EUR");
        sut.put(
                new FlightPrice(from, to, amount),
                Duration.ZERO
        );

        final var result = sut.get(from, to);
        assertThat(result).isEmpty();
    }

    @Test
    void givenMultithreadCaller_whenGettingAnEmptyEntryFromCache_fetchApiOnlyOnce() {
        final var sut = new TimeBasedFlightPriceCache(clock);
        final var from = "LHR";
        final var to = "MAD";

        try (final var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            final var counter = new AtomicInteger();
            for (int i = 0; i < 10_000; i++) {
                executor.submit(() -> {
                    sut.compute(from, to, () -> {
                        System.out.println("Fetching API");
                        counter.getAndIncrement();
                        return Optional.of(new FlightPrice(from, to, Money.of(new BigDecimal("10"), "EUR")));
                    }, Duration.ofHours(1));
                });
            }

            assertThat(counter.get()).isEqualTo(1);
        }
    }
}