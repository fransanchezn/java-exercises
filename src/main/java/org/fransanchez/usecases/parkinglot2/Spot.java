package org.fransanchez.usecases.parkinglot2;

public class Spot {
    final int id;
    Vehicle vehicle;

    public Spot(final int id) {
        this.id = id;
    }

    public boolean park(final Vehicle vehicle) {
        System.out.println("Parked: spot: " + id + " Vehicle: "+ vehicle.licensePlate);
        this.vehicle = vehicle;
        return true;
    }

    public boolean freeUp() {
        System.out.println("FreeUp: spot: " + id);
        vehicle = null;
        return true;
    }

    public boolean isAvailable() {
        return vehicle == null;
    }
}
