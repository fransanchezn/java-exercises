package org.fransanchez.usecases.parkinglot.exceptions;

public class VehicleNotFoundException extends RuntimeException {
    public VehicleNotFoundException(final String vehiclePlate) {
        super("Vehicle not found inside the parking: " + vehiclePlate);
    }
}
