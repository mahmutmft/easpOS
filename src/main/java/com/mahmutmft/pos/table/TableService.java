package com.mahmutmft.pos.table;

import com.mahmutmft.pos.item.Item;
import com.mahmutmft.pos.item.ItemService;
import com.mahmutmft.pos.order.Order;
import com.mahmutmft.pos.order.OrderService;
import com.mahmutmft.pos.sale.SaleService;
import com.mahmutmft.pos.stock.StockService;
import com.mahmutmft.pos.waiter.Waiter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Scanner;

public class TableService {

    private int id = 1;

    private final Scanner scanner = new Scanner(System.in);
    StockService stockService = new StockService();
    private final OrderService orderService = new OrderService(stockService);
    private final ItemService itemService = new ItemService();
    private final SaleService saleService = new SaleService();


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


    public void openTable(Table table, ArrayList<Item> items, Waiter waiter) {

        if (table.getOrders().isEmpty()) {

            System.out.println("The table is empty. Press 1 if you want to add a new order");

            int option = scanner.nextInt();

            if (option == 1) {
                addNewOrder(table, items);

                table.setStatus(TableStatus.BUSY);
                table.setWaiter(waiter);
            }

            return;
        }


        System.out.println("\nTable already has orders.");
        System.out.println("Press 1 to add a new order or 2 to list orders or 3 to pay");

        int option = scanner.nextInt();

        if (option == 1) {
            addNewOrder(table, items);
        }

        if (option == 2) {
            listTableOrders(table);
        }

        if (option == 3) {
            System.out.println(calculateTheTable(table));
        }
    }


    private void listTableOrders(Table table) {
        for (Order order : table.getOrders()) {
            System.out.println("\nOrder #" + order.getId());
            orderService.listOrderItems(order);
        }
    }

    public BigDecimal calculateTheTable(Table table) {
        BigDecimal tableTotal = new BigDecimal("0");
        for (Order order : table.getOrders()) {
            tableTotal = tableTotal.add(orderService.calculateTotal(order));
        }
        return tableTotal;
    }

    public void payTheTable(Table table) {

        BigDecimal totalPrice = calculateTheTable(table);

        System.out.println(totalPrice);
        System.out.println("Are you sure you want to pay the table");

        int number = scanner.nextInt();

        if (number == 1) {

            System.out.println("Table is paid");
            saleService.storeSale(table, totalPrice);

            table.setStatus(TableStatus.AVAILABLE);
            table.setWaiter(null);
            table.getOrders().clear();
        }
    }
}
