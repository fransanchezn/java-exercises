package org.fransanchez.usecases.parkinglot2;

import java.util.ArrayList;
import java.util.List;

public class Floor {
    private final List<Spot> spots;

    public Floor(final int size) {
        this.spots = new ArrayList<>(size);

        for (int i = 0; i < size; i++) {
            spots.add(new Spot(i));
        }
    }

    public synchronized boolean park(final Vehicle vehicle) {
        final var available = spots.stream().filter(Spot::isAvailable).count();
        if (available < vehicle.size) {
            return false;
        }

        var allocatedSpots = new ArrayList<Spot>(vehicle.size);
        for (int i = 0; i < spots.size(); i++) {
            allocatedSpots = new ArrayList<>(vehicle.size);
            for (int j = 0; j < vehicle.size && j + i < spots.size(); j++) {
                if (spots.get(i + j).isAvailable()) {
                    allocatedSpots.add(spots.get(i + j));
                } else {
                    break;
                }

                if (allocatedSpots.size() == vehicle.size) {
                    park(allocatedSpots, vehicle);
                    return true;
                }
            }
        }

        return false;
    }

    public synchronized boolean free(final String licensePlate) {
        return spots.stream()
                .filter(i -> i.vehicle != null && i.vehicle.licensePlate.equals(licensePlate))
                .map(Spot::freeUp)
                .reduce(true, Boolean::equals);
    }

    private void park(final List<Spot> spots, final Vehicle vehicle) {
        spots.forEach(i -> i.park(vehicle));
    }
}
