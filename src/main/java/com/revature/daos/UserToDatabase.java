package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.revature.beans.User;
import com.revature.utilities.DatabaseManager;

public class UserToDatabase implements UserDao {
	private Connection c = null;
	private PreparedStatement stmt = null;
	
	public void createUser(User u) {
		if (u == null) {
			return;
		}
		
		c = DatabaseManager.getConnection();
		
		try {
			stmt = c.prepareStatement("INSERT INTO users VALUES (?, ?, ?, ?, ?, ?);");
			stmt.setString(1, u.getUsername());
			stmt.setString(2, u.getPassword());
			stmt.setString(3, u.getFirstName());
			stmt.setString(4, u.getLastName());
			stmt.setInt(5, u.getAge());
			stmt.setBoolean(6, u.isAdmin());
			
			stmt.executeUpdate();
		
			for(int id : u.getAccountIds()) {
				stmt = c.prepareStatement("INSERT INTO hasaccounts SELECT ?, ? WHERE NOT EXISTS (SELECT id FROM hasaccounts WHERE id = ?);");
				stmt.setString(1, u.getUsername());
				stmt.setInt(2, id);
				stmt.setInt(3, id);
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
	
	public User findByUsernameAndPassword(String username, String password) {
		// verify that what was passed in is not null
		if (username == null || password == null) {
			return null;
		}
		
		c = DatabaseManager.getConnection();
		ResultSet rs = null;
		ResultSet rs2 = null;
		User u = new User();
		
		try {
			stmt = c.prepareStatement("SELECT * FROM users WHERE username = ? AND password = ?");
			stmt.setString(1, username);
			stmt.setString(2, password);
			
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				u = new User(rs.getString("username"), rs.getString("password"), rs.getString("firstname"), rs.getString("lastname"), rs.getInt("age"), rs.getBoolean("admin"));
			}
			
			if("default".equals(u.getUsername())) {
				return null;
			}
			stmt = c.prepareStatement("SELECT id FROM hasaccounts WHERE username = ?");
			stmt.setString(1, u.getUsername());
			rs2 = stmt.executeQuery();
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
		finally {
			closeResources();
		}
		
		return null;
	}
	
	public void updateUser(User u) {
		if (u == null) {
			return;
		}
		
		c = DatabaseManager.getConnection();
		
		try {
			stmt = c.prepareStatement("UPDATE users SET username = ?, password = ?, firstname = ?, lastname = ?, age = ?, admin = ? WHERE username = ?;");
			stmt.setString(1, u.getUsername());
			stmt.setString(2, u.getPassword());
			stmt.setString(3, u.getFirstName());
			stmt.setString(4, u.getLastName());
			stmt.setInt(5, u.getAge());
			stmt.setBoolean(6, u.isAdmin());
			stmt.setString(7, u.getUsername());
			
			for(int id : u.getAccountIds()) {
				stmt = c.prepareStatement("INSERT INTO hasaccounts SELECT ?, ? WHERE NOT EXISTS (SELECT id FROM hasaccounts WHERE id = ?);");
				stmt.setString(1, u.getUsername());
				stmt.setInt(2, id);
				stmt.setInt(3, id);
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
	
	public void deleteUser(User u) {
		
	}
	
	public User findByUsername(String username) {
		// verify that what was passed in is not null
				if (username == null) {
					return null;
				}
				
				c = DatabaseManager.getConnection();
				ResultSet rs = null;
				ResultSet rs2 = null;
				User u = new User();
				
				try {
					stmt = c.prepareStatement("SELECT * FROM users WHERE username = ?");
					stmt.setString(1, username);
					rs = stmt.executeQuery();
					
					while(rs.next()) {
						u = new User(rs.getString("username"), rs.getString("password"), rs.getString("firstname"), rs.getString("lastname"), rs.getInt("age"), rs.getBoolean("admin"));
					}
					
					if("default".equals(u.getUsername())) {
						return null;
					}
					stmt = c.prepareStatement("SELECT id FROM hasaccounts WHERE username = ?");
					stmt.setString(1, u.getUsername());
					rs2 = stmt.executeQuery();
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
				finally {
					closeResources();
				}
				
				return null;
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
