package com.mahmutmft.pos.sale;

import com.mahmutmft.pos.order.Order;
import com.mahmutmft.pos.order.OrderItem;
import com.mahmutmft.pos.table.Table;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class SaleService {

    private final List<Sale> sales = new ArrayList<>();
    private int id = 1;

    public void storeSale(Table table, BigDecimal totalPrice) {

        if (table.getWaiter() == null) {
            throw new IllegalStateException("Cannot create a sale without an assigned waiter.");
        }

        ArrayList<Order> orderCopies = new ArrayList<>();
        for (Order order : table.getOrders()) {
            Order orderCopy = new Order(order.getId());
            for (OrderItem orderItem : order.getOrderItems()) {
                OrderItem orderItemCopy = new OrderItem(orderItem.getItem(), orderItem.getQuantity());
                orderCopy.getOrderItems().add(orderItemCopy);
            }
            orderCopies.add(orderCopy);
        }
        Sale sale = new Sale(id, table.getWaiter().getId(), table.getId(), orderCopies, totalPrice);
        sales.add(sale);
        id++;
    }

    public List<Sale> getSales() {
        return sales;
    }

    public BigDecimal getSalesByWaiter(int waiterId) {
        BigDecimal price = new BigDecimal("0");
        for (Sale sale : sales) {
            if (sale.getWaiterId() == waiterId) {
                price = price.add(sale.getTotalPrice());
            }
        }
        return price;
    }

    public BigDecimal getSalesByMonth(int month, int year) {
        BigDecimal price = new BigDecimal("0");
        for (Sale sale : sales) {
            if (sale.getDateTime().getYear() == year) {
                if (sale.getDateTime().getMonthValue() == month) price = price.add(sale.getTotalPrice());
            }
        }
        return price;
    }

    public BigDecimal getSalesByYear(int year) {
        BigDecimal price = new BigDecimal("0");
        for (Sale sale : sales) {
            if (year == sale.getDateTime().getYear()) {
                price = price.add(sale.getTotalPrice());
            }
        }
        return price;
    }

    public BigDecimal getSalesByWaiterMonth(int month, int year, int waiterId) {
        BigDecimal price = new BigDecimal("0");
        for (Sale sale : sales) {
            if (sale.getWaiterId() == waiterId) {
                if (year == sale.getDateTime().getYear()) if (month == sale.getDateTime().getMonthValue()) {
                    price = price.add(sale.getTotalPrice());
                }
            }
        }
        return price;
    }

    public BigDecimal getSalesByWaiterYear(int year, int waiterId) {
        BigDecimal price = new BigDecimal("0");
        for (Sale sale : sales) {
            if (sale.getWaiterId() == waiterId) {
                if (year == sale.getDateTime().getYear()) {
                    price = price.add(sale.getTotalPrice());
                }
            }
        }
        return price;
    }
}
