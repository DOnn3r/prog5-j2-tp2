package application.prog5j2tp2.entity.object;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
public abstract class RentableItem implements RentalObject{
    private int id;
    private double dailyRentalPrice;
    @Setter
    private boolean available;

    @Override
    public boolean availability() {
        return available;
    }


    @Override
    public double getDailyRentalPrice() {
        return dailyRentalPrice;
    }
}
