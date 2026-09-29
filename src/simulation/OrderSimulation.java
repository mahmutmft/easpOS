package simulation;

import exception.InvalidPriceException;
import model.Item;
import model.Order;
import service.OrderService;

import java.math.BigDecimal;

public class OrderSimulation {

    public static void main(String[] args) {

        OrderService orderService = new OrderService();

        // CREATE ORDER
        System.out.println("\n=== CREATE ORDER ===");

        Order order = orderService.createOrder();

        System.out.println("Order created");


        // CREATE ITEMS
        System.out.println("\n=== CREATE ITEMS ===");

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

            System.out.println("Items created");


            // ADD ITEMS
            System.out.println("\n=== ADD ITEMS ===");

            orderService.addItemToOrder(order, cocaCola, 21);
            orderService.addItemToOrder(order, fanta, 2);

            orderService.listOrderItems(order);


            // CALCULATE TOTAL
            System.out.println("\n=== TOTAL ===");

            BigDecimal total = orderService.calculateTotal(order);

            System.out.println("Total: " + total);


            // CHANGE QUANTITY
            System.out.println("\n=== CHANGE QUANTITY ===");

            orderService.changeQuantity(order);

            System.out.println("\nUpdated order:");
            orderService.listOrderItems(order);


            // REMOVE ITEM
            System.out.println("\n=== REMOVE ITEM ===");

            orderService.removeItem(order);

            System.out.println("\nUpdated order:");
            orderService.listOrderItems(order);


            // FINAL RESULT
            System.out.println("\n=== FINAL ORDER ===");

            orderService.listOrderItems(order);

            BigDecimal finalTotal = orderService.calculateTotal(order);

            System.out.println("Final total: " + finalTotal);

        } catch (InvalidPriceException e) {
            System.out.println("ERROR: " + e.getMessage());
        }

        System.out.println("\n=== SIMULATION FINISHED ===");
    }
}