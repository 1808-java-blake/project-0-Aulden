package com.revature.daos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.revature.beans.Account;
import com.revature.utilities.DatabaseManager;

public class AccountToDatabase implements AccountDao{
	
	public void createAccount(Account a) {
		if (a == null) {
			return;
		}
		
		Connection c = DatabaseManager.getConnection();
		Statement stmt = null;
		Statement stmt2 = null;
		
		try {
			stmt = c.createStatement();
			stmt.executeUpdate(String.format("INSERT INTO accounts VALUES (%d, %d);", a.getId(), a.getBalance()));
		
			stmt2 = c.createStatement();
			stmt2.executeUpdate(String.format("INSERT INTO hastransactionhistory VALUES (%d, '%s');", a.getId(), "Account Created"));
		}
		catch(SQLException ex) {
			System.out.println(ex.getMessage());
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public Account findByAccountId(int accountId) {
				
				Connection c = DatabaseManager.getConnection();
				Statement stmt = null;
				Statement stmt2 = null;
				ResultSet rs = null;
				ResultSet rs2 = null;
				Account a = new Account();
				
				try {
					stmt = c.createStatement();
					rs = stmt.executeQuery(String.format("SELECT * FROM accounts WHERE id = %d", accountId));
					
					while(rs.next()) {
						a = new Account(rs.getInt("id"), rs.getInt("balance"));
					}
					
					stmt2 = c.createStatement();
					rs2 = stmt2.executeQuery(String.format("SELECT history_part FROM hastransactionhistory WHERE id = %d", a.getId()));
					List<String> li = a.getTransactHistory();
					
					while(rs2.next()){
						li.add(rs2.getString("history_part"));
					}
					
					a.setTransactHistory(li);
					
//					System.out.println(a);
					
					return a;
				}
				catch(SQLException ex) {
					System.out.println(ex.getMessage());
				}
				catch(Exception ex) {
					ex.printStackTrace();
				}
		
		return null;
	}
	
	public void updateAccount(Account a) {
		if (a == null) {
			return;
		}
		
		Connection c = DatabaseManager.getConnection();
		Statement stmt = null;
		
		try {
			stmt = c.createStatement();
			stmt.executeUpdate(String.format("UPDATE accounts SET id = %d, balance = %d WHERE id = %d;", a.getId(), a.getBalance(), a.getId()));
		
			for(String part : a.getTransactHistory()) {
				stmt.executeUpdate(String.format("INSERT INTO hastransactionhistory SELECT %d, '%s' WHERE NOT EXISTS (SELECT history_part FROM hastransactionhistory WHERE history_part = '%s');", a.getId(), part, part));
			}
		}
		catch(SQLException ex) {
			System.out.println(ex.getMessage());
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	public void deleteAccount(Account a) {
		if (a == null) {
			return;
		}
		
		Connection c = DatabaseManager.getConnection();
		Statement stmt = null;
		
		try {
			stmt = c.createStatement();
			stmt.executeUpdate(String.format("DELETE FROM accounts WHERE id = %d;", a.getId()));
		}
		catch(SQLException ex) {
			System.out.println(ex.getMessage());
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}
}
