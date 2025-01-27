package org.fransanchez.usecases.webcrawler.infrastructure;

import org.fransanchez.usecases.webcrawler.application.Web;
import org.fransanchez.usecases.webcrawler.application.WebFetcher;

import java.net.URI;
import java.util.Set;
import java.util.stream.Collectors;

public class HttpWebFetcher implements WebFetcher {
    private final static String LINK_TAG = "a[href]";
    private final static String LINK_ATTRIBUTE = "abs:href";

    private final JsoupWebClient client;

    public HttpWebFetcher(final JsoupWebClient client) {
        this.client = client;
    }

    @Override
    public Set<Web> getWebChildren(final Web web) {
        System.out.println("Fetching web: " + web.getUri() + " and level: " + web.getLevel());

        return client.fetchWebDocument(web.getUri().toString())
                .map(d -> d.select(LINK_TAG).stream()
                .map(e -> e.attr(LINK_ATTRIBUTE))
                .map(s -> new Web(URI.create(s), web.getLevel() + 1))
                .filter(w -> w.isSameHostChild(web))
                .collect(Collectors.toUnmodifiableSet()))
                .orElseGet(Set::of);
    }
}
