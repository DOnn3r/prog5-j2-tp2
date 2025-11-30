package application.prog5j2tp2.service;

import application.prog5j2tp2.entity.users.*;
import application.prog5j2tp2.manager.CustomerDAO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

@Service
@Slf4j
public class CustomerService {
    private final CustomerDAO customerDAO;
    private int nextCustomerId = 4;

    public CustomerService(CustomerDAO customerDAO) {
        this.customerDAO = customerDAO;
    }

    public Customer findById(int id) {
        return customerDAO.getById(id);
    }

    public List<Customer> findAll() {
        log.info("Service: Retrieving all customers.");
        return List.of(customerDAO.getById(1), customerDAO.getById(2));
    }

    public Customer createNewCustomer(application.prog5j2tp2.dto.CustomerDTO dto) {
        log.info("Service: Attempting to create new customer of type {}", dto.getCustomerType());

        if (dto.getName() == null || dto.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Customer name is required.");
        }
        Customer newCustomer;
        int newId = nextCustomerId++;
        try {
            switch (dto.getCustomerType().toUpperCase()) {
                case "PERSONAL":
                    if (dto.getIdentifier() == null) throw new IllegalArgumentException("National ID is required.");
                    LocalDate birthDate = LocalDate.parse(dto.getDateString(), DateTimeFormatter.ISO_LOCAL_DATE);
                    newCustomer = new PersonalCustomer(newId, dto.getName(), dto.getIdentifier(), birthDate);
                    break;
                case "COMPANY":
                    if (dto.getIdentifier() == null) throw new IllegalArgumentException("Legal representative is required.");
                    LocalDate creationDate = LocalDate.parse(dto.getDateString(), DateTimeFormatter.ISO_LOCAL_DATE);
                    newCustomer = new CompanyCustomer(newId, dto.getName(), dto.getIdentifier(), creationDate);
                    break;
                case "ASSOCIATION":
                    if (dto.getLocationInfo() == null) throw new IllegalArgumentException("Association name is required.");
                    newCustomer = new AssociationCustomer(newId, dto.getName(), dto.getLocationInfo(), "N/A");
                    break;
                default:
                    throw new IllegalArgumentException("Invalid customer type.");
            }
        } catch (DateTimeParseException e) {
            log.error("Date format error for customer: {}", dto.getDateString());
            throw new IllegalArgumentException("Invalid date format. Use YYYY-MM-DD.");
        }

        log.info("New customer ID {} of type {} created successfully.", newId, dto.getCustomerType());
        customerDAO.save(newCustomer);
        return newCustomer;
    }

    public Customer updateCustomer(int id, application.prog5j2tp2.dto.CustomerDTO dto) {
        Customer existingCustomer = findById(id);
        log.info("Service: Updating customer ID {}.", id);
        String newName = (dto.getName() != null && !dto.getName().trim().isEmpty()) ? dto.getName() : existingCustomer.getName();
        Customer updatedCustomer = new PersonalCustomer(
                existingCustomer.getId(),
                newName,
                ((PersonalCustomer) existingCustomer).getNationalId(),
                ((PersonalCustomer) existingCustomer).getBirthDate()
        );
        return customerDAO.save(updatedCustomer);
    }

    public void deleteCustomer(int id) {
        log.info("Service: Attempting to delete customer ID {}.", id);
        customerDAO.delete(id);
        log.info("Customer ID {} successfully deleted.", id);
    }

}