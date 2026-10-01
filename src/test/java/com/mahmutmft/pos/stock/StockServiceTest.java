package com.mahmutmft.pos.stock;

import com.mahmutmft.pos.item.Item;
import com.mahmutmft.pos.order.Order;
import com.mahmutmft.pos.order.OrderService;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class StockServiceTest {

    @Test
    void addsNewStockAndRecordsDelivery() {
        StockService service = new StockService();
        Item item = item(1, "Cola");

        service.addStock(item, 20, new BigDecimal("50"));

        assertEquals(1, service.stockList.size());
        assertEquals(20, service.stockList.getFirst().getQuantity());
        assertEquals(new BigDecimal("50"), service.stockList.getFirst().getStockPrice());
        assertEquals(1, service.stockMovementService.movements.size());
        assertEquals(StockMovementType.DELIVERY, service.stockMovementService.movements.getFirst().getType());
        assertEquals(20, service.stockMovementService.movements.getFirst().getQuantity());
    }

    @Test
    void combinesDeliveriesUsingWeightedAveragePrice() {
        StockService service = new StockService();
        Item item = item(1, "Cola");

        service.addStock(item, 20, new BigDecimal("50"));
        service.addStock(item, 30, new BigDecimal("60"));

        Stock stock = service.stockList.getFirst();
        assertEquals(50, stock.getQuantity());
        assertEquals(new BigDecimal("56.00"), stock.getStockPrice());
    }

    @Test
    void removesAndAdjustsStockWithMovementHistory() {
        StockService service = new StockService();
        Item item = item(1, "Cola");
        service.addStock(item, 20, new BigDecimal("50"));

        service.removeStock(item, 3);
        service.adjustStock(item, 15);

        assertEquals(15, service.stockList.getFirst().getQuantity());
        assertEquals(3, service.stockMovementService.movements.size());
        assertEquals(StockMovementType.SALE, service.stockMovementService.movements.get(1).getType());
        assertEquals(-3, service.stockMovementService.movements.get(1).getQuantity());
        assertEquals(StockMovementType.ADJUSTMENT, service.stockMovementService.movements.get(2).getType());
        assertEquals(-2, service.stockMovementService.movements.get(2).getQuantity());
    }

    @Test
    void confirmsOrderByRemovingItsItemsFromStock() {
        StockService stockService = new StockService();
        OrderService orderService = new OrderService(stockService);
        Item cola = item(1, "Cola");
        stockService.addStock(cola, 10, new BigDecimal("50"));
        Order order = orderService.createOrder();
        orderService.addItemToOrder(order, cola, 4);

        orderService.confirmOrder(order);

        assertEquals(6, stockService.stockList.getFirst().getQuantity());
        assertEquals(StockMovementType.SALE, stockService.stockMovementService.movements.getLast().getType());
        assertEquals(-4, stockService.stockMovementService.movements.getLast().getQuantity());
    }

    @Test
    void printsOnlyLowStockItems() {
        StockService service = new StockService();
        service.addStock(item(1, "Cola"), 10, new BigDecimal("50"));
        service.addStock(item(2, "Fanta"), 4, new BigDecimal("45"));
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        PrintStream original = System.out;

        try {
            System.setOut(new PrintStream(output));
            service.showLowStock(5);
        } finally {
            System.setOut(original);
        }

        assertTrue(output.toString().contains("Fanta"));
        assertTrue(!output.toString().contains("Cola"));
    }

    private Item item(int id, String name) {
        return new Item(id, name, new BigDecimal("100"), name, name + ".png");
    }
}
