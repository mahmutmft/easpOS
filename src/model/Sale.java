package model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Sale {
    private int id;
    private int waiterId;
    private int tableId;
    private ArrayList<Order> orderList;
    private BigDecimal totalPrice;
    private LocalDateTime dateTime;

    public Sale(int id, int waiterId, int tableId, ArrayList<Order> orderList, BigDecimal totalPrice) {
        this.id = id;
        this.waiterId = waiterId;
        this.tableId = tableId;
        this.orderList = orderList;
        this.totalPrice = totalPrice;
        this.dateTime = LocalDateTime.now();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getWaiterId() {
        return waiterId;
    }

    public void setWaiterId(int waiterId) {
        this.waiterId = waiterId;
    }

    public int getTableId() {
        return tableId;
    }

    public void setTableId(int tableId) {
        this.tableId = tableId;
    }

    public ArrayList<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(ArrayList<Order> orderList) {
        this.orderList = orderList;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}
