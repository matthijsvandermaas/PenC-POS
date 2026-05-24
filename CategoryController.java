package com.example.SpringBooth.POS;

import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
public class CategoryController {

    private List<Category> categories = new ArrayList<>(List.of(
            new Category(1, "Warme dranken"),
            new Category(2, "Koude dranken"),
            new Category(3, "Lunch"),
            new Category(4, "Diner"),
            new Category(5, "Snacks")));

    @GetMapping("/categories")
    public List<Category> getCategories() {
        return categories;
    }

    @PostMapping("/categories")
    public Category addCategory(@RequestBody Category category) {
        categories.add(category);
        return category;
    }

    @DeleteMapping("/categories/{id}")
    public String deleteCategory(@PathVariable int id) {
        categories.removeIf(c -> c.getId() == id);
        return "Categorie verwijderd";
    }
}
