package org.fransanchez.usecases.exchangerate.infrastructure.openexchangerate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.fransanchez.usecases.exchangerate.domain.exception.ExchangeRateUnexpectedException;

import javax.money.CurrencyUnit;
import java.io.Closeable;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Optional;
import java.util.concurrent.Executors;

public class OpenExchangeRateClient implements Closeable {
    private final static String APP_ID = "a9b20a6956fd43c8af621c4d6a457151";
    private final static String END_POINT = "https://openexchangerates.org/api/latest.json";

    private static OpenExchangeRateClient instance;

    private final HttpClient httpClient;
    private final ObjectMapper objectMapper;

    private OpenExchangeRateClient() {
        httpClient = HttpClient.newBuilder()
                .executor(Executors.newVirtualThreadPerTaskExecutor())
                .build();
        objectMapper = new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public static OpenExchangeRateClient getInstance() {
        synchronized (OpenExchangeRateClient.class) {
            if (instance == null) {
                instance = new OpenExchangeRateClient();
            }

            return instance;
        }
    }

    public Optional<OpenExchangeRateResponse> getExchangeRate(final CurrencyUnit fromCurrencyCode, final CurrencyUnit toCurrencyCode) {
        try {
            System.out.println("OpenExchangeRateClient fetching exchange rate: from:" + fromCurrencyCode + " to: " + toCurrencyCode);
            final var request = HttpRequest.newBuilder()
                    .uri(getUri(fromCurrencyCode, toCurrencyCode))
                    .GET()
                    .build();
            final var response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            return parse(response.body());
        } catch (IOException | InterruptedException e) {
            throw ExchangeRateUnexpectedException.of(e);
        }
    }

    private Optional<OpenExchangeRateResponse> parse(final String body) throws JsonProcessingException {
        final var openExchangeRateResponse = objectMapper.readValue(body, OpenExchangeRateResponse.class);
        return openExchangeRateResponse.rates().isEmpty()
                ? Optional.empty()
                : Optional.of(openExchangeRateResponse);
    }

    private URI getUri(final CurrencyUnit fromCurrency, final CurrencyUnit toCurrency) {
        return URI.create(END_POINT
                + "?app_id=" + APP_ID
                + "&base2=" + fromCurrency.getCurrencyCode()
                + "&symbols=" + toCurrency.getCurrencyCode());
    }

    @Override
    public void close() {
        httpClient.close();
    }
}
