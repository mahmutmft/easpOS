package com.mahmutmft.pos.item;

import java.math.BigDecimal;

public class Item {
    private int id;
    private String name;
    private BigDecimal price;
    private String description;
    private String imagePath;

    public Item(int id, String name, BigDecimal price, String description, String imagePath) {
        checkPrice(price);
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.imagePath = imagePath;
    }

    private void checkPrice(BigDecimal price){
        if (price.compareTo(BigDecimal.ZERO) <= 0){
           throw new InvalidPriceException("Price is not valid");
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    @Override
    public String toString() {
       return String.format("id: %s - %s %s" ,id, name, price);
    }
}
