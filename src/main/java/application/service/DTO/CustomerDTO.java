package application.prog5j2tp2.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerDTO {
    private String customerType;
    private String name;
    private String identifier;
    private String dateString;
    private String locationInfo;
}