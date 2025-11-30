package application.prog5j2tp2.entity.object;

import lombok.Getter;

@Getter
public class Cars extends RentableItem{
    private final String name;
    private final String color;
    private final String brand;

    public Cars(int id, double dailyRentalPrice, boolean available, String name, String color, String brand) {
        super(id, dailyRentalPrice, available);
        this.name = name;
        this.color = color;
        this.brand = brand;
    }
}
