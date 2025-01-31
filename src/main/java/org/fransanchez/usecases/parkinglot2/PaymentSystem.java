package org.fransanchez.usecases.parkinglot2;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.Instant;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class PaymentSystem {
    private final Map<String, Ticket> tickets;
    private final BigDecimal farePerMinute = new BigDecimal("0.64");

    public PaymentSystem() {
        tickets = new ConcurrentHashMap<>();
    }

    public Ticket add(final String licensePlate) {
        final var ticket = new Ticket(licensePlate, Instant.now());
        final var prevTicket = tickets.putIfAbsent(licensePlate, ticket);

        if (prevTicket != null) {
            throw new RuntimeException("Car already in the payment system");
        }

        return ticket;
    }

    public Receipt pay(final String licensePlate) {
        final var ticket = tickets.remove(licensePlate);

        if (ticket == null) {
            throw new RuntimeException("License plate not found in Payment System");
        }

        return charge(ticket);
    }

    private Receipt charge(final Ticket ticket) {
        final var duration = Duration.between(ticket.startTime(), Instant.now());

        System.out.println("Charging: " + ticket.licensePlate() + " for: " + duration.toMinutes());
        return new Receipt(
                ticket.licensePlate,
                duration,
                farePerMinute.multiply(new BigDecimal(duration.toSeconds())));
    }

    public record Ticket(String licensePlate, Instant startTime) {}
    public record Receipt(String licensePlate, Duration endTime, BigDecimal cost) {
    }
}
