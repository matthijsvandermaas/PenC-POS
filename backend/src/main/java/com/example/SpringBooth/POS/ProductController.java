package com.example.SpringBooth.POS;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class ProductController {

	@GetMapping("/products")
	public List<Product> getProducts() {
		return List.of(
				new Product(1, "Pizza", 8.50, "Eten"),
				new Product(2, "Cola", 2.50, "Drinken"),
				new Product(3, "Bier", 3.00, "Drinken"));
	}
}