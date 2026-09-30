package com.mahmutmft.pos.simulation;

import com.mahmutmft.pos.item.Item;
import com.mahmutmft.pos.order.Order;
import com.mahmutmft.pos.order.OrderService;
import com.mahmutmft.pos.sale.Sale;
import com.mahmutmft.pos.sale.SaleService;
import com.mahmutmft.pos.stock.StockService;
import com.mahmutmft.pos.table.Table;
import com.mahmutmft.pos.table.TableService;
import com.mahmutmft.pos.waiter.Waiter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class SaleSimulation {

    public static void main(String[] args) {

        SaleService saleService = new SaleService();
        StockService stockService = new StockService();
        OrderService orderService = new OrderService(stockService);
        TableService tableService = new TableService();

        Waiter mahmut = new Waiter(1, "Mahmut", "muty", "1234");
        Waiter mahmut2 = new Waiter(2, "Mahmut2", "mahmut2", "1234");

        Item cocaCola = new Item(1, "Coca Cola", new BigDecimal("100"), "Cold drink", "path");

        Item burger = new Item(2, "Cheeseburger", new BigDecimal("250"), "Cheeseburger", "path");

        Item fries = new Item(3, "French Fries", new BigDecimal("150"), "French fries", "path");

        Table table1 = new Table(1);
        table1.setWaiter(mahmut);

        Order order1 = orderService.createOrder();
        orderService.addItemToOrder(order1, cocaCola, 2);
        orderService.addItemToOrder(order1, burger, 1);
        table1.getOrders().add(order1);

        BigDecimal total1 = tableService.calculateTheTable(table1);
        saleService.storeSale(table1, total1);

        Sale sale1 = saleService.getSales().getLast();
        sale1.setDateTime(LocalDateTime.of(2026, 9, 10, 15, 30));

        table1.getOrders().clear();
        table1.setWaiter(null);

        Table table2 = new Table(2);
        table2.setWaiter(mahmut2);

        Order order2 = orderService.createOrder();
        orderService.addItemToOrder(order2, burger, 2);
        orderService.addItemToOrder(order2, fries, 1);
        table2.getOrders().add(order2);

        BigDecimal total2 = tableService.calculateTheTable(table2);
        saleService.storeSale(table2, total2);

        Sale sale2 = saleService.getSales().getLast();
        sale2.setDateTime(LocalDateTime.of(2026, 9, 15, 20, 0));

        table2.getOrders().clear();
        table2.setWaiter(null);

        Table table3 = new Table(3);
        table3.setWaiter(mahmut);

        Order order3 = orderService.createOrder();
        orderService.addItemToOrder(order3, fries, 4);
        table3.getOrders().add(order3);

        BigDecimal total3 = tableService.calculateTheTable(table3);
        saleService.storeSale(table3, total3);

        Sale sale3 = saleService.getSales().getLast();
        sale3.setDateTime(LocalDateTime.of(2026, 8, 20, 18, 45));

        table3.getOrders().clear();
        table3.setWaiter(null);

        Table table4 = new Table(4);
        table4.setWaiter(mahmut);

        Order order4 = orderService.createOrder();
        orderService.addItemToOrder(order4, burger, 3);
        table4.getOrders().add(order4);

        BigDecimal total4 = tableService.calculateTheTable(table4);
        saleService.storeSale(table4, total4);

        Sale sale4 = saleService.getSales().getLast();
        sale4.setDateTime(LocalDateTime.of(2025, 9, 5, 13, 0));

        table4.getOrders().clear();
        table4.setWaiter(null);

        System.out.println("\n==============================");
        System.out.println("ALL SALES");
        System.out.println("==============================");

        for (Sale sale : saleService.getSales()) {
            System.out.println("Sale #" + sale.getId() + " | Waiter: " + sale.getWaiterId() + " | Table: " + sale.getTableId() + " | Total: " + sale.getTotalPrice() + " | Date: " + sale.getDateTime());
        }

        System.out.println("\n==============================");
        System.out.println("SALES STATISTICS");
        System.out.println("==============================");

        System.out.println("Mahmut all time: " + saleService.getSalesByWaiter(mahmut.getId()) + " MKD");

        System.out.println("Mahmut2 all time: " + saleService.getSalesByWaiter(mahmut2.getId()) + " MKD");

        System.out.println("Restaurant September 2026: " + saleService.getSalesByMonth(9, 2026) + " MKD");

        System.out.println("Restaurant 2026: " + saleService.getSalesByYear(2026) + " MKD");

        System.out.println("Mahmut September 2026: " + saleService.getSalesByWaiterMonth(9, 2026, mahmut.getId()) + " MKD");

        System.out.println("Mahmut 2026: " + saleService.getSalesByWaiterYear(2026, mahmut.getId()) + " MKD");

        System.out.println("\n==============================");
        System.out.println("EXPECTED RESULTS");
        System.out.println("==============================");

        System.out.println("Mahmut all time: 1800 MKD");
        System.out.println("Mahmut2 all time: 650 MKD");
        System.out.println("Restaurant September 2026: 1100 MKD");
        System.out.println("Restaurant 2026: 1700 MKD");
        System.out.println("Mahmut September 2026: 450 MKD");
        System.out.println("Mahmut 2026: 1050 MKD");
    }
}
