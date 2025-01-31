package org.fransanchez.usecases.parkinglot2;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.Executors;

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
            final var floor = floors.get(i);
            if (floor.park(vehicle)) {
                System.out.println("Parked: Floor: " + i);
                return true;
            }
        }

        return false;
    }

    public boolean free(final String licensePlate) {
        for (int i = 0; i < floors.size(); i++) {
            final var floor = floors.get(i);
            if (floor.free(licensePlate)) {
                System.out.println("Free up: Floor: " + i);
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) throws InterruptedException {
        final var garage = new Garage(1);

        final var cars = new ArrayList<String>();
        for (int i = 0; i < 10; i++) {
            cars.add(UUID.randomUUID().toString());
        }

        try (final var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            for (String plate: cars) {
                executor.submit(() -> {
                    try {
                        final var parked = garage.park(new Car(plate));
                        if (!parked) {
                            System.out.println("#### NOT PARKED! ####");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
            }
        }

        try (final var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            for (String plate: cars) {
                executor.submit(() -> garage.free(plate));
            }
        }

        final var result = garage.park(new Car(UUID.randomUUID().toString()));
        System.out.println("Last car: " + result);
    }
}
