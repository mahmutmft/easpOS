package com.mahmutmft.pos.sale;

import com.mahmutmft.pos.order.Order;
import com.mahmutmft.pos.table.Table;
import com.mahmutmft.pos.waiter.Waiter;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SaleServiceTest {

    @Test
    void storesSaleWithTableSnapshot() {
        SaleService service = new SaleService();
        Waiter waiter = waiter(1, "Mahmut");
        Table table = table(3, waiter);
        table.getOrders().add(new Order(7));

        service.storeSale(table, new BigDecimal("450"));
        table.getOrders().clear();

        Sale sale = service.getSales().getFirst();
        assertEquals(1, sale.getId());
        assertEquals(1, sale.getWaiterId());
        assertEquals(3, sale.getTableId());
        assertEquals(1, sale.getOrderList().size());
        assertEquals(0, new BigDecimal("450").compareTo(sale.getTotalPrice()));
    }

    @Test
    void calculatesSalesReports() {
        SaleService service = new SaleService();
        Waiter mahmut = waiter(1, "Mahmut");
        Waiter ana = waiter(2, "Ana");

        store(service, mahmut, 1, "450", LocalDateTime.of(2026, 9, 10, 15, 30));
        store(service, ana, 2, "650", LocalDateTime.of(2026, 9, 15, 20, 0));
        store(service, mahmut, 3, "600", LocalDateTime.of(2026, 8, 20, 18, 45));
        store(service, mahmut, 4, "750", LocalDateTime.of(2025, 9, 5, 13, 0));

        assertAmount("1800", service.getSalesByWaiter(mahmut.getId()));
        assertAmount("650", service.getSalesByWaiter(ana.getId()));
        assertAmount("1100", service.getSalesByMonth(9, 2026));
        assertAmount("1700", service.getSalesByYear(2026));
        assertAmount("450", service.getSalesByWaiterMonth(9, 2026, mahmut.getId()));
        assertAmount("1050", service.getSalesByWaiterYear(2026, mahmut.getId()));
    }

    private void store(SaleService service, Waiter waiter, int tableId, String total, LocalDateTime dateTime) {
        service.storeSale(table(tableId, waiter), new BigDecimal(total));
        service.getSales().getLast().setDateTime(dateTime);
    }

    private Table table(int id, Waiter waiter) {
        Table table = new Table(id);
        table.setWaiter(waiter);
        return table;
    }

    private Waiter waiter(int id, String name) {
        return new Waiter(id, name, name.toLowerCase(), "password");
    }

    private void assertAmount(String expected, BigDecimal actual) {
        assertEquals(0, new BigDecimal(expected).compareTo(actual));
    }
}
