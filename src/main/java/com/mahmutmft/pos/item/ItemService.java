package com.mahmutmft.pos.item;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Scanner;

public class ItemService {
    private int id = 1;

    public Item createItem(String name, BigDecimal price, String description, String imagePath) {
        Item item = new Item(id, name, price, description, imagePath);
        id++;
        return item;
    }

    public void listItems(ArrayList<Item> items) {
        for (Item temp : items) {
            System.out.println(temp.toString());
        }
    }
}
