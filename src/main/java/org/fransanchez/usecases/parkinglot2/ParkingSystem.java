package org.fransanchez.usecases.parkinglot2;

import java.util.UUID;
import java.util.concurrent.Executors;

public class ParkingSystem {
    private final Garage garage;
    private final PaymentSystem paymentSystem;

    public ParkingSystem(final int floors) {
        this.garage = new Garage(floors);
        this.paymentSystem = new PaymentSystem();
    }

    public boolean park(final Vehicle vehicle) {
        final var spaceAvailable = garage.park(vehicle);
        if (spaceAvailable) {
            paymentSystem.add(vehicle.licensePlate);
        }
        return spaceAvailable;
    }

    public boolean pay(final String licensePlate) {
        paymentSystem.pay(licensePlate);
        return garage.free(licensePlate);
    }

    public static void main(final String[] args) {
        final var parkingSystem = new ParkingSystem(1);
        try (final var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            for (int i = 0; i < 20; i++) {
                executor.submit(() -> {
                    final var plate = UUID.randomUUID().toString();
                    final var parked = parkingSystem.park(new Car(plate));
                    if (!parked) {
                        System.out.println("Vehicle: " + plate + " not parked!");
                    }
                });
            }
        }

        System.out.println(parkingSystem);
    }
}
