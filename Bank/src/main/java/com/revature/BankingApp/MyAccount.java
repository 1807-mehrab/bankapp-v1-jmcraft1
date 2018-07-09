package com.revature.BankingApp;

public class MyAccount extends Account {

    private double balance;
    public MyAccount(String acctNumber) {
        this.acctNumber = acctNumber;
        balance = 0;
    }

	@Override
	public boolean addFunds(double fundsToAdd) {
        balance += fundsToAdd;
		return true;
	}

	@Override
	public boolean withdrawal(double moneySpent) {
        if (balance - moneySpent < 0) {
            return false;
        }else {
            balance = balance - moneySpent;
            return true;
        }
	}

	@Override
	public double getBalance() {
		// TODO Auto-generated method stub
		return balance;
	}
}