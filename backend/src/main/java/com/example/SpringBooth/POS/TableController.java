package com.example.SpringBooth.POS;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class TableController {

    private final TableRepository repository;

    public TableController(TableRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/tables")
    public List<RestaurantTable> getTables() {
        return repository.findAll();
    }

    @PostMapping("/tables")
    public RestaurantTable addTable(@RequestBody RestaurantTable table) {
        return repository.save(table);
    }

    @PutMapping("/tables/{id}/status")
    public RestaurantTable updateStatus(@PathVariable Integer id, @RequestParam String status) {
        return repository.findById(id).map(table -> {
            table.setStatus(status);
            return repository.save(table);
        }).orElseThrow();
    }

    @DeleteMapping("/tables/{id}")
    public String deleteTable(@PathVariable Integer id) {
        repository.deleteById(id);
        return "Tafel verwijderd";
    }
}
