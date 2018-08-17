package com.revature.screens;

import java.util.Scanner;

import org.apache.log4j.Logger;

import com.revature.beans.CurrentUser;
import com.revature.beans.User;
import com.revature.beans.Account;
import com.revature.daos.AccountDao;
import com.revature.daos.UserDao;

public class WithdrawalScreen implements Screen{
	private Scanner scan = new Scanner(System.in);
	private UserDao ud = UserDao.currentUserDao;
	private AccountDao ad = AccountDao.currentAccountDao;
	private User u = ud.findByUsernameAndPassword(CurrentUser.username, CurrentUser.password);
	private Logger log = Logger.getRootLogger();
	
	public Screen start() {
		
		while(true) {
		System.out.println();
		System.out.println("Big Bank Withdrawal");
		System.out.println("-----------------");
		System.out.print("Account IDs: ");
		//loop through all account ids for user
		for(int id : u.getAccountIds()) {
			System.out.print(id + ", ");
		}
		System.out.println();
		
		System.out.println("Enter the account number you would like to withdraw from, or 'exit' to return");
		String in = scan.nextLine();
		
		if("exit".equalsIgnoreCase(in)) {
			log.info(u.getUsername() + " exited Withdrawal screen");
			break;
		}
		else if(u.getAccountIds().contains(Integer.parseInt(in))){
			System.out.print("Enter amount to withdraw: $");
			String withdrawal = scan.nextLine();
			try {
				Account a = ad.findByAccountId(Integer.parseInt(in));
				a.withdraw(Integer.parseInt(withdrawal));
				log.info("Successfully withdrew $" + Integer.parseInt(withdrawal));
				ad.updateAccount(a);
				log.info("Updated " + a.getId() + " successfully");
			}
			catch(Exception ex) {
				log.debug("Error occurred");
				System.out.println("Error: Please try again");
				ex.printStackTrace();
			}
		}
		else{
			log.info("Invalid input");
			System.out.println("Invalid input, please try again");
		}
		
	}
		
		log.info(u.getUsername() + " entered Home screen");
		return new HomeScreen();
		
	}
}
