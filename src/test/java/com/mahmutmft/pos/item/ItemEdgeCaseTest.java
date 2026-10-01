package com.mahmutmft.pos.item;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertThrows;

class ItemEdgeCaseTest {

    @Test
    void rejectsNullPrice() {
        ItemService service = new ItemService();

        assertThrows(
                InvalidPriceException.class,
                () -> service.createItem("Water", null, "Water", "water.png")
        );
    }

    @Test
    void rejectsZeroPriceWhenPriceIsChanged() {
        Item item = item();

        assertThrows(InvalidPriceException.class, () -> item.setPrice(BigDecimal.ZERO));
    }

    @Test
    void rejectsNegativePriceWhenPriceIsChanged() {
        Item item = item();

        assertThrows(InvalidPriceException.class, () -> item.setPrice(new BigDecimal("-1")));
    }

    @Test
    void failedCreationDoesNotConsumeId() {
        ItemService service = new ItemService();
        assertThrows(
                InvalidPriceException.class,
                () -> service.createItem("Invalid", BigDecimal.ZERO, "Invalid", "invalid.png")
        );

        Item item = service.createItem("Water", new BigDecimal("60"), "Water", "water.png");

        org.junit.jupiter.api.Assertions.assertEquals(1, item.getId());
    }

    private Item item() {
        return new ItemService().createItem("Water", new BigDecimal("60"), "Water", "water.png");
    }
}
