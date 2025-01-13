package org.fransanchez.usecases.parkinglot2;

import java.util.ArrayList;
import java.util.List;

public class Garage {
    private final List<Floor> floors;

    public Garage(final int size) {
        this.floors = new ArrayList<>(size);

        for (int i = 0; i < size; i++) {
            floors.add(new Floor(10));
        }
    }

    public boolean park(final Vehicle vehicle) {
        for (int i = 0; i < floors.size(); i++) {
            if (floors.get(i).park(vehicle)) {
                System.out.println("Parked: Floor: " + i);
                return true;
            }
        }

        return false;
    }

    public boolean free(final String licensePlate) {
        for (int i = 0; i < floors.size(); i++) {
            if (floors.get(i).free(licensePlate)) {
                System.out.println("Free up: Floor: " + i);
                return true;
            }
        }

        return false;
    }
}
