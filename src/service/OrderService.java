package service;

import model.Item;
import model.Order;
import model.OrderItem;
import java.math.BigDecimal;

public class OrderService {
    private int id = 1;

    public Order createOrder() {
        Order order = new Order(id);
        id++;
        return order;
    }

    public void addItemToOrder(Order order, Item item, int quantity){
        OrderItem orderItem = new OrderItem(item, quantity);
        order.getOrderItems().add(orderItem);
    }

    public void listOrderItems(Order order){
        for (OrderItem order1: order.getOrderItems()){
            System.out.println(order1);
        }
    }

    public BigDecimal calculateTotal(Order order){
        BigDecimal calculated = new BigDecimal("0");
        for (OrderItem order1 : order.getOrderItems()){
            calculated = calculated.add(BigDecimal.valueOf(order1.getQuantity()).multiply(order1.getItem().getPrice()));
        }
        return calculated;
    }
}
