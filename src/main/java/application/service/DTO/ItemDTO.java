package application.prog5j2tp2.dto;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class ItemDTO {
    private String itemType;
    private double dailyRentalPrice;
    private List<String> specificAttributes;
}