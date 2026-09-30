package service;
import model.ENUMS.StockMovementType;
import model.Item;
import model.StockMovement;

import java.util.ArrayList;

public class StockMovementService {
    ArrayList<StockMovement> movements = new ArrayList<>();

    public void recordMovement(Item item, int quantity, StockMovementType type){
        StockMovement stockMovement = new StockMovement(item, quantity, type);
        movements.add(stockMovement);
    }

    public void listMovements(){
        for (StockMovement stockMovement : movements){
            System.out.println(stockMovement);
        }
    }

    public void getMovementsByItem(Item item){
        for (StockMovement stockMovement : movements){
            if (stockMovement.getItem().getId() == item.getId()){
                System.out.println(stockMovement);
            }
        }
    }

    public void getMovementsByType(StockMovementType type){
        for (StockMovement stockMovement : movements){
            if (type == stockMovement.getType()){
                System.out.println(stockMovement);
            }
        }
    }

    public void getMovementsByMonth (int month, int year){
        for (StockMovement stockMovement : movements){
            if (stockMovement.getDateTime().getYear() == year){
                if (stockMovement.getDateTime().getMonthValue() == month){
                    System.out.println(stockMovement);
                }
            }
        }
    }

    public void getMovementsByYear (int year){
        for (StockMovement stockMovement : movements){
            if (stockMovement.getDateTime().getYear() == year){
                System.out.println(stockMovement);
            }
        }
    }
}
