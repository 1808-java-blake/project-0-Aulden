package com.revature.screens;

import java.util.Scanner;

import com.revature.beans.CurrentUser;
import com.revature.beans.User;
import com.revature.daos.AccountDao;
import com.revature.daos.UserDao;

public class ViewTransactionHistoryAdminScreen implements Screen{
	private UserDao ud = UserDao.currentUserDao;
	private AccountDao ad = AccountDao.currentAccountDao;
	private Scanner scan = new Scanner(System.in);
	
	public Screen start() {
		User user = ud.findByUsernameAndPassword(CurrentUser.username, CurrentUser.password);
		
		System.out.println("Big Bank Find User's Transaction History");
		System.out.println("-----------------------------------------");
		
		if(!user.isAdmin()) {
			System.out.println("You're no admin... get out of here!");
			System.out.println();
			System.out.println();
			
			return new HomeScreen();
		}
		
		while(true) {
			System.out.println();
			System.out.println("Big Bank Admin Screen");
			System.out.println("----------------------");
			System.out.println("Enter a username to see their accounts' transaction history, or exit to return");
			String un = scan.nextLine();
			
			if("exit".equalsIgnoreCase(un)) {
				break;
			}
			
			try {
				User u = ud.findByUsername(un);
				
				System.out.println();
				System.out.println(un + "'s Accounts");
				System.out.println("---------------------------");
				for(int id : u.getAccountIds()) {
					System.out.println("Account with ID " + id + " has the following transaction history: " + ad.findByAccountId(id).getTransactHistory());
				}
				
				System.out.println("Hit enter to continue...");
				scan.nextLine();
			}
			catch(Exception ex) {
				System.out.println("Error: Please try again");
			}
		
		}
		
		return new HomeScreen();
	}
	
}
