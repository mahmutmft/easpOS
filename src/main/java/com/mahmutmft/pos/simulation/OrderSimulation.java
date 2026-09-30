package com.mahmutmft.pos.simulation;

import com.mahmutmft.pos.item.InvalidPriceException;
import com.mahmutmft.pos.item.Item;
import com.mahmutmft.pos.order.Order;
import com.mahmutmft.pos.order.OrderService;
import com.mahmutmft.pos.stock.StockService;

import java.math.BigDecimal;

public class OrderSimulation {

    public static void main(String[] args) {

        StockService stockService = new StockService();
        OrderService orderService = new OrderService(stockService);

        System.out.println("--- Creating Order ---");
        Order order = orderService.createOrder();
        System.out.println("Order Created");

        System.out.println("------");
        System.out.println("--- Creating Items ---");

        try {
            Item cocaCola = new Item(
                    1,
                    "CocaCola",
                    new BigDecimal("100"),
                    "Cold drink",
                    "test"
            );

            Item fanta = new Item(
                    2,
                    "Fanta",
                    new BigDecimal("80"),
                    "Orange drink",
                    "test"
            );

            System.out.println("Items Created");

            System.out.println("------");

            orderService.addItemToOrder(order, cocaCola, 21);
            orderService.addItemToOrder(order, fanta, 2);

            System.out.println("Items Added");

        } catch (InvalidPriceException e) {
            System.out.println("ERROR: " + e.getMessage());
        }

        System.out.println("------");
        System.out.println("--- Order Items ---");
        orderService.listOrderItems(order);

        System.out.println("------");
        System.out.println("--- Calculating Total ---");

        BigDecimal total = orderService.calculateTotal(order);

        System.out.println("Total: " + total);

        System.out.println("------");
        System.out.println("Trying change quantity");
        orderService.changeQuantity(order);
        System.out.println("--- Order Items ---");
        orderService.listOrderItems(order);
        System.out.println("Trying to remove an item");
        orderService.removeItem(order);
        System.out.println("--- Simulation Finished ---");
    }
}
