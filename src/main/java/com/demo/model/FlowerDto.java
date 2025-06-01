package com.demo.model;

import java.math.BigDecimal;

public class FlowerDto {
    private Long id;
    private String name;
    private String color;

    private int price;
    private int quantity;
    private String status;
    private boolean archived;

    public FlowerDto() {
    }

    public FlowerDto(Long id, String name, String color, int price,
                     int quantity, String status, boolean archived) {
        this.id = id;
        this.name = name;
        this.color = color;
        this.price = price;
        this.quantity = quantity;
        this.status = status;
        this.archived = archived;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isArchived() {
        return archived;
    }

    public void setArchived(boolean archived) {
        this.archived = archived;
    }
}