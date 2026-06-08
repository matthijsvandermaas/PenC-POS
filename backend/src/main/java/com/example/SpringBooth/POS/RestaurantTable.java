package com.example.SpringBooth.POS;

import jakarta.persistence.*;

@Entity
@Table(name = "tables")
public class RestaurantTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer number;
    private String status;

    public RestaurantTable() {
    }

    public RestaurantTable(Integer id, Integer number, String status) {
        this.id = id;
        this.number = number;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public Integer getNumber() {
        return number;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}