package org.fransanchez.usecases.exchangerate.domain.exception;

import javax.money.CurrencyUnit;

public class ExchangeRateNotFoundException extends RuntimeException {
    private ExchangeRateNotFoundException(final CurrencyUnit fromCurrency, CurrencyUnit toCurrency) {
        super("Exchange rate not found for from currency: " + fromCurrency.getCurrencyCode() + " and to currency: " + toCurrency.getCurrencyCode());
    }

    public static ExchangeRateNotFoundException of(final CurrencyUnit fromCurrency, CurrencyUnit toCurrency) {
        return new ExchangeRateNotFoundException(fromCurrency, toCurrency);
    }
}
