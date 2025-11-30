package application.prog5j2tp2.service;

import application.prog5j2tp2.entity.Rental;
import application.prog5j2tp2.entity.object.RentableItem;
import application.prog5j2tp2.entity.users.Customer;
import application.prog5j2tp2.exception.ItemNotAvailableException;
import application.prog5j2tp2.exception.ResourceNotFoundException;
import application.prog5j2tp2.manager.CustomerDAO;
import application.prog5j2tp2.manager.ItemDAO;
import application.prog5j2tp2.manager.RentalDAO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@AllArgsConstructor
@Slf4j
@Service
public class RentalService {
    private final RentalDAO rentalDAO;
    private final ItemDAO itemDAO;
    private final CustomerDAO customerDAO;

    public Rental createNewRental(int customerId, int itemId, LocalDate startDate, LocalDate endDate) {

        log.info("Starting rental creation for customer ID {} with item ID {}.", customerId, itemId);

        if (startDate.isAfter(endDate)) {
            log.error("Validation failed: Start date ({}) is after end date ({}).", startDate, endDate);
            throw new IllegalArgumentException("Start date cannot be after end date.");
        }

        Customer customer = customerDAO.getById(customerId);

        RentableItem itemToRent = itemDAO.findById(itemId)
                .orElseThrow(() -> new ResourceNotFoundException("Rentable Item ID " + itemId + " not found."));

        if (!itemToRent.availability()) {
            log.error("Item ID {} is not available.", itemId);
            throw new ItemNotAvailableException("Item ID " + itemId + " is not available.");
        }

        int newId = rentalDAO.getNextId();

        Rental finalRental = new Rental(newId, itemToRent, customer, startDate, endDate);
        double total = finalRental.calculateTotalRentalPrice();

        itemDAO.updateAvailability(itemToRent.getId(), false);
        Rental savedRental = rentalDAO.save(finalRental);

        log.info("Contract N°{} successfully created for total amount {}.", newId, total);

        return savedRental;
    }

    public Rental returnRental(int rentalId) {
        log.info("Processing return for contract N°{}.", rentalId);

        Rental rental = rentalDAO.findById(rentalId)
                .orElseThrow(() -> new ResourceNotFoundException("Rental contract ID " + rentalId + " not found."));

        if (LocalDate.now().isAfter(rental.getEndDate())) {
            log.warn("Late return for contract N°{}. Penalties may apply.", rentalId);

        }
        itemDAO.updateAvailability(rental.getRentableItem().getId(), true);

        log.info("Contract N°{} completed. Item ID {} is available again.", rentalId, rental.getRentableItem().getId());
        return rental;
    }

    public RentalDAO getRentalManager() {
        return rentalDAO;
    }
}