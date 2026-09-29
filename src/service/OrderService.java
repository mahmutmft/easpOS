package service;

import model.Item;
import model.Order;
import model.OrderItem;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class OrderService {
    private int id = 1;
    Scanner scanner = new Scanner(System.in);

    public Order createOrder() {
        Order order = new Order(id);
        id++;
        return order;
    }

    public void addItemToOrder(Order order, Item item, int quantity) {
        OrderItem orderItem = new OrderItem(item, quantity);
        order.getOrderItems().add(orderItem);
    }

    public void listOrderItems(Order order) {
        for (OrderItem order1 : order.getOrderItems()) {
            System.out.println(order1);
        }
    }

    public BigDecimal calculateTotal(Order order) {
        BigDecimal calculated = new BigDecimal("0");
        for (OrderItem order1 : order.getOrderItems()) {
            calculated = calculated.add(BigDecimal.valueOf(order1.getQuantity()).multiply(order1.getItem().getPrice()));
        }
        return calculated;
    }

    public void changeQuantity(Order order) {
        System.out.println("What id you want to change the quantity");
        int number = scanner.nextInt();
        System.out.println("What should it be");
        int newQuantity = scanner.nextInt();
        if (newQuantity > 0) {
            for (OrderItem item : order.getOrderItems()) {
                Item item1 = item.getItem();
                if (item1.getId() == number) {
                    item.setQuantity(newQuantity);
                }
            }
        } else {
            System.out.println("Enter positive number for quantity");
        }

    }

    public void removeItem(Order order) {
        System.out.println("What item should be removed");
        int numberItem = scanner.nextInt();
        order.getOrderItems().removeIf(
                orderItem -> orderItem.getItem().getId() == numberItem
        );
    }
}
