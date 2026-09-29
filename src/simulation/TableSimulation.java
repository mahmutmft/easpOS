package simulation;

import model.Item;
import model.Table;
import model.Waiter;
import service.TableService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class TableSimulation {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        TableService tableService = new TableService();

        ArrayList<Item> items = createItems();
        ArrayList<Waiter> waiters = createWaiters();
        ArrayList<Table> tables = createTables(tableService);

        Waiter loggedWaiter = login(scanner, waiters);

        System.out.println(
                "\nYou were logged in successfully as " + loggedWaiter.getName()
        );

        while (true) {

            printSection("TABLES");

            tableService.listTheTables(tables);

            System.out.println(
                    "\nPress the number of table to open one or 0 to exit:"
            );

            int tableId = scanner.nextInt();

            if (tableId == 0) {
                break;
            }

            Table selectedTable = tables.get(tableId - 1);

            tableService.openTable(
                    selectedTable,
                    items,
                    loggedWaiter
            );

            if (!selectedTable.getOrders().isEmpty()) {

                printSection("TABLE TOTAL");

                System.out.println(
                        "Total: " +
                                tableService.calculateTheTable(selectedTable)
                );

                System.out.println(
                        "\nPress 1 to pay or 0 to go back:"
                );

                int option = scanner.nextInt();

                if (option == 1) {
                    tableService.payTheTable(selectedTable);
                }
            }
        }

        printSection("SIMULATION FINISHED");
    }


    private static ArrayList<Table> createTables(
            TableService tableService
    ) {

        ArrayList<Table> tables = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            tables.add(tableService.createTable());
        }

        return tables;
    }


    private static ArrayList<Waiter> createWaiters() {

        ArrayList<Waiter> waiters = new ArrayList<>();

        Waiter mahmut = new Waiter(
                1,
                "Mahmut",
                "muty",
                "1234"
        );

        waiters.add(mahmut);

        return waiters;
    }


    private static ArrayList<Item> createItems() {

        ArrayList<Item> items = new ArrayList<>();

        items.add(new Item(
                1,
                "Coca Cola",
                new BigDecimal("100"),
                "Cold Coca-Cola drink",
                "path"
        ));

        items.add(new Item(
                2,
                "Fanta",
                new BigDecimal("100"),
                "Cold orange drink",
                "path"
        ));

        items.add(new Item(
                3,
                "Cheeseburger",
                new BigDecimal("250"),
                "Burger with beef and cheese",
                "path"
        ));

        items.add(new Item(
                4,
                "French Fries",
                new BigDecimal("150"),
                "Crispy french fries",
                "path"
        ));

        items.add(new Item(
                5,
                "Chicken Burger",
                new BigDecimal("220"),
                "Burger with grilled chicken",
                "path"
        ));

        items.add(new Item(
                6,
                "Water",
                new BigDecimal("60"),
                "Cold bottled water",
                "path"
        ));

        return items;
    }


    private static Waiter login(
            Scanner scanner,
            ArrayList<Waiter> waiters
    ) {

        while (true) {

            System.out.println("Enter the username:");
            String username = scanner.nextLine();

            for (Waiter waiter : waiters) {

                if (Objects.equals(
                        waiter.getUsername(),
                        username
                )) {

                    System.out.println("Enter the password:");
                    String password = scanner.nextLine();

                    if (waiter.getPassword().equals(password)) {
                        return waiter;
                    }
                }
            }

            System.out.println("Wrong username or password.\n");
        }
    }


    private static void printSection(String title) {

        System.out.println("\n==============================");
        System.out.println(title);
        System.out.println("==============================");
    }
}