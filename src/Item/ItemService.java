package Item;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Scanner;

public class ItemService {
    Scanner scanner = new Scanner(System.in);
    public Item createItem(int id){
        System.out.println("Enter an item name");
        String name = scanner.nextLine();
        System.out.println("Enter the price for the item");
        BigDecimal price = scanner.nextBigDecimal();
        System.out.println("Enter the descriptions for an item");
        scanner.nextLine();
        String description = scanner.nextLine();
        System.out.println("Upload the image");
        String imagePath = scanner.nextLine();

        return new Item(id, name, price, description, imagePath);
    }

    public void listItems(ArrayList<Item> items){
        for (Item temp : items) {
            System.out.println(temp.toString());
        }
    }
}
