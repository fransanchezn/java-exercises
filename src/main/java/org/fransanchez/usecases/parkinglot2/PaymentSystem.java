package org.fransanchez.usecases.parkinglot2;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class PaymentSystem {
    private final Map<String, Ticket> tickets;
    private final BigDecimal farePerMinute = new BigDecimal("0.64");

    public PaymentSystem() {
        tickets = new HashMap<>();
    }

    public Optional<Ticket> add(final String licensePlate) {
        final var ticket = tickets.get(licensePlate);
        if (ticket != null) {
            return Optional.empty();
        }

        final var newTicket = new Ticket(licensePlate, Instant.now());
        tickets.put(licensePlate, newTicket);
        return Optional.of(newTicket);
    }

    public Optional<Receipt> pay(final String licensePlate) {
        final var ticketOpt = Optional.ofNullable(tickets.get(licensePlate));
        if (ticketOpt.isEmpty()) {
            return Optional.empty();
        }

        final var ticket = ticketOpt.get();
        return Optional.of(charge(ticket));
    }

    private Receipt charge(final Ticket ticket) {
        final var duration = Duration.between(ticket.startTime(), Instant.now());
        tickets.remove(ticket.licensePlate());

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
