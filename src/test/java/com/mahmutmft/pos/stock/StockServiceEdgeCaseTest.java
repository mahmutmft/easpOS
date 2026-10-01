package com.mahmutmft.pos.stock;

import com.mahmutmft.pos.item.InvalidPriceException;
import com.mahmutmft.pos.item.Item;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class StockServiceEdgeCaseTest {

    @Test
    void rejectsZeroQuantityDelivery() {
        StockService service = new StockService();

        assertThrows(InvalidQuantityException.class, () -> service.addStock(item(1), 0, new BigDecimal("50")));
    }

    @Test
    void rejectsNegativeQuantityDelivery() {
        StockService service = new StockService();

        assertThrows(InvalidQuantityException.class, () -> service.addStock(item(1), -5, new BigDecimal("50")));
    }

    @Test
    void rejectsRemovingMoreThanAvailableStock() {
        StockService service = new StockService();
        Item item = item(1);
        service.addStock(item, 5, new BigDecimal("50"));

        assertThrows(IllegalArgumentException.class, () -> service.removeStock(item, 6));
        assertEquals(5, service.stockList.getFirst().getQuantity());
    }

    @Test
    void rejectsNegativeStockAdjustment() {
        StockService service = new StockService();
        Item item = item(1);
        service.addStock(item, 5, new BigDecimal("50"));

        assertThrows(InvalidQuantityException.class, () -> service.adjustStock(item, -1));
        assertEquals(5, service.stockList.getFirst().getQuantity());
    }

    @Test
    void ignoresRemovalForUnknownItemWithoutRecordingMovement() {
        StockService service = new StockService();
        service.addStock(item(1), 5, new BigDecimal("50"));
        int movementsBefore = service.stockMovementService.movements.size();

        service.removeStock(item(2), 1);

        assertEquals(5, service.stockList.getFirst().getQuantity());
        assertEquals(movementsBefore, service.stockMovementService.movements.size());
    }

    @Test
    void rejectsDeliveryWithNonPositivePrice() {
        StockService service = new StockService();

        assertThrows(InvalidPriceException.class, () -> service.addStock(item(1), 5, BigDecimal.ZERO));
    }

    private Item item(int id) {
        return new Item(id, "Item " + id, new BigDecimal("100"), "Item", "item.png");
    }
}
