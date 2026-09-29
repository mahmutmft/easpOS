package service;

import model.Item;
import model.Stock;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class StockService {
    List<Stock> stockList = new ArrayList<>();

    public void addStock(Item item, int quantity, BigDecimal stockPrice) {

        Stock newStock = new Stock(item, quantity, stockPrice);
        boolean sameItem = false;

        for (Stock stock : stockList) {

            if (stock.getItem().getId() == item.getId()) {

                sameItem = true;

                int oldQuantity = stock.getQuantity();
                int totalQuantity = oldQuantity + quantity;

                BigDecimal oldValue =
                        stock.getStockPrice().multiply(new BigDecimal(oldQuantity));

                BigDecimal newValue =
                        stockPrice.multiply(new BigDecimal(quantity));

                BigDecimal totalValue = oldValue.add(newValue);

                BigDecimal avgPrice = totalValue.divide(
                        new BigDecimal(totalQuantity),
                        2,
                        RoundingMode.HALF_UP
                );

                stock.setQuantity(totalQuantity);
                stock.setStockPrice(avgPrice);

                break;
            }
        }

        if (!sameItem) {
            stockList.add(newStock);
        }
    }

    public void listStocks() {
        for (Stock stock : stockList) {
            System.out.println(stock);
        }
    }

    public void removeStock(Item item, int quantity) {
        for (Stock stock : stockList){
            if (stock.getItem().getId() == item.getId()){
                stock.setQuantity(stock.getQuantity() - quantity);
                break;
            }
        }
    }
}
