package application.prog5j2tp2.controller;

import application.prog5j2tp2.dto.CustomerDTO;
import application.prog5j2tp2.entity.users.Customer;
import application.prog5j2tp2.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getById(@PathVariable int id) {
        return ResponseEntity.ok(customerService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<Customer>> getAll() {
        return ResponseEntity.ok(customerService.findAll());
    }

    @PostMapping
    public ResponseEntity<Customer> createCustomer(@RequestBody CustomerDTO dto) {
        Customer created = customerService.createNewCustomer(dto);
        return ResponseEntity.ok(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(
            @PathVariable int id,
            @RequestBody CustomerDTO dto
    ) {
        Customer updated = customerService.updateCustomer(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable int id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }
}
