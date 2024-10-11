package org.fransanchez.designpattern.creational.builder;

public class CarBuilder {

    // All configurable attribute for Car
    private String type;
    private String brand;
    private CarEngine engine;

    public CarBuilder withType(final String type) {
        this.type = type;
        return this;
    }

    public CarBuilder withBrand(final String brand) {
        this.brand = brand;
        return this;
    }

    public CarBuilder withEngine(final CarEngine engine) {
        this.engine = engine;
        return this;
    }

    public Car build() {
        return new Car(type, brand, engine);
    }

    public static void main(String[] args) {
        final var builder = new CarBuilder();
        final var car = builder
                .withBrand("Ford")
                .withType("Sports")
                .withEngine(CarEngine.TWO_LITRES_V6)
                .build();

        System.out.println(car);

    }
}
