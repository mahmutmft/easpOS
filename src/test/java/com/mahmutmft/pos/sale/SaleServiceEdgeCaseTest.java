package com.mahmutmft.pos.sale;

import com.mahmutmft.pos.item.Item;
import com.mahmutmft.pos.order.Order;
import com.mahmutmft.pos.order.OrderItem;
import com.mahmutmft.pos.table.Table;
import com.mahmutmft.pos.waiter.Waiter;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SaleServiceEdgeCaseTest {

    @Test
    void rejectsSaleWithoutAssignedWaiter() {
        SaleService service = new SaleService();

        assertThrows(IllegalStateException.class, () -> service.storeSale(new Table(1), new BigDecimal("100")));
    }

    @Test
    void reportsZeroWhenNoSalesMatch() {
        SaleService service = new SaleService();

        assertEquals(0, BigDecimal.ZERO.compareTo(service.getSalesByYear(1999)));
        assertEquals(0, BigDecimal.ZERO.compareTo(service.getSalesByWaiter(999)));
    }

    @Test
    void storedSaleDoesNotChangeWhenOriginalOrderChanges() {
        SaleService service = new SaleService();
        Table table = new Table(1);
        table.setWaiter(new Waiter(1, "Mahmut", "mahmut", "password"));
        Order order = new Order(1);
        OrderItem orderItem = new OrderItem(
                new Item(1, "Cola", new BigDecimal("100"), "Cola", "cola.png"),
                2
        );
        order.getOrderItems().add(orderItem);
        table.getOrders().add(order);
        service.storeSale(table, new BigDecimal("200"));

        orderItem.setQuantity(99);

        int storedQuantity = service.getSales().getFirst().getOrderList().getFirst().getOrderItems().getFirst().getQuantity();
        assertEquals(2, storedQuantity);
    }
}
