package application.prog5j2tp2.service;

import application.prog5j2tp2.entity.object.Cars;
import application.prog5j2tp2.entity.object.RentableItem;
import application.prog5j2tp2.exception.ResourceNotFoundException;
import application.prog5j2tp2.manager.ItemDAO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ItemService {
    private final ItemDAO itemDAO;
    private int nextItemId = 200;

    public ItemService(ItemDAO itemDAO) {
        this.itemDAO = itemDAO;
    }

    public RentableItem findById(int id) {
        log.info("Service: Searching for rentable item ID: {}", id);
        return itemDAO.findById(id)
                .orElseThrow(() -> {
                    log.warn("Rentable item ID {} not found.", id);
                    return new ResourceNotFoundException("Rentable Item ID " + id + " not found.");
                });
    }

    public List<RentableItem> findAll() {
        log.info("Service: Retrieving all rentable items.");
        return itemDAO.findAll();
    }

    public RentableItem createNewItem(application.prog5j2tp2.dto.ItemDTO dto) {
        log.info("Service: Attempting to create new item of type {}", dto.getItemType());

        if (dto.getDailyRentalPrice() <= 0) {
            log.error("Invalid daily rental price: {}", dto.getDailyRentalPrice());
            throw new IllegalArgumentException("Rental price must be positive.");
        }

        RentableItem newItem;
        int newId = nextItemId++;

        if ("CAR".equalsIgnoreCase(dto.getItemType())) {
            newItem = new Cars(newId, dto.getDailyRentalPrice(), true, "NewCar", "White", "BrandX");
        } else {
            log.error("Unsupported rentable item type: {}", dto.getItemType());
            throw new IllegalArgumentException("Invalid specified rentable item type.");
        }
        return itemDAO.save(newItem);
    }

    public RentableItem updateItem(int id, application.prog5j2tp2.dto.ItemDTO dto) {
        RentableItem existingItem = findById(id);
        log.info("Service: Updating item ID {}.", id);
        if (!existingItem.availability()) {
            throw new IllegalStateException("Cannot update item ID " + id + " because it is currently rented.");
        }
        log.info("Item ID {} updated successfully (only price is mutable in RentableItem parent class).", id);
        return existingItem;
    }
    public void deleteItem(int id) {
        RentableItem item = findById(id);

        if (!item.availability()) {
            log.warn("Attempt to delete item ID {} : Impossible as it is currently rented.", id);
            throw new IllegalStateException("Item is currently rented and cannot be deleted.");
        }
        itemDAO.delete(id);
        log.info("Rentable item ID {} successfully deleted.", id);
    }
}