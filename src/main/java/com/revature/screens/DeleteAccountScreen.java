package com.revature.screens;

import java.util.Scanner;

import org.apache.log4j.Logger;

import com.revature.beans.CurrentUser;
import com.revature.beans.User;
import com.revature.daos.AccountDao;
import com.revature.daos.UserDao;

public class DeleteAccountScreen implements Screen{
	private UserDao ud = UserDao.currentUserDao;
	private AccountDao ad = AccountDao.currentAccountDao;
	private Scanner scan = new Scanner(System.in);
	private Logger log = Logger.getRootLogger();
	
	public Screen start() {
		User user = ud.findByUsernameAndPassword(CurrentUser.username, CurrentUser.password);
		
		while(true) {
		System.out.println();
		System.out.println("Big Bank Account Deletion");
		System.out.println("--------------------------");
		System.out.print("Accounts: ");
		
		for(int id : user.getAccountIds()) {
			System.out.print(id + ", ");
		}
		
		System.out.println("Enter the ID of the account to delete, or 'exit' to return");
		String in = scan.nextLine();
		
		if("exit".equalsIgnoreCase(in)) {
			log.info(user.getUsername() + " exited Delete Account screen");
			break;
		}
		else if(user.getAccountIds().contains(Integer.parseInt(in))) {
			ad.deleteAccount(ad.findByAccountId(Integer.parseInt(in)));
			user.removeAccountID(Integer.parseInt(in));
			log.debug("Account " + Integer.parseInt(in) + " deleted successfully");
			ud.updateUser(user);
			log.debug("User " + user.getUsername() + " deleted successfully");
			System.out.println("Account deleted successfully");
		}
		}
		
		return new HomeScreen();
	}
		
}
