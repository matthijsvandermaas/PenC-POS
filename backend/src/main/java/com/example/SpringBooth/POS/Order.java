package com.example.SpringBooth.POS;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToMany(cascade = CascadeType.ALL)
    private List<OrderItem> items;

    private double total;
    private String status;
    private Integer tableNumber;

    public Order() {
    }

    public Order(Integer id, List<OrderItem> items, double total, String status, Integer tableNumber) {
        this.id = id;
        this.items = items;
        this.total = total;
        this.status = status;
        this.tableNumber = tableNumber;
    }

    public Integer getId() {
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

    public Integer getTableNumber() {
        return tableNumber;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}