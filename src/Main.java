import exception.InvalidPriceException;
import Item.Item;
import waiter.Waiter;
import Item.ItemService;
import waiter.WaiterService;

import java.util.ArrayList;
import java.util.Scanner;


public class Main {
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