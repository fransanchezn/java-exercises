package org.fransanchez.usecases.flightprices.infrastructure;

import org.fransanchez.usecases.flightprices.domain.FlightPrice;
import org.javamoney.moneta.Money;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class ApiFlightPriceClientTest {

    private final ApiFlightPriceClient sut = new ApiFlightPriceClient();

    @Test
    void givenExistingFlightPrice_whenCallingFetch_returnsFlightPrice() {
        final var result = sut.fetch("LHR", "MAD");

        assertThat(result)
                .isEqualTo(Optional.of(new FlightPrice("LHR", "MAD", Money.of(new BigDecimal("100"), "GBP"))));
    }

    @Test
    void givenNonExistingFlightPrice_whenCallingFetch_returnsFlightPrice() {
        final var result = sut.fetch("ZWAS", "MAD");

        assertThat(result).isEmpty();
    }
}