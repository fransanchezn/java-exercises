package org.fransanchez.usecases.parkinglot2;

public abstract sealed class Vehicle permits Car, Limo, Semitruck {
    protected final int size;
    protected final String licensePlate;

    protected Vehicle(final int size, final String licensePlate) {
        this.size = size;
        this.licensePlate = licensePlate;
    }

    public int size() {
        return size;
    }

    public String licensePlate() {
        return licensePlate;
    }
}
