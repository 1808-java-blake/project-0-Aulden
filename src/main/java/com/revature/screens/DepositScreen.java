package com.revature.screens;

import java.util.Scanner;

import org.apache.log4j.Logger;

import com.revature.beans.Account;
import com.revature.beans.CurrentUser;
import com.revature.beans.User;
import com.revature.daos.AccountDao;
import com.revature.daos.UserDao;

public class DepositScreen implements Screen{
	private Scanner scan = new Scanner(System.in);
	private UserDao ud = UserDao.currentUserDao;
	private AccountDao ad = AccountDao.currentAccountDao;
	private User u = ud.findByUsernameAndPassword(CurrentUser.username, CurrentUser.password);
	private Logger log = Logger.getRootLogger();
	
	public Screen start() {
		
		while(true) {
		System.out.println();
		System.out.println("Big Bank Deposit");
		System.out.println("-----------------");
		System.out.print("Account IDs: ");
		//loop through all account ids for user
		for(int id : u.getAccountIds()) {
			System.out.print(id + ", ");
		}
		System.out.println();
		
		System.out.println("Enter the account number you would like to deposit to, or 'exit' to return");
		String in = scan.nextLine();
		
		if("exit".equalsIgnoreCase(in)) {
			log.info(u.getUsername() + " exited Deposit screen");
			break;
		}
		else if(u.getAccountIds().contains(Integer.parseInt(in))){
			System.out.print("Enter amount to deposit: $");
			String dep = scan.nextLine();
			try {
				Account a = ad.findByAccountId(Integer.parseInt(in));
				a.deposit(Integer.parseInt(dep));
				log.debug("$" + Integer.parseInt(dep) + " deposited to " + Integer.parseInt(in) + " successfully");
				ad.updateAccount(a);
				log.debug("Account " + Integer.parseInt(in) + " updated successfully");
			}
			catch(Exception ex) {
				log.debug("Error encountered" + u.getUsername());
				System.out.println("Error: Please try again");
			}
		}
		else{
			log.info("Invalid input from " + u.getUsername());
			System.out.println("Invalid input, please try again");
		}
		
	}
		
		return new HomeScreen();
		
	}
}
