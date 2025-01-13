package org.fransanchez.usecases.exchangerate.domain;

import javax.money.CurrencyUnit;
import java.math.BigDecimal;
import java.util.Optional;

public interface ExchangeRateProvider {
    Optional<BigDecimal> getExchangeRate(final CurrencyUnit from, final CurrencyUnit to);
}
