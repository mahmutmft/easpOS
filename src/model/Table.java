package model;

import java.util.ArrayList;

public class Table {
    private int id;
    private Waiter waiter;
    private TableStatus status;
    private String name;
    private ArrayList<Order> orders;

    public Table(int id) {
        this.id = id;
        this.orders = new ArrayList<>();
        this.status = TableStatus.AVAILABLE;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Waiter getWaiter() {
        return waiter;
    }

    public void setWaiter(Waiter waiter) {
        this.waiter = waiter;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TableStatus getStatus() {
        return status;
    }

    public void setStatus(TableStatus status) {
        this.status = status;
    }

    public ArrayList<Order> getOrders() {
        return orders;
    }

    public void setOrders(ArrayList<Order> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        if (waiter == null) {
            return String.format("Table ID: %d - Status: %s", id, status);
        }

        return String.format(
                "Table ID: %d - Status: %s - Waiter: %s",
                id,
                status,
                waiter.getName()
        );
    }
}
