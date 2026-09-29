package simulation;

import model.Item;
import service.StockService;

import java.math.BigDecimal;

public class StockSimulation {

    public static void main(String[] args) {

        StockService stockService = new StockService();

        Item cocaCola = new Item(
                1,
                "Coca-Cola",
                new BigDecimal("120"),
                "Cold Drink",
                "path"
        );

        Item fanta = new Item(
                2,
                "Fanta",
                new BigDecimal("100"),
                "Cold Drink",
                "path"
        );

        Item tropical = new Item(
                3,
                "Tropical",
                new BigDecimal("80"),
                "Cold Drink",
                "path"
        );

        printSection("ADDING FIRST STOCK");

        System.out.println("Adding 20 Coca-Cola @ 50 MKD");
        stockService.addStock(cocaCola, 20, new BigDecimal("50"));

        System.out.println("Adding 10 Fanta @ 50 MKD");
        stockService.addStock(fanta, 10, new BigDecimal("50"));

        System.out.println("Adding 12 Tropical @ 50 MKD");
        stockService.addStock(tropical, 12, new BigDecimal("50"));

        printSection("CURRENT STOCK");
        stockService.listStocks();

        printSection("NEW DELIVERY");

        System.out.println("Adding 30 Coca-Cola @ 60 MKD");
        stockService.addStock(cocaCola, 30, new BigDecimal("60"));

        printSection("STOCK AFTER DELIVERY");
        stockService.listStocks();

        printSection("REMOVING STOCK");

        System.out.println("Removing 5 Coca-Cola");
        stockService.removeStock(cocaCola, 5);

        System.out.println("Removing 3 Fanta");
        stockService.removeStock(fanta, 3);

        System.out.println("Removing 2 Tropical");
        stockService.removeStock(tropical, 2);

        printSection("FINAL STOCK");
        stockService.listStocks();

        printSection("SIMULATION FINISHED");
    }

    private static void printSection(String title) {
        System.out.println("\n==============================");
        System.out.println(title);
        System.out.println("==============================");
    }
}