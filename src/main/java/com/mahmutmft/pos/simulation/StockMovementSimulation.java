package com.mahmutmft.pos.simulation;

import com.mahmutmft.pos.item.Item;
import com.mahmutmft.pos.stock.StockMovementService;
import com.mahmutmft.pos.stock.StockMovementType;

import java.math.BigDecimal;

public class StockMovementSimulation {

    public static void main(String[] args) {

        StockMovementService movementService = new StockMovementService();

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

        movementService.recordMovement(cocaCola, 20, StockMovementType.DELIVERY);
        movementService.recordMovement(fanta, 10, StockMovementType.DELIVERY);

        movementService.recordMovement(cocaCola, -3, StockMovementType.SALE);
        movementService.recordMovement(fanta, -2, StockMovementType.SALE);

        movementService.recordMovement(cocaCola, -1, StockMovementType.ADJUSTMENT);

        System.out.println("=== ALL MOVEMENTS ===");
        movementService.listMovements();

        System.out.println("\n=== COCA-COLA MOVEMENTS ===");
        movementService.getMovementsByItem(cocaCola);

        System.out.println("\n=== SALES ===");
        movementService.getMovementsByType(StockMovementType.SALE);

        System.out.println("\n=== MOVEMENTS THIS YEAR ===");
        movementService.getMovementsByYear(2026);

        System.out.println("\n=== MOVEMENTS SEPTEMBER 2026 ===");
        movementService.getMovementsByMonth(9, 2026);
    }
}
