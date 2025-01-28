package org.fransanchez.concurrency.course.multithreading.ep3.performanceoptimization;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

public class ThroughputHttpServer {
    private static final String INPUT_FILE = "/throughputhttpserver/war_and_peace.txt";

    public static void main(final String[] args) throws IOException {
        String book;
        try (final var inputStream = ThroughputHttpServer.class.getResourceAsStream(INPUT_FILE)) {
            book = new String(inputStream.readAllBytes());
        }
        startServer(book);
    }

    private static void startServer(final String text) throws IOException {
        final var executor = Executors.newFixedThreadPool(1);

        final var server = HttpServer.create(new InetSocketAddress(8000), 0);
        server.createContext("/search", new WordCountHandler(text));
        server.setExecutor(executor);
        server.start();
    }

    public static class WordCountHandler implements HttpHandler {
        private final String text;

        public WordCountHandler(final String text) {
            this.text = text;
        }

        @Override
        public void handle(final HttpExchange exchange) throws IOException {
            final var query = exchange.getRequestURI().getQuery();
            final var keyValue = query.split("=");
            final var action = keyValue[0];
            final var word = keyValue[1];

            if (!action.equals("word")) {
                exchange.sendResponseHeaders(400, 0);
                return;
            }

            final var count = countWord(word);

            final byte[] response = Long.toString(count).getBytes();
            exchange.sendResponseHeaders(200, response.length);
            try (final var output = exchange.getResponseBody()) {
                output.write(response);
            }
        }

        private long countWord(final String word) {
            long count = 0;
            var currentWord = new StringBuilder();

            for (int i = 0; i < text.length(); i++) {
                if (Character.isLetter(text.charAt(i))) {
                    currentWord.append(text.charAt(i));
                } else {
                    if (currentWord.toString().equals(word)) {
                        count++;
                    }
                    if (!currentWord.isEmpty()) {
                        currentWord = new StringBuilder();
                    }
                }
            }

            return count;
        }
    }
}
