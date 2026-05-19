package com.example.SpringBooth.POS;

import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductController {

	private List<Product> products = new ArrayList<>(List.of(
			new Product(1, "Pizza", 8.50, "Eten"),
			new Product(2, "Cola", 2.50, "Drinken"),
			new Product(3, "Bier", 3.00, "Drinken")));

	@GetMapping("/products")
	public List<Product> getProducts() {
		return products;
	}

	@PostMapping("/products")
	public Product addProduct(@RequestBody Product product) {
		products.add(product);
		return product;
	}

	@DeleteMapping("/products/{id}")
	public String deleteProduct(@PathVariable int id) {
		products.removeIf(p -> p.getId() == id);
		return "Product verwijderd";
	}
}