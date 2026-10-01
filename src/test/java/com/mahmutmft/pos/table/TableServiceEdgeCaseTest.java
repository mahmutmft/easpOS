package com.mahmutmft.pos.table;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TableServiceEdgeCaseTest {

    @Test
    void emptyTableHasZeroTotal() {
        TableService service = new TableService();

        assertEquals(0, BigDecimal.ZERO.compareTo(service.calculateTheTable(new Table(1))));
    }

    @Test
    void newTableHasAvailableStatusAndNoWaiter() {
        Table table = new Table(1);

        assertEquals(TableStatus.AVAILABLE, table.getStatus());
        assertEquals(null, table.getWaiter());
    }
}
