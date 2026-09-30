package com.mahmutmft.pos;

import com.mahmutmft.pos.item.InvalidPriceException;
import com.mahmutmft.pos.item.Item;
import com.mahmutmft.pos.item.ItemService;
import com.mahmutmft.pos.waiter.Waiter;
import com.mahmutmft.pos.waiter.WaiterService;

import java.util.ArrayList;
import java.util.Scanner;


public class PosApplication {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ItemService itemService = new ItemService();
        WaiterService waiterService = new WaiterService();
        ArrayList<Item> items = new ArrayList<>();
        ArrayList<Waiter> waiters = new ArrayList<>();


        System.out.println("If you want to make an item press 1");
        System.out.println("If you want to list the items press 2");
        System.out.println("If you want to create an account press 3");
        System.out.println("If you want to create an account press 4");
        System.out.println("If you want to quit press 0");

        int id = 1;
        while (true) {
            int numberPressed = scanner.nextInt();
            scanner.nextLine();
            if (numberPressed == 1) {
                try {
                    items.add(itemService.createItem(id));
                    id++;
                } catch (InvalidPriceException e) {
                    System.out.println(e.getMessage());
                }
            } else if (numberPressed == 2) {
                itemService.listItems(items);
            } else if (numberPressed == 3) {
                waiters.add(waiterService.createAccount());
            } else if (numberPressed == 4) {
                waiterService.listAccounts(waiters);
            } else if (numberPressed == 0) {
                break;
            } else {
                System.out.println("Invalid options");
            }
        }
    }
}
