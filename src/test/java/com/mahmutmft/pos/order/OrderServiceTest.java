package com.mahmutmft.pos.order;

import com.mahmutmft.pos.item.Item;
import com.mahmutmft.pos.stock.StockService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

class OrderServiceTest {

    private OrderService orderService;

    @BeforeEach
    void setUp() {
        orderService = new OrderService(new StockService());
    }

    @Test
    void createsOrdersWithSequentialIds() {
        assertEquals(1, orderService.createOrder().getId());
        assertEquals(2, orderService.createOrder().getId());
    }

    @Test
    void addsDifferentItemsToOrder() {
        Order order = orderService.createOrder();
        Item cola = item(1, "Cola", "120");
        Item burger = item(2, "Burger", "250");

        orderService.addItemToOrder(order, cola, 2);
        orderService.addItemToOrder(order, burger, 1);

        assertEquals(2, order.getOrderItems().size());
        assertSame(cola, order.getOrderItems().get(0).getItem());
        assertSame(burger, order.getOrderItems().get(1).getItem());
    }

    @Test
    void mergesItemsWithTheSameId() {
        Order order = orderService.createOrder();
        Item first = item(1, "Cola", "120");
        Item second = item(1, "Cola", "120");

        orderService.addItemToOrder(order, first, 2);
        orderService.addItemToOrder(order, second, 3);

        assertEquals(1, order.getOrderItems().size());
        assertEquals(5, order.getOrderItems().getFirst().getQuantity());
        assertSame(first, order.getOrderItems().getFirst().getItem());
    }

    @Test
    void calculatesOrderTotal() {
        Order order = orderService.createOrder();
        orderService.addItemToOrder(order, item(1, "Cola", "120"), 2);
        orderService.addItemToOrder(order, item(2, "Burger", "250"), 1);

        assertEquals(0, new BigDecimal("490").compareTo(orderService.calculateTotal(order)));
    }

    private Item item(int id, String name, String price) {
        return new Item(id, name, new BigDecimal(price), name, name + ".png");
    }
}
