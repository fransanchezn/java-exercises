package org.fransanchez.usecases.parkinglot;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ParkingLevel {
    private final int level;
    private final List<ParkingSlot> slots;

    public ParkingLevel(final int level, final List<ParkingSlot> slots) {
        this.level = level;
        this.slots = slots;
    }

    public List<ParkingSlot> addSlots(final VehicleType type, final int numberOfSlots) {
        final int nextNumber = slots.stream().mapToInt(ParkingSlot::number).max().orElse(0) + 1;
        final var newSlots = IntStream.range(nextNumber, nextNumber + numberOfSlots)
                .mapToObj(i -> new ParkingSlot(level, i, type))
                .collect(Collectors.toList());
        slots.addAll(newSlots);

        return newSlots;
    }

    public synchronized Optional<ParkingSlot> reserveSlot(final VehicleType type, final String vehiclePlate) {
        final var slotOptional = slots.stream()
                .filter(i -> type.equals(i.type()))
                .filter(ParkingSlot::isAvailable)
                .findAny();

        if (slotOptional.isPresent()) {
            final var slot = slotOptional.get();
            slot.reserveSlot(vehiclePlate);
        }

        return slotOptional;
    }

    public synchronized Optional<ParkingSlot> freeSlot(final String vehiclePlate) {
        final var slotOptional = slots.stream()
                .filter(i -> i.vehiclePlate().isPresent() && i.vehiclePlate().get().equals(vehiclePlate))
                .findFirst();

        if (slotOptional.isPresent()) {
            final var slot = slotOptional.get();
            slot.freeSlot();
        }

        return slotOptional;
    }

    public List<ParkingSlot> getAvailableSlots() {
        return slots.stream().filter(ParkingSlot::isAvailable).collect(Collectors.toList());
    }
}
