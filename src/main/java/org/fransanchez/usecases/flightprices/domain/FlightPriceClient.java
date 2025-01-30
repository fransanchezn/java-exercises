package org.fransanchez.usecases.flightprices.domain;

import java.util.Optional;

public interface FlightPriceClient {
    Optional<FlightPrice> fetch(final String from, final String to);
}
