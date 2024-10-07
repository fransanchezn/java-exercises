package org.fransanchez.usecases.parkinglot;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ParkingMain {
    public static void main(final String[] args) throws InterruptedException, ExecutionException {
        final var executor = Executors.newFixedThreadPool(100);

        // Parking 1
        final var parking = new Parking();

        final var levelOne = parking.addLevel();
        final var levelTwo = parking.addLevel();

        // Level 1
        levelOne.addSlots(VehicleType.CAR, 50);
        levelOne.addSlots(VehicleType.TRUCK, 1);
        levelOne.addSlots(VehicleType.MOTORCYCLE, 1);

        // Level 2
        levelTwo.addSlots(VehicleType.CAR, 50);

        // Entering vehicles
        final var random = new Random();
        final var vehicles = new ArrayList<String>();
        for (int i = 0; i < 100; i++) {
            vehicles.add(random.nextInt(0, 1001) + "");
        }

        final var futures = new ArrayList<Future<?>>();
        for (int i = 0; i < 100; i++) {
            int finalI = i;
            futures.add(executor.submit(() -> {
                parking.reserveSlot(vehicles.get(finalI), VehicleType.CAR);
                System.out.println("Availability entry: " + parking.getAvailability().getOrDefault(VehicleType.CAR, new ArrayList<>()).size());

                try {
                    Thread.sleep(random.nextInt(500, 1000));
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                System.out.println("Availability exit: " + parking.getAvailability().getOrDefault(VehicleType.CAR, new ArrayList<>()).size());
                parking.freeSlot(vehicles.get(finalI));
            }));
        }

        for (var future: futures) {
            future.get();
        }

        System.out.println("Availability: " + parking.getAvailability().getOrDefault(VehicleType.CAR, new ArrayList<>()).size());

        // Exiting vehicles
        System.out.println("END");
    }
}
