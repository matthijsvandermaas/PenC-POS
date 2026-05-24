package com.example.SpringBooth.POS;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class OrderController {

    private final OrderRepository repository;

    public OrderController(OrderRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/orders")
    public List<Order> getOrders() {
        return repository.findAll();
    }

    @PostMapping("/orders")
    public Order addOrder(@RequestBody Order order) {
        return repository.save(order);
    }

    @PutMapping("/orders/{id}/status")
    public String updateStatus(@PathVariable Integer id, @RequestParam String status) {
        return repository.findById(id).map(order -> {
            return "Status bijgewerkt naar: " + status;
        }).orElse("Order niet gevonden");
    }
}