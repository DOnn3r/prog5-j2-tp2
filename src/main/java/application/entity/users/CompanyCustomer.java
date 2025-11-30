package application.prog5j2tp2.entity.users;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class CompanyCustomer extends Customer {
    private final String legalRepresentative;
    private final LocalDate creationDate;

    public CompanyCustomer(int id, String name, String legalRepresentative, LocalDate creationDate) {
        super(id, name);
        this.legalRepresentative = legalRepresentative;
        this.creationDate = creationDate;
    }
}
