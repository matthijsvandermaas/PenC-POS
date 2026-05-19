package com.example.SpringBooth.POS;

import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
public class OrderController {

    private List<Order> orders = new ArrayList<>();

    @GetMapping("/orders")
    public List<Order> getOrders() {
        return orders;
    }

    @PostMapping("/orders")
    public Order addOrder(@RequestBody Order order) {
        orders.add(order);
        return order;
    }

    @PutMapping("/orders/{id}/status")
    public String updateStatus(@PathVariable int id, @RequestParam String status) {
        for (Order o : orders) {
            if (o.getId() == id) {
                // status update komt later met database
                return "Status bijgewerkt naar: " + status;
            }
        }
        return "Order niet gevonden";
    }
}
