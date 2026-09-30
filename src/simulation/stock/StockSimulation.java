package simulation.stock;

import Item.Item;
import stock.StockService;

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

        printSection("1. ADDING FIRST STOCK");

        System.out.println("Adding 20 Coca-Cola @ 50 MKD");
        stockService.addStock(cocaCola, 20, new BigDecimal("50"));

        System.out.println("Adding 10 Fanta @ 50 MKD");
        stockService.addStock(fanta, 10, new BigDecimal("50"));

        System.out.println("Adding 12 Tropical @ 50 MKD");
        stockService.addStock(tropical, 12, new BigDecimal("50"));

        printSection("2. CURRENT STOCK");
        stockService.listStocks();


        printSection("3. NEW DELIVERY / AVERAGE PRICE");

        System.out.println("Adding 30 Coca-Cola @ 60 MKD");
        stockService.addStock(cocaCola, 30, new BigDecimal("60"));

        System.out.println("\nExpected Coca-Cola:");
        System.out.println("Quantity: 50");
        System.out.println("Average price: 56.00 MKD");

        System.out.println("\nActual stock:");
        stockService.listStocks();


        printSection("4. REMOVING STOCK");

        System.out.println("Removing 5 Coca-Cola");
        stockService.removeStock(cocaCola, 5);

        System.out.println("Removing 3 Fanta");
        stockService.removeStock(fanta, 3);

        System.out.println("Removing 2 Tropical");
        stockService.removeStock(tropical, 2);

        System.out.println("\nExpected:");
        System.out.println("Coca-Cola: 45");
        System.out.println("Fanta: 7");
        System.out.println("Tropical: 10");

        System.out.println("\nActual:");
        stockService.listStocks();


        printSection("5. ADJUSTING STOCK");

        System.out.println("Physical count found only 4 Fanta");
        System.out.println("Adjusting Fanta from 7 to 4");

        stockService.adjustStock(fanta, 4);

        System.out.println("\nExpected Fanta quantity: 4");

        System.out.println("\nActual:");
        stockService.listStocks();


        printSection("6. LOW STOCK");

        System.out.println("Showing products with 5 or fewer pieces:");
        System.out.println("Expected: Fanta only");

        System.out.println("\nActual:");
        stockService.showLowStock(5);


        printSection("7. FINAL STOCK");

        stockService.listStocks();


        printSection("SIMULATION FINISHED");
    }

    private static void printSection(String title) {
        System.out.println("\n==============================");
        System.out.println(title);
        System.out.println("==============================");
    }
}