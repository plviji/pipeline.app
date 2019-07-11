package com.revature.pojos;

public class AccountInfo {
	
	private int id;
	private int balance;
	private String type;
	

	public AccountInfo() {}

	public AccountInfo(int id, int balance, String type) {
		super();
		this.id = id;
		this.balance = balance;
		this.type = type;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}


}
