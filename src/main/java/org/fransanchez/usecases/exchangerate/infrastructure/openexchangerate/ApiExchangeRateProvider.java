package org.fransanchez.usecases.exchangerate.infrastructure.openexchangerate;

import org.fransanchez.usecases.exchangerate.domain.ExchangeRateProvider;
import org.fransanchez.usecases.exchangerate.domain.cache.TimeBasedCache;

import javax.money.CurrencyUnit;
import java.math.BigDecimal;
import java.util.Optional;

public class ApiExchangeRateProvider implements ExchangeRateProvider {
    private final OpenExchangeRateClient client;
    private final TimeBasedCache<String, BigDecimal> cache;

    public ApiExchangeRateProvider(final OpenExchangeRateClient client, final TimeBasedCache<String, BigDecimal> cache) {
        this.client = client;
        this.cache = cache;
    }

    @Override
    public Optional<BigDecimal> getExchangeRate(final CurrencyUnit from, final CurrencyUnit to) {
        return cache.get(getCacheKey(from, to))
                .or(() -> fetchAndPopulateCache(from, to));
    }

    private Optional<BigDecimal> fetchAndPopulateCache(final CurrencyUnit from, final CurrencyUnit to) {
        final var exchangeRateOpt = client.getExchangeRate(from, to)
                .flatMap(er -> er.rates().values().stream().findFirst())
                .map(BigDecimal::new);

        exchangeRateOpt.ifPresent(bigDecimal -> cache.put(getCacheKey(from, to), bigDecimal, 2L));

        return exchangeRateOpt;
    }

    private String getCacheKey(final CurrencyUnit from, final CurrencyUnit to) {
        return String.format("%s-%s", from.getCurrencyCode(), to.getCurrencyCode());
    }
}
