package simulation;

import exception.InvalidPriceException;
import model.Item;
import model.Order;
import service.OrderService;

import java.math.BigDecimal;

public class OrderSimulation {

    public static void main(String[] args) {

        OrderService orderService = new OrderService();

        printSection("CREATE ORDER");

        Order order = orderService.createOrder();
        System.out.println("Order created: #" + order.getId());

        try {
            printSection("CREATE ITEMS");

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

            System.out.println("Created: " + cocaCola.getName());
            System.out.println("Created: " + fanta.getName());


            printSection("ADD ITEMS");

            orderService.addItemToOrder(order, cocaCola, 21);
            orderService.addItemToOrder(order, fanta, 2);

            // Test duplicate item
            orderService.addItemToOrder(order, cocaCola, 21);

            printOrder(orderService, order);


            printSection("CURRENT TOTAL");

            System.out.println(
                    "Total: " + orderService.calculateTotal(order)
            );


            printSection("CHANGE QUANTITY");

            orderService.changeQuantity(order);

            System.out.println("\nOrder after quantity change:");
            printOrder(orderService, order);


            printSection("REMOVE ITEM");

            orderService.removeItem(order);

            System.out.println("\nOrder after removing item:");
            printOrder(orderService, order);


            printSection("FINAL ORDER");

            printOrder(orderService, order);

            BigDecimal finalTotal = orderService.calculateTotal(order);

            System.out.println("\nFinal total: " + finalTotal);

        } catch (InvalidPriceException e) {
            System.out.println("\nSIMULATION ERROR: " + e.getMessage());
        }

        printSection("SIMULATION FINISHED");
    }


    private static void printOrder(OrderService orderService, Order order) {
        orderService.listOrderItems(order);
    }


    private static void printSection(String title) {
        System.out.println("\n==============================");
        System.out.println(title);
        System.out.println("==============================");
    }
}