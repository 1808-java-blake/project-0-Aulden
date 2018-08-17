package com.revature.daos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.List;

import com.revature.beans.Account;
import com.revature.utilities.DatabaseManager;

public class AccountToDatabase implements AccountDao{
	private Connection c = null;
	private PreparedStatement stmt = null;
	
	public void createAccount(Account a) {
		if (a == null) {
			return;
		}
		
		c = DatabaseManager.getConnection();
		
		try {
			stmt = c.prepareStatement("INSERT INTO accounts VALUES (?, ?);");
			stmt.setInt(1, a.getId());
			stmt.setInt(2, a.getBalance());
			stmt.executeUpdate();
			
			stmt = c.prepareStatement("INSERT INTO hastransactionhistory VALUES (?, ?);");
			stmt.setInt(1, a.getId());
			stmt.setString(2, "Account Created");
			stmt.executeUpdate();
		}
		catch(SQLException ex) {
			System.out.println(ex.getMessage());
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		finally {
			closeResources();
		}
		
	}
	
	public Account findByAccountId(int accountId) {
				
				c = DatabaseManager.getConnection();
				ResultSet rs = null;
				ResultSet rs2 = null;
				Account a = new Account();
				
				try {
					stmt = c.prepareStatement("SELECT * FROM accounts WHERE id = ?");
					stmt.setInt(1, accountId);
					rs = stmt.executeQuery();
					
					while(rs.next()) {
						a = new Account(rs.getInt("id"), rs.getInt("balance"));
					}
					
					stmt = c.prepareStatement("SELECT history_part FROM hastransactionhistory WHERE id = ?");
					stmt.setInt(1, accountId);
					rs2 = stmt.executeQuery();
					List<String> li = a.getTransactHistory();
					
					while(rs2.next()){
						li.add(rs2.getString("history_part"));
					}
					
					a.setTransactHistory(li);
										
					return a;
				}
				catch(SQLException ex) {
					System.out.println(ex.getMessage());
				}
				catch(Exception ex) {
					ex.printStackTrace();
				}
				finally {
					closeResources();
				}
		
		return null;
	}
	
	public void updateAccount(Account a) {
		if (a == null) {
			return;
		}
		
		c = DatabaseManager.getConnection();
		
		try {
			stmt = c.prepareStatement("UPDATE accounts SET id = ?, balance = ? WHERE id = ?;");
			stmt.setInt(1, a.getId());
			stmt.setInt(2, a.getBalance());
			stmt.setInt(3, a.getId());
			stmt.executeUpdate();
		
			for(String part : a.getTransactHistory()) {
				stmt = c.prepareStatement("INSERT INTO hastransactionhistory SELECT ?, ? WHERE NOT EXISTS (SELECT history_part FROM hastransactionhistory WHERE history_part = ?);");
				stmt.setInt(1, a.getId());
				stmt.setString(2, part);
				stmt.setString(3, part);
				stmt.executeUpdate();
			}
		}
		catch(SQLException ex) {
			System.out.println(ex.getMessage());
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		finally {
			closeResources();
		}
		
	}
	public void deleteAccount(Account a) {
		if (a == null) {
			return;
		}
		
		c = DatabaseManager.getConnection();
		
		try {
			stmt = c.prepareStatement("DELETE FROM accounts WHERE id = ?;");
			stmt.setInt(1, a.getId());
			stmt.executeUpdate();
		}
		catch(SQLException ex) {
			System.out.println(ex.getMessage());
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		finally {
			closeResources();
		}
		
	}
	
	private void closeResources() {

        try {
            if (stmt != null) {
            	stmt.close();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
        	if(c != null) {
        		c.close();
        	}
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
