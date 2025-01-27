package org.fransanchez.usecases.webcrawler.application;

import java.net.URI;

public class UnknownWebFetcherException extends RuntimeException {
    public UnknownWebFetcherException(final URI uri, final Throwable cause) {
        super(String.format("Error while fetching web: " + uri), cause);
    }
}
