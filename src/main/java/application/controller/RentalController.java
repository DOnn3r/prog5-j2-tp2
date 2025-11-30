package application.prog5j2tp2.controller;

import application.prog5j2tp2.dto.RentalDTO;
import application.prog5j2tp2.entity.Rental;
import application.prog5j2tp2.service.RentalService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/rentals")
public class RentalController {

    private final RentalService rentalService;

    @PostMapping
    public ResponseEntity<Rental> createRental(@RequestBody RentalDTO dto) {
        Rental rental = rentalService.createNewRental(
                dto.getCustomerId(),
                dto.getItemId(),
                dto.getStartDate(),
                dto.getEndDate()
        );
        return ResponseEntity.ok(rental);
    }

    @PostMapping("/{id}/return")
    public ResponseEntity<Rental> returnRental(@PathVariable int id) {
        return ResponseEntity.ok(rentalService.returnRental(id));
    }
}
