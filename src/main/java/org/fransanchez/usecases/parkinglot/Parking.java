package org.fransanchez.usecases.parkinglot;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.fransanchez.usecases.parkinglot.exceptions.ParkingFullException;
import org.fransanchez.usecases.parkinglot.exceptions.VehicleNotFoundException;

public class Parking {
    private final List<ParkingLevel> levels;

    public Parking() {
        this.levels = new ArrayList<>();
    }

    public synchronized ParkingLevel addLevel() {
        final var nextLevel = levels.size();
        final var level = new ParkingLevel(nextLevel, new ArrayList<>());
        levels.add(level);

        return level;
    }

    public ParkingSlot reserveSlot(final String vehiclePlate, final VehicleType type) {
        for (var level: levels) {
            final var slotOptional = level.reserveSlot(type, vehiclePlate);
            if (slotOptional.isPresent()) {
                return slotOptional.get();
            }
        }

        throw new ParkingFullException(type);
    }

    public ParkingSlot freeSlot(final String vehiclePlate) {
        for (var level: levels) {
            final var slotOptional = level.freeSlot(vehiclePlate);
            if (slotOptional.isPresent()) {
                return slotOptional.get();
            }
        }

        throw new VehicleNotFoundException(vehiclePlate);
    }

    public Map<VehicleType, List<ParkingSlot>> getAvailability() {
        return levels.stream()
                .flatMap(i -> i.getAvailableSlots().stream())
                .collect(Collectors.groupingBy(ParkingSlot::type));
    }
}
