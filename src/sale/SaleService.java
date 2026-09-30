package sale;

import table.Table;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class SaleService {

    private List<Sale> sales = new ArrayList<>();
    private int id = 1;

    public void storeSale(Table table, BigDecimal totalPrice) {

        Sale sale = new Sale(id, table.getWaiter().getId(), table.getId(), new ArrayList<>(table.getOrders()), totalPrice);

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

    public BigDecimal getSalesByYear(int year){
        BigDecimal price = new BigDecimal("0");
        for (Sale sale : sales){
            if (year == sale.getDateTime().getYear()){
                price = price.add(sale.getTotalPrice());
            }
        }
        return price;
    }

    public BigDecimal getSalesByWaiterMonth(int month, int year, int waiterId){
        BigDecimal price = new BigDecimal("0");
        for (Sale sale : sales) {
            if (sale.getWaiterId() == waiterId){
                if (year == sale.getDateTime().getYear()) if (month == sale.getDateTime().getMonthValue()) {
                    price = price.add(sale.getTotalPrice());
                }
            }
        }
        return price;
    }

    public BigDecimal getSalesByWaiterYear(int year, int waiterId){
        BigDecimal price = new BigDecimal("0");
        for (Sale sale : sales) {
            if (sale.getWaiterId() == waiterId){
                if (year == sale.getDateTime().getYear()) {
                    price = price.add(sale.getTotalPrice());
                }
            }
        }
        return price;
    }
}