package org.fransanchez.usecases.webcrawler.infrastructure;

import org.fransanchez.usecases.webcrawler.application.UnknownWebFetcherException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.net.URI;
import java.util.Optional;

public class JsoupWebClient {
    public Optional<Document> fetchWebDocument(final String uri) {
        try {
            final var response = Jsoup.connect(uri)
                    .ignoreContentType(true)
                    .execute();

            final var contentType = response.contentType();
            if (contentType == null || !contentType.startsWith("text/html")) {
                return Optional.empty();
            }

            return Optional.of(response.parse());
        } catch (IOException e) {
            throw new UnknownWebFetcherException(URI.create(uri), e);
        }
    }
}
