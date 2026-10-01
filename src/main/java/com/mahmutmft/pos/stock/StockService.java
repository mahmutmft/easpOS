package com.mahmutmft.pos.stock;

import com.mahmutmft.pos.item.InvalidPriceException;
import com.mahmutmft.pos.item.Item;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class StockService {
    List<Stock> stockList = new ArrayList<>();
    StockMovementService stockMovementService = new StockMovementService();

    public StockMovementService getStockMovementService() {
        return stockMovementService;
    }

    public void addStock(Item item, int quantity, BigDecimal stockPrice) {

        if (quantity <= 0) {
            throw new InvalidQuantityException("Quantity must be greater than 0.");
        }

        if (stockPrice == null || stockPrice.compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidPriceException("Stock price must be greater than 0.");
        }

        Stock newStock = new Stock(item, quantity, stockPrice);
        boolean sameItem = false;

        for (Stock stock : stockList) {

            if (stock.getItem().getId() == item.getId()) {

                sameItem = true;

                int oldQuantity = stock.getQuantity();
                int totalQuantity = oldQuantity + quantity;

                BigDecimal oldValue = stock.getStockPrice().multiply(new BigDecimal(oldQuantity));

                BigDecimal newValue = stockPrice.multiply(new BigDecimal(quantity));

                BigDecimal totalValue = oldValue.add(newValue);

                BigDecimal avgPrice = totalValue.divide(new BigDecimal(totalQuantity), 2, RoundingMode.HALF_UP);

                stock.setQuantity(totalQuantity);
                stock.setStockPrice(avgPrice);

                break;
            }
        }

        if (!sameItem) {
            stockList.add(newStock);
        }
        stockMovementService.recordMovement(item, quantity, StockMovementType.DELIVERY);
    }

    public void listStocks() {
        for (Stock stock : stockList) {
            System.out.println(stock);
        }
    }

    public void removeStock(Item item, int quantity) {
        for (Stock stock : stockList) {
            if (stock.getItem().getId() == item.getId()) {
                if (quantity > stock.getQuantity()) {
                    throw new IllegalArgumentException("Cannot remove more stock than is currently available.");
                }

                stock.setQuantity(stock.getQuantity() - quantity);
                stockMovementService.recordMovement(item, -quantity, StockMovementType.SALE);
                break;
            }
        }
    }

    public void adjustStock(Item item, int quantity) {
        if (quantity <= 0) {
            throw new InvalidQuantityException("Quantity must be greater than 0.");
        }
        for (Stock stock : stockList) {
            if (item.getId() == stock.getItem().getId()) {
                int difference = quantity - stock.getQuantity();
                stock.setQuantity(quantity);
                stockMovementService.recordMovement(item, difference, StockMovementType.ADJUSTMENT);
                break;
            }
        }
    }

    public void showLowStock(int belowStock) {
        for (Stock stock : stockList) {
            if (stock.getQuantity() <= belowStock) {
                System.out.println(stock);
            }
        }
    }

    public BigDecimal getTotalStockValue() {
        BigDecimal results = new BigDecimal("0");
        for (Stock stock : stockList) {
            results = results.add(stock.getStockPrice().multiply(new BigDecimal(stock.getQuantity())));
        }
        return results;
    }

    // getTotalStockValue - колку вреди целата моментална залиха по набавна цена
    // addStock() да не прима quantity <= 0
}
