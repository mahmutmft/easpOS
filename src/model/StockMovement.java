package model;

import model.ENUMS.StockMovementType;

import java.time.LocalDateTime;

public class StockMovement {
    private Item item;
    private int quantity;
    private StockMovementType type;
    private LocalDateTime dateTime;


    public StockMovement(Item item, int quantity, StockMovementType type) {
        this.item = item;
        this.quantity = quantity;
        this.type = type;
        this.dateTime = LocalDateTime.now();
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

    public StockMovementType getType() {
        return type;
    }

    public void setType(StockMovementType type) {
        this.type = type;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return String.format(
                "%s | %-15s | Quantity: %+d | %s",
                type,
                item.getName(),
                quantity,
                dateTime
        );
    }
}
