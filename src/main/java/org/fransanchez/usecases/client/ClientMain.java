package org.fransanchez.usecases.client;

public class ClientMain {
    public static void main(String[] args) {
        final var client = new BasicClient();
        final var circuitBreakerClient = new CircuitBreakerClient(client);
        final var retryerClient = new RetryClient(circuitBreakerClient);

        final var result = retryerClient.execute("Hola!");

        System.out.println("Result: " + result);
    }
}
