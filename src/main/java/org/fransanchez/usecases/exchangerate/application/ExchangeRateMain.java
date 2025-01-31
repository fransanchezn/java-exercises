package org.fransanchez.usecases.exchangerate.application;

import org.fransanchez.usecases.exchangerate.domain.MoneyConverter;
import org.fransanchez.usecases.exchangerate.infrastructure.openexchangerate.ApiExchangeRateProvider;
import org.fransanchez.usecases.exchangerate.infrastructure.openexchangerate.OpenExchangeRateClient;
import org.fransanchez.usecases.exchangerate.infrastructure.cache.MapTimeBasedCache;
import org.javamoney.moneta.Money;

import javax.money.Monetary;
import java.math.BigDecimal;
import java.util.concurrent.Executors;

public class ExchangeRateMain {
    public static void main(String[] args) {
        try (
                final var exchangeRateClient = OpenExchangeRateClient.getInstance();
                final var cache = new MapTimeBasedCache<String, BigDecimal>()
        ) {
            final var exchangeRateProvider = new ApiExchangeRateProvider(exchangeRateClient, cache);
            final var exchangeRateService = new MoneyConverter(exchangeRateProvider);

            final var usd = Monetary.getCurrency("USD");
            final var eur = Monetary.getCurrency("EUR");
            final var gbp = Monetary.getCurrency("GBP");

            try (final var executorPool = Executors.newFixedThreadPool(100)) {
                for (int i = 0; i < 1; i++) {
                    if (i % 2 == 0) {
                        executorPool.submit(() -> {
                            final var usdGbp = exchangeRateService.convert(Money.of(new BigDecimal("1.23432"), usd), gbp);
                            System.out.println("USD to GBP: " + usdGbp);
                        });
                    } else {
                        executorPool.submit(() -> {
                            final var usdEur = exchangeRateService.convert(Money.of(new BigDecimal("1.23432"), usd), eur);
                            System.out.println("USD to EUR: " + usdEur);
                        });
                    }
                }
            }
        }
    }
}
