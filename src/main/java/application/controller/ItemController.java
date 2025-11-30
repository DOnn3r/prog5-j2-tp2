package application.prog5j2tp2.controller;

import application.prog5j2tp2.dto.ItemDTO;
import application.prog5j2tp2.entity.object.RentableItem;
import application.prog5j2tp2.service.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/items")
public class ItemController {

    private final ItemService itemService;

    @GetMapping("/{id}")
    public ResponseEntity<RentableItem> getById(@PathVariable int id) {
        return ResponseEntity.ok(itemService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<RentableItem>> getAll() {
        return ResponseEntity.ok(itemService.findAll());
    }

    @PostMapping
    public ResponseEntity<RentableItem> createItem(@RequestBody ItemDTO dto) {
        return ResponseEntity.ok(itemService.createNewItem(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RentableItem> updateItem(
            @PathVariable int id,
            @RequestBody ItemDTO dto
    ) {
        return ResponseEntity.ok(itemService.updateItem(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable int id) {
        itemService.deleteItem(id);
        return ResponseEntity.noContent().build();
    }
}
