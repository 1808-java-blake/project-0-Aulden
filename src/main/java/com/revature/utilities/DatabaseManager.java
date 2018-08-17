package com.revature.utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {
	private static final String username = "postgres";
	private static final String password = "";
	private static Connection c = null;
	
	public static Connection getConnection(){
		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection("jdbc:postgresql://revature-1808.clnjocmokdqy.us-west-2.rds.amazonaws.com:5432/postgres?currentSchema=big_bank", DatabaseManager.username, DatabaseManager.password);
		}
		catch(SQLException ex) {
			ex.printStackTrace();
			System.out.println("Error 1");
		}
		catch(Exception ex) {
			ex.printStackTrace();
			System.out.println("Error 2");
		}
		
		return c;
	}
}
