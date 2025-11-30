package application.prog5j2tp2.entity.users;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class PersonalCustomer extends Customer {
    private final String nationalId;
    private final LocalDate birthDate;

    public PersonalCustomer(int id, String name, String nationalId, LocalDate birthDate) {
        super(id, name);
        this.nationalId = nationalId;
        this.birthDate = birthDate;
    }
}
