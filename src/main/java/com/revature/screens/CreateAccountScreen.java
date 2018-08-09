package com.revature.screens;

import java.util.Scanner;

import com.revature.beans.Account;
import com.revature.beans.CurrentUser;
import com.revature.beans.User;
import com.revature.daos.AccountDao;
import com.revature.daos.UserDao;

public class CreateAccountScreen implements Screen{
	private UserDao ud = UserDao.currentUserDao;
	private AccountDao ad = AccountDao.currentAccountDao;
	private Scanner scan = new Scanner(System.in);
	
	public Screen start() {
		User user = ud.findByUsernameAndPassword(CurrentUser.username, CurrentUser.password);
		
		while(true) {
		System.out.println("Big Bank Account Creation");
		System.out.println("--------------------------");
		System.out.print("Accounts: ");
		
		for(int id : user.getAccountIds()) {
			System.out.print(id + ", ");
		}
		
		System.out.println("Create a new account? (y/n)");
		String in = scan.nextLine();
		
		if("n".equalsIgnoreCase(in)) {
			break;
		}
		else if("y".equalsIgnoreCase(in)) {
			Account newAct = new Account();
			ad.createAccount(newAct);
			user.addAccountId(newAct.getId());
			ud.updateUser(user);
			System.out.println("Account created successfully with ID " + newAct.getId());
		}
		}
		
		return new HomeScreen();
	}
		
}
