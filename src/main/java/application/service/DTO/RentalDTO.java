package application.prog5j2tp2.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class RentalDTO {
    private int customerId;
    private int itemId;
    private LocalDate startDate;
    private LocalDate endDate;
}