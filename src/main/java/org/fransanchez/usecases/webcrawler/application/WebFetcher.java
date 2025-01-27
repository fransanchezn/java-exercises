package org.fransanchez.usecases.webcrawler.application;

import java.util.Set;

public interface WebFetcher {
    Set<Web> getWebChildren(final Web web);
}
