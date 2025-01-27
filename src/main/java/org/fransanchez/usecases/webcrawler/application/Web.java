package org.fransanchez.usecases.webcrawler.application;

import java.net.URI;

public class Web {
    private final URI uri;
    private final int level;

    public Web(final URI main, final int level) {
        this.uri = main;
        this.level = level;
    }

    public boolean isSameHostChild(final Web web) {
        return this.uri.getHost().equals(web.uri.getHost());
    }

    public URI getUri() {
        return uri;
    }

    public int getLevel() {
        return level;
    }
}
