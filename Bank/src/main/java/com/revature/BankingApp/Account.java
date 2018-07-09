package com.revature.BankingApp;

public abstract class Account {
    protected double balance = 0;
    protected String acctNumber;

    public Account(){}

    public Account(String acctNumber) {
       
        this.acctNumber = acctNumber;
    }

    public abstract boolean addFunds(double fundsToAdd);

    public abstract boolean withdrawal(double moneySpent);

    public abstract double getBalance();


}