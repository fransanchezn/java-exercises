package org.fransanchez.usecases;

import org.fransanchez.usecases.parkinglot.VehicleType;

public record Vehicle(VehicleType type, String plate) {
}
