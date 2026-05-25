package com.example.SpringBooth.POS;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class CategoryController {

    private final CategoryRepository repository;

    public CategoryController(CategoryRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/categories")
    public List<Category> getCategories() {
        return repository.findAll();
    }

    @PostMapping("/categories")
    public Category addCategory(@RequestBody Category category) {
        return repository.save(category);
    }

    @DeleteMapping("/categories/{id}")
    public String deleteCategory(@PathVariable Integer id) {
        repository.deleteById(id);
        return "Categorie verwijderd";
    }
}