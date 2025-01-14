package org.fransanchez.usecases.exchangerate.domain;

import org.fransanchez.usecases.exchangerate.domain.exception.ExchangeRateNotFoundException;

import javax.money.CurrencyUnit;
import javax.money.MonetaryAmount;

public class MoneyConverter {
    private final ExchangeRateProvider exchangeRateProvider;

    public MoneyConverter(final ExchangeRateProvider exchangeRateProvider) {
        this.exchangeRateProvider = exchangeRateProvider;
    }

    public MoneyConverterResult convert(final MonetaryAmount amount, final CurrencyUnit toCurrency) {
        final var exchangeRate = exchangeRateProvider.getExchangeRate(amount.getCurrency(), toCurrency)
                .orElseThrow(() -> ExchangeRateNotFoundException.of(amount.getCurrency(), toCurrency));

        final var convertedAmount = amount.multiply(exchangeRate);
        return new MoneyConverterResult(convertedAmount, exchangeRate);
    }
}
