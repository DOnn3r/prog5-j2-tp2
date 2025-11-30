package application.prog5j2tp2.manager;

import application.prog5j2tp2.entity.Rental;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@Slf4j
public class RentalDAO {
    private final List<Rental> rentals = new ArrayList<>();
    private int nextId = 1000;

    public Rental save(Rental rental) {
        rentals.add(rental);
        log.info("Rental contract N°{} recorded in memory.", rental.getId());
        return rental;
    }

    public Optional<Rental> findById(int id) {
        log.debug("Searching for rental contract with ID: {}", id);
        return rentals.stream()
                .filter(r -> r.getId() == id)
                .findFirst();
    }

    public List<Rental> findAll() {
        log.debug("Extracting {} rental contracts.", rentals.size());
        return List.copyOf(rentals);
    }

    public int getNextId() {
        log.debug("Generating new rental ID: {}", nextId);
        return nextId++;
    }
}