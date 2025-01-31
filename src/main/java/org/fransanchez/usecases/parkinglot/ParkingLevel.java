package org.fransanchez.usecases.parkinglot;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ParkingLevel {
    private final int level;
    private final List<ParkingSlot> slots;

    public ParkingLevel(final int level, final List<ParkingSlot> slots) {
        this.level = level;
        this.slots = slots;
    }

    public synchronized List<ParkingSlot> addSlots(final VehicleType type, final int numberOfSlots) {
        final int nextNumber = slots.stream().mapToInt(ParkingSlot::number).max().orElse(0) + 1;
        final var newSlots = IntStream.range(nextNumber, nextNumber + numberOfSlots)
                .mapToObj(i -> new ParkingSlot(level, i, type))
                .collect(Collectors.toList());
        slots.addAll(newSlots);

        return newSlots;
    }

    public Optional<ParkingSlot> reserveSlot(final VehicleType type, final String vehiclePlate) {
        for (final var slot : slots) {
            if (slot.type().equals(type) && slot.isAvailable()) {
                synchronized (slot) {
                    if (slot.isAvailable()) {
                        slot.reserveSlot(vehiclePlate);
                        return Optional.of(slot);
                    }
                }
            }
        }

        return Optional.empty();
    }

    public synchronized Optional<ParkingSlot> freeSlot(final String vehiclePlate) {
        for (final var slot : slots) {
            if (slot.vehiclePlate().isPresent() && slot.vehiclePlate().get().equals(vehiclePlate)) {
                synchronized (slot) {
                    slot.freeSlot();
                    return Optional.of(slot);
                }
            }
        }

        return Optional.empty();
    }

    public synchronized List<ParkingSlot> getAvailableSlots() {
        return slots.stream().filter(ParkingSlot::isAvailable).collect(Collectors.toList());
    }

    public static void main(final String[] args) {
        final var level = new ParkingLevel(0, new ArrayList<>());
        level.addSlots(VehicleType.CAR, 10_000);

        try (final var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            for (int i = 0; i < 10_000; i++) {
                executor.submit(() -> {
                    level.reserveSlot(VehicleType.CAR, UUID.randomUUID().toString());
                });
            }
        }

        System.out.println(level.getAvailableSlots().size());
    }
}
