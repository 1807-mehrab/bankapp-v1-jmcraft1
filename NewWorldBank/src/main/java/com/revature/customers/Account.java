package com.revature.customers;

public class Account {

	private String account_id, account_type;
	private int customer_id;
	private double balance;

	public Account(String account_id, String account_type, int customer_id, double balance) {
		super();
		this.account_id = account_id;
		this.account_type = account_type;
		this.customer_id = customer_id;
		this.balance = balance;
	}

	public String getAccount_id() {
		return account_id;
	}

	public void setAccount_id(String account_id) {
		this.account_id = account_id;
	}

	public String getAccount_type() {
		return account_type;
	}

	public void setAccount_type(String account_type) {
		this.account_type = account_type;
	}

	public int getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

}
