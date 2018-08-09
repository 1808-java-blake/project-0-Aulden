package com.revature.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Account implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 14L;
	private int id;
	private int balance;
	private List<String> transactHistory = new ArrayList<>();
	
	public List<String> getTransactHistory() {
		return transactHistory;
	}
	public void addTransactHistory(String s) {
		transactHistory.add(s);
	}
	@Override
	public String toString() {
		return "Account [id=" + id + ", balance=" + balance + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + balance;
		result = prime * result + id;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		if (balance != other.balance)
			return false;
		if (id != other.id)
			return false;
		return true;
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
	public Account(int id, int balance) {
		super();
		this.id = id;
		this.balance = balance;
	}
	public Account() {
		super();
		Random r = new Random();
		this.id = r.nextInt(100000)+1;
		this.balance = 0;
		// TODO Auto-generated constructor stub
	}
}
