package org.fransanchez.usecases.parkinglot2;

public class ParkingSystem {
    private final Garage garage;
    private final PaymentSystem paymentSystem;

    public ParkingSystem(final int floors) {
        this.garage = new Garage(2);
        this.paymentSystem = new PaymentSystem();
    }

    public boolean park(final Vehicle vehicle) {
        return paymentSystem.add(vehicle.licensePlate)
                .map(t -> garage.park(vehicle))
                .orElse(false);
    }

    public boolean pay(final String licensePlate) {
        return paymentSystem.pay(licensePlate)
                .map(r -> garage.free(licensePlate))
                .orElse(false);
    }

    public static void main(String[] args) {
        final var parkingSystem = new ParkingSystem(2);
        parkingSystem.park(new Car("Fran001"));
        parkingSystem.park(new Limo("Fran002"));
        parkingSystem.park(new Semitruck("Fran003"));

        parkingSystem.pay("Fran002");
        parkingSystem.park(new Semitruck("Fran004"));
    }
}
