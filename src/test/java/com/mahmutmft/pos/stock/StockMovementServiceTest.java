package com.mahmutmft.pos.stock;

import com.mahmutmft.pos.item.Item;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class StockMovementServiceTest {

    @Test
    void recordsMovementDetails() {
        StockMovementService service = new StockMovementService();
        Item item = new Item(1, "Cola", new BigDecimal("100"), "Cola", "cola.png");

        service.recordMovement(item, 12, StockMovementType.DELIVERY);

        StockMovement movement = service.movements.getFirst();
        assertEquals(item, movement.getItem());
        assertEquals(12, movement.getQuantity());
        assertEquals(StockMovementType.DELIVERY, movement.getType());
        assertNotNull(movement.getDateTime());
    }

    @Test
    void allowsMovementDateToBeChanged() {
        StockMovementService service = new StockMovementService();
        Item item = new Item(1, "Cola", new BigDecimal("100"), "Cola", "cola.png");
        LocalDateTime dateTime = LocalDateTime.of(2026, 9, 15, 10, 30);
        service.recordMovement(item, -2, StockMovementType.SALE);

        service.movements.getFirst().setDateTime(dateTime);

        assertEquals(dateTime, service.movements.getFirst().getDateTime());
    }
}
