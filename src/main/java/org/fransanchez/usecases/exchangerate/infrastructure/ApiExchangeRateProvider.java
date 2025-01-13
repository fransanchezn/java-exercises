package org.fransanchez.usecases.exchangerate.infrastructure;

import org.fransanchez.usecases.exchangerate.domain.ExchangeRateProvider;

import javax.money.CurrencyUnit;
import java.math.BigDecimal;
import java.util.Optional;

public class ApiExchangeRateProvider implements ExchangeRateProvider {
    private final OpenExchangeRateClient client;

    public ApiExchangeRateProvider(final OpenExchangeRateClient client) {
        this.client = client;
    }

    @Override
    public Optional<BigDecimal> getExchangeRate(CurrencyUnit from, CurrencyUnit to) {
        return client.getExchangeRate(from, to)
                .flatMap(er -> er.rates().values().stream().findFirst())
                .map(BigDecimal::new);
    }
}
