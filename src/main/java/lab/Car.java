package lab;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Car {
    private UUID id;
    private String make;
    private String model;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", make='" + make + '\'' +
                ", model='" + model + '\'' +
                '}';
    }

    public static void main(String[] args) {
        List<Car> cars = new ArrayList<>();
        UUID id = UUID.randomUUID();
        Car c1 = new Car();
        c1.setId(id);
        c1.setMake("Merc");
        c1.setModel("CLA250");
        cars.add(c1);

        Car c2 = new Car();
        c2.setId(id);
        c2.setMake("Merc");
        c2.setModel("CLS400");
        cars.add(c2);

        System.out.println(cars);

        cars.stream()
                .filter((car) -> car.getModel().contains("CLA"))
                .findFirst()
                .ifPresent((foundCar) -> cars.remove(foundCar));

        System.out.println(cars);
    }
}
