package simulation.order;

import Item.Item;
import order.Order;
import order.OrderService;
import stock.StockService;

import java.math.BigDecimal;

public class OrderStockSimulation {

    public static void main(String[] args) {

        StockService stockService = new StockService();
        OrderService orderService = new OrderService(stockService);

        Item cocaCola = new Item(
                1,
                "Coca-Cola",
                new BigDecimal("120"),
                "Coca-Cola",
                ""
        );

        Item fanta = new Item(
                2,
                "Fanta",
                new BigDecimal("100"),
                "Fanta",
                ""
        );

        stockService.addStock(
                cocaCola,
                50,
                new BigDecimal("56")
        );

        stockService.addStock(
                fanta,
                20,
                new BigDecimal("50")
        );

        System.out.println("=== STOCK BEFORE ORDER ===");
        stockService.listStocks();

        Order order = orderService.createOrder();

        orderService.addItemToOrder(order, cocaCola, 3);
        orderService.addItemToOrder(order, fanta, 2);

        System.out.println("\n=== ORDER ===");
        orderService.listOrderItems(order);

        orderService.confirmOrder(order);

        System.out.println("\n=== STOCK AFTER ORDER ===");
        stockService.listStocks();

        System.out.println("\n=== STOCK MOVEMENTS ===");
        stockService.getStockMovementService().listMovements();
    }
}