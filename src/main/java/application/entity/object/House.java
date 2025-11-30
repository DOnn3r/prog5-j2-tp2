package application.prog5j2tp2.entity.object;

import lombok.Getter;

@Getter
public class House extends RentableItem{
    private final String address;
    private final String city;
    private final String state;

    public House(int id, double dailyRentalPrice, boolean available, String address, String city, String state) {
        super(id, dailyRentalPrice, available);
        this.address = address;
        this.city = city;
        this.state = state;
    }
}
