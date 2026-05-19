package com.example.SpringBooth.POS;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class ProductController {
	@GetMapping("/products")
	public List<String> getProducts() {
		return List.of("Pizza", "Cola", "Bier");
	}
}