package simulation.waiter;

import waiter.Waiter;
import waiter.WaiterService;

import java.util.ArrayList;

public class WaiterLoginSimulation {

    public static void main(String[] args) {

        WaiterService waiterService = new WaiterService();
        ArrayList<Waiter> waiters = new ArrayList<>();

        printSection("CREATE WAITER #1");

        Waiter waiter1 = waiterService.createAccount();
        waiters.add(waiter1);

        System.out.println("Waiter created");


        printSection("CREATE WAITER #2");

        Waiter waiter2 = waiterService.createAccount();
        waiters.add(waiter2);

        System.out.println("Waiter created");


        printSection("WAITER ACCOUNTS");

        waiterService.listAccounts(waiters);


        printSection("SIMULATION FINISHED");
    }

    private static void printSection(String title) {
        System.out.println("\n==============================");
        System.out.println(title);
        System.out.println("==============================");
    }
}