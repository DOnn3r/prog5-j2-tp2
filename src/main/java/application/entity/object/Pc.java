package application.prog5j2tp2.entity.object;

import lombok.Getter;

@Getter
public class Pc extends RentableItem{
    private final String brand;
    private final String model;

    public Pc(int id, double dailyRentalPrice, boolean available, String brand, String model, String dell) {
        super(id, dailyRentalPrice, available);
        this.brand = brand;
        this.model = model;
    }
}
