package application.prog5j2tp2.entity.object;

import lombok.Getter;

@Getter
public class Bike extends RentableItem{
    private final String brand;
    private final String model;
    private final String color;

    public Bike(int id, double dailyRentalPrice, boolean available, String brand, String model, String color) {
        super(id, dailyRentalPrice, available);
        this.brand = brand;
        this.model = model;
        this.color = color;
    }
}
