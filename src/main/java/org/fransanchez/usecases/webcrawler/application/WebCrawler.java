package org.fransanchez.usecases.webcrawler.application;

import org.fransanchez.usecases.webcrawler.infrastructure.HttpWebFetcher;
import org.fransanchez.usecases.webcrawler.infrastructure.InMemWebQueue;
import org.fransanchez.usecases.webcrawler.infrastructure.InMemoryWebStore;
import org.fransanchez.usecases.webcrawler.infrastructure.JsoupWebClient;

import java.io.Closeable;
import java.io.IOException;
import java.net.URI;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class WebCrawler implements Closeable {
    private final WebFetcher webFetcher;
    private final WebStore webStore;
    private final WebQueue webQueue;
    private final ExecutorService executor;

    public WebCrawler(final WebFetcher webFetcher, final WebStore webStore, final WebQueue webQueue) {
        this.webFetcher = webFetcher;
        this.webStore = webStore;
        this.webQueue = webQueue;
        this.executor = Executors.newVirtualThreadPerTaskExecutor();
    }

    public void crawl(final Web web) {
        webQueue.add(web);
        Web next = web;
        while(next != null) {
            next = webQueue.poll();
            System.out.println("Thread polling: " + Thread.currentThread());
            if (next != null && next.getLevel() < 3 && !webStore.contains(next.getUri())) {
                webStore.add(next.getUri());
                final Web finalNext = next;
                executor.submit(() -> processWeb(finalNext));
            }
        }
    }

    private void processWeb(final Web web) {
        final var children = webFetcher.getWebChildren(web);
        for (final var child : children) {
            if (!webStore.contains(child.getUri())) {
                webQueue.add(child);
            }
        }
    }

    public static void main(final String[] args) throws IOException {
        final var client = new JsoupWebClient();
        final var webFetcher = new HttpWebFetcher(client);
        final var webStore = new InMemoryWebStore();
        final var webQueue = new InMemWebQueue(10);

        try (final var webCrawler = new WebCrawler(webFetcher, webStore, webQueue)) {
            webCrawler.crawl(new Web(URI.create("https://web-scraping.dev/"), 0));
        }

        System.out.println("Total size: " + webStore.getAll().size());
        System.out.println(webStore.getAll());
    }

    @Override
    public void close() {
        executor.close();
    }
}
