package com.mahmutmft.pos.order;

import com.mahmutmft.pos.item.Item;
import com.mahmutmft.pos.stock.StockService;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class OrderServiceEdgeCaseTest {

    @Test
    void emptyOrderHasZeroTotal() {
        OrderService service = new OrderService(new StockService());

        assertEquals(0, BigDecimal.ZERO.compareTo(service.calculateTotal(service.createOrder())));
    }

    @Test
    void rejectsZeroItemQuantity() {
        OrderService service = new OrderService(new StockService());
        Order order = service.createOrder();

        assertThrows(InvalidQuantityException.class, () -> service.addItemToOrder(order, item(), 0));
    }

    @Test
    void rejectsNegativeItemQuantity() {
        OrderService service = new OrderService(new StockService());
        Order order = service.createOrder();

        assertThrows(InvalidQuantityException.class, () -> service.addItemToOrder(order, item(), -2));
    }

    @Test
    void confirmingOrderTwiceDoesNotRemoveStockTwice() {
        StockService stockService = new StockService();
        OrderService orderService = new OrderService(stockService);
        Item item = item();
        stockService.addStock(item, 10, new BigDecimal("30"));
        Order order = orderService.createOrder();
        orderService.addItemToOrder(order, item, 2);

        orderService.confirmOrder(order);
        orderService.confirmOrder(order);

        String expected = "Name: Water, Quantity: 8, StockPrice: 30";
        assertEquals(expected, stock(stockService));
    }

    private String stock(StockService stockService) {
        java.io.ByteArrayOutputStream output = new java.io.ByteArrayOutputStream();
        java.io.PrintStream original = System.out;
        try {
            System.setOut(new java.io.PrintStream(output));
            stockService.listStocks();
        } finally {
            System.setOut(original);
        }
        return output.toString().trim();
    }

    private Item item() {
        return new Item(1, "Water", new BigDecimal("60"), "Water", "water.png");
    }
}
