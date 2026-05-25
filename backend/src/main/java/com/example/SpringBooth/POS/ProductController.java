package com.example.SpringBooth.POS;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class ProductController {

	private final ProductRepository repository;

	public ProductController(ProductRepository repository) {
		this.repository = repository;
	}

	@GetMapping("/products")
	public List<Product> getProducts() {
		return repository.findAll();
	}

	@PostMapping("/products")
	public Product addProduct(@RequestBody Product product) {
		return repository.save(product);
	}

	@DeleteMapping("/products/{id}")
	public String deleteProduct(@PathVariable Integer id) {
		repository.deleteById(id);
		return "Product verwijderd";
	}
}