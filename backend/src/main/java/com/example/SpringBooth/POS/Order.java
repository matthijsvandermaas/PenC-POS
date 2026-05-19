package com.example.SpringBooth.POS;

import java.util.List;

public class Order {

    private int id;
    private List<OrderItem> items;
    private double total;
    private String status;

    public Order(int id, List<OrderItem> items, double total, String status) {
        this.id = id;
        this.items = items;
        this.total = total;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public double getTotal() {
        return total;
    }

    public String getStatus() {
        return status;
    }
}
