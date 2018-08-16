package com.revature.daos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.revature.beans.User;
import com.revature.utilities.DatabaseManager;

public class UserToDatabase implements UserDao {
	
	public void createUser(User u) {
		if (u == null) {
			return;
		}
		
		Connection c = DatabaseManager.getConnection();
		Statement stmt = null;
		
		try {
			stmt = c.createStatement();
			stmt.executeUpdate(String.format("INSERT INTO users VALUES ('%s', '%s', '%s', '%s', %d, %b);", u.getUsername(), u.getPassword(), u.getFirstName(), u.getLastName(), u.getAge(), u.isAdmin()));
		
			for(int id : u.getAccountIds()) {
				stmt.executeUpdate(String.format("INSERT INTO hasaccounts SELECT '%s', %d WHERE NOT EXISTS (SELECT id FROM hasaccounts WHERE id = %d);", u.getUsername(), id, id));
			}
		}
		catch(SQLException ex) {
			System.out.println(ex.getMessage());
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		
	}
	
	public User findByUsernameAndPassword(String username, String password) {
		// verify that what was passed in is not null
		if (username == null || password == null) {
			return null;
		}
		
		Connection c = DatabaseManager.getConnection();
		Statement stmt = null;
		Statement stmt2 = null;
		ResultSet rs = null;
		ResultSet rs2 = null;
		User u = new User();
		
		try {
			stmt = c.createStatement();
			rs = stmt.executeQuery(String.format("SELECT * FROM users WHERE username = '%s' AND password = '%s'", username, password));
			
			while(rs.next()) {
				u = new User(rs.getString("username"), rs.getString("password"), rs.getString("firstname"), rs.getString("lastname"), rs.getInt("age"), rs.getBoolean("admin"));
			}
			
			stmt2 = c.createStatement();
			if("default".equals(u.getUsername())) {
				return null;
			}
			rs2 = stmt2.executeQuery(String.format("SELECT id FROM hasaccounts WHERE username = '%s'", u.getUsername()));
			List<Integer> li = u.getAccountIds();
			
			while(rs2.next()){
				li.add(rs2.getInt("id"));
//				System.out.println(rs2.getInt("id"));
			}
			
			u.setAccountIds(li);
			
			return u;
		}
		catch(SQLException ex) {
			System.out.println(ex.getMessage());
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		
		return null;
	}
	
	public void updateUser(User u) {
		if (u == null) {
			return;
		}
		
		Connection c = DatabaseManager.getConnection();
		Statement stmt = null;
		
		try {
			stmt = c.createStatement();
			stmt.executeUpdate(String.format("UPDATE users SET username = '%s', password = '%s', firstname = '%s', lastname = '%s', age = %d, admin = %b WHERE username = '%s';", u.getUsername(), u.getPassword(), u.getFirstName(), u.getLastName(), u.getAge(), u.isAdmin(), u.getUsername()));
			
			for(int id : u.getAccountIds()) {
				stmt.executeUpdate(String.format("INSERT INTO hasaccounts SELECT '%s', %d WHERE NOT EXISTS (SELECT id FROM hasaccounts WHERE id = %d);", u.getUsername(), id, id));
			}
		}
		catch(SQLException ex) {
			System.out.println(ex.getMessage());
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public void deleteUser(User u) {
		
	}
	
	public User findByUsername(String username) {
		// verify that what was passed in is not null
				if (username == null) {
					return null;
				}
				
				Connection c = DatabaseManager.getConnection();
				Statement stmt = null;
				Statement stmt2 = null;
				ResultSet rs = null;
				ResultSet rs2 = null;
				User u = new User();
				
				try {
					stmt = c.createStatement();
					rs = stmt.executeQuery(String.format("SELECT * FROM users WHERE username = '%s';", username));
					
					while(rs.next()) {
						u = new User(rs.getString("username"), rs.getString("password"), rs.getString("firstname"), rs.getString("lastname"), rs.getInt("age"), rs.getBoolean("admin"));
					}
					
					stmt2 = c.createStatement();
					if("default".equals(u.getUsername())) {
						return null;
					}
					rs2 = stmt2.executeQuery(String.format("SELECT id FROM hasaccounts WHERE username = '%s'", u.getUsername()));
					List<Integer> li = u.getAccountIds();
					
					while(rs2.next()){
						li.add(rs2.getInt("id"));
					}
					
					u.setAccountIds(li);
					
					return u;
				}
				catch(SQLException ex) {
					System.out.println(ex.getMessage());
				}
				catch(Exception ex) {
					ex.printStackTrace();
				}
				
				return null;
	}

}
