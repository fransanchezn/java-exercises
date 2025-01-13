package org.fransanchez.usecases.exchangerate.domain.exception;

public class ExchangeRateUnexpectedException extends RuntimeException {
    private ExchangeRateUnexpectedException(final Exception e) {
        super("Unexpected exception while communicating with the exchange rate provider", e);
    }

    public static ExchangeRateUnexpectedException of(final Exception e) {
        return new ExchangeRateUnexpectedException(e);
    }
}
