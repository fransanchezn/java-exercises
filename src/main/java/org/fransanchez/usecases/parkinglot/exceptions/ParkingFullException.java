package org.fransanchez.usecases.parkinglot.exceptions;

import org.fransanchez.usecases.parkinglot.VehicleType;

public class ParkingFullException extends RuntimeException {
    public ParkingFullException(final VehicleType type) {
        super("Parking full for vehicle type: " + type);
    }
}
