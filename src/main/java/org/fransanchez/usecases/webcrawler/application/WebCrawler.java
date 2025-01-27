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

    public void crawl(final Web web, final int threads) {
        webQueue.add(web);
        processQueue(threads);
    }

    private void processQueue(final int threads) {
        for (int i = 0; i < threads; i++) {
            executor.submit(() -> {
                while (true) {
                    final var web = webQueue.poll();
                    if (web == null) {
                        System.out.println("Thread finished " + Thread.currentThread());
                        break;
                    }

                    if (!webStore.add(web.getUri())) {
                        continue;
                    }

                    final var children = webFetcher.getWebChildren(web);
                    for (final var child: children) {
                        if (child.getLevel() < 3 && !webStore.contains(child.getUri())) {
                            webQueue.add(child);
                        }
                    }
                }
            });
        }
    }

    public static void main(final String[] args) throws IOException {
        final var client = new JsoupWebClient();
        final var webFetcher = new HttpWebFetcher(client);
        final var webStore = new InMemoryWebStore();
        final var webQueue = new InMemWebQueue(1_000);

        try (final var webCrawler = new WebCrawler(webFetcher, webStore, webQueue)) {
            webCrawler.crawl(new Web(URI.create("https://web-scraping.dev/"), 0), 20_000);
        }

        System.out.println("Total size: " + webStore.getAll().size());
        System.out.println(webStore.getAll());
    }

    @Override
    public void close() {
        executor.close();
    }
}
