package com.revature.BankingApp;

import java.util.Scanner;

public class Customer {
    private String name, password;
    private MyAccount a;
    Scanner input;

    public Customer(String name, String password, MyAccount a) {
        this.name = name;
        this.password = password;
        this.a = a;
    }

    public void addFunds() {
    	input = new Scanner(System.in);
        System.out.println("Enter amount to deposit.");
        String money = input.next();
        boolean isDouble = false;
        double moneyDeposit = 0;
        while (!isDouble) {
            try {
                moneyDeposit = Double.parseDouble(money);
                // will truncate anything more than two decimal places
                moneyDeposit = moneyDeposit * 100;
                int m = (int) moneyDeposit;
                moneyDeposit = m/100.0;
                isDouble = true;
            }catch(Exception e) {
                System.out.println("Not a valid monetary amount. Please enter a number with maximum two decimal places.");
            }
        }
        boolean successfulAdd = a.addFunds(moneyDeposit);
        if(successfulAdd) {
            System.out.println(moneyDeposit + " has been added to your account.\nNew balance = $" + a.getBalance());
        }
    }

    public void withdraw() {
    	input = new Scanner(System.in);
        System.out.println("Enter amount to withdraw.");
        String money = input.nextLine();
        boolean isDouble = false;
        double moneyWithdraw = 0;
        while (!isDouble) {
            try {
                moneyWithdraw = Double.parseDouble(money);
                // will truncate anything more than two decimal places
                moneyWithdraw = moneyWithdraw * 100;
                int m = (int) moneyWithdraw;
                moneyWithdraw = m/100.0;
                isDouble = true;
            }catch(Exception e) {
                System.out.println("Not a valid monetary amount. Please enter a number with maximum two decimal places.");
            }
        }
        boolean successfulWithdraw = a.withdrawal(moneyWithdraw);
        if (successfulWithdraw) {
            System.out.println("Withdrawal = " + moneyWithdraw + "\nNew balance is $" + a.getBalance());
        }else {
            System.out.println("Insufficient funds\nBalance is " + a.getBalance());
        }
    }

    public void checkBalance() {
        double b = a.getBalance();
        System.out.println("Balance is $" + b);
    }
}