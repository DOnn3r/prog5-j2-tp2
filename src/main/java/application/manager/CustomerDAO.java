package application.prog5j2tp2.manager;

import application.prog5j2tp2.entity.users.*;
import application.prog5j2tp2.exception.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j; // 👈 Import de Lombok Slf4j
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Repository
@Slf4j
public class CustomerDAO {
    private final List<Customer> customers = new ArrayList<>();

    public CustomerDAO() {
        customers.add(new PersonalCustomer(1, "Alice Dupont", "A12345678", LocalDate.of(1985, 5, 20)));
        customers.add(new CompanyCustomer(2, "TechCorp SARL", "Marc Dubois", LocalDate.of(2010, 1, 15)));
        customers.add(new AssociationCustomer(3, "Croix Verte", "Association pour l'Environnement", "10 Rue de la Nature, 75000 Paris"));

        log.info("List of {} customers initialized (3 different types).", customers.size());
    }

    public Optional<Customer> findById(int id) {
        log.debug("Searching for customer ID: {}", id);
        return customers.stream().filter(c -> c.getId() == id).findFirst();
    }

    public Customer getById(int id) {
        return findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer ID " + id + " not found."));
    }

    public Customer save(Customer customer) {
        Optional<Customer> existingCustomer = findById(customer.getId());

        if (existingCustomer.isPresent()) {
            int index = customers.indexOf(existingCustomer.get());
            customers.set(index, customer);
            log.info("Customer ID {} updated.", customer.getId());
        } else {
            customers.add(customer);
            log.info("New customer ID {} saved.", customer.getId());
        }
        return customer;
    }

    public void delete(int customerId) {
        if (customers.removeIf(customer -> customer.getId() == customerId)) {
            log.info("Customer ID {} deleted.", customerId);
        } else {
            throw new ResourceNotFoundException("Customer ID " + customerId + " not found for deletion.");
        }
    }
}