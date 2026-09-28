package service;

import model.Waiter;

import java.util.ArrayList;
import java.util.Scanner;

public class WaiterService {
    Scanner scanner = new Scanner(System.in);
    private int id = 1;
    public Waiter createAccount(){
        System.out.println("Enter the account name");
        String name = scanner.nextLine();
        System.out.println("Enter the account username");
        String username = scanner.nextLine();
        System.out.println("Enter the account password");
        String password = scanner.nextLine();
        Waiter waiter = new Waiter(id,name,username,password);
        id++;
        return waiter;
    }
    public void listAccounts(ArrayList<Waiter> waiters){
        for (Waiter waiter: waiters){
            System.out.println(waiter);
        }
    }
}
