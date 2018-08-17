package com.revature.screens;

import java.util.Scanner;

import org.apache.log4j.Logger;

import com.revature.beans.CurrentUser;
import com.revature.beans.User;
import com.revature.daos.AccountDao;
import com.revature.daos.UserDao;

public class ViewTransactionHistoryAdminScreen implements Screen{
	private UserDao ud = UserDao.currentUserDao;
	private AccountDao ad = AccountDao.currentAccountDao;
	private Scanner scan = new Scanner(System.in);
	private Logger log = Logger.getRootLogger();
	
	public Screen start() {
		User user = ud.findByUsernameAndPassword(CurrentUser.username, CurrentUser.password);
		
		System.out.println("Big Bank Find User's Transaction History");
		System.out.println("-----------------------------------------");
		
		if(!user.isAdmin()) {
			log.info("Non-admin " + user.getUsername() + " attempted to use admin screen");
			System.out.println("You're no admin... get out of here!");
			System.out.println();
			System.out.println();
			
			log.info(user.getUsername() + " entered Home screen");
			return new HomeScreen();
		}
		
		while(true) {
			System.out.println();
			System.out.println("Big Bank Admin Screen");
			System.out.println("----------------------");
			System.out.println("Enter a username to see their accounts' transaction history, or exit to return");
			String un = scan.nextLine();
			
			if("exit".equalsIgnoreCase(un)) {
				log.info(user.getUsername() + " exited Admin screen");
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
				log.info("User not found");
				System.out.println("Error: Please try again");
			}
		
		}
		
		log.info(user.getUsername() + " entered Home screen");
		return new HomeScreen();
	}
	
}
