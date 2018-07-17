package com.revature.interfaces;

public interface CustomerActions {
	
	boolean deposit(double fundsToAdd);
	
	boolean withdraw(double fundsToSubtract);
	
	double checkBalance();
	
	boolean transfer(long acct_from, long acct_to);

}
