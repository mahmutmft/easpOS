package com.mahmutmft.pos.stock;

import com.mahmutmft.pos.item.Item;

import java.math.BigDecimal;

public class Stock {

    private Item item;
    private int quantity;
    private BigDecimal stockPrice;

    public Stock(Item item, int quantity, BigDecimal stockPrice) {
        this.item = item;
        this.quantity = quantity;
        this.stockPrice = stockPrice;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getStockPrice() {
        return stockPrice;
    }

    public void setStockPrice(BigDecimal stockPrice) {
        this.stockPrice = stockPrice;
    }

    @Override
    public String toString() {
        return String.format("Name: %s, Quantity: %d, StockPrice: %s", item.getName(), quantity, stockPrice);
    }
}
