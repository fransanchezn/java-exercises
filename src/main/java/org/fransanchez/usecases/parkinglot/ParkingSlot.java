package org.fransanchez.usecases.parkinglot;

import java.util.Optional;

public class ParkingSlot {
    private final int number;
    private final int level;
    private final VehicleType type;
    private String vehiclePlate;

    public ParkingSlot(final int level, final int number, final VehicleType type) {
        this.level = level;
        this.number = number;
        this.type = type;
        this.vehiclePlate = null;
    }

    public void reserveSlot(final String vehiclePlate) {
        if (this.vehiclePlate != null) {
            throw new IllegalStateException("Vehicle spot is already take");
        }

        this.vehiclePlate = vehiclePlate;
    }

    public void freeSlot() {
        this.vehiclePlate = null;
    }

    public int number() {
        return number;
    }

    public VehicleType type() {
        return type;
    }

    public boolean isAvailable() {
        return vehiclePlate == null || vehiclePlate.isEmpty();
    }

    public Optional<String> vehiclePlate() {
        return Optional.ofNullable(vehiclePlate);
    }

    @Override
    public String toString() {
        return "ParkingSlot{" +
                "level=" + level +
                " number=" + number +
                ", type=" + type +
                ", vehiclePlate='" + vehiclePlate + '\'' +
                '}';
    }
}
