package org.fransanchez.usecases.flightprices.domain;

import javax.money.MonetaryAmount;

public record FlightPrice(String from, String to, MonetaryAmount amount) { }
