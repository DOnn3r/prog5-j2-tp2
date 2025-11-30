package application.prog5j2tp2.entity.users;

import lombok.Getter;

@Getter
public class AssociationCustomer extends Customer{
    private final String associationName;
    private final String headquartersAddress;

    public AssociationCustomer(int id, String name, String associationName, String headquartersAddress) {
        super(id, name);
        this.associationName = associationName;
        this.headquartersAddress = headquartersAddress;
    }
}
