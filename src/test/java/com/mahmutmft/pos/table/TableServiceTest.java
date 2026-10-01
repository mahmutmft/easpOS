package com.mahmutmft.pos.table;

import com.mahmutmft.pos.item.Item;
import com.mahmutmft.pos.order.Order;
import com.mahmutmft.pos.order.OrderItem;
import com.mahmutmft.pos.waiter.Waiter;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TableServiceTest {

    @Test
    void createsAvailableTablesWithSequentialIds() {
        TableService service = new TableService();

        Table first = service.createTable();
        Table second = service.createTable();

        assertEquals(1, first.getId());
        assertEquals(2, second.getId());
        assertEquals(TableStatus.AVAILABLE, first.getStatus());
        assertTrue(first.getOrders().isEmpty());
    }

    @Test
    void calculatesTotalAcrossAllTableOrders() {
        TableService service = new TableService();
        Table table = new Table(1);
        Order first = new Order(1);
        Order second = new Order(2);
        first.getOrderItems().add(new OrderItem(item(1, "Cola", "120"), 2));
        second.getOrderItems().add(new OrderItem(item(2, "Burger", "250"), 1));
        table.getOrders().add(first);
        table.getOrders().add(second);

        assertEquals(0, new BigDecimal("490").compareTo(service.calculateTheTable(table)));
    }

    @Test
    void formatsTableWithAssignedWaiter() {
        Table table = new Table(4);
        table.setStatus(TableStatus.BUSY);
        table.setWaiter(new Waiter(1, "Mahmut", "mahmut", "password"));

        assertEquals("Table ID: 4 - Status: BUSY - Waiter: Mahmut", table.toString());
    }

    private Item item(int id, String name, String price) {
        return new Item(id, name, new BigDecimal(price), name, name + ".png");
    }
}
