package com.example.SpringBooth.POS;

import jakarta.persistence.*;

@Entity
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String destination;

    public Category() {
    }

    public Category(Integer id, String name, String destination) {
        this.id = id;
        this.name = name;
        this.destination = destination;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDestination() {
        return destination;
    }
}