package com.revature.customers;

public class SaveTheChangeCustomer extends Customer {

	public SaveTheChangeCustomer(String fName, String lName, String address, String city, String state, String zip,
			String phone, String email, String password) {
		super(fName, lName, address, city, state, zip, phone, email, password);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean deposit(double fundsToAdd) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean withdraw(double fundsToSubtract) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public double checkBalance() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean transfer(long acct_from, long acct_to) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateProfile(String email) {
		// TODO Auto-generated method stub
		return false;
	}

	
	
	

}
