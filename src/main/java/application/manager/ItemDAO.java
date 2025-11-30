package application.prog5j2tp2.manager;

import application.prog5j2tp2.entity.object.*;
import application.prog5j2tp2.exception.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j; // 👈 Import de Lombok Slf4j
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@Slf4j
public class ItemDAO {
    private final List<RentableItem> inventory = new ArrayList<>();

    public ItemDAO() {
        inventory.add(new Cars(101, 50.0, true, "Clio V", "Bleu", "Renault"));
        inventory.add(new House(102, 250.0, true, "123 Main St", "Paris", "France"));
        inventory.add(new Cars(103, 150.0, true, "Model 3", "Noir", "Tesla"));
        inventory.add(new Bike(104, 15.0, true, "Explorer", "Mountain", "Vert"));
        inventory.add(new Pc(105, 10.0, true, "Latitude", "E5470", "Dell"));
        inventory.add(new Money(106, 0.05, true, 0.1));

        log.info("Inventory initialized with {} rentable items.", inventory.size());
    }

    public Optional<RentableItem> findById(int id) {
        log.debug("Searching for rentable item ID: {}", id);
        return inventory.stream().filter(item -> item.getId() == id).findFirst();
    }

    public List<RentableItem> findAll() {
        return List.copyOf(inventory);
    }

    public void updateAvailability(int itemId, boolean available) {
        RentableItem item = findById(itemId)
                .orElseThrow(() -> new ResourceNotFoundException("Rentable Item ID " + itemId + " not found."));

        item.setAvailable(available);
        log.info("Availability for item ID {} updated to: {}", itemId, available);
    }

    public RentableItem save(RentableItem item) {
        Optional<RentableItem> existingItem = findById(item.getId());

        if (existingItem.isPresent()) {
            int index = inventory.indexOf(existingItem.get());
            inventory.set(index, item);
            log.info("Rentable item ID {} updated in inventory.", item.getId());
        } else {
            inventory.add(item);
            log.info("New rentable item ID {} saved to inventory.", item.getId());
        }
        return item;
    }

    public void delete(int itemId) {
        if (inventory.removeIf(item -> item.getId() == itemId)) {
            log.info("Rentable item ID {} deleted from inventory.", itemId);
        } else {
            throw new ResourceNotFoundException("Rentable Item ID " + itemId + " not found for deletion.");
        }
    }
}