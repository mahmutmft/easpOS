package service;

import model.*;

import java.util.ArrayList;
import java.util.Scanner;

public class TableService {

    private int id = 1;

    private final Scanner scanner = new Scanner(System.in);
    private final OrderService orderService = new OrderService();
    private final ItemService itemService = new ItemService();


    public Table createTable() {
        Table table = new Table(id);
        id++;
        return table;
    }


    public void listTheTables(ArrayList<Table> tables) {
        for (Table table : tables) {
            System.out.println(table);
        }
    }


    public void addNewOrder(Table table, ArrayList<Item> items) {

        Order order = orderService.createOrder();
        table.getOrders().add(order);

        itemService.listItems(items);

        System.out.println("Choose what item you want to add. Type the ID:");
        int itemId = scanner.nextInt();

        for (Item item : items) {

            if (item.getId() == itemId) {

                System.out.println("Input quantity:");
                int quantity = scanner.nextInt();

                orderService.addItemToOrder(order, item, quantity);
                break;
            }
        }
    }


    public void openTable(
            Table table,
            ArrayList<Item> items,
            Waiter waiter
    ) {

        if (table.getOrders().isEmpty()) {

            System.out.println(
                    "The table is empty. Press 1 if you want to add a new order"
            );

            int option = scanner.nextInt();

            if (option == 1) {
                addNewOrder(table, items);

                table.setStatus(TableStatus.BUSY);
                table.setWaiter(waiter);
            }

            return;
        }


        System.out.println("\nTable already has orders.");
        System.out.println("Press 1 to add a new order or 2 to list orders");

        int option = scanner.nextInt();

        if (option == 1) {
            addNewOrder(table, items);
        }

        if (option == 2) {
            listTableOrders(table);
        }
    }


    private void listTableOrders(Table table) {

        for (Order order : table.getOrders()) {
            System.out.println("\nOrder #" + order.getId());
            orderService.listOrderItems(order);
        }
    }
}