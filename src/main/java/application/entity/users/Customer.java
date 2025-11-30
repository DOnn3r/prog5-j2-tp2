package application.prog5j2tp2.entity.users;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public abstract class Customer implements RentalCustomer{
    private int id;
    private String name;

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }
}
