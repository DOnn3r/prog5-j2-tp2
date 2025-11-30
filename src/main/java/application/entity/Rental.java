package application.prog5j2tp2.entity;

import application.prog5j2tp2.entity.object.RentableItem;
import application.prog5j2tp2.entity.users.Customer;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@AllArgsConstructor
@Getter
public class Rental {
    private final int id;
    private RentableItem rentableItem;
    private Customer customer;
    private LocalDate startDate;
    private LocalDate endDate;

    public long getRentalDays(){
        return ChronoUnit.DAYS.between(startDate, endDate) +1;
    }

    public double calculateTotalRentalPrice(){
        return rentableItem.getDailyRentalPrice()* getRentalDays();
    }
}
