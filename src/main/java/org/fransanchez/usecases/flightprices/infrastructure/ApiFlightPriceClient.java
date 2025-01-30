package org.fransanchez.usecases.flightprices.infrastructure;

import org.fransanchez.usecases.flightprices.domain.FlightPrice;
import org.fransanchez.usecases.flightprices.domain.FlightPriceClient;
import org.javamoney.moneta.Money;

import javax.money.MonetaryAmount;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ApiFlightPriceClient implements FlightPriceClient {

    private static final Map<String, MonetaryAmount> flightPrices;

    static {
        flightPrices = new HashMap<>();
        flightPrices.put("LHR:MAD", Money.of(new BigDecimal("100"), "GBP"));
        flightPrices.put("MAD:LHR", Money.of(new BigDecimal("110"), "EUR"));
    }

    @Override
    public Optional<FlightPrice> fetch(String from, String to) {
        delay();
        return Optional.ofNullable(flightPrices.get(String.format("%s:%s", from, to)))
                .map(m -> new FlightPrice(from, to, m));
    }

    private void delay() {
        try {
            Thread.sleep(5_000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
