package com.revature.BankingApp;

import java.util.Scanner;

public class Main {

    
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("All email addresses and passwords will be considered valid since no database to store customer info.");
        System.out.println("Enter email address.");
        String email = input.nextLine();
        System.out.println("Enter password.");
        String password = input.nextLine();
        String s = "";
        for (int i = 0; i < 16; i++) {
            s += Math.random() * 10;
        }
        
        MyAccount a = new MyAccount(s);
        Customer c = new Customer(email, password, a);
        System.out.println("Balance = $0.00");
        System.out.println("Deposit funds: press 1\nWithdraw funds: press 2\nCheck balance: press 3\nQuit: press q");
        String action = input.nextLine();
        System.out.println(action);
        while (!(action.equals("1") || action.equals("2") || action.equals("3") || action.equals("q"))) {
            System.out.println("Invalid action. Please enter 1, 2, or 3");
            action = input.next();
        }
        while (action != "q") {
            if (action.equals("1")) {
                c.addFunds();
            }else if (action.equals("2")) {
                c.withdraw();
            }else if (action.equals("3")){
                c.checkBalance();
            }
            System.out.println("Deposit funds: press 1\nWithdraw funds: press 2\nCheck balance: press 3\nQuit: press q");
            action = input.next();
            while (!(action.equals("1") || action.equals("2") || action.equals("3") || action.equals("q"))) {
                System.out.println("Invalid action. Please enter 1, 2, or 3");
                action = input.next();
            }
        }
        if (action.equals("q")) {
        	System.out.println("Goodbye");
        	
        }
        
        
    }
}
