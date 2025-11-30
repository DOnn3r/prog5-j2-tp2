package application.prog5j2tp2.entity.object;

import lombok.Getter;

@Getter
public class Money extends RentableItem{
    private final double tax;

    public Money(int id, double dailyRentalPrice, boolean available, double tax) {
        super(id, dailyRentalPrice, available);
        this.tax = tax;
    }
}
