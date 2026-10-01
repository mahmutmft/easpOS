package com.mahmutmft.pos.item;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ItemTest {

    @Test
    void createsItemWithValidPrice() {
        ItemService service = new ItemService();
        Item item = service.createItem("Coca-Cola", new BigDecimal("120"), "Cold drink", "image.png");

        assertAll(
                () -> assertEquals(1, item.getId()),
                () -> assertEquals("Coca-Cola", item.getName()),
                () -> assertEquals(new BigDecimal("120"), item.getPrice()),
                () -> assertEquals("Cold drink", item.getDescription()),
                () -> assertEquals("image.png", item.getImagePath())
        );
    }

    @Test
    void rejectsZeroPrice() {
        ItemService service = new ItemService();

        assertThrows(
                InvalidPriceException.class,
                () -> service.createItem("Water", BigDecimal.ZERO, "Water", "water.png")
        );
    }

    @Test
    void rejectsNegativePrice() {
        ItemService service = new ItemService();

        assertThrows(
                InvalidPriceException.class,
                () -> service.createItem("Water", new BigDecimal("-1"), "Water", "water.png")
        );
    }

    @Test
    void formatsItemDetails() {
        ItemService service = new ItemService();
        Item item = service.createItem("Fanta", new BigDecimal("100"), "Orange drink", "fanta.png");

        assertEquals("id: 1 - Fanta 100", item.toString());
    }

    @Test
    void createsItemsWithSequentialIds() {
        ItemService service = new ItemService();

        Item first = service.createItem("Water", new BigDecimal("60"), "Water", "water.png");
        Item second = service.createItem("Fanta", new BigDecimal("100"), "Fanta", "fanta.png");

        assertEquals(1, first.getId());
        assertEquals(2, second.getId());
    }
}
