package org.fransanchez.usecases.exchangerate.infrastructure;

import org.fransanchez.usecases.exchangerate.domain.ExchangeRateProvider;

import javax.money.CurrencyUnit;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class InMemExchangeRateProvider implements ExchangeRateProvider {
    private final Map<String, BigDecimal> exchangeRates;

    public InMemExchangeRateProvider() {
        exchangeRates = new HashMap<>();

        // EUR
        exchangeRates.put("EURGBP", new BigDecimal("0.84"));
        exchangeRates.put("EURUSD", new BigDecimal("1.02"));
        // GBP
        exchangeRates.put("GBPEUR", new BigDecimal("1.19"));
        exchangeRates.put("GBPUSD", new BigDecimal("1.21"));
        // USD
        exchangeRates.put("USDEUR", new BigDecimal("0.98"));
        exchangeRates.put("USDGBP", new BigDecimal("0.82"));
    }

    @Override
    public Optional<BigDecimal> getExchangeRate(final CurrencyUnit from, final CurrencyUnit to) {
        return Optional.ofNullable(exchangeRates.get(currencyExchangeRateKey(from, to)));
    }

    private String currencyExchangeRateKey(final CurrencyUnit from, final CurrencyUnit to) {
        return String.format("%s%s", from.getCurrencyCode(), to.getCurrencyCode());
    }
}
